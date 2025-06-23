# Best Time to Buy and Sell Stock II

**Date:** 2025-01-26  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Array/Greedy Algorithm

## Problem Statement

You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `i`th day. Design an algorithm to find the maximum profit you can achieve. You may complete as many transactions as you want (i.e., buy one and sell one share of the stock multiple times).

**Note:** You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

## Intuition/Approach

**Greedy Approach:** Capture every profitable opportunity by buying before every price increase and selling at every price peak. This maximizes total profit by accumulating all positive price differences.

**Algorithm Logic:**
1. Iterate through prices array starting from day 1
2. For each day, compare current price with previous day's price
3. If current price > previous price, add the difference to profit
4. This effectively captures all upward price movements
5. Return total accumulated profit

## Algorithm Steps

1. Initialize `profit = 0` to track total profit
2. Loop from index 1 to `prices.length - 1`
3. For each index `i`, compare `prices[i]` with `prices[i-1]`
4. If `prices[i] > prices[i-1]`, add `prices[i] - prices[i-1]` to profit
5. Continue until all price differences are processed
6. Return total profit

## Key Observations

- We can make unlimited transactions (buy-sell pairs)
- Greedy approach: capture every profitable price increase
- No need to track actual buy/sell days, just profit differences
- Every consecutive increasing price contributes to profit
- Equivalent to buying at every valley and selling at every peak

## Time & Space Complexity

- **Time Complexity:** O(n) - single pass through prices array
- **Space Complexity:** O(1) - only using constant extra space

## Edge Cases Considered

- [ ] Single day (no transactions possible)
- [ ] Continuously decreasing prices (zero profit)
- [ ] Continuously increasing prices (buy day 1, sell last day)
- [ ] All prices are the same (zero profit)
- [ ] Empty array or null input
- [ ] Prices with only two days

## Solution Code

```java
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i-1] < prices[i]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }   
}
```

## Alternative Approaches

1. **Dynamic Programming Approach:** Track buy/sell states
   ```java
   // State: buy[i] = max profit after buying on day i
   // State: sell[i] = max profit after selling on day i
   ```

2. **Peak-Valley Approach:** Explicitly find valleys and peaks
3. **State Machine:** Model as finite state machine with buy/sell states

## Related Problems

- Best Time to Buy and Sell Stock (single transaction)
- Best Time to Buy and Sell Stock III (at most 2 transactions)
- Best Time to Buy and Sell Stock IV (at most k transactions)
- Best Time to Buy and Sell Stock with Cooldown

**LeetCode Connection:** LeetCode #122 - Best Time to Buy and Sell Stock II 