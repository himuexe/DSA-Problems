# Spiral Matrix Traversal

**Difficulty:** Medium  
**Topic:** 2D Arrays, Matrix Traversal, Pattern Recognition  
**Source:** AC (Apna College)

## Problem Statement

Given a 2D matrix, traverse it in spiral order (clockwise) and return the elements as a list.

**Spiral Order:** Start from top-left, move right → down → left → up, then repeat for inner layers.

**Example:**
```
Input:  [1, 2, 3]
        [4, 5, 6]
        [7, 8, 9]

Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
```

## Approach & Intuition

### Layer-by-Layer Traversal:
The matrix can be visualized as concentric rectangular layers. Process each layer from outside to inside.

### Four-Direction Pattern:
For each layer, traverse in this order:
1. **Right:** Top row from left to right
2. **Down:** Right column from top to bottom
3. **Left:** Bottom row from right to left
4. **Up:** Left column from bottom to top

### Boundary Management:
- Track four boundaries: firstRow, lastRow, firstCol, lastCol
- After each direction, shrink the corresponding boundary
- Continue until boundaries overlap

## Key Observations

1. **Boundary Shrinking:** After each direction traversal, corresponding boundary moves inward
2. **Condition Checks:** Before left and up traversals, verify boundaries haven't crossed
3. **Edge Cases:** Single row/column matrices require special handling
4. **Direction Order:** Right → Down → Left → Up pattern repeats

## Complexity Analysis

**Time Complexity:** O(m × n) - Visit each element exactly once  
**Space Complexity:** O(m × n) - For storing result list (O(1) excluding result)

## Edge Cases Considered

1. **Empty Matrix:** Return empty list
2. **Single Element:** Return list with single element
3. **Single Row:** Only left-to-right traversal
4. **Single Column:** Only top-to-bottom traversal
5. **Rectangular Matrix:** Different row and column counts

## Code Implementation

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

## Algorithm Walkthrough

**3x3 Matrix Example:**
```
Layer 1: [1,2,3] → [6,9] → [8,7] → [4] = [1,2,3,6,9,8,7,4]
Layer 2: [5] = [5]
Final: [1,2,3,6,9,8,7,4,5]
```

## Alternative Solutions

1. **Direction Vector Approach:** Use direction arrays and change direction on boundary hit
2. **Recursive Approach:** Recursively process outer layer then inner matrix
3. **State Machine:** Track current direction state and switch on boundaries

## Cross-Reference

- **Related Problems:** Matrix rotation, diagonal traversal
- **Prerequisites:** 2D arrays, boundary management
- **Next Steps:** Anti-spiral traversal, matrix layer manipulation

---

*Documented on: 2024-12-19*  
*Category: Arrays (2D Matrix Traversal)* 