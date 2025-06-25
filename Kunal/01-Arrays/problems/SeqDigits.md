# Sequential Digits

**Date:** June 25, 2025  
**Category:** Kunal Arrays  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** Number Generation/String Manipulation

## Problem Statement

An integer has **sequential digits** if and only if each digit in the number is one more than the previous digit.

Return a **sorted** list of all the integers in the range `[low, high]` inclusive that have sequential digits.

**Example:**
```
Input: low = 100, high = 300
Output: [123,234]
Explanation: 123 has sequential digits, 234 has sequential digits.

Input: low = 1000, high = 13000  
Output: [1234,2345,3456,4567,5678,6789,12345]
```

## Intuition/Approach

**Brute Force Generation with String Template:**
- **Key Insight:** Use template string "123456789" to generate all possible sequential digit numbers
- **Nested loops:** Outer loop for length, inner loop for starting digit
- **Range filtering:** Only include numbers within [low, high] range

**Algorithm Logic:**
1. Use template string "123456789" as source of sequential digits
2. For each possible length (1 to 9 digits)
3. For each possible starting position in template
4. Extract substring and convert to integer
5. Check if number falls within [low, high] range

## Algorithm Steps

1. **Initialize:** Template string "123456789" and result list
2. **Length iteration:** For `i` from 1 to 9 (possible lengths)
3. **Starting position:** For `j` from 0 to (9-i) (valid starting positions)
4. **Extract:** Get substring from `j` to `i+j`
5. **Convert:** Parse substring to integer
6. **Filter:** Add to result if within [low, high] range
7. **Return:** Sorted list (naturally sorted due to generation order)

## Key Observations

- **Template-based generation:** Avoids complex digit manipulation
- **Natural sorting:** Generation order produces sorted results
- **Bounded generation:** Maximum 9 digits possible (1-9)
- **Efficient filtering:** Check range during generation
- **No duplicates:** Each valid sequential number generated exactly once

## Time & Space Complexity

- **Time Complexity:** O(1) - Fixed number of possible sequential digit numbers (≤ 45)
- **Space Complexity:** O(1) - Output space not counted, constant extra space

## Edge Cases Considered

- [x] Single digit range (1-9 all have sequential digits)
- [x] No valid numbers in range
- [x] Range includes maximum sequential number (123456789)
- [x] Range starts/ends with sequential numbers
- [x] Very small range with no sequential numbers

## Solution Code

```java
import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String str = "123456789";
        List<Integer> list = new ArrayList<>();
        
        // Try all possible lengths (1 to 9 digits)
        for(int i = 1; i <= 9; i++) {
            // Try all possible starting positions
            for(int j = 0; i + j <= 9; j++) {
                String tmp = str.substring(j, i + j);
                int val = Integer.valueOf(tmp);
                
                if(val >= low && val <= high) {
                    list.add(val);
                }
            }
        }
        
        return list;
    }
}
```

## Algorithm Walkthrough

### Example: low = 100, high = 300

**Length 1:** 1,2,3,4,5,6,7,8,9 (all < 100, skip)
**Length 2:** 12,23,34,45,56,67,78,89 (all < 100, skip)  
**Length 3:** 
- j=0: "123" → 123 (✓ in range [100,300])
- j=1: "234" → 234 (✓ in range [100,300])
- j=2: "345" → 345 (✗ > 300)
- j=3: "456" → 456 (✗ > 300)
- ... (all remaining > 300)

**Result:** [123, 234]

## All Possible Sequential Digit Numbers

**1-digit:** 1,2,3,4,5,6,7,8,9
**2-digit:** 12,23,34,45,56,67,78,89
**3-digit:** 123,234,345,456,567,678,789
**4-digit:** 1234,2345,3456,4567,5678,6789
**5-digit:** 12345,23456,34567,45678,56789
**6-digit:** 123456,234567,345678,456789
**7-digit:** 1234567,2345678,3456789
**8-digit:** 12345678,23456789
**9-digit:** 123456789

**Total:** 45 numbers

## Alternative Approaches

1. **BFS/Queue-based Generation:**
```java
public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    
    // Initialize with single digits
    for(int i = 1; i <= 9; i++) {
        queue.offer(i);
    }
    
    while(!queue.isEmpty()) {
        int num = queue.poll();
        if(num >= low && num <= high) {
            result.add(num);
        }
        
        if(num > high) break;
        
        int lastDigit = num % 10;
        if(lastDigit < 9) {
            queue.offer(num * 10 + lastDigit + 1);
        }
    }
    
    return result;
}
```

2. **Recursive Generation:**
```java
public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> result = new ArrayList<>();
    
    for(int start = 1; start <= 9; start++) {
        generateSequential(start, low, high, result);
    }
    
    Collections.sort(result);
    return result;
}

private void generateSequential(int num, int low, int high, List<Integer> result) {
    if(num > high) return;
    if(num >= low) result.add(num);
    
    int lastDigit = num % 10;
    if(lastDigit < 9) {
        generateSequential(num * 10 + lastDigit + 1, low, high, result);
    }
}
```

## Mathematical Analysis

### Generation Pattern
Sequential digit numbers follow a clear pattern:
- Each length k has (10-k) possible numbers
- Starting digit ranges from 1 to (10-k)
- Each number is formed by k consecutive digits

### Count by Length
- Length 1: 9 numbers
- Length 2: 8 numbers  
- Length 3: 7 numbers
- ...
- Length 9: 1 number

**Total:** 9+8+7+6+5+4+3+2+1 = 45 numbers

## Performance Analysis

### String vs Mathematical Approach
- **String approach:** Simple substring operations, easy to understand
- **Mathematical approach:** Digit manipulation, potentially faster
- **BFS approach:** Natural generation order, more complex

### Time Complexity Comparison
All approaches are O(1) since there are only 45 possible numbers, but:
- **String approach:** Constant string operations
- **BFS approach:** Queue operations overhead  
- **Recursive approach:** Function call overhead

## Key Learnings

1. **Template technique:** Use string template for digit sequence generation
2. **Bounded problem:** Fixed upper limit makes brute force viable
3. **Natural ordering:** Generation order can provide sorted results
4. **Range filtering:** Check constraints during generation for efficiency
5. **Multiple approaches:** Same result with different implementation strategies

## Related Problems

- Numbers With Same Consecutive Differences
- Count Numbers with Unique Digits
- Self Dividing Numbers
- Monotone Increasing Digits
- Numbers At Most N Given Digit Set

**LeetCode Connection:** LeetCode #1291 - Sequential Digits 