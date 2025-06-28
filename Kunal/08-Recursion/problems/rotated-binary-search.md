# Rotated Binary Search - Recursive Search in Rotated Array

## Problem Statement
Find the index of a target element in a rotated sorted array using recursive binary search approach.

## Intuition/Approach
**Modified Binary Search Strategy:**
1. **Identify Sorted Half:** One half of the array is always sorted in rotated array
2. **Target Range Check:** Determine if target lies in the sorted half
3. **Recursive Search:** Search in appropriate half based on target location
4. **Base Case:** Element found or search space exhausted

**Key Insight:** Despite rotation, one half is always sorted, allowing binary search logic.

## Key Observations
- **Rotated Array Property:** One half is always sorted, other may contain rotation point
- **Range-Based Decision:** Use sorted half's range to determine search direction
- **Binary Search Optimization:** O(log n) complexity maintained despite rotation
- **Multiple Conditions:** Need to handle various rotation scenarios
- **Index-Based Return:** Returns index position, not just boolean found/not found

## Algorithm Steps
1. **Base Case:** If start > end, return -1 (element not found)
2. **Calculate Middle:** mid = start + (end - start) / 2
3. **Direct Match:** If arr[mid] == target, return mid
4. **Identify Sorted Half:**
   - If arr[start] <= arr[mid]: Left half is sorted
   - Otherwise: Right half is sorted
5. **Search Decision:**
   - If target in sorted half range: Search that half
   - Otherwise: Search the other half
6. **Recursive Calls:** Continue with appropriate range

## Time & Space Complexity
- **Time Complexity:** O(log n)
  - Each recursive call eliminates half the search space
  - Maximum recursion depth: O(log n)
- **Space Complexity:** O(log n)
  - Recursion stack depth: O(log n)
  - No additional data structures used

## Edge Cases Considered
- [x] Target not in array
- [x] Array not rotated (normal sorted array)
- [x] Single element array
- [x] Target at rotation point
- [x] Target at array boundaries
- [x] Multiple occurrences of target
- [x] Empty array

## Code Implementation
```java
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
        // Right half is sorted (note: missing return in original code)
        if (target >= arr[m] && target <= arr[e]) {
            return search(arr, target, m + 1, e);  // Search right half
        } else {
            return search(arr, target, s, m - 1);  // Search left half
        }
    }
}

// Usage: search(arr, target, 0, arr.length - 1)
```

## Example Walkthrough
**Input:** arr = [4, 5, 6, 7, 0, 1, 2], target = 0
**Initial Call:** search(arr, 0, 0, 6)

**Step 1:** mid = 3, arr[3] = 7, target ≠ 7
- Left half [4,5,6,7] is sorted (4 ≤ 7)
- Target 0 not in range [4,7]
- Search right half: search(arr, 0, 4, 6)

**Step 2:** mid = 5, arr[5] = 1, target ≠ 1  
- Right half [1,2] is sorted (1 ≤ 2)
- Target 0 not in range [1,2]
- Search left half: search(arr, 0, 4, 4)

**Step 3:** mid = 4, arr[4] = 0, target = 0
- **Found!** Return index 4

## Key Learning Points
- **Rotated Array Analysis:** Understanding sorted half identification
- **Binary Search Adaptation:** Modifying classic binary search for special cases  
- **Condition Logic:** Multiple if-else conditions for different scenarios
- **Recursive Problem Decomposition:** Breaking down complex search space

## Applications
- **System Design:** Searching in circular/rotated data structures
- **Database Indexing:** Handling rotated B-tree scenarios
- **Competitive Programming:** Common interview and contest problem
- **Algorithm Optimization:** Maintaining logarithmic complexity in modified arrays

---
**Date:** June 27, 2025  
**Topic:** Searching Algorithms & Recursion  
**Difficulty:** Intermediate-Advanced  
**Category:** Modified Binary Search 