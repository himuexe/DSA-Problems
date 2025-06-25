# Jump Game

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Greedy Algorithm

## Problem Statement

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your **maximum jump length** at that position.

Return `true` if you can reach the last index, or `false` otherwise.

**Example:**
```
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Input: nums = [3,2,1,0,4]  
Output: false
Explanation: You will always arrive at index 3. Its maximum jump length is 0, which makes it impossible to reach the last index.
```

## Intuition/Approach

**Greedy Strategy:** Track the maximum reachable index at each step
- **Key Insight:** At each position, we update our maximum reachable distance
- **Early success:** If we can reach the last index, return true immediately
- **Early failure:** If current position exceeds our reachable range, return false

**Algorithm Logic:**
1. Initialize `reachable = 0` (maximum index we can reach)
2. For each position `i` that we can actually reach (`i <= reachable`):
   - Update `reachable = max(reachable, i + nums[i])`
   - If `reachable >= nums.length-1`, we can reach the end
3. If loop completes without reaching end, return false

## Algorithm Steps

1. **Initialize:** `reachable = 0` to track maximum reachable index
2. **Iterate:** For each position `i` from 0 to n-1:
   - **Check reachability:** Only process if `i <= reachable`
   - **Update reach:** `reachable = max(reachable, i + nums[i])`
   - **Early termination:** If `reachable >= n-1`, return true
3. **Final check:** If loop completes, return false

## Key Observations

- **Greedy choice:** Always track the maximum possible reach
- **Early termination:** Stop as soon as target is reachable
- **Reachability constraint:** Only process positions we can actually reach
- **Single pass:** O(n) time complexity with constant space
- **No backtracking:** Greedy approach guarantees optimal solution

## Time & Space Complexity

- **Time Complexity:** O(n) - Single pass through array
- **Space Complexity:** O(1) - Only using constant extra space

## Edge Cases Considered

- [x] Single element array (always true - already at end)
- [x] First element is 0 and array length > 1 (false - can't move)
- [x] All elements are 0 except first (depends on first element)
- [x] Array with sufficient jumps to reach end
- [x] Array where we get stuck before reaching end

## Solution Code

```java
class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        
        for(int i = 0; i < nums.length && i <= reachable; i++) {
            reachable = Math.max(reachable, i + nums[i]);
            
            // Early termination: can reach the end
            if(reachable >= nums.length - 1) {
                return true;
            }
        }
        
        return false;
    }
}
```

## Alternative Approaches

1. **Dynamic Programming:** O(n²) approach checking all possible jumps
2. **BFS/DFS:** Treat as graph traversal problem
3. **Backwards Greedy:** Start from end and work backwards

## Greedy Algorithm Analysis

**Why Greedy Works:**
- **Optimal substructure:** Best choice at each step leads to global optimum
- **Greedy choice property:** Maximizing reach at each position is always beneficial
- **No need to backtrack:** Once we know maximum reach, previous decisions don't matter

**Proof Intuition:**
If we can reach position `i`, then tracking `max(reachable, i + nums[i])` gives us the furthest we can possibly go from positions 0 through i.

## Related Problems

- Jump Game II (minimum jumps to reach end)
- Minimum Number of Taps to Open to Water a Garden
- Video Stitching
- Minimum Number of Refueling Stops

**LeetCode Connection:** LeetCode #55 - Jump Game 