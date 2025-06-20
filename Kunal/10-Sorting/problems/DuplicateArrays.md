# Duplicate Arrays - Kunal Implementation

## Problem Statement
Find all duplicate numbers in an array of integers where each integer is in the range [1, n] (n = array length).

## Algorithm Overview
Uses **Cyclic Sort** to arrange numbers in their correct positions, then scans to identify all numbers that appear in wrong positions (indicating duplicates).

## Key Approach & Intuition
1. **Cyclic Sort**: Place each number at index `number-1`
2. **Duplicate Detection**: After sorting, scan for positions where `nums[i] != i+1`
3. **Duplicate Collection**: Collect all numbers that don't match their expected positions
4. **Skip Consecutive**: Skip consecutive duplicates to avoid re-adding same duplicate

## Code Implementation
```java
public List<Integer> findDuplicates(int[] nums) {
    List<Integer> list = new ArrayList<>();
    sort(nums);
    for(int i=0;i<nums.length;i++){
        if(nums[i] != i+1){
            list.add(nums[i]);
           while(i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        } 
        }
    }
    return list;
}

public void sort(int[] arr) {
    int i = 0;
    while(i < arr.length) {
        int correctPos = arr[i] - 1; 
        if(arr[i] != arr[correctPos]) {
            swap(arr, i, correctPos);
        } else {
            i++;
        }
    }
}
```

## Key Observations
- **Mismatch Detection**: `nums[i] != i+1` identifies positions with wrong values
- **Duplicate Identification**: Wrong value at position indicates it's a duplicate
- **Consecutive Skip**: `while` loop skips consecutive duplicates to avoid multiple additions
- **Complete Scan**: Checks every position for potential duplicates

## Complexity Analysis
- **Time Complexity**: O(n) - cyclic sort + linear scan with skip
- **Space Complexity**: O(1) excluding output list - constant extra space

## Edge Cases Considered
- No duplicates present (empty result)
- Single duplicate number
- Multiple different duplicates
- Consecutive duplicate numbers
- All numbers are duplicates

## Test Case
```java
Input: [4,3,2,7,8,2,3,1] (length=8, range 1-8)
Duplicates: [2,3] (both appear twice)
Expected Output: [2,3]

Input: [4,3,2,1,2,3] (length=6, range 1-6) 
Duplicates: [2,3] (missing 5,6)
Expected Output: [2,3]

Input: [1,1,2] (duplicate 1, missing 3)
Expected Output: [1]
```

## Algorithm Walkthrough
1. **Sort Phase**: Place numbers using cyclic sort
2. **Scan Phase**: Check each position for mismatch
3. **Collection Phase**: Add mismatched values to result
4. **Skip Phase**: Skip consecutive duplicates to avoid re-adding

## Alternative Approaches
1. **Hash Set**: Track seen numbers, identify duplicates
2. **Negative Marking**: Mark indices by making values negative
3. **Boolean Array**: Use auxiliary array for presence tracking
4. **Sorting + Scan**: O(n log n) comparison sort then scan

## Advantages of Cyclic Sort Approach
- **Linear Time**: O(n) optimal complexity
- **In-Place**: No additional data structures for sorting
- **Complete Detection**: Finds all duplicates in single pass
- **Handles Multiple**: Naturally manages multiple different duplicates

## Related Problems
- Find Duplicate Number (single duplicate)
- Find All Missing Numbers
- Set Mismatch
- First Missing Positive

## LeetCode Reference
**Problem 442**: Find All Duplicates in an Array

## Difficulty Level
**Medium** - Applies cyclic sort with duplicate collection logic

## Source
Kunal Kushwaha - 10-Sorting Module 