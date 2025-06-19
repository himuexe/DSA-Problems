# Count Digit Occurrence

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Count how many times a specific digit appears in a given integer. For example, count how many times digit 3 appears in number 12323 (answer: 2 times).

## Intuition/Approach
Extract each digit from right to left using modulo operation, compare with target digit, and increment counter when match is found. Continue until all digits are processed.

## Key Observations
- Extract digits using modulo 10 operation
- Remove processed digits using integer division by 10
- Count matches by comparison with target digit
- Process continues until number becomes 0

## Algorithm Steps
1. Initialize count = 0
2. While number > 0:
   - Extract rightmost digit using number % 10
   - If digit equals target digit, increment count
   - Remove processed digit using number /= 10
3. Return final count

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through all digits, constant extra space

## Edge Cases Considered
- [x] Digit not present in number (count = 0)
- [x] Digit appears multiple times
- [x] Single digit numbers
- [x] Target digit is 0
- [x] Number with trailing zeros
- [ ] Negative numbers (not handled in current implementation)

## Solution Code

```java
// Language: Java
public static int count(int n, int digit){
    int count = 0;
    while(n > 0){
        int rem = n % 10;
        if(rem == digit){
            count++;
        }
        n /= 10;
    }
    return count;
}
```

## Alternative Approaches
- **String Approach:** Convert to string and count character occurrences
- **Recursive:** Recursive digit extraction and counting
- **Mathematical:** Use logarithms and division patterns

## Related Problems
- **AC:** [ReverseNum.java - similar digit extraction patterns]
- **Kunal:** [Armstrong.java - digit manipulation techniques]
- **LeetCode:** [Number of Digit One - Problem 233]

## Personal Notes
Fundamental digit processing technique. The pattern of extracting digits using modulo and division is essential for many number manipulation problems. Good practice for counting and digit analysis.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #digitManipulation #counting #numberAnalysis #iteration 