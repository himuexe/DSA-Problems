# Longest Increasing Subsequence (LIS)

**Date:** 2025-01-19  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Dynamic Programming/Binary Search

## Problem Statement

Given an integer array `nums`, return the length of the longest strictly increasing subsequence. A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.

## Intuition/Approach

**Binary Search + Greedy Approach:**
This solution uses a combination of greedy strategy and binary search to achieve O(n log n) complexity:

1. **Maintain a list** that represents the smallest tail of increasing subsequence of length `i+1` at position `i`
2. **For each number:** Either extend the sequence (if larger) or replace an element to maintain the "smallest tail" property
3. **Binary search** to find the correct position for replacement

**Key Insight:** We don't need the actual subsequence, just its length. By maintaining the smallest possible tail for each length, we maximize opportunities for future extensions.

## Algorithm Steps

1. Initialize an ArrayList with the first element
2. For each subsequent element:
   - If it's larger than the last element in list → extend the sequence
   - Otherwise, find the correct position using binary search and replace
3. The size of the list gives the LIS length

## Key Observations

- Uses "patience sorting" concept
- Binary search finds the leftmost position where element can be placed
- The list doesn't represent the actual LIS, but maintains optimal state
- Greedy choice: always keep the smallest possible tail
- Each element is processed exactly once with log n binary search

## Time & Space Complexity

- **Time Complexity:** O(n log n) - n elements × log n binary search
- **Space Complexity:** O(n) - for the auxiliary list in worst case

## Edge Cases Considered

- [ ] Single element array (LIS = 1)
- [ ] All elements equal (LIS = 1)
- [ ] Strictly decreasing array (LIS = 1)
- [ ] Already sorted array (LIS = n)
- [ ] Empty array (LIS = 0)
- [ ] Negative numbers and duplicates

## Solution Code

```java
import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                // Extend the sequence
                list.add(nums[i]);
            } else {
                // Find position and replace
                int index = binarySearch(list, nums[i]);
                list.set(index, nums[i]);
            }
        }
        
        return list.size();
    }
    
    private int binarySearch(ArrayList<Integer> list, int val) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (val > list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
}
```

## Alternative Approaches

1. **Dynamic Programming:** O(n²) approach using dp[i] = LIS ending at i
2. **Recursive with Memoization:** Top-down DP approach
3. **Built-in Binary Search:** Use Collections.binarySearch()

## Why Binary Search Works

The binary search finds the leftmost position where we can place the current element, maintaining the invariant that `list[i]` contains the smallest tail of all increasing subsequences of length `i+1`.

## Related Problems

- Longest Increasing Subsequence II
- Russian Doll Envelopes
- Maximum Length of Pair Chain
- Number of Longest Increasing Subsequence

**LeetCode Connection:** LeetCode #300 - Longest Increasing Subsequence 