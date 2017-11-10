package playground;

import java.util.Stack;

import static playground.Utils.*;

// https://leetcode.com/problems/valid-parentheses

public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.empty())
                        return false;
                    char top = stack.pop();
                    if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '['))
                        return false;
                    break;
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        println(isValid("(")); // false
        println(isValid("((")); // false
        println(isValid("))")); // false
        println(isValid("()")); // true
        println(isValid("()(")); // false
        println(isValid("({)}")); // false
        println(isValid("({})")); // true
        println(isValid("()[]{}")); // true
    }
}
