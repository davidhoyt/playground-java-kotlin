package playground;

import static playground.Utils.*;

// https://leetcode.com/problems/add-two-numbers/description/

//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//    Output: 7 -> 0 -> 8

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode node = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int next = n1 + n2 + carry;
            int digit = next % 10;
            carry = next / 10;
            ListNode nextNode = new ListNode(digit);
            if (result == null)
                node = result = nextNode;
            else
                node.next = nextNode;
            node = nextNode;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
            // e.g.: 1 and 9->9
            // Need to handle the very last carry.
            if (l1 == null && l2 == null && carry > 0)
                node.next = new ListNode(carry);
        }
      return result;
    }

    public void run() {
        // WRONG
        printListNode(addTwoNumbers(ListNode.create(1), ListNode.create(9, 9)));
        printListNode(addTwoNumbers(ListNode.create(5), ListNode.create(5)));
        printListNode(addTwoNumbers(ListNode.create(0), ListNode.create(0)));
        printListNode(addTwoNumbers(ListNode.create(2, 4, 3), ListNode.create(5, 6)));
        printListNode(addTwoNumbers(ListNode.create(2, 4, 3), ListNode.create(5, 6, 4)));
    }

    public static void main(String[] args) {
        new AddTwoNumbers().run();
    }
}
