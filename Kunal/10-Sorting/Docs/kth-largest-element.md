# Kth Largest Element

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Medium  

---

## Problem Statement
Find the kth largest element in an unsorted array without fully sorting the array.

## Intuition/Approach
**QuickSelect Algorithm (Modified QuickSort):**
Uses QuickSort partitioning to place pivot in correct position, then selectively recurses on the partition containing the target element.

**Key Insight:** Don't need to sort entire array, just find the element at the correct position. Convert "kth largest" to array index (length - k) and use partitioning to find it efficiently.

## Key Observations
- **Partial Sorting:** Only sorts as much as needed to find target element
- **Average O(n) Performance:** Better than full sorting for single element query
- **In-Place Algorithm:** Uses constant extra space
- **QuickSort Relation:** Uses same partitioning logic but recurses on one side only
- **Lomuto Partition:** Uses last element as pivot with single-pointer partitioning

## Algorithm Steps
1. **Index Conversion:** Convert kth largest to 0-based index (nums.length - k)
2. **QuickSelect Process:** Partition array around last element (pivot)
3. **Recursive Search:** If pivot index == target index, found kth largest; otherwise recurse on appropriate partition
4. **Partitioning (Lomuto):** Use last element as pivot, maintain partition index, swap elements ≤ pivot to left side

## Complexity Analysis
- **Time Complexity:** O(n) average, O(n²) worst case
- **Space Complexity:** O(log n) average, O(n) worst case
- **Justification:** Average case: each level processes fewer elements. Worst case: when pivot is always min/max element. Space: recursion stack depth.

## Edge Cases Considered
- [x] k = 1 (largest element)
- [x] k = array.length (smallest element) 
- [x] Single element array
- [x] All elements equal
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Array with duplicates

## Solution Code

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

## Alternative Approaches
1. **Heap-based:** Build max heap, extract k times (O(n + k log n))
2. **Sorting:** Full sort then return kth element (O(n log n))
3. **Counting sort:** For small range integers (O(n + range))
4. **Randomized QuickSelect:** Better average case performance

## Personal Notes
QuickSelect is an elegant optimization of QuickSort for selection problems. The key insight is that we only need to partition until we find the target element, avoiding unnecessary sorting. The Lomuto partitioning scheme makes the implementation clean and efficient. This algorithm is particularly useful for finding medians, percentiles, and top-k elements without full sorting.

---

**Tags:** #sorting #quickselect #quicksort #selection #medium 