# Find Minimum in Rotated Sorted Array

**Date:** 2025-01-19  
**Category:** Kunal Searching  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Binary Search/Array

## Problem Statement

Suppose an array of length `n` sorted in ascending order is rotated between `1` and `n` times. Given the sorted rotated array `nums` of unique elements, return the minimum element of this array.

## Intuition/Approach

**Binary Search with Pivot Detection:**
The key insight is that in a rotated sorted array, the minimum element is always the element right after the pivot (the largest element). We can use binary search to find the pivot efficiently.

**Algorithm Strategy:**
1. **Quick Check:** If array is not rotated (`nums[0] <= nums[n-1]`), return first element
2. **Find Pivot:** Use binary search to locate the rotation point
3. **Return Minimum:** The minimum is at position `pivot + 1`

**Pivot Identification:**
- `nums[mid] > nums[mid + 1]` → mid is the pivot
- `nums[mid] < nums[mid - 1]` → mid - 1 is the pivot

## Algorithm Steps

1. **Check if array is rotated:** Compare first and last elements
2. **Binary search for pivot:**
   - Calculate mid point
   - Check if mid is the pivot (larger than next element)
   - Check if mid-1 is the pivot (smaller than previous element)
   - Decide which half to search based on comparison with left boundary
3. **Return minimum:** Element at position `pivot + 1`

## Key Observations

- Non-rotated array: return first element immediately
- Rotated array: minimum is always right after the pivot
- Binary search reduces search space by half each iteration
- Pivot is the point where sorted order breaks
- Only works with unique elements (no duplicates)

## Time & Space Complexity

- **Time Complexity:** O(log n) - binary search through the array
- **Space Complexity:** O(1) - only using constant extra space

## Edge Cases Considered

- [ ] Array not rotated (already sorted)
- [ ] Array rotated once (minimum at index 1)
- [ ] Array rotated n-1 times (minimum at last position)
- [ ] Single element array
- [ ] Two element array

## Solution Code

```java
class Solution {
    public int findMin(int[] nums) {
        // If the array is not rotated, just return the first element
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        
        int pivot = findPivot(nums);
        return nums[pivot + 1];
    }
    
    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is the pivot
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }
            
            // Decide which half to search
            if (nums[mid] <= nums[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
```

## Alternative Approaches

1. **Direct Binary Search:**
   ```java
   while (left < right) {
       int mid = left + (right - left) / 2;
       if (nums[mid] > nums[right]) {
           left = mid + 1;
       } else {
           right = mid;
       }
   }
   return nums[left];
   ```

2. **Linear Search:** O(n) approach by scanning entire array
3. **Divide and Conquer:** Recursive approach with similar logic

## Why This Works

In a rotated sorted array, the minimum element is always in the unsorted part. By comparing `nums[mid]` with boundary elements, we can determine which half contains the rotation point and continue searching there.

## Related Problems

- Search in Rotated Sorted Array
- Find Minimum in Rotated Sorted Array II (with duplicates)
- Find Peak Element
- Search for Range

**LeetCode Connection:** LeetCode #153 - Find Minimum in Rotated Sorted Array 