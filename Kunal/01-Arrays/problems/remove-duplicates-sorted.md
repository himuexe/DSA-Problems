# Remove Duplicates from Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given a sorted array, remove duplicates in-place such that each element appears only once and return the new length. Do not allocate extra space for another array.

## Intuition/Approach
Use two pointers technique: slow pointer tracks position for next unique element, fast pointer scans through array. When different element found, copy it to slow pointer position and advance slow pointer.

## Key Observations
- Array is already sorted, duplicates are adjacent
- Use two pointers: left (slow) for writing, right (fast) for reading
- Only advance left pointer when different element is found
- In-place modification saves space
- Return left + 1 as the new length

## Algorithm Steps
1. Initialize left = 0, right = 0
2. While right < array length:
   - If nums[left] != nums[right]:
     - Increment left pointer
     - Copy nums[right] to nums[left]
   - Increment right pointer
3. Return left + 1 (new array length)

## Complexity Analysis
- **Time Complexity:** O(n) - single pass through array
- **Space Complexity:** O(1) - in-place modification, constant extra space
- **Justification:** Each element visited once by right pointer

## Edge Cases Considered
- [x] Empty array (return 0)
- [x] Single element array (return 1)
- [x] All elements are same (return 1)
- [x] No duplicates (return original length)
- [x] Array with only duplicates at end
- [x] Alternating duplicates pattern

## Solution Code

```java
// Language: Java
class Solution {
    public int removeDuplicates(int[] nums) {
        int left = 0, right = 0;
        while(right < nums.length){
            if(nums[left] != nums[right]){
                left++;
                nums[left] = nums[right];
            }
            right++;
        }
        return left + 1;
    }
}
```

## Alternative Approaches
- **Single Pointer:** Use one pointer and check with previous element
- **Set-based:** Use HashSet but requires extra space
- **Stream API:** Use Java streams for functional approach

## Related Problems
- **AC:** [Array Modification, In-place Operations]
- **Kunal:** [Two Pointers, Array Cleanup]
- **LeetCode:** [26. Remove Duplicates from Sorted Array, 80. Remove Duplicates from Sorted Array II]

## Personal Notes
Fundamental two-pointer problem demonstrating in-place array modification. The key insight is using separate read and write pointers to efficiently remove duplicates while maintaining order. Essential pattern for array manipulation problems.

## Revision History
- **First Solve:** June 26, 2025 - Implemented two-pointer in-place approach

---
**Tags:** #twopointers #inplace #duplicates #sorted #arrays 