# Reverse Doubly Linked List

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Reverse a doubly linked list by swapping the prev and next pointers of each node and updating the head and tail pointers. Transform the list so that the original tail becomes the new head.

## Intuition/Approach
Traverse the doubly linked list and for each node, swap its prev and next pointers. Use the original next pointer to move forward during traversal. After swapping all nodes, update the head pointer to point to the original tail.

## Key Observations
- Each node's prev and next pointers must be swapped
- Use original next pointer to traverse forward
- Original head becomes new tail, original tail becomes new head
- Simpler than singly linked list reversal due to bidirectional pointers
- Only one pass through the list needed

## Algorithm Steps
1. Check if list is empty or has single node (return as is)
2. Initialize current = head, prev = null
3. For each node:
   - Store next = current.next
   - Swap current.next = current.prev
   - Swap current.prev = next
   - Move forward: prev = current, current = next
4. Update head = prev (last processed node)

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through list
- **Space Complexity:** O(1) - Only using temporary pointers
- **Justification:** Visit each node once, constant space for pointer swaps

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node list (only one node)
- [x] Two-node list (minimum case for reversal)
- [x] Proper head and tail pointer updates
- [x] Maintaining bidirectional integrity

## Solution Code

```java
// Language: Java
public class ReverseDLL {
    class Node{
        int data;
        Node next;
        Node prev;
        
        Node(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void reverse(){
        Node current = head;
        Node prev = null;
        
        while(current != null){
            Node next = current.next;
            
            // Swap prev and next pointers
            current.next = current.prev;
            current.prev = next;
            
            // Move to next node
            prev = current;
            current = next;
        }
        
        // Update head to point to the new first node
        head = prev;
    }

    public void print(){
        if(head == null){
            System.out.println("DLL is empty");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
1. **Recursive**: Implement recursively with O(n) stack space
2. **Two-Pass**: First pass to reverse, second pass to update head/tail
3. **Stack-Based**: Use stack to reverse order, O(n) space
4. **Array Conversion**: Convert to array, reverse, rebuild

## Related Problems
- **AC:** Reverse Singly LinkedList, Doubly LinkedList operations
- **Kunal:** LinkedList reversal variations, bidirectional operations
- **LeetCode:** #206 Reverse Linked List, #707 Design Linked List, #146 LRU Cache

## Personal Notes
- Simpler than singly linked list reversal due to bidirectional pointers
- Key insight: swap prev and next pointers for each node
- Important to use original next pointer for traversal
- Foundation for more complex doubly linked list operations

## Revision History
- **First Solve:** 2025-07-02 - Implemented pointer swapping approach for doubly linked list reversal
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #doubly-linkedlist #reversal #pointer-swapping #bidirectional #in-place 