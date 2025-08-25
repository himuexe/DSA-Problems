# Is Subsequence

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Given strings `s` and `t`, return true if `s` is a subsequence of `t` (characters of `s` appear in order within `t`).

## Intuition/Approach
Two pointers: advance both when characters match; otherwise advance only the `t` pointer. Success when the `s` pointer reaches the end.

## Key Observations
- Subsequence preserves order but allows gaps
- Greedy first-match works; no backtracking required
- Single linear scan is sufficient

## Algorithm Steps
1. Set p1 = 0 over s, p2 = 0 over t
2. While p1 < |s| and p2 < |t|:
   - If s[p1] == t[p2], increment both
   - Else increment p2
3. Return p1 == |s|

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass over t with constant extra variables

## Edge Cases Considered
- [x] Empty s (true)
- [x] Empty t (true only if s empty)
- [x] s longer than t (false)
- [x] Identical strings (true)
- [x] No matching characters (false)

## Solution Code

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int p1 = 0; 
        int p2 = 0;
        while(p1 < s.length() && p2 < t.length()){
            if(s.charAt(p1) == t.charAt(p2)){
                p1++;
                p2++;
            }
            else{
                p2++;
            }
        }
        return p1 == s.length();
    }
}
```

## Alternative Approaches
- Recursive matching (inefficient)
- DP (LCS-based) for batched queries
- indexOf with moving start

## Personal Notes
Common pre-check in many string DP problems; lightweight and effective.

---
**Tags:** #strings #twopointers #subsequence