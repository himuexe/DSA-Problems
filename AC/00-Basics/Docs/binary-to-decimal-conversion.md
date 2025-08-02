# Binary to Decimal Conversion

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Convert a binary number (represented as integer) to its decimal equivalent. The binary number is given as an integer where each digit is either 0 or 1.

## Intuition/Approach
Extract each binary digit from right to left, multiply by corresponding power of 2, and sum all results. Use positional notation where rightmost digit has power 0.

## Key Observations
- Binary uses base 2, decimal uses base 10
- Each position represents a power of 2 (1, 2, 4, 8, 16...)
- Extract digits using modulo 10 and integer division
- Build result by accumulating powers of 2

## Algorithm Steps
1. Initialize decimal result as 0 and power as 0
2. While binary number > 0:
   - Extract rightmost digit using modulo 10
   - Add digit × 2^power to result
   - Increment power for next position
   - Remove processed digit using integer division

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of binary digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through digits, constant extra space

## Edge Cases Considered
- [x] Binary 0 (decimal 0)
- [x] Single digit binary (0 or 1)
- [x] Multiple digits binary
- [x] Large binary numbers (within int range)
- [x] Powers of 2 calculation accuracy

## Solution Code

```java
// Language: Java
public static int conversion(int binNum){
    int decNum = 0;
    int pow = 0;
    while(binNum > 0){
        decNum += (binNum % 10) * (int)Math.pow(2, pow);
        pow++;
        binNum /= 10;
    }
    return decNum;
}
```

## Alternative Approaches
- **String Processing:** Convert to string and process character by character
- **Recursive Approach:** Recursive digit extraction and conversion
- **Built-in Methods:** Integer.parseInt(binary, 2) in Java

## Personal Notes
Fundamental number system conversion. Understanding positional notation is crucial for many computer science concepts. The pattern of extracting digits is reusable in many problems.

---
**Tags:** #basics #numberSystems #binaryConversion #mathematics #positionalNotation 