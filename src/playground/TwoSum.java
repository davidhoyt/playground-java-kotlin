package playground;

import java.util.HashMap;
import static playground.Utils.*;

//https://leetcode.com/problems/two-sum/description/

//Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//Example:
//  Given nums = [2, 7, 11, 15], target = 9,
//
//  Because nums[0] + nums[1] = 2 + 7 = 9,
//  return [0, 1].

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complements = new HashMap<>(nums.length);
        
        for (int i = 0; i < nums.length; ++i) {
            if (complements.containsKey(nums[i])) {
                return new int[] {
                    complements.get(nums[i]),
                    i
                };
            }
            int complement = target - nums[i];
            complements.put(complement, i);
        }
        
        return null;
    }

    public void run() {
        printIntArray(twoSum(new int[] { -1,-2,-3,-4,-5 }, -8));
        printIntArray(twoSum(new int[] { 3,2,4 }, 6));
    }

    public static void main(String[] args) {
        new TwoSum().run();
    }
}
