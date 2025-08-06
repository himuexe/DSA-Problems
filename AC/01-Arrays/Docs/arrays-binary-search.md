# Binary Search

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given a sorted array of integers and a target value, find the index of the target value in the array. If the target is not found, return -1. The array is sorted in ascending order.

## Intuition/Approach
Use binary search algorithm to efficiently find the target by repeatedly dividing the search space in half. Compare the middle element with the target and eliminate half of the remaining elements based on the comparison.

## Key Observations
- Array must be sorted for binary search to work
- Divide and conquer approach reduces search space by half each iteration
- Use mid = left + (right - left) / 2 to avoid integer overflow
- Three cases: target found, target is smaller (search left), target is larger (search right)

## Algorithm Steps
1. Initialize left = 0 and right = array.length - 1
2. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - If arr[mid] == target, return mid
   - If arr[mid] < target, search right half (left = mid + 1)
   - If arr[mid] > target, search left half (right = mid - 1)
3. If not found, return -1

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Each iteration eliminates half the search space, constant extra space

## Edge Cases Considered
- [x] Empty array - Return -1
- [x] Single element (target found) - Return 0
- [x] Single element (target not found) - Return -1
- [x] Target at beginning - Return 0
- [x] Target at end - Return last index
- [x] Target not in array - Return -1
- [x] Duplicate elements - Return any valid index

## Solution Code
```java
// Language: Java
public static int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
        // Avoid overflow with this calculation
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;  // Target found
        } else if (arr[mid] < target) {
            left = mid + 1;  // Search right half
        } else {
            right = mid - 1;  // Search left half
        }
    }
    return -1;  // Target not found
}
```

## Alternative Approaches
- Linear Search: O(n) time - check each element sequentially
- Recursive Binary Search: O(log n) time, O(log n) space - recursive implementation
- Iterative Binary Search: O(log n) time, O(1) space - optimal solution above

## Personal Notes
Fundamental divide-and-conquer algorithm. Must remember the overflow-safe mid calculation. Foundation for many advanced search problems. Key insight: eliminate half the search space each iteration.

---
**Tags:** #arrays #binarysearch #divideandconquer #searching #logarithmic 