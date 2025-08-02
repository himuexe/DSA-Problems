# Bit Odd Even Check

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Determine if a number is odd or even using bit manipulation instead of modulo operation. Check the least significant bit to determine parity.

## Intuition/Approach
The least significant bit (LSB) determines if a number is odd or even. If LSB is 1, the number is odd; if LSB is 0, the number is even. Use bit masking to check LSB.

## Key Observations
- LSB of odd numbers is always 1
- LSB of even numbers is always 0
- Bit mask with 1 isolates the LSB
- AND operation with mask reveals LSB value
- More efficient than modulo operation

## Algorithm Steps
1. Create bit mask with value 1
2. AND the number with bit mask
3. If result is 1, number is odd
4. If result is 0, number is even

## Complexity Analysis
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)
- **Justification:** Single bitwise operation, constant time and space

## Edge Cases Considered
- [x] Number 0 (even)
- [x] Number 1 (odd)
- [x] Positive even numbers
- [x] Positive odd numbers
- [x] Large numbers (bit pattern remains consistent)

## Solution Code

```java
// Language: Java
public static void check(int n){
    int bitMask=1;
    if((n & bitMask) == 1){
        System.out.println("Odd number");
    }
    else{
        System.out.println("Even number");
    }
}

public static void main(String[] args){
    check(2);   // Even
    check(15);  // Odd
    check(20);  // Even
}
```

## Alternative Approaches
- **Modulo Operation:** n % 2 == 0 (traditional approach)
- **Division Check:** n / 2 * 2 == n (less efficient)
- **Bit Shift:** Compare n with (n >> 1) << 1

## Personal Notes
Simple but effective bit manipulation technique. Demonstrates how bit operations can replace arithmetic operations for better performance. The concept extends to more complex bit manipulation algorithms.

---
**Tags:** #basics #bitManipulation #oddEven #parity #optimization 