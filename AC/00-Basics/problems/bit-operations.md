# Bit Operations

**Source:** AC | **Topic:** Basics | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Implement fundamental bit manipulation operations: get ith bit, set ith bit, clear ith bit, clear last i bits, and clear bits in range [i,j].

## Intuition/Approach
Use bit masks to manipulate individual bits or bit ranges. Each operation uses specific bit manipulation patterns with AND, OR, and NOT operations.

## Key Observations
- Bit mask (1<<i) isolates bit at position i
- AND operation with mask checks/clears bits
- OR operation with mask sets bits
- NOT operation flips mask bits
- Combining masks enables range operations

## Algorithm Steps

### Get ith Bit:
1. Create mask with bit set at position i
2. AND with original number
3. Return 1 if non-zero, 0 otherwise

### Set ith Bit:
1. Create mask with bit set at position i
2. OR with original number

### Clear ith Bit:
1. Create inverted mask with 0 at position i
2. AND with original number

### Clear Last i Bits:
1. Create mask with all bits set from position i onwards
2. AND with original number

### Clear Bits in Range [i,j]:
1. Create mask with 0s in range [i,j]
2. Combine left and right masks using OR
3. AND with original number

## Complexity Analysis
- **Time Complexity:** O(1) for all operations
- **Space Complexity:** O(1)
- **Justification:** Direct bit manipulation, constant time and space

## Edge Cases Considered
- [x] Position 0 (least significant bit)
- [x] Position at word boundary
- [x] Clearing no bits (i=0)
- [x] Range operations with same i,j
- [x] Full word operations

## Solution Code

```java
// Language: Java
public static int getIthBit(int n, int i){
    int bitMask = 1<<i;
    if((n&bitMask)==0){
        return 0;
    }else{
        return 1;
    }
}

public static int setIthBit(int n, int i){
    int bitMask = 1<<i;
    return n | bitMask;
}

public static int clearIthBit(int n , int i){
    int bitMask = ~(1<<i);
    return n & bitMask;
}

public static int clearIBits(int n, int i){
    int bitMask = (~0)<<i;
    return n & bitMask;
}

public static int clearIBits(int n ,int i, int j ){
    int a =((~0)<<(j+1));
    int b=(1<<i)-1;
    int bitMask = a|b;
    return n & bitMask;
}
```

## Alternative Approaches
- **Library Functions:** Built-in bitwise operations in various languages
- **Bit Manipulation Libraries:** Specialized libraries for complex operations
- **Hardware Instructions:** Direct CPU bit manipulation instructions

## Related Problems
- **AC:** [FastExp.java - uses bit manipulation for exponentiation]
- **Kunal:** [BitManipulation problems - comprehensive bit operations]
- **LeetCode:** [Single Number series - various bit manipulation problems]

## Personal Notes
Comprehensive bit manipulation toolkit. These operations are building blocks for more complex algorithms like bit vectors, hash functions, and compression algorithms. Understanding bit masks is essential for system programming.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #bitManipulation #bitMasks #bitOperations #systemProgramming 