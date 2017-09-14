package playground;

import java.util.HashSet;

import static playground.Utils.print;
import static playground.Utils.println;

//https://leetcode.com/problems/median-of-two-sorted-arrays/description/

//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//Example 1:
//  nums1 = [1, 3]
//  nums2 = [2]
//
//  The median is 2.0
//
//Example 2:
//  nums1 = [1, 2]
//  nums2 = [3, 4]
//
//  The median is (2 + 3)/2 = 2.5

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid1 = 0, mid2 = 0;
        int left1 = 0, left2 = 0;
        int right1 = nums1.length - 1, right2 = nums2.length - 1;
        int val1, val2;
        
        int lookingFor = (nums1.length + nums2.length) / 2;
        
//        while(true) {
//            mid1 = (right1 - left1) / 2;
//            mid2 = (right2 - left2) / 2;
//            int mid = mid1 + mid2;
//            if (mid > lookingFor) {
//                // Need to adjust one of the two.
//
////                if (right2 > ) {
////                    right2
////                }
//            } else if (mid < lookingFor) {
//
//            } else {
//
//            }
//        }
        
//        while (true) {
//            mid1 = (right1 - left1) / 2;
//            mid2 = (right2 - left2) / 2;
//
//            val1 = nums1[mid1];
//            val2 = nums2[mid2];
//
//            //if ()
//        }
        return 1.0D;
    }

    public void run() {
        //println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        println(findMedianSortedArrays(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 11}));
    }

    public static void main(String[] args) {
        new MedianOfTwoSortedArrays().run();
    }
}
