# Copy List with Random Pointer

**Source:** Kunal | **Topic:** LinkedList, Deep Copy | **Difficulty:** Medium

---

## Problem Statement
Create a deep copy of a singly linked list where each node has a random pointer to any node or null. The copy should have new nodes with the same values and pointer structure.

## Intuition/Approach
Use a three-pass approach to avoid extra space:
- Interweave copy nodes between original nodes.
- Set random pointers for copy nodes using the interweaved structure.
- Separate the copied list from the original.

## Key Observations
- Interweaving allows random pointer assignment without extra space.
- Each copy node follows its original, simplifying pointer updates.
- Final separation restores both lists while maintaining structure.
- O(1) space is achieved by reusing the list structure.

## Algorithm Steps
1. **Interweave:** Insert copy nodes (original -> copy -> original -> copy).
2. **Set Random Pointers:** For each original node, set copy’s random pointer (curr.next.random = curr.random.next).
3. **Separate Lists:** Extract copy list, restore original list.

## Complexity Analysis
- **Time Complexity:** O(n) - Three linear passes.
- **Space Complexity:** O(1) - Excludes output space, uses only pointers.
- **Justification:** Each pass visits each node once, no extra data structures.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node with self-pointing random pointer.
- [x] All random pointers null.
- [x] All nodes have random pointers.
- [x] Random pointer to arbitrary nodes.
- [x] Long lists with complex random pointers.

## Solution Code
```java
public class Solution {
    class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // Step 1: Interweave copy nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        // Step 2: Assign random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        // Step 3: Separate lists
        Node copyHead = head.next;
        Node copyCurr = copyHead;
        curr = head;
        while (curr != null) {
            curr.next = curr.next.next;
            curr = curr.next;
            if (copyCurr.next != null) {
                copyCurr.next = copyCurr.next.next;
                copyCurr = copyCurr.next;
            }
        }
        return copyHead;
    }
}
```

## Alternative Approaches
- **HashMap:** Map original nodes to copies (O(n) space).
- **Recursive with Memoization:** Cleaner but uses O(n) stack space.
- **Array-Based:** Store nodes in array, rebuild (O(n) space).

## Personal Notes
- The interweaving technique is a brilliant space-saving approach.
- Understanding the random pointer assignment (curr.next.random = curr.random.next) is key.
- This problem tests deep understanding of pointer manipulation.

---
**Tags:** #linkedlist #deep_copy #random_pointer #interweaving #medium