# Rotate Image 90 Degrees

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Rotate an n×n 2D matrix representing an image by 90 degrees clockwise in-place. Do not allocate another 2D matrix.

## Intuition/Approach
Two-step process: first transpose the matrix (flip along main diagonal), then reverse each row (flip horizontally). This combination achieves 90-degree clockwise rotation.

## Key Observations
- 90° clockwise rotation = transpose + horizontal flip
- Transpose: swap matrix[i][j] with matrix[j][i]
- Reverse rows: swap matrix[i][j] with matrix[i][n-1-j]
- In-place operations save space
- Two separate passes for clarity and correctness

## Algorithm Steps
1. **Transpose Matrix:**
   - For i from 0 to n-1, j from i to n-1
   - Swap matrix[i][j] with matrix[j][i]
2. **Reverse Each Row:**
   - For each row i, reverse elements
   - Swap matrix[i][j] with matrix[i][n-1-j]
3. Matrix is now rotated 90° clockwise

## Complexity Analysis
- **Time Complexity:** O(n²) - visit each cell constant number of times
- **Space Complexity:** O(1) - in-place rotation, no extra space
- **Justification:** Two passes through matrix elements

## Edge Cases Considered
- [x] 1×1 matrix (no change needed)
- [x] 2×2 matrix (minimal rotation)
- [x] Odd dimension matrices
- [x] Even dimension matrices
- [x] Matrix with duplicate values
- [x] Matrix with negative numbers

## Solution Code

```java
// Language: Java
class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);  // Flip across main diagonal
        reverseRows(matrix); // Flip horizontally
    }
    
    // Flip across main diagonal (transpose)
    public void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    
    // Flip horizontally (reverse each row)
    public void reverseRows(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }
}
```

## Alternative Approaches
- **Four-way Swap:** Rotate 4 elements at once in cycles
- **Layer-by-layer:** Rotate outer layers progressively inward
- **Mathematical Formula:** Direct coordinate transformation

## Related Problems
- **AC:** [Matrix Transformation, 2D Array Rotation]
- **Kunal:** [Array Manipulation, In-place Operations]
- **LeetCode:** [48. Rotate Image, 54. Spiral Matrix, 59. Spiral Matrix II]

## Personal Notes
Elegant problem demonstrating matrix transformation decomposition. The key insight is breaking complex rotation into two simple operations. Essential pattern for understanding geometric transformations in computer graphics and matrix manipulation.

## Revision History
- **First Solve:** June 26, 2025 - Implemented transpose + reverse approach

---
**Tags:** #matrix #rotation #transpose #inplace #transformation 