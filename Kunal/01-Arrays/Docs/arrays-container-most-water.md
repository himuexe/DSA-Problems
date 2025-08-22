# Container With Most Water

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given n non-negative integers height where each represents a point at coordinate (i, height[i]). Find two lines, which, together with the x-axis, form a container that can hold the maximum amount of water.

## Intuition/Approach
Use two-pointer technique starting from both ends. The key insight is that water level is limited by the shorter line, so we move the pointer with the shorter height to potentially find a better container.

## Key Observations
- Water level limited by shorter line
- Move pointer with shorter height
- Start from both ends
- Track maximum area found
- Area = min(height[left], height[right]) * (right - left)

## Algorithm Steps
1. Initialize left = 0, right = n-1
2. Calculate current area
3. Update maximum area if current is larger
4. Move pointer with shorter height inward
5. Continue until pointers meet

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass with two pointers

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element - Return 0 (no container possible)
- [x] Two elements - Calculate area between them
- [x] All heights same - Return area between first and last
- [x] Strictly increasing - Return area between first and last
- [x] Strictly decreasing - Return area between first and last

## Solution Code

```java
// Language: Java
public static int maxArea(int[] height) {
    int maxArea = 0;
    int left = 0;
    int right = height.length - 1;
    
    while (left < right) {
        int currentArea = Math.min(height[left], height[right]) * (right - left);
        maxArea = Math.max(maxArea, currentArea);
        
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxArea;
}
```

## Alternative Approaches
- **Brute force:** Check all pairs O(n²)
- **Dynamic programming:** Track maximum area for each position
- **Greedy with sorting:** Sort heights and find optimal pairs

## Personal Notes
This is a classic two-pointer problem that demonstrates the power of the technique. The key insight is that we can eliminate many potential containers by moving the pointer with the shorter height, since that line will never form a better container with any other line. This approach is optimal and elegant.

---
**Tags:** #arrays #twopointers #area #medium #container
