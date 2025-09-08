# Reverse Stack Using Recursion

**Source:** AC | **Topic:** Stacks | **Difficulty:** Medium  

## Problem Statement

Given a stack, reverse the stack using recursion without using any additional data structures. The reversal should be done in-place using only the stack operations (push, pop, peek, isEmpty).

## Intuition/Approach

Use recursion to reverse the stack by combining two recursive functions:
1. **reverseStack():** Recursively removes elements and reverses the remaining stack
2. **pushAtBottom():** Recursively pushes an element to the bottom of the stack

The key insight is that recursion naturally provides the temporary storage needed to reverse the stack without additional data structures.

## Key Observations

- Recursion call stack acts as temporary storage
- Each recursive call removes one element and processes remaining stack
- pushAtBottom() ensures reversed element goes to correct position
- No additional data structures needed besides recursion stack

## Algorithm Steps

1. **reverseStack(stack):**
   - Base case: if stack is empty, return
   - Pop top element
   - Recursively reverse remaining stack
   - Push popped element to bottom of reversed stack

2. **pushAtBottom(stack, element):**
   - Base case: if stack is empty, push element
   - Pop top element
   - Recursively push element to bottom
   - Push back the popped element

## Complexity Analysis

- **Time Complexity:** O(n²) - For each element, pushAtBottom takes O(n) time
- **Space Complexity:** O(n) - Recursion stack depth

## Edge Cases Considered

- [ ] Empty stack → remains empty
- [ ] Single element → remains unchanged
- [ ] Two elements → order reversed
- [ ] All elements same → order reversed but appears same
- [ ] Large stack → deep recursion (potential stack overflow)

## Solution Code

```java
import java.util.*;
public class ReverseStack {

    public static void reverseStack(Stack<Integer> st){
        if(st.isEmpty()){
            return; // Base case
        }
        int top = st.pop();
        reverseStack(st); // Reverse remaining stack
        pushAtBottom(st, top); // Push current element to bottom
    }

    public static void pushAtBottom(Stack<Integer> st , int data){
        if(st.isEmpty()){
            st.push(data); // Base case: push to bottom
            return;
        }
        int top = st.pop();
        pushAtBottom(st, data); // Recursively push to bottom
        st.push(top); // Restore popped element
    }

    public static void printStack(Stack<Integer> st) {
        Stack<Integer> temp = new Stack<>();
        while (!st.isEmpty()) {
            int top = st.pop();
            System.out.println(top); 
            temp.push(top); 
        }
        // Restore original stack
        while (!temp.isEmpty()) {
            st.push(temp.pop());
        }
    }

    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        
        System.out.println("Original stack:");
        printStack(st);
        
        reverseStack(st);
        
        System.out.println("Reversed stack:");
        printStack(st);
    }
}
```

## Alternative Approaches

1. **Using Additional Stack:** Use second stack to reverse (not in-place)
2. **Array-based:** Convert to array, reverse, convert back
3. **Iterative with Queue:** Use queue for temporary storage

## Personal Notes

- Elegant demonstration of recursion power
- Shows how recursion can replace iterative auxiliary data structures
- Important for understanding recursive problem decomposition
- Good practice for recursive thinking and stack manipulation

---
**Tags:** #stack #recursion #in-place #medium #elegant-solution #interview-prep