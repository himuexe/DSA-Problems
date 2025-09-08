# Queue Using Array (Linear)

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a basic queue using an array supporting `add`, `remove`, and `peek` with a rear pointer (front always at index 0 by shifting on removal).

## Intuition/Approach
Track `rear` index; on `add`, insert at `rear+1`; on `remove`, shift elements left by one, decreasing `rear`. Simpler than circular queue but removal is O(n).

## Key Observations
- Shifting on every removal leads to O(n) cost.
- Suitable for small inputs or learning; prefer circular queue for performance.
- Empty if `rear == -1`; full if `rear == size-1`.

## Algorithm Steps
1. Initialize `rear = -1`, allocate `arr[size]`.
2. Add: if full, reject; set `arr[++rear] = data`.
3. Remove: if empty, reject; save `arr[0]`; shift `arr[i]=arr[i+1]` for `i in [0,rear-1]`; `rear--`.
4. Peek: if empty, reject; return `arr[0]`.

## Complexity Analysis
- **Time Complexity:** Add O(1), Remove O(n), Peek O(1)
- **Space Complexity:** O(n)
- **Justification:** Shifting dominates removals.

## Edge Cases Considered
- [x] Remove/peek on empty
- [x] Add on full
- [x] Single element
- [ ] Large input performance

## Solution Code

```java
public class QueueArray{
    static class Queue {
        static int arr[];
        static int size;
        static int rear;
        Queue(int n){
            arr = new int[n];
            size = n;
            rear = -1;
        }
        public static boolean isEmpty(){
            return rear == -1;
        }
        public static void add(int data){
            if(rear == size - 1){
                System.out.println("Queue is full");
                return;
            }
            rear++;
            arr[rear] = data;
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int front = arr[0];
            for(int i = 0; i < rear; i++){
                arr[i] = arr[i + 1];
            }
            rear--;
            return front;
        }
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            return arr[0];
        }
    }
}
```

## Alternative Approaches
- Circular array to avoid shifting.
- Linked list based queue for O(1) operations.

## Personal Notes
Good for understanding fundamentals; avoid in production due to O(n) removal.

---
**Tags:** #queues #array #implementation
