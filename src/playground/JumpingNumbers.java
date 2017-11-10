package playground;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static playground.Utils.*;

// http://www.geeksforgeeks.org/print-all-jumping-numbers-smaller-than-or-equal-to-a-given-value/

public class JumpingNumbers {
    public static ArrayList<Integer> jumpingNumbersLessThan(int x) {
        ArrayList<Integer> numbers = new ArrayList<>();

        // Start at each digit.
        for (int i = 0; i <= 9; ++i)
            work(numbers, x, i);

        return numbers;
    }

    public static void work(ArrayList<Integer> numbers, int x, int num) {
        // Do a BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.add(num);

        while (!q.isEmpty()) {
            num = q.poll();
            if (num <= x) {
                numbers.add(num);

                int lastDigit = num % 10;

                int next = num * 10 + lastDigit;

                // If last digit is 0, append next digit only
                if (lastDigit == 0) {
                    q.add(next + 1);
                // If last digit is 9, append previous digit only
                } else if (lastDigit == 9) {
                    q.add(next - 1);
                // If last digit is neither 0 nor 9, append both previous and next digits
                } else {
                    q.add(next - 1);
                    q.add(next + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        print(jumpingNumbersLessThan(400));
    }
}
