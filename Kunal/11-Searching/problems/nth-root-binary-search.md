# Nth Root using Binary Search

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Find the nth root of a given number using binary search. If the exact nth root exists, return it; otherwise return -1. For example, the 3rd root of 27 is 3.

## Intuition/Approach
Use binary search on the answer space from 0 to the given number. For each candidate, calculate its nth power and compare with the target number.

## Key Observations
- Search space: [0, number]
- For each mid value, calculate mid^n
- If mid^n equals number, found the answer
- If mid^n < number, search in right half
- If mid^n > number, search in left half
- Handle overflow during power calculation

## Algorithm Steps
1. Handle edge cases: power = 1, number = 0 or 1
2. Set left = 0, right = number
3. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - Calculate midPower = mid^power
   - If midPower == number: return mid
   - If midPower < number: left = mid + 1
   - If midPower > number: right = mid - 1
4. Return -1 if no exact root found

## Complexity Analysis
- **Time Complexity:** O(log n × p) where p is the power
- **Space Complexity:** O(1)
- **Justification:** Binary search iterations × power calculation time

## Edge Cases Considered
- [x] Power = 1 (return number itself)
- [x] Number = 0 (root is 0)
- [x] Number = 1 (root is 1)
- [x] Perfect nth powers
- [x] Non-perfect nth powers (return -1)
- [x] Large numbers causing overflow

## Solution Code

```java
// Language: Java
public static long search(int number, int power){
    if(power == 1){
        return number;
    }
    if(number == 0 || number == 1){
        return number;
    }
    
    long left = 0;
    long right = number;
    
    while(left <= right){
        long mid = left + (right - left) / 2;
        long midPower = power(mid, power);
        
        if(midPower == number){
            return mid;
        }
        else if(midPower < number){
            left = mid + 1;
        }
        else{
            right = mid - 1;
        }
    }
    
    return -1;
}

private static long power(long base, int exp){
    long result = 1;
    for(int i = 0; i < exp; i++){
        if(result > Long.MAX_VALUE / base){
            return Long.MAX_VALUE;
        }
        result *= base;
    }
    return result;
}
```

## Alternative Approaches
- **Newton's Method:** Iterative approximation method for roots
- **Built-in Methods:** Math.pow() with precision handling
- **Exponential Search:** Different search strategy for the answer space

## Related Problems
- **Kunal:** [Split Array Largest Sum - binary search on answer space]
- **LeetCode:** [Sqrt(x) - Problem 69]
- **GeeksforGeeks:** [Find nth root of a number]

## Personal Notes
Another example of binary search on answer space. The overflow handling in power calculation is crucial for correctness. This pattern is useful for many mathematical optimization problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #answerSpace #mathematics #nthRoot 