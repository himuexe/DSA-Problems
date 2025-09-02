# All Missing Numbers

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Easy-Medium  

---

## Problem Statement
Find all numbers that are missing from an array of integers where each integer is in the range [1, n] (n = array length).

## Intuition/Approach
Uses **Cyclic Sort** to place numbers at correct positions, then identify all positions that don't contain their expected values.

**Key Insight:** Numbers 1 to n map to indices 0 to n-1. After sorting, any position where `nums[i] != i+1` indicates a missing number. Collect all missing numbers by scanning for these mismatches.

## Key Observations
- **1-Based Mapping:** Number `n` goes to index `n-1`
- **Mismatch Detection:** `nums[i] != i+1` identifies missing numbers
- **Complete Scan:** Check every position for missing values
- **List Collection:** Efficiently stores all missing numbers

## Algorithm Steps
1. **Apply Cyclic Sort:** Place each number at index `number-1`
2. **Scan for Mismatches:** Check each position for `nums[i] != i+1`
3. **Collect Missing:** Add `i+1` to result list for each mismatch
4. **Return Result:** Return list of all missing numbers

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) excluding output list
- **Justification:** Cyclic sort takes O(n) time, and linear scan takes O(n) time, totaling O(n). Only constant extra space is used for sorting.

## Edge Cases Considered
- [x] All numbers present (empty result)
- [x] All numbers missing (return 1 to n)
- [x] Single missing number
- [x] Multiple consecutive missing numbers
- [x] Array with duplicates

## Solution Code

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

## Alternative Approaches
1. **Hash Set:** Mark present numbers, find absent ones
2. **Boolean Array:** Use auxiliary boolean array for marking
3. **Negative Marking:** Mark presence by making values negative
4. **Bit Manipulation:** Use bit vector for presence tracking

## Personal Notes
This problem demonstrates how cyclic sort can efficiently find all missing numbers in a range-constrained array. The key insight is that after sorting, any index-value mismatch directly reveals a missing number. The approach maintains O(n) time complexity while finding all missing numbers in a single pass, making it superior to hash-based approaches that require O(n) space.

---

**Tags:** #sorting #cyclicsort #arrays #missing #easymedium 