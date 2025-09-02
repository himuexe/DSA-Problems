# Recursive Linear Search - Find All Occurrences

**Source:** Kunal Recursion | **Topic:** Searching, Recursion | **Difficulty:** Beginner

---

## Problem Statement
Find all indices where a target element appears in an array using a recursive linear search approach.

## Intuition/Approach
The approach uses recursive traversal to check each element:
- **Index-Based Recursion:** Process the array sequentially by incrementing the index.
- **Global State:** Use a static ArrayList to collect all matching indices.
- **Base Case:** Stop when the index reaches the array's end.
- The recursion mimics a linear scan, collecting indices where the target is found.

## Key Observations
- A static ArrayList maintains results across recursive calls.
- The recursion is linear, visiting each element exactly once.
- The approach is simple but effective for collecting all occurrences.
- The base case is straightforward, checking the end of the array.
- The method is flexible for handling multiple occurrences or none.

## Algorithm Steps
1. If index == array.length - 1, return (end of traversal).
2. If arr[index] == target, add index to the global result list.
3. Recursively call with index + 1 to check the next element.
4. After recursion completes, the global list contains all matching indices.

## Complexity Analysis
- **Time Complexity:** O(n) - Each element is visited once.
- **Space Complexity:** O(n + k) - O(n) for recursion stack, O(k) for storing k matching indices.
- **Justification:** Linear traversal ensures O(n) time, while the recursion stack and result list contribute to space complexity.

## Edge Cases Considered
- [x] Empty input.
- [x] Single element array.
- [x] Target not found in array.
- [x] Target appears multiple times.
- [x] Target at first or last position.
- [x] All elements are the target.

## Solution Code
```java
public class RecursiveLinearSearch {
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
    // Usage: list.clear(); findAllIndex(arr, 0, target);
}
```

## Alternative Approaches
- **Iterative Linear Search:** Simpler and avoids recursion stack overhead.
- **Binary Search (if sorted):** O(log n) for sorted arrays, but only finds one occurrence.
- **Stream API (Java):** Use Java streams for a functional approach, though less educational for recursion.

## Personal Notes
- The use of a static list for collecting results was initially confusing but simplifies state management across recursive calls.
- Clearing the list before a new search is critical to avoid stale data.
- This problem is an excellent introduction to recursive traversal and state accumulation.

---
**Tags:** #searching #recursion #linear_search