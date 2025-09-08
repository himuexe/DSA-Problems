# Stack Implementation Using ArrayList

**Source:** AC | **Topic:** Stacks | **Difficulty:** Easy  

## Problem Statement

Implement a stack data structure using ArrayList. The implementation should support basic stack operations: push, pop, peek, and isEmpty. The ArrayList should provide dynamic resizing and efficient access to the last element.

## Intuition/Approach

Use ArrayList where:
1. **Last index** represents the top of the stack
2. **Push operation** adds element at the end (using add())
3. **Pop operation** removes and returns last element
4. **Peek operation** returns last element without removal

The key insight is that ArrayList provides O(1) access to the last element, making it ideal for stack operations.

## Key Observations

- ArrayList end serves as stack top for O(1) operations
- Dynamic resizing handled automatically by ArrayList
- Built-in methods (add, remove, get) simplify implementation
- No need for manual memory management

## Algorithm Steps

1. **Initialize:** Create ArrayList to store stack elements
2. **Push Operation:** Use list.add(data) to append element
3. **Pop Operation:**
   - Get last element using list.get(size-1)
   - Remove last element using list.remove(size-1)
   - Return the retrieved element
4. **Peek Operation:** Return list.get(size-1) without removal
5. **isEmpty:** Check if list.size() == 0

## Complexity Analysis

- **Time Complexity:** O(1) for all operations (push, pop, peek, isEmpty)
- **Space Complexity:** O(n) - where n is number of elements in stack

## Edge Cases Considered

- [ ] Empty stack → isEmpty() returns true, pop/peek handle gracefully
- [ ] Single element → pop makes stack empty
- [ ] Large number of elements → ArrayList automatically resizes
- [ ] Pop from empty stack → should handle with bounds checking

## Solution Code

```java
import java.util.*;
public class StackArrayList{
     static class Stack{
         ArrayList<Integer> list = new ArrayList<>();

        public boolean isEmpty(){
            return list.size() == 0;
        }
        
        public void push(int data){
            list.add(data); // Add at end
        }
        
        public int pop(){
            if(isEmpty()){
                return -1; // Error handling
            }
            int top = list.get(list.size()-1); // Get last element
            list.remove(list.size()-1); // Remove last element
            return top;
        }
        
        public int peek(){
            if(isEmpty()){
                return -1; // Error handling
            }
            return list.get(list.size()-1); // Return last element
        }
    }

    public static void main(String[] args){
        Stack st = new Stack();
        st.push(1);
        st.push(2);
        st.push(3);

        while(!st.isEmpty()){
            System.out.println(st.peek());
            st.pop();
        }
    }
}
```

## Alternative Approaches

1. **Array-based Stack:** Use fixed-size array with top pointer
2. **LinkedList-based Stack:** Use linked list for dynamic allocation
3. **Built-in Stack:** Use Java's built-in Stack class

## Personal Notes

- Simplest stack implementation using built-in data structures
- Good introduction to stack concepts without complex memory management
- Demonstrates how ArrayList can be adapted for stack operations
- Practical approach for most real-world applications

---
**Tags:** #stack #arraylist #implementation #data-structure #easy #dynamic-array