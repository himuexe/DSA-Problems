# Peak Index in Mountain Array

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a mountain array (an array that increases to a peak and then decreases), find the index of the peak element. The array is guaranteed to be a mountain array.

## Intuition/Approach
Use binary search to find the peak. Compare the middle element with its next element. If arr[mid] > arr[mid+1], the peak is in the left half (including mid). Otherwise, the peak is in the right half.

## Key Observations
- Mountain array has exactly one peak
- Peak element is greater than both neighbors
- Can use binary search since array has specific structure
- Compare mid with mid+1 to determine search direction

## Algorithm Steps
1. Initialize left = 0, right = arr.length - 1
2. While left < right:
   - Calculate mid = left + (right - left) / 2
   - If arr[mid] > arr[mid+1]: peak is in left half, right = mid
   - Else: peak is in right half, left = mid + 1
3. Return left (the peak index)

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Binary search halves search space each iteration

## Edge Cases Considered
- [x] Peak at beginning (after first element)
- [x] Peak at end (before last element)
- [x] Peak in middle
- [x] Minimum mountain array (3 elements)
- [x] Large mountain arrays

## Solution Code

```java
// Language: Java
public static int peakIndexInMountainArray(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    while(left < right){
        int mid = left + (right - left) / 2;
        if(arr[mid] > arr[mid + 1]){
            right = mid;
        }
        else {
            left = mid + 1;
        }
    }
    return left;
}
```

## Alternative Approaches
- **Linear Search:** O(n) scan to find peak
- **Two-pointer:** Start from ends and move towards peak
- **Ternary Search:** Divide into three parts instead of two

## Related Problems
- **Kunal:** [Find in Mountain Array - mountain array patterns]
- **LeetCode:** [Peak Index in a Mountain Array - Problem 852]
- **LeetCode:** [Find Peak Element - Problem 162]

## Personal Notes
Classic application of binary search on specially structured arrays. The key insight is using the comparison with the next element to guide the search direction.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #mountainArray #peakFinding #medium 