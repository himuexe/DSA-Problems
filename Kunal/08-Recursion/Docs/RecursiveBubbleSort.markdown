# Recursive BubbleSort - Row-Column Approach

**Source:** Kunal Recursion | **Topic:** Sorting, Recursion | **Difficulty:** Beginner-Intermediate

---

## Problem Statement
Implement the BubbleSort algorithm using a recursive approach, treating it as a row-column problem to replace traditional nested loops.

## Intuition/Approach
The recursive approach mimics the iterative bubble sort by using two parameters (row and column) to control passes and comparisons:
- **Row Recursion:** Represents each pass of bubble sort, reducing the number of elements to compare.
- **Column Recursion:** Handles comparisons within a pass, swapping adjacent elements if needed.
- The recursion replaces nested loops, making it an educational exercise in converting iterative algorithms to recursive ones.

## Key Observations
- The row parameter tracks the number of passes, decreasing with each recursive call.
- The column parameter controls comparisons within a pass, resetting to 0 for each new row.
- The algorithm performs in-place sorting, modifying the original array.
- This is a stable sort, preserving the relative order of equal elements.
- The approach is less efficient than iterative bubble sort due to recursion overhead but is valuable for learning.

## Algorithm Steps
1. If row == 0, return (array is sorted).
2. If column < row:
   - Compare arr[column] and arr[column+1]; swap if arr[column] > arr[column+1].
   - Recursively call with column + 1.
3. If column >= row:
   - One pass is complete; recursively call with row - 1 and column = 0.

## Complexity Analysis
- **Time Complexity:** O(n²) - Each row (n passes) involves up to n comparisons.
- **Space Complexity:** O(n) - Due to the recursion stack depth for row recursion; sorting is in-place.
- **Justification:** The total number of comparisons mirrors iterative bubble sort (O(n²)), while the recursion stack adds O(n) space.

## Edge Cases Considered
- [x] Empty input.
- [x] Single element array.
- [x] Two element array.
- [x] Already sorted array.
- [x] Reverse sorted array.
- [x] Array with duplicate elements.

## Solution Code
```java
public class RecursiveBubbleSort {
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
}
```

## Alternative Approaches
- **Iterative Bubble Sort:** Uses nested loops for simplicity and slightly better performance.
- **Tail Recursion Optimization:** Convert to tail recursion to reduce stack space (though Java doesn't optimize this).
- **Other Sorting Algorithms:** MergeSort or QuickSort for better time complexity (O(n log n)).

## Personal Notes
- This recursive approach helped clarify how nested loops can be modeled as recursive calls with multiple parameters.
- The row-column analogy makes it easier to visualize the recursion process.
- While not practical for production, it’s a great exercise for understanding recursion depth and state management.

---
**Tags:** #sorting #recursion #bubble_sort