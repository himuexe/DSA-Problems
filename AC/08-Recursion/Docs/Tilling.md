# Tiling Problem - Floor Tiling with 2x1 Tiles

**Source:** AC | **Topic:** Recursion | **Difficulty:** Medium  

---

## Problem Statement
Given a 2×n floor, count the number of ways to tile it using 2×1 tiles placed horizontally or vertically.

Example (n = 3): total ways = 3.

## Intuition/Approach
- First column choice: place one horizontal tile (covers 1 column height 2) or two vertical tiles (cover 2 columns).
- This yields the recurrence T(n) = T(n-1) + T(n-2).
- Base: T(0)=1 (empty), T(1)=1.

## Key Observations
- Identical to Fibonacci sequence shifted by one: T(n) = F(n+1).
- Naive recursion is exponential; DP reduces to linear time.

## Algorithm Steps
1. If n ≤ 1, return 1.
2. Return `tillingProblem(n-1) + tillingProblem(n-2)`.

## Complexity Analysis
- **Time Complexity:** O(2^n) (naive)
- **Space Complexity:** O(n)
- **Justification:** Overlapping subproblems without memoization; recursion depth n.

## Edge Cases Considered
- [x] n = 0
- [x] n = 1
- [x] n = 2
- [x] Large n (stack/overflow concerns)
- [x] Negative n (invalid)

## Solution Code

```java
public class Tilling {
    public static int tillingProblem(int n) {
        if (n <= 1) return 1;
        return tillingProblem(n - 1) + tillingProblem(n - 2);
    }
}
```

## Alternative Approaches
- Bottom-up DP array O(n) time, O(n) space.
- Space-optimized DP using two variables O(1) space.
- Matrix exponentiation O(log n) time.

## Personal Notes
- Prefer DP for constraints where n can be large; recursion is illustrative.

---
**Tags:** #recursion #dp #fibonacci