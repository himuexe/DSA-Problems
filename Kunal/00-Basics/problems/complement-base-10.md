# Complement Base 10 (Hamming Distance)

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Calculate the Hamming distance between two integers. The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

## Intuition/Approach
Use XOR operation to find differing bits, then count the number of set bits in the result. XOR gives 1 where bits differ and 0 where they match.

## Key Observations
- XOR operation highlights differing bits between two numbers
- Bit manipulation with mask to check each position
- Loop through all 32 bits of integer representation
- Use bit shifting to move mask position

## Algorithm Steps
1. XOR the two input numbers to get differing bits
2. Initialize a mask with value 1 and counter to 0
3. For each of the 32 bit positions:
   - Check if current bit is set using AND operation with mask
   - If set, increment counter
   - Left shift mask to next position
4. Return the total count

## Complexity Analysis
- **Time Complexity:** O(32) = O(1) - constant time for 32-bit integers
- **Space Complexity:** O(1) - only using constant extra space
- **Justification:** Fixed number of iterations regardless of input values

## Edge Cases Considered
- [x] Same numbers (distance = 0)
- [x] Maximum difference (all bits different)
- [x] Negative numbers (treated as 32-bit signed integers)
- [x] Zero values
- [x] Single bit difference

## Solution Code

```java
// Language: Java
class Solution {
    public int hammingDistance(int x, int y) {
        int count = 0;
        int xor = x ^ y;
        int mask = 1;
        for(int i = 1; i < 32; i++){
            if((xor & mask) != 0){
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}
```

## Alternative Approaches
- **Built-in Function:** Use Integer.bitCount(x ^ y) for direct bit counting
- **Brian Kernighan's Algorithm:** Use n & (n-1) to clear rightmost set bit
- **Recursive Approach:** Recursively count bits by checking LSB and right shifting

## Related Problems
- **AC:** [Bit Operations, Count Set Bits]
- **Kunal:** [Single Number XOR, Power of Two Check]
- **LeetCode:** [461. Hamming Distance, 477. Total Hamming Distance]

## Personal Notes
Classic bit manipulation problem demonstrating practical use of XOR operation. The algorithm efficiently counts differing bits without converting to binary strings. Important for understanding bitwise operations and their applications.

## Revision History
- **First Solve:** June 26, 2025 - Initial implementation with bit masking approach

---
**Tags:** #bitmanipulation #hamming #xor #basics #mathematics 