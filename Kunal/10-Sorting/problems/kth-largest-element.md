# Kth Largest Element - QuickSelect Algorithm

## Problem Statement
**LeetCode Problem:** Find the kth largest element in an unsorted array without fully sorting the array.

## Intuition/Approach
**QuickSelect Algorithm (Modified QuickSort):**
1. **Partition Strategy:** Use QuickSort partitioning to place pivot in correct position
2. **Selective Recursion:** Only recurse on the partition containing the target element
3. **Index Conversion:** Convert "kth largest" to array index (length - k)
4. **Early Termination:** Stop when pivot index equals target index

**Key Insight:** Don't need to sort entire array, just find the element at the correct position.

## Key Observations
- **Partial Sorting:** Only sorts as much as needed to find target element
- **Average O(n) Performance:** Better than full sorting for single element query
- **In-Place Algorithm:** Uses constant extra space
- **QuickSort Relation:** Uses same partitioning logic but recurses on one side only
- **Lomuto Partition:** Uses last element as pivot with single-pointer partitioning

## Algorithm Steps
1. **Index Conversion:** Convert kth largest to 0-based index (nums.length - k)
2. **QuickSelect Process:**
   - Partition array around last element (pivot)
   - If pivot index == target index: Found kth largest
   - If pivot index < target index: Search right partition
   - If pivot index > target index: Search left partition
3. **Partitioning (Lomuto):**
   - Use last element as pivot
   - Maintain partition index i (elements ≤ pivot)
   - Swap elements ≤ pivot to left side
   - Place pivot in correct position

## Time & Space Complexity
- **Time Complexity:** 
  - Average: O(n) - each level processes fewer elements
  - Worst: O(n²) - when pivot is always min/max element
- **Space Complexity:** O(log n)
  - Recursion stack: O(log n) average, O(n) worst case
  - In-place partitioning: O(1) additional space

## Edge Cases Considered
- [x] k = 1 (largest element)
- [x] k = array.length (smallest element) 
- [x] Single element array
- [x] All elements equal
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with duplicates

## Code Implementation
```java
public int findKthLargest(int[] nums, int k) {
    // Convert kth largest to 0-based index
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
}

private int quickSelect(int[] nums, int low, int high, int k) {
    int pi = partition(nums, low, high);
    
    if (pi == k) {
        return nums[pi];  // Found kth largest element
    } else if (pi < k) {
        return quickSelect(nums, pi + 1, high, k);  // Search right
    } else {
        return quickSelect(nums, low, pi - 1, k);   // Search left
    }
}

private int partition(int[] nums, int low, int high) {
    int pivot = nums[high];  // Last element as pivot
    int i = low - 1;         // Partition index
    
    // Lomuto partition scheme
    for (int j = low; j < high; j++) {
        if (nums[j] <= pivot) {
            i++;
            swap(nums, i, j);
        }
    }
    
    swap(nums, i + 1, high);  // Place pivot in correct position
    return i + 1;             // Return pivot index
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Example Walkthrough
**Input:** nums = [3, 2, 1, 5, 6, 4], k = 2 (2nd largest)
**Target Index:** 6 - 2 = 4 (0-based index for 2nd largest)

**Step 1:** Partition around pivot = 4
- Result: [3, 2, 1, 4, 6, 5], pivot index = 3
- pi = 3 < target = 4, search right: quickSelect([6, 5], 4, 5, 4)

**Step 2:** Partition around pivot = 5  
- Result: [3, 2, 1, 4, 5, 6], pivot index = 4
- pi = 4 == target = 4, **Found!** Return nums[4] = 5

**Final Result:** 5 (2nd largest element)

## Key Learning Points
- **QuickSelect Optimization:** Selective recursion improves average performance
- **Lomuto Partitioning:** Single-pointer partition scheme implementation
- **Index Conversion:** Understanding kth largest vs array index mapping
- **Early Termination:** Stop when target position found

## Applications
- **Top-K Problems:** Finding top k elements efficiently
- **Statistics:** Finding median, percentiles without full sorting
- **Database Queries:** ORDER BY with LIMIT optimization
- **Competitive Programming:** Efficient selection algorithms

---
**Date:** June 27, 2025  
**Topic:** Selection Algorithms & Sorting  
**Difficulty:** Intermediate  
**Category:** QuickSelect / LeetCode Problem 