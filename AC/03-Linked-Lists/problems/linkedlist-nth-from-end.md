# Remove Nth Node From End

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Remove the nth node from the end of a linked list using a two-pass approach. Return the modified list head. For example, given list 1→2→3→4→5 and n=2, remove 4th node and return 1→2→3→5.

## Intuition/Approach
Use a two-pass approach: first pass to calculate the size of the linked list, second pass to find and remove the target node. Convert "nth from end" to "from beginning" using formula: target_index = size - n. Handle edge case where we need to remove the head node.

## Key Observations
- Two-pass approach is straightforward and easy to understand
- Need to convert "from end" position to "from beginning" position
- Special handling required when removing head node (n == size)
- Must find previous node to update its next pointer
- No dummy node used in this implementation

## Algorithm Steps
1. First pass: traverse entire list to calculate size
2. Check if n == size (removing head), handle specially
3. Second pass: traverse to node before target (size - n - 1 steps)
4. Update previous node's next pointer to skip target node
5. Return head (or new head if original head was removed)

## Complexity Analysis
- **Time Complexity:** O(n) - Two passes through the list
- **Space Complexity:** O(1) - Only using temporary variables
- **Justification:** Two separate traversals, constant extra space

## Edge Cases Considered
- [x] Removing head node (n == size)
- [x] Single node list (n == 1, size == 1)
- [x] Removing last node (n == 1)
- [x] Two-node list
- [x] Invalid n values (assume valid input)

## Solution Code

```java
// Language: Java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Phase 1: Calculate size
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        
        // Phase 2: Remove node
        // Special case: removing head node
        if (n == size) {
            head = head.next;
            return head;
        }
        
        // Find previous node (size - n - 1 steps from head)
        ListNode prev = head;
        for (int i = 1; i < size - n; i++) {
            prev = prev.next;
        }
        
        // Skip the target node
        prev.next = prev.next.next;
        return head;
    }
}

// ListNode definition
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

## Alternative Approaches
1. **One-Pass Two-Pointer**: Use two pointers with n-gap between them
2. **Dummy Node**: Use dummy node to simplify edge cases
3. **Recursive**: Recursive approach with return counting
4. **Stack-Based**: Use stack to find nth from end

## Related Problems
- **AC:** LinkedList basic operations, deletion problems
- **Kunal:** Two-pointer techniques, LinkedList manipulation
- **LeetCode:** #19 Remove Nth Node From End, #237 Delete Node, #83 Remove Duplicates

## Personal Notes
- Two-pass approach is more intuitive than one-pass
- Key insight: convert "from end" to "from beginning" indexing
- Important to handle head removal as special case
- Foundation for understanding more complex deletion problems

## Revision History
- **First Solve:** 2025-07-02 - Implemented two-pass approach for nth node removal
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #deletion #two-pass #nth-from-end #indexing 