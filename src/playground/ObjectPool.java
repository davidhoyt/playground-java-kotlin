package playground;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

// Makeshift usage (due to time this is easier than an actual diagram):
//  - new ObjectPool<T>(min, max, () -> new T)
//    - automatically initializes
//  - acquire()
//  - Use provided Lease<T>
//  - When done, call lease.release() which returns the instance to the pool
//  - When done with the pool, call pool.destroy()

// Generally works by acquiring leases until hitting max. Primary interaction
// with the pool is mediated through a Lease instance which retains a reference
// to the pool from which it was allocated. This eases the burden on the user
// so they do not have to release it directly to the same pool. It is tracked
// automatically.
//
// My preference is to actually leverage higher-order functions to control
// the acquisition and release of resources. But the spec asks for explicit
// acquire and release methods. This would also benefit from the use of
// dependent typing to bind the lease or resource with its source pool.
//
// We could have used a stack or queue of used and unused resources and simply
// controlled the movement across the 2 stacks. The total size of both would
// never exceed the max provided.
//
// It wasn't clear if the expectation was to have working code. "Design" for me
// meant a general approach and/or outline.
public class ObjectPool<T> {
  private AtomicInteger remaining;
  private AtomicBoolean destroyed = new AtomicBoolean(false);
  private Supplier<T> initInstance;
  private ArrayBlockingQueue<T> pool;
  private ConcurrentLinkedDeque<Lease> leases;
  
  public class Lease {
    private T instance;
    
    public Lease(T instance) {
      this.instance = instance;
    }
    
    public T get() {
      return instance;
    }
    
    /**
     * Things to look out for:
     * - Double releases
     * - Concurrent releases
     * - Destroyed object pool
     */
    public void release() {
      pool.add(instance);
      leases.remove(this);
    }
  }
  
  public ObjectPool(int min, int max, Supplier<T> initInstance) {
    if (min < 1)
      throw new IllegalStateException("Minimum must be greater than zero");
    if (max < min)
      throw new IllegalStateException("Maximum must be greater than the minimum");
    this.initInstance = initInstance;
    this.remaining = new AtomicInteger(max - min);
    this.pool = new ArrayBlockingQueue<>(max);
    this.leases = new ConcurrentLinkedDeque<>();
    
    init(min);
  }
  
  // Initialize the pool by instantiating the minimum number of instances.
  private void init(int min) {
    for (int i = 0; i < min; ++i) {
      pool.add(initInstance.get());
    }
  }
  
  public void use(Consumer<T> use) throws InterruptedException {
    // Acquire a resource from the pool.
    Lease lease = null;
    try {
      lease = acquire();
      use.accept(lease.get());
    } finally {
      if (lease != null) {
        // Release it automatically.
        lease.release();
      }
    }
  }
  
  public Lease acquire() throws InterruptedException {
    // Acquire a resource.
    // Might include having to add additional instances since only the min
    // was initially added. It can automatically create instances through
    // the constructor's initInstance.
    // Would typically lock and block. ArrayBlockingQueue takes care of a
    // lot of this bookkeeping. Otherwise I might use a Semaphore or
    // CountDownLatch to handle the locking.
    //
    // Considerations:
    //   - Concurrent access
    //       (could reduce contention by striping access using multiple locks)
    
    if (!destroyed.get()) {
      T instance = pool.poll();
      if (instance == null) {
        if (remaining.decrementAndGet() > 0) {
          pool.add(initInstance.get());
        }
        instance = pool.take();
      }
      Lease lease = new Lease(instance);
      leases.add(lease);
      return lease;
    } else {
      throw new IllegalStateException("Cannot acquire a lease on a destroyed pool");
    }
  }
  
  /**
   * Iterate through all referenced leases and release them.
   */
  public void destroy() {
    if (!destroyed.get()) {
      destroyed.set(true);
      // Release all leases.
      for (Lease lease : leases) {
        lease.release();
      }
      leases.clear();
    }
  }
  
  public static void main(String[] args) throws InterruptedException {
    AtomicInteger ai = new AtomicInteger(1);
    ObjectPool<Integer> pool = new ObjectPool<>(15, 20, () -> ai.getAndIncrement());
    
    // Acquires and releases a lease automatically for the duration of the provided
    // function.
    pool.use(instance -> System.out.println("Received " + instance));
    
    ObjectPool<Integer>.Lease lease = pool.acquire();
    System.out.println("Received " + lease.get());
    lease.release();
    
    // Test reuse.
    for (int i = 0; i < 50; ++i) {
      lease = pool.acquire();
      System.out.println(i + ": " + lease.get());
      lease.release();
    }
    
    System.out.println("DONE");
    
    pool.destroy();
  }
}