# Find Numbers with Even Number of Digits

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an array of integers, return how many of them contain an even number of digits. For example, 12 has 2 digits (even), while 123 has 3 digits (odd).

## Intuition/Approach
For each number in the array, count the number of digits. If the digit count is even, increment our result counter. The key insight is using logarithms to efficiently count digits: digits = log10(number) + 1.

## Key Observations
- Need to count digits for each number in the array
- Use Math.log10(n) + 1 to count digits efficiently
- Only positive numbers are considered (per typical problem constraints)
- Even digit count: 2, 4, 6, 8... digits
- Logarithm approach is more efficient than string conversion or division loops

## Algorithm Steps
1. Initialize count = 0
2. For each number in the array:
   - Calculate digits = (int)Math.log10(number) + 1
   - If digits % 2 == 0, increment count
3. Return count

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, logarithm operation is O(1), constant extra space

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Array with single-digit numbers - All have odd digit count (1)
- [x] Array with two-digit numbers - All have even digit count (2)
- [x] Mixed digit counts - Count only those with even digits
- [x] Large numbers - Logarithm approach handles efficiently
- [x] All numbers have odd digits - Return 0
- [x] All numbers have even digits - Return array length

## Solution Code

```java
// Language: Java
public static int calcDigits(int[] arr) {
    int count = 0;
    for (int nums : arr) {
        if (even(nums)) {
            count++;
        }
    }
    return count;
}

static boolean even(int nums) {
    int digits = digits(nums);
    return digits % 2 == 0;
}

static int digits(int nums) {
    return (int) Math.log10(nums) + 1;
}
```

## Alternative Approaches
1. **String Conversion:** Convert to string and check length - O(n * log(max_number))
2. **Division Loop:** Repeatedly divide by 10 until 0 - O(n * log(max_number))
3. **Logarithm Approach:** O(n) - optimal solution above
4. **Lookup Table:** Pre-compute for common ranges - O(n) but complex setup

## Related Problems
- **AC:** Count digits in numbers, digit manipulation problems
- **Kunal:** Number of digits in factorial, palindrome numbers
- **LeetCode:** #1295 Find Numbers with Even Number of Digits, #7 Reverse Integer

## Personal Notes
- Logarithm approach is elegant and efficient for digit counting
- Important to handle edge cases like single-digit numbers
- Good practice for array iteration and mathematical operations
- Foundation for more complex digit manipulation problems

## Revision History
- **First Solve:** 2024-12-19 - Implemented logarithm approach, understood efficient digit counting
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #digits #logarithm #counting #mathematics 