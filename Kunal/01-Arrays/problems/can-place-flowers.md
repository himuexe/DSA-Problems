# Can Place Flowers

**Date:** 2025-01-26  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** Array/Greedy Algorithm

## Problem Statement

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots. Given an integer array `flowerbed` containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer `n`, return `true` if `n` new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.

## Intuition/Approach

**Greedy Approach:** Iterate through the flowerbed and plant flowers whenever possible. For each empty plot (0), check if both adjacent plots are empty (or don't exist for boundary cases), then plant a flower and increment the count.

**Algorithm Logic:**
1. Iterate through each plot in the flowerbed
2. For each empty plot (value 0), check adjacent plots
3. If both left and right neighbors are empty (or don't exist), plant flower
4. Update the plot to 1 and increment planted count
5. Check if we've planted enough flowers (count >= n)

## Algorithm Steps

1. Initialize `count = 0` to track planted flowers
2. Loop through each index `i` in flowerbed
3. If `flowerbed[i] == 0` (empty plot):
   - Check left neighbor: `i-1 < 0 || flowerbed[i-1] == 0`
   - Check right neighbor: `i+1 >= flowerbed.length || flowerbed[i+1] == 0`
   - If both neighbors are empty, plant flower:
     - Set `flowerbed[i] = 1`
     - Increment `count++`
4. Return `count >= n`

## Key Observations

- Greedy approach works because planting earlier never hurts later opportunities
- Boundary handling: plots at edges have fewer neighbors to check
- Must check both neighbors before planting to avoid violations
- Once planted, update the array to prevent future conflicts
- No need to continue if we've already planted n flowers

## Time & Space Complexity

- **Time Complexity:** O(n) - single pass through flowerbed array
- **Space Complexity:** O(1) - only using constant extra space

## Edge Cases Considered

- [ ] Empty flowerbed (can't plant any flowers)
- [ ] Single plot flowerbed (empty or occupied)
- [ ] All plots already occupied (can't plant any)
- [ ] All plots empty (can plant every other plot)
- [ ] n = 0 (no flowers needed to plant)
- [ ] Need more flowers than possible

## Solution Code

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean emptyLeftPlot = (i-1 < 0 || flowerbed[i-1] == 0);
                boolean emptyRightPlot = (i+1 >= flowerbed.length || flowerbed[i+1] == 0);
                if (emptyLeftPlot && emptyRightPlot) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }
        return count >= n;
    }
}
```

## Alternative Approaches

1. **Two-Pass Approach:** First count max possible, then check if >= n
2. **Pattern Recognition:** Count consecutive zeros and calculate max flowers
3. **Simulation:** Try all combinations (exponential time - not efficient)

## Related Problems

- Wiggle Subsequence
- Non-overlapping Intervals  
- Gas Station
- Jump Game
- Candy Distribution

**LeetCode Connection:** LeetCode #605 - Can Place Flowers 