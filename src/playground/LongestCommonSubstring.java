package playground;

import java.util.HashMap;

import static playground.Utils.*;

//https://en.wikipedia.org/wiki/Longest_common_substring_problem

public class LongestCommonSubstring {
    public String lcs(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        
        if (lenA == 0 || lenB == 0) {
            return "";
        }
    
        int[][] table = new int[lenA][lenB];
        
        int maxSoFar = 0;
        String ret = "";
        
        for (int i = 0; i < lenA; ++i) {
            for (int j = 0; j < lenB; ++j) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == 0 || j == 0) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = table[i - 1][j - 1] + 1;
                    }
                  if (table[i][j] > maxSoFar) {
                      maxSoFar = table[i][j];
                      ret = a.substring(i - maxSoFar + 1, i + 1);
                  }
                } else {
                    table[i][j] = 0;
                }
            }
        }
        
        //printIntMatrix(table);
        
        return ret;
    }

    public void run() {
        println(lcs("abab", "baba"));
    }

    public static void main(String[] args) {
        new LongestCommonSubstring().run();
    }
}
