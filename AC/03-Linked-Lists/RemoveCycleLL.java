/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 class Solution {
    public void removeCycle(ListNode head) {
        if (head == null || head.next == null) {
            return; // No cycle possible
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Detect cycle using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            return; // No cycle to remove
        }

        // Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // Now, slow (or fast) is the start of the cycle

        // Find the last node in the cycle
        ListNode lastNode = fast;
        while (lastNode.next != fast) {
            lastNode = lastNode.next;
        }

        // Break the cycle
        lastNode.next = null;
    }
}