# Trapping Rain Water

**Source:** AC | **Topic:** Arrays | **Difficulty:** Hard  

---

## Problem Statement
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining. Each element represents the height of the elevation at that position.

## Intuition/Approach
Use two pointers approach from both ends. The key insight is that water level at any position is determined by the minimum of the maximum heights to its left and right. By maintaining pointers from both ends and tracking the maximum heights seen so far, we can calculate trapped water efficiently.

## Key Observations
- Water at position i = min(max_left, max_right) - height[i]
- We can use two pointers to avoid computing left and right maximums separately
- Move the pointer with smaller height because it determines the water level
- Only add water when current height is less than the corresponding maximum

## Algorithm Steps
1. Initialize left and right pointers at array ends
2. Initialize leftMax and rightMax to track maximum heights seen
3. While left <= right:
   - If height[left] <= height[right], process left side
   - Update leftMax or add water based on current height
   - Move left pointer forward
   - Otherwise, process right side similarly
   - Move right pointer backward
4. Return total water trapped

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array with constant extra space using two pointers

## Edge Cases Considered
- [x] Empty array - Returns 0
- [x] Single element - Returns 0 (no water can be trapped)
- [x] Two elements - Returns 0 (no valley to trap water)
- [x] Flat array (all same height) - Returns 0
- [x] Strictly increasing - Returns 0
- [x] Strictly decreasing - Returns 0
- [x] Valley shape - Calculates trapped water correctly

## Solution Code
```java
// Language: Java
public static int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int water = 0;
    while (left <= right) {
        if (height[left] <= height[right]) {
            // Process left side
            if (height[left] > leftMax) {
                leftMax = height[left];
            } else {
                water += leftMax - height[left];
            }
            left++;
        } else {
            // Process right side
            if (height[right] > rightMax) {
                rightMax = height[right];
            } else {
                water += rightMax - height[right];
            }
            right--;
        }
    }
    return water;
}
```

## Alternative Approaches
- Brute Force: O(n²) - for each position, find left and right maximums
- Dynamic Programming: O(n) time, O(n) space - precompute left and right max arrays
- Stack-based: O(n) time, O(n) space - use stack to track potential water boundaries
- Two Pointers: O(n) time, O(1) space - optimal solution implemented above

## Personal Notes
This is a classic two-pointer problem that requires careful thinking about invariants. The key insight is understanding which side determines the water level. Excellent problem for practicing two-pointer technique and spatial reasoning. Important to visualize the problem to understand the algorithm.

---
**Tags:** #arrays #twopointers #hard #geometry #water 