# Linear Search

**Source:** AC | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an array of integers and a target value, find the index of the target value in the array. If the target is not found, return -1. The array can be unsorted.

## Intuition/Approach
Check each element in the array sequentially from left to right until the target is found or the end of the array is reached. This is the simplest search algorithm and works on both sorted and unsorted arrays.

## Key Observations
- Works on unsorted arrays (unlike binary search)
- Checks elements one by one in order
- Stops as soon as target is found (early termination)
- Returns first occurrence if there are duplicates
- Time complexity is proportional to array size

## Algorithm Steps
1. Start from the first element (index 0)
2. Compare current element with target
3. If match found, return current index
4. If not found, move to next element
5. If end of array reached without finding target, return -1

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** May need to check all elements in worst case, constant extra space

## Edge Cases Considered
- [x] Empty array - Return -1
- [x] Single element (target found) - Return 0
- [x] Single element (target not found) - Return -1
- [x] Target at beginning - Return 0 (best case)
- [x] Target at end - Return last index (worst case)
- [x] Target not in array - Return -1
- [x] Multiple occurrences - Return first occurrence

## Solution Code
```java
// Language: Java
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;  // Target found at index i
        }
    }
    return -1;  // Target not found
}
```

## Alternative Approaches
- Binary Search: O(log n) time - but requires sorted array
- Hash Table: O(1) search time - but requires O(n) preprocessing space
- Enhanced Linear Search: Use sentinel values or two-pointer optimization
- Recursive Linear Search: O(n) time, O(n) space - recursive implementation

## Personal Notes
Most basic search algorithm - foundation for understanding searching. Only search algorithm that works on unsorted data. Often used as baseline for comparing other search algorithms. Important to understand when to use linear vs binary search.

---
**Tags:** #arrays #searching #linear #fundamental #unsorted 