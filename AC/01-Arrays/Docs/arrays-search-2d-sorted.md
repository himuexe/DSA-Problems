# Search in 2D Sorted Matrix

**Source:** AC | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Search for a target element in a 2D matrix where:
- Each row is sorted in ascending order
- Each column is sorted in ascending order
Return true if element is found, false otherwise. Print the position (row, column) if element is found.

## Intuition/Approach
Start from the top-right corner. If the current element is greater than the target, move left. If it is less, move down. This eliminates either a row or a column at each step.

## Key Observations
- Matrix is sorted both row-wise and column-wise
- Each comparison eliminates a row or a column
- Top-right or bottom-left are optimal starting points
- Never need to check all elements

## Algorithm Steps
1. Start from top-right corner (row = 0, col = n-1)
2. Compare current element with target:
   - If equal: Found! Return true
   - If target < current: Move left (col--)
   - If target > current: Move down (row++)
3. Continue until element found or boundaries exceeded

## Complexity Analysis
- **Time Complexity:** O(m + n) - At most traverse one complete row + one complete column
- **Space Complexity:** O(1) - Only using row and column pointers
- **Justification:** Each step eliminates a row or column

## Edge Cases Considered
- [x] Element at corners: Top-left, top-right, bottom-left, bottom-right
- [x] Element not present: Algorithm terminates when boundaries exceeded
- [x] Single row/column matrix: Degenerates to linear search
- [x] Empty matrix: Should handle gracefully

## Solution Code
```java
public static boolean search(int[][] arr, int key) {
    int row = 0;
    int col = arr[0].length - 1;  // Start from top-right
    while (row < arr.length && col >= 0) {
        if (arr[row][col] == key) {
            System.out.println("Key found at index: " + row + " " + col);
            return true;
        } else if (key < arr[row][col]) {
            col--;  // Move left
        } else {
            row++;  // Move down
        }
    }
    return false;
}
```

## Alternative Approaches
- Linear Search: O(m × n)
- Binary Search on each row: O(m × log n)
- Divide and Conquer: Recursively divide matrix into quadrants

## Personal Notes
Classic matrix search problem. The staircase search is optimal for sorted 2D matrices. Understanding the elimination of search space is key for efficient solutions.

---
**Tags:** #arrays #matrix #2Dsearch #binarysearch #staircasesearch 