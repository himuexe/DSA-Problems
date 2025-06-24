# Merge Sorted Array

**Date:** June 24, 2025  
**Topic:** Arrays  
**Difficulty:** Easy  
**Time Complexity:** O(m + n)  
**Space Complexity:** O(1)  
**Source:** Kunal Kushwaha DSA Course

---

## Problem Statement

Given two sorted integer arrays `nums1` and `nums2`, merge `nums2` into `nums1` as one sorted array. The number of elements initialized in `nums1` and `nums2` are `m` and `n` respectively. You may assume that `nums1` has a size equal to `m + n` such that it has enough space to hold additional elements from `nums2`.

**Example:**
```
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

---

## Approach & Intuition

**Key Insight:** Use two pointers starting from the end of both arrays and fill `nums1` from the back to avoid overwriting elements.

### Algorithm Steps:
1. **Initialize Pointers:** `p1 = m-1` (last element in nums1), `p2 = n-1` (last element in nums2)
2. **Iterate Backwards:** Start from `nums1.length-1` and move towards 0
3. **Compare Elements:** Compare elements at `p1` and `p2` positions
4. **Place Larger Element:** Put the larger element at current position in nums1
5. **Move Pointer:** Decrement the pointer of the array from which element was taken
6. **Handle Edge Cases:** Use `Integer.MIN_VALUE` when pointer goes out of bounds

### Why This Works:
- **No Overwriting:** Filling from the end ensures we don't overwrite unprocessed elements
- **Optimal Space:** In-place merging without extra array
- **Single Pass:** O(m+n) time complexity with one traversal

---

## Code Implementation

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = n-1;
        
        for(int i = nums1.length-1; i >= 0; i--) {
            int valAtp1 = p1 >= 0 ? nums1[p1] : Integer.MIN_VALUE;
            int valAtp2 = p2 >= 0 ? nums2[p2] : Integer.MIN_VALUE;
            
            if(valAtp1 > valAtp2) {
                nums1[i] = valAtp1;
                p1--;
            } else {
                nums1[i] = valAtp2;
                p2--;
            }
        }
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(m + n) - Single pass through both arrays
- **Space Complexity:** O(1) - In-place merging, no extra space needed
- **Optimal Solution:** Yes, cannot do better than O(m+n) time for merging

---

## Edge Cases

- [x] **Empty arrays:** When m=0 or n=0
- [x] **All elements from nums2 smaller:** nums2 elements placed first
- [x] **All elements from nums1 smaller:** nums1 elements remain in place
- [x] **Duplicate elements:** Handled correctly with stable merging
- [x] **Single element arrays:** m=1, n=1 case
- [x] **Pointer bounds:** Integer.MIN_VALUE prevents array access issues

---

## Alternative Approaches

### Approach 1: Forward Merging with Extra Space
- Create temporary array, merge forward, copy back
- Time: O(m+n), Space: O(m+n)
- Less optimal due to extra space requirement

### Approach 2: Sort After Copying
- Copy nums2 to nums1, then sort entire array
- Time: O((m+n)log(m+n)), Space: O(1)
- Less optimal due to higher time complexity

### Approach 3: Insertion Sort Style
- Insert each element from nums2 into correct position in nums1
- Time: O(m*n), Space: O(1)
- Less optimal due to quadratic time complexity

---

## Key Learnings

1. **Backwards Iteration:** Prevents overwriting in in-place algorithms
2. **Two Pointers:** Efficient for merging sorted sequences
3. **Boundary Handling:** Use sentinel values for clean pointer management
4. **In-place Algorithms:** Optimize space by utilizing given array structure

---

## Related Problems

- **LeetCode 88:** Merge Sorted Array (exact same problem)
- **Merge Two Sorted Lists:** Similar merging logic for linked lists
- **Merge k Sorted Arrays:** Extension using heap or divide-and-conquer
- **Sort Colors:** Another in-place array manipulation problem

---

## Notes

- **Interview Frequency:** Very high - common in technical interviews
- **Pattern:** Two pointers, in-place manipulation
- **Follow-up:** Can you do it in O(1) space? (This solution achieves it)
- **Variation:** What if we need to return a new array instead of in-place? 