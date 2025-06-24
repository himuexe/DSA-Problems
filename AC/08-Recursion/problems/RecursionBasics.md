# Recursion Basics - Multiple Algorithms

**Date:** June 24, 2025  
**Topic:** Recursion  
**Difficulty:** Easy to Medium  
**Time Complexity:** Varies by algorithm  
**Space Complexity:** O(n) for recursion stack  
**Source:** Apna College DSA Course

---

## Problem Statement

A comprehensive collection of fundamental recursive algorithms including:
1. **Print Increasing Numbers** (1 to n)
2. **Print Decreasing Numbers** (n to 1)  
3. **Factorial Calculation** (n!)
4. **Sum of Natural Numbers** (1+2+...+n)
5. **Fibonacci Sequence** (nth Fibonacci number)
6. **Power Calculation** (x^n basic)
7. **Optimized Power** (x^n with divide-and-conquer)

---

## Approach & Intuition

### 1. Print Increasing Numbers (1 to n)
**Recursive Logic:** Print (n-1) first, then print n
```java
public static void printInc(int n) {
    if(n == 1) {
        System.out.print(n);
        return;
    }
    printInc(n-1);  // Recursive call first
    System.out.print(n);  // Print after recursion
}
```

### 2. Print Decreasing Numbers (n to 1)
**Recursive Logic:** Print n first, then print (n-1)
```java
public static void printDec(int n) {
    if(n == 1) {
        System.out.print(n);
        return;
    }
    System.out.print(n);  // Print before recursion
    printDec(n-1);  // Recursive call after
}
```

### 3. Factorial Calculation
**Mathematical Relation:** n! = n × (n-1)!
```java
public static int factorial(int n) {
    if(n == 0) {
        return 1;  // Base case: 0! = 1
    }
    return n * factorial(n-1);
}
```

### 4. Sum of Natural Numbers
**Mathematical Relation:** Sum(n) = n + Sum(n-1)
```java
public static int sum(int n) {
    if(n == 1) {
        return 1;  // Base case
    }
    return n + sum(n-1);
}
```

### 5. Fibonacci Sequence
**Mathematical Relation:** F(n) = F(n-1) + F(n-2)
```java
public static int fib(int n) {
    if(n == 0) return 0;  // F(0) = 0
    if(n == 1) return 1;  // F(1) = 1
    return fib(n-1) + fib(n-2);
}
```

### 6. Basic Power Calculation
**Mathematical Relation:** x^n = x × x^(n-1)
```java
public static int pow(int x, int n) {
    if(n == 1) {
        return x;  // Base case
    }
    return x * pow(x, n-1);
}
```

### 7. Optimized Power (Divide & Conquer)
**Key Optimization:** x^n = (x^(n/2))² for even n, x^n = x × (x^(n/2))² for odd n
```java
public static int optpow(int x, int n) {
    if(n == 0) {
        return 1;
    }
    int halfPower = optpow(x, n/2);
    int halfPowerSq = halfPower * halfPower;
    if(n % 2 != 0) {
        halfPowerSq *= x;  // Multiply by x for odd powers
    }
    return halfPowerSq;
}
```

---

## Complexity Analysis

| Algorithm | Time Complexity | Space Complexity | Notes |
|-----------|----------------|------------------|-------|
| **Print Inc/Dec** | O(n) | O(n) | Linear recursion |
| **Factorial** | O(n) | O(n) | Linear recursion |
| **Sum** | O(n) | O(n) | Linear recursion |
| **Fibonacci** | O(2^n) | O(n) | Exponential - inefficient |
| **Basic Power** | O(n) | O(n) | Linear recursion |
| **Optimized Power** | O(log n) | O(log n) | Divide & conquer |

---

## Key Recursion Concepts

### 1. Base Case Design
- **Factorial:** n = 0 returns 1
- **Fibonacci:** n = 0 returns 0, n = 1 returns 1
- **Print functions:** n = 1 is the stopping condition

### 2. Recursive Relation
- Each problem has a clear mathematical recursive relation
- The current problem is solved using smaller subproblems

### 3. Call Stack Behavior
- **Print Increasing:** Recursion first, then action (builds stack, then prints)
- **Print Decreasing:** Action first, then recursion (prints while building stack)

### 4. Optimization Techniques
- **Divide & Conquer:** Optimized power reduces O(n) to O(log n)
- **Memoization:** Not implemented but would improve Fibonacci to O(n)

---

## Edge Cases

- [x] **n = 0:** Handled in factorial and optimized power
- [x] **n = 1:** Base case for most algorithms
- [x] **Negative numbers:** Not handled - would need additional validation
- [x] **Large numbers:** May cause stack overflow for deep recursion
- [x] **Integer overflow:** Not handled for large factorials/powers

---

## Alternative Approaches

### Iterative Solutions
All recursive algorithms can be converted to iterative:
- **Factorial:** Use loop with accumulator
- **Fibonacci:** Use two variables to track previous values
- **Power:** Use loop with multiplication
- **Sum:** Use mathematical formula n(n+1)/2

### Optimized Recursive Solutions
- **Fibonacci:** Use memoization or dynamic programming
- **Power:** Already optimized with divide-and-conquer
- **Tail Recursion:** Convert to tail-recursive forms where possible

---

## Key Learnings

1. **Recursion Structure:** Base case + recursive case pattern
2. **Stack Space:** All recursive solutions use O(n) stack space
3. **Time Complexity:** Varies from O(log n) to O(2^n) depending on algorithm
4. **Optimization:** Divide-and-conquer can significantly improve performance
5. **Call Order:** When to perform action (before/after recursive call) matters

---

## Related Problems

- **Tower of Hanoi:** Classic recursion problem
- **Tree Traversals:** Inorder, preorder, postorder
- **Binary Search:** Recursive divide-and-conquer
- **Merge Sort:** Recursive sorting algorithm
- **Subset Generation:** Recursive backtracking

---

## Notes

- **Learning Value:** Excellent introduction to recursion fundamentals
- **Interview Relevance:** Basic recursion concepts frequently tested
- **Optimization Awareness:** Fibonacci shows exponential time trap
- **Pattern Recognition:** Multiple recursive patterns in single implementation 