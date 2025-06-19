# FizzBuzz Problem

**Source:** Kunal | **Topic:** Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Generate a list of strings for numbers 1 to n where:
- Numbers divisible by 3 and 5 → "FizzBuzz"
- Numbers divisible by 3 only → "Fizz"  
- Numbers divisible by 5 only → "Buzz"
- All other numbers → number as string

## Intuition/Approach
Iterate through numbers 1 to n, check divisibility conditions in the correct order (3 and 5 first, then individual conditions), and add appropriate strings to result list.

## Key Observations
- Must check divisibility by both 3 and 5 first (to avoid overriding with individual conditions)
- Use modulo operator for divisibility checks
- Convert integers to strings for non-Fizz/Buzz numbers
- ArrayList provides dynamic sizing for result

## Algorithm Steps
1. Create ArrayList to store results
2. Loop from 1 to n (inclusive)
3. For each number i:
   - If i % 3 == 0 AND i % 5 == 0 → add "FizzBuzz"
   - Else if i % 3 == 0 → add "Fizz"
   - Else if i % 5 == 0 → add "Buzz"
   - Else → add i as string
4. Return the list

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass through n numbers, storing n results

## Edge Cases Considered
- [x] n = 1 (single element)
- [x] Numbers divisible by 3 only
- [x] Numbers divisible by 5 only  
- [x] Numbers divisible by both 3 and 5
- [x] Numbers not divisible by either

## Solution Code

```java
// Language: Java
public static List<String> fizzBuzz(int n) {
    List<String> list = new ArrayList<>();
    for(int i = 1; i <= n; i++){
        if(i % 3 == 0 && i % 5 == 0){
            list.add("FizzBuzz");
        }
        else if(i % 3 == 0){
            list.add("Fizz");
        }
        else if(i % 5 == 0){
            list.add("Buzz");
        }
        else{
            list.add(Integer.toString(i));
        }
    }
    return list;
}
```

## Alternative Approaches
- **String Concatenation:** Build string dynamically (Fizz + Buzz)
- **Modular Arithmetic:** Use counters instead of modulo operations
- **Map/Dictionary:** Store divisibility rules in data structure

## Related Problems
- **AC:** [OddEven.java - conditional logic patterns]
- **Kunal:** [CaseCheck.java - condition checking approaches]
- **LeetCode:** [Fizz Buzz - Problem 412]

## Personal Notes
Classic programming interview problem. Good practice for conditional logic and order of operations. The list-based approach is clean and efficient.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #conditionals #fizzbuzz #modularArithmetic #lists 