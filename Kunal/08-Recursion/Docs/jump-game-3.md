# Jump Game III

**Source:** Kunal | **Topic:** Recursion/DFS | **Difficulty:** Medium  

---

## Problem Statement
Given a non-negative integer array and a start index, from index i you may jump to i + arr[i] or i - arr[i]. Determine if you can reach any index with value 0.

## Intuition/Approach
- DFS/recursion from the start index while marking visited to avoid loops.
- Use in-place marking (negate) or a separate visited array.
- Explore both forward and backward jumps.

## Key Observations
- Stop if index out of bounds or already visited.
- Return true immediately when encountering arr[i] == 0.
- Each index is processed at most once.

## Algorithm Steps
1. If `start` is out of bounds or visited, return false.
2. If `arr[start] == 0`, return true.
3. Mark visited; recurse to `start + arr[start]` and `start - arr[start]`.
4. Return OR of the two results.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each index visited at most once; recursion depth ≤ n.

## Edge Cases Considered
- [x] Start already at zero
- [x] No zero exists
- [x] Out-of-bounds jumps
- [x] Cycles
- [x] Single element array

## Solution Code

```java
class Solution {
    public boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0) return false;
        if (arr[start] == 0) return true;
        int jump = arr[start];
        arr[start] = -arr[start]; // mark visited
        return canReach(arr, start + jump) || canReach(arr, start - jump);
    }
}
```

## Alternative Approaches
- BFS with a queue and visited set/array.
- Use boolean visited[] to avoid modifying input.

## Personal Notes
- In interviews, mention both in-place marking and visited array trade-offs.

---
**Tags:** #dfs #recursion #arrays