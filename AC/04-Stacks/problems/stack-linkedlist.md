# Stack Implementation Using Linked List

**Source:** AC (Apna College)  
**Topic:** Stacks  
**Difficulty:** Medium  
**Date:** 2025-07-05

## Problem Statement

Implement a stack data structure using a linked list. The implementation should support basic stack operations: push, pop, peek, and isEmpty. The linked list should provide dynamic memory allocation without fixed size limitations.

## Intuition/Approach

Use a singly linked list where:
1. **Head node** represents the top of the stack
2. **Push operation** adds new node at the head
3. **Pop operation** removes head node and returns its data
4. **Peek operation** returns head node data without removal

The key insight is that linked list head provides O(1) access, making it ideal for stack operations.

## Key Observations

- Linked list head serves as stack top for O(1) operations
- Dynamic memory allocation removes size constraints
- No memory waste (allocates exactly what's needed)
- Push/pop operations only modify head reference

## Algorithm Steps

1. **Node Structure:** Each node contains data and reference to next node
2. **Push Operation:**
   - Create new node with given data
   - Point new node to current head
   - Update head to new node
3. **Pop Operation:**
   - Check if stack is empty
   - Store head data
   - Update head to next node
   - Return stored data
4. **Peek Operation:** Return head data without modification

## Complexity Analysis

- **Time Complexity:** O(1) for all operations (push, pop, peek, isEmpty)
- **Space Complexity:** O(n) - where n is number of elements in stack

## Edge Cases

- [ ] Empty stack → isEmpty() returns true, pop/peek return -1
- [ ] Single element → pop makes stack empty
- [ ] Large number of elements → no size limit (memory permitting)
- [ ] Pop from empty stack → returns -1 (error handling)

## Solution Code

```java
public class StackLL {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Stack {
        Node head = null;

        public boolean isEmpty() {
            return head == null;
        }

        public void push(int data) {
            Node newNode = new Node(data);
            if (isEmpty()) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        public int pop() {
            if (isEmpty()) {
                return -1; // Error: stack underflow
            }
            int top = head.data;
            head = head.next;
            return top;
        }

        public int peek() {
            if (isEmpty()) {
                return -1; // Error: stack empty
            }
            return head.data;
        }
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        st.push(1);
        st.push(2);
        st.push(3);

        while (!st.isEmpty()) {
            System.out.println(st.peek());
            st.pop();
        }
    }
}
```

## Alternative Approaches

1. **Array-based Stack:** Use array with top pointer (fixed size)
2. **ArrayList-based Stack:** Use dynamic array (built-in resizing)
3. **Doubly Linked List:** Use doubly linked list (more memory overhead)

## Related Problems

- **Stack Using ArrayList** (alternative implementation)
- **Queue Using Linked List** (similar linked list usage)
- **Reverse Linked List** (similar linked list manipulation)
- **LRU Cache** (uses linked list for stack-like operations)

## Personal Notes

- Excellent for understanding linked list applications
- Demonstrates dynamic memory allocation advantages
- Good foundation for understanding stack internals
- Important for implementing custom data structures

## Tags

`#stack` `#linked-list` `#implementation` `#data-structure` `#medium` `#dynamic-memory`

---

**Revision History:**
- 2025-07-05: Initial documentation with comprehensive algorithm analysis 