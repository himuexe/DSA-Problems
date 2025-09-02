# Remove Cycle from Linked List

**Source:** AC | **Topic:** LinkedList, Cycle Detection | **Difficulty:** Hard

---

## Problem Statement
Remove a cycle from a singly linked list using Floyd’s Cycle Detection Algorithm and cycle start detection. Break the cycle by setting the last node’s next pointer to null.

## Intuition/Approach
Use a three-phase approach:
- Detect cycle using Floyd’s algorithm.
- Find cycle start by moving one pointer to head and advancing both at equal speed.
- Break cycle by finding the last node and setting its next to null.

## Key Observations
- Floyd’s algorithm guarantees cycle detection.
- Distance from head to cycle start equals distance from meeting point to cycle start.
- Last node in cycle points to cycle start.
- Three phases separate concerns for clarity.

## Algorithm Steps
1. Use slow/fast pointers to detect cycle.
2. If no cycle, return.
3. Reset slow to head, move both at same speed to find cycle start.
4. From cycle start, find last node (points back to start).
5. Set lastNode.next = null to break cycle.

## Complexity Analysis
- **Time Complexity:** O(n) - Three linear passes (detection, start, break).
- **Space Complexity:** O(1) - Only pointers used.
- **Justification:** Each phase is linear, with constant space.

## Edge Cases Considered
- [x] No cycle.
- [x] Empty list.
- [x] Single node with self-loop.
- [x] Cycle at head.
- [x] Cycle in middle/end.
- [x] Two-node cycle.

## Solution Code
```java
public class Solution {
    public void removeCycle(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode lastNode = fast;
        while (lastNode.next != fast) {
            lastNode = lastNode.next;
        }
        lastNode.next = null;
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
- **HashSet:** Store nodes, find cycle start (O(n) space).
- **Marking:** Modify node values (destructive).
- **Two-Pass HashSet:** Collect nodes, find cycle (O(n) space).
- **Array-Based:** Store nodes in array (O(n) space).

## Personal Notes
- The mathematical insight behind cycle start detection is elegant.
- Floyd’s algorithm is critical for both detection and removal.
- Breaking the cycle at the last node ensures a clean linear list.

---
**Tags:** #linkedlist #cycle_removal #floyd_algorithm #three_phase