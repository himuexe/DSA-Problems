# Subarray Product Less Than K

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given an array of positive integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

## Intuition/Approach
Use sliding window technique with two pointers. Expand the window by moving the right pointer and shrink it by moving the left pointer when the product becomes >= k. Count all valid subarrays ending at each right position.

## Key Observations
- All elements are positive, so product increases as window expands
- Use sliding window to maintain product < k
- Count subarrays ending at each right position
- Shrink window from left when product >= k

## Algorithm Steps
1. Initialize left = 0, product = 1, count = 0
2. Iterate with right pointer from 0 to n-1
3. Multiply product by nums[right]
4. While product >= k, divide by nums[left] and increment left
5. Add (right - left + 1) to count (all subarrays ending at right)
6. Return total count

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Each element visited at most twice (once by right, once by left)

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element < k - Return 1
- [x] Single element >= k - Return 0
- [x] All elements < k - Count all possible subarrays
- [x] All elements >= k - Return 0
- [x] k = 1 - No subarrays possible (all products >= 1)

## Solution Code

```java
// Language: Java
public static int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) return 0;
    
    int left = 0, product = 1, count = 0;
    for (int right = 0; right < nums.length; right++) {
        product *= nums[right];
        while (product >= k) {
            product /= nums[left];
            left++;
        }
        count += right - left + 1;
    }
    return count;
}
```

## Alternative Approaches
- **Brute Force:** Check all possible subarrays O(n²)
- **Binary Search:** For each starting position, find valid ending O(n log n)
- **Prefix Product:** Use prefix product array with binary search

## Personal Notes
Classic sliding window problem. The key insight is that since all elements are positive, the product is monotonic - it only increases as we expand the window. This allows us to use the sliding window technique efficiently. The counting trick (right - left + 1) is crucial for getting all valid subarrays ending at each position.

---
**Tags:** #arrays #slidingwindow #twopointers #product #medium 