# Odd or Even Number Check

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Determine whether a given integer is odd or even. An even number is divisible by 2, while an odd number is not.

## Intuition/Approach
Use modulo operator to check if the number is divisible by 2. If remainder is 0, the number is even; otherwise, it's odd.

## Key Observations
- Even numbers have remainder 0 when divided by 2
- Odd numbers have remainder 1 when divided by 2
- Modulo operator (%) gives the remainder of division
- Works for both positive and negative numbers

## Algorithm Steps
1. Take the input number
2. Calculate number % 2
3. If result is 0, number is even
4. If result is 1, number is odd
5. Display the result

## Complexity Analysis
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)
- **Justification:** Single modulo operation, constant space

## Edge Cases Considered
- [x] Positive even numbers
- [x] Positive odd numbers
- [x] Zero (even by definition)
- [x] Negative numbers (works correctly)
- [x] Large numbers

## Solution Code

```java
// Language: Java
public static void oddEven(int number){
    if(number % 2 == 0){
        System.out.println(number + " is Even ");
    }
    else{
        System.out.println(number + " is Odd ");
    }
}
```

## Alternative Approaches
- **Bitwise AND:** (number & 1) == 0 for even, == 1 for odd
- **Division Check:** number / 2 * 2 == number for even numbers
- **String Approach:** Check last digit character

## Related Problems
- **AC:** [Prime.java - uses modulo for divisibility checks]
- **Kunal:** [FizzBuzz.java - similar conditional logic]
- **LeetCode:** [Add Digits - Problem 258]

## Personal Notes
Most fundamental programming concept involving conditional statements and modulo operator. Great introduction to boolean logic and decision making in programming.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #conditionals #modulo #oddEven #fundamentals 