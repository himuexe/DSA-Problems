# Reverse Array

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an array of integers, reverse the array in-place. The first element should become the last, the second element should become the second-to-last, and so on.

## Intuition/Approach
Use the two-pointer technique with pointers at the beginning and end of the array. Swap elements and move pointers toward the center until they meet. This achieves reversal in-place with optimal time complexity.

## Key Observations
- In-place reversal requires swapping elements from both ends
- Two pointers eliminate the need for additional space
- Stop when pointers meet or cross (left >= right)
- Works for both even and odd length arrays

## Algorithm Steps
1. Initialize left pointer at index 0 and right pointer at last index
2. While left < right:
   - Swap elements at left and right positions
   - Increment left pointer
   - Decrement right pointer
3. Array is now reversed in-place

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Visit each element once, constant extra space for pointers and temp variable

## Edge Cases Considered
- [x] Empty array - No operation needed
- [x] Single element - No operation needed
- [x] Two elements - Single swap
- [x] Odd length array - Middle element stays in place
- [x] Even length array - All elements are swapped

## Solution Code

```java
// Language: Java
public static void reverseArray(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left < right) {
        // Swap elements at left and right positions
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        
        // Move pointers toward center
        left++;
        right--;
    }
}
```

## Alternative Approaches
1. **Extra Space:** O(n) space - create new array and copy in reverse order
2. **Recursion:** O(n) space - recursive function with stack overhead
3. **Two Pointers:** O(1) space - optimal solution implemented above
4. **Stack:** O(n) space - push all elements, then pop back to array

## Related Problems
- **AC:** Rotate Array, Palindrome Array
- **Kunal:** Array Rotation, String Reversal
- **LeetCode:** #344 Reverse String, #189 Rotate Array

## Personal Notes
- Fundamental two-pointer technique problem
- Foundation for many array manipulation problems
- Important to understand in-place operations
- Pattern reused in palindrome checking and array rotation

## Revision History
- **First Solve:** 2024-12-19 - Implemented two-pointer approach, understood in-place operations
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #twopointers #inplace #easy #fundamental 