# Push Element at Bottom of Stack

**Source:** AC | **Topic:** Stacks | **Difficulty:** Medium  

## Problem Statement

Given a stack and an element, push the element to the bottom of the stack using only stack operations (push, pop, peek, isEmpty) and recursion. No additional data structures should be used.

## Intuition/Approach

Use recursion to temporarily store stack elements and push the new element to the bottom:
1. **Base case:** If stack is empty, push the element directly
2. **Recursive case:** Pop the top element, recursively push the new element to bottom, then push back the popped element

The key insight is that recursion naturally provides temporary storage for stack elements without using additional data structures.

## Key Observations

- Recursion call stack acts as temporary storage for popped elements
- Each recursive call removes one element from top
- New element is pushed when stack becomes empty (bottom reached)
- Popped elements are restored in reverse order (maintaining original stack order)

## Algorithm Steps

1. **Base case:** If stack is empty, push the element and return
2. **Recursive case:**
   - Pop the top element
   - Recursively call pushAtBottom with remaining stack
   - Push back the popped element
3. **Result:** Element is inserted at bottom, original elements maintain order

## Complexity Analysis

- **Time Complexity:** O(n) - Visit each element once
- **Space Complexity:** O(n) - Recursion stack depth

## Edge Cases Considered

- [ ] Empty stack → element becomes only element
- [ ] Single element stack → new element at bottom, original at top
- [ ] Large stack → deep recursion (potential stack overflow)
- [ ] Multiple consecutive pushAtBottom calls → elements accumulate at bottom

## Solution Code

```java
import java.util.*;
public class PushAtBottom {

    public static void pushAtBottom(Stack<Integer> st , int data){
        if(st.isEmpty()){
            st.push(data); // Base case: push to bottom
            return;
        }
        int top = st.pop();
        pushAtBottom(st, data); // Recursively push to bottom
        st.push(top); // Restore popped element
    }

    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        
        System.out.println("Original stack (top to bottom): 3, 2, 1");
        
        pushAtBottom(st, 4);
        
        System.out.println("After pushing 4 at bottom:");
        while (!st.isEmpty()) {
             System.out.println(st.peek());
             st.pop();   
        }
        // Result: 3, 2, 1, 4 (4 is at bottom)
    }
}
```

## Alternative Approaches

1. **Using Additional Stack:** Use second stack to temporarily store elements
2. **Array-based:** Convert to array, insert at beginning, convert back
3. **Iterative with Queue:** Use queue for temporary storage

## Personal Notes

- Fundamental recursive stack operation
- Building block for more complex stack algorithms
- Demonstrates elegance of recursion for stack manipulation
- Important for understanding how recursion can replace iterative auxiliary structures

---
**Tags:** #stack #recursion #helper-function #medium #fundamental #insertion