# Longest Common Prefix

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Find the longest common prefix among an array of strings. If none, return "".

## Intuition/Approach
Horizontal scanning: keep a candidate prefix and shrink it until it is a prefix of each string in turn.

## Key Observations
- Common prefix must be a prefix of every string
- Shrinking strategy guarantees correctness
- Early exit if prefix becomes empty

## Algorithm Steps
1. If array empty, return ""
2. Set prefix = first string
3. For each next string:
   - While current string does not start with prefix, drop last char of prefix
   - If prefix becomes empty, return ""
4. Return prefix

## Complexity Analysis
- **Time Complexity:** O(S) where S is total chars
- **Space Complexity:** O(1)
- **Justification:** In-place prefix shrinking over input strings

## Edge Cases Considered
- [x] Empty array
- [x] Single string
- [x] Empty string present
- [x] No common prefix

## Solution Code

```java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) {
                return "";
            }
        }
    }
    return prefix;
}
```

## Alternative Approaches
- Vertical scanning
- Divide and conquer
- Binary search on prefix length
- Trie-based approach

## Personal Notes
- Practical and simple; replaceable with more advanced methods if needed.

---
**Tags:** #strings #prefix #scanning