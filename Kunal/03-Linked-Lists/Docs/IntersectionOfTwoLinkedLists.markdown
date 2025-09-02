# Intersection of Two Linked Lists

**Source:** Kunal | **Topic:** LinkedList, Two Pointers | **Difficulty:** Easy

---

## Problem Statement
Given the heads of two singly linked lists, return the node where they intersect (share the same node reference). Return null if no intersection exists.

## Intuition/Approach
Transform the problem into cycle detection by connecting the end of list A to list B’s head. Use Floyd’s algorithm to find the cycle start (intersection point), then restore the original list.

## Key Observations
- Intersection forms a Y-shaped structure.
- Connecting lists creates a cycle starting at the intersection.
- Floyd’s algorithm efficiently finds the cycle start.
- Restoring the original list is critical to avoid modifying input.

## Algorithm Steps
1. If either head is null, return null.
2. Find end of list A and connect to head B.
3. Use Floyd’s algorithm to detect cycle and find cycle start.
4. Break the connection to restore list A.
5. Return the intersection node (or null if no cycle).

## Complexity Analysis
- **Time Complexity:** O(m + n) - m, n are list lengths (traversal and cycle detection).
- **Space Complexity:** O(1) - Only pointers used.
- **Justification:** Linear passes for connection, detection, and restoration.

## Edge Cases Considered
- [x] No intersection.
- [x] Both lists empty.
- [x] One list empty.
- [x] Intersection at head.
- [x] Lists of equal length.
- [x] Lists of different lengths.

## Solution Code
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode endA = headA;
        while (endA.next != null) {
            endA = endA.next;
        }
        endA.next = headB;
        ListNode start = null;
        ListNode slow = headA;
        ListNode fast = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = headA;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                start = slow;
                break;
            }
        }
        endA.next = null; // Restore original list
        return start;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

## Alternative Approaches
- **Two Pointers:** Traverse both lists, switch heads on reaching end (O(m + n) time, O(1) space).
- **HashSet:** Store nodes of one list, check other list (O(n) space).
- **Length Difference:** Align lists by skipping nodes (O(m + n) time, O(1) space).

## Personal Notes
- Using Floyd’s algorithm for intersection is a creative application.
- Restoring the original list is a critical step for correctness.
- The two-pointer alternative is simpler and avoids modifying the list.

---
**Tags:** #linkedlist #intersection #floyd_algorithm #two_pointers #easy