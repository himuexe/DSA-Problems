# Insertion Sort

**Source:** AC | **Topic:** Sorting | **Difficulty:** Easy  

---

## Problem Statement
Implement Insertion Sort to sort an integer array in ascending order.

## Intuition/Approach
- Build a sorted prefix and insert each new element into its correct place within the prefix.
- Shift larger elements to the right to create space for the current element.
- Analogy: sorting playing cards in your hand.

## Key Observations
- Current element `curr` is placed by scanning left with `prev`.
- Shifting avoids repeated swaps; stable by nature.
- Performs well on small or nearly sorted inputs.

## Algorithm Steps
1. Loop `i` from 1 to `n - 1`, set `curr = arr[i]`, `prev = i - 1`.
2. While `prev >= 0` and `arr[prev] > curr`, shift `arr[prev]` to `arr[prev+1]` and decrement `prev`.
3. Place `curr` at `arr[prev + 1]`.

## Complexity Analysis
- **Time Complexity:** Best O(n), Average O(n^2), Worst O(n^2)
- **Space Complexity:** O(1)
- **Justification:** At most triangular number of shifts; constant extra space.

## Edge Cases Considered
- [x] Empty input
- [x] Single element
- [x] Already sorted
- [x] Reverse sorted
- [x] Duplicates present

## Solution Code

```java
public static void sort(int[] arr){
    for(int i = 1; i < arr.length; i++){
        int curr = arr[i];
        int prev = i - 1;
        while(prev >= 0 && arr[prev] > curr){
            arr[prev + 1] = arr[prev];
            prev--;
        }
        arr[prev + 1] = curr;
    }
}
```

## Alternative Approaches
- Binary insertion sort to reduce comparisons to O(log n) per insertion (still O(n^2) moves).

## Personal Notes
- Example: input `[13,1,14,2,10,11]` → output `[1,2,10,11,13,14]`.

---
**Tags:** #sorting #arrays #stable #inplace #adaptive