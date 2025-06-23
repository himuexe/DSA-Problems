# Move Zeros

**Date:** 2025-01-26  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** Array/Two Pointers

## Problem Statement

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements. The operation must be done **in-place** without making a copy of the array.

## Intuition/Approach

**Two-Pointer Approach:** Use two pointers - `left` pointer to track position for next non-zero element, and `right` pointer to iterate through array. When a non-zero element is found, place it at the `left` position and advance `left`. Finally, fill remaining positions with zeros.

**Algorithm Logic:**
1. Use `left` pointer to track where next non-zero element should be placed
2. Use `right` pointer to iterate through the entire array
3. When `nums[right] != 0`, copy it to `nums[left]` and increment `left`
4. After processing all elements, fill positions from `left` to end with zeros
5. This maintains relative order of non-zero elements

## Algorithm Steps

1. Handle edge case: if array length is 1, return immediately
2. Initialize `left = 0` to track position for next non-zero element
3. Loop with `right` from 0 to `nums.length - 1`:
   - If `nums[right] != 0`:
     - Set `nums[left] = nums[right]`
     - Increment `left++`
4. Fill remaining positions with zeros:
   - Loop from `left` to `nums.length - 1`
   - Set `nums[i] = 0`

## Key Observations

- Two-pointer technique maintains relative order of non-zero elements
- In-place algorithm - no extra space needed for new array
- First pass collects all non-zero elements in order
- Second pass fills remaining positions with zeros
- Early termination for single-element arrays
- Time-optimal solution with minimal passes

## Time & Space Complexity

- **Time Complexity:** O(n) - two passes through the array at most
- **Space Complexity:** O(1) - only using constant extra space (in-place)

## Edge Cases Considered

- [ ] Single element array (handled with early return)
- [ ] Array with no zeros (all elements copied, no zeros to fill)
- [ ] Array with all zeros (left stays at 0, entire array filled with zeros)
- [ ] Array with zeros at beginning, middle, or end
- [ ] Empty array (edge case, though not explicitly handled)
- [ ] Mixed positive/negative numbers with zeros

## Solution Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums.length == 1) return;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
        }
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

## Alternative Approaches

1. **Swap-based Approach:** Swap non-zero elements with zero elements
   ```java
   // Track next zero position and swap when non-zero found
   ```

2. **Additional Array:** Create new array, copy non-zeros, then zeros (O(n) space)
3. **Bubble Sort Style:** Multiple passes to bubble zeros to end (O(n²) time)

## Related Problems

- Remove Element
- Remove Duplicates from Sorted Array
- Move Zeros II (with minimum swaps)
- Partition Array
- Sort Colors (Dutch National Flag)

**LeetCode Connection:** LeetCode #283 - Move Zeros 