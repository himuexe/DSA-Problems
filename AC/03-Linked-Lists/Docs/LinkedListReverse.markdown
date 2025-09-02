# Reverse Linked List

**Source:** AC | **Topic:** LinkedList, Pointer Manipulation | **Difficulty:** Medium

---

## Problem Statement
Reverse a singly linked list in-place by changing the direction of pointers, transforming 1→2→3→4→5 to 5→4→3→2→1.

## Intuition/Approach
Use a three-pointer iterative approach (prev, curr, next):
- Save the next node before breaking the current link.
- Reverse the current node’s pointer to point to prev.
- Move all pointers forward one step.
- Update head to the last processed node.

## Key Observations
- Three-pointer technique ensures in-place reversal.
- Saving the next pointer prevents losing the list.
- Original head becomes the new tail, and last node becomes the new head.
- Single pass is sufficient for complete reversal.

## Algorithm Steps
1. Initialize prev = null, curr = head.
2. Set tail = head (original head becomes new tail).
3. While curr != null:
   - Save next = curr.next.
   - Set curr.next = prev.
   - Move prev = curr, curr = next.
4. Update head = prev.

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the list.
- **Space Complexity:** O(1) - Only three pointers used.
- **Justification:** Each node is processed once with constant extra space.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node (no change needed).
- [x] Two-node list (simple swap).
- [x] Head and tail updates.
- [x] Large lists with many nodes.

## Solution Code
```java
public class ReverseLL {
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

    public void reverse() {
        Node prev = null;
        Node curr = tail = head; // Original head becomes new tail
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
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
- **Recursive Reversal:** Use recursion (O(n) stack space).
- **Stack-Based:** Store nodes in stack, rebuild (O(n) space).
- **Array Conversion:** Convert to array, reverse, rebuild (O(n) space).
- **Two-Pass:** Count nodes first, then reverse (unnecessary).

## Personal Notes
- Changed static members to instance variables for better encapsulation.
- The three-pointer technique is fundamental and widely applicable.
- Saving the next pointer is critical to maintain list connectivity.
- This is a core problem for understanding linked list manipulations.

---
**Tags:** #linkedlist #reversal #three_pointer #in_place