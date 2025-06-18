# Floor and Ceiling of Target in Sorted Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a sorted array and a target value, find the floor and ceiling of the target. Floor is the largest element in the array smaller than or equal to the target. Ceiling is the smallest element in the array greater than or equal to the target. If floor or ceiling doesn't exist, return -1.

## Intuition/Approach
Use modified binary search for both floor and ceiling. For ceiling, when we don't find exact target, the left pointer will be at the position where target should be inserted (smallest element greater than target). For floor, the right pointer will be at the largest element smaller than target.

## Key Observations
- Floor: largest element ≤ target
- Ceiling: smallest element ≥ target
- If target exists, both floor and ceiling are the target itself
- Use binary search termination positions to find floor and ceiling
- When binary search ends, left pointer is at ceiling position, right pointer is at floor position

## Algorithm Steps
1. **For Ceiling:**
   - Use binary search to find target
   - If found, return index
   - If not found, left pointer is at ceiling position
   - Check bounds and return appropriate result

2. **For Floor:**
   - Use binary search to find target
   - If found, return index
   - If not found, right pointer is at floor position
   - Check bounds and return appropriate result

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Binary search for both operations, constant extra space

## Edge Cases Considered
- [x] Empty array - Return -1 for both
- [x] Target smaller than all elements - Floor: -1, Ceiling: first element
- [x] Target larger than all elements - Floor: last element, Ceiling: -1
- [x] Target exists in array - Both return the target index
- [x] Target between two elements - Floor: smaller element, Ceiling: larger element
- [x] Single element array - Check if it's floor/ceiling of target

## Solution Code

```java
// Language: Java
public static int ceiling(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] > target) {
            right = mid - 1;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            return mid;  // Target found
        }
    }
    
    // Target not found, left is at ceiling position
    return left < arr.length ? left : -1;
}

public static int floor(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] > target) {
            right = mid - 1;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            return mid;  // Target found
        }
    }
    
    // Target not found, right is at floor position
    return right >= 0 ? right : -1;
}
```

## Alternative Approaches
1. **Linear Search:** O(n) - scan array but too slow for large arrays
2. **Built-in Binary Search:** Use Arrays.binarySearch() and handle insertion point
3. **Recursive Binary Search:** Same logic but recursive implementation
4. **Two-Pointer Approach:** Less efficient than binary search for sorted arrays

## Related Problems
- **AC:** Search insert position, binary search variations
- **Kunal:** First and last position, next greatest element
- **LeetCode:** #35 Search Insert Position, #744 Find Smallest Letter Greater Than Target

## Personal Notes
- Understanding binary search termination conditions is crucial
- Key insight: when binary search fails, pointer positions give us floor and ceiling
- Floor and ceiling are fundamental concepts in many algorithm problems
- Important to handle boundary conditions properly

## Revision History
- **First Solve:** 2024-12-19 - Implemented binary search with floor/ceiling logic, understood pointer positions
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #searching #binarysearch #floor #ceiling #sorted #bounds #logarithmic 