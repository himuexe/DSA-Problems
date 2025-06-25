# Plus One

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** Array Manipulation/Math

## Problem Statement

You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading zeros.

Increment the large integer by one and return the resulting array of digits.

**Example:**
```
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123. Incrementing by one gives 123 + 1 = 124.

Input: digits = [9,9,9]
Output: [1,0,0,0]
Explanation: The array represents the integer 999. Incrementing by one gives 999 + 1 = 1000.
```

## Intuition/Approach

**Carry Propagation Algorithm:** Simulate manual addition with carry handling
- **Key Insight:** Start from the rightmost digit and propagate carry leftward
- **Carry logic:** If digit + carry ≥ 10, set digit = (digit+carry) % 10 and carry = 1
- **Special case:** If carry remains 1 after processing all digits, create new array

**Algorithm Logic:**
1. Initialize `carry = 1` (representing the +1 operation)
2. Process digits from right to left
3. For each digit: `current = digit + carry`
4. If `current ≥ 10`: set `carry = 1`, `digit = current % 10`
5. If `current < 10`: set `carry = 0`, `digit = current`
6. If carry remains 1 after all digits, create new array with leading 1

## Algorithm Steps

1. **Initialize:** `carry = 1` to represent the increment operation
2. **Right-to-left processing:** For `i` from `digits.length-1` to 0:
   - **Calculate:** `curr = digits[i] + carry`
   - **Handle carry:** If `curr ≥ 10`, set `carry = 1` and `digits[i] = curr % 10`
   - **No carry:** If `curr < 10`, set `carry = 0` and `digits[i] = curr`
3. **Final carry check:** If `carry == 1`, create new array with leading 1

## Key Observations

- **Carry propagation:** Each digit addition may generate carry for next position
- **In-place modification:** Original array is modified to save space
- **Special case handling:** All 9s require array expansion
- **Early termination:** If no carry, remaining digits unchanged
- **Mathematical simulation:** Mirrors manual addition process

## Time & Space Complexity

- **Time Complexity:** O(n) - Single pass through array
- **Space Complexity:** O(1) extra space, O(n) if new array needed for all 9s case

## Edge Cases Considered

- [x] Single digit (0-8): increment normally
- [x] Single digit 9: becomes [1,0]
- [x] Multiple digits ending in 9: carry propagation
- [x] All digits are 9: create new array [1,0,0,...,0]
- [x] No carry needed: early termination possible

## Solution Code

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        
        // Process digits from right to left
        for(int i = digits.length - 1; i >= 0; i--) {
            int curr = digits[i] + carry;
            
            if(curr >= 10) {
                carry = 1;
                digits[i] = curr % 10;
            } else {
                carry = 0;
                digits[i] = curr;
            }
        }
        
        // If carry remains, create new array with leading 1
        if(carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            // Rest of array is already initialized to 0
            return newDigits;
        }
        
        return digits;  // Return modified original array
    }
}
```

## Alternative Approaches

1. **Optimized Early Return:**
```java
public int[] plusOne(int[] digits) {
    for(int i = digits.length - 1; i >= 0; i--) {
        if(digits[i] < 9) {
            digits[i]++;
            return digits;  // No carry needed
        }
        digits[i] = 0;  // Carry forward
    }
    
    // All digits were 9
    int[] newDigits = new int[digits.length + 1];
    newDigits[0] = 1;
    return newDigits;
}
```

2. **Recursive Approach:**
```java
public int[] plusOneRecursive(int[] digits, int index, int carry) {
    if(index < 0) {
        if(carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
    
    int sum = digits[index] + carry;
    digits[index] = sum % 10;
    return plusOneRecursive(digits, index - 1, sum / 10);
}
```

## Mathematical Analysis

### Carry Propagation Pattern
- **No carry:** Most common case, O(1) operations
- **Single carry:** Affects one additional digit
- **Multiple carries:** Cascade effect (e.g., 199 + 1 = 200)
- **All carries:** Worst case, all digits are 9

### Frequency Analysis
For uniformly random digits:
- **Probability of carry:** 1/10 per digit
- **Expected carry length:** Geometric distribution
- **Worst case frequency:** Very low for large numbers

## Key Learnings

1. **Carry simulation:** Mathematical operations can be simulated with arrays
2. **Right-to-left processing:** Natural order for arithmetic operations
3. **Array expansion:** Handle overflow by creating larger array
4. **Early termination:** Optimize for common case (no carry)
5. **In-place modification:** Space-efficient when possible

## Related Problems

- Add Two Numbers (linked list representation)
- Multiply Strings
- Add Binary
- Add to Array-Form of Integer
- Increment Submatrices by One

**LeetCode Connection:** LeetCode #66 - Plus One 