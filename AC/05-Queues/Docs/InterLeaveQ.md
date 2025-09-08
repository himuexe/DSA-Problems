# Interleave First and Second Halves of a Queue

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Given a queue with an even number of elements, interleave its first half with the second half in-place.

## Intuition/Approach
Move the first half into a temporary queue. Then alternately add one element from the first-half queue and one from the remaining second-half elements in the original queue.

## Key Observations
- Requires even-sized queue; otherwise invalid.
- Only O(n) extra space for the temporary queue.
- Order within each half is preserved while interleaving.

## Algorithm Steps
1. Validate `queue.size()` is even; else throw error.
2. Dequeue first half into `firstHalf` queue.
3. While `firstHalf` not empty: `queue.add(firstHalf.remove()); queue.add(queue.remove());`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each element moved a constant number of times; extra queue stores n/2 elements.

## Edge Cases Considered
- [x] Empty queue (even size)
- [x] Size 2 minimal case
- [x] Large even sizes
- [ ] Odd size (raises error)

## Solution Code

```java
import java.util.*;
public class InterLeaveQ {
    public static void interLeave(Queue<Integer> queue) {
        if (queue.size() % 2 != 0) {
            throw new IllegalArgumentException("Queue must have an even number of elements");
        }
        int halfSize = queue.size() / 2;
        Queue<Integer> firstHalf = new LinkedList<>();
        for (int i = 0; i < halfSize; i++) {
            firstHalf.add(queue.remove());
        }
        while (!firstHalf.isEmpty()) {
            queue.add(firstHalf.remove());
            queue.add(queue.remove());
        }
    }
}
```

## Alternative Approaches
- Use stack to interleave with different constraints.
- Perform interleaving by rotating the queue n/2 times with careful sequencing.

## Personal Notes
Validate even size early to avoid partial transformations.

---
**Tags:** #queues #interleave #algorithm
