# Reverse Bits

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Reverse the bits of a given 32-bit unsigned integer. For example, given input 43261596 (binary: 00000010100101000001111010011100), return 964176192 (binary: 00111001011110000010100101000000).

## Intuition/Approach
Build the result bit by bit by extracting the least significant bit from input and adding it to the most significant position of result. Use bit shifting to process each bit position.

## Key Observations
- Process each bit from right to left in input
- Build result from left to right
- Use AND operation with 1 to extract least significant bit
- Use OR operation to set bits in result
- Left shift result and right shift input to move to next bit

## Algorithm Steps
1. Initialize result (ans) to 0
2. For each of the 32 bit positions:
   - Left shift result by 1 to make room for next bit
   - Extract LSB from input using AND with 1
   - OR the extracted bit with result
   - Right shift input by 1 to process next bit
3. Return the reversed bit pattern

## Complexity Analysis
- **Time Complexity:** O(1) - constant 32 iterations for fixed bit width
- **Space Complexity:** O(1) - using only a few variables
- **Justification:** Fixed number of operations regardless of input value

## Edge Cases Considered
- [x] All zeros (result = 0)
- [x] All ones (result = all ones)
- [x] Single bit set (moves to opposite position)
- [x] Alternating pattern (gets reversed)
- [x] Maximum unsigned integer value

## Solution Code

```java
// Language: Java
class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for(int i = 0; i < 32; i++){
            ans <<= 1;
            ans = ans | (n & 1);
            n >>= 1;
        }
        return ans;
    }
}
```

## Alternative Approaches
- **Divide and Conquer:** Recursively swap bit groups (1-bit, 2-bit, 4-bit, etc.)
- **Lookup Table:** Pre-compute reversed values for bytes, combine results
- **Built-in Method:** Use Integer.reverse() or similar library functions

## Related Problems
- **AC:** [Bit Manipulation, Binary Representation]
- **Kunal:** [Hamming Distance, Power of Two, Complement Base 10]
- **LeetCode:** [190. Reverse Bits, 7. Reverse Integer, 191. Number of 1 Bits]

## Personal Notes
Excellent problem for understanding bit manipulation fundamentals. The technique of building result bit-by-bit while processing input is widely applicable. Important to understand the difference between logical and arithmetic right shifts when dealing with signed integers.

## Revision History
- **First Solve:** June 26, 2025 - Implemented iterative bit-by-bit reversal

---
**Tags:** #bitmanipulation #reverse #bits #binary #basics 