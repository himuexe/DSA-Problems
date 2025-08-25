# Needle in Haystack

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Find the index of the first occurrence of `needle` in `haystack`. Return -1 if not found.

## Intuition/Approach
Brute-force sliding window: compare each length-m substring of `haystack` against `needle`.

## Key Observations
- Only n-m+1 starting positions are valid
- Early return on first match
- Empty needle conventionally returns 0

## Algorithm Steps
1. If needle empty, return 0
2. If n < m, return -1
3. For i in [0..n-m]:
   - If haystack.substring(i, i+m).equals(needle) return i
4. Return -1

## Complexity Analysis
- **Time Complexity:** O(n·m)
- **Space Complexity:** O(m)
- **Justification:** Substring allocation per window; alternatives can avoid it

## Edge Cases Considered
- [x] Empty needle
- [x] Haystack shorter than needle
- [x] Equal strings
- [x] Not found
- [x] Multiple occurrences (return first)

## Solution Code

```java
public int strStr(String haystack, String needle) {
    if (needle.isEmpty()) {
        return 0;
    }
    int n = haystack.length();
    int m = needle.length();
    if (n < m) {
        return -1;
    }
    for (int i = 0; i <= n - m; i++) {
        String str = haystack.substring(i, i + m);
        if (str.equals(needle)) {
            return i;
        }
    }
    return -1;
}
```

## Alternative Approaches
- Built-in indexOf
- Character-by-character compare to avoid substring allocations
- KMP / Boyer-Moore / Rabin-Karp

## Personal Notes
- For large inputs prefer KMP or indexOf; this is acceptable for small cases

---
**Tags:** #strings #patternmatching #bruteforce