# Search in Rotated Sorted Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  

---

## Problem Statement
Given a rotated sorted array and a target value, find the index of the target. The array was originally sorted in ascending order but rotated at some unknown pivot point.

## Intuition/Approach
First find the pivot point (where rotation occurred), then apply binary search on the appropriate half. The pivot is the largest element in the rotated array.

**Key Insight:** Rotated sorted arrays have two sorted subarrays separated by a pivot point. After finding the pivot, we can determine which half contains the target and apply standard binary search on the correct portion.

## Key Observations
- Array has two sorted subarrays separated by pivot
- Pivot is the largest element (where arr[i] > arr[i+1])
- After finding pivot, determine which half contains the target
- Apply standard binary search on the correct half

## Algorithm Steps
1. **Find pivot:** Use binary search to locate rotation point
   - Compare mid with adjacent elements to detect rotation
   - Use element comparisons to guide search direction
2. **Search left half:** Apply binary search on [0, pivot]
3. **Search right half:** If not found, search [pivot+1, end]
4. **Return result:** Index if found, -1 otherwise

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Two binary searches, each taking O(log n) time

## Edge Cases Considered
- [x] No rotation (original sorted array)
- [x] Target in left sorted portion
- [x] Target in right sorted portion
- [x] Target not present
- [x] Array with duplicate elements at pivot
- [x] Single element array

## Solution Code

```java
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
1. **Single Pass Binary Search:** Determine which half to search without finding pivot first
2. **Linear Search:** O(n) brute force approach
3. **Modified Binary Search:** Handle rotation logic within single binary search

## Personal Notes
This is an important variation of binary search that deals with rotated arrays. The pivot finding algorithm is crucial for handling rotated sorted arrays efficiently. The two-phase approach (find pivot, then search) makes the logic clear and maintainable, though a single-pass approach is also possible.

---

**Tags:** #searching #binarysearch #rotatedarray #pivotfinding #medium 