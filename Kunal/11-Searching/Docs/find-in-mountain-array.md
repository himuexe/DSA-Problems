# Find in Mountain Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Hard  

---

## Problem Statement
Given a mountain array and a target value, find the smallest index where the target occurs. A mountain array increases to a peak and then decreases. You can only access elements through get() method with limited calls.

## Intuition/Approach
First find the peak of the mountain array, then perform binary search on both ascending (left) and descending (right) parts. Return the smallest index where target is found.

**Key Insight:** Mountain arrays have two sorted parts - ascending [0, peak] and descending [peak+1, end]. We need to search both parts since the target might appear in both, and return the smallest index to satisfy problem requirements.

## Key Observations
- Mountain array has ascending part [0, peak] and descending part [peak+1, end]
- Need to search both parts since target might appear in both
- Return smallest index to satisfy problem requirements
- Use different binary search logic for ascending vs descending parts

## Algorithm Steps
1. **Find peak index:** Use binary search to locate the mountain peak
2. **Search ascending part:** Binary search in [0, peak] with normal logic
3. **Check result:** If found in ascending part, return that index (guaranteed smaller)
4. **Search descending part:** If not found, binary search in [peak+1, end] with reverse logic
5. **Return final result:** Result from descending part search

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Three binary searches (peak finding + two searches), each taking O(log n) time

## Edge Cases Considered
- [x] Target in ascending part only
- [x] Target in descending part only
- [x] Target in both parts (return smaller index)
- [x] Target not present
- [x] Peak at beginning or end
- [x] Minimum mountain array (3 elements)

## Solution Code

```java
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
1. **Linear Search:** O(n) but violates API call limits
2. **Single Binary Search:** More complex logic handling both parts simultaneously
3. **Ternary Search:** Alternative approach for peak finding

## Personal Notes
This is an advanced binary search problem that combines peak finding with bidirectional search. The key insight is understanding that mountain arrays have two sorted parts requiring different search strategies. It's crucial to handle ascending vs descending search logic correctly and prioritize smaller indices as required by the problem.

---

**Tags:** #searching #binarysearch #mountainarray #hard #bidirectionalsearch 