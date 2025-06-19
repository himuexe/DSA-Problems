# Fibonacci Sequence Generation

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Generate and print the first n terms of the Fibonacci sequence. The Fibonacci sequence starts with 0, 1, and each subsequent number is the sum of the two preceding ones.

## Intuition/Approach
Use iterative approach with two variables to track the previous two numbers. Print each term as we generate it, updating the variables for the next iteration.

## Key Observations
- Sequence starts with 0, 1
- Each term is sum of previous two terms
- Need to handle cases where n < 2
- Space-efficient iterative approach preferred over recursive

## Algorithm Steps
1. Initialize first two terms (a=0, b=1)
2. Print the first two terms
3. Loop from term 3 to n:
   - Calculate next term as sum of previous two
   - Print the new term
   - Update variables for next iteration

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single loop through n terms, constant extra space

## Edge Cases Considered
- [x] n = 1 (only first term)
- [x] n = 2 (first two terms)
- [x] n > 2 (full sequence generation)
- [x] Large n values (within computation limits)
- [ ] n = 0 or negative (not explicitly handled)

## Solution Code

```java
// Language: Java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of terms: ");
    int n = sc.nextInt();
    
    int a = 0, b = 1;
    System.out.print("Fibonacci Series: " + a + ", " + b);
    
    for (int i = 2; i < n; i++) {
        int c = a + b;
        System.out.print(", " + c);
        a = b;
        b = c;
    }
    
    sc.close();
}
```

## Alternative Approaches
- **Recursive:** fibonacci(n) = fibonacci(n-1) + fibonacci(n-2) (exponential time)
- **Memoization:** Store computed values to avoid recalculation
- **Matrix Exponentiation:** For finding nth Fibonacci number efficiently

## Related Problems
- **AC:** [Factorial.java - sequence generation patterns]
- **Kunal:** [Armstrong.java - iterative mathematical computations]
- **LeetCode:** [Fibonacci Number - Problem 509]

## Personal Notes
Classic sequence generation problem. The iterative approach is much more efficient than naive recursion. Good practice for understanding variable updates in loops.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #fibonacci #sequences #iterative #mathematics 