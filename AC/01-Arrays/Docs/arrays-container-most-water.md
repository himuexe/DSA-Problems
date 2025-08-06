# Container With Most Water

**Source:** AC | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Find two lines in an array that form a container holding the most water. Each element represents the height of a vertical line. Return the maximum area that can be formed.

## Intuition/Approach
Use two pointers starting at both ends. Calculate area using minimum height and distance between pointers. Move the pointer with smaller height inward to potentially find larger area.

## Key Observations
- Area = min(height[left], height[right]) × (right - left)
- Moving pointer with larger height won't increase area (limited by smaller height)
- Moving pointer with smaller height might find taller line
- Optimal solution requires exploring maximum possible widths first

## Algorithm Steps
1. Initialize left pointer at start, right pointer at end
2. Track maximum area found so far
3. While left < right:
   - Calculate current area using minimum height and width
   - Update maximum area if current is larger
   - Move pointer with smaller height inward
   - If heights are equal, move either pointer

## Complexity Analysis
- **Time Complexity:** O(n) where n is array length
- **Space Complexity:** O(1)
- **Justification:** Single pass with two pointers, constant extra space

## Edge Cases Considered
- [x] Array with 2 elements (minimum valid input)
- [x] All elements same height
- [x] Increasing height array
- [x] Decreasing height array
- [x] Single tall element among short ones

## Solution Code
```java
// Language: Java
class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int right =height.length-1;
        int area=0;
        while(left < right){
            int h = (int)Math.min(height[right],height[left]);
            int width = right - left;
            int currentArea = h*width;
            area = Math.max(area,currentArea);
            if(height[left] < height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return area;
    }
}
```

## Alternative Approaches
- Brute Force: Try all pairs O(n²) time
- Divide and Conquer: Recursive approach with similar complexity
- Dynamic Programming: Overkill for this problem

## Personal Notes
Classic two pointers optimization. Key insight is that moving the shorter line pointer is always optimal. The greedy approach works because we start with maximum width and progressively trade width for potentially better height. Essential for understanding pointer movement strategies.

---
**Tags:** #arrays #twoPointers #greedy #areaCalculation #optimization 