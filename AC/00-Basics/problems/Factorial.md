# Factorial Calculation - AC Implementation

## Problem Statement
Calculate the factorial of a given positive integer. Factorial of n (denoted as n!) is the product of all positive integers from 1 to n.

## Algorithm Overview
**Factorial Calculation** uses iterative multiplication to compute the product of all integers from 1 to the given number.

## Key Approach & Intuition
1. **Mathematical Definition**: n! = n × (n-1) × (n-2) × ... × 2 × 1
2. **Base Case**: 0! = 1 and 1! = 1 by definition
3. **Iterative Multiplication**: Start with 1 and multiply by each number from 1 to n
4. **Accumulation**: Use a variable to accumulate the running product

## Code Implementation
```java
public static int calculateFactorial(int number){
    int fact = 1;
    for(int i = 1; i <= number; i++){
        fact *= i;
    }
    return fact;
}
```

## Key Observations
- **Initialization**: `fact = 1` handles base case and serves as multiplicative identity
- **Loop Range**: Iterates from 1 to n (inclusive) to cover all factors
- **Accumulation**: `fact *= i` multiplies current product by next factor
- **Return Value**: Final accumulated product is the factorial

## Complexity Analysis
- **Time Complexity**: O(n) - single loop from 1 to n
- **Space Complexity**: O(1) - only uses constant extra space

## Edge Cases Considered
- n = 0: Returns 1 (0! = 1 by mathematical definition)
- n = 1: Returns 1 (1! = 1)
- Large values of n: May cause integer overflow

## Test Cases
```java
Input: 5
Process: 1 × 1 × 2 × 3 × 4 × 5 = 120
Output: 120

Input: 0
Output: 1 (by definition)

Input: 3
Process: 1 × 1 × 2 × 3 = 6
Output: 6
```

## Mathematical Properties
- **Growth Rate**: Factorial grows extremely rapidly (n! > 2^n for n ≥ 4)
- **Base Cases**: 0! = 1, 1! = 1
- **Recursive Definition**: n! = n × (n-1)!
- **Applications**: Permutations, combinations, probability calculations

## Alternative Approaches
1. **Recursive Implementation**: `factorial(n) = n * factorial(n-1)`
2. **BigInteger**: For handling large factorials without overflow
3. **Memoization**: Store computed values to avoid recomputation
4. **Stirling's Approximation**: For very large n (approximation)

## Advantages of Iterative Approach
- **Space Efficient**: O(1) space complexity
- **No Stack Overflow**: Avoids recursion depth issues
- **Simple Logic**: Easy to understand and implement
- **Performance**: Generally faster than recursive approach

## Limitations
- **Integer Overflow**: Limited by int range (factorial of 13 already overflows int)
- **Negative Numbers**: Implementation doesn't handle negative inputs
- **Large Numbers**: May need BigInteger for practical applications

## Related Concepts
- Mathematical sequences and series
- Combinatorics (permutations and combinations)
- Recursive vs iterative algorithms
- Big integer arithmetic

## Applications
- **Combinatorics**: Calculating permutations (nPr) and combinations (nCr)
- **Probability**: Computing probabilities in discrete distributions
- **Mathematical Analysis**: Series expansions (e.g., e^x = Σ(x^n/n!))
- **Algorithm Analysis**: Counting arrangements and orderings

## Difficulty Level
**Easy** - Fundamental mathematical algorithm

## Source
AC (Apna College) - 00-Basics Module 