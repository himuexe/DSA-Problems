# Two Sum Problem - Example Template

**Problem:** LeetCode 1. Two Sum  
**Date Solved:** [CURRENT_DATE] | **Revision Due:** [CURRENT_DATE + 7] | **Status:** Solved  
**Source:** AC 01-Arrays | **Difficulty:** Easy | **Time:** 15 min

> **Dynamic Date Instructions:** 
> - Replace [CURRENT_DATE] with actual system date (format: YYYY-MM-DD)
> - Replace [CURRENT_DATE + 7] with date 7 days from current date
> - Use `date +"%Y-%m-%d"` command for current date
> - Use `date -d "+7 days" +"%Y-%m-%d"` for revision date

---

## Problem Statement

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

**Example:**
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9
```

---

## Intuition & Approach

**Key Insight:** Instead of checking every pair (O(n²)), use a hash map to store complements and find them in O(1).

**Algorithm Steps:**
1. Create hash map to store number → index mapping
2. For each element, calculate complement = target - current number
3. Check if complement exists in hash map
4. If yes, return current index and complement's index
5. If no, add current number and index to hash map

**Why This Works:** We're essentially asking "have I seen the number that would complete this sum before?"

---

## Solution Code

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        
        map.put(nums[i], i);
    }
    
    throw new IllegalArgumentException("No solution found");
}
```

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through array
- **Space Complexity:** O(n) - Hash map storage for n elements

**Trade-off:** We trade space for time efficiency.

---

## Edge Cases Considered

- [ ] Empty array
- [ ] Array with only one element  
- [ ] No valid solution exists
- [ ] Multiple solutions (problem guarantees exactly one)
- [ ] Duplicate numbers in array
- [ ] Negative numbers
- [ ] Target is 0

---

## Alternative Approaches

1. **Brute Force O(n²):** Check every pair combination
2. **Sort + Two Pointers O(n log n):** Sort first, then use two pointers (but loses original indices)

---

## Similar Problems

- 3Sum (LeetCode 15)
- 4Sum (LeetCode 18)
- Two Sum II - Input array is sorted (LeetCode 167)

---

## Key Takeaways

- Hash maps are powerful for complement-finding problems
- Trading space for time is often worthwhile
- Consider what information you need to store (value vs. index vs. both)

---

## Learning Log

- **First Solve:** [CURRENT_DATE] - Solved with hash map approach, understood the pattern

**Revision Notes:**
- [REVISION_DATE]: [Notes from revision]
- [REVISION_DATE]: [Notes from revision]

---
**Tags:** #arrays #hashmap #twopointers #complement 