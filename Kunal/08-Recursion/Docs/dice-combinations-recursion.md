# Dice Combinations - Recursive Target Sum

**Source:** Kunal | **Topic:** Recursion | **Difficulty:** Medium  

---

## Problem Statement
Generate all sequences of dice rolls (1–6) whose values sum to a given target.

## Intuition/Approach
- At each step, try every face value i from 1..6 that does not overshoot the remaining target.
- Append i to the path and recurse with remaining reduced by i.
- When remaining becomes 0, record the built path.

## Key Observations
- Order matters; [1,3] and [3,1] are different sequences.
- Depth equals target when using digits as unit steps.
- Branching factor up to 6 yields exponential number of sequences.

## Algorithm Steps
1. If `target == 0`, return list containing current path.
2. For i in 1..6 where i ≤ target: explore `perm(path + i, target - i)` and aggregate results.

## Complexity Analysis
- **Time Complexity:** O(6^target)
- **Space Complexity:** O(target + total_output)
- **Justification:** Branching factor up to 6, depth target; store resulting strings.

## Edge Cases Considered
- [x] target = 0
- [x] target = 1
- [x] target > 6
- [x] Negative target (invalid)
- [x] Large target (output explosion)

## Solution Code

```java
import java.util.ArrayList;

public class Dice {
    public static ArrayList<String> perm(String p, int target) {
        if (target == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 6 && i <= target; i++) {
            list.addAll(perm(p + i, target - i));
        }
        return list;
    }
}
```

## Alternative Approaches
- Return count only (int) rather than sequences to avoid huge outputs.
- Parameterize dice faces for non-standard dice.

## Personal Notes
- When printing instead of returning, use a void method for lower memory usage.

---
**Tags:** #recursion #enumeration #dice