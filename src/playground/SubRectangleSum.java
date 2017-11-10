package playground;

// https://www.careercup.com/question?id=5154229209530368

//In order to compute the sum of all the elements within the area of rectangle in O(1) running time
// we can preprocess the Matrix to have in each element of the Matrix the sum of all the elements
// between (0,0) and the lowerRight corner. Then we can use this preprocessed Matrix to get the sum
// for all the elements between upperLeft and lowerRight by getting the sum at lower right, minus
// the sum at the element on the bottom left-1 of the rectangle (that will subtract all the elements
// on the left os the Rectangle), minus the sum at the element on the top-1 right (that will subtract
// all the elements above the rectangle, and then we have to readd the elements that are both on the
// left and above the reactangle, because we have subtracted them 2 times, so we add the sum at the
// top-1 left-1 corner. In this way we can compute the sum of all the elements in a rectangle inside
// a matrix we just 3 operations for any dimension.

/*
n rows * m columns matrix.

0 1 3 4 5
9 2 -1 9 0
2 3 8 7 4

3x5

sum((1, 2) - (2, 4)) = -1 + 9 + 8 + 7 + 4 = ?
sum((2, 2) - (2, 4)) = 8 + 7 + 4 = ?

sum - calculation: O(1)
total space usage : O(n * m)

=======================
a = [0 1 3 4 5]

n > i > 0
arr[n] = [a[0], arr[i-1] + a[i], ...] = [0, 1, 4, 8, 13]

sum(1, 3) = 8 = arr[3] - arr[0]

O(n^2 + m^2)

sum - calculation : O(1)
total space usage : O(n)

any better? :)

*/


import java.io.*;

import java.util.*;


class SubRectangleSum {
  private int[][] table;
  private int n;
  private int m;

  public SubRectangleSum(int[][] arr) {
    n = arr.length;
    m = arr[0].length;

    table = new int[n][m];

    for (int i = 0; i < n; ++i) {
      table[i] = new int[m];
      for (int j = 0; j < m; ++j) {
          buildTable(j, i, arr);
      }
    }

    printIntMatrix(table);
  }

  private int orZero(int x, int y) {
      if (x < 0 || y < 0)
          return 0;
      else
          return table[y][x];
  }

  private void buildTable(int x, int y, int[][] arr) {
      for (int i = 0; i < n; i ++) {
          for (int j = 0; j < m; ++j) {
              table[i][j] = arr[i][j] + orZero(j, i - 1) + orZero(j - 1, i) - orZero(j - 1, i - 1);
          }
      }
  }

  int sum(int y1, int x1, int y2, int x2) {
    // Sanitize inputs.

    // (1, 2) (2, 3)
    //
    // 0  1   3  4  5
    //      +---------+
    // 9  2 |-1  9  0 |
    //      |         |
    // 2  3 | 8  7  4 |
    //      +---------+

    int lowerRightPortion = orZero(x2, y2);
    int lowerLeftPortion = orZero(x1 - 1, y2);
    int upperRightPortion = orZero(x2, y1 - 1);
    int upperLeftPortion = orZero(x1 - 1, y1 - 1);
    return lowerRightPortion + upperLeftPortion - lowerLeftPortion - upperRightPortion;
  }

  public static void main(String[] args) {
//    int[][] arr1 = new int[][] {
//      {0, 1,  3, 4, 5},
//      {9, 2, -1, 9, 0},
//      {2, 3,  8, 7, 4}
//    };

    int[][] arr2 = new int[][] {
        {1, 2, 8, 1},
        {7, 4, 6, 1},
        {4, 7, 7, 8}
    };

    SubRectangleSum s = new SubRectangleSum(arr2);

//    System.out.println(s.sum(1, 2, 2, 4));
    System.out.println(s.sum(0, 1, 2, 2));
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
      System.out.println("<null>");
    } else {
      for (int i = 0; i < matrix.length; ++i) {
        int[] row = matrix[i];
        for (int j = 0; j < row.length; ++j) {
          System.out.printf("%" + columnWidth + "d", row[j]);
        }
        System.out.println();
      }
    }
  }
}

