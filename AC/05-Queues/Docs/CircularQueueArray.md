# Circular Queue Using Array

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a circular queue using an array supporting enqueue (`add`) and dequeue (`remove`) with wrap-around. Detect full and empty states reliably.

## Intuition/Approach
Maintain `front` and `rear` indices and use modulo arithmetic over a fixed-size array. Empty when both are `-1`; full when `(rear + 1) % size == front`.

## Key Observations
- Circular wrap avoids O(n) shifts.
- Reset to `-1` when queue becomes empty again.
- Correct full/empty checks prevent overwrite and underflow.

## Algorithm Steps
1. Initialize `front = rear = -1`, allocate `arr[size]`.
2. Enqueue: if full, reject; if `front == -1`, set to 0; set `rear = (rear + 1) % size`, write value.
3. Dequeue: if empty, reject; read `arr[front]`; if `front == rear`, set both to `-1`; else `front = (front + 1) % size`.

## Complexity Analysis
- **Time Complexity:** O(1) per operation
- **Space Complexity:** O(n)
- **Justification:** Constant-time index updates on a fixed array.

## Edge Cases Considered
- [x] Dequeue on empty
- [x] Enqueue on full
- [x] Single element add/remove
- [x] Wrap-around boundary
- [ ] Other: dynamic resizing (not included)

## Solution Code

```java
public class CircularQueueArray {
    static class Queue{
        static int arr[];
        static int size;
        static int front;
        static int rear;
        Queue(int n){
            arr = new int[n];
            size=n;
            front=-1;
            rear=-1;
        }
        public static boolean isEmpty(){
            return front == -1 && rear == -1;
        }
        public static boolean isFull(){
            return (rear + 1) % size == front;
        }
        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }
            if(front == -1){
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int result = arr[front];
            if(front == rear){
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }
    }
}
```

## Alternative Approaches
- Dynamic circular buffer that resizes when full.
- Use `Deque<Integer>` to avoid manual indices.

## Personal Notes
Boundary tests around becoming full/empty and wrap transitions prevent most bugs.

---
**Tags:** #queues #array #circular-queue #implementation
