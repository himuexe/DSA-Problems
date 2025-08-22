# Find First and Last Position of Element in Sorted Array

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  

---

## Problem Statement
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value. If the target is not found in the array, return [-1, -1]. The algorithm must run in O(log n) time complexity.

## Intuition/Approach
Use binary search twice: once to find the first occurrence (leftmost) and once to find the last occurrence (rightmost). The key insight is to modify the standard binary search to continue searching even after finding the target - search left to find first occurrence, search right to find last occurrence.

**Key Insight:** When we find the target during binary search, we don't stop immediately. Instead, we continue searching in the appropriate direction to find the leftmost or rightmost occurrence, ensuring we get the complete range.

## Key Observations
- Requires O(log n) time complexity, so binary search is mandatory
- Need two separate binary searches: one for first, one for last position
- When target is found, continue searching in the appropriate direction
- If first position is not found, no need to search for last position
- Return [-1, -1] if target doesn't exist in array

## Algorithm Steps
1. **Search for first occurrence:** Use binary search, but when target is found, continue searching left
2. **Check first result:** If first occurrence is found, proceed to next step
3. **Search for last occurrence:** Use similar approach, but continue searching right when target found
4. **Return result:** Return [first, last] or [-1, -1] if not found

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Two binary searches, each taking O(log n) time, constant extra space

## Edge Cases Considered
- [x] Empty array - Return [-1, -1]
- [x] Single element (target found) - Return [0, 0]
- [x] Single element (target not found) - Return [-1, -1]
- [x] Target not in array - Return [-1, -1]
- [x] Target appears once - Return [index, index]
- [x] Target appears multiple times - Return [first, last]
- [x] Entire array is the target - Return [0, length-1]

## Solution Code

```java
public int[] searchRange(int[] nums, int target) {
    int[] ans = {-1, -1};
    
    // Find first occurrence
    ans[0] = search(nums, target, true);
    
    if (ans[0] != -1) {
        // Find last occurrence only if first is found
        ans[1] = search(nums, target, false);
    }
    
    return ans;
}

int search(int[] nums, int target, boolean findFirst) {
    int ans = -1;
    int left = 0;
    int right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            ans = mid;  // Found target, but continue searching
            if (findFirst) {
                right = mid - 1;  // Continue searching left for first
            } else {
                left = mid + 1;   // Continue searching right for last
            }
        }
    }
    
    return ans;
}
```

## Alternative Approaches
1. **Linear Search:** O(n) - scan array to find first and last, but too slow
2. **Single Binary Search + Expand:** Find any occurrence, then expand left/right - O(n) worst case
3. **Built-in Functions:** Use Arrays.binarySearch() variants - same complexity but less control
4. **Recursive Binary Search:** Same logic but recursive implementation

## Personal Notes
This is a classic binary search variation that requires careful handling of the "equal" case. The key insight is not to stop searching when the target is found, but to continue in the appropriate direction to find the leftmost or rightmost occurrence. This pattern is fundamental for many advanced binary search problems.

---

**Tags:** #searching #binarysearch #firstandlast #sorted #logarithmic #medium 