# Rotate Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given an array, rotate the array to the right by k steps, where k is non-negative. Modify the array in-place with O(1) extra memory.

## Intuition/Approach
Use three-step reversal technique: reverse entire array, then reverse first k elements, then reverse remaining elements. This achieves right rotation in-place with optimal complexity.

## Key Observations
- Right rotation by k steps = left rotation by (n-k) steps
- Use k = k % n to handle cases where k > array length
- Three reversals achieve desired rotation without extra space
- Reverse operations can be done in-place efficiently
- Handle edge cases where k = 0 or k = n

## Algorithm Steps
1. Normalize k by taking k = k % nums.length
2. Reverse entire array: reverse(nums, 0, n-1)
3. Reverse first k elements: reverse(nums, 0, k-1)
4. Reverse remaining elements: reverse(nums, k, n-1)
5. Array is now rotated right by k positions

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Three linear passes through array segments

## Edge Cases Considered
- [x] k = 0 (no rotation needed)
- [x] k = array length (full rotation, back to original)
- [x] k > array length (use modulo)
- [x] Single element array (no change)
- [x] Empty array (handle gracefully)
- [x] Two element array (simple swap)

## Solution Code

```java
// Language: Java
public static void rotate(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
}

private static void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```

## Alternative Approaches
- **Brute Force:** Rotate one step at a time, k times O(nk)
- **Extra Array:** Use additional array to store rotated elements O(n) space
- **Cyclic Replacements:** Move elements to final positions directly

## Personal Notes
This is a classic array rotation problem that demonstrates the power of the reversal technique. The key insight is that rotation can be decomposed into three simple reversal operations. This approach is optimal in both time and space complexity and is a fundamental pattern for many array manipulation problems.

---
**Tags:** #arrays #rotation #reverse #inplace #medium 