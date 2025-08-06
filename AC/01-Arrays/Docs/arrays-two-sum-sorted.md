# Two Sum in Sorted Array

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Find two numbers in a sorted array that add up to a specific target. Return their 1-indexed positions. The array is sorted in ascending order.

## Intuition/Approach
Use two pointers technique taking advantage of sorted property. Start with pointers at both ends, move them based on sum comparison with target.

## Key Observations
- Sorted array allows efficient two pointers approach
- If sum is too small, move left pointer right (increase sum)
- If sum is too large, move right pointer left (decrease sum)
- No need for extra space unlike HashMap approach

## Algorithm Steps
1. Initialize left pointer at start (index 0)
2. Initialize right pointer at end (index length-1)
3. While left < right:
   - Calculate sum of elements at both pointers
   - If sum equals target, return 1-indexed positions
   - If sum less than target, increment left pointer
   - If sum greater than target, decrement right pointer

## Complexity Analysis
- **Time Complexity:** O(n) where n is array length
- **Space Complexity:** O(1)
- **Justification:** Single pass with two pointers, no extra data structures

## Edge Cases Considered
- [x] Exactly one solution exists (problem guarantee)
- [x] Solution at array boundaries
- [x] Target requires middle elements
- [x] 1-indexed return format (add 1 to indices)
- [x] Negative numbers in sorted array

## Solution Code
```java
// Language: Java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        while(left<right){
            if(numbers[left]+numbers[right]==target){
                return new int[]{left+1,right+1};
            }
            else if(numbers[left]+numbers[right]<target){
                left++;
            }
            else{
                right--;
            }
        } 
        return new int[]{-1,-1};
    }
}
```

## Alternative Approaches
- HashMap Approach: O(n) time, O(n) space (unnecessary for sorted array)
- Binary Search: For each element, binary search for complement O(n log n)
- Brute Force: Nested loops O(n²) time

## Personal Notes
Optimal solution for sorted arrays. Demonstrates how sorted property enables space-efficient solutions. Two pointers technique is fundamental for many array problems. Note the 1-indexed return requirement.

---
**Tags:** #arrays #twoPointers #sortedArray #optimization #spaceEfficient 