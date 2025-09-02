# Rotate List

**Source:** Kunal Kushwaha | **Topic:** LinkedList, Pointer Manipulation | **Difficulty:** Medium

---

## Problem Statement
Rotate a singly linked list to the right by k places, where k can be larger than the list length. For example, for [1,2,3,4,5] and k=2, return [4,5,1,2,3].

## Intuition/Approach
Convert rotation into a reconnection problem:
- Calculate list length and effective rotations (k % length).
- Find the new tail (at length - k - 1).
- Break the list and reconnect the old tail to the old head.

## Key Observations
- Using k % length avoids redundant rotations.
- Temporarily creating a circular list simplifies reconnection.
- The new tail’s position is critical for correct rotation.
- Edge cases like k=0 or single-node lists require early returns.

## Algorithm Steps
1. If head is null, head.next is null, or k=0, return head.
2. Traverse to find list length and tail.
3. Compute effective k = k % length; if k=0, return head.
4. Find new tail at position (length - k - 1).
5. Set new head as newTail.next, break at new tail, connect old tail to old head.
6. Return new head.

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass for length, partial pass for new tail.
- **Space Complexity:** O(1) - Constant extra space.
- **Justification:** Linear traversal for length and rotation, no extra data structures.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node.
- [x] k = 0 (no rotation).
- [x] k >= length (use modulo).
- [x] k very large (modulo optimization).
- [x] Two-node list.

## Solution Code
```java
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
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
- **Circular List First:** Make circular, then break at k (similar complexity).
- **Two-Pass:** Compute length, then rotate (same as current).
- **Recursive:** Use recursion for rotation (O(n) stack space).

## Personal Notes
- The modulo optimization for k is critical for efficiency.
- Circular connection simplifies the rotation logic.
- This problem reinforces pointer manipulation and list reconnection skills.

---
**Tags:** #linkedlist #rotation #pointer_manipulation #medium