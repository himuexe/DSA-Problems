# Queue Using Linked List

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a queue using a singly linked list with `add`, `remove`, and `peek` in O(1) time.

## Intuition/Approach
Maintain `head` and `tail` pointers. Enqueue at `tail`, dequeue from `head`. Empty when both are `null`.

## Key Observations
- O(1) enqueue and dequeue without shifting.
- When removing last remaining node, set both `head` and `tail` to `null`.
- Handle empty checks before operations.

## Algorithm Steps
1. Define `Node{int data; Node next;}`.
2. Enqueue: create node; if `tail==null`, set `head=tail=newNode`; else `tail.next=newNode; tail=newNode`.
3. Dequeue: if empty, reject; save `head.data`; if `head==tail`, set both `null`; else `head=head.next`.
4. Peek: if empty, reject; return `head.data`.

## Complexity Analysis
- **Time Complexity:** O(1) per operation
- **Space Complexity:** O(n)
- **Justification:** Pointer updates only; nodes store values.

## Edge Cases Considered
- [x] Operations on empty queue
- [x] Single element becoming empty
- [x] Multiple sequential operations

## Solution Code

```java
public class QueueLL {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    static class Queue{
        static Node head = null;
        static Node tail = null;
        public static boolean isEmpty(){
            return head == null && tail == null;
        }
        public static void add(int data){
            Node newNode = new Node(data);
            if(tail == null){
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int front = head.data;
            if(head == tail){
                head = tail = null;
            } else {
                head = head.next;
            }
            return front;
        }
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            return head.data;
        }
    }
}
```

## Alternative Approaches
- Use `Deque<Integer>` for built-in queue operations.
- Circular linked list variant to simplify edge checks.

## Personal Notes
Linked list queues scale well and avoid array resizing or shifts.

---
**Tags:** #queues #linkedlist #implementation
