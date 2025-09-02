# Linked List Merge Sort

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Hard  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Sort a linked list using merge sort algorithm with divide and conquer approach. Split the list into halves, recursively sort each half, and merge them back together. Achieve O(n log n) time complexity.

## Intuition/Approach
Apply divide and conquer strategy: recursively split the list into smaller sublists until single nodes remain, then merge them back in sorted order. Use Floyd's algorithm to find middle, split the list, and recursively sort both halves before merging.

## Key Observations
- Divide and conquer approach is optimal for linked list sorting
- Floyd's algorithm efficiently finds middle without counting nodes
- Merge operation combines two sorted lists into one sorted list
- Recursive structure naturally handles all cases
- In-place sorting with only pointer manipulation

## Algorithm Steps
1. Base case: if head == null or head.next == null, return head
2. Find middle using slow/fast pointers (Floyd's algorithm)
3. Split list into two halves at middle point
4. Recursively sort both halves
5. Merge the two sorted halves back together
6. Return merged sorted list

## Complexity Analysis
- **Time Complexity:** O(n log n) - Standard merge sort complexity
- **Space Complexity:** O(log n) - Due to recursion stack depth
- **Justification:** Divides into log n levels, each level takes O(n) time

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node list (head.next == null)
- [x] Two-node list (minimum merge case)
- [x] Already sorted lists
- [x] Reverse sorted lists
- [x] Lists with duplicate values

## Solution Code

```java
// Language: Java
public class SortLL {
    class Node{
        int data;
        Node next;
        Node (int data ){
            this.data = data;
            this.next = null;
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
        head = newNode;
    }

    private Node getMid(Node head){
        Node slow = head;
        Node fast = head.next;  // Important: start fast at head.next
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;  // slow is the middle node
    }

    private Node merge(Node head1, Node head2){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        
        while(head1 != null && head2 != null){
            if(head1.data <= head2.data){
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        
        // Add remaining nodes
        while(head1 != null){
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while(head2 != null){
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        
        return mergedLL.next;
    }

    public Node mergeSort(Node head){
        if(head == null || head.next == null){
            return head;
        }
        
        // Find middle
        Node mid = getMid(head);
        
        // Split the list
        Node rightHead = mid.next;
        mid.next = null;
        
        // Recursively sort both halves
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);
        
        // Merge the sorted halves
        return merge(newLeft, newRight);
    }

    public void print(){
        if(head == null){
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
1. **Iterative Merge Sort**: Bottom-up approach without recursion (O(1) space)
2. **Quick Sort**: Alternative O(n log n) algorithm (not stable)
3. **Insertion Sort**: O(n²) but better for small lists
4. **Heap Sort**: O(n log n) but requires extra space

## Related Problems
- **AC:** LinkedList basic operations, Merge operations
- **Kunal:** Sorting algorithms, Divide and conquer
- **LeetCode:** #148 Sort List, #21 Merge Two Sorted Lists, #23 Merge k Sorted Lists

## Personal Notes
- Most efficient sorting algorithm for linked lists
- Key insight: use Floyd's algorithm for middle finding
- Important to start fast pointer at head.next for proper splitting
- Stable sort that maintains relative order of equal elements

## Revision History
- **First Solve:** 2025-07-02 - Implemented divide and conquer merge sort algorithm
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #merge-sort #divide-conquer #sorting #optimal 