# Contains Duplicate

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

## Intuition/Approach
Use a HashSet to track seen elements. Iterate through the array, and if we encounter an element we've seen before, return true. If we finish the iteration without finding duplicates, return false.

## Key Observations
- Need to detect if any element appears more than once
- HashSet provides O(1) lookup and insertion
- Return true as soon as first duplicate is found
- Return false if no duplicates found
- Simple one-pass solution

## Algorithm Steps
1. Initialize empty HashSet
2. Iterate through array elements
3. If element exists in HashSet, return true
4. Otherwise, add element to HashSet
5. If loop completes, return false

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass through array, HashSet may store all elements

## Edge Cases Considered
- [x] Empty array - Return false
- [x] Single element - Return false
- [x] Two elements (same) - Return true
- [x] Two elements (different) - Return false
- [x] All elements same - Return true
- [x] All elements different - Return false

## Solution Code

```java
// Language: Java
public static boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    
    for (int num : nums) {
        if (seen.contains(num)) {
            return true;
        }
        seen.add(num);
    }
    
    return false;
}
```

## Alternative Approaches
- **Sorting:** Sort array then check adjacent elements O(n log n) time
- **Brute force:** Check all pairs O(n²) time
- **Bit manipulation:** For small range integers

## Personal Notes
This is a classic problem that demonstrates the power of HashSet for duplicate detection. The key insight is that we can use a HashSet to track seen elements and detect duplicates in a single pass. This approach is optimal in time complexity and is a common pattern for many duplicate-related problems.

---
**Tags:** #arrays #hashset #duplicates #easy #searching 