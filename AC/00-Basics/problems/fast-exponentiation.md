# Fast Exponentiation

**Source:** AC | **Topic:** Basics | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Calculate a^n efficiently using fast exponentiation (exponentiation by squaring). Avoid the naive O(n) approach by using bit manipulation and iterative squaring.

## Intuition/Approach
Use binary representation of exponent. For each bit position, if bit is set, multiply current result by current base. Square the base for each bit position to handle higher powers efficiently.

## Key Observations
- Binary representation of exponent determines multiplication steps
- Squaring base reduces problem size by half each iteration
- Right shift (n>>1) processes bits from right to left
- Bit masking (n&1) checks if current bit is set

## Algorithm Steps
1. Initialize result as 1
2. While exponent > 0:
   - If current bit is set (n&1 != 0), multiply result by current base
   - Square the base for next iteration
   - Right shift exponent to process next bit

## Complexity Analysis
- **Time Complexity:** O(log n) where n is the exponent
- **Space Complexity:** O(1)
- **Justification:** Each iteration reduces exponent by half, constant space

## Edge Cases Considered
- [x] Exponent 0 (result = 1)
- [x] Exponent 1 (result = base)
- [x] Even exponents (no odd bit multiplications)
- [x] Odd exponents (includes final base multiplication)
- [x] Large exponents (efficient bit processing)

## Solution Code

```java
// Language: Java
public static int fastExpo(int n, int a){
    int ans=1;
    while(n>0){
        if((n&1)!=0){
            ans *=a;
        }
        a *=a;
        n = n>>1;
    }
    return ans;
}
```

## Alternative Approaches
- **Recursive Approach:** Divide and conquer with recursion
- **Naive Approach:** Simple loop multiplication (O(n))
- **Matrix Exponentiation:** For advanced applications

## Related Problems
- **AC:** [Power2.java - power of 2 detection using bit manipulation]
- **Kunal:** [BitManipulation problems - similar bit operations]
- **LeetCode:** [Pow(x, n) - Problem 50]

## Personal Notes
Classic divide and conquer optimization using bit manipulation. Essential for competitive programming and performance-critical applications. The technique generalizes to matrix exponentiation and modular arithmetic.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #bitManipulation #exponentiation #divideAndConquer #optimization 