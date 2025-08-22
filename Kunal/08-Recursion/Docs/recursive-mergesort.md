# Recursive MergeSort - Stable Divide and Conquer Sorting

## Problem Statement
Implement MergeSort algorithm using recursion with two approaches: return-based (creating new arrays) and in-place (modifying original array).

## Intuition/Approach
**Divide and Conquer with Guaranteed Performance:**
1. **Divide:** Split array into two halves recursively until single elements
2. **Conquer:** Merge sorted halves back together in correct order
3. **Base Case:** Array with 1 element is already sorted
4. **Two Variations:** Return new sorted array vs modify original in-place

**Key Insight:** Merging two sorted arrays is linear operation, ensuring O(n log n) performance.

## Key Observations
- **Stable Sort:** Maintains relative order of equal elements
- **Predictable Performance:** Always O(n log n) regardless of input
- **Two Implementation Styles:** Return-based vs in-place modification
- **Extra Space Trade-off:** Return-based uses more space but simpler logic
- **Recursive Structure:** Each level creates perfectly balanced divide

## Algorithm Steps
1. **Return-Based Approach:**
   - Base case: Return array if length == 1
   - Split array into left and right halves using Arrays.copyOfRange
   - Recursively sort both halves
   - Merge sorted halves and return combined result

2. **In-Place Approach:**
   - Base case: Return if end - start == 1
   - Calculate middle index
   - Recursively sort left [start, mid) and right [mid, end)
   - Merge in-place using temporary array for current range

## Time & Space Complexity
- **Time Complexity:** O(n log n)
  - Division: O(log n) levels
  - Merging: O(n) per level
  - Total: O(n log n) guaranteed
- **Space Complexity:** 
  - Return-based: O(n log n) - new arrays at each level
  - In-place: O(n) - single temp array + O(log n) recursion stack

## Edge Cases Considered
- [x] Single element array
- [x] Two element array
- [x] Empty array
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with duplicates
- [x] Large arrays (memory efficiency)

## Code Implementation
```java
// Return-based MergeSort
public static int[] sort(int[] arr) {
    if (arr.length == 1) {
        return arr;  // Base case
    }
    int mid = arr.length / 2;
    int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
    int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));
    return merge(left, right);
}

public static int[] merge(int[] first, int[] second) {
    int[] newArr = new int[first.length + second.length];
    int i = 0, j = 0, k = 0;
    
    // Merge both arrays
    while (i < first.length && j < second.length) {
        newArr[k++] = (first[i] < second[j]) ? first[i++] : second[j++];
    }
    
    // Copy remaining elements
    while (i < first.length) newArr[k++] = first[i++];
    while (j < second.length) newArr[k++] = second[j++];
    
    return newArr;
}

// In-place MergeSort
public static void sortInPlace(int[] arr, int s, int e) {
    if (e - s == 1) {
        return;  // Base case
    }
    int mid = s + (e - s) / 2;
    sortInPlace(arr, s, mid);      // Sort left half
    sortInPlace(arr, mid, e);      // Sort right half
    mergeInPlace(arr, s, mid, e);  // Merge in-place
}

public static void mergeInPlace(int[] arr, int s, int mid, int e) {
    int[] newArr = new int[e - s];
    int i = s, j = mid, k = 0;
    
    // Merge both halves
    while (i < mid && j < e) {
        newArr[k++] = (arr[i] < arr[j]) ? arr[i++] : arr[j++];
    }
    
    // Copy remaining elements
    while (i < mid) newArr[k++] = arr[i++];
    while (j < e) newArr[k++] = arr[j++];
    
    // Copy back to original array
    System.arraycopy(newArr, 0, arr, s, newArr.length);
}
```

## Example Walkthrough
**Input:** [5, 4, 3, 2, 1]
**Division:** [5,4,3,2,1] → [5,4,3] + [2,1] → [5,4] + [3] + [2] + [1] → [5] + [4] + [3] + [2] + [1]
**Merging:** [5]+[4]→[4,5], [2]+[1]→[1,2], [4,5]+[3]→[3,4,5], [3,4,5]+[1,2]→[1,2,3,4,5]
**Final Result:** [1, 2, 3, 4, 5]

## Key Learning Points
- **Two Implementation Patterns:** Return-based vs in-place modification
- **Space-Time Trade-offs:** More memory for simpler logic vs memory efficiency
- **Stable Sorting Property:** Guaranteed preservation of equal element order
- **Predictable Performance:** No worst-case degradation like QuickSort

## Applications
- **External Sorting:** Sorting large datasets that don't fit in memory
- **Stable Sorting Required:** When order of equal elements must be preserved
- **Guaranteed Performance:** When consistent O(n log n) performance is critical
- **Large Scale Systems:** Reliable sorting for production systems

---
**Date:** June 27, 2025  
**Topic:** Sorting Algorithms & Recursion  
**Difficulty:** Intermediate  
**Category:** Divide and Conquer 