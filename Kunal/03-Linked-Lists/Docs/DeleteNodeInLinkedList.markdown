# Delete Node in a Linked List

**Source:** Kunal | **Topic:** LinkedList, Pointer Manipulation | **Difficulty:** Medium

---

## Problem Statement
Delete a given node from a singly linked list, with access only to that node (not the head). The node is guaranteed not to be the tail.

## Intuition/Approach
Since we lack access to the previous node, directly unlinking the current node is impossible. Instead, copy the next node’s value to the current node and update the current node’s next pointer to skip the next node, effectively deleting it.

## Key Observations
- Direct deletion is not feasible without the previous node.
- Copying the next node’s value simulates deletion.
- The actual deletion occurs by skipping the next node.
- The guarantee that the node is not the tail simplifies the solution.

## Algorithm Steps
1. Copy the value of the next node to the current node.
2. Update the current node’s next pointer to skip the next node.
3. The next node is effectively removed from the list.

## Complexity Analysis
- **Time Complexity:** O(1) - Two assignments, no traversal.
- **Space Complexity:** O(1) - No extra space used.
- **Justification:** Constant-time operations due to direct pointer manipulation.

## Edge Cases Considered
- [x] Node is not the tail (guaranteed).
- [x] Next node exists (guaranteed).
- [x] List has at least two nodes (guaranteed).
- [x] Node in the middle of a long list.

## Solution Code
```java
public class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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
- **Traditional Deletion:** Requires previous node reference (not applicable).
- **Recursive Deletion:** Unnecessary due to single-node access constraint.

## Personal Notes
- This problem teaches a clever workaround for deletion without full list access.
- The insight of transforming the current node into the next node is key.
- Useful for understanding pointer manipulation in constrained scenarios.

---
**Tags:** #linkedlist #deletion #pointer_manipulation #trick #medium