package playground;

import java.util.HashSet;

import static playground.Utils.*;

public class UniqueCharacters {
    public static boolean checkUnique(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            } else {
                set.add(c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        println(checkUnique("abcd"));
        println(checkUnique("abcdd"));
    }
}
