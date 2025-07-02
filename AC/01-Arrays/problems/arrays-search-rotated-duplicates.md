# Search in Rotated Sorted Array with Duplicates

**Difficulty:** Hard  
**Category:** Binary Search + Array Manipulation  
**Source:** AC Arrays (Advanced)

## Problem Statement
Given a rotated sorted array with possible duplicates, determine if a target value exists in the array. The array was originally sorted in ascending order but was rotated at some pivot point.

## Intuition/Approach
This problem extends the classic rotated array search to handle duplicate elements. The key challenge is when duplicates exist at the boundaries (`nums[left] == nums[mid] == nums[right]`), making it impossible to determine which half is sorted. In such cases, we shrink the search space by moving both pointers inward.

**Algorithm Steps:**
1. Use binary search with left and right pointers
2. Handle duplicates case: when `nums[left] == nums[mid] == nums[right]`, shrink boundaries
3. Determine which half is sorted and check if target lies in that range
4. Move to the appropriate half based on target position

## Key Observations
- **Duplicate Handling:** When duplicates span the boundaries, we cannot determine sortedness, so we linearly shrink the search space
- **Sorted Half Detection:** Either left half `[left, mid]` or right half `[mid, right]` must be sorted
- **Target Range Checking:** Once we identify the sorted half, we check if target lies within its range
- **Worst Case Degradation:** With many duplicates, algorithm can degrade to O(n) time complexity

## Time & Space Complexity
- **Time Complexity:** 
  - **Best/Average:** O(log n) - when few duplicates exist
  - **Worst:** O(n) - when array contains mostly duplicates
- **Space Complexity:** O(1) - only using constant extra space

## Edge Cases Considered
- Array with all identical elements
- Target at rotation pivot point
- Single element array
- Target not present in array
- Duplicates spanning multiple segments

## Solution Code
```java
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates - shrink search space
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left++;
                right--;
            }
            // Left half is sorted
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
```

## Algorithm Analysis
- **Search Strategy:** Binary search with duplicate-aware pivot detection
- **Optimization:** Efficiently handles duplicate boundaries with linear shrinking
- **Robustness:** Works for all rotation scenarios and duplicate patterns
- **LeetCode Connection:** Classic "Search in Rotated Sorted Array II" problem 