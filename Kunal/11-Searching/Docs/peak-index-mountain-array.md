# Peak Index in Mountain Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  

---

## Problem Statement
Given a mountain array (an array that increases to a peak and then decreases), find the index of the peak element. The array is guaranteed to be a mountain array.

## Intuition/Approach
Use binary search to find the peak. Compare the middle element with its next element. If arr[mid] > arr[mid+1], the peak is in the left half (including mid). Otherwise, the peak is in the right half.

**Key Insight:** Mountain arrays have a specific structure that allows binary search to work efficiently. By comparing mid with mid+1, we can determine which half contains the peak and continue searching in that direction.

## Key Observations
- Mountain array has exactly one peak
- Peak element is greater than both neighbors
- Can use binary search since array has specific structure
- Compare mid with mid+1 to determine search direction

## Algorithm Steps
1. **Initialize boundaries:** left = 0, right = arr.length - 1
2. **Binary search loop:** While left < right
   - Calculate mid = left + (right - left) / 2
   - If arr[mid] > arr[mid+1]: peak is in left half, right = mid
   - Else: peak is in right half, left = mid + 1
3. **Return result:** Return left (the peak index)

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
1. **Linear Search:** O(n) scan to find peak
2. **Two-pointer:** Start from ends and move towards peak
3. **Ternary Search:** Divide into three parts instead of two

## Personal Notes
This is a classic application of binary search on specially structured arrays. The key insight is using the comparison with the next element to guide the search direction. The algorithm is elegant and efficient, achieving O(log n) time complexity by leveraging the mountain array's specific properties.

---

**Tags:** #searching #binarysearch #mountainarray #peakfinding #medium 