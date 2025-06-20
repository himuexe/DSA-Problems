# Selection Sort - AC Implementation

## Problem Statement
Implement Selection Sort algorithm to sort an array of integers in ascending order.

## Algorithm Overview
**Selection Sort** finds the minimum element from the unsorted portion and places it at the beginning of the sorted portion.

## Key Approach & Intuition
1. **Selection Strategy**: Find the minimum element in the unsorted portion
2. **Position Tracking**: Keep track of minimum element's position
3. **Swap Operation**: Move minimum element to its correct sorted position
4. **Boundary Movement**: Expand sorted portion by one element each iteration

## Code Implementation
```java
public static void sort(int[] arr){
    for(int i=0;i<arr.length-1;i++){
        int minPos=i;
        for(int j=i+1;j<arr.length;j++){
            if(arr[j]<arr[minPos]){
                minPos=j;
            }
        }
        int temp =arr[minPos];
        arr[minPos]=arr[i];
        arr[i]=temp;
    }
}
```

## Key Observations
- **Minimum Finding**: Inner loop searches for minimum element in unsorted portion
- **Single Swap**: Only one swap per iteration (efficient in terms of write operations)
- **Sorted Boundary**: Elements at positions 0 to i-1 are always sorted
- **Decreasing Search Space**: Search space reduces by 1 each iteration

## Complexity Analysis
- **Time Complexity**: 
  - Best Case: O(n²) - always performs n²/2 comparisons
  - Average Case: O(n²)
  - Worst Case: O(n²)
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Empty array or single element (handled by loop condition)
- All elements are the same (no effect on correctness)
- Already sorted array (still performs O(n²) operations)
- Reverse sorted array (worst case scenario)

## Test Case
```java
Input: [13,1,14,2,10,11]
Output: [1,2,10,11,13,14]
```

## Advantages
- **Few Swaps**: Performs at most n-1 swaps
- **In-Place**: No additional space required
- **Simple**: Easy to understand and implement

## Disadvantages
- **Inefficient**: O(n²) time complexity even for best case
- **Unstable**: Does not preserve relative order of equal elements

## Related Concepts
- Comparison-based sorting
- In-place sorting
- Unstable sorting algorithm

## Difficulty Level
**Easy** - Fundamental sorting algorithm

## Source
AC (Apna College) - 10-Sorting Module 