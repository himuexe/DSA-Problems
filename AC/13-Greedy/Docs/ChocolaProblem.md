# Minimum Cost to Cut Chocolate

**Source:** AC | **Topic:** Greedy | **Difficulty:** Medium  

---

## Problem Statement
Given costs for horizontal and vertical cuts of a chocolate bar, compute the minimum total cost to cut the bar into 1x1 pieces. Each time you make a horizontal cut, it applies across all current vertical pieces (and vice versa), so the effective cost of a cut is its cost multiplied by the number of pieces in the perpendicular direction.

## Intuition/Approach
Use a greedy strategy: always apply the currently most expensive cut to maximize its contribution when the perpendicular piece count is smallest. Sort both horizontal and vertical cut arrays in descending order and pick the larger of the two at each step, updating the respective piece counts.

## Key Observations
- Multiplicative effect: a cut cost scales with the number of perpendicular pieces.
- Taking larger cuts earlier minimizes total cost.
- After one list is exhausted, the remaining cuts are applied with the current perpendicular multiplier.

## Algorithm Steps
1. Sort `horizontalCost` and `verticalCost` in descending order.
2. Maintain indices `h`, `v` and piece counters `hp` (horizontal pieces), `vp` (vertical pieces), both start at 1.
3. While both lists have cuts left:
   - If `horizontalCost[h] >= verticalCost[v]`, add `horizontalCost[h] * vp`, increment `hp` and `h`.
   - Else add `verticalCost[v] * hp`, increment `vp` and `v`.
4. Add remaining horizontal cuts multiplied by `vp`.
5. Add remaining vertical cuts multiplied by `hp`.

## Complexity Analysis
- **Time Complexity:** O((H + V) log(H + V)) due to sorting
- **Space Complexity:** O(1) auxiliary (excluding input arrays and sort in-place)
- **Justification:** Single pass after sorting; constant extra state.

## Edge Cases Considered
- [x] Single element in one or both arrays
- [x] One of the arrays empty
- [ ] Large inputs
- [ ] Non-positive costs (if allowed)
- [ ] Other: Input not sorted; duplicates

## Solution Code

```java
import java.util.*;

public class ChocolaProblem {
    public static int minCostToCutChocolate(Integer[] horizontalCost, Integer[] verticalCost) {
        Arrays.sort(horizontalCost, Collections.reverseOrder());
        Arrays.sort(verticalCost, Collections.reverseOrder());
        int h = 0, v = 0;
        int hp = 1, vp = 1;
        int totalCost = 0;
        while (h < horizontalCost.length && v < verticalCost.length) {
            if (horizontalCost[h] >= verticalCost[v]) {
                totalCost += horizontalCost[h] * vp;
                hp++; h++;
            } else {
                totalCost += verticalCost[v] * hp;
                vp++; v++;
            }
        }
        while (h < horizontalCost.length) totalCost += horizontalCost[h++] * vp;
        while (v < verticalCost.length) totalCost += verticalCost[v++] * hp;
        return totalCost;
    }
}
```

## Alternative Approaches
- Dynamic programming is unnecessary; the greedy strategy is optimal due to the multiplicative structure.
- Priority queues can simulate picking next largest without full sort if inputs are streamed.

## Personal Notes
Classic greedy proof by exchange argument: swapping a larger-cost cut earlier never increases cost.

---
**Tags:** #greedy #sorting #arrays #optimization
