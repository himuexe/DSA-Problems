# Intersection of Two Linked Lists

**Source:** Kunal | **Topic:** Linked Lists | **Difficulty:** Easy  
**Date Solved:** 2025-07-04 | **Revision Due:** 2025-07-11 | **Status:** Solved

---

## Problem Statement
Given the heads of two singly linked lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

## Intuition/Approach
Transform the intersection problem into a cycle detection problem. Connect the end of list A to the head of list B, then use Floyd's cycle detection algorithm to find the start of the cycle (which is the intersection point).

## Key Observations
- Intersection creates a Y-shaped structure
- After connecting lists, intersection becomes cycle start
- Floyd's algorithm can find cycle start efficiently
- Must restore original list structure after finding intersection

## Algorithm Steps
1. Connect the end of list A to the head of list B
2. Use Floyd's cycle detection (slow/fast pointers) to detect if cycle exists
3. If cycle found, find the cycle start using the standard algorithm
4. Restore original list structure by breaking the connection
5. Return the intersection node (or null if no cycle)

## Complexity Analysis
- **Time Complexity:** O(m + n) - Where m, n are lengths of the two lists
- **Space Complexity:** O(1) - Only using constant extra space
- **Justification:** One pass to connect, one pass for cycle detection, one pass to find start

## Edge Cases Considered
- [x] No intersection (return null)
- [x] Both lists are empty
- [x] One list is empty
- [x] Intersection at head of one list
- [x] Lists have same length

## Solution Code

```java
public class Solution {
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
        
        endA.next = null;
        return start;
    }
}
```

## Alternative Approaches
- **Two Pointers**: Traverse both lists simultaneously, switch lists when reaching end
- **HashSet**: Store all nodes of one list, check if nodes of other list exist
- **Length Difference**: Calculate lengths, align pointers, then traverse together

## Related Problems
- **AC:** [Cycle detection in linked lists]
- **Kunal:** [Floyd's cycle detection algorithm]
- **LeetCode:** [160. Intersection of Two Linked Lists]

## Personal Notes
Clever use of Floyd's algorithm for intersection detection. The key insight is temporarily connecting the lists to create a cycle, then using standard cycle detection techniques.

## Revision History
- **First Solve:** 2025-07-04 - Implemented cycle detection approach
- **Review 1:** 2025-07-11 - [Notes]
- **Review 2:** 2025-07-18 - [Notes]

---
**Tags:** #linkedlist #intersection #floydscycle #twopointers #easy 