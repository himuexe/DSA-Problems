# Recursion Basics - Multiple Algorithms

**Source:** AC | **Topic:** Recursion | **Difficulty:** Easy–Medium  

---

## Problem Statement
Summarize fundamental recursive algorithms: printing ranges (inc/dec), factorial, sum of naturals, Fibonacci, power, and fast power.

## Intuition/Approach
- Each function has a clear base case and a recurrence that reduces input size.
- Decide whether to act before or after the recursive call depending on desired order.

### Print Increasing (1..n)
```java
public static void printInc(int n) {
    if (n == 1) { System.out.print(n); return; }
    printInc(n - 1);
    System.out.print(n);
}
```

### Print Decreasing (n..1)
```java
public static void printDec(int n) {
    if (n == 1) { System.out.print(n); return; }
    System.out.print(n);
    printDec(n - 1);
}
```

### Factorial
```java
public static int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```

### Sum of 1..n
```java
public static int sum(int n) {
    if (n == 1) return 1;
    return n + sum(n - 1);
}
```

### Fibonacci (naive)
```java
public static int fib(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fib(n - 1) + fib(n - 2);
}
```

### Power x^n (basic)
```java
public static int pow(int x, int n) {
    if (n == 1) return x;
    return x * pow(x, n - 1);
}
```

### Optimized Power (divide & conquer)
```java
public static int optpow(int x, int n) {
    if (n == 0) return 1;
    int half = optpow(x, n / 2);
    int res = half * half;
    if ((n & 1) == 1) res *= x;
    return res;
}
```

## Key Observations
- Base-case correctness prevents infinite recursion.
- Work-before vs work-after determines order of effects.
- Optimized power reduces time from O(n) to O(log n).

## Algorithm Steps
1. Define base case(s) that stop recursion.
2. Reduce the problem size with each recursive call.
3. Combine results or perform actions pre/post call as needed.

## Complexity Analysis
- Print Inc/Dec: Time O(n), Space O(n)
- Factorial: Time O(n), Space O(n)
- Sum: Time O(n), Space O(n)
- Fibonacci (naive): Time O(2^n), Space O(n)
- Power: Time O(n), Space O(n)
- Optimized Power: Time O(log n), Space O(log n)

## Edge Cases Considered
- [x] n = 0 (for factorial/optpow)
- [x] n = 1
- [x] Negative inputs (out of scope unless validated)
- [x] Large n (stack overflow risk)
- [x] Integer overflow for factorial/power

## Solution Code

```java
// See per-section code snippets above.
```

## Alternative Approaches
- Iterative conversions for factorial, sum, and power.
- Memoization for Fibonacci to achieve O(n) time.

## Personal Notes
- Great set to practice identifying base cases and progression.

---
**Tags:** #recursion #basics #divideandconquer