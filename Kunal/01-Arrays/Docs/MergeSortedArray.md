# Merge Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively. Merge nums2 into nums1 in non-decreasing order.

## Intuition/Approach
Use three-pointer technique starting from the end of both arrays. Compare elements from both arrays and place the larger one at the end of nums1. This avoids overwriting elements in nums1 that haven't been processed yet.

## Key Observations
- nums1 has enough space to hold all elements
- Start from end to avoid overwriting
- Compare elements from both arrays
- Place larger element at end of nums1
- Move pointers accordingly

## Algorithm Steps
1. Initialize pointers: p1 = m-1, p2 = n-1, p = m+n-1
2. While both pointers are valid:
   - Compare nums1[p1] and nums2[p2]
   - Place larger element at nums1[p]
   - Move corresponding pointer and p
3. Copy remaining elements from nums2 if any

## Complexity Analysis
- **Time Complexity:** O(m + n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through both arrays, in-place modification

## Edge Cases Considered
- [x] Empty nums2 - No change to nums1
- [x] Empty nums1 - Copy all from nums2
- [x] All nums1 elements larger - Copy nums2 to beginning
- [x] All nums2 elements larger - nums1 unchanged
- [x] Mixed values - Merge correctly
- [x] Duplicate values - Handle correctly

## Solution Code

```java
// Language: Java
public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1;  // Pointer for nums1
    int p2 = n - 1;  // Pointer for nums2
    int p = m + n - 1;  // Pointer for merged result
    
    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[p--] = nums1[p1--];
        } else {
            nums1[p--] = nums2[p2--];
        }
    }
    
    // Copy remaining elements from nums2
    while (p2 >= 0) {
        nums1[p--] = nums2[p2--];
    }
}
```

## Alternative Approaches
- **Extra space:** Create new array and merge
- **Two-pass:** First copy nums2, then sort
- **Insertion:** Insert nums2 elements one by one

## Personal Notes
This is a classic merge problem that demonstrates the three-pointer technique. The key insight is starting from the end to avoid overwriting elements in nums1 that haven't been processed yet. This approach is optimal in both time and space complexity and is a fundamental pattern for many array merging problems.

---
**Tags:** #arrays #merging #twopointers #easy #sorted 