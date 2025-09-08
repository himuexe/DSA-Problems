# Valid Parentheses

**Source:** AC | **Topic:** Stacks | **Difficulty:** Easy  

## Problem Statement

Given a string containing parentheses characters '(', ')', '{', '}', '[', ']', determine if the input string has valid (balanced) parentheses.

A string is valid if:
1. Open brackets must be closed by the same type of brackets
2. Open brackets must be closed in the correct order
3. Every close bracket has a corresponding open bracket

## Intuition/Approach

Use a stack to track opening brackets and match them with closing brackets:
1. **Push opening brackets** onto stack
2. **For closing brackets**, check if stack is empty or top doesn't match
3. **Pop matching opening bracket** when valid closing bracket found
4. **String is valid** if stack is empty at the end

The stack ensures proper nesting and matching of bracket types.

## Key Observations

- Stack naturally handles the Last-In-First-Out nature of nested brackets
- Only need to track opening brackets in stack
- Each closing bracket must match the most recent unmatched opening bracket
- Empty stack at end indicates all brackets were properly matched

## Algorithm Steps

1. **Initialize** empty stack
2. **For each character** in string:
   - If opening bracket: push onto stack
   - If closing bracket:
     - Check if stack is empty → return false
     - Check if top of stack matches current closing bracket
     - If match: pop from stack
     - If no match: return false
3. **Return** stack.isEmpty() (true if all brackets matched)

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(n) - Stack space in worst case (all opening brackets)

## Edge Cases Considered

- [ ] Empty string → valid (true)
- [ ] Single opening bracket → invalid
- [ ] Single closing bracket → invalid
- [ ] All opening brackets → invalid
- [ ] All closing brackets → invalid
- [ ] Correct brackets but wrong order → invalid

## Solution Code

```java
import java.util.*;
public class ValidPar {

    public static boolean isValid(String str){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == '{' || ch == '[' || ch == '('){
                st.push(ch);
            }
            else{
                if(st.isEmpty()){
                    return false; // No matching opening bracket
                }
                if((st.peek() =='(' && ch == ')') || 
                   (st.peek()== '{' && ch == '}') || 
                   (st.peek() == '[' && ch ==']')){
                    st.pop(); // Valid match found
                }
                else{
                    return false; // Invalid bracket type match
                }
            }
        }
        // Valid if all brackets are matched
        return st.isEmpty();
    }

    public static void main(String[] args){
        String str = "({})[";
        if(isValid(str)){
            System.out.println("Valid hai");
        }
        else{
            System.out.println("Nahi hai");
        }
    }
}
```

## Alternative Approaches

1. **Counter-based:** Use separate counters for each bracket type (works only for simple cases)
2. **Recursive:** Recursively check for matching brackets
3. **HashMap-based:** Use HashMap to map closing brackets to opening brackets

## Personal Notes

- Fundamental stack problem - excellent for understanding stack basics
- Classic example of LIFO (Last In, First Out) principle
- Foundation for more complex bracket and expression problems
- Very common in technical interviews as a warm-up question

---
**Tags:** #stack #parentheses #bracket-matching #easy #fundamental #interview-prep