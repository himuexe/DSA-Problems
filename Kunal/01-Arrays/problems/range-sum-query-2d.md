# Range Sum Query 2D - Immutable

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given a 2D matrix, implement a class that supports querying the sum of elements inside a rectangle defined by (row1, col1) as top-left corner and (row2, col2) as bottom-right corner.

## Intuition/Approach
Use 2D prefix sum array where dp[i][j] represents sum of rectangle from (0,0) to (i-1,j-1). For any rectangle query, use inclusion-exclusion principle to calculate sum in O(1) time.

## Key Observations
- Precompute 2D prefix sums during initialization
- Use inclusion-exclusion: sum = total - left - top + overlap
- dp[i][j] = dp[i-1][j] + dp[i][j-1] + matrix[i-1][j-1] - dp[i-1][j-1]
- Query: dp[row2+1][col2+1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row1][col1]
- 1-indexed dp array simplifies boundary conditions

## Algorithm Steps
1. **Initialization:**
   - Create dp array of size (m+1) × (n+1)
   - Fill dp using 2D prefix sum formula
2. **Query:**
   - Use inclusion-exclusion principle
   - Return sum using precomputed dp values
3. **Formula:** total - left_strip - top_strip + overlap

## Complexity Analysis
- **Time Complexity:** O(mn) initialization, O(1) per query
- **Space Complexity:** O(mn) - 2D prefix sum array
- **Justification:** Preprocessing enables constant-time queries

## Edge Cases Considered
- [x] Single cell rectangle
- [x] Entire matrix as rectangle
- [x] Rectangle on matrix boundaries
- [x] Single row/column rectangles
- [x] Matrix with negative numbers
- [x] Zero-area rectangles

## Solution Code

```java
// Language: Java
class NumMatrix {
    int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for(int r = 0; r < matrix.length; r++){
            for(int c = 0; c < matrix[0].length; c++){
                dp[r + 1][c + 1] = dp[r][c + 1] + dp[r + 1][c] + matrix[r][c] - dp[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
```

## Alternative Approaches
- **Brute Force:** Calculate sum for each query O(mn) per query
- **1D Prefix Sums:** Compute row-wise prefix sums O(n) per query
- **Segment Tree 2D:** More complex but supports updates

## Related Problems
- **AC:** [Prefix Sum, Range Queries, 2D Array Operations]
- **Kunal:** [Subarray Sum, Matrix Problems]
- **LeetCode:** [304. Range Sum Query 2D, 303. Range Sum Query, 308. Range Sum Query 2D - Mutable]

## Personal Notes
Classic 2D prefix sum problem demonstrating inclusion-exclusion principle. The key insight is preprocessing to enable constant-time queries. Essential pattern for range query optimization in 2D structures.

## Revision History
- **First Solve:** June 26, 2025 - Implemented 2D prefix sum with inclusion-exclusion

---
**Tags:** #prefixsum #2d #matrix #rangequery #inclusionexclusion 