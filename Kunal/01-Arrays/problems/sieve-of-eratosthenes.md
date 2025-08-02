# Sieve of Eratosthenes

**Date:** 2025-01-19  
**Category:** Kunal Basics  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Number Theory/Prime Numbers

## Problem Statement

Find all prime numbers up to a given number `n` efficiently using the Sieve of Eratosthenes algorithm.

## Intuition/Approach

The **Sieve of Eratosthenes** is an ancient algorithm for finding all prime numbers up to a given limit. The key insight is that we can eliminate all multiples of each prime number, leaving only the primes.

**Core Concept:**
1. Start with assumption that all numbers are prime
2. For each prime number `p`, mark all its multiples as composite
3. The numbers that remain unmarked are prime

**Optimization:** We only need to check up to √n because any composite number greater than √n must have already been marked by a smaller prime factor.

## Algorithm Steps

1. **Initialize:** Create boolean array `primes[n+1]`, where `false` means prime
2. **Outer Loop:** For each number `i` from 2 to √n:
   - If `primes[i]` is `false` (i.e., `i` is prime)
   - **Inner Loop:** Mark all multiples of `i` starting from `2*i`
3. **Result Collection:** All indices with `false` values are prime numbers

## Key Observations

- Uses `false` to represent prime numbers (opposite of intuitive)
- Only checks up to √n for efficiency
- Marks multiples starting from `2*i` (could optimize to `i*i`)
- Time complexity is much better than checking each number individually
- Space-time tradeoff: uses O(n) space for O(n log log n) time

## Time & Space Complexity

- **Time Complexity:** O(n log log n) - very efficient for prime generation
- **Space Complexity:** O(n) - boolean array of size n+1

## Edge Cases Considered

- [ ] n = 1 (no primes less than 2)
- [ ] n = 2 (only prime is 2)
- [ ] Small values of n (2, 3, 4, 5)
- [ ] Large values of n (memory considerations)
- [ ] n = 0 or negative (invalid input)

## Solution Code

```java
public class Sieve {
    public static void main(String[] args) {
        int n = 40;
        boolean[] primes = new boolean[n+1];
        sieve(n, primes);
    }
    
    public static void sieve(int n, boolean[] primes) {
        // Mark multiples of each prime as composite
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) { // i is prime
                // Mark all multiples of i as composite
                for (int j = 2 * i; j <= n; j += i) {
                    primes[j] = true;
                }
            }
        }
        
        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
```

## Alternative Approaches

1. **Optimized Sieve:** Start marking from `i*i` instead of `2*i`
2. **Segmented Sieve:** For very large numbers, divide into segments
3. **Sieve of Sundaram:** Alternative sieve algorithm
4. **Wheel Factorization:** Skip multiples of small primes like 2, 3, 5

## Optimized Version

```java
// Start from i*i instead of 2*i for better efficiency
for (int j = i * i; j <= n; j += i) {
    primes[j] = true;
}
```

## Related Problems

- Count prime numbers (LeetCode #204)
- Prime factorization
- Finding largest prime factor
- Goldbach's conjecture verification
- Twin primes generation

**LeetCode Connection:** LeetCode #204 - Count Primes 