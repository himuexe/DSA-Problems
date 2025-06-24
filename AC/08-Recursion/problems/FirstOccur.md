# First and Last Occurrence in Array

**Date:** June 24, 2025  
**Topic:** Recursion  
**Difficulty:** Easy  
**Time Complexity:** O(n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Given an array and a target element, find:
1. **First Occurrence:** Index of the first occurrence of target element
2. **Last Occurrence:** Index of the last occurrence of target element

Return -1 if the element is not found in the array.

**Example:**
```
Input: arr = [1,2,3,4,5,3,7,8], target = 3
Output: 
- First occurrence: 2
- Last occurrence: 5
```

---

## Approach & Intuition

### First Occurrence Algorithm
**Recursive Strategy:** Traverse from left to right (index 0 to n-1)
- **Base Case:** If we reach end of array (i == arr.length), return -1
- **Found Case:** If current element equals target, return current index
- **Recursive Case:** Continue searching in remaining array

### Last Occurrence Algorithm  
**Recursive Strategy:** Traverse from right to left (index n-1 to 0)
- **Base Case:** If we reach before start of array (i == 0), return -1
- **Found Case:** If current element equals target, return current index
- **Recursive Case:** Continue searching in remaining array

### Why This Approach Works
- **First Occurrence:** Forward traversal ensures we find leftmost match
- **Last Occurrence:** Backward traversal ensures we find rightmost match
- **Early Termination:** Both algorithms stop as soon as they find a match
- **Complete Coverage:** Both algorithms check every element if needed

---

## Code Implementation

```java
public class FirstOccur {
    // Find first occurrence (left to right)
    public static int firstindex(int[] arr, int i, int target) {
        if(i == arr.length) {
            return -1;  // Reached end, element not found
        }
        if(arr[i] == target) {
            return i;   // Found at current index
        }
        return firstindex(arr, i+1, target);  // Search in remaining array
    }
    
    // Find last occurrence (right to left)
    public static int lastindex(int[] arr, int i, int target) {
        if(i == 0) {
            return -1;  // Reached start, element not found
        }
        if(arr[i] == target) {
            return i;   // Found at current index
        }
        return lastindex(arr, i-1, target);  // Search in remaining array
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(n) - In worst case, we traverse entire array
- **Space Complexity:** O(n) - Recursion stack depth can be up to n
- **Best Case:** O(1) - Element found at first/last position respectively
- **Worst Case:** O(n) - Element not present or at opposite end

---

## Recursion Tree Analysis

### First Occurrence Example: arr = [1,2,3,4,5,3,7,8], target = 3
```
firstindex([1,2,3,4,5,3,7,8], 0, 3)
├── arr[0] = 1 ≠ 3, continue
├── firstindex([1,2,3,4,5,3,7,8], 1, 3)
    ├── arr[1] = 2 ≠ 3, continue
    ├── firstindex([1,2,3,4,5,3,7,8], 2, 3)
        ├── arr[2] = 3 = 3, return 2 ✓
```

### Last Occurrence Example: arr = [1,2,3,4,5,3,7,8], target = 3
```
lastindex([1,2,3,4,5,3,7,8], 7, 3)
├── arr[7] = 8 ≠ 3, continue
├── lastindex([1,2,3,4,5,3,7,8], 6, 3)
    ├── arr[6] = 7 ≠ 3, continue
    ├── ... (continue until index 5)
    ├── lastindex([1,2,3,4,5,3,7,8], 5, 3)
        ├── arr[5] = 3 = 3, return 5 ✓
```

---

## Edge Cases

- [x] **Empty array:** arr.length = 0, both return -1
- [x] **Single element match:** Both return same index
- [x] **Single element no match:** Both return -1
- [x] **Element not present:** Both return -1
- [x] **All elements same:** First returns 0, last returns n-1
- [x] **Target at boundaries:** First at index 0, last at index n-1

---

## Alternative Approaches

### Iterative Solutions
```java
// First occurrence - iterative
public static int firstOccurIterative(int[] arr, int target) {
    for(int i = 0; i < arr.length; i++) {
        if(arr[i] == target) return i;
    }
    return -1;
}

// Last occurrence - iterative
public static int lastOccurIterative(int[] arr, int target) {
    for(int i = arr.length-1; i >= 0; i--) {
        if(arr[i] == target) return i;
    }
    return -1;
}
```

### Single Pass Solution
```java
// Find both in single pass
public static int[] findBothOccurrences(int[] arr, int target) {
    int first = -1, last = -1;
    for(int i = 0; i < arr.length; i++) {
        if(arr[i] == target) {
            if(first == -1) first = i;  // First occurrence
            last = i;  // Keep updating last
        }
    }
    return new int[]{first, last};
}
```

### Binary Search (For Sorted Arrays)
- Time: O(log n), Space: O(1)
- Use modified binary search to find first and last positions

---

## Key Learnings

1. **Recursion Direction:** Forward for first, backward for last occurrence
2. **Base Case Design:** Different base cases for different traversal directions
3. **Early Termination:** Return immediately when target is found
4. **Index Management:** Careful handling of array bounds in recursion
5. **Space Trade-off:** Recursive solution uses O(n) space vs O(1) for iterative

---

## Related Problems

- **Binary Search:** Find element in sorted array
- **Search in Rotated Array:** Find element in rotated sorted array
- **Find Peak Element:** Similar recursive array traversal
- **Count Occurrences:** Extension to count total occurrences
- **Find All Indices:** Return all indices where element occurs

---

## Notes

- **Interview Frequency:** Medium - good for testing recursion understanding
- **Pattern:** Linear array traversal with recursion
- **Optimization:** Iterative version more space-efficient
- **Extension:** Can be modified to find all occurrences
- **Variation:** Works for any comparable data type, not just integers 