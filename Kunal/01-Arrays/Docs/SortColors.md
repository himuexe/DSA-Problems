# Sort Colors (Dutch National Flag)

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given an array `nums` with `n` objects colored red, white, or blue, sort them **in-place** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively.

You must solve this problem **without using the library's sort function**.

**Example:**
```
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Input: nums = [2,0,1]
Output: [0,1,2]
```

## Intuition/Approach
**Dutch National Flag Algorithm (Three-Pointer Technique):**
- **Key Insight:** Use three pointers to partition array into three regions
- **Regions:** [0...left-1] = 0s, [left...mid-1] = 1s, [mid...right] = unprocessed, [right+1...n-1] = 2s
- **Invariant maintenance:** Keep the three regions properly partitioned

**Algorithm Logic:**
1. `left` pointer: boundary between 0s and 1s
2. `mid` pointer: current element being processed
3. `right` pointer: boundary between unprocessed and 2s
4. Process elements and maintain invariants through swapping

## Key Observations
- **Three regions:** Maintained throughout the algorithm
- **Single pass:** O(n) time complexity
- **In-place:** O(1) extra space
- **Invariant preservation:** Each operation maintains the partitioning
- **No mid increment for 2s:** New element at mid position needs reprocessing

## Algorithm Steps
1. **Initialize:** `left = 0`, `mid = 0`, `right = nums.length - 1`
2. **Process while mid ≤ right:**
   - **If nums[mid] == 0:** Swap with nums[left], increment both left and mid
   - **If nums[mid] == 1:** Just increment mid (already in correct region)
   - **If nums[mid] == 2:** Swap with nums[right], decrement right (don't increment mid)
3. **Termination:** When mid > right, array is sorted

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array with constant extra space

## Edge Cases Considered
- [x] All elements are same color
- [x] Already sorted array
- [x] Reverse sorted array
- [x] Single element array
- [x] Two element array with different combinations

## Solution Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int left = 0;   // Boundary between 0s and 1s
        int mid = 0;    // Current processing pointer
        int right = nums.length - 1;  // Boundary between 1s and 2s
        
        while(mid <= right) {
            if(nums[mid] == 0) {
                // Swap 0 to the left region
                swap(left, mid, nums);
                left++;
                mid++;
            } else if(nums[mid] == 1) {
                // 1 is already in correct position
                mid++;
            } else {  // nums[mid] == 2
                // Swap 2 to the right region  
                swap(right, mid, nums);
                right--;
                // Don't increment mid - need to process swapped element
            }
        }
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

## Alternative Approaches
1. **Two-Pass Counting Sort:**
```java
public void sortColors(int[] nums) {
    int count0 = 0, count1 = 0, count2 = 0;
    
    // Count occurrences
    for(int num : nums) {
        if(num == 0) count0++;
        else if(num == 1) count1++;
        else count2++;
    }
    
    // Overwrite array
    int index = 0;
    for(int i = 0; i < count0; i++) nums[index++] = 0;
    for(int i = 0; i < count1; i++) nums[index++] = 1;
    for(int i = 0; i < count2; i++) nums[index++] = 2;
}
```
- **Time:** O(n), **Space:** O(1), **Passes:** 2

2. **Library Sort:** `Arrays.sort(nums)` (not allowed by problem constraints)
3. **Bubble Sort:** O(n²) time, O(1) space (for educational purposes)

## Personal Notes
This is a fundamental three-pointer problem that demonstrates the Dutch National Flag algorithm. The key insight is maintaining three regions and carefully managing pointer movements. Named after the Dutch flag colors (red, white, blue), this approach is optimal for three-way partitioning problems. The algorithm preserves invariants throughout execution and achieves O(n) time complexity with O(1) space.

---
**Tags:** #arrays #threepointers #dutchflag #sorting #inplace #medium 