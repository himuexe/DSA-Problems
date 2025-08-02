# Reverse Number

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Reverse the digits of a given positive integer. For example, if input is 1234, output should be 4321.

## Intuition/Approach
Extract digits from right to left using modulo operation, then build the reversed number by shifting previous result and adding new digit.

## Key Observations
- Extract rightmost digit using number % 10
- Remove processed digit using number / 10
- Build reversed number: rev = rev * 10 + digit
- Continue until original number becomes 0

## Algorithm Steps
1. Initialize reversed number as 0
2. While original number > 0:
   - Extract rightmost digit using modulo 10
   - Update reversed number: rev = rev * 10 + digit
   - Remove processed digit: number = number / 10
3. Return reversed number

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through all digits, constant extra space

## Edge Cases Considered
- [x] Single digit numbers
- [x] Numbers ending with zeros (leading zeros lost)
- [x] Large numbers (within int range)
- [ ] Negative numbers (not handled in current implementation)
- [ ] Integer overflow for large reversed numbers

## Solution Code

```java
// Language: Java
public static int calculateReverse(int number){
    int rev = 0;
    while(number > 0){
        rev = number % 10 + rev * 10;
        number /= 10;
    }
    return rev;
}
```

## Alternative Approaches
- **String Approach:** Convert to string, reverse string, convert back to int
- **Recursive Approach:** Recursive digit extraction and building
- **StringBuilder:** Use StringBuilder.reverse() for string manipulation

## Personal Notes
Fundamental digit manipulation technique. The formula rev = rev * 10 + digit is crucial for building numbers digit by digit. Watch out for integer overflow with large numbers.

---
**Tags:** #basics #digitManipulation #reverseNumber #mathematics #iterative 