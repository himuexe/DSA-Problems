# Array Questions - Recursive Array Manipulation

**Source:** AC | **Topic:** Recursion | **Difficulty:** Intermediate  

---

## Problem Statement
Demonstrate recursive array manipulation by filling an array with sequential values, printing the array state during recursion, and then modifying values during backtracking to show how recursive calls affect array state.

## Intuition/Approach
- Forward recursion fills the array with increasing values.
- On reaching the base case, the array is printed once when fully filled.
- During backtracking, each element is decreased by 2, illustrating the difference between forward and return phases.
- Arrays are passed by reference, so changes persist across calls.

## Key Observations
- Arrays (mutable) reflect changes across recursive frames.
- Distinct forward (do work before call) vs backward phase (do work after call).
- Backtracking enables post-recursion adjustments to previously set values.
- Index-based sequential processing keeps logic simple.
- Useful to visualize recursion effects on shared state.

## Algorithm Steps
1. If `idx == arr.length`, print the array and return.
2. Set `arr[idx] = val`.
3. Recurse: `changeArr(arr, idx + 1, val + 1)`.
4. On return, set `arr[idx] = arr[idx] - 2`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each index is visited once; recursion depth is n.

## Edge Cases Considered
- [x] Empty array (length 0)
- [x] Single element
- [x] Large arrays (stack depth considerations)
- [x] Integer overflow in value calculations
- [x] Negative starting values

## Solution Code

```java
public static void changeArr(int[] arr, int idx, int val) {
    // Base case: reached end of array
    if (idx == arr.length) {
        System.err.println(java.util.Arrays.toString(arr));
        return;
    }

    // Forward operation: fill array with sequential values
    arr[idx] = val;

    // Recursive call: move to next index with incremented value
    changeArr(arr, idx + 1, val + 1);

    // Backtrack operation: modify array during return phase
    arr[idx] = arr[idx] - 2;
}
```

## Alternative Approaches
- Print on both forward and backward phases to visualize state changes step-by-step.
- Use an immutable structure copy at each step (educational, but O(n^2) time and space overall).

## Personal Notes
- Final state for size 5 and start value 1 becomes `[-1, 0, 1, 2, 3]` after backtracking.

---
**Tags:** #recursion #arrays #backtracking #education 