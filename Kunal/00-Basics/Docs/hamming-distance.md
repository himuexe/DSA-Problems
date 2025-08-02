# Hamming Distance

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Calculate the Hamming distance between two integers x and y. The Hamming distance is the number of positions at which the corresponding bits are different.

## Intuition/Approach
XOR the two numbers to highlight positions where bits differ, then count the number of 1s in the result using bit manipulation with a shifting mask.

## Key Observations
- XOR operation produces 1 where bits differ, 0 where they match
- Need to check each bit position in 32-bit integer representation
- Use mask with AND operation to isolate each bit
- Left shift mask to move through bit positions systematically

## Algorithm Steps
1. Compute XOR of input numbers to get differing bits
2. Initialize count to 0 and mask to 1
3. Iterate through bit positions (1 to 31):
   - Use bitwise AND to check if current bit is set
   - If set, increment count
   - Left shift mask to next position
4. Return total count of differing bits

## Complexity Analysis
- **Time Complexity:** O(1) - constant 32 iterations for integer bits
- **Space Complexity:** O(1) - using only a few variables
- **Justification:** Fixed loop iterations independent of input values

## Edge Cases Considered
- [x] Identical numbers (result = 0)
- [x] Numbers with maximum bit difference
- [x] One number is zero
- [x] Negative numbers (32-bit signed representation)
- [x] Single bit flip scenarios

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
- **Integer.bitCount():** Built-in Java method to count set bits
- **Brian Kernighan's Algorithm:** Use xor &= (xor - 1) to clear rightmost set bit
- **Lookup Table:** Pre-compute bit counts for bytes, combine results

## Personal Notes
Fundamental bit manipulation problem that demonstrates practical application of XOR operation. The mask-shifting technique is a common pattern in bit manipulation problems. Understanding this concept is essential for more complex bitwise algorithms.

---
**Tags:** #bitmanipulation #hamming #xor #counting #basics 