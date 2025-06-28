# Recursive Linear Search - Find All Occurrences

## Problem Statement
Find all indices where a target element occurs in an array using recursive linear search approach.

## Intuition/Approach
**Recursive Traversal with Collection:**
1. **Sequential Examination:** Check each element one by one recursively
2. **Global Collection:** Use static list to collect all matching indices
3. **Base Case:** When index reaches array end, stop recursion
4. **Conditional Addition:** Add index to list if element matches target

**Key Insight:** Linear search naturally maps to recursive traversal with index tracking.

## Key Observations
- **Global State Management:** Uses static ArrayList to maintain results across calls
- **Index-Based Recursion:** Current index parameter drives the recursion
- **All Occurrences Collection:** Unlike single-find, collects all matching positions
- **Linear Traversal:** Visits every element exactly once
- **Simple Base Case:** Array end condition terminates recursion

## Algorithm Steps
1. **Base Case:** If index == array.length - 1, return (traversal complete)
2. **Element Check:** If arr[index] == target:
   - Add current index to global result list
3. **Recursive Call:** Continue with index + 1 to check next element
4. **Result Access:** Static list contains all matching indices after completion

## Time & Space Complexity
- **Time Complexity:** O(n)
  - Visit each array element exactly once
  - Constant time operations per element
- **Space Complexity:** O(n + k)
  - Recursion stack depth: O(n)
  - Result storage: O(k) where k = number of matches
  - Global list storage: O(k)

## Edge Cases Considered
- [x] Target not found in array
- [x] Target appears multiple times
- [x] Target at first position
- [x] Target at last position
- [x] All elements are target
- [x] Single element array
- [x] Empty array (index bounds)

## Code Implementation
```java
static ArrayList<Integer> list = new ArrayList<>();

public static void findAllIndex(int[] arr, int index, int target) {
    if (index == arr.length - 1) {
        return;  // Base case: reached end of array
    }
    
    if (arr[index] == target) {
        list.add(index);  // Add matching index to global list
    }
    
    findAllIndex(arr, index + 1, target);  // Recurse to next element
}

// Usage: 
// list.clear(); // Clear previous results
// findAllIndex(arr, 0, target);
// System.out.println(list); // Print all indices
```

## Example Walkthrough
**Input:** arr = [20, 1, 2, 4, 19, 2, 4], target = 2
**Initial Call:** findAllIndex(arr, 0, 2)

**Traversal Process:**
- index 0: arr[0] = 20 ≠ 2, continue
- index 1: arr[1] = 1 ≠ 2, continue  
- index 2: arr[2] = 2 = 2, add 2 to list
- index 3: arr[3] = 4 ≠ 2, continue
- index 4: arr[4] = 19 ≠ 2, continue
- index 5: arr[5] = 2 = 2, add 5 to list
- index 6: arr[6] = 4 ≠ 2, base case reached

**Final Result:** list = [2, 5]

## Key Learning Points
- **Global State in Recursion:** Using static variables to maintain state across calls
- **Index-Based Iteration:** Converting array iteration to recursive pattern
- **Collection During Traversal:** Gathering results while processing
- **Simple Recursive Pattern:** Straightforward mapping of iterative search

## Applications
- **Pattern Matching:** Finding all occurrences of a pattern
- **Data Analysis:** Locating all instances of specific values
- **Index Tracking:** Maintaining position information for further processing
- **Educational Purpose:** Understanding basic recursive traversal patterns

---
**Date:** June 27, 2025  
**Topic:** Searching Algorithms & Recursion  
**Difficulty:** Beginner  
**Category:** Recursive Traversal 