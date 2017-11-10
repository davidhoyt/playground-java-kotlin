package playground;

import java.util.Stack;

import static playground.Utils.*;

// Convert tree to linked list w/ in-order traversal

public class TreeToLinkedList {
    public static ListNode work(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        ListNode dummyHead = ListNode.create(-1); // dummy
        ListNode listNode = dummyHead;

        // First node to visit is the left-most one.
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            node = stack.pop();

            // Visit the node!

            ListNode newListNode = ListNode.create(node.val);
            listNode.next = newListNode;
            listNode = newListNode;

            // Add in the left-most node of the right child.
            if (node.right != null) {
                node = node.right;

                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeNode.create(2, 3, 1, 5, 4, 0, 6);
        print(t1);
        print(work(t1));
    }
}
