# Three Sum

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] = 0.

## Intuition/Approach
Sort the array first, then use three pointers approach. Fix the first element and use two pointers to find pairs that sum to the negative of the first element. Skip duplicates to ensure unique triplets.

## Key Observations
- Sort array to enable two-pointer technique
- Fix first element, find two others that sum to its negative
- Skip duplicate values to avoid duplicate triplets
- Use two pointers (left, right) to efficiently find target sum
- Early termination when first element becomes positive

## Algorithm Steps
1. Sort the input array
2. For each element at index i (up to length-3):
   - Skip duplicates by checking if nums[i] == nums[i-1]
   - Set target = -nums[i], left = i+1, right = length-1
   - Use two pointers to find pairs summing to target:
     - If sum equals target: add triplet, skip duplicates, move both pointers
     - If sum < target: move left pointer right
     - If sum > target: move right pointer left
3. Return all found triplets

## Complexity Analysis
- **Time Complexity:** O(n²) - O(n log n) sorting + O(n²) two-pointer search
- **Space Complexity:** O(1) - excluding output space, only constant extra space
- **Justification:** Nested loops with two-pointer optimization

## Edge Cases Considered
- [x] Array length < 3 (impossible to form triplet)
- [x] All positive numbers (no zero sum possible)
- [x] All negative numbers (no zero sum possible)
- [x] Array with zeros
- [x] Duplicate values in array
- [x] Multiple valid triplets

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = nums.length - 1;
                int target = 0 - nums[i];
                while (left < right) {  
                    if (nums[left] + nums[right] == target) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[left]);
                        triplet.add(nums[right]);
                        triplets.add(triplet);
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;  
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return triplets;
    }
}
```

## Alternative Approaches
- **HashMap:** Use HashMap to store complements, O(n²) time but O(n) space
- **Brute Force:** Check all triplets with three nested loops O(n³)
- **Set-based:** Use HashSet to avoid duplicates with different implementation

## Related Problems
- **AC:** [Two Sum, Four Sum, Array Triplets]
- **Kunal:** [K-diff Pairs, Two Sum Variations, Array Combinations]
- **LeetCode:** [15. 3Sum, 16. 3Sum Closest, 18. 4Sum]

## Personal Notes
Classic problem demonstrating sorted array + two-pointer technique. The key challenges are handling duplicates correctly and optimizing the search. Essential pattern for understanding multi-pointer problems and duplicate handling in array algorithms.

## Revision History
- **First Solve:** June 26, 2025 - Implemented sorted array with two-pointer approach

---
**Tags:** #twopointers #sorting #triplets #arrays #duplicates 