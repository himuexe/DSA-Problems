# K-diff Pairs in Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of integers and an integer k, find the number of unique k-diff pairs in the array. A k-diff pair is defined as (nums[i], nums[j]) where i != j and |nums[i] - nums[j]| = k.

## Intuition/Approach
Use HashMap to count frequencies of all elements, then for each unique element, check if (element + k) exists in the map. Handle special case where k = 0 (need frequency ≥ 2).

## Key Observations
- Need to find pairs with absolute difference exactly k
- Use frequency map to avoid duplicate counting
- Special case: k = 0 requires same element appearing at least twice
- For k > 0, check if (current_element + k) exists in map
- Only count each unique pair once

## Algorithm Steps
1. Create HashMap to store frequency of each element
2. Initialize count = 0
3. For each unique element in the map:
   - If k = 0: check if frequency ≥ 2, increment count if true
   - If k > 0: check if (element + k) exists in map, increment count if true
4. Return total count

## Complexity Analysis
- **Time Complexity:** O(n) - single pass for frequency counting + map iteration
- **Space Complexity:** O(n) - HashMap storage for unique elements
- **Justification:** Each element processed once, HashMap operations are O(1) average case

## Edge Cases Considered
- [x] k = 0 (requires duplicate elements)
- [x] Empty array (return 0)
- [x] Single element array (return 0)
- [x] All elements are same
- [x] No valid pairs exist
- [x] Negative k values

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(k == 0){
                if(entry.getValue() >= 2){
                    count++;
                }
            }
            else{
                if(map.containsKey(entry.getKey() + k)){
                    count++;
                }
            }
        }
        return count;
    }
}
```

## Alternative Approaches
- **Two Pointers:** Sort array and use two pointers to find pairs
- **Brute Force:** Check all pairs with nested loops O(n²)
- **Set-based:** Use HashSet for O(1) lookups with duplicate handling

## Related Problems
- **AC:** [Two Sum, Pair Sum Problems]
- **Kunal:** [Contains Duplicate, Two Sum Variations]
- **LeetCode:** [532. K-diff Pairs in an Array, 1. Two Sum, 15. 3Sum]

## Personal Notes
Excellent problem demonstrating HashMap applications for frequency counting and pair finding. The key insight is handling the special case k=0 differently since it requires finding duplicates. Shows importance of careful case analysis in algorithm design.

## Revision History
- **First Solve:** June 26, 2025 - Implemented HashMap-based frequency counting approach

---
**Tags:** #hashmap #pairs #frequency #arrays #counting 