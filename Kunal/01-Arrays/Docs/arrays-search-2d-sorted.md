# Search a 2D Matrix

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties: Integers in each row are sorted from left to right. The first integer of each row is greater than the last integer of the previous row.

## Intuition/Approach
Use binary search on the flattened matrix. Since the matrix is sorted row-wise and each row's first element is greater than the previous row's last element, we can treat it as a single sorted array and apply binary search.

## Key Observations
- Matrix is sorted row-wise
- First element of each row > last element of previous row
- Can treat as single sorted array
- Use binary search for O(log(mn)) time
- Convert 1D index to 2D coordinates

## Algorithm Steps
1. Treat matrix as sorted array of length m*n
2. Use binary search with left = 0, right = m*n - 1
3. Convert mid index to row and column: row = mid/n, col = mid%n
4. Compare matrix[row][col] with target
5. Adjust search range accordingly

## Complexity Analysis
- **Time Complexity:** O(log(mn))
- **Space Complexity:** O(1)
- **Justification:** Binary search on flattened matrix

## Edge Cases Considered
- [x] Empty matrix - Return false
- [x] Single element - Check if equals target
- [x] Single row - Binary search on row
- [x] Single column - Binary search on column
- [x] Target not found - Return false
- [x] Target found - Return true

## Solution Code

```java
// Language: Java
public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0) return false;
    
    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0;
    int right = m * n - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int row = mid / n;
        int col = mid % n;
        
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
}
```

## Alternative Approaches
- **Two binary searches:** First find row, then find column
- **Linear search:** O(mn) time, simple but inefficient
- **Staircase search:** Start from top-right corner

## Personal Notes
This is a clever problem that demonstrates how to apply 1D algorithms to 2D data structures. The key insight is recognizing that the matrix properties allow us to treat it as a single sorted array. The index conversion (row = mid/n, col = mid%n) is a common pattern in 2D array problems.

---
**Tags:** #arrays #2darray #binarysearch #medium #matrix
