# Trailing Zeros in Factorial

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an integer n, return the number of trailing zeros in n! (n factorial). Trailing zeros are created by pairs of factors 2 and 5.

## Intuition/Approach
Count the number of times 5 appears as a factor in numbers from 1 to n. Since there are always more factors of 2 than 5, the number of trailing zeros equals the number of factor 5s.

## Key Observations
- Trailing zeros come from 10 = 2 × 5
- In factorial, there are always more 2s than 5s as factors
- Count factors of 5: n/5 + n/25 + n/125 + ...
- Each multiple of 5^k contributes k factors of 5

## Algorithm Steps
1. Initialize count = 0
2. Start with divisor = 5
3. While divisor <= n:
   - Add n/divisor to count (number of multiples)
   - Multiply divisor by 5 for next iteration
4. Return total count

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Loop runs log₅(n) times, constant space

## Edge Cases Considered
- [x] n = 0 (0 trailing zeros)
- [x] n < 5 (0 trailing zeros)
- [x] n = 5 (1 trailing zero)
- [x] Large values of n
- [x] Powers of 5 (contribute multiple factors)

## Solution Code

```java
// Language: Java
public static int trailingZeroes(int n) {
    int count = 0;
    for(int i = 5; i <= n; i = i * 5){
        count += n / i;
    }
    return count;
}
```

## Alternative Approaches
- **Recursive:** Recursively calculate n/5 + trailingZeroes(n/5)
- **While Loop:** Use while loop instead of for loop
- **Mathematical:** Direct formula using logarithms

## Related Problems
- **AC:** [Factorial.java - factorial calculation concepts]
- **Kunal:** [Pow.java - mathematical pattern analysis]
- **LeetCode:** [Factorial Trailing Zeroes - Problem 172]

## Personal Notes
Clever mathematical insight about counting factors rather than computing factorial. Demonstrates importance of mathematical analysis in optimization. Avoids large number calculations.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #mathematics #factorial #trailingZeros #numberTheory 