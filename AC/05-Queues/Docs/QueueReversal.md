# Reverse a Queue Using Stack

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Reverse the order of all elements in a queue using an auxiliary stack.

## Intuition/Approach
Use a stack to reverse order: dequeue all items into a stack (LIFO), then pop from stack back to the queue.

## Key Observations
- Two-phase transfer reverses order.
- Works for any queue implementation.
- Uses O(n) extra space for the stack.

## Algorithm Steps
1. While queue not empty, `stack.push(queue.remove())`.
2. While stack not empty, `queue.add(stack.pop())`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each element is moved twice; stack stores all elements.

## Edge Cases Considered
- [x] Empty queue
- [x] Single element
- [x] Large queue

## Solution Code

```java
import java.util.*;
public class QueueReversal {
    public static void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
}
```

## Alternative Approaches
- Recursive reversal using call stack.
- Use `ArrayDeque` instead of `Stack`.

## Personal Notes
Ensure queue type supports `add` and `remove` semantics used here.

---
**Tags:** #queues #stack #reverse #algorithm
