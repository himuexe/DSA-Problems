# Jump Game

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position. Return true if you can reach the last index, or false otherwise.

## Intuition/Approach
Use greedy approach: track the maximum reachable position from current position. If at any point we can't reach the current position, return false. If we can reach or exceed the last index, return true.

## Key Observations
- Track maximum reachable position
- If current position > max reachable, return false
- Update max reachable at each step
- Return true if max reachable >= last index
- Greedy approach is optimal

## Algorithm Steps
1. Initialize maxReachable = 0
2. Iterate through array
3. If current position > maxReachable, return false
4. Update maxReachable = max(maxReachable, i + nums[i])
5. If maxReachable >= last index, return true
6. Return false if loop completes

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Single element - Return true
- [x] Two elements - Check if can jump to second
- [x] All zeros - Return false (can't move)
- [x] Large jump values - Handle correctly
- [x] Can't reach end - Return false
- [x] Can reach end - Return true

## Solution Code

```java
// Language: Java
public static boolean canJump(int[] nums) {
    int maxReachable = 0;
    
    for (int i = 0; i < nums.length; i++) {
        if (i > maxReachable) {
            return false;
        }
        maxReachable = Math.max(maxReachable, i + nums[i]);
        if (maxReachable >= nums.length - 1) {
            return true;
        }
    }
    
    return false;
}
```

## Alternative Approaches
- **Dynamic Programming:** Track reachability for each position
- **Backtracking:** Try all possible jump combinations
- **BFS:** Model as graph traversal problem

## Personal Notes
This is a classic greedy problem that demonstrates how sometimes the optimal solution can be found by making locally optimal choices. The key insight is tracking the maximum reachable position and checking if we can reach the current position. This approach is optimal in both time and space complexity.

---
**Tags:** #arrays #greedy #jumping #medium #reachability 