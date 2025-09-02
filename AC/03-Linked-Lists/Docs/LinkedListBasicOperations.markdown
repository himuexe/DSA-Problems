# LinkedList Basic Operations

**Source:** AC | **Topic:** LinkedList, Data Structures | **Difficulty:** Easy

---

## Problem Statement
Implement a singly linked list with fundamental operations: add (first, last, at index), remove (first, last), and print. Maintain size tracking and handle edge cases like empty lists or invalid indices.

## Intuition/Approach
Use a singly linked list with head and tail pointers for efficient insertions/deletions at the ends. Track size for O(1) size queries. Implement each operation with careful pointer updates and edge case handling.

## Key Observations
- Head and tail pointers optimize addFirst/addLast operations.
- Size tracking ensures O(1) size access.
- Operations at specific indices require traversal, making them O(n).
- Edge cases (empty list, single node) require special handling.
- Static members in the original code imply a single shared list.

## Algorithm Steps
1. **Node Class:** Define with data and next pointer.
2. **Add First:** Create new node, link to head, update head and size.
3. **Add Last:** Create new node, link from tail, update tail and size.
4. **Add at Index:** Traverse to index-1, insert node, update links and size.
5. **Remove First:** Update head, handle single-node case, return value.
6. **Remove Last:** Traverse to second-last node, update tail, return value.
7. **Print:** Traverse from head, print each node’s data.

## Complexity Analysis
- **Time Complexity:** 
  - Add First/Last, Remove First: O(1)
  - Add at Index, Remove Last, Print: O(n)
- **Space Complexity:** O(1) for operations (excluding list storage)
- **Justification:** Constant-time operations use direct pointer updates; others require linear traversal.

## Edge Cases Considered
- [x] Empty list (head == null).
- [x] Single node (head == tail).
- [x] Invalid index for add (out of bounds).
- [x] Consistent size updates.
- [x] Null pointer handling in traversals.
- [x] Removing from empty list.

## Solution Code
```java
public class LinkedList {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void add(int idx, int data) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException();
        }
        if (idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node prev = head;
        for (int i = 0; i < idx - 1; i++) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public int removeFirst() {
        if (head == null) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }
        size--;
        int val = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return val;
    }

    public int removeLast() {
        if (head == null) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            return removeFirst();
        }
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        size--;
        int val = tail.data;
        prev.next = null;
        tail = prev;
        return val;
    }

    public void print() {
        if (head == null) {
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
- **Dummy Head Node:** Simplifies edge cases for insertions/deletions.
- **Generic LinkedList:** Use generics for type safety.
- **Instance-Based:** Use non-static members for multiple list instances.
- **Circular LinkedList:** Connect tail to head for circular structure.

## Personal Notes
- Changed static members to instance variables for better encapsulation and flexibility.
- Tail pointer optimization significantly improves addLast performance.
- This implementation is foundational for understanding linked list manipulations.

---
**Tags:** #linkedlist #data_structures #basic_operations #pointer_manipulation