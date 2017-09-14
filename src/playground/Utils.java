package playground;

public class Utils {
  public static class ListNode {
    int val;
    ListNode next;
  
    ListNode(int x) {
      val = x;
    }
  
    public static ListNode create(int... given) {
      if (given.length <= 0)
        return null;
      ListNode root = null;
      ListNode node = null;
      int i = 0;
      for (int n : given) {
        if (i == 0) {
          node = root = new ListNode(n);
        } else {
          ListNode newNode = new ListNode(n);
          node.next = newNode;
          node = newNode;
        }
        i++;
      }
      return root;
    }
  }
 
  public static void print(String s) {
    System.out.print(s);
  }
  
  public static void print(int i) {
    System.out.print(i);
  }
  
  public static void println(String s) {
    System.out.println(s);
  }
  
  public static void println() {
    System.out.println();
  }
  
  public static void println(int i) {
    System.out.println(i);
  }
  
  public static void println(double d) {
    System.out.println(d);
  }
  
  public static void printf(String format, Object...args) {
    System.out.printf(format, args);
  }
  
  public static <T> void print(Iterable<T> iter) {
    if (iter == null) {
      println("<null>");
    } else {
      print("{ ");
      iter.forEach(t -> { print(t.toString()) ; print(", "); });
      println("}");
    }
  }
  
  public static void printIntArray(int[] arr) {
    if (arr == null) {
      println("<null>");
    } else {
      print("{ ");
      for (int i : arr) {
        print(Integer.toString(i));
        print(", ");
      }
      println("}");
    }
  }
  
  public static void printIntMatrix(int[][] matrix) {
    int maxSize = 1;
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[i].length; ++j) {
        int len = Integer.toString(matrix[i][j]).length();
        if (len > maxSize)
          maxSize = len;
      }
    }
    printIntMatrix(maxSize + 1, matrix);
  }
  
  public static void printIntMatrix(int columnWidth, int[][] matrix) {
    if (matrix == null) {
      println("<null>");
    } else {
      for (int i = 0; i < matrix.length; ++i) {
        int[] row = matrix[i];
        for (int j = 0; j < row.length; ++j) {
          printf("%" + columnWidth + "d", row[j]);
        }
        println();
      }
    }
  }
  
  public static void printListNode(ListNode node) {
    if (node == null)
      println("<empty>");
    while (node != null) {
      print(Integer.toString(node.val));
      node = node.next;
      if (node != null) {
        print(" -> ");
      }
    }
    println("");
  }
}
