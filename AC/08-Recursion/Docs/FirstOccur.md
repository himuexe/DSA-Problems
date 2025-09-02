# First and Last Occurrence in Array

**Source:** AC | **Topic:** Recursion | **Difficulty:** Easy  

---

## Problem Statement
Given an array and a target element, return the indices of the first and last occurrence of the target. If not found, return -1 for the respective query.

Example:
```
Input: arr = [1,2,3,4,5,3,7,8], target = 3
Output:
- First occurrence: 2
- Last occurrence: 5
```

## Intuition/Approach
- First occurrence: recurse left-to-right; return the first index where `arr[i] == target`.
- Last occurrence: recurse right-to-left; return the first index from the end where `arr[i] == target`.
- Different base cases for forward vs backward traversal.

## Key Observations
- Forward traversal yields leftmost match; backward traversal yields rightmost match.
- Early return stops further recursion once a match is found.
- Works for unsorted arrays; binary search variant applies to sorted arrays.

## Algorithm Steps
1. First occurrence:
   - If `i == arr.length`, return -1.
   - If `arr[i] == target`, return `i`.
   - Else recurse with `i+1`.
2. Last occurrence:
   - If `i == 0`, return -1.
   - If `arr[i] == target`, return `i`.
   - Else recurse with `i-1`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** At most n recursive calls; linear scan.

## Edge Cases Considered
- [x] Empty array (return -1)
- [x] Single element match/no match
- [x] All elements same
- [x] Target at boundaries
- [x] Target absent

## Solution Code

```java
public class FirstOccur {
    // Find first occurrence (left to right)
    public static int firstindex(int[] arr, int i, int target) {
        if (i == arr.length) {
            return -1;
        }
        if (arr[i] == target) {
            return i;
        }
        return firstindex(arr, i + 1, target);
    }

    // Find last occurrence (right to left)
    public static int lastindex(int[] arr, int i, int target) {
        if (i == 0) {
            return -1;
        }
        if (arr[i] == target) {
            return i;
        }
        return lastindex(arr, i - 1, target);
    }
}
```

## Alternative Approaches
- Iterative scans from front/back; O(n) time, O(1) space.
- Single pass to compute both indices in one loop.
- Binary search variants for sorted arrays to find boundaries in O(log n).

## Personal Notes
- For very large arrays, prefer iterative solutions to avoid recursion stack usage.

---
**Tags:** #recursion #arrays #search