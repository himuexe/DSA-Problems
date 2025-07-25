# Find Largest Element in Array

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an array of integers, find and return the largest element in the array. The array can contain positive, negative, or zero values.

## Intuition/Approach
Traverse the array once while keeping track of the maximum element seen so far. Initialize with the smallest possible integer value and update whenever a larger element is found.

## Key Observations
- Single pass through the array is sufficient
- Initialize with Integer.MIN_VALUE to handle all negative arrays
- Alternative: initialize with first element (arr[0])
- Comparison operation is the key - maintain running maximum

## Algorithm Steps
1. Initialize largest = Integer.MIN_VALUE
2. Iterate through each element in the array
3. If current element > largest, update largest
4. Return largest after traversing all elements

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space for largest variable

## Edge Cases Considered
- [x] Empty array - Handle with proper initialization
- [x] Single element - Returns that element
- [x] All negative numbers - Returns the least negative (largest)
- [x] All positive numbers - Returns the maximum positive
- [x] Mixed positive and negative - Returns the largest overall
- [x] Array with zeros - Handles correctly

## Solution Code

```java
// Language: Java
public static int findLargest(int[] arr) {
    int largest = Integer.MIN_VALUE;
    
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] > largest) {
            largest = arr[i];
        }
    }
    
    return largest;
}
```

## Alternative Approaches
1. **Sorting:** O(n log n) time - sort array and return last element
2. **Min/Max Heap:** O(n) time, O(n) space - build max heap and extract max
3. **Streams (Java 8+):** Arrays.stream(arr).max().orElse(Integer.MIN_VALUE)
4. **Linear Search:** O(n) time, O(1) space - optimal solution above

## Related Problems
- **AC:** Second Largest Element, Smallest Element, Kth Largest
- **Kunal:** Maximum and Minimum in Array
- **LeetCode:** #215 Kth Largest Element, #414 Third Maximum Number

## Personal Notes
- Fundamental array traversal problem
- Good introduction to tracking state while iterating
- Building block for more complex problems like Kth largest
- Important to consider edge cases with negative numbers

## Revision History
- **First Solve:** 2024-12-19 - Implemented linear search approach, understood array traversal
- **Review 1:** (scheduled for 2024-12-20)
- **Review 2:** (to be scheduled)

---
**Tags:** #arrays #traversal #linear #maximum #fundamental 