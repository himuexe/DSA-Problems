# Spiral Matrix Traversal

**Source:** AC | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given a 2D matrix, traverse it in spiral order (clockwise) and return the elements as a list.

## Intuition/Approach
The matrix can be visualized as concentric rectangular layers. Process each layer from outside to inside, traversing in the order: right, down, left, up. Track four boundaries and shrink them after each direction.

## Key Observations
- Boundary shrinking after each direction
- Condition checks before left and up traversals
- Single row/column matrices require special handling
- Direction order: right → down → left → up

## Algorithm Steps
1. Track four boundaries: firstRow, lastRow, firstCol, lastCol
2. While boundaries do not overlap:
   - Traverse right (top row)
   - Traverse down (right column)
   - Traverse left (bottom row, if exists)
   - Traverse up (left column, if exists)
   - Shrink boundaries after each direction

## Complexity Analysis
- **Time Complexity:** O(m × n) - Visit each element exactly once
- **Space Complexity:** O(m × n) for result list (O(1) excluding result)
- **Justification:** Each element is visited once

## Edge Cases Considered
- [x] Empty matrix
- [x] Single element
- [x] Single row
- [x] Single column
- [x] Rectangular matrix

## Solution Code
```java
public static List<Integer> spiralOrder(int[][] arr) {
    List<Integer> list = new ArrayList<>();
    if (arr == null || arr.length == 0) return list;
    int firstRow = 0, firstCol = 0;
    int lastRow = arr.length - 1;
    int lastCol = arr[0].length - 1;
    while (firstRow <= lastRow && firstCol <= lastCol) {
        // Traverse right
        for (int i = firstCol; i <= lastCol; i++) {
            list.add(arr[firstRow][i]);
        }
        firstRow++;
        // Traverse down
        for (int i = firstRow; i <= lastRow; i++) {
            list.add(arr[i][lastCol]);
        }
        lastCol--;
        // Traverse left (if row exists)
        if (firstRow <= lastRow) {
            for (int i = lastCol; i >= firstCol; i--) {
                list.add(arr[lastRow][i]);
            }
            lastRow--;
        }
        // Traverse up (if column exists)
        if (firstCol <= lastCol) {
            for (int i = lastRow; i >= firstRow; i--) {
                list.add(arr[i][firstCol]);
            }
            firstCol++;
        }
    }
    return list;
}
```

## Alternative Approaches
- Direction vector approach: Use direction arrays and change direction on boundary hit
- Recursive approach: Recursively process outer layer then inner matrix
- State machine: Track current direction state and switch on boundaries

## Personal Notes
Classic matrix traversal problem. Understanding boundary management and direction order is key. The spiral pattern is a common interview question for 2D arrays.

---
**Tags:** #arrays #matrix #spiral #traversal #2Darrays 