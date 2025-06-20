# Armstrong Number Check

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Check if a given number is an Armstrong number (also known as narcissistic number). An Armstrong number is a number that equals the sum of its own digits each raised to the power of the number of digits.

## Intuition/Approach
Extract each digit, count total digits, then calculate the sum of each digit raised to the power of digit count. Compare with original number.

## Key Observations
- Need to count digits first to determine the power
- Extract digits using modulo and division operations
- Use Math.pow() for exponentiation
- Handle edge case of negative numbers

## Algorithm Steps
1. Handle negative numbers (return false)
2. Count total number of digits in the number
3. Extract each digit using modulo operation
4. Calculate digit^(total_digits) and add to sum
5. Compare final sum with original number

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through digits, constant extra space

## Edge Cases Considered
- [x] Negative numbers (handled as false)
- [x] Single digit numbers (all are Armstrong)
- [x] Zero (handled correctly)
- [x] Large numbers (Math.pow handles the calculations)
- [x] Numbers with different digit counts

## Solution Code

```java
// Language: Java
public static boolean isArmstrong(int number) {
    if (number < 0) return false; 

    int originalNumber = number;
    int sum = 0;
    int digits = countDigits(number);

    while (number > 0) {
        int digit = number % 10;
        sum += Math.pow(digit, digits);
        number /= 10;
    }

    return sum == originalNumber;
}

public static int countDigits(int number) {
    if (number == 0) return 1;
    return (int)Math.log10(number)+1;
}
```

## Alternative Approaches
- **String Conversion:** Convert to string to count digits (less efficient)
- **Logarithmic Count:** Use log10 to count digits (potential precision issues)
- **Recursive Approach:** Recursive digit extraction and calculation

## Related Problems
- **AC:** [Prime.java - mathematical verification patterns]
- **Kunal:** [Palindrome.java - digit manipulation techniques]
- **LeetCode:** [Happy Number - Problem 202]

## Personal Notes
Good example of digit manipulation techniques. The helper function approach keeps code clean and reusable. Pattern of preserving original value while modifying working copy is important.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #mathematics #digitManipulation #armstrong #numberTheory 