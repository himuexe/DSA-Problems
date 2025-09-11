# Maximum Length of Pair Chain

**Source:** AC | **Topic:** Greedy | **Difficulty:** Easy  

---

## Problem Statement
Given a list of pairs `(a, b)` where `a < b`, find the length of the longest chain that can be formed such that for each consecutive pair `(a_i, b_i)` and `(a_{i+1}, b_{i+1})`, we have `b_i < a_{i+1}`.

## Intuition/Approach
This is analogous to activity selection: sort pairs by their end value and greedily pick the next pair whose start is greater than the current chain end.

## Key Observations
- Sorting by end times enables local optimal choices to be globally optimal.
- Maintain the current chain end and count selections.
- Equivalent to selecting maximum non-overlapping intervals.

## Algorithm Steps
1. Sort `pairs` ascending by `pair[1]` (end).
2. Initialize `chainLen = 1`, `chainEnd = pairs[0][1]`.
3. For each subsequent pair:
   - If `pairs[i][0] > chainEnd`, take it, increment `chainLen`, update `chainEnd`.
4. Return `chainLen`.

## Complexity Analysis
- **Time Complexity:** O(N log N)
- **Space Complexity:** O(1) auxiliary
- **Justification:** Sorting dominates; single pass selection.

## Edge Cases Considered
- [x] Single pair
- [ ] Empty input (handle by returning 0)
- [ ] Equal ends/starts
- [ ] Large N
- [ ] Other: Negative values

## Solution Code

```java
import java.util.*;

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(p -> p[1]));
        int chainLen = 1;
        int chainEnd = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {
                chainLen++;
                chainEnd = pairs[i][1];
            }
        }
        return chainLen;
    }
}
```

## Alternative Approaches
- DP similar to LIS on pairs sorted by start can also solve but is O(N^2).

## Personal Notes
Identical pattern to interval scheduling/activity selection.

---
**Tags:** #greedy #interval-scheduling #sorting
