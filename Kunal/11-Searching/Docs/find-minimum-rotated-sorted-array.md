# Find Minimum in Rotated Sorted Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  

---

## Problem Statement
Suppose an array of length `n` sorted in ascending order is rotated between `1` and `n` times. Given the sorted rotated array `nums` of unique elements, return the minimum element of this array.

## Intuition/Approach
**Binary Search with Pivot Detection:**
The key insight is that in a rotated sorted array, the minimum element is always the element right after the pivot (the largest element). We can use binary search to find the pivot efficiently.

**Algorithm Strategy:**
1. **Quick Check:** If array is not rotated (`nums[0] <= nums[n-1]`), return first element
2. **Find Pivot:** Use binary search to locate the rotation point
3. **Return Minimum:** The minimum is at position `pivot + 1`

## Key Observations
- Non-rotated array: return first element immediately
- Rotated array: minimum is always right after the pivot
- Binary search reduces search space by half each iteration
- Pivot is the point where sorted order breaks
- Only works with unique elements (no duplicates)

## Algorithm Steps
1. **Check if array is rotated:** Compare first and last elements
2. **Binary search for pivot:**
   - Calculate mid point
   - Check if mid is the pivot (larger than next element)
   - Check if mid-1 is the pivot (smaller than previous element)
   - Decide which half to search based on comparison with left boundary
3. **Return minimum:** Element at position `pivot + 1`

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Binary search through the array with constant extra space

## Edge Cases Considered
- [x] Array not rotated (already sorted)
- [x] Array rotated once (minimum at index 1)
- [x] Array rotated n-1 times (minimum at last position)
- [x] Single element array
- [x] Two element array

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
1. **Direct Binary Search:** Modify binary search to find minimum directly
2. **Linear Search:** O(n) approach by scanning entire array
3. **Divide and Conquer:** Recursive approach with similar logic

## Personal Notes
This problem demonstrates the power of binary search for finding specific elements in rotated sorted arrays. The key insight is identifying the pivot point where the sorted order breaks, then finding the minimum element right after it. The binary search approach achieves O(log n) time complexity, making it much more efficient than linear search.

---

**Tags:** #searching #binarysearch #rotatedarray #minimum #medium 