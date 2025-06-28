# Recursive BubbleSort - Row-Column Approach

## Problem Statement
Implement BubbleSort algorithm using recursion with a row-column approach instead of traditional nested loops.

## Intuition/Approach
**Recursive Bubble Pattern:**
1. **Row-Column Recursion:** Treat sorting as a 2D problem with row (passes) and column (comparisons)
2. **Base Case:** When row becomes 0, array is sorted
3. **Column Recursion:** For each row, recursively handle comparisons
4. **Row Recursion:** After completing column comparisons, move to next row

**Key Insight:** Replace nested loops with nested recursive calls for educational purposes.

## Key Observations
- **Two-Dimensional Recursion:** Row and column parameters control recursion depth
- **Educational Pattern:** Demonstrates how loops can be converted to recursion
- **In-Place Sorting:** Modifies original array without extra space
- **Stable Sort:** Adjacent swaps preserve relative order of equal elements
- **Inefficient Complexity:** O(n²) time due to nature of bubble sort

## Algorithm Steps
1. **Base Case Check:** If row == 0, return (array sorted)
2. **Column Recursion:** If column < row:
   - Compare adjacent elements arr[c] and arr[c+1]
   - Swap if arr[c] > arr[c+1] (bubble larger element right)
   - Recursively call with column + 1
3. **Row Recursion:** If column >= row:
   - One pass complete, move to next row
   - Recursively call with row - 1 and column = 0

## Time & Space Complexity
- **Time Complexity:** O(n²)
  - Outer recursion (rows): O(n) calls
  - Inner recursion (columns): O(n) calls per row
  - Total comparisons: O(n²)
- **Space Complexity:** O(n)
  - Recursion stack depth: O(n) for row recursion
  - Additional space: O(1) in-place sorting

## Edge Cases Considered
- [x] Single element array
- [x] Two element array
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with duplicates
- [x] Empty array

## Code Implementation
```java
static void bubble(int[] arr, int r, int c) {
    if (r == 0) {
        return;  // Base case: all passes complete
    }
    
    if (c < r) {
        // Column recursion: handle comparisons in current row
        if (arr[c] > arr[c + 1]) {
            // Swap adjacent elements
            int temp = arr[c];
            arr[c] = arr[c + 1];
            arr[c + 1] = temp;
        }
        bubble(arr, r, c + 1);  // Move to next column
    } else {
        // Row recursion: move to next pass
        bubble(arr, r - 1, 0);  // Next row, reset column to 0
    }
}

// Usage: bubble(arr, arr.length - 1, 0)
```

## Example Walkthrough
**Input:** [4, 3, 2, 1]
**Initial Call:** bubble(arr, 3, 0)

**Pass 1 (r=3):**
- Compare 4,3 → swap → [3,4,2,1]
- Compare 4,2 → swap → [3,2,4,1]  
- Compare 4,1 → swap → [3,2,1,4]

**Pass 2 (r=2):**
- Compare 3,2 → swap → [2,3,1,4]
- Compare 3,1 → swap → [2,1,3,4]

**Pass 3 (r=1):**
- Compare 2,1 → swap → [1,2,3,4]

**Final Result:** [1, 2, 3, 4]

## Key Learning Points
- **Recursion as Loop Replacement:** Shows how nested loops can become nested recursion
- **Two-Parameter Recursion:** Row and column parameters manage state
- **Educational Value:** Helps understand recursion patterns beyond simple problems
- **Base Case Design:** Multiple conditions determine when recursion stops

## Applications
- **Educational Tool:** Teaching recursion concepts with familiar algorithm
- **Recursion Practice:** Understanding multi-parameter recursive functions
- **Algorithm Conversion:** Learning to convert iterative to recursive patterns
- **Interview Preparation:** Demonstrating recursion understanding

---
**Date:** June 27, 2025  
**Topic:** Sorting Algorithms & Recursion  
**Difficulty:** Beginner-Intermediate  
**Category:** Recursive Algorithm Conversion 