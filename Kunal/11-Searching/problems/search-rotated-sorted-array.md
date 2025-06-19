# Search in Rotated Sorted Array

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given a rotated sorted array and a target value, find the index of the target. The array was originally sorted in ascending order but rotated at some unknown pivot point.

## Intuition/Approach
First find the pivot point (where rotation occurred), then apply binary search on the appropriate half. The pivot is the largest element in the rotated array.

## Key Observations
- Array has two sorted subarrays separated by pivot
- Pivot is the largest element (where arr[i] > arr[i+1])
- After finding pivot, determine which half contains the target
- Apply standard binary search on the correct half

## Algorithm Steps
1. Find pivot using binary search:
   - Compare mid with adjacent elements to detect rotation point
   - Use element comparisons to guide search direction
2. Apply binary search on left half [0, pivot]
3. If not found, apply binary search on right half [pivot+1, end]
4. Return the index if found, -1 otherwise

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Two binary searches, each O(log n)

## Edge Cases Considered
- [x] No rotation (original sorted array)
- [x] Target in left sorted portion
- [x] Target in right sorted portion
- [x] Target not present
- [x] Array with duplicate elements at pivot
- [x] Single element array

## Solution Code

```java
// Language: Java
public static int search(int[] nums, int target) {
    // Find pivot
    int pivot = pivot(nums);
    // Apply binary search to left side
    int leftside = binarySearch(nums, 0, pivot, target);
    if(leftside != -1){
        return leftside;
    }
    // Apply binary search to right side
    int rightside = binarySearch(nums, pivot + 1, nums.length - 1, target);
    return rightside;
}

static int pivot(int[] nums){
    int left = 0;
    int right = nums.length - 1;
    while(left < right){
        int mid = left + (right - left) / 2;
        if(mid < right && nums[mid] > nums[mid + 1]){
           return mid;
        }
        else if(mid > left && nums[mid] < nums[mid - 1]){
            return mid - 1;
        }
        else if(nums[mid] <= nums[left]){
            right = mid - 1;
        }
        else{
            left = mid + 1;
        }
    }
    return -1;
}

static int binarySearch(int[] nums, int left, int right, int target){
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(nums[mid] > target){
            right = mid - 1;
        }
        else if(nums[mid] < target){
            left = mid + 1;
        }
        else{
            return mid;
        }
    }
    return -1;
}
```

## Alternative Approaches
- **Single Pass Binary Search:** Determine which half to search without finding pivot first
- **Linear Search:** O(n) brute force approach
- **Modified Binary Search:** Handle rotation logic within single binary search

## Related Problems
- **Kunal:** [Find First and Last Position - binary search applications]
- **LeetCode:** [Search in Rotated Sorted Array - Problem 33]
- **LeetCode:** [Find Minimum in Rotated Sorted Array - Problem 153]

## Personal Notes
Important variation of binary search dealing with rotated arrays. The pivot finding algorithm is crucial for handling rotated sorted arrays efficiently.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #rotatedArray #pivotFinding #medium 