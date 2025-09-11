# Coin Change (Greedy with Canonical Coin Systems)

**Source:** AC | **Topic:** Greedy | **Difficulty:** Easy  

---

## Problem Statement
Given coin denominations and a target amount, use a greedy approach (always pick the largest coin not exceeding the remaining amount) to compute the number of coins used. Assumes a canonical coin system (like Indian or US coins) where greedy yields an optimal solution.

## Intuition/Approach
Sort coins descending and repeatedly subtract the largest coin that fits. This minimizes the remaining amount quickly and is optimal for canonical systems.

## Key Observations
- Greedy is optimal only for canonical coin systems.
- For arbitrary coin sets, DP is required for optimality.
- Sorting once and taking as many of the largest coin as possible is efficient.

## Algorithm Steps
1. Sort `coins` in descending order.
2. Initialize `count=0`.
3. For each coin `c`:
   - While `amount >= c`: `amount -= c`, increment `count`.
4. Return `count` (or indicate impossible if `amount > 0`).

## Complexity Analysis
- **Time Complexity:** O(C log C + K) where C is number of denominations and K is number of coins taken
- **Space Complexity:** O(1)
- **Justification:** Single pass after sorting; constant memory.

## Edge Cases Considered
- [x] amount = 0
- [x] Single denomination
- [ ] Non-canonical sets (greedy may fail)
- [ ] Large amount
- [ ] Other: Impossible to make amount

## Solution Code

```java
import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        // reverse in-place
        for (int i = 0; i < coins.length / 2; i++) {
            int tmp = coins[i];
            coins[i] = coins[coins.length - 1 - i];
            coins[coins.length - 1 - i] = tmp;
        }
        int count = 0;
        for (int coin : coins) {
            if (amount == 0) break;
            if (coin <= amount) {
                int take = amount / coin; // take as many as possible
                count += take;
                amount -= take * coin;
            }
        }
        return amount == 0 ? count : -1; // -1 if not possible with greedy
    }
}
```

## Alternative Approaches
- Dynamic Programming (unbounded knapsack) to guarantee minimal coins for any coin set.
- BFS on remainder states for exact minimal steps.

## Personal Notes
Greedy fails on coin sets like {1,3,4} for amount 6. Use DP in such cases.

---
**Tags:** #greedy #coin-change #math #sorting
