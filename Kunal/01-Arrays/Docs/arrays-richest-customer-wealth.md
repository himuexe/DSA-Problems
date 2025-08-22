# Richest Customer Wealth

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i-th customer has in the j-th bank. Return the wealth that the richest customer has. A customer's wealth is the amount they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.

## Intuition/Approach
Iterate through each customer's accounts, sum up their total wealth across all banks, and keep track of the maximum wealth found. Return the maximum wealth.

## Key Observations
- Each customer has multiple bank accounts
- Need to sum all accounts for each customer
- Track maximum wealth seen so far
- Simple iteration and summation problem
- Return single maximum value

## Algorithm Steps
1. Initialize maxWealth = 0
2. Iterate through each customer (row)
3. For each customer, sum all bank accounts
4. Update maxWealth if current sum is larger
5. Return maxWealth

## Complexity Analysis
- **Time Complexity:** O(m × n)
- **Space Complexity:** O(1)
- **Justification:** Visit each element once, constant extra space

## Edge Cases Considered
- [x] Single customer - Return their wealth
- [x] Single bank - Sum single values
- [x] All customers have same wealth - Return that value
- [x] One customer has much more wealth - Return their wealth
- [x] Empty accounts - Return 0
- [x] Negative amounts - Handle correctly

## Solution Code

```java
// Language: Java
public static int maximumWealth(int[][] accounts) {
    int maxWealth = 0;
    
    for (int i = 0; i < accounts.length; i++) {
        int currentWealth = 0;
        for (int j = 0; j < accounts[i].length; j++) {
            currentWealth += accounts[i][j];
        }
        maxWealth = Math.max(maxWealth, currentWealth);
    }
    
    return maxWealth;
}
```

## Alternative Approaches
- **Stream API:** Use Java streams for functional approach
- **Separate loops:** First sum each customer, then find maximum
- **In-place modification:** Modify accounts array (not recommended)

## Personal Notes
This is a straightforward 2D array problem that tests basic iteration and summation skills. The key insight is that we need to sum across rows (customers) and find the maximum. This pattern is common in many array-based problems where we need to aggregate data and find extremes.

---
**Tags:** #arrays #2darray #summation #easy #wealth 