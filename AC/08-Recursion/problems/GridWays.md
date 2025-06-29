# Grid Ways - Recursive Path Counting

## Problem Statement
Count the number of ways to move from the top-left corner (0,0) to the bottom-right corner (n-1,m-1) of an n×m grid, where you can only move right or down.

## Intuition/Approach
**Recursive Path Exploration:**
1. **Base Cases:** 
   - If reached destination (n-1,m-1), return 1 (found one valid path)
   - If out of bounds (i==n or j==m), return 0 (invalid path)
2. **Recursive Choices:** From any position, sum paths from:
   - Moving down: gridWays(i+1, j, n, m)
   - Moving right: gridWays(i, j+1, n, m)

**Key Insight:** Total paths = paths going down + paths going right from current position.

## Key Observations
- **Path Constraints:** Only rightward and downward movements allowed
- **Exponential Complexity:** Each cell has 2 choices, leading to 2^(n+m) recursive calls
- **Overlapping Subproblems:** Same (i,j) positions calculated multiple times
- **Mathematical Formula:** Answer equals C(n+m-2, n-1) = (n+m-2)! / ((n-1)!(m-1)!)
- **Classic DP Problem:** Can be optimized with memoization or bottom-up approach

## Algorithm Steps
1. **Base Case Check:**
   - If i == n-1 AND j == m-1: reached destination, return 1
   - If i == n OR j == m: out of bounds, return 0

2. **Recursive Exploration:**
   - Calculate paths by moving down: gridWays(i+1, j, n, m)
   - Calculate paths by moving right: gridWays(i, j+1, n, m)
   - Return sum of both possibilities

## Time & Space Complexity
- **Time Complexity:** O(2^(n+m))
  - Each position has 2 choices (right/down)
  - Total positions to explore: approximately 2^(n+m-2)
  - Exponential due to overlapping subproblems
- **Space Complexity:** O(n+m)
  - Recursion stack depth: maximum n+m-2 levels
  - No additional data structures used

## Edge Cases Considered
- [x] 1×1 grid (already at destination)
- [x] 1×n or n×1 grid (only one path possible)
- [x] Small grids (2×2, 3×3)
- [x] Starting position equals ending position
- [x] Invalid grid dimensions

## Code Implementation
```java
public static int gridWays(int i, int j, int n, int m) {
    // Base case: reached destination
    if (i == n - 1 && j == m - 1) {
        return 1;  // Found one valid path
    }
    
    // Base case: out of bounds
    else if (i == n || j == m) {
        return 0;  // Invalid path
    }
    
    // Recursive case: sum paths from both directions
    return gridWays(i + 1, j, n, m) +    // Move down
           gridWays(i, j + 1, n, m);     // Move right
}
```

## Example Walkthrough
**Input:** 3×3 grid (n=3, m=3)
**Call:** gridWays(0, 0, 3, 3)

**Path Tree (simplified):**
```
(0,0) → (1,0) + (0,1)
(1,0) → (2,0) + (1,1)
(0,1) → (1,1) + (0,2)
...
Final paths: 6 total ways
```

**Mathematical Verification:**
C(3+3-2, 3-1) = C(4,2) = 4!/(2!×2!) = 6 ✓

## Optimization Opportunities
1. **Memoization:** Store results for (i,j) positions
2. **Dynamic Programming:** Bottom-up table filling
3. **Mathematical Formula:** Direct calculation using combinations
4. **Space Optimization:** 1D DP array instead of 2D

## Key Learning Points
- **Recursive Thinking:** Breaking down problem into smaller subproblems
- **Path Counting:** Understanding combinatorial recursion patterns
- **Optimization Awareness:** Recognizing exponential complexity issues
- **Base Case Design:** Proper boundary condition handling

## Applications
- **Robot Path Planning:** Grid navigation problems
- **Dynamic Programming:** Foundation for optimization problems
- **Combinatorics:** Understanding path counting in constraints
- **Algorithm Design:** Recursive problem decomposition

---
**Date:** June 28, 2025  
**Topic:** Recursion & Path Counting  
**Difficulty:** Intermediate  
**Category:** Combinatorial Recursion 