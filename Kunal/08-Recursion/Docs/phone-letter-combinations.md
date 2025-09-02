# Phone Number Letter Combinations

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Given digits 2–9, return all possible letter combinations based on the phone keypad mapping.

## Intuition/Approach
- Backtrack over digits; for each digit, append each mapped letter and recurse.
- Use a mapping array for O(1) lookups.

## Key Observations
- Digits 7 and 9 map to 4 letters; others map to 3.
- Empty input yields empty list.

## Algorithm Steps
1. If input empty, return empty list.
2. Recursively build combinations by consuming one digit per level.

## Complexity Analysis
- **Time Complexity:** O(3^N · 4^M)
- **Space Complexity:** O(3^N · 4^M)
- **Justification:** Number of results dominates; recursion depth equals digits length.

## Edge Cases Considered
- [x] Empty input
- [x] Single digit
- [x] Invalid digits (0/1 → map to empty)

## Solution Code

```java
import java.util.*;
class Solution {
    private static final String[] MAP = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> out = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return out;
        dfs(digits, 0, new StringBuilder(), out);
        return out;
    }

    private void dfs(String d, int idx, StringBuilder path, List<String> out) {
        if (idx == d.length()) { out.add(path.toString()); return; }
        String letters = MAP[d.charAt(idx) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(d, idx + 1, path, out);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```

## Alternative Approaches
- Iterative BFS using a queue to build strings level by level.

## Personal Notes
- StringBuilder reduces allocation cost vs string concatenation.

---
**Tags:** #backtracking #strings #keypad