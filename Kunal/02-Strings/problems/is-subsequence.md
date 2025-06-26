# Is Subsequence

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given two strings `s` and `t`, return true if `s` is a subsequence of `t`, or false otherwise. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

## Intuition/Approach
Use two-pointer technique to track progress in both strings. Advance first pointer only when characters match, always advance second pointer. If first pointer reaches end of `s`, then `s` is a subsequence of `t`.

## Key Observations
- Subsequence maintains relative order but allows gaps
- Two pointers can track matching progress efficiently
- Only need to match all characters of `s` in order within `t`
- Greedy matching works: match first occurrence of each character
- No backtracking needed due to subsequence property

## Algorithm Steps
1. Initialize two pointers: p1 for string `s`, p2 for string `t`
2. While both pointers are within bounds:
   - If characters match: advance both pointers
   - If characters don't match: advance only p2
3. Return true if p1 reached end of `s` (all characters matched)

## Complexity Analysis
- **Time Complexity:** O(n) - single pass through string `t`
- **Space Complexity:** O(1) - only using two pointer variables
- **Justification:** Linear scan with constant space two-pointer technique

## Edge Cases Considered
- [x] Empty string s (always subsequence)
- [x] Empty string t (subsequence only if s is empty)
- [x] s longer than t (impossible subsequence)
- [x] Identical strings (perfect subsequence)
- [x] Single character strings
- [x] No matching characters (not subsequence)

## Solution Code

```java
// Language: Java
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
- **Recursive:** Recursive matching with substring calls O(n²) worst case
- **Dynamic Programming:** 2D DP table for longest common subsequence O(mn) space
- **Built-in Methods:** Using indexOf with starting position tracking

## Related Problems
- **AC:** [Longest Common Subsequence, String Matching, Two Pointers]
- **Kunal:** [Valid Anagram, Longest Common Prefix, String Comparison]
- **LeetCode:** [392. Is Subsequence, 115. Distinct Subsequences, 1143. Longest Common Subsequence]

## Personal Notes
Classic two-pointer problem demonstrating greedy matching strategy. The key insight is that for subsequences, we only need to find characters in order, not consecutively. This approach is both intuitive and optimal.

## Revision History
- **First Solve:** June 26, 2025 - Implemented two-pointer greedy approach

---
**Tags:** #strings #twopointers #subsequence #greedy #matching 