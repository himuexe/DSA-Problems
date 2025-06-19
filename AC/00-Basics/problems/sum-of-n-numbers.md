# Sum of First N Natural Numbers

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Calculate the sum of first n natural numbers (1 + 2 + 3 + ... + n). For example, sum of first 5 numbers is 1+2+3+4+5 = 15.

## Intuition/Approach
Use iterative approach to add all numbers from 1 to n. Initialize sum as 0 and add each number in the range.

## Key Observations
- Natural numbers start from 1
- Simple arithmetic series: 1, 2, 3, ..., n
- Can be optimized using mathematical formula: n*(n+1)/2
- Iterative approach is more intuitive for beginners

## Algorithm Steps
1. Initialize sum as 0
2. Loop from 1 to n (inclusive)
3. Add current number to sum
4. Return final sum

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single loop through n numbers, constant extra space

## Edge Cases Considered
- [x] n = 1 (sum is 1)
- [x] n = 0 (sum is 0)
- [x] Large values of n (within int range)
- [ ] Negative numbers (not handled in current implementation)

## Solution Code

```java
// Language: Java
public static int sumUptoN(int number){
    int sum = 0;
    for(int i = 1; i <= number; i++){
        sum += i;
    }
    return sum;
}
```

## Alternative Approaches
- **Mathematical Formula:** n*(n+1)/2 - O(1) time complexity
- **Recursive:** sum(n) = n + sum(n-1) - less efficient due to stack overhead
- **Stream API:** IntStream.rangeClosed(1,n).sum() in Java 8+

## Related Problems
- **AC:** [Factorial.java - similar iterative patterns]
- **Kunal:** [Fibonacci.java - sequence summation concepts]
- **LeetCode:** [Sum of Even Numbers After Queries - Problem 985]

## Personal Notes
Fundamental mathematical computation. The iterative approach teaches basic loop patterns. The mathematical formula optimization is worth knowing for efficiency.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #mathematics #summation #iterative #naturalNumbers 