# Matrix Diagonal Sum

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Calculate the sum of elements on both diagonals of a square matrix. For a square matrix, find the sum of:
- Primary diagonal (top-left to bottom-right): elements where i == j
- Secondary diagonal (top-right to bottom-left): elements where i + j == n - 1
If the matrix has odd dimensions, the center element should only be counted once (as it's common to both diagonals).

## Intuition/Approach
Directly access diagonal elements. For each row i, add arr[i][i] (primary diagonal) and arr[i][n-1-i] (secondary diagonal). Avoid double counting the center element in odd matrices by checking if i != n-1-i.

## Key Observations
- Primary diagonal elements have equal row and column indices
- Secondary diagonal for row i is at column (n-1-i)
- Center element in odd-sized matrices appears on both diagonals
- Direct access eliminates need for nested loops

## Algorithm Steps
1. Initialize sum = 0
2. For each row i:
   - Add arr[i][i] (primary diagonal)
   - If i != n-1-i, add arr[i][n-1-i] (secondary diagonal)
3. Return sum

## Complexity Analysis
- **Time Complexity:** O(n) - Single loop through rows
- **Space Complexity:** O(1) - Only using variables for sum calculation
- **Justification:** Each diagonal element accessed directly

## Edge Cases Considered
- [x] Single element matrix (1x1)
- [x] Even-sized matrix
- [x] Odd-sized matrix
- [x] Empty matrix

## Solution Code
```java
public static int diagonalSum(int[][] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
        // Add primary diagonal element
        sum += arr[i][i];
        // Add secondary diagonal element (if different from primary)
        if (i != arr.length - i - 1) {
            sum += arr[i][arr.length - i - 1];
        }
    }
    return sum;
}
```

## Alternative Approaches
- Brute force: Check all elements for diagonal conditions
- Two-pass method: Calculate primary and secondary diagonal sums separately, then adjust for center overlap

## Personal Notes
Diagonal sum is a classic matrix operation. Direct access to diagonals is efficient and avoids unnecessary checks. Good for practicing 2D array indexing.

---
**Tags:** #arrays #matrix #diagonal #sum #2Darrays 