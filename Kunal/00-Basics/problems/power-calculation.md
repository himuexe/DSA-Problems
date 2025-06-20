# Power Calculation (Exponentiation)

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Calculate x raised to the power of n (x^n) efficiently. Handle both positive and negative exponents, returning double precision result.

## Intuition/Approach
Use binary exponentiation (exponentiation by squaring) to achieve O(log n) time complexity. Handle negative exponents by calculating positive power and taking reciprocal.

## Key Observations
- Binary exponentiation reduces time complexity from O(n) to O(log n)
- For even exponents: x^n = (x^2)^(n/2)
- For odd exponents: x^n = x × x^(n-1)
- Negative exponents: x^(-n) = 1 / x^n

## Algorithm Steps
1. Initialize result as 1.0 and handle negative exponent
2. Convert exponent to positive (long to handle Integer.MIN_VALUE)
3. While exponent > 0:
   - If exponent is even: square base, halve exponent
   - If exponent is odd: multiply result by base, decrement exponent
4. Return reciprocal if original exponent was negative

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Binary reduction of exponent, constant extra space

## Edge Cases Considered
- [x] Positive exponents
- [x] Negative exponents
- [x] Zero exponent (result is 1)
- [x] Base = 0 with positive exponent
- [x] Integer overflow protection

## Solution Code

```java
// Language: Java
public static double myPow(double x, int n) {
    double ans = 1.0;
    long exp = n;
    if(exp < 0){
        exp = exp * -1;
    }
    while(exp > 0){
        if(exp % 2 == 0){
            x = x * x;
            exp /= 2;
        }
        else{
            ans *= x;
            exp = exp - 1;
        }
    }
    if(n < 0){
        return (double)(1.0) / (double)ans;
    }
    return ans;
}
```

## Alternative Approaches
- **Naive Approach:** Multiply x by itself n times - O(n) time
- **Recursive:** Recursive binary exponentiation - O(log n) time, O(log n) space
- **Built-in:** Math.pow(x, n) in Java

## Related Problems
- **AC:** [BinaryToDecimal.java - uses Math.pow for calculations]
- **Kunal:** [Armstrong.java - uses Math.pow for digit powers]
- **LeetCode:** [Pow(x, n) - Problem 50]

## Personal Notes
Classic example of binary exponentiation optimization. The technique of handling negative exponents and integer overflow shows attention to edge cases. Important algorithmic pattern.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #exponentiation #binaryExponentiation #mathematics #optimization 