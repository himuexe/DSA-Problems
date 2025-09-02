# Rotated Binary Search - Recursive Search in Rotated Array

**Source:** Kunal Recursion | **Topic:** Searching, Recursion | **Difficulty:** Intermediate-Advanced

---

## Problem Statement
Find the index of a target element in a rotated sorted array using a recursive binary search approach.

## Intuition/Approach
The approach modifies binary search to handle rotation:
- **Sorted Half Identification:** One half of the array is always sorted.
- **Range Check:** Determine if the target lies in the sorted half.
- **Recursive Search:** Narrow the search to the appropriate half.
- The base case is when the search space is exhausted or the target is found.

## Key Observations
- One half of the array is always sorted, enabling binary search logic.
- The rotation point complicates the decision but doesn’t affect time complexity.
- The algorithm maintains O(log n) time by halving the search space.
- Multiple conditions handle various rotation scenarios.
- Returns the index of the target or -1 if not found.

## Algorithm Steps
1. If start > end, return -1 (target not found).
2. Calculate mid = start + (end - start) / 2.
3. If arr[mid] == target, return mid.
4. Check if left half [start, mid] is sorted (arr[start] <= arr[mid]).
   - If target is in left half range, search [start, mid-1].
   - Else, search [mid+1, end].
5. If right half [mid, end] is sorted:
   - If target is in right half range, search [mid+1, end].
   - Else, search [start, mid-1].

## Complexity Analysis
- **Time Complexity:** O(log n) - Each recursive call halves the search space.
- **Space Complexity:** O(log n) - Due to recursion stack depth.
- **Justification:** The binary search structure ensures logarithmic time, with recursion stack as the primary space cost.

## Edge Cases Considered
- [x] Empty input.
- [x] Single element array.
- [x] Target not in array.
- [x] Array not rotated (normal sorted array).
- [x] Target at rotation point or boundaries.

## Solution Code
```java
public class RotatedBinarySearch {
    static int search(int[] arr, int target, int s, int e) {
        if (s > e) {
            return -1;  // Base case: element not found
        }
        
        int m = s + (e - s) / 2;
        
        if (arr[m] == target) {
            return m;  // Element found at middle
        }
        
        // Check which half is sorted
        if (arr[s] <= arr[m]) {
            // Left half is sorted
            if (target >= arr[s] && target <= arr[m]) {
                return search(arr, target, s, m - 1);  // Search left half
            } else {
                return search(arr, target, m + 1, e);  // Search right half
            }
        } else {
            // Right half is sorted
            if (target >= arr[m] && target <= arr[e]) {
                return search(arr, target, m + 1, e);  // Search right half
            } else {
                return search(arr, target, s, m - 1);  // Search left half
            }
        }
    }
    // Usage: search(arr, target, 0, arr.length - 1)
}
```

## Alternative Approaches
- **Iterative Binary Search:** Avoid recursion for better space efficiency.
- **Find Rotation Point First:** Locate the pivot, then apply standard binary search.
- **Two-Pass Approach:** Find the minimum element (rotation point) and adjust search accordingly.

## Personal Notes
- Identifying the sorted half was the key insight that made the algorithm click.
- The multiple if-else conditions require careful handling to avoid logical errors.
- This problem is a great example of adapting a classic algorithm to a modified scenario.

---
**Tags:** #searching #recursion #binary_search #rotated_array