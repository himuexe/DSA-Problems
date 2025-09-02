# Duplicate Number

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Medium  

---

## Problem Statement
Find the duplicate number in an array of integers where each integer is in the range [1, n] and there is exactly one duplicate.

## Intuition/Approach
Uses **Cyclic Sort** to place numbers at their correct positions, then identify the position where the expected number is not present.

**Key Insight:** After cyclic sorting, the first position where `nums[i] != i+1` contains the duplicate. The duplicate value displaces another number from its correct position.

## Key Observations
- **Range Validation:** `arr[i] <= arr.length` handles boundary conditions
- **Duplicate Displacement:** Duplicate value displaces another number from its correct position
- **First Mismatch:** The first position where value ≠ expected index+1 contains duplicate
- **No False Positives:** Algorithm guarantees finding the actual duplicate

## Algorithm Steps
1. **Apply Cyclic Sort:** Place each number at index `number-1`
2. **Range Check:** Ensure `arr[i] <= arr.length` before swapping
3. **Scan for Mismatch:** Find first position where `nums[i] != i+1`
4. **Return Duplicate:** The value at the mismatched position is the duplicate

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Cyclic sort takes O(n) time, and linear scan takes O(n) time, totaling O(n). Only constant extra space is used.

## Edge Cases Considered
- [x] Duplicate is the smallest number
- [x] Duplicate is the largest number
- [x] Duplicate appears multiple times
- [x] Array with minimum size (n+1 where one number repeats)

## Solution Code

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

## Alternative Approaches
1. **Floyd's Cycle Detection:** Treat as linked list cycle problem
2. **Hash Set:** Track seen numbers
3. **Binary Search:** On value range
4. **Bit Manipulation:** XOR-based approach
5. **Mathematical:** Sum comparison

## Personal Notes
This problem elegantly demonstrates how cyclic sort can be used for duplicate detection. The key insight is that after sorting, any index-value mismatch directly reveals the duplicate. While Floyd's cycle detection is more optimal for this specific problem (as it doesn't modify the array), the cyclic sort approach provides a clear and intuitive solution that's easy to understand and implement.

---

**Tags:** #sorting #cyclicsort #arrays #duplicates #medium 