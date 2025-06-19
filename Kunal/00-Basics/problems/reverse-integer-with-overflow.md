# Reverse Integer with Overflow Check

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Reverse the digits of a 32-bit signed integer. If reversing causes the integer to overflow, return 0. Handle both positive and negative integers.

## Intuition/Approach
Extract digits from right to left while building the reversed number. Check for integer overflow before each multiplication to prevent overflow. Handle negative numbers by checking for underflow as well.

## Key Observations
- Integer overflow check: rev > MAX_VALUE/10 or (rev == MAX_VALUE/10 && digit > 7)
- Integer underflow check: rev < MIN_VALUE/10 or (rev == MIN_VALUE/10 && digit < -8)
- Need to handle both positive and negative numbers
- Return 0 on overflow instead of throwing exception

## Algorithm Steps
1. Initialize reversed number as 0
2. While input number != 0:
   - Extract rightmost digit using modulo
   - Check for overflow/underflow conditions
   - If overflow would occur, return 0
   - Update reversed number: rev = rev * 10 + digit
   - Remove processed digit from input
3. Return reversed number

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through digits, constant extra space

## Edge Cases Considered
- [x] Positive numbers
- [x] Negative numbers  
- [x] Numbers causing overflow (return 0)
- [x] Numbers causing underflow (return 0)
- [x] Single digit numbers
- [x] Integer boundary values

## Solution Code

```java
// Language: Java
public static int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        int pop = x % 10;
        x /= 10;
        if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
        if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
        rev = rev * 10 + pop;
    }
    return rev;
}
```

## Alternative Approaches
- **String Approach:** Convert to string, reverse, convert back (handles overflow differently)
- **Long Approach:** Use long for calculations, check bounds before returning int
- **Exception Handling:** Catch overflow exceptions instead of preventive checks

## Related Problems
- **AC:** [ReverseNum.java - similar digit reversal without overflow handling]
- **Kunal:** [Palindrome.java - digit manipulation techniques]
- **LeetCode:** [Reverse Integer - Problem 7]

## Personal Notes
Important example of overflow handling in competitive programming. The preventive overflow checking technique is crucial for robust integer operations. Shows attention to edge cases and boundary conditions.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #digitManipulation #overflowHandling #integerBoundaries #edgeCases 