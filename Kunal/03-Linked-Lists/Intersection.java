/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Intersection {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode endA = headA;
        while(endA.next != null){
            endA = endA.next;
        }
        endA.next = headB;
        ListNode start = null;
        ListNode slow = headA;
        ListNode fast = headA;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                start = headA;
                while(start != slow){
                    start = start.next;
                    slow = slow.next;
                }
                break;
            }
        }
        endA.next=null;
        return start;
    }
}