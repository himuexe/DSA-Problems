# Squares of Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an integer array sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

## Intuition/Approach
Use two pointers from both ends of array to compare absolute values. Since largest squares come from elements with largest absolute values, build result array from right to left by selecting larger absolute value.

## Key Observations
- Input array is sorted but may contain negative numbers
- Largest squares come from elements with largest absolute values
- Use two pointers to compare absolute values from both ends
- Build result array from right to left (largest to smallest squares)
- Avoid sorting after squaring by utilizing input array's sorted property

## Algorithm Steps
1. Initialize left = 0, right = length-1, result array, index = length-1
2. While left <= right:
   - Compare absolute values of nums[left] and nums[right]
   - Square the larger absolute value and place at result[index]
   - Move corresponding pointer (left++ or right--)
   - Decrement index
3. Return result array

## Complexity Analysis
- **Time Complexity:** O(n) - single pass through array with two pointers
- **Space Complexity:** O(1) - excluding output array, only constant extra space
- **Justification:** Each element processed exactly once

## Edge Cases Considered
- [x] All positive numbers (right pointer dominates)
- [x] All negative numbers (left pointer dominates)
- [x] Mixed positive and negative numbers
- [x] Array with zeros
- [x] Single element array
- [x] Array with duplicates

## Solution Code

```java
// Language: Java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] squared = new int[nums.length];
        int index = squared.length - 1;
        while(left <= right){
            int tmp = Math.max(nums[left] * nums[left], nums[right] * nums[right]);
            squared[index--] = tmp;
            if(Math.abs(nums[left]) < Math.abs(nums[right])){
                right--;
            }
            else{
                left++;
            }
        }
        return squared;
    }
}
```

## Alternative Approaches
- **Square and Sort:** Square all elements then sort O(n log n)
- **Merge Approach:** Separate positive and negative, square both, then merge
- **Single Pass with Sorting:** Square elements while traversing, then sort

## Related Problems
- **AC:** [Two Pointers, Sorted Array Operations]
- **Kunal:** [Merge Sorted Arrays, Two Sum]
- **LeetCode:** [977. Squares of a Sorted Array, 88. Merge Sorted Array]

## Personal Notes
Elegant problem demonstrating two-pointer optimization on sorted arrays. The key insight is building result from largest to smallest squares while maintaining sorted property. Shows how to avoid unnecessary sorting by leveraging existing array properties.

## Revision History
- **First Solve:** June 26, 2025 - Implemented two-pointer approach from both ends

---
**Tags:** #twopointers #sorting #arrays #squares #optimization 