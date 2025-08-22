# Squares of a Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

## Intuition/Approach
Use two-pointer technique starting from both ends. Since the array is sorted, the largest squares will be at the ends. Compare absolute values and place the larger square at the end of the result array.

## Key Observations
- Array is sorted, so largest absolute values are at the ends
- Use two pointers from both ends
- Compare absolute values to find larger squares
- Place larger squares at the end of result array
- Handle negative numbers correctly

## Algorithm Steps
1. Initialize left = 0, right = n-1, result array of size n
2. Compare absolute values of nums[left] and nums[right]
3. Place larger square at end of result array
4. Move pointer inward (left++ or right--)
5. Fill result array from right to left

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass through array, need result array for output

## Edge Cases Considered
- [x] Empty array - Return empty array
- [x] Single element - Return single squared element
- [x] All positive numbers - Simple squaring
- [x] All negative numbers - Reverse order after squaring
- [x] Mixed positive/negative - Handle correctly with two pointers
- [x] Contains zeros - Zeros stay in middle

## Solution Code

```java
// Language: Java
public static int[] sortedSquares(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int left = 0, right = n - 1;
    int index = n - 1;
    
    while (left <= right) {
        int leftSquare = nums[left] * nums[left];
        int rightSquare = nums[right] * nums[right];
        
        if (leftSquare > rightSquare) {
            result[index--] = leftSquare;
            left++;
        } else {
            result[index--] = rightSquare;
            right--;
        }
    }
    
    return result;
}
```

## Alternative Approaches
- **Sort after squaring:** Square all elements then sort O(n log n)
- **Merge sort style:** Split into positive and negative, merge squares
- **In-place:** Square in place then sort

## Personal Notes
This is an elegant two-pointer problem that demonstrates how to handle sorted arrays with mixed positive and negative numbers. The key insight is that the largest squares will be at the ends of the array, so we can use two pointers to efficiently build the result array from largest to smallest. This approach is optimal in both time and space complexity.

---
**Tags:** #arrays #twopointers #sorting #easy #squares 