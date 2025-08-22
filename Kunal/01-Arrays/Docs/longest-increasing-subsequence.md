# Longest Increasing Subsequence

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given an integer array nums, return the length of the longest strictly increasing subsequence. A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.

## Intuition/Approach
Use dynamic programming with binary search optimization. Maintain an array where dp[i] represents the smallest tail value of all increasing subsequences of length i+1. Use binary search to find the right position to insert each element.

## Key Observations
- Use dynamic programming with binary search
- dp[i] = smallest tail value for subsequence of length i+1
- Binary search finds insertion position efficiently
- Length of dp array gives the answer
- Each element is processed once

## Algorithm Steps
1. Initialize dp array with first element
2. For each element, find insertion position using binary search
3. If element > all tails, append to dp
4. Otherwise, replace element at insertion position
5. Return length of dp array

## Complexity Analysis
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(n)
- **Justification:** n elements, log n for binary search each, dp array storage

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element - Return 1
- [x] All elements same - Return 1
- [x] Strictly increasing - Return array length
- [x] Strictly decreasing - Return 1
- [x] Mixed pattern - Find optimal subsequence

## Solution Code

```java
// Language: Java
public static int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    
    int[] dp = new int[nums.length];
    int len = 0;
    
    for (int num : nums) {
        int i = binarySearch(dp, 0, len, num);
        dp[i] = num;
        if (i == len) {
            len++;
        }
    }
    
    return len;
}

private static int binarySearch(int[] dp, int left, int right, int target) {
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (dp[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

## Alternative Approaches
- **Standard DP:** O(n²) time, O(n) space
- **Segment Tree:** O(n log n) time, O(n) space
- **Patience Sorting:** O(n log n) time, O(n) space

## Personal Notes
This is a classic dynamic programming problem that demonstrates the power of combining DP with binary search for optimization. The key insight is maintaining the smallest tail values for each possible subsequence length, which allows us to use binary search for efficient insertion. This approach is optimal and is a great example of how to optimize DP solutions.

---
**Tags:** #arrays #dynamicprogramming #binarysearch #medium #subsequence 