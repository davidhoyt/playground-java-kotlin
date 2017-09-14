package playground;

import java.util.ArrayList;
import java.util.HashSet;

import static playground.Utils.*;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

//Given a string, find the length of the longest substring without repeating characters.
//
//Examples:
//
//Given "abcabcbb", the answer is "abc", which the length is 3.
//
//Given "bbbbb", the answer is "b", with the length of 1.
//
//Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

public class LongestSubstringWithoutRepeatingCharacters {
    // Uses a sliding window that tracks chars we have seen thus far.
    // It holds an int that represents the left-most unique character
    // in the sequence thus far. It moves it forward if we end up seeing
    // it again.
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
    
        int difference = 0;
        int startOfUniqueSequenceSoFar = 0;
        int endOfUniqueSequenceSoFar = 0;
        HashSet<Character> soFar = new HashSet<>();
    
        int len = s.length();
        
        while (startOfUniqueSequenceSoFar < len && endOfUniqueSequenceSoFar < len) {
            if (!soFar.contains(s.charAt(endOfUniqueSequenceSoFar))) {
                soFar.add(s.charAt(endOfUniqueSequenceSoFar++));
                difference = Math.max(difference, endOfUniqueSequenceSoFar - startOfUniqueSequenceSoFar);
            } else {
                soFar.remove(s.charAt(startOfUniqueSequenceSoFar++));
            }
        }
        print(soFar);
        return difference;
    }

    public void run() {
        println(lengthOfLongestSubstring("dvdf"));
        println(lengthOfLongestSubstring("aab"));
        println(lengthOfLongestSubstring("abcabcbb"));
        println(lengthOfLongestSubstring("bbbbb"));
        println(lengthOfLongestSubstring("pwwkew"));
    }

    public static void main(String[] args) {
        new LongestSubstringWithoutRepeatingCharacters().run();
    }
}
