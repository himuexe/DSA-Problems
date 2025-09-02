# Partition K Equal Sum Subsets

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Hard  

---

## Problem Statement
Given an integer array nums and an integer k, determine if you can partition nums into k non-empty subsets with equal sum.

## Intuition/Approach
- Total sum must be divisible by k; target = sum/k.
- Backtrack assigning elements to the current bucket until target is reached, then move to next bucket.
- Track visited elements to avoid reuse; sort descending to prune early.

## Key Observations
- If any element > target, impossible.
- Symmetry: filling buckets in identical states should be avoided (prune duplicates).

## Algorithm Steps
1. Compute sum; if sum % k != 0, return false; target = sum/k; sort nums desc.
2. DFS over indices from high to low with `visited[]`, `currSum`, and remaining `k`.
3. When `currSum == target`, recurse with `k-1`, `currSum = 0` and restart index.
4. Skip duplicates and numbers that exceed remaining capacity.

## Complexity Analysis
- **Time Complexity:** Exponential, ~O(k · 2^n) worst-case
- **Space Complexity:** O(n)
- **Justification:** Subset search with pruning; visited array of size n.

## Edge Cases Considered
- [x] k = 1
- [x] k > n
- [x] sum % k != 0
- [x] max(nums) > target

## Solution Code

```java
import java.util.*;
class Solution {
    private boolean dfs(int[] nums, boolean[] used, int k, int target, int curr, int start) {
        if (k == 0) return true;
        if (curr == target) return dfs(nums, used, k - 1, target, 0, 0);
        for (int i = start; i < nums.length; i++) {
            if (used[i]) continue;
            if (curr + nums[i] > target) continue;
            used[i] = true;
            if (dfs(nums, used, k, target, curr + nums[i], i + 1)) return true;
            used[i] = false;
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++; // skip dups
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0; for (int v : nums) sum += v;
        if (k <= 0 || sum % k != 0) return false;
        int target = sum / k;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > target) return false;
        }
        boolean[] used = new boolean[nums.length];
        return dfs(nums, used, k, target, 0, 0);
    }
}
```

## Alternative Approaches
- Bitmask DP on subsets with memoization (state = bitmask, bucket sum mod target).

## Personal Notes
- Sorting descending and early checks make a big practical difference.

---
**Tags:** #backtracking #partition #subset