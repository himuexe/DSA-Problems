# Palindrome Number Check

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  

---

## Problem Statement
Check if a given integer is a palindrome. A palindrome number reads the same backward as forward (e.g., 121, 1331).

## Intuition/Approach
Reverse the digits of the number and compare with the original. If they are equal, the number is a palindrome.

## Key Observations
- Negative numbers are not palindromes by definition
- Single digit numbers are always palindromes
- Use digit reversal technique to create reversed number
- Compare reversed number with original

## Algorithm Steps
1. Handle negative numbers (return false)
2. Store original number for comparison
3. Reverse the digits:
   - Extract digit using modulo 10
   - Build reversed number: rev = rev * 10 + digit
   - Remove processed digit using integer division
4. Compare reversed number with original

## Complexity Analysis
- **Time Complexity:** O(d) where d is number of digits
- **Space Complexity:** O(1)
- **Justification:** Single pass through digits, constant extra space

## Edge Cases Considered
- [x] Negative numbers (handled as false)
- [x] Single digit numbers (palindromes)
- [x] Numbers ending with zero (not palindromes unless single digit)
- [x] Even and odd length numbers
- [x] Large palindromic numbers

## Solution Code

```java
// Language: Java
public static boolean isPalindrome(int x){
    int n = x;
    if(n < 0){
       return false;
    }
    int rev = 0;
    while(n > 0){
        rev = rev * 10 + (n % 10);
        n /= 10;
    }
    return rev == x;
}
```

## Alternative Approaches
- **String Approach:** Convert to string and compare with reversed string
- **Two Pointer:** Compare digits from both ends (more complex for integers)
- **Half Reversal:** Only reverse half digits for optimization

## Personal Notes
Combines digit manipulation with comparison logic. The approach of reversing the entire number is straightforward and easy to understand. Good practice for digit extraction patterns.

---
**Tags:** #basics #palindrome #digitManipulation #numberVerification #mathematics 