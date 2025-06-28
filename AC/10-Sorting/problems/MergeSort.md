# Merge Sort - Divide and Conquer Sorting

## Problem Statement
Implement merge sort algorithm to sort an array in ascending order using the divide-and-conquer approach.

## Intuition/Approach
**Divide and Conquer Strategy:**
1. **Divide:** Recursively split the array into two halves until single elements
2. **Conquer:** Merge the sorted halves back together in correct order
3. **Base Case:** Array with 1 element is already sorted

**Key Insight:** Merging two sorted arrays is O(n) operation, leading to O(n log n) overall complexity.

## Key Observations
- **Stable Sort:** Maintains relative order of equal elements
- **Predictable Performance:** Always O(n log n) regardless of input distribution
- **Extra Space Required:** Needs temporary array for merging
- **Two-Phase Algorithm:** Recursive division + iterative merging
- **Optimal for Large Datasets:** Consistent performance for large inputs

## Algorithm Steps
1. **Recursive Division:**
   - Calculate middle index: `mid = si + (ei-si)/2`
   - Recursively sort left half: `sort(arr, si, mid)`
   - Recursively sort right half: `sort(arr, mid+1, ei)`

2. **Merge Process:**
   - Create temporary array for current subarray
   - Use two pointers to merge sorted halves
   - Copy remaining elements from both subarrays
   - Copy merged result back to original array

## Time & Space Complexity
- **Time Complexity:** O(n log n)
  - Division: O(log n) levels of recursion
  - Merging: O(n) at each level
  - Total: O(n log n)
- **Space Complexity:** O(n)
  - Temporary arrays for merging: O(n)
  - Recursion stack: O(log n)
  - Total: O(n)

## Edge Cases Considered
- [x] Single element array (base case)
- [x] Two element array
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with duplicate elements
- [x] Empty array handling

## Code Implementation
```java
public static void sort(int[] arr, int si, int ei) {
    if (si >= ei) {
        return; // Base case: single element
    }
    int mid = si + (ei - si) / 2;
    sort(arr, si, mid);        // Sort left half
    sort(arr, mid + 1, ei);    // Sort right half
    merge(arr, si, mid, ei);   // Merge sorted halves
}

public static void merge(int[] arr, int si, int mid, int ei) {
    int[] temp = new int[ei - si + 1];
    int i = si, j = mid + 1, k = 0;
    
    // Merge both halves
    while (i <= mid && j <= ei) {
        temp[k++] = (arr[i] < arr[j]) ? arr[i++] : arr[j++];
    }
    
    // Copy remaining elements
    while (i <= mid) temp[k++] = arr[i++];
    while (j <= ei) temp[k++] = arr[j++];
    
    // Copy back to original array
    System.arraycopy(temp, 0, arr, si, temp.length);
}
```

## Key Learning Points
- **Divide-and-Conquer Paradigm:** Classic example of recursive problem solving
- **Stable Sorting:** Preserves order of equal elements
- **Optimal Comparison-Based Sort:** Achieves theoretical lower bound O(n log n)
- **Memory Trade-off:** Uses extra space for guaranteed performance

---
**Date:** June 27, 2025  
**Topic:** Sorting Algorithms  
**Difficulty:** Intermediate  
**Category:** Divide and Conquer 