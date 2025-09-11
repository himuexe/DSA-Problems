# Fractional Knapsack (Greedy by Value/Weight)

**Source:** AC | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given `n` items with values and weights, and a knapsack capacity `W`, determine the maximum total value that can be accommodated in the knapsack if you can take fractions of items.

## Intuition/Approach
- Greedy choice: take items in descending order of value-to-weight ratio.
- Always fill as much as possible of the highest ratio item first; this is optimal for the fractional variant.

## Key Observations
- Fractional knapsack differs from 0/1 knapsack: items can be split.
- Sorting by `value/weight` ratio ensures locally optimal decisions are globally optimal.
- Stop early once capacity is filled.

## Algorithm Steps
1. Compute `ratio = value/weight` for each item.
2. Sort items by `ratio` in descending order.
3. Initialize `totalValue = 0` and remaining capacity `W`.
4. For each item:
   - If `weight <= W`, take the whole item; subtract weight from `W`, add full value.
   - Else, take fraction `W/weight` of the item; add `ratio * W` to value; set `W = 0` and stop.
5. Return `totalValue`.

## Complexity Analysis
- **Time Complexity:** O(n log n) for sorting
- **Space Complexity:** O(n) for item array
- **Justification:** Single linear scan after sorting.

## Edge Cases Considered
- [x] Empty items or zero capacity (answer 0)
- [x] Items heavier than capacity
- [x] All items fit within capacity
- [x] Values or weights with large magnitudes
- [ ] Other: duplicate ratios

## Solution Code

```java
import java.util.*;

public class FractionalKnapsack {
    static class Item {
        int value;
        int weight;
        double valuePerWeight;
        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.valuePerWeight = (double) value / weight;
        }
    }

    public static double getMaxValue(int[] val, int[] wt, int W) {
        int n = val.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));
        double totalValue = 0;
        for (Item item : items) {
            if (W == 0) break;
            if (item.weight <= W) {
                totalValue += item.value;
                W -= item.weight;
            } else {
                totalValue += item.valuePerWeight * W;
                W = 0;
            }
        }
        return totalValue;
    }
}
```

## Alternative Approaches
- For 0/1 knapsack (no fractions), dynamic programming is needed; greedy fails.

## Personal Notes
- Sorting stability does not matter since only ratios drive the choice.

---
**Tags:** #arrays #greedy #sorting #knapsack
