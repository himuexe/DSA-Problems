# Prime Number Detection

**Source:** AC | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Create a program to check whether a given number is prime or not. A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.

## Intuition/Approach
Use optimized trial division method - only check divisors up to √n and skip even numbers after checking for 2. This reduces the time complexity significantly for large numbers.

## Key Observations
- Numbers ≤ 1 are not prime by definition
- 2 is the only even prime number  
- For odd numbers, only need to check odd divisors up to √n
- Early termination when a divisor is found

## Algorithm Steps
1. Handle edge cases: numbers ≤ 1 (not prime), 2 (prime)
2. Check if number is even (not prime if > 2)
3. Loop through odd numbers from 3 to √n
4. If any divisor found, number is not prime
5. If no divisors found, number is prime

## Complexity Analysis
- **Time Complexity:** O(√n)
- **Space Complexity:** O(1)
- **Justification:** Only checking divisors up to square root, constant space usage

## Edge Cases Considered
- [x] Numbers ≤ 1 (handled as not prime)
- [x] Number 2 (handled as prime)
- [x] Even numbers > 2 (handled as not prime)
- [x] Large numbers (optimized with √n limit)
- [x] Perfect squares (√n boundary handled correctly)

## Solution Code

```java
// Language: Java
public static void isPrime(int number) {
    if (number <= 1) {
        System.out.println("The number " + number + " is not Prime");
        return;
    }
    if (number == 2) {
        System.out.println("The number " + number + " is Prime");
        return;
    }
    if (number % 2 == 0) {
        System.out.println("The number " + number + " is not Prime");
        return;
    }
    
    boolean isPrime = true;
    for (int i = 3; i <= Math.sqrt(number); i += 2) {
        if (number % i == 0) {
            isPrime = false;
            break;
        }
    }
    
    if (isPrime) {
        System.out.println("The number " + number + " is Prime");
    } else {
        System.out.println("The number " + number + " is not Prime");
    }
}
```

## Alternative Approaches
- **Brute Force:** Check all numbers from 2 to n-1 (O(n) time)
- **Sieve of Eratosthenes:** For finding multiple primes up to n (better for ranges)
- **Probabilistic Tests:** Miller-Rabin for very large numbers

## Personal Notes
This is a fundamental algorithm that appears in many advanced problems. The optimization of checking only up to √n is crucial for performance. The pattern of handling edge cases first is good practice.

---
**Tags:** #basics #mathematics #primeNumbers #optimization #numberTheory 