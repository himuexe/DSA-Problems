# Merge Sort

**Source:** AC | **Topic:** Sorting | **Difficulty:** Medium  

---

## Problem Statement
Implement Merge Sort to sort an integer array in ascending order using the divide-and-conquer paradigm.

## Intuition/Approach
- Divide: recursively split the array until subarrays of size 1 (already sorted).
- Conquer: merge two sorted subarrays in linear time using two pointers.
- Key insight: merging dominates cost at each level; there are log n levels.

## Key Observations
- Stable: equal elements retain relative order.
- Predictable O(n log n) regardless of input distribution.
- Requires extra memory for temporary arrays during merging.

## Algorithm Steps
1. Find mid: `mid = si + (ei - si) / 2`.
2. Recursively sort left `[si..mid]` and right `[mid+1..ei]`.
3. Merge the two sorted halves into a temporary array and copy back.

## Complexity Analysis
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(n)
- **Justification:** Each level merges all elements once; log n levels; temp array of size proportional to subproblem.

## Edge Cases Considered
- [x] Empty input
- [x] Single element
- [x] Already sorted
- [x] Reverse sorted
- [x] Duplicates present

## Solution Code

```java
public static void sort(int[] arr, int si, int ei) {
    if (si >= ei) {
        return;
    }
    int mid = si + (ei - si) / 2;
    sort(arr, si, mid);
    sort(arr, mid + 1, ei);
    merge(arr, si, mid, ei);
}

public static void merge(int[] arr, int si, int mid, int ei) {
    int[] temp = new int[ei - si + 1];
    int i = si, j = mid + 1, k = 0;
    while (i <= mid && j <= ei) {
        temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
    }
    while (i <= mid) temp[k++] = arr[i++];
    while (j <= ei) temp[k++] = arr[j++];
    System.arraycopy(temp, 0, arr, si, temp.length);
}
```

## Alternative Approaches
- Bottom-up (iterative) merge sort eliminates recursion using subarray sizes 1,2,4,...

## Personal Notes
- For large datasets where stability matters, merge sort is a solid default.

---
**Tags:** #sorting #divide-and-conquer #stable #nlogn