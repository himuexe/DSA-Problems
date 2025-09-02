# Doubly Linked List Operations

**Source:** AC | **Topic:** LinkedList, Data Structures | **Difficulty:** Medium

---

## Problem Statement
Implement a doubly linked list with add, remove, and bidirectional traversal (forward and backward). Each node has prev and next pointers for bidirectional navigation.

## Intuition/Approach
Maintain head, tail, and size for efficient operations. Add nodes at the end, remove by value (first occurrence), and support traversal in both directions. Update both prev and next pointers to ensure bidirectional integrity.

## Key Observations
- Dual pointers (prev, next) enable bidirectional traversal.
- Head and tail pointers optimize end operations.
- Removal requires updating both prev and next links.
- Edge cases (head/tail removal) need careful handling.
- Size tracking provides O(1) size queries.

## Algorithm Steps
1. **Node Class:** Define with data, prev, and next pointers.
2. **Add:** Append node at tail, update prev/next links.
3. **Remove:** Find node by value, update surrounding links (head, tail, or middle cases).
4. **Forward Print:** Traverse from head to tail.
5. **Backward Print:** Traverse from tail to head.

## Complexity Analysis
- **Time Complexity:** 
  - Add: O(1)
  - Remove: O(n) (search required)
  - Print: O(n)
- **Space Complexity:** O(1) (excluding list storage)
- **Justification:** Constant-time add due to tail pointer; linear search for remove.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node (head == tail).
- [x] Remove head/tail/middle node.
- [x] Node not found in remove.
- [x] Consistent prev/next updates.
- [x] Bidirectional traversal integrity.

## Solution Code
```java
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

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

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public boolean remove(int data) {
        if (head == null) {
            return false;
        }
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

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
- **Circular DLL:** Connect tail to head for circular structure.
- **Sentinel Nodes:** Use header/trailer nodes to simplify edge cases.
- **Generic DLL:** Use generics for type safety.
- **Array-Based:** Simulate DLL with array indices (O(n) space).

## Personal Notes
- Managing dual pointers adds complexity but enables bidirectional traversal.
- Tail pointer simplifies add operations significantly.
- This is a foundation for advanced structures like LRU Cache.

---
**Tags:** #doubly_linkedlist #bidirectional #data_structures #traversal