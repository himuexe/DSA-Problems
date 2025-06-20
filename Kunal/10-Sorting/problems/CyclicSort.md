# Cyclic Sort - Kunal Implementation

## Problem Statement
Implement Cyclic Sort algorithm to sort an array containing numbers from 1 to N.

## Algorithm Overview
**Cyclic Sort** is a specialized sorting algorithm designed for arrays containing numbers in a specific range (1 to N). It places each number at its correct index position.

## Key Approach & Intuition
1. **Index Mapping**: Number `n` should be at index `n-1`
2. **Correct Position**: For each element, calculate where it should be placed
3. **Swap to Position**: If element is not in correct position, swap it
4. **Continue Until Sorted**: Only increment index when element is correctly placed

## Code Implementation
```java
public static void sort(int[] arr) {
    int i = 0;
    while (i < arr.length) {
        int correctPos = arr[i] - 1;
        if (arr[i] != arr[correctPos]) {
            int temp = arr[i];
            arr[i] = arr[correctPos];
            arr[correctPos] = temp;
        } else {
            i++;
        }
    }
}
```

## Key Observations
- **Direct Placement**: Each number is placed directly at its target position
- **No Comparisons**: Unlike comparison-based sorts, uses index calculation
- **Conditional Increment**: Index only increments when element is correctly placed
- **Perfect for Range**: Optimal for consecutive numbers starting from 1

## Complexity Analysis
- **Time Complexity**: O(n) - each element is moved at most once to its correct position
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Array with single element (correct by definition)
- Already sorted array (quick verification)
- Reverse sorted array (maximum swaps needed)
- Array with all elements in wrong positions

## Test Case
```java
Input: [2,1,3,5,4]
Output: [1,2,3,4,5]
```

## Advantages
- **Linear Time**: O(n) time complexity for specific range
- **In-Place**: No additional space required
- **Optimal**: Best possible time complexity for this problem type

## Disadvantages
- **Limited Scope**: Only works for specific range (1 to N)
- **Not General Purpose**: Cannot handle arbitrary arrays
- **Range Dependent**: Requires prior knowledge of value range

## Related Concepts
- Index-based sorting
- Range-specific algorithms
- Cycle detection patterns

## Applications
- Finding missing numbers
- Detecting duplicates
- Set mismatch problems
- First missing positive

## Difficulty Level
**Medium** - Specialized algorithm with specific use cases

## Source
Kunal Kushwaha - 10-Sorting Module 