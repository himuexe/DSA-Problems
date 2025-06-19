# Single Number (XOR Approach)

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a non-empty array of integers where every element appears twice except for one, find that single element that appears only once.

## Intuition/Approach
Use XOR operation properties: a ^ a = 0 and a ^ 0 = a. XOR all elements together - the duplicates will cancel out, leaving only the single element.

## Key Observations
- XOR operation: a ^ a = 0 (identical numbers cancel out)
- XOR operation: a ^ 0 = a (XOR with 0 gives original number)  
- XOR is commutative and associative
- All pairs will cancel out, leaving only the single element

## Algorithm Steps
1. Initialize result with first array element
2. XOR result with each remaining element in the array
3. All duplicate pairs will cancel out (become 0)
4. Return the final result (the single element)

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Array with single element (no duplicates)
- [x] Array with multiple pairs and one single
- [x] Different ordering of elements
- [x] Negative numbers (XOR works with all integers)
- [x] Large arrays

## Solution Code

```java
// Language: Java
public static int singleNumber(int[] nums) {
    int ans = nums[0];
    for(int i = 1; i < nums.length; i++){
        ans = ans ^ nums[i];
    }
    return ans;
}
```

## Alternative Approaches
- **HashSet:** Add/remove elements, remaining element is the answer (O(n) space)
- **HashMap:** Count occurrences, find element with count 1 (O(n) space)
- **Sorting:** Sort array, check adjacent pairs (O(n log n) time)

## Related Problems
- **AC:** [OddEven.java - basic bit manipulation concepts]
- **Kunal:** [FizzBuzz.java - array processing patterns]
- **LeetCode:** [Single Number - Problem 136]

## Personal Notes
Elegant bit manipulation solution. XOR properties make this optimal in both time and space. Great introduction to bitwise operations and their practical applications.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #bitManipulation #xor #arrays #optimization 