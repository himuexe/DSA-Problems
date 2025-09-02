# Check if Array is Sorted

**Source:** AC | **Topic:** Recursion | **Difficulty:** Easy  

---

## Problem Statement
Determine if an integer array is sorted in non-decreasing order using recursion. Return true if sorted, false otherwise.

Example:
```
[1,2,3,5,4] → false
[1,2,3,4,5] → true
```

## Intuition/Approach
- An array is sorted if each adjacent pair satisfies `arr[i] <= arr[i+1]` and the suffix from `i+1` is sorted.
- Compare current pair; if violation found, early return false; otherwise recurse.

## Key Observations
- Only adjacent comparisons are needed.
- Early termination improves best-case time.
- Recursion depth equals array length in worst case.

## Algorithm Steps
1. If `i == arr.length - 1`, return true.
2. If `arr[i] > arr[i+1]`, return false.
3. Return `sorted(arr, i+1)`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Linear scan with recursion stack depth up to n.

## Edge Cases Considered
- [x] Empty array (treat separately if needed)
- [x] Single element
- [x] All equal elements
- [x] Reverse sorted (early exit)
- [x] Two-element arrays

## Solution Code

```java
public class IsSorted {
    public static boolean sorted(int[] arr, int i) {
        if (i == arr.length - 1) return true;
        if (arr[i] > arr[i + 1]) return false;
        return sorted(arr, i + 1);
    }
}
```

## Alternative Approaches
- Iterative O(n) with O(1) space.
- Tail-recursive style for clarity (same complexity).
- Streams (Java 8+) using IntStream to compare neighbors.

## Personal Notes
- Prefer iterative in production to avoid stack growth; recursion is educational here.

---
**Tags:** #recursion #arrays #validation