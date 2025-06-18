# Search in 2D Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a 2D array (matrix) of integers and a target value, find the target in the matrix and return its coordinates [row, col]. If the target is not found, return [-1, -1].

## Intuition/Approach
Use nested loops to traverse the 2D array row by row, column by column. This is a brute force approach that works for any 2D array regardless of whether it's sorted or not.

## Key Observations
- 2D array is essentially an array of arrays
- Need nested loops to access each element
- Row-major traversal: process all columns in a row before moving to next row
- Return coordinates as [row, col] format
- Early termination when target is found

## Algorithm Steps
1. Iterate through each row using outer loop
2. For each row, iterate through each column using inner loop
3. Compare arr[row][col] with target
4. If match found, return [row, col]
5. If all elements checked without finding target, return [-1, -1]

## Complexity Analysis
- **Time Complexity:** O(m × n) where m = rows, n = columns
- **Space Complexity:** O(1)
- **Justification:** May need to check all elements in worst case, constant extra space

## Edge Cases Considered
- [x] Empty matrix - Return [-1, -1]
- [x] Single element (target found) - Return [0, 0]
- [x] Single element (target not found) - Return [-1, -1]
- [x] Single row matrix - Works correctly
- [x] Single column matrix - Works correctly
- [x] Target at [0, 0] - Return [0, 0]
- [x] Target at last position - Return [lastRow, lastCol]
- [x] Target not in matrix - Return [-1, -1]

## Solution Code

```java
// Language: Java
static int[] searchArray(int[][] arr, int target) {
    for (int row = 0; row < arr.length; row++) {
        for (int col = 0; col < arr[row].length; col++) {
            if (arr[row][col] == target) {
                return new int[]{row, col};
            }
        }
    }
    return new int[]{-1, -1};  // Target not found
}
```

## Alternative Approaches
1. **Row-wise Binary Search:** O(m × log n) - if each row is sorted
2. **Staircase Search:** O(m + n) - if matrix is row and column sorted
3. **Flatten and Search:** O(m × n) - convert 2D to 1D and use linear search
4. **Divide and Conquer:** O(m × n) - recursive approach with same complexity

## Related Problems
- **AC:** Search in Sorted Matrix, Spiral Matrix Traversal
- **Kunal:** Binary Search in 2D, Matrix Rotation
- **LeetCode:** #74 Search 2D Matrix, #240 Search 2D Matrix II

## Personal Notes
- Introduction to 2D array manipulation
- Foundation for more complex matrix problems
- Understanding nested loops and coordinate systems
- Can be optimized significantly if matrix has special properties (sorted)

## Revision History
- **First Solve:** 2024-12-19 - Implemented nested loop approach, understood 2D array traversal
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #2darray #matrix #searching #nestedloops 