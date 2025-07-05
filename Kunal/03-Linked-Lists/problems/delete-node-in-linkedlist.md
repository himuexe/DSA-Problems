# Delete Node in a Linked List

**Source:** Kunal | **Topic:** Linked Lists | **Difficulty:** Medium  
**Date Solved:** 2025-07-04 | **Revision Due:** 2025-07-11 | **Status:** Solved

---

## Problem Statement
Delete a node from a singly linked list, given only access to that node (not the head of the list). The node to be deleted is guaranteed to not be the tail of the list.

## Intuition/Approach
Since we don't have access to the previous node, we cannot directly unlink the current node. Instead, we copy the next node's value to the current node and then delete the next node.

## Key Observations
- Cannot delete the node directly without previous node reference
- Can "fake" deletion by copying next node's value
- Actually delete the next node instead of current node
- This technique only works if the node is not the tail

## Algorithm Steps
1. Copy the value from the next node to the current node
2. Update the current node's next pointer to skip the next node
3. The next node is effectively deleted from the list

## Complexity Analysis
- **Time Complexity:** O(1) - Constant time operation
- **Space Complexity:** O(1) - No extra space required
- **Justification:** Only performs two assignments, no traversal needed

## Edge Cases Considered
- [x] Node is not the tail (guaranteed by problem)
- [x] Next node exists (guaranteed by problem)
- [x] List has at least 2 nodes (guaranteed by problem)
- [x] Single node (not applicable as guaranteed not tail)

## Solution Code

```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

## Alternative Approaches
- **Traditional Deletion**: Would require head/previous node reference, not applicable here
- **Recursive Approach**: Not applicable due to constraint of only having current node

## Related Problems
- **AC:** [Basic linked list deletion]
- **Kunal:** [Linked list manipulation]
- **LeetCode:** [237. Delete Node in a Linked List]

## Personal Notes
This is a clever trick problem that demonstrates thinking outside the box. The key insight is that we don't actually need to delete the current node - we can transform it into the next node and delete that one instead.

## Revision History
- **First Solve:** 2025-07-04 - Implemented value copying technique
- **Review 1:** 2025-07-11 - [Notes]
- **Review 2:** 2025-07-18 - [Notes]

---
**Tags:** #linkedlist #deletion #trick #medium #constant-time 