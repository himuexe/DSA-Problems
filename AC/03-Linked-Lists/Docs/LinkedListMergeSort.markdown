# Linked List Merge Sort

**Source:** AC | **Topic:** LinkedList, Sorting | **Difficulty:** Hard

---

## Problem Statement
Sort a singly linked list using the merge sort algorithm with a divide-and-conquer approach, achieving O(n log n) time complexity.

## Intuition/Approach
Recursively split the list into two halves using Floyd’s algorithm to find the middle, sort each half, and merge them back into a sorted list. Use pointer manipulation for in-place merging.

## Key Observations
- Floyd’s algorithm efficiently finds the middle without counting nodes.
- Merging two sorted lists is linear and stable.
- Recursive splitting creates log n levels, each with O(n) merging.
- Fast pointer starts at head.next to ensure proper splitting.

## Algorithm Steps
1. Base case: If head == null or head.next == null, return head.
2. Find middle using slow/fast pointers (fast starts at head.next).
3. Split list: Set mid.next = null, get right half.
4. Recursively sort left and right halves.
5. Merge sorted halves using a dummy node for simplicity.
6. Return merged list head.

## Complexity Analysis
- **Time Complexity:** O(n log n) - log n levels, O(n) merging per level.
- **Space Complexity:** O(log n) - Recursion stack depth.
- **Justification:** Balanced splitting and linear merging yield optimal time; recursion stack is logarithmic.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node.
- [x] Two-node list.
- [x] Already sorted list.
- [x] Reverse sorted list.
- [x] Duplicate values.

## Solution Code
```java
public class SortLL {
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

    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        if (head1 != null) {
            temp.next = head1;
        }
        if (head2 != null) {
            temp.next = head2;
        }
        return mergedLL.next;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);
        return merge(newLeft, newRight);
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
- **Iterative Merge Sort:** Bottom-up approach (O(1) space).
- **Quick Sort:** O(n log n) average but unstable.
- **Insertion Sort:** O(n²) but simpler for small lists.
- **Heap Sort:** O(n log n) but requires extra space.

## Personal Notes
- Starting fast at head.next ensures even splitting.
- Merge sort is optimal for linked lists due to sequential access.
- Stable merging preserves relative order of equal elements.

---
**Tags:** #linkedlist #merge_sort #divide_and_conquer #sorting