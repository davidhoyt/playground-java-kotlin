package playground;

import static playground.Utils.*;

public class PalindromePermutation {
    // Check if it is a permutation of a palindrome.

    // What makes something a permutation of a palindrome?
    // You have an even number of characters or at most one character that has an odd number.
    public static boolean work(String str) {
        String s = str.toLowerCase();
        int[] hist = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isLetter(c))
              hist[s.charAt(i)]++;
        }
        int numOdds = 0;
        for (int i = 0; i < hist.length; ++i) {
            if ((hist[i] % 2) == 1)
                numOdds++;
        }
        //printCharHistArray(hist);
        return (numOdds <= 1);
    }

    public static void main(String[] args) {
        println(work("abc"));
        println(work("abcd"));
        println(work("aabb"));
        println(work("abcddab"));
        println(work("Tact Coa"));
        println(work("taco cat"));
    }
}
