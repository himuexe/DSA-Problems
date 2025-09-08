# Reverse String Using Stack

**Source:** AC | **Topic:** Stacks | **Difficulty:** Easy  

## Problem Statement

Given a string, reverse it using a stack data structure. The reversal should utilize the LIFO (Last In, First Out) property of stacks.

## Intuition/Approach

Use the natural LIFO property of stacks to reverse the string:
1. **Push all characters** of the string onto the stack
2. **Pop characters one by one** and append to result
3. **LIFO order** automatically reverses the string

The stack acts as a temporary storage that naturally reverses the order due to its LIFO property.

## Key Observations

- Stack LIFO property automatically handles reversal
- Simple and intuitive approach for string reversal
- No complex logic needed - just push all, then pop all
- StringBuilder used for efficient string concatenation

## Algorithm Steps

1. **Initialize** empty stack and StringBuilder
2. **Push all characters** from string onto stack
3. **Pop characters** until stack is empty
4. **Append each popped character** to result
5. **Return** the reversed string

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass to push + single pass to pop
- **Space Complexity:** O(n) - Stack space for storing characters

## Edge Cases Considered

- [ ] Empty string → empty result
- [ ] Single character → same character
- [ ] Palindrome → reversed palindrome
- [ ] All same characters → appears unchanged
- [ ] Very long string → potential memory issues

## Solution Code

```java
import java.util.*;

public class ReverseString {

    public static String reverse(String str){
        Stack<Character> st = new Stack<>();
        int idx = 0;
        
        // Push all characters onto stack
        while(idx < str.length()){
            st.push(str.charAt(idx));
            idx++;
        }
        
        // Pop characters to build reversed string
        StringBuilder result = new StringBuilder("");
        while(!st.isEmpty()){
            char curr = st.pop();
            result.append(curr);
        }
        
        return result.toString();
    }

    public static void main(String[] args){
        String str = "abcd";
        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverse(str));
    }
}
```

## Alternative Approaches

1. **Two Pointer Technique:** Swap characters from both ends (O(1) space)
2. **Recursion:** Recursively reverse substrings
3. **Built-in Methods:** Use StringBuilder.reverse() or Collections.reverse()
4. **Character Array:** Convert to char array and reverse in-place

## Personal Notes

- Excellent introductory stack problem
- Demonstrates fundamental stack operations clearly
- Good for understanding LIFO principle
- Simple but effective illustration of stack usage

---
**Tags:** #stack #string #reversal #easy #fundamental #lifo