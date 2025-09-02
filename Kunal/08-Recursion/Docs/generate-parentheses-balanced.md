# Generate Balanced Parentheses

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Generate all well-formed parentheses strings with n pairs.

## Intuition/Approach
- Backtracking with counts of open and close used.
- Add '(' if open < n; add ')' if close < open to maintain balance.
- When length reaches 2n, record the string.

## Key Observations
- Validity invariant: close ≤ open at all times.
- Exactly 2n characters in each output.
- Count equals the nth Catalan number.

## Algorithm Steps
1. If length == 2n, add current string to results.
2. If open < n, recurse with '(' and open+1.
3. If close < open, recurse with ')' and close+1.

## Complexity Analysis
- **Time Complexity:** O(Cn) ≈ O(4^n / n^{3/2})
- **Space Complexity:** O(Cn)
- **Justification:** Number of outputs dominates; recursion depth up to 2n.

## Edge Cases Considered
- [x] n = 0
- [x] n = 1
- [x] Large n (output explosion)

## Solution Code

```java
import java.util.*;
class Solution {
    private void dfs(List<String> out, int n, String curr, int open, int close) {
        if (curr.length() == 2 * n) { out.add(curr); return; }
        if (open < n) dfs(out, n, curr + "(", open + 1, close);
        if (close < open) dfs(out, n, curr + ")", open, close + 1);
    }
    public List<String> generateParenthesis(int n) {
        List<String> out = new ArrayList<>();
        dfs(out, n, "", 0, 0);
        return out;
    }
}
```

## Alternative Approaches
- Use StringBuilder for fewer allocations.
- DP by constructing sets for k from 0..n via Catalan recurrence.

## Personal Notes
- Great example of constraint-guarded generation with minimal pruning logic.

---
**Tags:** #backtracking #catalan #strings