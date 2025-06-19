# Search in 2D Sorted Matrix

**Difficulty:** Medium  
**Topic:** 2D Arrays, Binary Search, Matrix Search  
**Source:** AC (Apna College)

## Problem Statement

Search for a target element in a 2D matrix where:
- Each row is sorted in ascending order
- Each column is sorted in ascending order
- Return true if element is found, false otherwise
- Print the position (row, column) if element is found

## Approach & Intuition

### Staircase Search Algorithm:
The key insight is to start from either:
- **Top-right corner** (chosen in this implementation)
- **Bottom-left corner**

**Why these positions work:**
- From top-right: Can eliminate either entire row (move down) or entire column (move left)
- From bottom-left: Can eliminate either entire row (move up) or entire column (move right)

### Algorithm Steps:
1. Start from top-right corner (row = 0, col = n-1)
2. Compare current element with target:
   - If equal: Found! Return true
   - If target < current: Move left (col--)
   - If target > current: Move down (row++)
3. Continue until element found or boundaries exceeded

## Key Observations

1. **Matrix Properties**: Both row-wise and column-wise sorted
2. **Search Space Reduction**: Each comparison eliminates either a row or column
3. **Optimal Starting Points**: Top-right or bottom-left provide maximum elimination
4. **Time Efficiency**: Never need to check all elements

## Complexity Analysis

**Time Complexity:** O(m + n) - At most traverse one complete row + one complete column  
**Space Complexity:** O(1) - Only using row and column pointers

**Alternative Approaches:**
- Linear Search: O(m × n)
- Binary Search on each row: O(m × log n)

## Edge Cases Considered

1. **Element at corners:** Top-left, top-right, bottom-left, bottom-right
2. **Element not present:** Algorithm terminates when boundaries exceeded
3. **Single row/column matrix:** Degenerates to linear search
4. **Empty matrix:** Should handle gracefully (not in current implementation)

## Code Implementation

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

## Alternative Solutions

1. **Bottom-left Start:** Start from (m-1, 0) and use opposite movement logic
2. **Binary Search Approach:** Binary search on each row
3. **Divide and Conquer:** Recursively divide matrix into quadrants

## Visualization

```
Matrix:     Target = 5
1  2  3     Start: (0,2) = 3
4  5  6     3 < 5, move down
7  8  9     (1,2) = 6, 6 > 5, move left
            (1,1) = 5, Found!
```

## Cross-Reference

- **Related Problems:** Binary search, matrix traversal
- **Prerequisites:** 2D arrays, binary search concept
- **Next Steps:** Search in rotated sorted matrix, find peak element in 2D

---

*Documented on: 2024-12-19*  
*Category: Arrays (2D Matrix Search)* 