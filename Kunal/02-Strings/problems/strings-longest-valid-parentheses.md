# Longest Valid Parentheses

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Hard  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Find the length of the longest valid parentheses substring. A valid parentheses substring contains properly matched opening and closing parentheses.

## Intuition/Approach
Use stack to track indices of unmatched parentheses. The stack helps determine boundaries of valid parentheses sequences by maintaining indices of unmatched characters.

## Key Observations
- Stack tracks indices, not characters
- Initialize stack with -1 as base for length calculation
- For '(': push index onto stack
- For ')': pop from stack, calculate length using remaining top
- Empty stack after popping means current ')' is unmatched
- Distance between current index and stack top gives valid length

## Algorithm Steps
1. Initialize stack with -1 as base
2. Iterate through string:
   - If '(': push current index
   - If ')': 
     - Pop from stack
     - If stack becomes empty: push current index (unmatched ')')
     - Else: calculate length as (current_index - stack.top())
3. Track maximum length found

## Complexity Analysis
- **Time Complexity:** O(n) where n is string length
- **Space Complexity:** O(n) for stack in worst case
- **Justification:** Single pass through string, stack operations are O(1)

## Edge Cases Considered
- [x] Empty string (length 0)
- [x] String with no valid parentheses
- [x] All opening parentheses
- [x] All closing parentheses
- [x] Nested parentheses
- [x] Multiple valid sequences

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public int longestValidParentheses(String s) {
        int ans =0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack.push(i);
            }
            else{
                stack.pop();
                if(stack.size() ==  0){
                    stack.push(i);
                }
                else{
                    ans = Math.max(ans, i- stack.peek());
                }
            }
        }
        return ans;
    }
}
```

## Alternative Approaches
- **Dynamic Programming:** DP array to track valid lengths
- **Two Pass:** Left-to-right and right-to-left counting
- **Counter Approach:** Track balance of parentheses

## Related Problems
- **AC:** [Parentheses matching problems]
- **Kunal:** [ValidParentheses.java - basic parentheses validation]
- **LeetCode:** [Longest Valid Parentheses - Problem 32]

## Personal Notes
Advanced stack application for parentheses problems. The key insight is using indices instead of characters to calculate lengths. Understanding stack state transitions is crucial. This pattern extends to other bracket matching problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #stack #parentheses #hardProblem #dynamicProgramming 