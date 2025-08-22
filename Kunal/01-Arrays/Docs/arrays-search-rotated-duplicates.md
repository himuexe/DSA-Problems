# Search in Rotated Sorted Array II

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
There is an integer array nums sorted in non-decreasing order (with duplicate values). Before it is passed to your function, nums is possibly rotated at an unknown pivot index k. Given the array nums after the possible rotation and an integer target, return true if target is in nums, or false if it is not.

## Intuition/Approach
Use binary search with modifications to handle duplicates. When we encounter duplicates, we can't determine which half to search, so we increment the left pointer to skip duplicates and continue searching.

## Key Observations
- Array is sorted but rotated
- May contain duplicates
- Can't determine search direction with duplicates
- Skip duplicates by incrementing left pointer
- Modified binary search approach

## Algorithm Steps
1. Use binary search with left and right pointers
2. Calculate mid point
3. If nums[mid] == target, return true
4. If nums[left] == nums[mid], increment left and continue
5. Determine which half to search based on sorted portion
6. Adjust search range accordingly

## Complexity Analysis
- **Time Complexity:** O(n) worst case, O(log n) average
- **Space Complexity:** O(1)
- **Justification:** May need to skip many duplicates in worst case

## Edge Cases Considered
- [x] Empty array - Return false
- [x] Single element - Check if equals target
- [x] All elements same - Check if equals target
- [x] No rotation - Standard binary search
- [x] Full rotation - Back to original order
- [x] Target not found - Return false

## Solution Code

```java
// Language: Java
public static boolean search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return true;
        }
        
        if (nums[left] == nums[mid]) {
            left++;
            continue;
        }
        
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    
    return false;
}
```

## Alternative Approaches
- **Linear search:** O(n) time, simple but inefficient
- **Two-pass:** First find pivot, then binary search
- **Recursive:** Divide and conquer approach

## Personal Notes
This is a challenging problem that extends the basic rotated array search to handle duplicates. The key insight is that when we encounter duplicates, we can't determine the search direction, so we need to skip them. This makes the worst-case complexity O(n), but the average case remains O(log n).

---
**Tags:** #arrays #binarysearch #rotation #duplicates #medium
