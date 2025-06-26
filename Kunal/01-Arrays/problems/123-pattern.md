# 132 Pattern Detection

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of integers, find if there exists a subsequence of length 3 with indices i < j < k such that nums[i] < nums[k] < nums[j]. This is known as the 132 pattern.

## Intuition/Approach
Use a combination of preprocessing and stack-based approach. First, precompute minimum values from left. Then traverse from right using a stack to find valid 132 patterns by maintaining potential middle elements.

## Key Observations
- Need three indices i < j < k with nums[i] < nums[k] < nums[j]
- Precompute minimum values up to each position for efficient lookup
- Use stack to maintain decreasing sequence of potential middle elements
- Process from right to left to efficiently find valid patterns

## Algorithm Steps
1. Create min array where min[i] = minimum value from index 0 to i
2. Initialize empty stack and traverse array from right to left (starting from index 1)
3. For each position i, if nums[i] > min[i]:
   - Remove elements from stack that are ≤ min[i] (too small to be valid)
   - Check if stack top exists and is < nums[i] (valid 132 pattern found)
   - Push nums[i] to stack as potential middle element
4. Return true if pattern found, false otherwise

## Complexity Analysis
- **Time Complexity:** O(n) - single pass with each element pushed/popped at most once
- **Space Complexity:** O(n) - for min array and stack storage
- **Justification:** Each element is processed constant time on average

## Edge Cases Considered
- [x] Array length < 3 (impossible to have 132 pattern)
- [x] Strictly increasing array (no 132 pattern)
- [x] Strictly decreasing array (no 132 pattern)
- [x] Array with duplicates
- [x] Multiple valid patterns (return true for first found)

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3) return false;
        
        int[] min = new int[nums.length];
        min[0] = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            min[i] = Math.min(nums[i], min[i-1]);
        }
        
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i = nums.length - 1; i >= 1; i--){
            if(nums[i] > min[i]){
                while((!st.isEmpty()) && (st.peek() <= min[i])){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek() < nums[i]){
                    return true;
                }
                st.push(nums[i]);
            }
        }
        return false;
    }
}
```

## Alternative Approaches
- **Brute Force:** O(n³) approach checking all triplets
- **Two-Pass Method:** Find min on left and max on right for each middle element
- **Segment Tree:** Use advanced data structures for range queries

## Related Problems
- **AC:** [Longest Increasing Subsequence, Three Sum]
- **Kunal:** [Next Greater Element, Valid Parentheses]
- **LeetCode:** [456. 132 Pattern, 496. Next Greater Element I, 739. Daily Temperatures]

## Personal Notes
Excellent problem combining preprocessing, stack operations, and pattern recognition. The key insight is recognizing that we need to efficiently find elements satisfying the ordering constraint. Stack maintains candidates while preprocessing ensures efficient minimum lookups.

## Revision History
- **First Solve:** June 26, 2025 - Implemented stack-based approach with preprocessing

---
**Tags:** #stack #preprocessing #pattern #arrays #monotonic 