# First Missing Positive

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Hard  

---

## Problem Statement
Find the smallest missing positive integer in an unsorted integer array. The algorithm must run in O(n) time and use constant extra space.

## Intuition/Approach
Uses **Cyclic Sort** modified to handle positive integers in range [1, n], then scans to find the first missing positive.

**Key Insight:** First missing positive must be in range [1, n+1]. Only sort positive numbers ≤ array length, then find the first gap in sequence [1,2,3,...].

## Key Observations
- **Triple Condition:** `arr[i] > 0 && arr[i] <= arr.length && arr[i] != arr[correctPos]`
- **Positive Filter:** Only positive numbers are sorted
- **Range Limit:** Only numbers ≤ array length are considered
- **Ignore Negatives:** Negative numbers and zeros don't affect result

## Algorithm Steps
1. **Range Filtering:** Only sort positive numbers ≤ array length
2. **Cyclic Sort:** Place each valid number at index `number-1`
3. **Missing Detection:** Find first index where `nums[i] != i+1`
4. **Boundary Case:** If all positions filled, answer is `n+1`

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Each element is moved at most once during sorting, and we use only constant extra space.

## Edge Cases Considered
- [x] Array with no positive numbers
- [x] Array with all negative numbers
- [x] Missing positive is 1
- [x] Missing positive is larger than array length
- [x] Array contains zeros
- [x] Array contains duplicates

## Solution Code

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

## Alternative Approaches
1. **Hash Set:** O(n) space to track presence
2. **Sorting:** O(n log n) time with comparison sort
3. **Index Marking:** Use array indices as hash, mark with negatives

## Personal Notes
This problem demonstrates the power of cyclic sort for constrained range problems. The key insight is that the first missing positive must be in range [1, n+1], allowing us to use the array indices as a hash table. The triple condition in the sorting step ensures we only process relevant numbers while maintaining O(n) time and O(1) space complexity.

---

**Tags:** #sorting #cyclicsort #arrays #missing #hard 