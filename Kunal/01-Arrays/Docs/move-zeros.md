# Move Zeroes

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements. Note that you must do this in-place without making a copy of the array.

## Intuition/Approach
Use two-pointer technique: one pointer to track where the next non-zero element should be placed, and another to scan through the array. When we find a non-zero element, we place it at the write pointer and increment it.

## Key Observations
- Maintain relative order of non-zero elements
- Use two pointers: one for writing, one for reading
- Only write non-zero elements
- Fill remaining positions with zeros
- In-place modification required

## Algorithm Steps
1. Initialize write pointer at index 0
2. Iterate through array with read pointer
3. If current element is non-zero, write it at write pointer and increment write pointer
4. After loop, fill remaining positions with zeros
5. Array now has all non-zeros followed by zeros

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Empty array - No change
- [x] Single element (zero) - No change
- [x] Single element (non-zero) - No change
- [x] All zeros - No change
- [x] No zeros - No change
- [x] Mixed zeros and non-zeros - Move zeros to end

## Solution Code

```java
// Language: Java
public static void moveZeroes(int[] nums) {
    int writeIndex = 0;
    
    // Move all non-zero elements to front
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            nums[writeIndex] = nums[i];
            writeIndex++;
        }
    }
    
    // Fill remaining positions with zeros
    while (writeIndex < nums.length) {
        nums[writeIndex] = 0;
        writeIndex++;
    }
}
```

## Alternative Approaches
- **Two-pointer with swap:** Swap non-zero elements with zeros
- **Extra array:** Create new array and copy elements (violates in-place requirement)
- **Count zeros:** Count zeros and shift non-zeros accordingly

## Personal Notes
Classic two-pointer problem that demonstrates in-place array manipulation. The key insight is using separate read and write pointers to efficiently move non-zero elements to the front while maintaining their relative order. This pattern is useful for many array reordering problems.

---
**Tags:** #arrays #twopointers #inplace #easy #zeros 