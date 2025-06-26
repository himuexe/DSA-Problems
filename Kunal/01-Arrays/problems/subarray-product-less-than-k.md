# Subarray Product Less Than K

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of positive integers and an integer k, count the number of contiguous subarrays where the product of all elements is strictly less than k.

## Intuition/Approach
Use sliding window technique with two pointers. Expand right pointer to include new elements, shrink left pointer when product becomes ≥ k. For each valid window, add (right - left + 1) subarrays ending at right.

## Key Observations
- All elements are positive, so product only increases when adding elements
- Use sliding window: expand right, shrink left when needed
- For window [left, right], there are (right - left + 1) subarrays ending at right
- Shrink window from left when product ≥ k
- Each position contributes multiple subarrays to the count

## Algorithm Steps
1. Initialize left = 0, count = 0, prod = 1
2. For each right pointer position:
   - Multiply product by nums[right]
   - While product ≥ k and left ≤ right:
     - Divide product by nums[left]
     - Increment left pointer
   - Add (right - left + 1) to count (subarrays ending at right)
3. Return total count

## Complexity Analysis
- **Time Complexity:** O(n) - each element visited by both pointers at most once
- **Space Complexity:** O(1) - only using constant extra variables
- **Justification:** Amortized linear time due to sliding window property

## Edge Cases Considered
- [x] k = 0 or k = 1 (no valid subarrays)
- [x] All elements ≥ k (no valid subarrays)
- [x] All elements < k (all subarrays valid)
- [x] Single element array
- [x] Product overflow (assumes int range sufficient)
- [x] Array with 1s (doesn't affect product)

## Solution Code

```java
// Language: Java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int prod = 1;
        for(int right = 0; right < nums.length; right++){
            prod *= nums[right];
            while(left <= right && prod >= k){
                prod /= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }
}
```

## Alternative Approaches
- **Brute Force:** Check all subarrays O(n²) or O(n³)
- **Binary Search:** Use binary search with prefix products
- **Logarithmic Transform:** Convert to sum of logs to avoid overflow

## Related Problems
- **AC:** [Sliding Window Problems, Subarray Counting]
- **Kunal:** [Maximum Subarray, Two Pointers]
- **LeetCode:** [713. Subarray Product Less Than K, 209. Minimum Size Subarray Sum]

## Personal Notes
Excellent sliding window problem with counting twist. The key insight is that for each valid window ending at position i, there are (i - left + 1) valid subarrays. Demonstrates advanced sliding window technique beyond simple min/max problems.

## Revision History
- **First Solve:** June 26, 2025 - Implemented sliding window with subarray counting

---
**Tags:** #slidingwindow #twopointers #product #counting #subarrays 