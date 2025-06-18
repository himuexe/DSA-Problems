# Two Sum

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-26 | **Status:** Solved

---

## Problem Statement
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice.

## Intuition/Approach
Use a hash map to store numbers we've seen and their indices. For each number, check if its complement (target - current_number) exists in the hash map.

## Key Observations
- Need to return indices, not the actual numbers
- Only one solution exists, so we can return immediately when found
- Hash map provides O(1) lookup time
- We build the hash map as we iterate, so we don't need to worry about using same element twice

## Algorithm Steps
1. Create an empty hash map to store {number: index}
2. Iterate through the array with index and value
3. Calculate complement = target - current_value
4. Check if complement exists in hash map
5. If yes, return [hash_map[complement], current_index]
6. If no, add current_value and its index to hash map
7. Continue until solution found

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass through array (O(n)), hash map operations are O(1), worst case we store all elements in hash map (O(n) space)

## Edge Cases Considered
- [x] Empty input - Problem guarantees solution exists
- [x] Single element - Problem guarantees exactly one solution (need at least 2 elements)
- [x] Large input - Hash map scales well
- [x] Negative numbers - Works fine with hash map
- [x] Other: Duplicate numbers - handled correctly since we check before adding

## Solution Code

```python
# Language: Python
def twoSum(nums, target):
    seen = {}  # {number: index}
    
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    
    return []  # Should never reach here per problem constraints
```

## Alternative Approaches
1. **Brute Force:** O(n²) - check every pair
2. **Sort + Two Pointers:** O(n log n) - but loses original indices
3. **Current Hash Map:** O(n) - optimal for this problem

## Related Problems
- **AC:** Three Sum, Four Sum
- **Kunal:** Array pair problems
- **LeetCode:** #1 Two Sum, #15 3Sum, #18 4Sum

## Personal Notes
- Initially tried brute force, realized hash map is much more efficient
- Remember that we're looking for indices, not values
- Hash map approach is a common pattern for "find complement" problems

## Revision History
- **First Solve:** 2024-12-19 - Solved with hash map approach, understood the pattern
- **Review 1:** (scheduled for 2024-12-26)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #hashmap #twopointers #complement 