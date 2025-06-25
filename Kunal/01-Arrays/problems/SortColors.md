# Sort Colors (Dutch National Flag)

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Two Pointers/Sorting

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

## Algorithm Steps

1. **Initialize:** `left = 0`, `mid = 0`, `right = nums.length - 1`
2. **Process while mid ≤ right:**
   - **If nums[mid] == 0:** Swap with nums[left], increment both left and mid
   - **If nums[mid] == 1:** Just increment mid (already in correct region)
   - **If nums[mid] == 2:** Swap with nums[right], decrement right (don't increment mid)
3. **Termination:** When mid > right, array is sorted

## Key Observations

- **Three regions:** Maintained throughout the algorithm
- **Single pass:** O(n) time complexity
- **In-place:** O(1) extra space
- **Invariant preservation:** Each operation maintains the partitioning
- **No mid increment for 2s:** New element at mid position needs reprocessing

## Time & Space Complexity

- **Time Complexity:** O(n) - Single pass through array
- **Space Complexity:** O(1) - Only constant extra space for pointers

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

## Algorithm Visualization

### Example: nums = [2,0,2,1,1,0]

**Initial State:**
```
[2,0,2,1,1,0]
 ↑     ↑   ↑
left  mid right
```

**Step 1:** nums[mid]=2, swap with nums[right]
```
[0,0,2,1,1,2]
 ↑   ↑ ↑
left mid right
```

**Step 2:** nums[mid]=2, swap with nums[right]
```
[0,0,1,1,2,2]
 ↑   ↑↑
left mid,right
```

**Step 3:** nums[mid]=1, increment mid
```
[0,0,1,1,2,2]
 ↑     ↑
left   mid>right (done)
```

## Dutch National Flag Algorithm Invariants

### Region Definitions
1. **[0...left-1]:** All elements are 0 (red region)
2. **[left...mid-1]:** All elements are 1 (white region)  
3. **[mid...right]:** Unprocessed elements
4. **[right+1...n-1]:** All elements are 2 (blue region)

### Why It Works
- **Invariant preservation:** Each operation maintains the regional boundaries
- **Complete coverage:** Every element gets processed exactly once
- **Optimal swaps:** Minimal number of swaps needed for sorting

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

2. **Library Sort:**
```java
public void sortColors(int[] nums) {
    Arrays.sort(nums);  // Not allowed by problem constraints
}
```

3. **Bubble Sort (for educational purposes):**
```java
public void sortColors(int[] nums) {
    for(int i = 0; i < nums.length - 1; i++) {
        for(int j = 0; j < nums.length - 1 - i; j++) {
            if(nums[j] > nums[j+1]) {
                swap(j, j+1, nums);
            }
        }
    }
}
```
- **Time:** O(n²), **Space:** O(1)

## Why Dutch National Flag?

### Historical Context
- **Named after:** Dutch flag colors (red, white, blue)
- **Proposed by:** Edsger Dijkstra
- **Problem:** Efficiently partition array into three categories
- **Applications:** Sorting, partitioning, three-way quicksort

### Algorithm Properties
- **Optimal:** Minimum number of comparisons and swaps
- **In-place:** No extra space required
- **Single-pass:** One traversal through data
- **Stable regions:** Maintains order within each color group

## Performance Analysis

### Comparison with Other Approaches
| Approach | Time | Space | Passes | Stability |
|----------|------|-------|--------|-----------|
| **Dutch Flag** | O(n) | O(1) | 1 | No |
| **Counting Sort** | O(n) | O(1) | 2 | Yes |
| **Library Sort** | O(n log n) | O(log n) | 1 | Depends |
| **Bubble Sort** | O(n²) | O(1) | n | Yes |

### When to Use Dutch Flag
- **Three categories:** Perfect for three-way partitioning
- **In-place requirement:** When extra space is not available
- **Single pass needed:** When multiple passes are expensive
- **Unstable sorting acceptable:** When original order doesn't matter

## Key Learnings

1. **Three-pointer technique:** Powerful for three-way partitioning
2. **Invariant maintenance:** Keep regions properly defined throughout
3. **Careful pointer management:** Know when to increment each pointer
4. **Dutch flag pattern:** Applicable to many partitioning problems
5. **Optimal sorting:** Sometimes problem constraints allow better than O(n log n)

## Related Problems

- Partition Array into Three Parts With Equal Sum
- Sort Array By Parity
- Sort Array By Parity II  
- 3Sum (uses similar partitioning concepts)
- Quick Sort (three-way partitioning variant)

**LeetCode Connection:** LeetCode #75 - Sort Colors 