# Bubble Sort

**Source:** AC | **Topic:** Sorting | **Difficulty:** Easy  

---

## Problem Statement
Implement Bubble Sort to sort an integer array in ascending order.

## Intuition/Approach
- Repeatedly traverse the array and swap adjacent out-of-order elements so larger elements bubble to the end.
- Use an early-termination flag to stop when a full pass makes no swaps.
- After each pass, the last i elements are fixed, shrinking the next pass range.

## Key Observations
- Early termination via a `swapped` flag optimizes nearly sorted inputs.
- Comparison range reduces by one per outer pass (`arr.length - 1 - i`).
- In-place algorithm; stable with adjacent swaps.

## Algorithm Steps
1. Loop `i` from 0 to `n - 2`.
2. Set `swapped = false`.
3. Loop `j` from 0 to `n - 2 - i`. If `arr[j] > arr[j+1]`, swap and set `swapped = true`.
4. If `swapped` is false after inner loop, break early.

## Complexity Analysis
- **Time Complexity:** Best O(n), Average O(n^2), Worst O(n^2)
- **Space Complexity:** O(1)
- **Justification:** Nested passes over a shrinking unsorted region with constant extra space.

## Edge Cases Considered
- [x] Empty input
- [x] Single element
- [x] Already sorted
- [x] Reverse sorted
- [x] Duplicates present

## Solution Code

```java
public static void sort(int[] arr){
    boolean swapped;
    for(int i = 0; i < arr.length - 1; i++){
        swapped = false;
        for(int j = 0; j < arr.length - 1 - i; j++){
            if(arr[j] > arr[j + 1]){
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
                swapped = true;
            }
        }
        if(!swapped){
            break;
        }
    }
}
```

## Alternative Approaches
- Track the last swap index to further reduce the comparison window.
- Cocktail shaker sort (bidirectional bubble) for some distributions.

## Personal Notes
- Example: input `[13,1,14,2,10,11]` → output `[1,2,10,11,13,14]`.

---
**Tags:** #sorting #arrays #basics #stable #inplace


