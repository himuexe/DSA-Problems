# Remove Nth Node From End

**Source:** AC | **Topic:** LinkedList, Two-Pass | **Difficulty:** Medium

---

## Problem Statement
Remove the nth node from the end of a singly linked list and return the modified head. For example, for list 1→2→3→4→5 and n=2, return 1→2→3→5.

## Intuition/Approach
Use a two-pass approach:
- First pass: Calculate list size.
- Convert “nth from end” to “from beginning” index (size - n).
- Second pass: Traverse to the node before the target and update links.
- Handle special case for removing the head.

## Key Observations
- Size calculation enables index conversion.
- Head removal requires special handling (n == size).
- Previous node’s next pointer must skip the target node.
- Two-pass approach is intuitive but not optimal (one-pass possible).

## Algorithm Steps
1. Traverse list to compute size.
2. If n == size, remove head and return new head.
3. Traverse to (size - n - 1)th node (previous to target).
4. Update previous.next to skip target node.
5. Return head.

## Complexity Analysis
- **Time Complexity:** O(n) - Two linear passes.
- **Space Complexity:** O(1) - Only temporary pointers.
- **Justification:** Two traversals (size and removal) with constant space.

## Edge Cases Considered
- [x] Remove head (n == size).
- [x] Single node (n == 1).
- [x] Remove last node (n == 1).
- [x] Two-node list.
- [x] Invalid n (assume valid input).

## Solution Code
```java
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        if (n == size) {
            head = head.next;
            return head;
        }
        ListNode prev = head;
        for (int i = 1; i < size - n; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}
```

## Alternative Approaches
- **One-Pass Two-Pointer:** Use two pointers with n-node gap (O(n) time, O(1) space).
- **Dummy Node:** Simplify head removal (O(n) time, O(1) space).
- **Recursive:** Use recursion to count from end (O(n) stack space).
- **Stack-Based:** Store nodes in stack (O(n) space).

## Personal Notes
- The two-pass approach is clear but less efficient than one-pass.
- Converting “from end” to “from beginning” simplifies logic.
- Head removal case requires careful handling.

---
**Tags:** #linkedlist #deletion #two_pass #nth_from_end