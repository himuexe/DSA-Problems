# Jump Game III

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of non-negative integers, you are initially positioned at start index. When at index i, you can jump to i + arr[i] or i - arr[i]. Check if you can reach any index with value 0.

## Intuition/Approach
Use DFS/recursion with visited tracking to explore all possible jumps from current position. Mark visited positions as negative to avoid infinite loops and explore both forward and backward jumps.

## Key Observations
- Can jump forward (i + arr[i]) or backward (i - arr[i]) from position i
- Need to avoid infinite loops by tracking visited positions
- Use array modification (negative values) to mark visited positions
- Base cases: out of bounds, already visited, or found zero
- Recursive exploration of both jump directions

## Algorithm Steps
1. Check base cases: out of bounds, already visited (negative), or found zero
2. If current value is 0, return true (target reached)
3. Mark current position as visited (make value negative)
4. Recursively try both jumps:
   - Forward jump: canReach(arr, start + arr[start])
   - Backward jump: canReach(arr, start - arr[start])
5. Return true if either direction succeeds

## Complexity Analysis
- **Time Complexity:** O(n) - each position visited at most once
- **Space Complexity:** O(n) - recursion call stack depth
- **Justification:** Each index processed once, stack depth bounded by array size

## Edge Cases Considered
- [x] Start position has value 0 (immediately return true)
- [x] No zero values in array (return false)
- [x] Jumps lead out of bounds
- [x] Circular jumps that revisit same positions
- [x] Single element array

## Solution Code

```java
// Language: Java
class Solution {
    public boolean canReach(int[] arr, int start) {
        if(start < 0 || start >= arr.length || arr[start] < 0) return false;
        if(arr[start] == 0) return true;
        arr[start] = -1 * arr[start];
        boolean left = canReach(arr, start + arr[start]);
        boolean right = canReach(arr, start - arr[start]);
        return left || right;
    }
}
```

## Alternative Approaches
- **BFS with Queue:** Level-by-level exploration using queue and visited set
- **DFS with Visited Set:** Use separate boolean array to track visited positions
- **Iterative DFS:** Use stack to avoid recursion overhead

## Related Problems
- **AC:** [Jump Game I, Jump Game II, Array Traversal]
- **Kunal:** [Path Finding, Graph Traversal, DFS Problems]
- **LeetCode:** [1306. Jump Game III, 55. Jump Game, 45. Jump Game II]

## Personal Notes
Excellent problem combining array traversal, recursion, and visited state management. The key insight is using array modification to track visited positions efficiently. Demonstrates practical application of DFS in array-based problems.

## Revision History
- **First Solve:** June 26, 2025 - Implemented recursive DFS with array modification

---
**Tags:** #dfs #recursion #arrays #jump #traversal 