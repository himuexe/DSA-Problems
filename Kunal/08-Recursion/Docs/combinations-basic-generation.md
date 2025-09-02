# Basic Combinations Generation

**Source:** Kunal | **Topic:** Recursion/Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Generate all combinations of k numbers chosen from the range [1, n]. Each combination must contain exactly k unique numbers in ascending order.

## Intuition/Approach
- Backtrack by choosing numbers in ascending order using a `start` index.
- Add when size reaches k; otherwise continue exploring.
- Backtrack after each recursive call.

## Key Observations
- Start index prevents duplicates and ensures ordering.
- Prune when remaining numbers are insufficient to complete size k.

## Algorithm Steps
1. If `comb.size()==k`, output a copy and return.
2. For `i` from `start` to `n`:
   - Add `i`, recurse with `i+1`, then remove last.

## Complexity Analysis
- **Time Complexity:** O(C(n,k))
- **Space Complexity:** O(C(n,k) × k)
- **Justification:** Generates all combinations; recursion depth ≤ k.

## Edge Cases Considered
- [x] k = 0
- [x] k = n
- [x] k > n
- [x] n = 1

## Solution Code

```java
import java.util.*;
class Solution {
    private void dfs(List<List<Integer>> out, List<Integer> comb, int n, int k, int start) {
        if (comb.size() == k) {
            out.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {
            comb.add(i);
            dfs(out, comb, n, k, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> out = new ArrayList<>();
        dfs(out, new ArrayList<>(), n, k, 1);
        return out;
    }
}
```

## Alternative Approaches
- Iterative generation in lexicographic order.
- Bitmask enumeration for small n.

## Personal Notes
- Early pruning: for i..n, ensure `(n - i + 1) >= (k - comb.size())`.

---
**Tags:** #recursion #backtracking #combinations