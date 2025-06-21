# Two Sum (HashMap Approach)

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Find two numbers in an array that add up to a specific target. Return the indices of the two numbers. Each input has exactly one solution.

## Intuition/Approach
Use HashMap to store each number and its index as we iterate. For each element, check if its complement (target - current) exists in the map. This allows single-pass solution.

## Key Observations
- HashMap provides O(1) lookup time
- For each number x, we need to find (target - x)
- Store numbers as we encounter them to avoid using same element twice
- Return indices when complement is found

## Algorithm Steps
1. Create empty HashMap to store number → index mapping
2. Iterate through array:
   - Calculate complement = target - current number
   - If complement exists in map, return [map.get(complement), current_index]
   - Otherwise, store current number and index in map

## Complexity Analysis
- **Time Complexity:** O(n) where n is array length
- **Space Complexity:** O(n) for HashMap storage
- **Justification:** Single pass through array, constant time HashMap operations

## Edge Cases Considered
- [x] Exactly one solution exists (problem guarantee)
- [x] Numbers can be negative
- [x] Target can be negative or zero
- [x] Array contains duplicates
- [x] Same number used twice (prevented by algorithm)

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
       Map<Integer,Integer> map = new HashMap<>();
       for(int i=0;i<nums.length;i++){
        int complement = target-nums[i];
        if(map.containsKey(complement)){
            return new int[]{map.get(complement),i};
        }
        else{
            map.put(nums[i],i);
        }
       }
       return new int[]{-1,-1};
    }
}
```

## Alternative Approaches
- **Brute Force:** Nested loops O(n²) time, O(1) space
- **Sort + Two Pointers:** O(n log n) time, but loses original indices
- **Set Approach:** Similar to HashMap but only tracks existence

## Related Problems
- **AC:** [TwoSum2.java - sorted array variant with two pointers]
- **Kunal:** [Array problems with pair sum variants]
- **LeetCode:** [Two Sum - Problem 1 (this exact problem)]

## Personal Notes
Classic HashMap optimization problem. Demonstrates space-time tradeoff where additional O(n) space reduces time from O(n²) to O(n). Pattern useful for many "find pair/complement" problems. Essential interview question.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Arrays implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #arrays #hashMap #twoSum #optimization #interview 