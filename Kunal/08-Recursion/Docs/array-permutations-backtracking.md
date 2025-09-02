# Array Permutations Generation - Backtracking

**Source:** Kunal | **Topic:** Recursion/Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Generate all permutations of a given integer array using recursive backtracking.

## Intuition/Approach
- Depth-first search builds permutations incrementally.
- At each step, try adding any element not already used in the current permutation.
- Backtrack by removing the last element and trying the next option.

## Key Observations
- Classic include/exclude backtracking pattern.
- Track usage to avoid repeating elements in a permutation.
- Sorting the input provides consistent output order but is not required.

## Algorithm Steps
1. If `perm.size() == nums.length`, add a copy of `perm` to results and return.
2. Loop over indices; if element not used, add to `perm` and recurse.
3. Remove last element (backtrack) and continue.

## Complexity Analysis
- **Time Complexity:** O(n! × n)
- **Space Complexity:** O(n! × n)
- **Justification:** n! permutations; O(n) to copy/store each; recursion depth n.

## Edge Cases Considered
- [x] Empty array
- [x] Single element
- [x] Duplicate elements (ordering/handling)
- [x] Large n (factorial blow-up)

## Solution Code

```java
import java.util.*;
class Solution {
    private void dfs(int[] nums, List<List<Integer>> out, List<Integer> perm) {
        if (perm.size() == nums.length) {
            out.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (perm.contains(nums[i])) continue;
            perm.add(nums[i]);
            dfs(nums, out, perm);
            perm.remove(perm.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        dfs(nums, out, new ArrayList<>());
        return out;
    }
}
```

## Alternative Approaches
- Use a boolean `used[]` array for O(1) membership checks.
- Generate permutations in-place by swapping (Heap's algorithm).
- Iterative next-permutation to enumerate in lexicographic order.

## Personal Notes
- Prefer `used[]` over `contains()` for performance on large n.

---
**Tags:** #recursion #backtracking #permutations