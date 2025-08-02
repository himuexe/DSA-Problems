# Binomial Coefficient Calculation

**Source:** AC | **Topic:** 00-Basics | **Difficulty:** Medium  

---

## Problem Statement
Calculate the binomial coefficient C(n,r) = n! / (r! × (n-r)!) where n and r are non-negative integers and r ≤ n.

## Intuition/Approach
Use the factorial function to calculate n!, r!, and (n-r)!, then apply the binomial coefficient formula. Reuse the factorial calculation method for efficiency.

## Key Observations
- Binomial coefficient represents "n choose r" combinations
- Formula: C(n,r) = n! / (r! × (n-r)!)
- Factorial function can be reused for multiple calculations
- Important in combinatorics and probability theory

## Algorithm Steps
1. Get input values n and r
2. Calculate factorial of n using helper function
3. Calculate factorial of r using helper function
4. Calculate factorial of (n-r) using helper function
5. Apply formula: n! / (r! × (n-r)!)
6. Return the result

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Three factorial calculations, largest is O(n), constant space

## Edge Cases Considered
- [x] r = 0 (result should be 1)
- [x] r = n (result should be 1)
- [x] r = 1 (result should be n)
- [ ] r > n (mathematically undefined, not handled)
- [ ] Negative values (not handled)

## Solution Code

```java
// Language: Java
public static int calculateFactorial(int number){
    int fact = 1;
    for(int i = 1; i <= number; i++){
        fact *= i;
    }
    return fact;
}

public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter n");
    int n = sc.nextInt();
    System.out.println("Enter r");
    int r = sc.nextInt();
    int binomial = calculateFactorial(n) / (calculateFactorial(r) * calculateFactorial(n-r));
    System.out.println("The binomial coefficient is: " + binomial);
    sc.close();
}
```

## Alternative Approaches
- **Optimized Formula:** Use C(n,r) = C(n,n-r) for r > n/2
- **Pascal's Triangle:** Build using dynamic programming
- **Iterative:** Calculate C(n,r) = (n×(n-1)×...×(n-r+1)) / (r×(r-1)×...×1)

## Personal Notes
Important mathematical concept in combinatorics. The factorial reuse demonstrates good programming practice. Watch out for integer overflow with large values.

---
**Tags:** #basics #mathematics #binomial #combinatorics #factorial 