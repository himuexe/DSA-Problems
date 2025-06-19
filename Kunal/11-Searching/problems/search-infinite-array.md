# Search in Infinite Array

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a sorted infinite array (array with unknown size) and a target value, find the index of the target. You cannot use the array's length property.

## Intuition/Approach
Start with a small search window and exponentially expand it until the target is within bounds, then apply binary search. Use exception handling to detect array boundaries.

## Key Observations
- Cannot determine array size beforehand
- Start with window [0, 1] and expand exponentially
- Expansion: new_end = end + (end - start + 1) × 2
- Use exception handling for array bounds detection
- Apply binary search once target is within window

## Algorithm Steps
1. Initialize start = 0, end = 1
2. While target > getValue(arr, end):
   - Calculate new window size exponentially
   - Update start and end for next iteration
3. Apply binary search on final window [start, end]
4. Handle array bounds using exception catching

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Exponential expansion + binary search, both logarithmic

## Edge Cases Considered
- [x] Target at beginning of array
- [x] Target at end of actual array
- [x] Target not present
- [x] Single element array
- [x] Array bounds exceeded (handled with exceptions)

## Solution Code

```java
// Language: Java
static int ans(int arr[], int target){
    int start = 0;
    int end = 1;
    
    while(target > getValue(arr, end)){
        int newStart = end + 1;
        end = end + (end - start + 1) * 2;
        start = newStart;
    }
    
    return binarySearch(arr, start, end, target);
}

static int getValue(int arr[], int index){
    try{
        return arr[index];
    }
    catch(ArrayIndexOutOfBoundsException e){
        return Integer.MAX_VALUE;
    }
}

static int binarySearch(int arr[], int start, int end, int target){
    while(start <= end){
        int mid = start + (end - start) / 2;
        int midValue = getValue(arr, mid);
        
        if(midValue > target){
            end = mid - 1;
        }
        else if(midValue < target){
            start = mid + 1;
        }
        else{
            return mid;
        }
    }
    return -1;
}
```

## Alternative Approaches
- **Linear Search:** O(n) scan until target found or bounds exceeded
- **Bounded Doubling:** Different expansion strategies for the search window
- **Fibonacci Search:** Use Fibonacci numbers for window expansion

## Related Problems
- **Kunal:** [Binary Search variations - search techniques]
- **LeetCode:** [Search in a Sorted Array of Unknown Size - Problem 702]
- **GeeksforGeeks:** [Find position of element in infinite sorted array]

## Personal Notes
Clever technique for handling arrays with unknown size. The exponential expansion ensures logarithmic time complexity while the exception handling provides safe boundary detection.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #infiniteArray #exponentialSearch #medium 