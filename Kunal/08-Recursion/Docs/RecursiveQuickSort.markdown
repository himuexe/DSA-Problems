# Recursive QuickSort - Efficient Divide and Conquer Sorting

**Source:** Kunal Recursion | **Topic:** Sorting, Recursion | **Difficulty:** Intermediate-Advanced

---

## Problem Statement
Implement QuickSort using recursion with in-place partitioning to sort an array in ascending order.

## Intuition/Approach
QuickSort uses a divide-and-conquer strategy:
- **Pivot Selection:** Choose the middle element to avoid worst-case scenarios.
- **Partitioning:** Rearrange the array so elements < pivot are on the left, > pivot on the right.
- **Recursion:** Sort the left and right partitions recursively.
- The base case is when the subarray has 0 or 1 element.

## Key Observations
- In-place partitioning uses two pointers for efficiency.
- Middle pivot selection reduces the chance of O(n²) worst-case performance.
- QuickSort is unstable, potentially reordering equal elements.
- Performance varies: O(n log n) average, O(n²) worst case.
- Tail recursion optimization is possible but not implemented here.

## Algorithm Steps
1. If low >= high, return (subarray sorted).
2. Select pivot as the middle element.
3. Partition the array using two pointers (start and end).
4. While start <= end:
   - Move start right while arr[start] < pivot.
   - Move end left while arr[end] > pivot.
   - Swap elements if start <= end and advance pointers.
5. Recursively sort left [low, end] and right [start, high] partitions.

## Complexity Analysis
- **Time Complexity:** 
  - Average: O(n log n) - Balanced partitions.
  - Worst: O(n²) - Unbalanced partitions (e.g., sorted array).
- **Space Complexity:** O(log n) average, O(n) worst case - Due to recursion stack.
- **Justification:** Partitioning takes O(n) per level, with O(log n) levels on average.

## Edge Cases Considered
- [x] Empty input.
- [x] Single element array.
- [x] Two element array.
- [x] Already sorted array.
- [x] Reverse sorted array.
- [x] Array with all equal elements.

## Solution Code
```java
public class RecursiveQuickSort {
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
            while (nums[s] < pivot) {
                s++;
            }
            while (nums[e] > pivot) {
                e--;
            }
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
}
```

## Alternative Approaches
- **Random Pivot Selection:** Randomize pivot to reduce worst-case probability.
- **Iterative QuickSort:** Use a stack to eliminate recursion stack space.
- **Three-Way Partitioning:** Handle duplicates efficiently for arrays with many equal elements.

## Personal Notes
- Choosing the middle element as the pivot significantly improves performance for nearly sorted arrays.
- The two-pointer partitioning logic took time to master but is elegant and efficient.
- QuickSort’s variability in performance makes it interesting for real-world applications.

---
**Tags:** #sorting #recursion #quick_sort #divide_and_conquer