# Remove Duplicates from Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given a sorted array, remove the duplicates in-place such that each element appears only once and return the new length. Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

## Intuition/Approach
Use two-pointer technique: one pointer to track the position where the next unique element should be placed, and another to scan through the array. Since the array is sorted, duplicates will be adjacent, making it easy to skip them.

## Key Observations
- Array is sorted, so duplicates are adjacent
- Use two pointers: one for writing unique elements, one for reading
- Only write when we encounter a new unique element
- Return the length of the modified array

## Algorithm Steps
1. Initialize write pointer at index 1 (first element is always unique)
2. Iterate through array starting from index 1
3. If current element is different from previous element, write it at write pointer
4. Increment write pointer
5. Return write pointer as new length

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Empty array - Return 0
- [x] Single element - Return 1
- [x] All elements same - Return 1
- [x] No duplicates - Return original length
- [x] All elements different - Return original length

## Solution Code

```java
// Language: Java
public static int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    
    int writeIndex = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] != nums[i - 1]) {
            nums[writeIndex] = nums[i];
            writeIndex++;
        }
    }
    return writeIndex;
}
```

## Alternative Approaches
- **HashSet approach:** Use HashSet to track seen elements (O(n) space)
- **Extra array:** Create new array with unique elements (O(n) space)
- **Two-pointer with swap:** Swap elements instead of overwriting

## Personal Notes
Classic two-pointer problem. The key insight is that since the array is sorted, we can use the previous element to determine if the current element is unique. This approach is optimal in both time and space complexity.

---
**Tags:** #arrays #twopointers #inplace #easy #duplicates 