# Next Permutation

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Implement the next permutation algorithm, which rearranges numbers into the lexicographically next greater permutation. If no next permutation exists, rearrange to the smallest permutation (sorted order).

## Intuition/Approach
Find rightmost character that is smaller than its next character, then find the smallest character to its right that is larger than it, swap them, and reverse the suffix to get the lexicographically next permutation.

## Key Observations
- Scan from right to find first decreasing element (pivot)
- Find smallest element to the right that's larger than pivot
- Swap pivot with this element
- Reverse everything to the right of pivot position
- If no pivot found, array is in descending order (largest permutation)

## Algorithm Steps
1. Find pivot: scan from right, find first nums[i] < nums[i+1]
2. If pivot exists:
   - Find smallest element > pivot in suffix
   - Swap pivot with this element
   - Reverse suffix starting from pivot+1
3. If no pivot found:
   - Reverse entire array (next permutation of largest is smallest)

## Complexity Analysis
- **Time Complexity:** O(n) - three linear scans at most
- **Space Complexity:** O(1) - in-place rearrangement
- **Justification:** Each step processes array elements linearly

## Edge Cases Considered
- [x] Array in descending order (wrap to ascending)
- [x] Array in ascending order (swap last two)
- [x] Single element array (no change)
- [x] Two element array
- [x] Array with duplicates
- [x] Already largest permutation

## Solution Code

```java
// Language: Java
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            while(nums[i] >= nums[j]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int[] nums, int i, int j){
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
```

## Alternative Approaches
- **Library Function:** Use built-in next_permutation if available
- **Recursive Generation:** Generate all permutations and find next
- **STL Approach:** Implement using standard library algorithms

## Related Problems
- **AC:** [Permutation Generation, Array Rearrangement]
- **Kunal:** [Array Rotation, String Permutations]
- **LeetCode:** [31. Next Permutation, 46. Permutations, 47. Permutations II]

## Personal Notes
Classic algorithm demonstrating lexicographic ordering principles. The three-step process (find pivot, swap, reverse) efficiently generates the next permutation without generating all permutations. Essential for understanding combinatorial algorithms.

## Revision History
- **First Solve:** June 26, 2025 - Implemented standard next permutation algorithm

---
**Tags:** #permutation #lexicographic #arrays #combinatorics #inplace 