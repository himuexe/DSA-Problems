# Rotate Array

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Array Manipulation

## Problem Statement

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

**Example:**
```
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation: 
- rotate 1 steps to the right: [7,1,2,3,4,5,6]
- rotate 2 steps to the right: [6,7,1,2,3,4,5]  
- rotate 3 steps to the right: [5,6,7,1,2,3,4]

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
```

## Intuition/Approach

**Three-Step Reversal Algorithm:**
- **Key Insight:** Rotation can be achieved through three strategic reversals
- **Step 1:** Reverse the entire array
- **Step 2:** Reverse the first k elements
- **Step 3:** Reverse the remaining elements

**Mathematical Basis:** 
For array `[1,2,3,4,5,6,7]` with k=3:
1. Reverse all: `[7,6,5,4,3,2,1]`
2. Reverse first k=3: `[5,6,7,4,3,2,1]`
3. Reverse remaining: `[5,6,7,1,2,3,4]` ✓

## Algorithm Steps

1. **Normalize k:** `k = k % nums.length` (handle k > array length)
2. **Reverse entire array:** `reverse(nums, 0, nums.length-1)`
3. **Reverse first k elements:** `reverse(nums, 0, k-1)`
4. **Reverse remaining elements:** `reverse(nums, k, nums.length-1)`

## Key Observations

- **In-place rotation:** No extra space needed for rotation
- **Modular arithmetic:** Handle cases where k > array length
- **Three reversals:** Mathematical equivalence to k right rotations
- **Constant space:** O(1) extra space complexity
- **Any k value:** Works for all non-negative k values

## Time & Space Complexity

- **Time Complexity:** O(n) - Three passes through array (each reverse is O(n))
- **Space Complexity:** O(1) - Only constant extra space for swapping

## Edge Cases Considered

- [x] k = 0 (no rotation needed)
- [x] k = array length (equivalent to no rotation)
- [x] k > array length (use modular arithmetic)
- [x] Single element array (no change)
- [x] Two element array with k = 1

## Solution Code

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;  // Handle k > array length
        
        // Three-step reversal process
        reverse(nums, 0, nums.length - 1);  // Reverse entire array
        reverse(nums, 0, k - 1);            // Reverse first k elements
        reverse(nums, k, nums.length - 1);  // Reverse remaining elements
    }
    
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

## Algorithm Visualization

### Example: nums = [1,2,3,4,5,6,7], k = 3

**Step 1: Reverse entire array**
```
Original: [1,2,3,4,5,6,7]
Reversed: [7,6,5,4,3,2,1]
```

**Step 2: Reverse first k=3 elements**
```
Before: [7,6,5,4,3,2,1]
After:  [5,6,7,4,3,2,1]
        ↑---↑ (reversed)
```

**Step 3: Reverse remaining elements**
```
Before: [5,6,7,4,3,2,1]
After:  [5,6,7,1,2,3,4]
              ↑-----↑ (reversed)
```

## Alternative Approaches

1. **Extra Array Approach:**
```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int[] rotated = new int[n];
    
    for(int i = 0; i < n; i++) {
        rotated[(i + k) % n] = nums[i];
    }
    
    System.arraycopy(rotated, 0, nums, 0, n);
}
```
- **Time:** O(n), **Space:** O(n)

2. **Cyclic Replacement:**
```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int count = 0;
    
    for(int start = 0; count < n; start++) {
        int current = start;
        int prev = nums[start];
        
        do {
            int next = (current + k) % n;
            int temp = nums[next];
            nums[next] = prev;
            prev = temp;
            current = next;
            count++;
        } while(start != current);
    }
}
```
- **Time:** O(n), **Space:** O(1)

3. **Multiple Reversals (k times):**
```java
public void rotate(int[] nums, int k) {
    k = k % nums.length;
    for(int i = 0; i < k; i++) {
        rotateByOne(nums);
    }
}

private void rotateByOne(int[] nums) {
    int temp = nums[nums.length - 1];
    for(int i = nums.length - 1; i > 0; i--) {
        nums[i] = nums[i - 1];
    }
    nums[0] = temp;
}
```
- **Time:** O(k*n), **Space:** O(1)

## Mathematical Proof

### Why Three Reversals Work:
For array A = [a₁, a₂, ..., aₙ] and rotation by k positions:

1. **Reverse all:** A' = [aₙ, aₙ₋₁, ..., a₁]
2. **Reverse first k:** A'' = [aₙ₋ₖ₊₁, aₙ₋ₖ₊₂, ..., aₙ, aₙ₋₁, ..., a₁]
3. **Reverse remaining:** A''' = [aₙ₋ₖ₊₁, aₙ₋ₖ₊₂, ..., aₙ, a₁, a₂, ..., aₙ₋ₖ]

Result: Elements aₙ₋ₖ₊₁ to aₙ move to front, elements a₁ to aₙ₋ₖ move to back = Right rotation by k!

## Performance Comparison

| Approach | Time | Space | In-place | Readability |
|----------|------|-------|----------|-------------|
| **Three Reversals** | O(n) | O(1) | ✓ | Medium |
| **Extra Array** | O(n) | O(n) | ✗ | High |
| **Cyclic Replacement** | O(n) | O(1) | ✓ | Low |
| **k Rotations** | O(k*n) | O(1) | ✓ | High |

## Key Learnings

1. **Reversal technique:** Powerful pattern for array rearrangement
2. **Modular arithmetic:** Essential for handling edge cases
3. **In-place algorithms:** Achieve O(1) space with clever techniques
4. **Multiple approaches:** Same problem, different trade-offs
5. **Mathematical insight:** Rotation as composition of reversals

## Related Problems

- Rotate String
- Reverse Words in a String
- Rotate List
- Rotate Image
- Reverse Pairs

**LeetCode Connection:** LeetCode #189 - Rotate Array 