# Reverse Doubly Linked List

**Source:** AC | **Topic:** LinkedList, Pointer Manipulation | **Difficulty:** Medium

---

## Problem Statement
Reverse a doubly linked list by swapping each node’s prev and next pointers, updating the head to point to the original tail.

## Intuition/Approach
Traverse the list, swapping prev and next pointers for each node. Use the original next pointer to move forward. Update head to the last processed node (original tail).

## Key Observations
- Swapping prev and next pointers reverses the list direction.
- Original next pointer drives traversal to avoid losing connectivity.
- Simpler than singly linked list reversal due to bidirectional pointers.
- Head and tail updates are critical for correctness.

## Algorithm Steps
1. If list is empty or single-node, return.
2. Initialize current = head, prev = null.
3. For each node:
   - Store next = current.next.
   - Swap current.next = current.prev and current.prev = next.
   - Move forward: prev = current, current = next.
4. Set head = prev (original tail).

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the list.
- **Space Complexity:** O(1) - Only temporary pointers used.
- **Justification:** Each node is processed once with constant space.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node.
- [x] Two-node list.
- [x] Head and tail pointer consistency.
- [x] Bidirectional link integrity.

## Solution Code
```java
public class ReverseDLL {
    class Node {
        int data;
        Node next;
        Node prev;
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
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
        head.prev = newNode;
        head = newNode;
    }

    public void reverse() {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node next = current.next;
            current.next = current.prev;
            current.prev = next;
            prev = current;
            current = next;
        }
        head = prev;
        if (head != null) {
            tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
        }
    }

    public void print() {
        if (head == null) {
            System.out.println("DLL is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
- **Recursive Reversal:** Use recursion with O(n) stack space.
- **Two-Pass:** First reverse links, then update head/tail.
- **Stack-Based:** Store nodes and rebuild (O(n) space).
- **Array Conversion:** Convert to array, reverse, rebuild (O(n) space).

## Personal Notes
- Swapping pointers is simpler than singly linked list reversal due to prev pointers.
- Ensuring tail updates correctly was critical for bidirectional integrity.
- This problem reinforces pointer manipulation skills.

---
**Tags:** #doubly_linkedlist #reversal #pointer_manipulation #bidirectional