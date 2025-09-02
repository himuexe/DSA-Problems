# Remove Cycle from Linked List

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Hard  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Remove a cycle from a linked list using Floyd's Cycle Detection Algorithm combined with cycle start detection. Break the cycle by setting the last node's next pointer to null, converting it back to a linear linked list.

## Intuition/Approach
Use a three-phase approach: 1) Detect cycle using Floyd's algorithm, 2) Find cycle start by moving one pointer to head and advancing both at same speed, 3) Find last node in cycle and break the link. This leverages the mathematical property that distance from head to cycle start equals distance from meeting point to cycle start.

## Key Observations
- Mathematical proof: distance from head to cycle start = distance from meeting point to cycle start
- Must find both cycle existence and cycle start location
- Last node in cycle points back to cycle start
- Three distinct phases each with specific purpose
- Uses Floyd's algorithm properties for efficient detection

## Algorithm Steps
1. Phase 1: Use slow/fast pointers to detect cycle existence
2. If no cycle, return early (nothing to remove)
3. Phase 2: Reset slow to head, move both pointers at same speed to find cycle start
4. Phase 3: Traverse from cycle start to find last node in cycle
5. Break cycle by setting lastNode.next = null

## Complexity Analysis
- **Time Complexity:** O(n) - At most 2n iterations total across all phases
- **Space Complexity:** O(1) - Only using two pointers
- **Justification:** Three separate linear traversals, constant space usage

## Edge Cases Considered
- [x] No cycle present (early return)
- [x] Empty list (head == null)
- [x] Single node with self-loop
- [x] Cycle at head (entire list is cycle)
- [x] Cycle in middle or end of list
- [x] Two-node cycle

## Solution Code

```java
// Language: Java
class Solution {
    public void removeCycle(ListNode head) {
        if (head == null || head.next == null) {
            return; // No cycle possible
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Phase 1: Detect cycle using Floyd's algorithm
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

        // Phase 2: Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // Now, slow (or fast) is the start of the cycle

        // Phase 3: Find the last node in the cycle
        ListNode lastNode = fast;
        while (lastNode.next != fast) {
            lastNode = lastNode.next;
        }

        // Break the cycle
        lastNode.next = null;
    }
}

// ListNode definition
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
1. **HashSet-Based**: Store visited nodes, find first revisited node
2. **Marking Approach**: Temporarily modify node values to mark visited
3. **Two-Pass HashSet**: First pass to collect all nodes, second to find cycle
4. **Array-Based**: Store node references in array (if size known)

## Related Problems
- **AC:** Cycle Detection, LinkedList basic operations
- **Kunal:** Advanced LinkedList algorithms, Floyd's applications
- **LeetCode:** #142 Linked List Cycle II, #141 Linked List Cycle, #287 Find Duplicate Number

## Personal Notes
- Combines cycle detection with cycle removal - advanced algorithm
- Key insight: mathematical relationship between distances
- Must master Floyd's algorithm first before attempting this
- Three-phase approach provides clear separation of concerns

## Revision History
- **First Solve:** 2025-07-02 - Implemented three-phase cycle removal algorithm
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #cycle-removal #floyd-algorithm #advanced #three-phase 