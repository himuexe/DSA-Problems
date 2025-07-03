# Doubly Linked List Operations

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Implement a doubly linked list with bidirectional navigation, supporting add, remove, and both forward and backward traversal operations. Each node should have both prev and next pointers for efficient bidirectional access.

## Intuition/Approach
Create a doubly linked list where each node has both prev and next pointers. Maintain head and tail pointers for efficient access to both ends. For each operation, update both forward and backward links to maintain bidirectional integrity.

## Key Observations
- Each node has both prev and next pointers for bidirectional links
- Head and tail pointers enable efficient access to both ends
- All operations must update both forward and backward links
- More complex edge case handling due to dual pointers
- Enhanced functionality with backward traversal capability

## Algorithm Steps
1. Define Node class with data, prev, and next pointers
2. Initialize head and tail pointers to null
3. For add: create node, update tail links, set new tail
4. For remove: find node, update surrounding nodes' links
5. Handle special cases for head/tail removal
6. Implement forward and backward traversal methods

## Complexity Analysis
- **Time Complexity:** O(1) for add, O(n) for remove (search required)
- **Space Complexity:** O(1) for operations (not counting list storage)
- **Justification:** Direct tail manipulation for add, linear search for remove

## Edge Cases Considered
- [x] Empty list operations (head == null)
- [x] Single node operations (head == tail)
- [x] Head node removal (update head.prev = null)
- [x] Tail node removal (update tail.next = null)
- [x] Middle node removal (update surrounding nodes)
- [x] Node not found in remove operation

## Solution Code

```java
// Language: Java
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    // Node class
    private static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    // Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add a node at the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Method to remove a node by value (first occurrence)
    public boolean remove(int data) {
        if (head == null) {
            return false; // List is empty
        }

        Node current = head;
        while (current != null) {
            if (current.data == data) {
                // Case 1: Removing the head node
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null; // List becomes empty
                    }
                }
                // Case 2: Removing the tail node
                else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }
                // Case 3: Removing a middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // Node not found
    }

    // Method to print the list (forward traversal)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to print the list (backward traversal)
    public void printListReverse() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
1. **Circular Doubly Linked List**: Connect tail to head for circular structure
2. **Header/Trailer Sentinels**: Use dummy nodes to simplify edge cases
3. **Generic Implementation**: Use generics for type safety
4. **Array-Based**: Use arrays with forward/backward indices

## Related Problems
- **AC:** Reverse Doubly LinkedList, LinkedList basic operations
- **Kunal:** Design LinkedList, Advanced data structures
- **LeetCode:** #707 Design Linked List, #146 LRU Cache, #1206 Design Skiplist

## Personal Notes
- More complex than singly linked list due to dual pointer management
- Provides enhanced functionality with bidirectional traversal
- Key insight: always update both prev and next pointers
- Foundation for advanced data structures like LRU Cache

## Revision History
- **First Solve:** 2025-07-02 - Implemented complete doubly linked list with add/remove/traversal
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #doubly-linkedlist #bidirectional #datastructures #head-tail #traversal 