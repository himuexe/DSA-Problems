# Recursive QuickSort - Efficient Divide and Conquer Sorting

## Problem Statement
Implement QuickSort algorithm using recursion with in-place partitioning to sort an array in ascending order.

## Intuition/Approach
**Divide and Conquer with Pivot Strategy:**
1. **Choose Pivot:** Select middle element as pivot
2. **Partition:** Rearrange array so elements < pivot are left, elements > pivot are right
3. **Recursive Sort:** Recursively sort left and right partitions
4. **Base Case:** When low >= high, subarray has 0 or 1 element (already sorted)

**Key Insight:** Partitioning ensures pivot is in correct position, then recursively sort partitions.

## Key Observations
- **In-Place Partitioning:** Uses two pointers approach for space efficiency
- **Pivot Selection:** Middle element choice helps avoid worst-case on sorted arrays
- **Unstable Sort:** May change relative order of equal elements
- **Variable Performance:** Best/Average O(n log n), Worst O(n²)
- **Tail Recursion Optimization:** Possible with iterative approach

## Algorithm Steps
1. **Base Case Check:** If low >= high, return (subarray sorted)
2. **Initialize Pointers:** Start pointer at low, end pointer at high
3. **Pivot Selection:** Choose middle element as pivot value
4. **Partitioning Process:**
   - Move start pointer right while arr[start] < pivot
   - Move end pointer left while arr[end] > pivot
   - If start <= end, swap elements and advance both pointers
   - Continue until pointers cross
5. **Recursive Calls:** Sort left partition [low, end] and right partition [start, high]

## Time & Space Complexity
- **Time Complexity:** 
  - Best/Average: O(n log n) - balanced partitions
  - Worst: O(n²) - already sorted array (with poor pivot choice)
- **Space Complexity:** O(log n)
  - Recursion stack depth: O(log n) average, O(n) worst case
  - In-place sorting: O(1) additional space

## Edge Cases Considered
- [x] Empty array
- [x] Single element array
- [x] Two element array
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with all equal elements
- [x] Array with duplicates

## Code Implementation
```java
public static void sort(int[] nums, int low, int hi) {
    if (low >= hi) {
        return;  // Base case: subarray has 0 or 1 element
    }
    
    int s = low;
    int e = hi;
    int mid = s + (e - s) / 2;
    int pivot = nums[mid];
    
    // Partitioning phase
    while (s <= e) {
        // Find element >= pivot from left
        while (nums[s] < pivot) {
            s++;
        }
        // Find element <= pivot from right
        while (nums[e] > pivot) {
            e--;
        }
        // Swap if pointers haven't crossed
        if (s <= e) {
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }
    
    // Recursive calls on partitions
    sort(nums, low, e);    // Sort left partition
    sort(nums, s, hi);     // Sort right partition
}
```

## Example Walkthrough
**Input:** [5, 4, 3, 2, 1]
**Pivot:** 3 (middle element)
**Partitioning:** [2, 1] + [3] + [5, 4]
**Recursive calls:** Sort [2, 1] and [5, 4]
**Final Result:** [1, 2, 3, 4, 5]

## Key Learning Points
- **Pivot Selection Impact:** Middle element choice improves average performance
- **Two-Pointer Partitioning:** Efficient in-place rearrangement technique
- **Recursion on Partitions:** Divide-and-conquer reduces problem size
- **Performance Variability:** Input distribution affects algorithm efficiency

## Applications
- **General Purpose Sorting:** Fast sorting for random data
- **Database Systems:** Query result sorting
- **System Libraries:** Many standard library implementations
- **Competitive Programming:** Quick sorting solution for contests

---
**Date:** June 27, 2025  
**Topic:** Sorting Algorithms & Recursion  
**Difficulty:** Intermediate-Advanced  
**Category:** Divide and Conquer 