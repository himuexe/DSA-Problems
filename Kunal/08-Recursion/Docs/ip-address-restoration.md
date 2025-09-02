# IP Address Restoration - Backtracking

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Given a digit string, restore all possible valid IP addresses by inserting dots (four octets, each 0–255, no leading zeros except 0).

## Intuition/Approach
- Backtrack building four segments; at each step try lengths 1..3.
- Validate segment: non-empty, ≤ 3 digits, no leading zero unless single digit, value ≤ 255.
- When 4 segments built and all characters consumed, record the address.

## Key Observations
- Maximum length is 12 digits; minimum 4.
- Prune when remaining digits cannot fill remaining segments within 1..3 each.

## Algorithm Steps
1. If built 4 segments and consumed all digits, add path (trim trailing dot).
2. For len in 1..3, if within bounds and valid, append and recurse to next segment.

## Complexity Analysis
- **Time Complexity:** O(3^4) worst branching per segment (constant bound)
- **Space Complexity:** O(1) auxiliary; output proportional to solutions
- **Justification:** Depth limited to 4; try up to 3 lengths per segment.

## Edge Cases Considered
- [x] Length < 4 or > 12
- [x] Leading zeros like "01"
- [x] Values > 255
- [x] All zeros → "0.0.0.0"

## Solution Code

```java
import java.util.*;
class Solution {
    private boolean isValid(String s) {
        if (s.isEmpty() || s.length() > 3) return false;
        if (s.charAt(0) == '0' && s.length() > 1) return false;
        int v = Integer.parseInt(s);
        return v <= 255;
    }

    private void dfs(List<String> out, String s, String path, int seg, int start) {
        if (seg == 4) {
            if (start == s.length()) out.add(path.substring(0, path.length() - 1));
            return;
        }
        for (int i = start; i < s.length() && i < start + 3; i++) {
            String part = s.substring(start, i + 1);
            if (isValid(part)) dfs(out, s, path + part + '.', seg + 1, i + 1);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> out = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return out;
        dfs(out, s, "", 0, 0);
        return out;
    }
}
```

## Alternative Approaches
- Iterative four-loop over split positions (bounded to 1..3 each segment).
- StringBuilder for path to reduce allocations.

## Personal Notes
- Pre-checking length bounds prunes many impossible inputs immediately.

---
**Tags:** #backtracking #strings #validation