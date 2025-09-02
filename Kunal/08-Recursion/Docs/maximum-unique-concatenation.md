# Maximum Length Unique Character Concatenation

**Source:** Kunal | **Topic:** Backtracking/Bitmask | **Difficulty:** Medium  

---

## Problem Statement
From an array of strings, select a subset to concatenate such that all characters are unique; return the maximum possible length.

## Intuition/Approach
- Backtrack over strings deciding include/skip.
- Validate uniqueness before including by checking character occurrences.
- Bitmask representation (26-bit) speeds up uniqueness tests and unions.

## Key Observations
- Any string with internal duplicates can be discarded upfront.
- Using bitmasks enables O(1) merge and conflict checks via AND/OR.

## Algorithm Steps
1. Preprocess: convert each string to bitmask; drop invalid strings (duplicate chars).
2. DFS(index, usedMask, currLen):
   - Update global max with `currLen`.
   - For i from index..n-1:
     - If `(usedMask & mask[i]) == 0`, recurse with `usedMask | mask[i]` and length + len[i].

## Complexity Analysis
- **Time Complexity:** O(2^n) in worst case
- **Space Complexity:** O(n)
- **Justification:** Subset exploration; recursion depth ≤ n.

## Edge Cases Considered
- [x] Empty array → 0
- [x] Strings with internal duplicates
- [x] All strings mutually conflicting → 0 or max single length

## Solution Code

```java
import java.util.*;
class Solution {
    private int dfs(int[] masks, int[] lens, int idx, int used, int curr) {
        int best = curr;
        for (int i = idx; i < masks.length; i++) {
            if ((used & masks[i]) == 0) {
                best = Math.max(best, dfs(masks, lens, i + 1, used | masks[i], curr + lens[i]));
            }
        }
        return best;
    }

    public int maxLength(List<String> arr) {
        List<Integer> masksList = new ArrayList<>();
        List<Integer> lensList = new ArrayList<>();
        for (String s : arr) {
            int mask = 0; boolean ok = true;
            for (char ch : s.toCharArray()) {
                int bit = ch - 'a';
                if (bit < 0 || bit >= 26) { ok = false; break; }
                if (((mask >> bit) & 1) == 1) { ok = false; break; }
                mask |= 1 << bit;
            }
            if (ok) { masksList.add(mask); lensList.add(s.length()); }
        }
        int[] masks = masksList.stream().mapToInt(Integer::intValue).toArray();
        int[] lens = lensList.stream().mapToInt(Integer::intValue).toArray();
        return dfs(masks, lens, 0, 0, 0);
    }
}
```

## Alternative Approaches
- Pure string-based checking with boolean[26] per step (simpler, slower).
- Sort by length descending to explore promising branches first.

## Personal Notes
- Bitmasking drastically improves speed and makes pruning trivial.

---
**Tags:** #backtracking #bitmask #strings