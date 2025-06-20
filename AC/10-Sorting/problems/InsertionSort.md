# Insertion Sort - AC Implementation

## Problem Statement
Implement Insertion Sort algorithm to sort an array of integers in ascending order.

## Algorithm Overview
**Insertion Sort** builds the final sorted array one element at a time by inserting each element into its correct position within the already sorted portion.

## Key Approach & Intuition
1. **Card Sorting Analogy**: Like sorting playing cards in hand
2. **Sorted Portion**: Maintain a sorted portion at the beginning
3. **Element Insertion**: Take each element and insert it in correct position
4. **Shifting Strategy**: Shift larger elements to make space for insertion

## Code Implementation
```java
public static void sort(int[] arr){
    for(int i=1;i<arr.length;i++){
        int curr=arr[i];
        int prev=i-1;
        while(prev >=0 && arr[prev] >curr){
            arr[prev+1]=arr[prev];
            prev--;
        }
        arr[prev+1]=curr;
    }
}
```

## Key Observations
- **Current Element**: `curr` stores the element to be inserted
- **Backwards Scanning**: `prev` pointer moves backwards through sorted portion
- **Shifting Elements**: Larger elements are shifted right to make space
- **Correct Position**: Element is inserted when correct position is found

## Complexity Analysis
- **Time Complexity**: 
  - Best Case: O(n) - when array is already sorted
  - Average Case: O(n²)
  - Worst Case: O(n²) - when array is reverse sorted
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Empty array or single element (loop starts from index 1)
- Already sorted array (best case performance)
- Reverse sorted array (worst case scenario)
- Arrays with duplicate elements (stable behavior)

## Test Case
```java
Input: [13,1,14,2,10,11]
Output: [1,2,10,11,13,14]
```

## Advantages
- **Adaptive**: Efficient for small datasets and nearly sorted arrays
- **Stable**: Preserves relative order of equal elements
- **In-Place**: Only requires O(1) extra space
- **Online**: Can sort array as it receives elements

## Disadvantages
- **Quadratic Time**: O(n²) worst-case time complexity
- **Inefficient**: Poor performance on large, randomly ordered datasets

## Related Concepts
- Comparison-based sorting
- Stable sorting algorithm
- In-place sorting
- Adaptive algorithm

## Difficulty Level
**Easy** - Intuitive and fundamental sorting algorithm

## Source
AC (Apna College) - 10-Sorting Module 