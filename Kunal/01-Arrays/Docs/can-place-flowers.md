# Can Place Flowers

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots. Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

## Intuition/Approach
Use greedy approach: iterate through the flowerbed and plant flowers wherever possible (when current plot and adjacent plots are empty). This maximizes the number of flowers that can be planted.

## Key Observations
- Flowers cannot be planted in adjacent plots
- Plant flowers greedily wherever possible
- Check left and right neighbors before planting
- Handle edge cases (first and last plots)
- Return true if n flowers can be planted

## Algorithm Steps
1. Iterate through flowerbed array
2. For each empty plot (0), check if adjacent plots are also empty
3. If safe to plant, plant flower and increment count
4. Return true if count >= n, false otherwise

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Empty array - Return false if n > 0
- [x] Single element (empty) - Can plant if n <= 1
- [x] Single element (planted) - Cannot plant if n > 0
- [x] All empty plots - Can plant every other plot
- [x] All planted plots - Cannot plant any new flowers
- [x] n = 0 - Always return true

## Solution Code

```java
// Language: Java
public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    if (n == 0) return true;
    
    int count = 0;
    for (int i = 0; i < flowerbed.length; i++) {
        if (flowerbed[i] == 0) {
            boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
            boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
            
            if (leftEmpty && rightEmpty) {
                flowerbed[i] = 1;
                count++;
                if (count >= n) return true;
            }
        }
    }
    
    return count >= n;
}
```

## Alternative Approaches
- **Two-pass:** First count available spots, then check if n <= count
- **Sliding window:** Use sliding window to find consecutive empty plots
- **Recursive:** Try placing flowers in different positions

## Personal Notes
This is a straightforward greedy problem that tests understanding of array manipulation and edge case handling. The key insight is that we can plant flowers greedily wherever possible, and we need to carefully handle the boundary conditions for the first and last plots. This pattern is useful for many array-based constraint satisfaction problems.

---
**Tags:** #arrays #greedy #constraints #easy #flowers 