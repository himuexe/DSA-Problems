# LinkedList Basic Operations

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Easy  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Implement a singly linked list with fundamental operations including add (first, last, at index), remove (first, last), and print functionality. The linked list should maintain size tracking and handle edge cases properly.

## Intuition/Approach
Create a LinkedList class with Node structure and implement basic operations. Use head and tail pointers for efficient insertion/deletion. Maintain size counter for O(1) size operations. Handle edge cases like empty list and single-node scenarios.

## Key Observations
- Static members (head, tail, size) are shared across instances
- Size tracking enables O(1) size operations
- Tail pointer optimization for efficient last element access
- Edge case handling crucial for empty lists and single nodes
- Inner Node class provides encapsulation

## Algorithm Steps
1. Define Node class with data and next pointer
2. Initialize static head, tail, and size variables
3. Implement addFirst: create node, link to head, update head
4. Implement addLast: create node, link from tail, update tail
5. Implement add at index: traverse to position, insert node
6. Implement removeFirst: update head, handle size 1 case
7. Implement removeLast: traverse to second-last, update tail
8. Implement print: traverse and display all nodes

## Complexity Analysis
- **Time Complexity:** O(1) for add first/last, remove first; O(n) for add at index, remove last, print
- **Space Complexity:** O(1) for operations (not counting the list storage)
- **Justification:** Direct head/tail manipulation is constant time, traversal operations are linear

## Edge Cases Considered
- [x] Empty list operations (head == null)
- [x] Single-node list operations (size == 1)
- [x] Index bounds checking for add operations
- [x] Proper tail updates in remove operations
- [x] Size tracking consistency
- [x] Null pointer handling

## Solution Code

```java
// Language: Java
public class LinkedList{
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

    public void addLast(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void add(int idx, int data){
        if(idx == 0){
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node prev= head;
        for(int i=0;i<idx-1;i++){
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
    }

    public int removeFirst(){
        if(head == null){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }
        else if(size == 1){
            size=0;
            int val = head.data;
            head = tail = null;
            return val;
        }
        size--;
        int val = head.data;
        head = head.next;
        return val;
    }

    public int removeLast(){
        if(head == null){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }
        else if(size ==1){
            return removeFirst();
        }
        Node prev = head;
        for(int i=0;i<size-2;i++){
            prev = prev.next;
        }
        size--;
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        return val;
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
1. **Dummy Head**: Use dummy head node to simplify edge cases
2. **Circular LinkedList**: Link tail to head for circular structure
3. **Generic Implementation**: Use generics for type safety
4. **Non-static Members**: Use instance variables instead of static

## Related Problems
- **AC:** Search in LinkedList, Reverse LinkedList
- **Kunal:** LinkedList implementation variations
- **LeetCode:** #707 Design Linked List, #206 Reverse Linked List

## Personal Notes
- Fundamental building block for all linked list problems
- Static members make all instances share the same list
- Tail pointer optimization crucial for efficient operations
- Foundation for understanding more complex linked list algorithms

## Revision History
- **First Solve:** 2025-07-02 - Implemented complete LinkedList with all basic operations
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #datastructures #basic-operations #head-tail #size-tracking 