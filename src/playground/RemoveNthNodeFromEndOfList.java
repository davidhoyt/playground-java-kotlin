package playground;

import static playground.Utils.*;

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

//Given a linked list, remove the nth node from the end of list and return its head.
//
//For example,
//
//   Given linked list: 1->2->3->4->5, and n = 2.
//
//   After removing the second node from the end, the linked list becomes 1->2->3->5.
//Note:
//Given n will always be valid.
//Try to do this in one pass.

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode current = dummy;
        ListNode nth = dummy;
        
        // Move ahead n nodes.
        for (int i = 0; i <= n && current != null; i++) {
            current = current.next;
        }
        
        // Advance both pointers keeping the gap between them.
        while (current != null) {
            current = current.next;
            nth = nth.next;
        }
        
        // Skip over the node in between to "delete" it.
        nth.next = nth.next.next;
        
        // Use of dummy allows us to easily address when the first
        // node should be deleted.
        return dummy.next;
    }

    public void run() {
        printListNode(removeNthFromEnd(ListNode.create(1, 2), 2));
        printListNode(removeNthFromEnd(ListNode.create(1, 2, 3), 2));
        printListNode(removeNthFromEnd(ListNode.create(1, 2, 3, 4, 5), 2));
    }

    public static void main(String[] args) {
        new RemoveNthNodeFromEndOfList().run();
    }
}
