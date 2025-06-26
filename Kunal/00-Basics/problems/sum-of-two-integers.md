# Sum of Two Integers

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Calculate the sum of two integers a and b without using the + and - operators. You must implement addition using only bitwise operations.

## Intuition/Approach
Use XOR to get sum without carry, AND to get carry positions, then recursively add the carry to the sum until no carry remains. This mimics binary addition at the bit level.

## Key Observations
- XOR gives sum of bits without considering carry
- AND gives positions where carry is generated
- Carry must be left-shifted before adding to next iteration
- Recursion continues until carry becomes zero
- Base case: when carry is 0, return the XOR result

## Algorithm Steps
1. Calculate XOR of a and b (sum without carry)
2. Calculate AND of a and b (carry positions)
3. If carry is 0, return the XOR result
4. Otherwise, recursively call getSum with:
   - First parameter: XOR result
   - Second parameter: carry left-shifted by 1
5. Continue until carry becomes 0

## Complexity Analysis
- **Time Complexity:** O(log n) - maximum 32 iterations for 32-bit integers
- **Space Complexity:** O(log n) - recursive call stack depth
- **Justification:** Each iteration reduces the number of carry bits

## Edge Cases Considered
- [x] Both numbers positive
- [x] Both numbers negative
- [x] One positive, one negative
- [x] Zero values (a=0 or b=0)
- [x] Overflow scenarios

## Solution Code

```java
// Language: Java
class Solution {
    public int getSum(int a, int b) {
        int xor = a ^ b;
        int carry = a & b;
        if(carry == 0){
            return xor;
        }
        else{
            return getSum(xor, carry << 1);
        }
    }
}
```

## Alternative Approaches
- **Iterative Version:** Use while loop instead of recursion to avoid stack overhead
- **Full Adder Logic:** Implement using multiple XOR and AND operations
- **Bit-by-bit Addition:** Process each bit position individually

## Related Problems
- **AC:** [Binary Addition, Bit Manipulation]
- **Kunal:** [XOR Operations, Hamming Distance, Reverse Bits]
- **LeetCode:** [371. Sum of Two Integers, 2. Add Two Numbers, 67. Add Binary]

## Personal Notes
Fundamental problem demonstrating how computer arithmetic works at the hardware level. The recursive approach elegantly handles carry propagation. Understanding this concept is crucial for low-level programming and computer architecture knowledge.

## Revision History
- **First Solve:** June 26, 2025 - Implemented recursive bitwise addition

---
**Tags:** #bitmanipulation #addition #recursion #carry #basics 