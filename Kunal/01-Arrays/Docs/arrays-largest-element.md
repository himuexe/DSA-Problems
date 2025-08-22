# Find Largest Element in Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an array of integers, find and return the largest element in the array.

## Intuition/Approach
Use a single pass through the array, keeping track of the maximum element seen so far. Compare each element with the current maximum and update if a larger element is found.

## Key Observations
- Need to track maximum element
- Single pass through array
- Compare each element with current max
- Update max when larger element found
- Handle edge cases properly

## Algorithm Steps
1. Initialize max = first element
2. Iterate through array starting from second element
3. If current element > max, update max
4. Return max after loop completes

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Empty array - Handle gracefully
- [x] Single element - Return that element
- [x] Two elements - Compare and return larger
- [x] All elements same - Return any element
- [x] Negative numbers - Handle correctly
- [x] Large numbers - Handle overflow

## Solution Code

```java
// Language: Java
public static int findLargest(int[] arr) {
    if (arr.length == 0) {
        throw new IllegalArgumentException("Array cannot be empty");
    }
    
    int max = arr[0];
    
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    
    return max;
}
```

## Alternative Approaches
- **Sorting:** Sort array and return last element O(n log n)
- **Stream API:** Use Java streams for functional approach
- **Recursive:** Divide and conquer approach

## Personal Notes
This is a fundamental problem that demonstrates basic array traversal and comparison. The key insight is using a single variable to track the maximum and updating it when we find a larger element. This approach is optimal in both time and space complexity and is a building block for many other array problems.

---
**Tags:** #arrays #maximum #traversal #easy #fundamental
