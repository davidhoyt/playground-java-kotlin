package playground;

import java.util.HashSet;

import static playground.Utils.println;

public class IsPermutation {
    public static boolean permutation(String s, String t) {
        // Have to be same length.
        if (s.length() != t.length()) {
            return false;
        } else {
            // Count # of occurrences of each character.
            int[] hist = new int[128];
            for(int i = 0; i < s.length(); ++i) {
                hist[s.charAt(i)]++;
            }
            // Decrement # of occurrences in other string. If it dips below
            // 0, then we have a problem.
            for (int j = 0; j < t.length(); ++j) {
                if (hist[t.charAt(j)]-- < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        println(permutation("abcd", "a"));
        println(permutation("abcd", "abc"));
        println(permutation("abcd", "abcd"));
        println(permutation("abcd", "dbac"));
    }
}
