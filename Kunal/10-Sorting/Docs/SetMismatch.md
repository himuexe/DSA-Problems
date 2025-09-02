# Set Mismatch

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Easy-Medium  

---

## Problem Statement
Find the number that occurs twice and the number that is missing from a set that originally contained numbers from 1 to n.

## Intuition/Approach
Uses **Cyclic Sort** to arrange numbers, then identifies the first position where the expected number is not present. The value at that position is the duplicate, and the expected value is the missing number.

**Key Insight:** After cyclic sorting, the first position where `nums[i] != i+1` indicates a problem. The value at that position is the duplicate, and the expected value `i+1` is the missing number.

## Key Observations
- **Single Scan:** One pass through sorted array finds both numbers
- **Position Logic:** Duplicate occupies wrong position, displacing missing number
- **Direct Return:** `{nums[i], i+1}` gives duplicate and missing respectively
- **Guaranteed Solution:** Problem guarantees exactly one duplicate and one missing

## Algorithm Steps
1. **Apply Cyclic Sort:** Place each number at index `number-1`
2. **Scan for Mismatch:** Find first position where `nums[i] != i+1`
3. **Identify Duplicate:** Value at wrong position is the duplicate
4. **Identify Missing:** Expected value at that position is missing
5. **Return Result:** Return `{duplicate, missing}`

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Cyclic sort takes O(n) time, and linear scan takes O(n) time, totaling O(n). Only constant extra space is used.

## Edge Cases Considered
- [x] Duplicate is the smallest number (1)
- [x] Duplicate is the largest number (n)
- [x] Missing number is at the beginning
- [x] Missing number is at the end
- [x] Consecutive duplicate and missing numbers

## Solution Code

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

## Alternative Approaches
1. **Hash Set:** Two passes - find duplicate, then missing
2. **Mathematical:** Sum and sum of squares comparison
3. **XOR Manipulation:** Complex bit manipulation approach
4. **Sorting + Scan:** O(n log n) comparison sort

## Personal Notes
This problem elegantly demonstrates the power of cyclic sort for range-constrained problems. The key insight is that after sorting, the first mismatch reveals both the duplicate and missing numbers. The cyclic sort approach is optimal with O(n) time and O(1) space, making it superior to hash-based or mathematical approaches.

---

**Tags:** #sorting #cyclicsort #arrays #duplicates #missing #easymedium 