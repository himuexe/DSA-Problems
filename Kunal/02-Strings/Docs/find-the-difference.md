# Find the Difference

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Given two strings `s` and `t` where `t` is produced by shuffling `s` and adding one extra character, return the extra character.

## Intuition/Approach
Use XOR over all characters in both strings. Matched pairs cancel to 0; the remaining XOR is the extra character.

## Key Observations
- XOR cancels identical values: a ^ a = 0
- XOR with 0 yields the original value
- XOR is commutative/associative, order irrelevant

## Algorithm Steps
1. Initialize result = 0 (char)
2. XOR all characters of s into result
3. XOR all characters of t into result
4. Return result

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Linear scan; constant extra storage

## Edge Cases Considered
- [x] Empty s (t has one char)
- [x] Single character strings
- [x] All same characters with one extra
- [x] Mixed cases

## Solution Code

```java
class Solution {
    public char findTheDifference(String s, String t) {
        char result = 0;
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            result ^= t.charAt(i);
        }
        return result;
    }
}
```

## Alternative Approaches
- Frequency counting via array/HashMap
- Sort and compare
- Sum ASCII values (careful with overflow)

## Personal Notes
Classic XOR trick; also applies to single number and missing number problems.

---
**Tags:** #strings #xor #bitmanipulation