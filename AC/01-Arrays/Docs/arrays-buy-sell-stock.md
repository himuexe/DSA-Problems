# Best Time to Buy and Sell Stock

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

## Intuition/Approach
Track the minimum price seen so far and the maximum profit possible at each day. For each price, calculate the profit if we sell on that day and bought at the minimum price seen so far. Keep updating the minimum price and maximum profit as we iterate through the array.

## Key Observations
- We can only sell after we buy (no short selling)
- We want to buy at the lowest price and sell at the highest price after buying
- At each day, we either update our minimum buy price or calculate potential profit
- Single pass through the array is sufficient
- Greedy approach works: always buy at lowest price seen so far

## Algorithm Steps
1. Initialize minPrice to Integer.MAX_VALUE and maxProfit to 0
2. For each price in the array:
   - If current price < minPrice, update minPrice (better buying opportunity)
   - Else if (current price - minPrice) > maxProfit, update maxProfit
3. Return maxProfit

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space for minPrice and maxProfit variables

## Edge Cases Considered
- [x] Empty array - No transactions possible, return 0
- [x] Single element - Cannot buy and sell, return 0
- [x] Prices always decreasing - No profit possible, return 0
- [x] Prices always increasing - Buy on first day, sell on last day
- [x] All same prices - No profit possible, return 0
- [x] Mixed prices - Find optimal buy and sell days

## Solution Code
```java
// Language: Java
public static int maxProfit(int[] prices) {
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    for (int price : prices) {
        if (price < minPrice) {
            minPrice = price;  // Better buying opportunity
        } else if (price - minPrice > maxProfit) {
            maxProfit = price - minPrice;  // Better selling opportunity
        }
    }
    return maxProfit;
}
```

## Alternative Approaches
- Brute Force: O(n²) - try all possible buy and sell day combinations
- Two Pointer: O(n) - similar to current approach but with explicit buy/sell day tracking
- Dynamic Programming: O(n) - track states (hold/sold) but overkill for single transaction
- Kadane's Algorithm Variant: O(n) - treat as maximum subarray problem on price differences

## Personal Notes
This is essentially finding the maximum difference between two elements where the larger element comes after the smaller element. Similar to maximum subarray problem but with different constraints. Good example of greedy algorithm working optimally. Foundation for more complex stock trading problems.

---
**Tags:** #arrays #greedy #singlepass #profit #buysell 