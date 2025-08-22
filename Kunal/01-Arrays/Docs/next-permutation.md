# Next Permutation

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers. If such arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

## Intuition/Approach
Find the first decreasing element from right (a[i] < a[i+1]), then find the smallest element greater than a[i] from right, swap them, and reverse the suffix starting from i+1.

## Key Observations
- Permutations are lexicographically ordered
- Find first decreasing element from right (a[i] < a[i+1])
- Find smallest element > a[i] from right
- Swap and reverse suffix to get next permutation
- If no decreasing element found, reverse entire array

## Algorithm Steps
1. Find first decreasing element from right: a[i] < a[i+1]
2. If no such element, reverse entire array and return
3. Find smallest element > a[i] from right
4. Swap a[i] with that element
5. Reverse suffix starting from i+1

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass to find decreasing element, single pass to find swap element, single pass to reverse

## Edge Cases Considered
- [x] Single element array - No change
- [x] Two element array - Simple swap
- [x] Already largest permutation - Reverse to smallest
- [x] All elements same - No change
- [x] Strictly decreasing array - Reverse to smallest

## Solution Code

```java
// Language: Java
public static void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    
    // Find first decreasing element from right
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    
    if (i >= 0) {
        // Find smallest element > nums[i] from right
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        // Swap elements
        swap(nums, i, j);
    }
    
    // Reverse suffix
    reverse(nums, i + 1);
}

private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

private static void reverse(int[] nums, int start) {
    int end = nums.length - 1;
    while (start < end) {
        swap(nums, start, end);
        start++;
        end--;
    }
}
```

## Alternative Approaches
- **Generate all permutations:** O(n!) time, find next one
- **Library function:** Use next_permutation if available
- **Recursive approach:** Generate permutations recursively

## Personal Notes
This is a classic algorithm that requires understanding of how permutations work lexicographically. The key insight is finding the rightmost decreasing element and then finding the smallest element greater than it to swap with. The suffix reversal is crucial for getting the next permutation. This pattern is useful for many permutation-related problems.

---
**Tags:** #arrays #permutation #lexicographic #medium #nextpermutation 