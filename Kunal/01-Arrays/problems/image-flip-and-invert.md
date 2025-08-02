# Flip and Invert Image

**Date:** 2025-01-19  
**Category:** Kunal Basics  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** Array Manipulation/Matrix

## Problem Statement

Given an `n x n` binary matrix image, flip the image horizontally, then invert it, and return the resulting image. Flipping horizontally means each row is reversed. Inverting means each 0 is replaced by 1, and each 1 is replaced by 0.

## Intuition/Approach

Instead of performing two separate passes (flip then invert), we can optimize by doing both operations simultaneously in a single pass using two pointers:

1. **Two Pointers Setup:** For each row, use `left` and `right` pointers
2. **Simultaneous Operation:** While swapping elements, also invert them
3. **Single Pass Efficiency:** Combine flip and invert operations

## Algorithm Steps

1. Iterate through each row of the image
2. Set `left = 0` and `right = row.length - 1`
3. While `left <= right`:
   - Calculate inverted values: `1 - image[i][left]` and `1 - image[i][right]`
   - Swap the inverted values
   - Move pointers: `left++`, `right--`
4. Return the modified image

## Key Observations

- Binary inversion: `1 - value` flips 0→1 and 1→0
- Two pointers allow simultaneous flip and invert
- In-place modification saves space
- Single pass reduces time complexity
- Works correctly for both even and odd row lengths

## Time & Space Complexity

- **Time Complexity:** O(n²) - visit each element once
- **Space Complexity:** O(1) - in-place modification, only using temp variable

## Edge Cases Considered

- [ ] Single element matrix (1x1)
- [ ] Single row matrix
- [ ] Empty matrix (edge case)
- [ ] All zeros or all ones
- [ ] Odd vs even row lengths

## Solution Code

```java
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            int left = 0;
            int right = image[i].length - 1;
            
            // Flip and invert in one pass
            while (left <= right) {
                // Swap and invert
                int temp = 1 - image[i][left];
                image[i][left] = 1 - image[i][right];
                image[i][right] = temp;
                
                left++;
                right--;
            }
        }
        return image;
    }
}
```

## Alternative Approaches

1. **Two-Pass Solution:** Separate flip and invert operations
2. **Stream API:** Use Java streams for functional approach
3. **Bit Manipulation:** Use XOR for inversion (value ^ 1)

## Related Problems

- Matrix rotation (90°, 180°, 270°)
- Array reversal
- Binary matrix problems
- In-place array modifications

**LeetCode Connection:** LeetCode #832 - Flipping an Image 