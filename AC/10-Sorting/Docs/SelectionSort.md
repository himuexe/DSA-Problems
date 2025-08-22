# Selection Sort

**Source:** AC | **Topic:** Sorting | **Difficulty:** Easy  

---

## Problem Statement
Implement Selection Sort to sort an integer array in ascending order.

## Intuition/Approach
- Repeatedly select the minimum element from the unsorted region and place it at the beginning.
- Expand the sorted prefix one position per iteration.
- One swap per outer iteration minimizes writes.

## Key Observations
- Always performs the same comparisons regardless of input order.
- Unstable in its basic form (min element swap can reorder equals).
- In-place with minimal swaps (at most n − 1).

## Algorithm Steps
1. Loop `i` from 0 to `n - 2`; set `minPos = i`.
2. Loop `j` from `i + 1` to `n - 1`; if `arr[j] < arr[minPos]`, update `minPos`.
3. Swap `arr[minPos]` with `arr[i]`.

## Complexity Analysis
- **Time Complexity:** Best O(n^2), Average O(n^2), Worst O(n^2)
- **Space Complexity:** O(1)
- **Justification:** Triangular number of comparisons; constant extra space.

## Edge Cases Considered
- [x] Empty input
- [x] Single element
- [x] All elements equal
- [x] Already sorted
- [x] Reverse sorted

## Solution Code

```java
public static void sort(int[] arr){
    for(int i = 0; i < arr.length - 1; i++){
        int minPos = i;
        for(int j = i + 1; j < arr.length; j++){
            if(arr[j] < arr[minPos]){
                minPos = j;
            }
        }
        int temp = arr[minPos];
        arr[minPos] = arr[i];
        arr[i] = temp;
    }
}
```

## Alternative Approaches
- Use a stable selection variant by shifting instead of swapping (more writes).

## Personal Notes
- Example: input `[13,1,14,2,10,11]` → output `[1,2,10,11,13,14]`.

---
**Tags:** #sorting #arrays #inplace #unstable #basics