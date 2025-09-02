# Reverse Nodes in k-Group

**Source:** Kunal Kushwaha | **Topic:** LinkedList, Recursion | **Difficulty:** Hard

---

## Problem Statement
Reverse every k nodes in a singly linked list. If the remaining nodes are fewer than k, leave them unchanged. For example, for [1,2,3,4,5] and k=2, return [2,1,4,3,5].

## Intuition/Approach
Use recursion to reverse k nodes at a time:
- Check if k nodes are available.
- Reverse the first k nodes using a helper function.
- Recursively process the remaining list and connect to the reversed group.

## Key Observations
- Recursion simplifies handling multiple k-groups.
- A helper function isolates the k-node reversal logic.
- Remaining nodes (< k) are left unchanged.
- Connection between reversed groups is critical.

## Algorithm Steps
1. Count k nodes from the current head; if fewer, return head.
2. Reverse the first k nodes using a helper function.
3. Recursively reverse the remaining list.
4. Connect the current reversed group to the recursive result.
5. Return the new head of the reversed group.

## Complexity Analysis
- **Time Complexity:** O(n) - Each node is processed once.
- **Space Complexity:** O(n/k) - Recursion stack for n/k groups.
- **Justification:** Linear traversal for reversal, recursive calls proportional to groups.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node.
- [x] k = 1 (no reversal).
- [x] k >= list length (reverse all).
- [x] Fewer than k nodes remaining.
- [x] k equals list length.

## Solution Code
```java
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            ListNode reversedHead = reverseLinkedList(head, k);
            head.next = reverseKGroup(curr, k);
            return reversedHead;
        }
        return head;
    }

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

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

## Alternative Approaches
- **Iterative with Stack:** Use stack to reverse k nodes (O(k) space).
- **Two-Pointer Iterative:** Track group boundaries (O(1) space).
- **Dummy Node:** Simplify edge cases (O(1) space).

## Personal Notes
- Recursion makes the solution elegant but requires careful connection handling.
- The helper function for k-node reversal is reusable and clear.
- This problem builds on basic reversal, adding complexity with grouping.

---
**Tags:** #linkedlist #recursion #k_group_reversal #hard