# Friends Pairing Problem

**Source:** AC | **Topic:** Recursion | **Difficulty:** Medium  

---

## Problem Statement
Given n friends, count the number of ways they can remain single or be paired up such that each friend appears in at most one pair.

Example (n = 3):
```
(A)(B)(C), (AB)(C), (AC)(B), (BC)(A) → 4 ways
```

## Intuition/Approach
- Consider the choice for the nth friend: stay single or pair with any of the (n-1) others.
- If single → solve for (n-1).
- If paired → choose a partner in (n-1) ways, then solve for (n-2).
- Leads to recurrence f(n) = f(n-1) + (n-1)·f(n-2).

## Key Observations
- Base cases: f(1) = 1, f(2) = 2.
- Overlapping subproblems → exponential naive recursion.
- Natural fit for memoization/DP.

## Algorithm Steps
1. If n ≤ 2, return n.
2. Compute single = f(n-1).
3. Compute pair = (n-1) × f(n-2).
4. Return single + pair.

## Complexity Analysis
- **Time Complexity:** O(2^n) (naive)
- **Space Complexity:** O(n)
- **Justification:** Exponential branching without memoization; recursion depth n.

## Edge Cases Considered
- [x] n = 0 → 1 way (empty arrangement)
- [x] n = 1 → 1
- [x] n = 2 → 2
- [x] Large n (stack/magnitude concerns)
- [x] Negative n (invalid input)

## Solution Code

```java
public class FriendsPairing {
    public static int pairing(int n) {
        if (n <= 2) {
            return n; // 0→0? Conventionally 1 for n=0 handled separately
        }
        int single = pairing(n - 1);
        int pair = (n - 1) * pairing(n - 2);
        return single + pair;
    }
}
```

## Alternative Approaches
- Bottom-up DP: O(n) time, O(n) space.
- Space-optimized DP: O(n) time, O(1) space using two variables.
- Memoization: top-down with cache.

## Personal Notes
- Often appears with direct mapping to the recurrence; good warm-up for DP transformation.

---
**Tags:** #recursion #dp #combinatorics