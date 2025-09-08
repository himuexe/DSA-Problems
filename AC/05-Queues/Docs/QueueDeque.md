# Queue Using Deque

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a queue using Java's `Deque` with `enqueue`, `dequeue`, `isEmpty`, and `size` operations.

## Intuition/Approach
Use `LinkedList` as a `Deque`. Enqueue at tail (`addLast`), dequeue from head (`removeFirst`).

## Key Observations
- `Deque` provides efficient O(1) head/tail operations.
- Exceptions are thrown on underflow for `removeFirst`.
- Minimal code, leverages standard library.

## Algorithm Steps
1. Store elements in `Deque<Integer> deque = new LinkedList<>()`.
2. Enqueue: `deque.addLast(value)`.
3. Dequeue: if empty, throw; else `deque.removeFirst()`.
4. Helpers: `isEmpty()`, `size()` delegate to `deque`.

## Complexity Analysis
- **Time Complexity:** O(1) amortized per operation
- **Space Complexity:** O(n)
- **Justification:** Linked list-backed deque operations are constant time.

## Edge Cases Considered
- [x] Dequeue on empty (throw exception)
- [x] Large sequences of operations
- [ ] Null elements (not used)

## Solution Code

```java
import java.util.*;

public class QueueDeque {
    static class Queue{
        Deque<Integer> deque = new LinkedList<>();

        public void enqueue(int value) {
            deque.addLast(value);
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return deque.removeFirst();
        }

        public boolean isEmpty() {
            return deque.isEmpty();
        }

        public int size() {
            return deque.size();
        }
    }   
}
```

## Alternative Approaches
- Use `ArrayDeque` for better cache locality.
- Implement with circular array for more control.

## Personal Notes
`ArrayDeque` is generally preferred over `LinkedList` for queues in Java.

---
**Tags:** #queues #deque #java
