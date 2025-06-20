# Duplicate Number - Kunal Implementation

## Problem Statement
Find the duplicate number in an array of integers where each integer is in the range [1, n] and there is exactly one duplicate.

## Algorithm Overview
Uses **Cyclic Sort** to place numbers at their correct positions, then identify the position where the expected number is not present.

## Key Approach & Intuition
1. **Cyclic Sort**: Attempt to place each number at index `number-1`
2. **Duplicate Detection**: When sorting completes, scan for first mismatch
3. **Index Mismatch**: Position where `nums[i] != i+1` contains the duplicate
4. **Direct Return**: The value at the mismatched position is the duplicate

## Code Implementation
```java
public int findDuplicate(int[] nums) {
    sort(nums);
    for(int i=0;i<nums.length;i++){
        if(nums[i] != i+1){
            return nums[i];
        }
    }
    return -1;
}

public void sort(int[] arr) {
    int i = 0;
    while(i < arr.length) {
        int correctPos = arr[i] - 1; 
        if(arr[i] <= arr.length && arr[i] != arr[correctPos]) {
            swap(arr, i, correctPos);
        } else {
            i++;
        }
    }
}
```

## Key Observations
- **Range Validation**: `arr[i] <= arr.length` handles boundary conditions
- **Duplicate Displacement**: Duplicate value displaces another number from its correct position
- **First Mismatch**: The first position where value ≠ expected index+1 contains duplicate
- **No False Positives**: Algorithm guarantees finding the actual duplicate

## Complexity Analysis
- **Time Complexity**: O(n) - cyclic sort + linear scan
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Duplicate is the smallest number
- Duplicate is the largest number
- Duplicate appears multiple times
- Array with minimum size (n+1 where one number repeats)

## Test Case
```java
Input: [1,3,4,2,2] (duplicate: 2)
After sort: [1,2,4,2,3] (position 2 has wrong value)
Expected Output: 2

Input: [3,1,3,4,2] (duplicate: 3)
Expected Output: 3
```

## Alternative Approaches
1. **Floyd's Cycle Detection**: Treat as linked list cycle problem
2. **Hash Set**: Track seen numbers
3. **Binary Search**: On value range
4. **Bit Manipulation**: XOR-based approach
5. **Mathematical**: Sum comparison

## Advantages of Cyclic Sort Approach
- **Linear Time**: O(n) optimal complexity
- **In-Place**: No additional data structures
- **Simple Logic**: Easy to understand and implement
- **Single Pass**: One sort + one scan

## Disadvantages
- **Array Modification**: Changes original array
- **Range Dependent**: Requires numbers in specific range
- **Not Optimal**: Floyd's algorithm doesn't modify array

## Related Problems
- Find Missing Number
- Find All Missing Numbers
- First Missing Positive
- Set Mismatch

## LeetCode Reference
**Problem 287**: Find the Duplicate Number

## Difficulty Level
**Medium** - Applies cyclic sort to duplicate detection

## Source
Kunal Kushwaha - 10-Sorting Module 