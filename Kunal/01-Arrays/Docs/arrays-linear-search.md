# Linear Search

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an array of integers and a target value, find the index of the target value in the array. If the target is not found, return -1.

## Intuition/Approach
Use linear search: iterate through the array from beginning to end, comparing each element with the target. Return the index when found, or -1 if not found.

## Key Observations
- Simple sequential search
- Check each element one by one
- Return index when target found
- Return -1 if target not found
- Works on any array (sorted or unsorted)

## Algorithm Steps
1. Iterate through array from index 0 to n-1
2. Compare current element with target
3. If equal, return current index
4. If loop completes without finding, return -1

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** May need to check all elements in worst case

## Edge Cases Considered
- [x] Empty array - Return -1
- [x] Single element (target found) - Return 0
- [x] Single element (target not found) - Return -1
- [x] Target at beginning - Return 0
- [x] Target at end - Return last index
- [x] Target not in array - Return -1

## Solution Code

```java
// Language: Java
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}
```

## Alternative Approaches
- **Binary search:** O(log n) but requires sorted array
- **HashSet:** O(1) average but O(n) space
- **Stream API:** Use Java streams for functional approach

## Personal Notes
This is a fundamental search algorithm that demonstrates basic array traversal and comparison. While not the most efficient for large arrays, it's simple to implement and understand. It's also the foundation for more advanced search algorithms and is useful when the array is not sorted.

---
**Tags:** #arrays #search #linear #easy #fundamental
