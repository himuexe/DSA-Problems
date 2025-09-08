Find Minimum in Rotated Sorted Array
Source: Kunal | Topic: Searching | Difficulty: Medium  

Problem Statement
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. Given the sorted rotated array nums of unique elements, return the minimum element of this array.
Intuition/Approach
Binary Search for Minimum Element:The rotated sorted array has a pivot point where the sorted order breaks, and the minimum element is at the start of the second sorted segment. We can use binary search to directly find this minimum element by comparing the middle element with the rightmost element to determine which half contains the minimum.
Algorithm Strategy:

Initialize pointers: Set left to 0 and right to the last index.
Binary search: While left is less than right:
Compute the middle index.
If the middle element is greater than the rightmost element, the minimum lies in the right half.
Otherwise, the minimum lies in the left half, including the middle element.


Return result: The minimum element is at index left.

Key Observations

The minimum element is the first element of the second sorted segment.
If nums[mid] > nums[right], the minimum is in the right half.
If nums[mid] <= nums[right], the minimum is in the left half or at mid.
The array has no duplicates, simplifying the logic.
The approach works for both rotated and non-rotated arrays.

Algorithm Steps

Set left = 0 and right = nums.length - 1.
While left < right:
Calculate mid = left + (right - left) / 2.
If nums[mid] > nums[right], set left = mid + 1.
Else, set right = mid.


Return nums[left] as the minimum element.

Complexity Analysis

Time Complexity: O(log n)
Space Complexity: O(1)
Justification: Binary search halves the search space in each iteration, using only constant extra space.

Edge Cases Considered

 Array not rotated (already sorted)
 Array rotated once (minimum at index 1)
 Array rotated n-1 times (minimum at last position)
 Single element array
 Two element array

Solution Code
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }
}

Alternative Approaches

Pivot-Based Binary Search: Find the pivot point explicitly, then return the next element.
Linear Search: Scan the entire array to find the minimum (O(n) time).
Divide and Conquer: Recursively divide the array to find the minimum.

Personal Notes
The updated approach simplifies the original by directly searching for the minimum element instead of explicitly finding the pivot. By comparing the middle element with the rightmost element, we efficiently narrow down the search space. This method is more concise and avoids the need for a separate pivot-finding function, while maintaining O(log n) time complexity.

Tags: #searching #binarysearch #rotatedarray #minimum #medium