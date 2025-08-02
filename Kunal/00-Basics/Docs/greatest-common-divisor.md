# Greatest Common Divisor (GCD) & Least Common Multiple (LCM)

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement

Implement methods to find the Greatest Common Divisor (GCD) and Least Common Multiple (LCM) of two integers using the Euclidean algorithm.

## Intuition/Approach
The **Euclidean Algorithm** is based on the principle that the GCD of two numbers doesn't change if the larger number is replaced by its difference with the smaller number. More efficiently, we use modulo operation:
- `gcd(a, b) = gcd(b, a % b)` when `a > b`
- Base case: `gcd(0, b) = b`

For LCM, we use the mathematical relationship: `LCM(a, b) = (a * b) / GCD(a, b)`

## Algorithm Steps
1. **GCD Calculation:**
   - If `a == 0`, return `b` (base case)
   - Otherwise, recursively call `gcd(b % a, a)`

2. **LCM Calculation:**
   - Calculate GCD first
   - Apply formula: `(a * b) / GCD(a, b)`

## Key Observations
- Euclidean algorithm is highly efficient with O(log min(a,b)) complexity
- Recursive implementation is clean and follows mathematical definition
- LCM calculation leverages GCD to avoid complex factorization
- Works for both positive and negative integers

## Complexity Analysis
- **Time Complexity:** O(log min(a, b)) - logarithmic due to rapid reduction
- **Space Complexity:** O(log min(a, b)) - due to recursive call stack
- **Justification:** Euclidean algorithm reduces numbers exponentially

## Edge Cases Considered
- [x] One number is zero (handled by base case)
- [x] Both numbers are equal
- [x] Numbers are coprime (GCD = 1)
- [x] Large number differences
- [x] Negative numbers (mathematical properties still apply)

## Solution Code

```java
public class GCD {
    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
```

## Alternative Approaches
- **Iterative GCD:** Replace recursion with while loop
- **Binary GCD:** Use bit operations for even numbers
- **Extended Euclidean:** Also find coefficients for Bézout's identity

## Personal Notes
Fundamental mathematical algorithm with wide applications. The recursive implementation is elegant and follows the mathematical definition closely. Understanding this algorithm is essential for many number theory problems.

---
**Tags:** #basics #mathematics #numberTheory #gcd #lcm #euclideanAlgorithm 