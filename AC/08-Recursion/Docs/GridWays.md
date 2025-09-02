# Grid Ways - Recursive Path Counting

**Source:** AC | **Topic:** Recursion | **Difficulty:** Intermediate  

---

## Problem Statement
Count the number of ways to move from the top-left (0,0) to bottom-right (n-1,m-1) in an n×m grid using only right and down moves.

## Intuition/Approach
- From any cell (i,j), total paths = paths if you go down + paths if you go right.
- Base: reaching destination counts as 1 path; crossing boundary counts as 0.
- This is a classic combinatorial/DP problem; naive recursion is exponential.

## Key Observations
- Overlapping subproblems: many (i,j) states recomputed.
- Closed form via combinations: C(n+m-2, n-1).
- DP/memoization reduces to O(nm) time.

## Algorithm Steps
1. If `i == n-1 && j == m-1`, return 1.
2. If `i == n || j == m`, return 0.
3. Return `gridWays(i+1, j, n, m) + gridWays(i, j+1, n, m)`.

## Complexity Analysis
- **Time Complexity:** O(2^(n+m)) (naive)
- **Space Complexity:** O(n+m)
- **Justification:** Binary branching with depth ~ n+m.

## Edge Cases Considered
- [x] 1×1 grid
- [x] 1×n or n×1 grid
- [x] Invalid dimensions
- [x] Start equals end
- [x] Small grids (manual verification)

## Solution Code

```java
public static int gridWays(int i, int j, int n, int m) {
    if (i == n - 1 && j == m - 1) return 1;
    if (i == n || j == m) return 0;
    return gridWays(i + 1, j, n, m) + gridWays(i, j + 1, n, m);
}
```

## Alternative Approaches
- Memoization: cache results for (i,j) to get O(nm) time.
- Bottom-up DP: tabulation from destination backward.
- Combinatorics: compute C(n+m-2, n-1) with precomputed factorials or Pascal’s triangle.

## Personal Notes
- Prefer combinatorial formula when n and m are reasonable and modulo not involved.

---
**Tags:** #recursion #dp #combinatorics