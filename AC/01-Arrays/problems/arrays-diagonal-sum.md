# Matrix Diagonal Sum

**Difficulty:** Easy  
**Topic:** 2D Arrays, Matrix Operations  
**Source:** AC (Apna College)

## Problem Statement

Calculate the sum of elements on both diagonals of a square matrix. For a square matrix, find the sum of:
- Primary diagonal (top-left to bottom-right): elements where i == j
- Secondary diagonal (top-right to bottom-left): elements where i + j == n - 1

**Note:** If the matrix has odd dimensions, the center element should only be counted once (as it's common to both diagonals).

## Approach & Intuition

### Initial Approach (Brute Force):
- Iterate through all elements of the matrix
- For each element, check if it lies on primary diagonal (i == j) or secondary diagonal (i + j == n - 1)
- Add to sum if condition is met

### Optimized Approach:
- Instead of checking all elements, directly access diagonal elements
- For each row i, add arr[i][i] (primary diagonal)
- For each row i, add arr[i][n-1-i] (secondary diagonal)
- **Key Optimization:** Avoid double counting the center element in odd matrices by checking if i != n-1-i

## Key Observations

1. **Diagonal Access Pattern**: Primary diagonal elements have equal row and column indices
2. **Secondary Diagonal Formula**: For row i, column is (n-1-i)
3. **Center Element**: In odd-sized matrices, center element (n/2, n/2) appears on both diagonals
4. **Time Optimization**: Direct access eliminates need for nested loops

## Complexity Analysis

**Time Complexity:** O(n) - Single loop through rows  
**Space Complexity:** O(1) - Only using variables for sum calculation

**Previous Brute Force:** O(n²) time, O(1) space

## Edge Cases Considered

1. **Single Element Matrix (1x1):** Only one diagonal, sum equals the single element
2. **Even-sized Matrix:** No overlapping center element
3. **Odd-sized Matrix:** Center element counted only once
4. **Empty Matrix:** Should return 0 (though not handled in current implementation)

## Code Implementation

```java
public static int diagonalSum(int[][] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
        // Add primary diagonal element
        sum += arr[i][i];
        
        // Add secondary diagonal element (if different from primary)
        if (i != arr.length - i - 1) {
            sum += arr[i][arr.length - i - 1];
        }
    }
    return sum;
}
```

## Alternative Solutions

1. **Brute Force Approach:** Check all elements for diagonal conditions
2. **Two-pass Method:** Calculate primary and secondary diagonal sums separately, then adjust for center overlap

## Cross-Reference

- **Related Problems:** Matrix traversal, 2D array operations
- **Prerequisites:** Basic 2D array understanding
- **Next Steps:** Matrix rotation, transpose operations

---

*Documented on: 2024-12-19*  
*Category: Arrays (2D Matrix Operations)* 