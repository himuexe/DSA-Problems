# Combination Sum III - Limited Count and Range

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Medium  

---

## Problem Statement
Find all combinations of k numbers from 1..9 that sum to n. Each number may be used at most once in a combination.

## Intuition/Approach
- Backtracking over the range [1..9] with ascending index to avoid duplicates.
- Track remaining sum and current size; prune when size exceeds k or remaining < 0.

## Key Observations
- Fixed domain (1..9) and exact size k.
- Dual constraints: count and sum.
- Early pruning using arithmetic bounds improves performance.

## Algorithm Steps
1. If `current.size()==k && remaining==0`, record combination.
2. If `current.size()>=k || remaining<=0`, return.
3. For `i` from `start` to 9: add `i`, recurse with `i+1` and `remaining-i`, then backtrack.

## Complexity Analysis
- **Time Complexity:** O(C(9,k))
- **Space Complexity:** O(k)
- **Justification:** Depth limited by k; combinations bounded by domain size.

## Edge Cases Considered
- [x] k > 9
- [x] n out of feasible min/max range
- [x] k = 1 with n in [1,9]
- [x] k = 0 with n = 0
- [x] Early termination on negative remaining

## Solution Code

```java
import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int k, int rem, int start) {
        if (curr.size() == k && rem == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if (curr.size() >= k || rem <= 0) return;
        for (int i = start; i <= 9; i++) {
            curr.add(i);
            backtrack(result, curr, k, rem - i, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
```

## Alternative Approaches
- Pre-check bounds: min sum k(k+1)/2; max sum k(19-k)/2.
- Bitmask enumeration for very small k.

## Personal Notes
- Bound checks skip hopeless cases early and save recursion steps.

---
**Tags:** #backtracking #combinations #pruning