# Set Mismatch - Kunal Implementation

## Problem Statement
Find the number that occurs twice and the number that is missing from a set that originally contained numbers from 1 to n.

## Algorithm Overview
Uses **Cyclic Sort** to arrange numbers, then identifies the first position where the expected number is not present. The value at that position is the duplicate, and the expected value is the missing number.

## Key Approach & Intuition
1. **Cyclic Sort**: Place each number at index `number-1`
2. **Mismatch Detection**: Find first position where `nums[i] != i+1`
3. **Duplicate Identification**: Value at wrong position is the duplicate
4. **Missing Identification**: Expected value at that position is missing

## Code Implementation
```java
public int[] findErrorNums(int[] nums) {
    sort(nums);
    for(int i=0;i< nums.length;i++){
        if(nums[i] != i+1){
            return new int[] {nums[i],i+1};
        }
    }
    return new int[] {-1,-1};
}

public void sort(int[] arr){
    int i=0;
    while(i<arr.length){
        int correctPos=arr[i]-1;
        if(arr[i] != arr[correctPos]){
            swap(arr, i, correctPos);
        }
        else{
            i++;
        }
    }
}
```

## Key Observations
- **Single Scan**: One pass through sorted array finds both numbers
- **Position Logic**: Duplicate occupies wrong position, displacing missing number
- **Direct Return**: `{nums[i], i+1}` gives duplicate and missing respectively
- **Guaranteed Solution**: Problem guarantees exactly one duplicate and one missing

## Complexity Analysis
- **Time Complexity**: O(n) - cyclic sort + linear scan
- **Space Complexity**: O(1) - only uses constant extra space (excluding output)

## Edge Cases Considered
- Duplicate is the smallest number (1)
- Duplicate is the largest number (n)
- Missing number is at the beginning
- Missing number is at the end
- Consecutive duplicate and missing numbers

## Test Case
```java
Input: [1,2,2,4] (duplicate: 2, missing: 3)
After sort: [1,2,2,4] 
At index 2: nums[2]=2 but should be 3
Expected Output: [2,3]

Input: [1,1] (duplicate: 1, missing: 2)
After sort: [1,1]
At index 1: nums[1]=1 but should be 2  
Expected Output: [1,2]
```

## Why Cyclic Sort Works Here
1. **Perfect Mapping**: Numbers 1-n map perfectly to indices 0-(n-1)
2. **Displacement Effect**: Duplicate displaces exactly one other number
3. **Single Violation**: Only one position will have wrong value
4. **Clear Identification**: Wrong value = duplicate, expected value = missing

## Alternative Approaches
1. **Hash Set**: Two passes - find duplicate, then missing
2. **Mathematical**: Sum and sum of squares comparison
3. **XOR Manipulation**: Complex bit manipulation approach
4. **Sorting + Scan**: O(n log n) comparison sort

## Advantages of Cyclic Sort Approach
- **Single Algorithm**: Handles both finding duplicate and missing
- **Linear Time**: Optimal O(n) time complexity
- **In-Place**: No additional data structures needed
- **Simple Logic**: Easy to understand and implement

## Related Problems
- Find Duplicate Number
- Find Missing Number
- Find All Missing Numbers
- First Missing Positive

## LeetCode Reference
**Problem 645**: Set Mismatch

## Difficulty Level
**Easy-Medium** - Straightforward application of cyclic sort pattern

## Source
Kunal Kushwaha - 10-Sorting Module 