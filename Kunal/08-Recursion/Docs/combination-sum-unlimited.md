# Combination Sum - Unlimited Element Reuse

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Given a list of candidate numbers (no duplicates) and a target, find all unique combinations where candidates sum to target. A candidate may be used unlimited times.

## Intuition/Approach
- Backtrack with index `start` to avoid permuting the same combination.
- Reuse allowed: after including `candidates[i]`, recurse with the same `i`.
- Prune when running sum exceeds target (or use remaining-target style).

## Key Observations
- Sorting helps early break when candidate > remaining.
- Using remaining target simplifies comparisons and pruning.
- Solutions are combinations, not permutations; keep non-decreasing order via `start`.

## Algorithm Steps
1. If `rem == 0`, add a copy of current list to output and return.
2. For `i` from `start` to end:
   - If `candidates[i] > rem`, break (when sorted).
   - Add `candidates[i]`, recurse with `i` and `rem - candidates[i]`.
   - Backtrack by removing last.

## Complexity Analysis
- **Time Complexity:** Exponential in worst case (problem intrinsic)
- **Space Complexity:** O(T/min) recursion depth
- **Justification:** Branching on candidate choices; depth bounded by remaining/ min candidate.

## Edge Cases Considered
- [x] Target = 0 → empty combination
- [x] No solution cases
- [x] Single candidate equals target
- [x] Large target (pruning important)

## Solution Code

```java
import java.util.*;
class Solution {
    private void dfs(int[] nums, List<List<Integer>> out, List<Integer> comb, int rem, int start) {
        if (rem == 0) {
            out.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > rem) continue; // if sorted, could break
            comb.add(nums[i]);
            dfs(nums, out, comb, rem - nums[i], i);
            comb.remove(comb.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, out, new ArrayList<>(), target, 0);
        return out;
    }
}
```

## Alternative Approaches
- BFS over remaining target states.
- DP counting (number of ways) rather than listing combinations.

## Personal Notes
- Sort for better pruning and consistent output ordering.

---
**Tags:** #backtracking #combinations #unbounded