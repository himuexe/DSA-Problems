# Richest Customer Wealth

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i-th customer has in the j-th bank. Return the wealth that the richest customer has. A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.

## Intuition/Approach
Calculate the wealth (sum of all bank accounts) for each customer and track the maximum wealth seen so far. Use nested loops to traverse the 2D array - outer loop for customers (rows) and inner loop for accounts (columns).

## Key Observations
- 2D array where each row represents a customer and each column represents a bank account
- Need to sum all values in each row to get customer's total wealth
- Track maximum wealth across all customers
- Simple nested loop approach is optimal for this problem
- No need for complex algorithms since we must examine every element

## Algorithm Steps
1. Initialize max = Integer.MIN_VALUE
2. For each customer (row):
   - Initialize sum = 0
   - For each account (column):
     - Add account value to sum
   - If sum > max, update max
3. Return max

## Complexity Analysis
- **Time Complexity:** O(m × n) where m = customers, n = accounts per customer
- **Space Complexity:** O(1)
- **Justification:** Must examine every element in the 2D array, constant extra space for variables

## Edge Cases Considered
- [x] Single customer - Return their total wealth
- [x] Single account per customer - Return maximum account value
- [x] All customers have same wealth - Return that wealth value
- [x] Empty array - Not applicable (problem assumes valid input)
- [x] Negative account values - Handle correctly with proper initialization
- [x] Zero account values - Include in wealth calculation

## Solution Code

```java
// Language: Java
static int calcMax(int[][] arr) {
    int max = Integer.MIN_VALUE;
    
    for (int persons = 0; persons < arr.length; persons++) {
        int sum = 0;
        for (int accounts = 0; accounts < arr[persons].length; accounts++) {
            sum += arr[persons][accounts];
        }
        if (sum > max) {
            max = sum;
        }
    }
    
    return max;
}
```

## Alternative Approaches
1. **Streams API:** Use Java 8 streams for functional approach - same complexity but more concise
2. **Enhanced For Loop:** Use for-each loops - cleaner syntax, same performance
3. **Single Loop with Index:** Convert 2D to 1D indexing - more complex but same result
4. **Parallel Processing:** For very large arrays, but overkill for most cases

## Related Problems
- **AC:** 2D array sum problems, matrix manipulation
- **Kunal:** Search in 2D array, matrix traversal problems
- **LeetCode:** #1672 Richest Customer Wealth, #1380 Lucky Numbers in Matrix

## Personal Notes
- Straightforward 2D array traversal problem
- Good practice for nested loops and row-wise processing
- Foundation for more complex matrix operations
- Important to understand row-major vs column-major traversal

## Revision History
- **First Solve:** 2024-12-19 - Implemented nested loop approach, understood 2D array wealth calculation
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #2d #matrix #wealth #summation #nested-loops 