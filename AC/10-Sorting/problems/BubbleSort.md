# Bubble Sort - AC Implementation

## Problem Statement
Implement Bubble Sort algorithm to sort an array of integers in ascending order.

## Algorithm Overview
**Bubble Sort** is a simple comparison-based sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order.

## Key Approach & Intuition
1. **Bubbling Mechanism**: Larger elements "bubble up" to their correct positions through repeated swaps
2. **Optimization**: Use a `swapped` flag to detect when array is already sorted
3. **Pass Reduction**: After each pass, the largest element is in its correct position, so reduce the comparison range

## Code Implementation
```java
public static void sort(int[] arr){
    boolean swapped;
    for(int i=0;i<arr.length-1;i++){
        swapped =false;
        for(int j=0;j<arr.length-1-i;j++){
            if(arr[j]>arr[j+1]){
                int temp = arr[j+1];
                arr[j+1]=arr[j];
                arr[j]=temp;
                swapped=true;
            }
        }
        if(!swapped){
            break; // Array is sorted, exit early
        }
    }
}
```

## Key Observations
- **Early Termination**: The `swapped` flag provides significant optimization for nearly sorted arrays
- **Decreasing Range**: `arr.length-1-i` reduces comparisons in each pass
- **In-Place Sorting**: No extra space required beyond swap variables

## Complexity Analysis
- **Time Complexity**: 
  - Best Case: O(n) - when array is already sorted (early termination)
  - Average Case: O(n²)
  - Worst Case: O(n²) - when array is reverse sorted
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Empty array or single element (handled by loop conditions)
- Already sorted array (optimized with early termination)
- Reverse sorted array (worst case scenario)
- Arrays with duplicate elements

## Test Case
```java
Input: [13,1,14,2,10,11]
Output: [1,2,10,11,13,14]
```

## Related Concepts
- Comparison-based sorting
- Stable sorting algorithm
- In-place sorting
- Early termination optimization

## Difficulty Level
**Easy** - Good introduction to sorting algorithms

## Source
AC (Apna College) - 10-Sorting Module 