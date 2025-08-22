# Spiral Matrix

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given an m x n matrix, return all elements of the matrix in spiral order. Spiral order starts from the top-left corner, goes to the right, then down, then left, then up, and so on.

## Intuition/Approach
Use four pointers to track the boundaries of the spiral: top, bottom, left, right. Traverse the matrix in spiral order by moving these boundaries inward after each complete cycle.

## Key Observations
- Use four boundary pointers
- Traverse right, down, left, up in sequence
- Move boundaries inward after each direction
- Handle edge cases for single row/column
- Stop when boundaries cross

## Algorithm Steps
1. Initialize top = 0, bottom = m-1, left = 0, right = n-1
2. While top <= bottom and left <= right:
   - Traverse right from left to right
   - Traverse down from top+1 to bottom
   - Traverse left from right-1 to left (if top != bottom)
   - Traverse up from bottom-1 to top+1 (if left != right)
3. Move boundaries inward
4. Return spiral order list

## Complexity Analysis
- **Time Complexity:** O(m × n)
- **Space Complexity:** O(1) excluding output
- **Justification:** Visit each element once

## Edge Cases Considered
- [x] Empty matrix - Return empty list
- [x] Single element - Return that element
- [x] Single row - Traverse right only
- [x] Single column - Traverse down only
- [x] 2x2 matrix - Handle all directions
- [x] Large matrix - Handle efficiently

## Solution Code

```java
// Language: Java
public static List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix.length == 0) return result;
    
    int top = 0, bottom = matrix.length - 1;
    int left = 0, right = matrix[0].length - 1;
    
    while (top <= bottom && left <= right) {
        // Traverse right
        for (int j = left; j <= right; j++) {
            result.add(matrix[top][j]);
        }
        top++;
        
        // Traverse down
        for (int i = top; i <= bottom; i++) {
            result.add(matrix[i][right]);
        }
        right--;
        
        // Traverse left
        if (top <= bottom) {
            for (int j = right; j >= left; j--) {
                result.add(matrix[bottom][j]);
            }
            bottom--;
        }
        
        // Traverse up
        if (left <= right) {
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
    }
    
    return result;
}
```

## Alternative Approaches
- **Recursive:** Divide matrix into layers
- **Direction array:** Use direction vectors
- **Visited array:** Mark visited elements

## Personal Notes
This is a classic matrix traversal problem that tests understanding of boundary management and direction changes. The key insight is using four boundary pointers and moving them inward after each complete cycle. This approach is clean and handles all edge cases correctly.

---
**Tags:** #arrays #matrix #spiral #traversal #medium
