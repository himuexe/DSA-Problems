# Best Time to Buy and Sell Stock

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction.

## Intuition/Approach
Use a single pass through the array, keeping track of the minimum price seen so far and calculating the potential profit at each day. Update the maximum profit whenever we find a better opportunity.

## Key Observations
- Need to buy before selling
- Track minimum price seen so far
- Calculate profit at each day
- Update maximum profit when better opportunity found
- Single pass solution

## Algorithm Steps
1. Initialize minPrice = first price, maxProfit = 0
2. Iterate through array starting from second element
3. Update minPrice if current price is lower
4. Calculate current profit = current price - minPrice
5. Update maxProfit if current profit is larger
6. Return maxProfit

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
- [x] Strictly decreasing - Return 0

## Solution Code

```java
// Language: Java
public static int maxProfit(int[] prices) {
    if (prices.length < 2) return 0;
    
    int minPrice = prices[0];
    int maxProfit = 0;
    
    for (int i = 1; i < prices.length; i++) {
        minPrice = Math.min(minPrice, prices[i]);
        maxProfit = Math.max(maxProfit, prices[i] - minPrice);
    }
    
    return maxProfit;
}
```

## Alternative Approaches
- **Brute force:** Check all pairs O(n²)
- **Dynamic programming:** Track best buy/sell for each day
- **Two pointers:** Track buy and sell days

## Personal Notes
This is a classic problem that demonstrates the power of a single-pass solution. The key insight is that we only need to track the minimum price seen so far and calculate potential profits at each step. This approach is optimal in both time and space complexity and is a fundamental pattern for many optimization problems.

---
**Tags:** #arrays #profit #optimization #easy #stock
