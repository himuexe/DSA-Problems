# Minimum Sum of Absolute Differences of Two Arrays

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given two integer arrays of equal length, rearrange elements within each array so that the sum of absolute differences of paired elements at the same indices is minimized. Return that minimum possible sum.

## Intuition/Approach
- To minimize |a_i - b_i| across pairs, sort both arrays and pair elements in ascending order. 
- Sorting aligns similar-magnitude values, minimizing per-index differences by the rearrangement inequality.

## Key Observations
- Sorting both arrays and pairing correspondingly yields the global minimum.
- The arrays must be the same length; otherwise, the problem is ill-defined.
- Works with negative numbers as well due to absolute difference.

## Algorithm Steps
1. Sort `arr1` in non-decreasing order.
2. Sort `arr2` in non-decreasing order.
3. Initialize `sum = 0`.
4. For each index i, add `abs(arr1[i] - arr2[i])` to `sum`.
5. Return `sum`.

## Complexity Analysis
- **Time Complexity:** O(n log n) due to sorting both arrays
- **Space Complexity:** O(1) extra (or O(n) if sorting requires auxiliary storage)
- **Justification:** Pairwise linear pass after sorting dominates less than sorting.

## Edge Cases Considered
- [x] Empty input (sum is 0 if both arrays empty)
- [x] Single element
- [x] Large input
- [x] Negative numbers (if applicable)
- [ ] Other: duplicate-heavy arrays

## Solution Code

```java
import java.util.Arrays;

public class AbsoluteDifference {
    public static long minAbsoluteDifferenceSum(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        long sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum += Math.abs(arr1[i] - arr2[i]);
        }
        return sum;
    }
}
```

## Alternative Approaches
- If arrays are pre-sorted, skip sorting for O(n) time.
- If only one array can be permuted, sort that array and leave the other as-is, then pair.

## Personal Notes
- Rearrangement inequality formalizes why dual-sorting is optimal.

---
**Tags:** #arrays #greedy #sorting
