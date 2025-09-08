# Implement Queue Using Two Stacks

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a queue with `add` and `remove` operations using two stacks.

## Intuition/Approach
Maintain two stacks `s1` and `s2`. To enqueue, move all from `s1` to `s2`, push new element to `s1`, then move back from `s2` to `s1`. This preserves queue order in `s1` so `remove` is just `pop()`.

## Key Observations
- Enqueue becomes O(n), dequeue O(1) in this variant.
- Alternative variant makes enqueue O(1) and dequeue amortized O(1) by lazy transfer.
- Use `isEmpty` guard before removing.

## Algorithm Steps
1. Enqueue: while `s1` not empty, push to `s2`; push new element to `s1`; move back from `s2` to `s1`.
2. Dequeue: if empty, return -1; else `s1.pop()`.

## Complexity Analysis
- **Time Complexity:** Enqueue O(n), Dequeue O(1)
- **Space Complexity:** O(n)
- **Justification:** Elements are transferred between stacks during enqueue.

## Edge Cases Considered
- [x] Remove on empty
- [x] Multiple enqueues before dequeues
- [x] Single element

## Solution Code

```java
import java.util.*;
public class QStack {
    static class Queue{
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();
        public static boolean isEmpty(){
            return s1.isEmpty();
        }
        public static void add(int data){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            s1.push(data);
            while(!s2.isEmpty()){
                s1.push(s2.pop());
            }
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.pop();
        }
    }
}
```

## Alternative Approaches
- Lazy transfer: keep in `s1` for enqueue, transfer to `s2` only when `s2` empty on dequeue.

## Personal Notes
Amortized O(1) dequeue approach is generally more efficient.

---
**Tags:** #queues #stacks #implementation
