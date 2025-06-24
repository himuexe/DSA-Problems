# Check if Array is Sorted

**Date:** June 24, 2025  
**Topic:** Recursion  
**Difficulty:** Easy  
**Time Complexity:** O(n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Given an integer array, determine if the array is sorted in non-decreasing (ascending) order using recursion.

Return `true` if the array is sorted, `false` otherwise.

**Example:**
```
Input: arr = [1,2,3,5,4]
Output: false (because 5 > 4, violates ascending order)

Input: arr = [1,2,3,4,5]
Output: true (array is sorted in ascending order)
```

---

## Approach & Intuition

### Recursive Strategy
**Key Insight:** An array is sorted if:
1. **Current element ≤ Next element** (local condition)
2. **Rest of the array is also sorted** (recursive subproblem)

### Algorithm Steps:
1. **Base Case:** If we reach the last element (i == arr.length-1), return true
2. **Violation Check:** If current element > next element, return false immediately
3. **Recursive Case:** Check if rest of array starting from (i+1) is sorted

### Why This Works:
- **Early Termination:** Stop as soon as we find a violation
- **Complete Verification:** If no violation found, entire array is sorted
- **Optimal Checking:** Only need to compare adjacent elements
- **Recursive Decomposition:** Break problem into smaller subproblems

---

## Code Implementation

```java
public class IsSorted {
    public static boolean sorted(int[] arr, int i) {
        // Base case: reached last element
        if(i == arr.length - 1) {
            return true;  // Single element or end reached
        }
        
        // Check current pair
        if(arr[i] > arr[i+1]) {
            return false;  // Violation found
        }
        
        // Recursively check rest of array
        return sorted(arr, i+1);
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,5,4};
        if(sorted(arr, 0)) {
            System.out.println("Sorted hai");
        } else {
            System.out.println("Not sorted");
        }
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(n) - Visit each element once in worst case
- **Space Complexity:** O(n) - Recursion stack depth up to n
- **Best Case:** O(1) - First pair violates sorting (early termination)
- **Worst Case:** O(n) - Array is completely sorted, check all pairs

---

## Recursion Tree Analysis

### Example: arr = [1,2,3,5,4], starting with i=0

```
sorted([1,2,3,5,4], 0)
├── arr[0]=1 ≤ arr[1]=2 ✓, continue
├── sorted([1,2,3,5,4], 1)
    ├── arr[1]=2 ≤ arr[2]=3 ✓, continue
    ├── sorted([1,2,3,5,4], 2)
        ├── arr[2]=3 ≤ arr[3]=5 ✓, continue
        ├── sorted([1,2,3,5,4], 3)
            ├── arr[3]=5 > arr[4]=4 ✗, return false
```

### Example: arr = [1,2,3,4,5], starting with i=0

```
sorted([1,2,3,4,5], 0)
├── arr[0]=1 ≤ arr[1]=2 ✓, continue
├── sorted([1,2,3,4,5], 1)
    ├── arr[1]=2 ≤ arr[2]=3 ✓, continue
    ├── sorted([1,2,3,4,5], 2)
        ├── arr[2]=3 ≤ arr[3]=4 ✓, continue
        ├── sorted([1,2,3,4,5], 3)
            ├── arr[3]=4 ≤ arr[4]=5 ✓, continue
            ├── sorted([1,2,3,4,5], 4)
                ├── i=4 == arr.length-1, return true ✓
```

---

## Edge Cases

- [x] **Empty array:** Not handled - would need arr.length check
- [x] **Single element:** Returns true (base case i == arr.length-1)
- [x] **Two elements sorted:** [1,2] returns true
- [x] **Two elements unsorted:** [2,1] returns false  
- [x] **All elements same:** [3,3,3] returns true (non-decreasing)
- [x] **Reverse sorted:** [5,4,3,2,1] returns false immediately

---

## Alternative Approaches

### Iterative Solution
```java
public static boolean isSortedIterative(int[] arr) {
    for(int i = 0; i < arr.length - 1; i++) {
        if(arr[i] > arr[i+1]) {
            return false;
        }
    }
    return true;
}
```
- **Time:** O(n), **Space:** O(1)
- More space-efficient than recursive version

### Tail Recursive Solution
```java
public static boolean isSortedTailRecursive(int[] arr, int i) {
    if(i >= arr.length - 1) return true;
    return (arr[i] <= arr[i+1]) && isSortedTailRecursive(arr, i+1);
}
```
- Same complexity but more concise

### Stream API Solution (Java 8+)
```java
public static boolean isSortedStream(int[] arr) {
    return IntStream.range(0, arr.length-1)
                   .allMatch(i -> arr[i] <= arr[i+1]);
}
```

---

## Variations

### Check Descending Order
```java
public static boolean sortedDescending(int[] arr, int i) {
    if(i == arr.length - 1) return true;
    if(arr[i] < arr[i+1]) return false;  // Changed condition
    return sortedDescending(arr, i+1);
}
```

### Check Strictly Ascending
```java
public static boolean strictlyAscending(int[] arr, int i) {
    if(i == arr.length - 1) return true;
    if(arr[i] >= arr[i+1]) return false;  // No equal elements allowed
    return strictlyAscending(arr, i+1);
}
```

---

## Key Learnings

1. **Base Case Design:** Use array length bounds for termination
2. **Early Termination:** Return false immediately upon finding violation
3. **Adjacent Comparison:** Only need to check neighboring elements
4. **Recursion Pattern:** Linear traversal with boolean return
5. **Space Trade-off:** Recursive uses O(n) space vs O(1) for iterative

---

## Related Problems

- **Find Unsorted Subarray:** Find minimum subarray to sort entire array
- **Merge Sorted Arrays:** Combine two sorted arrays
- **Binary Search:** Requires sorted array as prerequisite
- **Insertion Sort:** Maintains sorted property during insertion
- **Validate Binary Search Tree:** Similar recursive validation pattern

---

## Notes

- **Interview Frequency:** Low to Medium - basic recursion concept
- **Pattern:** Boolean recursive validation
- **Optimization:** Iterative version preferred for space efficiency
- **Extension:** Can be modified for different sorting criteria
- **Learning Value:** Good introduction to recursive array traversal with conditions 