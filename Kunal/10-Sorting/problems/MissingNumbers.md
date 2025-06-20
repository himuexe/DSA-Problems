# Missing Numbers - Kunal Implementation

## Problem Statement
Find the missing number in an array containing n-1 distinct numbers taken from the range 0 to n.

## Algorithm Overview
Uses **Cyclic Sort** approach to place each number at its correct index, then scan to find the missing position.

## Key Approach & Intuition
1. **Cyclic Sort**: Place each number at its correct index position
2. **Index Mapping**: Number `n` should be at index `n` (0-based)
3. **Missing Detection**: First index that doesn't contain its expected value
4. **Edge Case**: If all positions filled, missing number is `n`

## Code Implementation
```java
public static int findMissing(int[] arr){
    sort(arr);
    for(int i=0;i<arr.length;i++){
        if(arr[i] != i){
            return i;
        }
    }
    return arr.length;
}

public static void sort(int[] arr){
    int i=0;
    while(i<arr.length){
        int correctPos=arr[i];
        if(arr[i] < arr.length && arr[i] != arr[correctPos]){
            swap(arr, i, correctPos);
        }
        else{
            i++;
        }
    }
}
```

## Key Observations
- **Range Check**: `arr[i] < arr.length` handles out-of-range values
- **Direct Index**: Element value equals its target index (0-based)
- **First Gap**: First mismatch between index and value is the answer
- **Boundary Case**: Missing number could be the largest in range

## Complexity Analysis
- **Time Complexity**: O(n) - cyclic sort + linear scan
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Missing number is the smallest (0)
- Missing number is the largest (n)
- Missing number is in the middle
- Array with single element

## Test Case
```java
Input: [3,0,2,1] (missing 4)
Expected Output: 4

Input: [0,1,3] (missing 2)  
Expected Output: 2
```

## Alternative Approaches
1. **Sum Formula**: Total sum - array sum = missing number
2. **XOR Approach**: XOR all numbers and indices
3. **Binary Search**: For sorted arrays
4. **Hash Set**: Mark present numbers, find absent

## Advantages of Cyclic Sort Approach
- **Linear Time**: O(n) optimal complexity
- **In-Place**: No extra space needed
- **Handles Duplicates**: Can be modified for arrays with duplicates
- **Direct Detection**: No mathematical calculations needed

## Related Problems
- Find All Missing Numbers
- First Missing Positive
- Find Duplicate Number
- Set Mismatch

## Difficulty Level
**Easy-Medium** - Requires understanding of cyclic sort pattern

## Source
Kunal Kushwaha - 10-Sorting Module 