# Number of Steps to Zero - Recursive Solution

**Source:** Kunal | **Topic:** Recursion | **Difficulty:** Easy  

---

## Problem Statement
Given a non-negative integer num, return the number of steps to reduce it to zero. If num is even, divide by 2; otherwise subtract 1.

## Intuition/Approach
- Each operation reduces the number; recursion naturally models the process.
- Use a helper with an accumulator or return 1 + recursive call directly.

## Key Observations
- Divisions dominate; step count is O(log n).
- Tail-recursive form easily converts to iterative.

## Algorithm Steps
1. If num == 0, return steps.
2. If even, recurse on num/2 with steps+1; else recurse on num-1 with steps+1.

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(log n)
- **Justification:** Each divide-by-2 reduces magnitude exponentially.

## Edge Cases Considered
- [x] num = 0
- [x] num = 1
- [x] Powers of two
- [x] Large values

## Solution Code

```java
public int numberOfSteps(int num) { return helper(num, 0); }
private int helper(int num, int steps) {
    if (num == 0) return steps;
    return (num % 2 == 0) ? helper(num / 2, steps + 1)
                          : helper(num - 1, steps + 1);
}
```

## Alternative Approaches
- Iterative loop accumulating steps.
- Bit-count trick for variant requiring adds for each bit and divisions for bit-length.

## Personal Notes
- Interview-friendly; discuss both recursive and iterative versions.

---
**Tags:** #recursion #math #bitwise