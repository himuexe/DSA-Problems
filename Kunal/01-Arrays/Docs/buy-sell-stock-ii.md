# Best Time to Buy and Sell Stock II

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
You are given an integer array prices where prices[i] is the price of a given stock on the ith day. On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day. Find and return the maximum profit you can achieve.

## Intuition/Approach
Use greedy approach: buy on day i and sell on day i+1 whenever there's a profit (prices[i+1] > prices[i]). This works because we can buy and sell multiple times, so we capture all positive price differences.

## Key Observations
- Can buy and sell multiple times
- Profit = sum of all positive price differences
- Buy low, sell high on consecutive days
- No need to track actual buy/sell days
- Greedy approach is optimal

## Algorithm Steps
1. Initialize profit = 0
2. Iterate through array from index 1
3. If prices[i] > prices[i-1], add (prices[i] - prices[i-1]) to profit
4. Return total profit

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element - Return 0 (no profit possible)
- [x] Two elements (increasing) - Return difference
- [x] Two elements (decreasing) - Return 0
- [x] All elements same - Return 0
- [x] Strictly increasing - Return sum of all differences

## Solution Code

```java
// Language: Java
public static int maxProfit(int[] prices) {
    int profit = 0;
    
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) {
            profit += prices[i] - prices[i - 1];
        }
    }
    
    return profit;
}
```

## Alternative Approaches
- **Dynamic Programming:** Track state of holding/not holding stock
- **Peak Valley:** Find all peaks and valleys, sum their differences
- **Recursive:** Try all possible buy/sell combinations

## Personal Notes
This is a classic greedy problem that demonstrates how sometimes the optimal solution can be found by making locally optimal choices. The key insight is that we can capture all positive price movements by buying and selling on consecutive days whenever there's a profit. This approach is much simpler than trying to find the optimal buy/sell points.

---
**Tags:** #arrays #greedy #profit #medium #stock 