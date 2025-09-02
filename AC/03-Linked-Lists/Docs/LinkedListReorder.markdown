# Linked List Reorder

**Source:** AC | **Topic:** LinkedList, Pointer Manipulation | **Difficulty:** Hard

---

## Problem Statement
Reorder a singly linked list from L₀→L₁→L₂→...→Lₙ₋₁→Lₙ to L₀→Lₙ→L₁→Lₙ₋₁→L₂→Lₙ₋₂→... by alternating nodes from the beginning and end, performing the operation in-place with O(1) space.

## Intuition/Approach
Use a three-phase approach:
- Find the middle of the list using Floyd’s algorithm and split it.
- Reverse the second half to access nodes from the end.
- Merge the first half with the reversed second half alternately.

## Key Observations
- Floyd’s algorithm efficiently locates the middle without counting nodes.
- Reversing the second half allows accessing end nodes in order.
- Alternating merge weaves the two halves together.
- In-place operation minimizes space usage.
- Odd-length lists have one extra node in the first half, handled naturally.

## Algorithm Steps
1. If list is empty or single-node, return.
2. **Phase 1: Split**
   - Use slow/fast pointers (fast starts at head) to find middle.
   - Split list by setting mid.next = null.
3. **Phase 2: Reverse**
   - Reverse second half using three-pointer technique.
4. **Phase 3: Merge**
   - Alternate nodes from first half (head) and reversed second half.
   - Update pointers to weave lists together.
   - Handle any remaining nodes from first half (for odd length).
5. Ensure proper null termination.

## Complexity Analysis
- **Time Complexity:** O(n) - Three linear passes (middle, reverse, merge).
- **Space Complexity:** O(1) - Only pointers used.
- **Justification:** Each phase (finding middle, reversing, merging) is O(n), totaling O(n).

## Edge Cases Considered
- [x] Empty list.
- [x] Single node (no reordering needed).
- [x] Two nodes (simple swap).
- [x] Odd vs. even length lists.
- [x] Three-node list (minimum reordering case).
- [x] Large lists with long second halves.

## Solution Code
```java
public class ReorderLL {
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

    public void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        // Phase 1: Find middle
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null; // Split list

        // Phase 2: Reverse second half
        Node prev = null;
        Node curr = secondHalf;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Phase 3: Merge alternately
        Node first = head;
        Node second = prev;
        while (second != null) {
            Node temp1 = first.next;
            Node temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
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
- **Stack-Based:** Store second half in stack (O(n) space).
- **Array Conversion:** Convert to array, reorder, rebuild (O(n) space).
- **Recursive Reordering:** Use recursion (O(n) stack space).
- **Deque-Based:** Use deque for front/back access (O(n) space).

## Personal Notes
- Changed static members to instance variables for better encapsulation.
- The three-phase approach breaks down a complex problem into manageable steps.
- Floyd’s algorithm and reversal techniques are reused effectively here.
- This problem tests mastery of multiple linked list patterns.

---
**Tags:** #linkedlist #reorder #floyd_algorithm #reversal #three_phase