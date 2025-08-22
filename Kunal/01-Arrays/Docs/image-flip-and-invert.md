# Flipping an Image

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image. To flip an image horizontally means that each row of the image is reversed. To invert an image means that each 0 becomes 1, and each 1 becomes 0.

## Intuition/Approach
First flip each row horizontally by reversing the row, then invert each element by changing 0 to 1 and 1 to 0. This can be done in a single pass through the matrix.

## Key Observations
- Flip horizontally = reverse each row
- Invert = change 0 to 1 and 1 to 0
- Can combine both operations in single pass
- For each row, reverse and invert simultaneously
- Matrix is n x n (square)

## Algorithm Steps
1. Iterate through each row of the matrix
2. For each row, use two pointers to reverse elements
3. While reversing, also invert each element (0→1, 1→0)
4. Return the modified matrix

## Complexity Analysis
- **Time Complexity:** O(n²)
- **Space Complexity:** O(1)
- **Justification:** Visit each element once, in-place modification

## Edge Cases Considered
- [x] 1x1 matrix - Single element flip and invert
- [x] 2x2 matrix - Two rows, each with two elements
- [x] All zeros - Become all ones after flip and invert
- [x] All ones - Become all zeros after flip and invert
- [x] Mixed values - Handle both operations correctly

## Solution Code

```java
// Language: Java
public static int[][] flipAndInvertImage(int[][] image) {
    int n = image.length;
    
    for (int i = 0; i < n; i++) {
        int left = 0, right = n - 1;
        while (left <= right) {
            // Swap and invert elements
            int temp = image[i][left];
            image[i][left] = 1 - image[i][right];
            image[i][right] = 1 - temp;
            left++;
            right--;
        }
    }
    
    return image;
}
```

## Alternative Approaches
- **Two-pass:** First flip, then invert separately
- **Extra space:** Create new matrix and copy elements
- **Bitwise:** Use XOR with 1 for inversion

## Personal Notes
This is a straightforward matrix manipulation problem that combines two common operations: flipping and inverting. The key insight is that we can perform both operations simultaneously while reversing each row, which is more efficient than doing them separately. The bitwise trick (1 - value) for inversion is elegant.

---
**Tags:** #arrays #matrix #flip #invert #easy 