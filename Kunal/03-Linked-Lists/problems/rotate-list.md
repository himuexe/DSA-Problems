# Rotate List

**Source:** Kunal Kushwaha  
**Topic:** Linked Lists  
**Difficulty:** Medium

---

## Problem Statement

Given the head of a linked list, rotate the list to the right by k places.

### Examples
- **Input:** head = [1,2,3,4,5], k = 2
- **Output:** [4,5,1,2,3]

- **Input:** head = [0,1,2], k = 4  
- **Output:** [2,0,1]

---

## Intuition/Approach

The key insight is to understand that rotating a linked list to the right by k places is equivalent to:
1. Finding the new tail (at position `length - k - 1`)
2. Breaking the list at that point
3. Connecting the old tail to the old head

**Algorithm Steps:**
1. Handle edge cases (empty list, single node, k=0)
2. Calculate the length of the list and find the tail
3. Calculate effective rotations needed (`k % length`)
4. Find the new tail position
5. Break the list and reconnect to perform rotation

---

## Key Observations

- **Optimization:** Use `k % length` to avoid unnecessary rotations
- **Edge Cases:** Handle null lists, single nodes, and k=0
- **Circular Connection:** Temporarily create a circular list for easier manipulation
- **Position Calculation:** New tail is at position `length - k - 1`

---

## Algorithm Steps

1. **Validation:** Check for edge cases (null, single node, k=0)
2. **Length Calculation:** Traverse to find list length and tail pointer
3. **Effective Rotation:** Calculate `k = k % length` to optimize
4. **New Tail Finding:** Navigate to position `length - k - 1`
5. **List Rotation:** Break at new tail and reconnect segments

---

## Time & Space Complexity

- **Time Complexity:** O(n) where n is the number of nodes
- **Space Complexity:** O(1) - only using a constant amount of extra space

---

## Edge Cases

- [ ] Empty list (head = null)
- [ ] Single node list
- [ ] k = 0 (no rotation needed)
- [ ] k >= length (use modulo optimization)
- [ ] k is very large (efficiency with modulo)

---

## Solution Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Handle edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Find the length of the list and get the tail
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Step 2: Calculate effective rotations needed (k % length)
        k = k % length;
        if (k == 0) {
            return head; // No rotation needed
        }
        
        // Step 3: Find the new tail (at position length - k - 1)
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        
        // Step 4: Perform the rotation
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        
        return newHead;
    }
}
```

---

## Alternative Approaches

1. **Circular List Approach:** Make the list circular first, then break at the appropriate position
2. **Two-Pass Approach:** First pass to find length, second pass to find rotation point
3. **Recursive Approach:** Use recursion to handle the rotation (less efficient)

---

## Related Problems

- **LeetCode 189:** Rotate Array
- **LeetCode 25:** Reverse Nodes in k-Group
- **LeetCode 206:** Reverse Linked List

---

## Personal Notes

- **LeetCode 61:** Direct implementation of this problem
- **Key Pattern:** List manipulation with reconnection
- **Interview Tip:** Always discuss edge cases and optimization strategies
- **Practice Focus:** Understanding pointer manipulation and circular connections

---

## Tags

`linked-list` `two-pointers` `rotation` `list-manipulation` `medium` 