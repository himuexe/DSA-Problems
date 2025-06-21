# Decode String

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Decode a string encoded in format k[encoded_string]. The encoded_string inside brackets should be repeated k times. For example, "3[a2[c]]" becomes "accaccacc".

## Intuition/Approach
Use two stacks: one for numbers (repetition counts) and one for strings (accumulated strings). When encountering '[', save current state. When encountering ']', restore and repeat.

## Key Observations
- Nested structure requires stack for proper handling
- Numbers can be multi-digit, need careful parsing
- '[' marks start of new nested level
- ']' marks end of current level, triggers repetition
- Two stacks maintain state for nested structure

## Algorithm Steps
1. Initialize number stack, string stack, and StringBuilder
2. Iterate through input string:
   - If digit: parse complete number (handle multi-digit)
   - If '[': push current string to stack, reset StringBuilder
   - If ']': pop count and previous string, repeat current string
   - If letter: append to current StringBuilder
3. Return final string

## Complexity Analysis
- **Time Complexity:** O(maxK^countK * n) where maxK is max repetition, countK is nested levels, n is string length
- **Space Complexity:** O(sum of all strings in stacks)
- **Justification:** Each character may be repeated multiple times based on nesting

## Edge Cases Considered
- [x] Single level encoding "3[a]"
- [x] Nested encoding "2[a3[b]]"
- [x] Multi-digit numbers "12[a]"
- [x] Adjacent encoded strings "2[a]3[b]"
- [x] No encoding (plain string)

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                int num = ch -'0';
                while(i+1< len && Character.isDigit(s.charAt(i+1))){
                    num = num*10 + s.charAt(i+1) - '0';
                    i++;
                }
                numStack.push(num);
            }
            else if( ch == '['){
                strStack.push(sb.toString());
                sb = new StringBuilder();
            }
            else if( ch == ']'){
                int k = numStack.pop();
                StringBuilder temp = new StringBuilder(strStack.pop());
                for(int j=0; j<k;j++){
                    temp.append(sb);
                }
                sb = temp;
            }
            else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
```

## Alternative Approaches
- **Recursive Approach:** Recursive parsing with index tracking
- **Single Stack:** Combine number and string in single stack
- **Regular Expression:** Pattern matching (less efficient)

## Related Problems
- **AC:** [Stack-based parsing problems]
- **Kunal:** [String manipulation with stacks]
- **LeetCode:** [Decode String - Problem 394]

## Personal Notes
Complex stack application with nested structure handling. Multi-digit number parsing is tricky detail. Understanding when to save/restore state is crucial. The two-stack approach cleanly separates concerns.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #stack #parsing #nestedStructure #stringBuilding 