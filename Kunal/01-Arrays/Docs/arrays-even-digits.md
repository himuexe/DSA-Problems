# Find Numbers with Even Number of Digits

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an array nums of integers, return how many of them contain an even number of digits.

## Intuition/Approach
Iterate through the array and count how many numbers have an even number of digits. For each number, count its digits and check if the count is even.

## Key Observations
- Need to count digits in each number
- Even number of digits means count % 2 == 0
- Can count digits by dividing by 10 repeatedly
- Handle edge case of 0 (1 digit)
- Return count of numbers with even digits

## Algorithm Steps
1. Initialize count = 0
2. Iterate through each number in array
3. For each number, count its digits
4. If digit count is even, increment count
5. Return final count

## Complexity Analysis
- **Time Complexity:** O(n × d) where d is average number of digits
- **Space Complexity:** O(1)
- **Justification:** Visit each number, count digits for each

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element - Check its digit count
- [x] All single digits - Return 0 (all odd)
- [x] All double digits - Return array length
- [x] Mixed digit counts - Count only even ones
- [x] Zero - Has 1 digit (odd)

## Solution Code

```java
// Language: Java
public static int findNumbers(int[] nums) {
    int count = 0;
    
    for (int num : nums) {
        if (countDigits(num) % 2 == 0) {
            count++;
        }
    }
    
    return count;
}

private static int countDigits(int num) {
    if (num == 0) return 1;
    
    int digits = 0;
    while (num != 0) {
        digits++;
        num /= 10;
    }
    return digits;
}
```

## Alternative Approaches
- **String conversion:** Convert to string and check length
- **Logarithm:** Use Math.log10(num) + 1 for digit count
- **Recursive:** Count digits recursively

## Personal Notes
This is a straightforward problem that tests understanding of digit counting and even/odd number properties. The key insight is that we need to count digits for each number and check if the count is even. The digit counting algorithm (divide by 10 repeatedly) is a common pattern in many number theory problems.

---
**Tags:** #arrays #digits #counting #easy #even 