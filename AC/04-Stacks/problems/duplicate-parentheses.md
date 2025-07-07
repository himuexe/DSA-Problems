# Duplicate Parentheses Detection

**Source:** AC (Apna College)  
**Topic:** Stacks  
**Difficulty:** Medium  
**Date:** 2025-07-05

## Problem Statement

Given a string containing parentheses and operators, determine if the expression contains duplicate parentheses. Duplicate parentheses occur when a subexpression is enclosed by unnecessary parentheses.

Examples:
- `((a+b))` → has duplicate parentheses
- `(a+b)` → no duplicate parentheses
- `((a+b)+(c+d))` → no duplicate parentheses

## Intuition/Approach

Use a stack to track characters and count elements between matching parentheses:
1. **Push all characters** onto stack until closing parenthesis is encountered
2. **Count elements** between opening and closing parentheses
3. **If count < 1**, then parentheses are duplicate (empty or only parentheses inside)
4. **Remove the opening parenthesis** and continue processing

The key insight is that valid parentheses must contain at least one operator or operand.

## Key Observations

- Stack-based approach for balanced parentheses processing
- Duplicate parentheses contain fewer than 1 element between them
- Only need to check when encountering closing parenthesis ')'
- All other characters (operators, operands, opening parentheses) are pushed

## Algorithm Steps

1. **Initialize** empty stack
2. **For each character** in string:
   - If closing parenthesis ')': count elements until opening '('
   - If count < 1: return true (duplicate found)
   - Remove opening parenthesis and continue
   - Otherwise: push character onto stack
3. **Return false** if no duplicates found

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(n) - Stack space for storing characters

## Edge Cases

- [ ] Empty string → no duplicates
- [ ] Single pair "()" → has duplicates  
- [ ] No parentheses → no duplicates
- [ ] Only opening parentheses → invalid input
- [ ] Only closing parentheses → invalid input
- [ ] Nested valid parentheses → no duplicates

## Solution Code

```java
import java.util.*;
public class DuplicatePar {

    public static boolean isDuplicate(String str){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == ')'){
                int count =0;
                while(!st.isEmpty() && st.peek() != '('){
                    st.pop();
                    count++;
                }
                if(count <1){
                    return true; // Duplicate parentheses found
                }
                else{
                    st.pop(); // Remove opening parenthesis
                }
            }
            else{
                st.push(ch); // Push all other characters
            }
        }
        return false;
    }

    public static void main(String[] args){
        String str1 = "((a+b))";
        String str2 = "(a-b)";
        System.out.println(isDuplicate(str2)); // false
        System.out.println(isDuplicate(str1)); // true
    }
}
```

## Alternative Approaches

1. **Counter-based:** Use counter instead of stack to track depth
2. **Regex-based:** Use regular expressions to find duplicate patterns
3. **Recursive:** Recursively check for nested duplicate parentheses

## Related Problems

- **Valid Parentheses** (basic parentheses validation)
- **Minimum Remove to Make Valid Parentheses** (LeetCode 1249)
- **Remove Duplicate Letters** (stack-based duplicate removal)
- **Balanced Parentheses** (general parentheses problems)

## Personal Notes

- Important for expression parsing and validation
- Demonstrates stack usage for nested structure analysis
- Common in compiler design and mathematical expression processing
- Good practice for understanding stack-based counting techniques

## Tags

`#stack` `#parentheses` `#duplicate-detection` `#expression-parsing` `#medium` `#string-processing`

---

**Revision History:**
- 2025-07-05: Initial documentation with comprehensive algorithm analysis 