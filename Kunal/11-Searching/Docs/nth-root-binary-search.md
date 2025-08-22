# Nth Root using Binary Search

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Medium  

---

## Problem Statement
Find the nth root of a given number using binary search. If the exact nth root exists, return it; otherwise return -1. For example, the 3rd root of 27 is 3.

## Intuition/Approach
Use binary search on the answer space from 0 to the given number. For each candidate, calculate its nth power and compare with the target number.

**Key Insight:** Binary search can be applied to answer space problems, not just sorted arrays. For each mid value, calculate mid^n and use the result to determine which half to search.

## Key Observations
- Search space: [0, number]
- For each mid value, calculate mid^n
- If mid^n equals number, found the answer
- If mid^n < number, search in right half
- If mid^n > number, search in left half
- Handle overflow during power calculation

## Algorithm Steps
1. **Handle edge cases:** power = 1, number = 0 or 1
2. **Set search boundaries:** left = 0, right = number
3. **Binary search loop:**
   - Calculate mid = left + (right - left) / 2
   - Calculate midPower = mid^power
   - If midPower == number: return mid
   - If midPower < number: left = mid + 1
   - If midPower > number: right = mid - 1
4. **Return result:** -1 if no exact root found

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
1. **Newton's Method:** Iterative approximation method for roots
2. **Built-in Methods:** Math.pow() with precision handling
3. **Exponential Search:** Different search strategy for the answer space

## Personal Notes
This is an excellent example of binary search on answer space, not just sorted arrays. The key insight is that binary search can be applied to any monotonic function. The overflow handling in power calculation is crucial for correctness. This pattern is useful for many mathematical optimization problems where we need to find the optimal value in a range.

---

**Tags:** #searching #binarysearch #answerspace #mathematics #nthroot #medium 