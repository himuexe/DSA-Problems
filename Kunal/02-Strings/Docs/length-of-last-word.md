# Length of Last Word

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Given a string `s` with words and spaces, return the length of the last word (maximal substring of non-space characters).

## Intuition/Approach
Scan from the end: skip trailing spaces, then count characters until the next space or start of string.

## Key Observations
- No need to split/trim whole string
- Naturally handles multiple/trailing spaces
- Single reverse pass is sufficient

## Algorithm Steps
1. Set i = s.length() - 1
2. While i >= 0 and s[i] == ' ', i--
3. Initialize length = 0; while i >= 0 and s[i] != ' ', length++, i--
4. Return length

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single reverse scan; constant variables

## Edge Cases Considered
- [x] Trailing spaces
- [x] Single word
- [x] Multiple spaces between words
- [x] Only spaces
- [x] Empty string
- [x] Single character

## Solution Code

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        return length;
    }
}
```

## Alternative Approaches
- Split and take last word length
- Trim and use lastIndexOf(' ')
- Regex to capture last word

## Personal Notes
- Straightforward reverse scan avoids extra allocations

---
**Tags:** #strings #twopointers #parsing