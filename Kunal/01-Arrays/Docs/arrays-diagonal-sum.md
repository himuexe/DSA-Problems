# Diagonal Sum of Matrix

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given a square matrix, calculate the sum of elements on both main diagonals. The main diagonal goes from top-left to bottom-right, and the secondary diagonal goes from top-right to bottom-left.

## Intuition/Approach
Iterate through the matrix and add elements where row index equals column index (main diagonal) and where row index plus column index equals n-1 (secondary diagonal). Handle the case where both diagonals intersect at the center.

## Key Observations
- Main diagonal: i == j
- Secondary diagonal: i + j == n-1
- Center element counted only once if n is odd
- Use single loop to process both diagonals
- Handle intersection point correctly

## Algorithm Steps
1. Initialize sum = 0
2. Iterate through matrix from 0 to n-1
3. Add element at (i, i) to sum (main diagonal)
4. Add element at (i, n-1-i) to sum (secondary diagonal)
5. If n is odd, subtract center element (counted twice)
6. Return final sum

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through diagonal elements

## Edge Cases Considered
- [x] 1x1 matrix - Return single element
- [x] 2x2 matrix - Add all four corner elements
- [x] 3x3 matrix - Handle center element correctly
- [x] Large matrix - Handle efficiently
- [x] All elements same - Calculate correctly
- [x] Zero matrix - Return 0

## Solution Code

```java
// Language: Java
public static int diagonalSum(int[][] mat) {
    int n = mat.length;
    int sum = 0;
    
    for (int i = 0; i < n; i++) {
        sum += mat[i][i];  // Main diagonal
        sum += mat[i][n - 1 - i];  // Secondary diagonal
    }
    
    // If n is odd, center element was counted twice
    if (n % 2 == 1) {
        sum -= mat[n / 2][n / 2];
    }
    
    return sum;
}
```

## Alternative Approaches
- **Two separate loops:** One for each diagonal
- **Four pointers:** Track both diagonals simultaneously
- **Recursive:** Divide matrix into quadrants

## Personal Notes
This is a straightforward matrix problem that tests understanding of diagonal traversal and index manipulation. The key insight is recognizing the patterns for both diagonals and handling the center element correctly when the matrix size is odd. This approach is clean and efficient.

---
**Tags:** #arrays #matrix #diagonal #sum #easy
