# Duplicate Arrays

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Medium  

---

## Problem Statement
Find all duplicate numbers in an array of integers where each integer is in the range [1, n] (n = array length).

## Intuition/Approach
Uses **Cyclic Sort** to arrange numbers in their correct positions, then scans to identify all numbers that appear in wrong positions (indicating duplicates).

**Key Insight:** After cyclic sorting, positions where `nums[i] != i+1` contain duplicates. Collect all mismatched values while skipping consecutive duplicates to avoid multiple additions.

## Key Observations
- **Mismatch Detection:** `nums[i] != i+1` identifies positions with wrong values
- **Duplicate Identification:** Wrong value at position indicates it's a duplicate
- **Consecutive Skip:** `while` loop skips consecutive duplicates to avoid multiple additions
- **Complete Scan:** Checks every position for potential duplicates

## Algorithm Steps
1. **Apply Cyclic Sort:** Place each number at index `number-1`
2. **Scan for Mismatches:** Check each position for `nums[i] != i+1`
3. **Collect Duplicates:** Add mismatched values to result list
4. **Skip Consecutive:** Skip consecutive duplicates to avoid re-adding same duplicate

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) excluding output list
- **Justification:** Cyclic sort takes O(n) time, and linear scan with skip takes O(n) time, totaling O(n). Only constant extra space is used for sorting.

## Edge Cases Considered
- [x] No duplicates present (empty result)
- [x] Single duplicate number
- [x] Multiple different duplicates
- [x] Consecutive duplicate numbers
- [x] All numbers are duplicates

## Solution Code

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

## Alternative Approaches
1. **Hash Set:** Track seen numbers, identify duplicates
2. **Negative Marking:** Mark indices by making values negative
3. **Boolean Array:** Use auxiliary array for presence tracking
4. **Sorting + Scan:** O(n log n) comparison sort then scan

## Personal Notes
This problem extends the single duplicate detection to finding all duplicates. The key insight is that after cyclic sorting, any position-value mismatch indicates a duplicate. The consecutive duplicate skipping logic is crucial to avoid adding the same duplicate multiple times. This approach maintains O(n) time complexity while finding all duplicates in a single pass.

---

**Tags:** #sorting #cyclicsort #arrays #duplicates #medium 