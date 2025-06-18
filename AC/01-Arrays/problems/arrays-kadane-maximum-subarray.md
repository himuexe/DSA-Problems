# Maximum Subarray Sum (Kadane's Algorithm)

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an integer array, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum. This is a classic dynamic programming problem that can be efficiently solved using Kadane's algorithm.

## Intuition/Approach
Use Kadane's algorithm which maintains two variables: `maxCurrent` (maximum sum ending at current position) and `maxGlobal` (maximum sum found so far). At each position, decide whether to extend the existing subarray or start a new one.

## Key Observations
- At each element, we have two choices: start a new subarray or extend the current one
- We extend the current subarray only if it contributes positively to the sum
- Keep track of the maximum sum seen so far globally
- This is a classic example of dynamic programming optimization

## Algorithm Steps
1. Initialize `maxCurrent` and `maxGlobal` with the first element
2. Iterate through the array starting from the second element
3. For each element, calculate `maxCurrent = max(current_element, maxCurrent + current_element)`
4. Update `maxGlobal = max(maxGlobal, maxCurrent)`
5. Return `maxGlobal`

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through the array with constant extra space

## Edge Cases Considered
- [x] Single element array - Returns that element
- [x] All negative numbers - Returns the least negative number
- [x] All positive numbers - Returns sum of all elements
- [x] Mixed positive and negative - Finds optimal subarray
- [x] Empty array - Not applicable (problem states at least one element)

## Solution Code

```java
// Language: Java
public static int kadane(int[] arr) {
    int maxCurrent = arr[0];
    int maxGlobal = arr[0];
    
    for (int i = 1; i < arr.length; i++) {
        // Choose to either start new subarray or extend current one
        maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
        // Update global maximum
        maxGlobal = Math.max(maxGlobal, maxCurrent);
    }
    
    return maxGlobal;
}
```

## Alternative Approaches
1. **Brute Force:** O(n³) - check all possible subarrays
2. **Optimized Brute Force:** O(n²) - use cumulative sums
3. **Divide and Conquer:** O(n log n) - recursively find max in left, right, and crossing
4. **Kadane's Algorithm:** O(n) - optimal dynamic programming solution

## Related Problems
- **AC:** Maximum Product Subarray, Maximum Sum Circular Subarray
- **Kunal:** Similar subarray problems
- **LeetCode:** #53 Maximum Subarray, #152 Maximum Product Subarray

## Personal Notes
- This is a fundamental DP problem that demonstrates the optimization principle
- The key insight is that at each position we make a local optimal choice
- Useful pattern for many other array optimization problems
- Important to understand both the algorithm and the underlying DP concept

## Revision History
- **First Solve:** 2024-12-19 - Implemented using Kadane's algorithm, understood DP pattern
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #dynamicprogramming #kadane #subarray #optimization 