# Recursive MergeSort - Stable Divide and Conquer Sorting

**Source:** Kunal Recursion | **Topic:** Sorting, Recursion | **Difficulty:** Intermediate

---

## Problem Statement
Implement MergeSort using recursion with two approaches: return-based (creating new arrays) and in-place (modifying the original array).

## Intuition/Approach
MergeSort uses a divide-and-conquer strategy:
- **Divide:** Split the array into two halves recursively until single elements remain.
- **Conquer:** Merge sorted halves in order.
- **Return-based:** Create new arrays during merging for simplicity.
- **In-place:** Modify the original array using a temporary array for merging.
- The base case is an array of one element, which is inherently sorted.

## Key Observations
- MergeSort is stable, preserving the order of equal elements.
- It guarantees O(n log n) time complexity regardless of input.
- Return-based is simpler but uses more memory; in-place is memory-efficient but complex.
- The merge step is linear, combining two sorted arrays efficiently.
- Recursive division creates balanced subproblems.

## Algorithm Steps
1. **Return-based:**
   - If array length == 1, return the array.
   - Split into left and right halves using Arrays.copyOfRange.
   - Recursively sort both halves.
   - Merge sorted halves into a new array and return.
2. **In-place:**
   - If end - start == 1, return.
   - Calculate middle index.
   - Recursively sort left [start, mid) and right [mid, end).
   - Merge in-place using a temporary array.

## Complexity Analysis
- **Time Complexity:** O(n log n) - O(log n) levels of division, O(n) merging per level.
- **Space Complexity:** 
  - Return-based: O(n log n) due to new arrays at each level.
  - In-place: O(n) for temporary array + O(log n) for recursion stack.
- **Justification:** The balanced division ensures logarithmic depth, and linear merging per level gives O(n log n) time.

## Edge Cases Considered
- [x] Empty input.
- [x] Single element array.
- [x] Two element array.
- [x] Already sorted array.
- [x] Reverse sorted array.
- [x] Array with duplicates.

## Solution Code
```java
import java.util.Arrays;

public class RecursiveMergeSort {
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
        
        while (i < first.length && j < second.length) {
            newArr[k++] = (first[i] < second[j]) ? first[i++] : second[j++];
        }
        
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
        sortInPlace(arr, midDm, e);      // Sort right half
        mergeInPlace(arr, s, mid, e);  // Merge in-place
    }
    
    public static void mergeInPlace(int[] arr, int s, int mid, int e) {
        int[] newArr = new int[e - s];
        int i = s, j = mid, k = 0;
        
        while (i < mid && j < e) {
            newArr[k++] = (arr[i] < arr[j]) ? arr[i++] : arr[j++];
        }
        
        while (i < mid) newArr[k++] = arr[i++];
        while (j < e) newArr[k++] = arr[j++];
        
        System.arraycopy(newArr, 0, arr, s, newArr.length);
    }
}
```

## Alternative Approaches
- **Iterative MergeSort:** Uses loops to avoid recursion stack overhead.
- **Bottom-Up MergeSort:** Starts with single elements and merges iteratively.
- **Hybrid Sorting:** Combine with insertion sort for small subarrays to optimize.

## Personal Notes
- The in-place version was challenging due to the need to manage indices carefully during merging.
- The return-based approach is more intuitive and easier to debug.
- Understanding the stability of MergeSort was key to appreciating its real-world applications.

---
**Tags:** #sorting #recursion #merge_sort #divide_and_conquer