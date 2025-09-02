# Linked List Cycle Detection

**Source:** AC | **Topic:** LinkedList, Two Pointers | **Difficulty:** Medium

---

## Problem Statement
Detect if a singly linked list contains a cycle using Floyd’s Cycle Detection Algorithm (Tortoise and Hare). Return true if a cycle exists, false otherwise.

## Intuition/Approach
Use two pointers (slow and fast) moving at different speeds. If a cycle exists, they will meet inside the cycle due to the relative speed difference. If no cycle exists, the fast pointer reaches null.

## Key Observations
- Fast pointer moves twice as fast, gaining one position per iteration.
- Meeting of pointers guarantees a cycle; null termination guarantees no cycle.
- Floyd’s algorithm is both time and space efficient.
- Null checks for both fast and fast.next prevent errors.

## Algorithm Steps
1. Initialize slow and fast pointers to head.
2. While fast and fast.next are not null:
   - Move slow one step (slow = slow.next).
   - Move fast two steps (fast = fast.next.next).
   - If slow == fast, return true (cycle detected).
3. Return false (no cycle).

## Complexity Analysis
- **Time Complexity:** O(n) - Fast pointer meets slow within cycle length or reaches null.
- **Space Complexity:** O(1) - Only two pointers used.
- **Justification:** Linear traversal with constant space due to pointer-based approach.

## Edge Cases Considered
- [x] Empty list (head == null).
- [x] Single node without cycle.
- [x] Single node with self-loop.
- [x] Two nodes with/without cycle.
- [x] Long cycle or large list.
- [x] Cycle at head or middle.

## Solution Code
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
```

## Alternative Approaches
- **HashSet:** Store visited nodes (O(n) space).
- **Marking Nodes:** Modify node values to mark visits (destructive).
- **Cycle Counting:** Limit iterations to detect loops (imprecise).
- **Recursive Check:** Recursively track visited nodes (O(n) space).

## Personal Notes
- Floyd’s algorithm is elegant and a must-know for linked list problems.
- The key is understanding why the pointers must meet in a cycle.
- This is a prerequisite for more complex problems like cycle removal.

---
**Tags:** #linkedlist #cycle_detection #floyd_algorithm #two_pointers