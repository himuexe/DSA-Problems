# Reverse String

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Reverse the input character array `s` in-place with O(1) extra memory.

## Intuition/Approach
Two-pointers swap from both ends and move inward until they meet.

## Key Observations
- In-place; no extra array required
- Each position swapped at most once
- n/2 swaps for length n

## Algorithm Steps
1. left = 0, right = n-1
2. While left < right: swap s[left], s[right]; left++, right--
3. End

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Constant work per swap; no extra storage

## Edge Cases Considered
- [x] Empty array
- [x] Single character
- [x] Two characters
- [x] Odd length retains middle unchanged

## Solution Code

```java
public void reverseString(char[] s) {
    int left = 0;
    int right = s.length - 1;
    while (left < right) {
        char temp = s[right];
        s[right] = s[left];
        s[left] = temp;
        left++;
        right--;
    }
}
```

## Alternative Approaches
- Recursion
- Stack-based reverse
- XOR swapping

## Personal Notes
- Template two-pointer pattern; building block for many string tasks

---
**Tags:** #strings #twopointers #inplace