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

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public static TreeNode from(int val, TreeNode left, TreeNode right) {
      return new TreeNode(val, left, right);
    }

    public static TreeNode from(int val, TreeNode left) {
      return from(val, left, null);
    }

    public static TreeNode from(int val) {
      return from(val, null, null);
    }

    public static void insert(int val, TreeNode node) {
      if (node == null)
        return;
      if (node.left == null && node.val > val) {
        node.left = from(val);
      } else if (node.right == null && node.val < val) {
        node.right = from(val);
      } else {
        if (node.val > val) insert(val, node.left);
        else if (node.val < val) insert(val, node.right);
      }
    }

    /**
     * Builds a binary search tree and returns the root.
     */
    public static TreeNode create(int...given) {
      if (given.length <= 0)
        return null;
      TreeNode node = from(given[0]);
      for (int i = 0; i < given.length; ++i) {
        insert(given[i], node);
      }
      return node;
    }

    public static int maxDepth(TreeNode node) {
      return maxDepthRec(1, node);
    }

    private static int maxDepthRec(int depth, TreeNode node) {
      if (node == null)
        return depth - 1;

      return Math.max(maxDepthRec(depth + 1, node.left), maxDepthRec(depth + 1, node.right));
    }

    public static boolean allNull(TreeNode node) {
      return (node == null || (node.left == null && node.right == null));
    }

    public static boolean allNull(java.util.List<TreeNode> nodes) {
      if (nodes == null)
        return true;
      for (TreeNode node : nodes)
        if (node != null)
          return false;
      return true;
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

  public static void println(boolean b) {
    System.out.println(Boolean.toString(b));
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

  public static <K, V> void print(java.util.Map<K, V> map) {
    if (map == null) {
      println("<null>");
    } else {
      print("{ ");
      map.forEach((k, v) -> {
        print(k.toString());
        print(": ");
        print(v.toString());
        print(", ");
      });
      println("}");
    }
  }
  
  public static void print(int[] arr) {
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

  public static void printCharHistArray(int[] arr) {
    if (arr == null) {
      println("<null>");
    } else {
      print("{ ");
      for (int i = 0; i < arr.length; ++i) {
        int val = arr[i];
        if (val > 0) {
          print((char)i + "");
          print("(");
          print(i);
          print("): ");
          print(Integer.toString(val));
          print(", ");
        }
      }
      println("}");
    }
  }

  public static void print(String[] arr) {
    if (arr == null) {
      println("<null>");
    } else {
      print("{ ");
      for (String s : arr) {
        print(s);
        print(", ");
      }
      println("}");
    }
  }
  
  public static void print(int[][] matrix) {
    int maxSize = 1;
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[i].length; ++j) {
        int len = Integer.toString(matrix[i][j]).length();
        if (len > maxSize)
          maxSize = len;
      }
    }
    print(maxSize + 1, matrix);
  }
  
  public static void print(int columnWidth, int[][] matrix) {
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

  public static void printSpace(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  // Courtesy https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
  public static void print(TreeNode node) {
    int depth = TreeNode.maxDepth(node);
    printNodeLevel(java.util.Collections.singletonList(node), 1, depth);
  }

  private static void printNodeLevel(java.util.List<TreeNode> nodes, int level, int maxDepth) {
    if (TreeNode.allNull(nodes))
      return;

    int floor = maxDepth - level;
    int edgeLines = (int)Math.pow(2, Math.max(floor - 1, 0));
    int firstSpaces = (int)Math.pow(2, floor) - 1;
    int betweenSpaces = (int)Math.pow(2, floor + 1) - 1;

    printSpace(firstSpaces);

    java.util.List<TreeNode> newNodes = new java.util.ArrayList<>();

    for (TreeNode node : nodes) {
      if (node != null) {
        print(node.val);
        newNodes.add(node.left);
        newNodes.add(node.right);
      } else {
        newNodes.add(null);
        newNodes.add(null);
        printSpace(1);
      }
      printSpace(betweenSpaces);
    }

    println();

    for (int i = 1; i <= edgeLines; ++i) {
      for (int j = 0; j < nodes.size(); ++j) {
        printSpace(firstSpaces - i);
        if (nodes.get(j) == null) {
          printSpace(edgeLines + edgeLines + i + 1);
          continue;
        }

        if (nodes.get(j).left != null)
          print("/");
        else
          printSpace(1);

        printSpace(i + i - 1);

        if (nodes.get(j).right != null)
          print("\\");
        else
          printSpace(1);

        printSpace(edgeLines + edgeLines - i);
      }
      println();
    }

    printNodeLevel(newNodes, level + 1, maxDepth);
  }
  
  public static void print(ListNode node) {
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
