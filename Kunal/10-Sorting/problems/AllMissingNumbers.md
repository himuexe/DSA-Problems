# All Missing Numbers - Kunal Implementation

## Problem Statement
Find all numbers that are missing from an array of integers where each integer is in the range [1, n] (n = array length).

## Algorithm Overview
Uses **Cyclic Sort** to place numbers at correct positions, then identify all positions that don't contain their expected values.

## Key Approach & Intuition
1. **Cyclic Sort**: Place each number at index `number-1`
2. **Range Mapping**: Numbers 1 to n map to indices 0 to n-1
3. **Gap Detection**: Scan sorted array to find all mismatches
4. **Missing Collection**: Collect all missing numbers in a list

## Code Implementation
```java
public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> list = new ArrayList<>();
    sort(nums);
    for(int i=0;i<nums.length;i++){
        if(nums[i] != i+1){
            list.add(i+1);
        }
    } 
    return list;       
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
- **1-Based Mapping**: Number `n` goes to index `n-1`
- **Mismatch Detection**: `nums[i] != i+1` identifies missing numbers
- **Complete Scan**: Check every position for missing values
- **List Collection**: Efficiently stores all missing numbers

## Complexity Analysis
- **Time Complexity**: O(n) - cyclic sort + linear scan
- **Space Complexity**: O(1) excluding output list - constant extra space

## Edge Cases Considered
- All numbers present (empty result)
- All numbers missing (return 1 to n)
- Single missing number
- Multiple consecutive missing numbers
- Array with duplicates

## Test Case
```java
Input: [4,3,2,7,8,2,3,1] (length=8, range 1-8)
Missing: [5,6]
Expected Output: [5,6]

Input: [1,1] (length=2, range 1-2)
Missing: [2]
Expected Output: [2]
```

## Alternative Approaches
1. **Hash Set**: Mark present numbers, find absent ones
2. **Boolean Array**: Use auxiliary boolean array for marking
3. **Negative Marking**: Mark presence by making values negative
4. **Bit Manipulation**: Use bit vector for presence tracking

## Advantages of Cyclic Sort Approach
- **Linear Time**: O(n) optimal complexity
- **In-Place**: No additional data structures needed
- **Single Pass**: One sort + one scan
- **Handles Duplicates**: Naturally manages duplicate values

## Related Problems
- Find Missing Number
- First Missing Positive
- Find Duplicate Number
- Set Mismatch

## LeetCode Reference
**Problem 448**: Find All Numbers Disappeared in an Array

## Difficulty Level
**Easy-Medium** - Applies cyclic sort pattern to collection problem

## Source
Kunal Kushwaha - 10-Sorting Module 