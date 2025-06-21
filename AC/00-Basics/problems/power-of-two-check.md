# Power of Two Check

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Determine if a given integer is a power of 2. A power of 2 has exactly one bit set in its binary representation.

## Intuition/Approach
Use the mathematical property that powers of 2 have exactly one bit set. The expression (n & (n-1)) equals 0 only for powers of 2, as it removes the single set bit.

## Key Observations
- Powers of 2: 1, 2, 4, 8, 16, 32... (binary: 1, 10, 100, 1000, 10000...)
- Subtracting 1 from power of 2 flips all bits after the set bit
- AND operation with original number results in 0
- This property is unique to powers of 2

## Algorithm Steps
1. Apply bitwise AND between n and (n-1)
2. If result is 0, then n is a power of 2
3. Return boolean result

## Complexity Analysis
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)
- **Justification:** Single bitwise operation, constant time and space

## Edge Cases Considered
- [x] Number 0 (not a power of 2, but returns true - edge case)
- [x] Number 1 (2^0 = 1, valid power of 2)
- [x] Actual powers of 2 (2, 4, 8, 16...)
- [x] Non-powers of 2 (3, 5, 6, 7...)
- [x] Negative numbers (not powers of 2)

## Solution Code

```java
// Language: Java
public static boolean isPower(int n){
    return (n&(n-1)) ==0;
}
```

## Alternative Approaches
- **Logarithmic Check:** Check if log₂(n) is an integer
- **Iterative Division:** Repeatedly divide by 2 until 1 or odd number
- **Bit Counting:** Count set bits (should be exactly 1)

## Related Problems
- **AC:** [CountSetBits.java - counts bits in numbers]
- **Kunal:** [BitManipulation problems - similar bit tricks]
- **LeetCode:** [Power of Two - Problem 231]

## Personal Notes
Elegant bit manipulation trick. The mathematical property (n & (n-1)) == 0 is a classic optimization. Note: Current implementation considers 0 as power of 2, which may need adjustment based on requirements.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #bitManipulation #powerOfTwo #mathematics #bitTricks 