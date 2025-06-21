# Count Set Bits

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Count the number of set bits (1s) in the binary representation of a given integer. Also known as population count or Hamming weight.

## Intuition/Approach
Iterate through each bit position using bit manipulation. Check if the least significant bit is set, increment counter if true, then right shift to process next bit.

## Key Observations
- Bit masking (n&1) checks if LSB is set
- Right shift (n>>1) processes bits from right to left
- Loop continues until all bits are processed (n becomes 0)
- Each iteration processes exactly one bit

## Algorithm Steps
1. Initialize counter as 0
2. While number > 0:
   - Check if current LSB is set using (n&1)
   - If set, increment counter
   - Right shift number to process next bit

## Complexity Analysis
- **Time Complexity:** O(log n) where n is the input number
- **Space Complexity:** O(1)
- **Justification:** Process each bit once, constant space

## Edge Cases Considered
- [x] Number 0 (no set bits)
- [x] Number 1 (single set bit)
- [x] Powers of 2 (single set bit)
- [x] All bits set (maximum count)
- [x] Negative numbers (not handled in current implementation)

## Solution Code

```java
// Language: Java
public static int count(int n){
    int count =0;
    while(n >0){
        if((n&1)!=0){
            count++;
        }
         n = n>>1;
    }
    return count;
}
```

## Alternative Approaches
- **Brian Kernighan's Algorithm:** n & (n-1) removes rightmost set bit
- **Built-in Function:** Integer.bitCount() in Java
- **Lookup Table:** Pre-computed counts for byte values

## Related Problems
- **AC:** [BitOperations.java - comprehensive bit manipulation]
- **Kunal:** [BitManipulation problems - similar bit operations]
- **LeetCode:** [Number of 1 Bits - Problem 191]

## Personal Notes
Fundamental bit manipulation operation. Understanding bit-level operations is crucial for optimization and system programming. The technique extends to more complex bit manipulation problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #bitManipulation #counting #binaryRepresentation #hammingWeight 