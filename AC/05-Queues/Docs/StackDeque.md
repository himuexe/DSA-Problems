# Stack Using Deque

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Implement a stack (LIFO) using Java's `Deque` API with `push`, `pop`, `peek`, `isEmpty`, and `size`.

## Intuition/Approach
Use `Deque` as a double-ended queue. Push and pop at the front using `addFirst` and `removeFirst` to model stack behavior.

## Key Observations
- `Deque` is ideal for building stacks/queues.
- Using `removeFirst` throws on underflow; guard with `isEmpty`.
- `ArrayDeque` often outperforms `LinkedList`.

## Algorithm Steps
1. Store elements in `Deque<Integer> deque`.
2. Push: `deque.addFirst(value)`.
3. Pop: if empty, throw; else `deque.removeFirst()`.
4. Peek: if empty, throw; else `deque.peekFirst()`.

## Complexity Analysis
- **Time Complexity:** O(1) per operation
- **Space Complexity:** O(n)

## Edge Cases Considered
- [x] Pop/peek on empty (throw)
- [x] Long sequences of operations

## Solution Code

```java
import java.util.*;
public class StackDeque {
    static class Stack {
        Deque<Integer> deque;
        public Stack() {
            deque = new LinkedList<>();
        }
        public void push(int value) {
            deque.addFirst(value);
        }
        public int pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return deque.removeFirst();
        }
        public int peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return deque.peekFirst();
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
- Use Java's built-in `Stack` (legacy) or `ArrayDeque`.

## Personal Notes
Prefer `ArrayDeque` for production due to lower overhead than `LinkedList`.

---
**Tags:** #stack #deque #java
