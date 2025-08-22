# Missing Numbers

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Easy-Medium  

---

## Problem Statement
Find the missing number in an array containing n-1 distinct numbers taken from the range 0 to n.

## Intuition/Approach
Uses **Cyclic Sort** approach to place each number at its correct index, then scan to find the missing position.

**Key Insight:** Number `n` should be at index `n` (0-based). After sorting, the first index that doesn't contain its expected value is the missing number. If all positions are filled, the missing number is `n`.

## Key Observations
- **Range Check:** `arr[i] < arr.length` handles out-of-range values
- **Direct Index:** Element value equals its target index (0-based)
- **First Gap:** First mismatch between index and value is the answer
- **Boundary Case:** Missing number could be the largest in range

## Algorithm Steps
1. **Apply Cyclic Sort:** Place each number at its correct index position
2. **Range Validation:** Ensure `arr[i] < arr.length` before swapping
3. **Scan for Missing:** Find first index where `arr[i] != i`
4. **Return Result:** Return the first missing index, or `arr.length` if none found

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Cyclic sort takes O(n) time, and linear scan takes O(n) time, totaling O(n). Only constant extra space is used.

## Edge Cases Considered
- [x] Missing number is the smallest (0)
- [x] Missing number is the largest (n)
- [x] Missing number is in the middle
- [x] Array with single element

## Solution Code

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

## Alternative Approaches
1. **Sum Formula:** Total sum - array sum = missing number
2. **XOR Approach:** XOR all numbers and indices
3. **Binary Search:** For sorted arrays
4. **Hash Set:** Mark present numbers, find absent

## Personal Notes
This problem demonstrates the versatility of cyclic sort for finding missing elements in range-constrained arrays. The key insight is that after sorting, any index-value mismatch directly reveals the missing number. The range check `arr[i] < arr.length` is crucial for handling edge cases where the missing number is the largest value in the range.

---

**Tags:** #sorting #cyclicsort #arrays #missing #easymedium 