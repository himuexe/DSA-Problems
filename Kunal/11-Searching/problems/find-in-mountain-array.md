# Find in Mountain Array

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Hard  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a mountain array and a target value, find the smallest index where the target occurs. A mountain array increases to a peak and then decreases. You can only access elements through get() method with limited calls.

## Intuition/Approach
First find the peak of the mountain array, then perform binary search on both ascending (left) and descending (right) parts. Return the smallest index where target is found.

## Key Observations
- Mountain array has ascending part [0, peak] and descending part [peak+1, end]
- Need to search both parts since target might appear in both
- Return smallest index to satisfy problem requirements
- Use different binary search logic for ascending vs descending parts

## Algorithm Steps
1. Find peak index using binary search
2. Search in ascending part [0, peak] with normal binary search
3. If found, return that index (it's guaranteed to be smaller)
4. If not found, search in descending part [peak+1, end] with reverse binary search
5. Return result from descending part search

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Three binary searches (peak finding + two searches)

## Edge Cases Considered
- [x] Target in ascending part only
- [x] Target in descending part only
- [x] Target in both parts (return smaller index)
- [x] Target not present
- [x] Peak at beginning or end
- [x] Minimum mountain array (3 elements)

## Solution Code

```java
// Language: Java
public int findInMountainArray(int target, MountainArray mountainArr) {
    // Find peak
    int peak = peak(mountainArr);
    
    // First try to find in ascending part
    int asc = binarySearch(mountainArr, target, 0, peak, true);
    if (asc != -1) {
        return asc;
    }
    
    // Then try to find in descending part
    return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
}

private int peak(MountainArray arr) {
    int left = 0;
    int right = arr.length() - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr.get(mid) > arr.get(mid + 1)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

private int binarySearch(MountainArray arr, int target, int left, int right, boolean ascending) {
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int midVal = arr.get(mid);
        
        if (midVal == target) {
            return mid;
        }
        
        if (ascending) {
            if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else {
            if (midVal < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
    return -1;
}
```

## Alternative Approaches
- **Linear Search:** O(n) but violates API call limits
- **Single Binary Search:** More complex logic handling both parts simultaneously
- **Ternary Search:** Alternative approach for peak finding

## Related Problems
- **Kunal:** [Peak Index in Mountain Array - peak finding techniques]
- **LeetCode:** [Find in Mountain Array - Problem 1095]
- **LeetCode:** [Peak Index in a Mountain Array - Problem 852]

## Personal Notes
Advanced binary search problem combining peak finding and bidirectional search. Important to handle ascending vs descending search logic correctly and prioritize smaller indices.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #mountainArray #hardProblem #bidirectionalSearch 