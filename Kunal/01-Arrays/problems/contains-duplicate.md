# Contains Duplicate

**Date:** 2025-01-19  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** Array/Hash Table

## Problem Statement

Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

## Intuition/Approach

**Sorting Approach:** Sort the array first, then check adjacent elements for duplicates. This approach leverages the fact that duplicate elements will be adjacent after sorting.

**Algorithm Logic:**
1. Sort the array using built-in sorting
2. Iterate through sorted array starting from index 1
3. Compare each element with its previous element
4. If any match is found, return true
5. If no matches found after complete iteration, return false

## Algorithm Steps

1. Sort the input array `nums` using `Arrays.sort()`
2. Loop from index 1 to `nums.length - 1`
3. For each index `i`, compare `nums[i]` with `nums[i-1]`
4. If `nums[i] == nums[i-1]`, return `true` (duplicate found)
5. If loop completes without finding duplicates, return `false`

## Key Observations

- Sorting brings duplicate elements together
- Only need to check adjacent elements after sorting
- Single pass through sorted array is sufficient
- No additional space needed for hash table
- Works with negative numbers and zero

## Time & Space Complexity

- **Time Complexity:** O(n log n) - dominated by sorting operation
- **Space Complexity:** O(1) - only using constant extra space (if in-place sorting)

## Edge Cases Considered

- [ ] Empty array (no duplicates)
- [ ] Single element array (no duplicates)
- [ ] All elements are the same
- [ ] No duplicates present
- [ ] Negative numbers and zero
- [ ] Array with only two elements

## Solution Code

```java
import java.util.*;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }
}
```

## Alternative Approaches

1. **Hash Set Approach:** O(n) time, O(n) space
   ```java
   Set<Integer> seen = new HashSet<>();
   for (int num : nums) {
       if (!seen.add(num)) return true;
   }
   return false;
   ```

2. **Brute Force:** O(n²) time, O(1) space - check every pair
3. **Frequency Counter:** Use HashMap to count occurrences

## Related Problems

- Two Sum
- Find All Duplicates in Array
- Contains Duplicate II & III
- Remove Duplicates from Sorted Array

**LeetCode Connection:** LeetCode #217 - Contains Duplicate 