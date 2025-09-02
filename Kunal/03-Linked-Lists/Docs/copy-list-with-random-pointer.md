# Copy List with Random Pointer

**Source:** Kunal | **Topic:** Linked Lists | **Difficulty:** Medium  
**Date Solved:** 2025-07-04 | **Revision Due:** 2025-07-11 | **Status:** Solved

---

## Problem Statement
Given a linked list where each node contains an additional "random" pointer that can point to any node in the list or NULL, create a deep copy of the list. The copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node.

## Intuition/Approach
The challenge is copying the random pointers correctly since they can point to any node in the list. The three-pass approach avoids using extra space for HashMap by interweaving the copy nodes with original nodes.

## Key Observations
- Cannot use simple traversal due to random pointers
- HashMap approach works but uses O(n) extra space
- Interweaving technique allows copying without extra space
- Need to separate original and copy lists at the end

## Algorithm Steps
1. **First Pass**: Create copy nodes and insert them between original nodes
2. **Second Pass**: Assign random pointers for copy nodes using the interweaved structure
3. **Third Pass**: Separate original and copy lists while maintaining connections

## Complexity Analysis
- **Time Complexity:** O(n) - Three passes through the list
- **Space Complexity:** O(1) - Only using constant extra space (excluding output)
- **Justification:** Each node is visited exactly 3 times, no additional data structures needed

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node with random pointer to itself
- [x] Random pointers pointing to null
- [x] All nodes having random pointers
- [x] No random pointers (all null)

## Solution Code

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return head;
        }
        
        // Step 1: Create copy nodes and insert them between original nodes
        Node curr = head;
        while(curr != null){
            Node temp = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = temp;
            curr = temp;
        }
        
        // Step 2: Assign random pointers for the copy nodes
        curr = head;
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Step 3: Separate the original and copied lists
        curr = head;
        Node copyHead = head.next;
        Node copyCurr = copyHead;
        
        while(curr != null){
            curr.next = curr.next.next;
            curr = curr.next;
            
            if(copyCurr.next != null){
                copyCurr.next = copyCurr.next.next;
                copyCurr = copyCurr.next;
            }
        }
        
        return copyHead;
    }
}
```

## Alternative Approaches
- **HashMap Approach**: Use HashMap to store original->copy mapping, requires O(n) extra space
- **Recursive Approach**: Recursive solution with memoization, cleaner but uses call stack

## Related Problems
- **AC:** [Copy techniques in linked lists]
- **Kunal:** [Deep copy problems]
- **LeetCode:** [138. Copy List with Random Pointer]

## Personal Notes
The interweaving technique is elegant and space-efficient. Key insight is that after interweaving, curr.random.next always points to the copy of the node that curr.random points to.

## Revision History
- **First Solve:** 2025-07-04 - Implemented three-pass interweaving solution
- **Review 1:** 2025-07-11 - [Notes]
- **Review 2:** 2025-07-18 - [Notes]

---
**Tags:** #linkedlist #deepcopy #randompointer #interweaving #medium 