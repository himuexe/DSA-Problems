# Array Questions - Recursive Array Manipulation

## Problem Statement
Demonstrate recursive array manipulation by filling an array with sequential values, printing the array state during recursion, and then modifying values during backtracking to show how recursive calls affect array state.

## Intuition/Approach
**Recursive Array State Exploration:**
1. **Forward Pass:** Fill array with sequential values (1, 2, 3, 4, 5)
2. **State Printing:** Print array when fully filled during recursion
3. **Backtrack Modification:** Subtract 2 from each element during return
4. **Final State:** Observe modified array after all recursive calls complete

**Key Insight:** Arrays are passed by reference, so modifications persist across recursive calls and demonstrate the difference between forward recursion and backtracking phases.

## Key Observations
- **Pass by Reference:** Array modifications are visible across all recursive calls
- **Recursion Phases:** Forward pass (filling) vs. backward pass (modifying)
- **State Persistence:** Changes made during recursion affect final array state
- **Sequential Processing:** Index-based sequential array manipulation
- **Educational Value:** Clear demonstration of how recursion affects mutable data structures

## Algorithm Steps
1. **Base Case:** When idx == arr.length, print current array state and return
2. **Forward Operation:**
   - Set arr[idx] = val (sequential value assignment)
   - Recursive call: changeArr(arr, idx+1, val+1)
3. **Backtrack Operation:**
   - After recursive call returns: arr[idx] = arr[idx] - 2
   - This modifies the array during the "unwinding" phase

## Array State Evolution
**Initial:** [0, 0, 0, 0, 0]

**Forward Recursion (filling):**
- Call(0,1): arr[0]=1 → [1, 0, 0, 0, 0]
- Call(1,2): arr[1]=2 → [1, 2, 0, 0, 0]  
- Call(2,3): arr[2]=3 → [1, 2, 3, 0, 0]
- Call(3,4): arr[3]=4 → [1, 2, 3, 4, 0]
- Call(4,5): arr[4]=5 → [1, 2, 3, 4, 5] **← Printed**

**Backtrack Unwinding (modifying):**
- Return from Call(4,5): arr[4] = 5-2 = 3 → [1, 2, 3, 4, 3]
- Return from Call(3,4): arr[3] = 4-2 = 2 → [1, 2, 3, 2, 3]
- Return from Call(2,3): arr[2] = 3-2 = 1 → [1, 2, 1, 2, 3]
- Return from Call(1,2): arr[1] = 2-2 = 0 → [1, 0, 1, 2, 3]
- Return from Call(0,1): arr[0] = 1-2 = -1 → [-1, 0, 1, 2, 3]

**Final State:** [-1, 0, 1, 2, 3]

## Time & Space Complexity
- **Time Complexity:** O(n)
  - Single pass through array of length n
  - Each index visited exactly once
  - Constant time operations per recursive call
- **Space Complexity:** O(n)
  - Recursion call stack: O(n) depth
  - Array storage: O(n) 
  - No additional data structures

## Edge Cases Considered
- [x] Empty array (length 0)
- [x] Single element array
- [x] Large arrays (stack overflow potential)
- [x] Integer overflow in value calculations
- [x] Negative starting values

## Code Implementation
```java
public static void changeArr(int[] arr, int idx, int val) {
    // Base case: reached end of array
    if (idx == arr.length) {
        System.err.println(Arrays.toString(arr));  // Print filled array
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

## Example Walkthrough
**Input:** Array of size 5, starting at index 0 with value 1

**Execution Trace:**
```
changeArr([0,0,0,0,0], 0, 1)
├─ arr[0] = 1 → [1,0,0,0,0]
├─ changeArr([1,0,0,0,0], 1, 2)
   ├─ arr[1] = 2 → [1,2,0,0,0]
   ├─ changeArr([1,2,0,0,0], 2, 3)
      ├─ arr[2] = 3 → [1,2,3,0,0]
      ├─ changeArr([1,2,3,0,0], 3, 4)
         ├─ arr[3] = 4 → [1,2,3,4,0]  
         ├─ changeArr([1,2,3,4,0], 4, 5)
            ├─ arr[4] = 5 → [1,2,3,4,5]
            ├─ Print: [1,2,3,4,5]
            └─ Return
         └─ arr[3] = 4-2 = 2 → [1,2,3,2,5]
      └─ arr[2] = 3-2 = 1 → [1,2,1,2,5]
   └─ arr[1] = 2-2 = 0 → [1,0,1,2,5]
└─ arr[0] = 1-2 = -1 → [-1,0,1,2,5]
```

Wait, let me recalculate this more carefully...

Actually, the backtracking happens on the value that was modified AFTER the recursive call returns. Let me trace this again:

- arr[4] becomes 5, then gets modified to 3
- arr[3] becomes 4, then gets modified to 2  
- arr[2] becomes 3, then gets modified to 1
- arr[1] becomes 2, then gets modified to 0
- arr[0] becomes 1, then gets modified to -1

So final: [-1, 0, 1, 2, 3] ✓

## Key Learning Points
- **Reference Semantics:** Understanding how arrays behave in recursive calls
- **Recursion Phases:** Distinguishing between forward and backward phases
- **State Tracking:** Observing how modifications persist across call stack
- **Backtracking Concept:** Using return phase for additional operations

## Applications
- **Algorithm Visualization:** Understanding recursion call stack behavior
- **State Management:** Learning how mutable structures behave in recursion
- **Debugging Practice:** Tracing recursive modifications step by step
- **Educational Tool:** Teaching recursion concepts with visible effects

---
**Date:** June 28, 2025  
**Topic:** Recursion & Array Manipulation  
**Difficulty:** Intermediate  
**Category:** Educational Recursion 