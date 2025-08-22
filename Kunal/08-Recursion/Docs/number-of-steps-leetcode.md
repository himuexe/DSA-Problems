# Number of Steps to Zero - LeetCode Recursive Solution

## Problem Statement
**LeetCode Problem:** Given a non-negative integer `num`, return the number of steps to reduce it to zero. In one step, if the current number is even, divide it by 2, otherwise subtract 1.

## Intuition/Approach
**Recursive Step Counting:**
1. **Base Case:** When number becomes 0, return current step count
2. **Even Numbers:** Divide by 2 and increment step count
3. **Odd Numbers:** Subtract 1 and increment step count
4. **Helper Function:** Use auxiliary function to track step count

**Key Insight:** Each operation reduces the problem size, leading to natural recursion.

## Key Observations
- **Binary Reduction Pattern:** Even numbers reduce faster through division
- **Step Accumulation:** Each recursive call adds one step
- **Tail Recursion:** Can be optimized to iterative approach
- **Mathematical Property:** Even division vs odd subtraction creates different paths
- **Guaranteed Termination:** All positive integers eventually reach 0

## Algorithm Steps
1. **Main Function:** Call helper with initial number and step count 0
2. **Helper Base Case:** If num == 0, return accumulated steps
3. **Even Number Case:** If num % 2 == 0:
   - Recursively call with num/2 and steps+1
4. **Odd Number Case:** Otherwise:
   - Recursively call with num-1 and steps+1

## Time & Space Complexity
- **Time Complexity:** O(log n)
  - Each division by 2 halves the number
  - Subtraction by 1 followed by division creates logarithmic reduction
  - Maximum operations: ~2 * log₂(n)
- **Space Complexity:** O(log n)
  - Recursion stack depth proportional to number of operations
  - Each call uses constant space

## Edge Cases Considered
- [x] num = 0 (already at target)
- [x] num = 1 (single subtraction)
- [x] Powers of 2 (only divisions needed)
- [x] Odd numbers (alternating subtract/divide pattern)
- [x] Large numbers (logarithmic reduction)

## Code Implementation
```java
public int numberOfSteps(int num) {
    return helper(num, 0);  // Start with 0 steps
}

private int helper(int num, int steps) {
    if (num == 0) {
        return steps;  // Base case: reached zero
    }
    
    if (num % 2 == 0) {
        // Even number: divide by 2
        return helper(num / 2, steps + 1);
    } else {
        // Odd number: subtract 1  
        return helper(num - 1, steps + 1);
    }
}
```

## Example Walkthrough
**Input:** num = 14
**Step-by-step Process:**

1. 14 (even) → 14/2 = 7, steps = 1
2. 7 (odd) → 7-1 = 6, steps = 2  
3. 6 (even) → 6/2 = 3, steps = 3
4. 3 (odd) → 3-1 = 2, steps = 4
5. 2 (even) → 2/2 = 1, steps = 5
6. 1 (odd) → 1-1 = 0, steps = 6
7. 0 → return 6

**Final Result:** 6 steps

## Key Learning Points
- **Helper Function Pattern:** Using auxiliary function for parameter tracking
- **Logarithmic Recursion:** Understanding log-time recursive algorithms  
- **Binary Operations:** Even/odd decisions create efficient reduction
- **LeetCode Patterns:** Common recursive problem-solving approach

## Applications
- **Algorithm Contests:** Understanding number reduction problems
- **Mathematical Computing:** Recursive number theory calculations
- **Interview Preparation:** Classic recursion problem type
- **Optimization Problems:** Step-counting in recursive solutions

---
**Date:** June 27, 2025  
**Topic:** Mathematical Algorithms & Recursion  
**Difficulty:** Beginner  
**Category:** LeetCode Problem 