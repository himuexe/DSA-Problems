/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Count the number of nodes in the list
        int count = 0;
        ListNode curr = head;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If we have k nodes, then we reverse them
        if (count == k) {
            // Reverse the first k nodes and get the new head
            ListNode reversedHead = reverseLinkedList(head, k);
            
            // Recursively reverse the remaining list and connect it
            head.next = reverseKGroup(curr, k);
            
            return reversedHead;
        }
        
        // If we don't have k nodes left, return head as is
        return head;
    }
    
    // Helper function to reverse the first k nodes of a linked list
    private ListNode reverseLinkedList(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (k > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }
        
        return prev;
    }
}