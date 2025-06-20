# First Missing Positive - Kunal Implementation

## Problem Statement
Find the smallest missing positive integer in an unsorted integer array. The algorithm must run in O(n) time and use constant extra space.

## Algorithm Overview
Uses **Cyclic Sort** modified to handle positive integers in range [1, n], then scans to find the first missing positive.

## Key Approach & Intuition
1. **Range Filtering**: Only sort positive numbers ≤ array length
2. **Cyclic Sort**: Place each valid number at index `number-1`
3. **Missing Detection**: First index where `nums[i] != i+1`
4. **Boundary Case**: If all positions filled, answer is `n+1`

## Code Implementation
```java
public int firstMissingPositive(int[] nums) {
    sort(nums);
    for(int i=0;i<nums.length;i++){
        if(nums[i] != i+1){
            return i+1;
        }
    }
    return nums.length+1;
}

public void sort(int[] arr){
    int i=0;
    while(i<arr.length){
        int correctPos=arr[i]-1;
        if(arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[correctPos]){
            swap(arr, i, correctPos);
        }
        else{
            i++;
        }
    }
}
```

## Key Observations
- **Triple Condition**: `arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[correctPos]`
- **Positive Filter**: Only positive numbers are sorted
- **Range Limit**: Only numbers ≤ array length are considered
- **Ignore Negatives**: Negative numbers and zeros don't affect result

## Complexity Analysis
- **Time Complexity**: O(n) - each element moved at most once
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- Array with no positive numbers
- Array with all negative numbers
- Missing positive is 1
- Missing positive is larger than array length
- Array contains zeros
- Array contains duplicates

## Test Case
```java
Input: [1,2,0] 
After sort: [1,2,0] (all in correct positions)
Expected Output: 3

Input: [3,4,-1,1]
After sort: [1,-1,3,4] (position 1 has wrong value)
Expected Output: 2

Input: [7,8,9,11,12]
After sort: [7,8,9,11,12] (no valid placements)
Expected Output: 1
```

## Why This Approach Works
1. **Range Insight**: First missing positive must be in range [1, n+1]
2. **Pigeonhole Principle**: n positions can hold at most n distinct values
3. **Optimal Placement**: If number > n, it can't be the answer (answer ≤ n+1)
4. **Gap Detection**: First gap in sequence [1,2,3,...] is the answer

## Alternative Approaches
1. **Hash Set**: O(n) space to track presence
2. **Sorting**: O(n log n) time with comparison sort
3. **Index Marking**: Use array indices as hash, mark with negatives

## Advantages of Cyclic Sort Approach
- **Optimal Time**: O(n) time complexity
- **Optimal Space**: O(1) space complexity
- **In-Place**: No additional data structures
- **Handles Edge Cases**: Robust to negative numbers and large values

## Related Problems
- Find Missing Number
- Find All Missing Numbers
- Find Duplicate Number
- Missing Ranges

## LeetCode Reference
**Problem 41**: First Missing Positive

## Difficulty Level
**Hard** - Requires optimal time/space constraints and edge case handling

## Source
Kunal Kushwaha - 10-Sorting Module 