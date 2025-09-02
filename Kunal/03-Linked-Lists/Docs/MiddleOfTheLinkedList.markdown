# Middle of the Linked List

**Source:** Kunal Kushwaha | **Topic:** LinkedList, Two Pointers | **Difficulty:** Easy

---

## Problem Statement
Given the head of a singly linked list, return the middle node. For even-length lists, return the second middle node (e.g., for [1,2,3,4], return node 3).

## Intuition/Approach
Use Floyd’s Tortoise and Hare algorithm: a slow pointer moves one step, a fast pointer moves two steps. When the fast pointer reaches the end, the slow pointer is at the middle.

## Key Observations
- Floyd’s algorithm ensures a single-pass solution.
- For even-length lists, returning the second middle node aligns with problem requirements.
- Fast pointer checks for null and null.next prevent errors.
- Simple and efficient with no extra space.

## Algorithm Steps
1. Initialize slow and fast pointers to head.
2. While fast and fast.next are not null:
   - Move slow one step (slow = slow.next).
   - Move fast two steps (fast = fast.next.next).
3. Return slow (middle node).

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass, fast pointer traverses at most n/2 steps.
- **Space Complexity:** O(1) - Only two pointers used.
- **Justification:** Linear traversal with constant space.

## Edge Cases Considered
- [x] Empty list (return null).
- [x] Single node.
- [x] Two nodes (return second).
- [x] Even-length list (return second middle).
- [x] Odd-length list (return exact middle).
- [x] Long lists.

## Solution Code
```java
public class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
- **Two-Pass:** Count nodes, then traverse to middle (O(n) time, O(1) space).
- **Array-Based:** Store nodes in array, return middle (O(n) space).
- **Length-Based:** Compute length, traverse to middle (O(n) time, O(1) space).

## Personal Notes
- Floyd’s algorithm is a versatile tool for linked list problems.
- The requirement to return the second middle node for even lists is a key detail.
- This is a foundational problem for understanding two-pointer techniques.

---
**Tags:** #linkedlist #floyd_algorithm #two_pointers #middle_finding #easy