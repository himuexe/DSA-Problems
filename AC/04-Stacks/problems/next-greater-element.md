# Next Greater Element

**Source:** AC (Apna College)  
**Topic:** Stacks  
**Difficulty:** Medium  
**Date:** 2025-07-05

## Problem Statement

Given an array of integers, find the next greater element for each element in the array. The next greater element for an element x is the first greater element to the right of x in the array. If no greater element exists, return -1.

## Intuition/Approach

Use a stack-based approach to efficiently find the next greater element:
1. **Traverse array from right to left** to build the result
2. **Maintain a stack** of indices in decreasing order (monotonic stack)
3. **For each element**, pop smaller elements from stack until finding greater element
4. **Top of stack** (if exists) is the next greater element

The key insight is that when processing element i, all elements to the right have been processed, so the stack contains potential candidates for the next greater element.

## Key Observations

- Stack stores indices, not values, for efficient comparison
- Monotonic decreasing stack ensures O(n) time complexity
- Each element is pushed and popped at most once
- Right-to-left traversal ensures next greater elements are already processed

## Algorithm Steps

1. **Initialize** empty stack and result array
2. **Traverse array from right to left:**
   - Pop elements from stack while they are <= current element
   - If stack is empty: no greater element exists (set -1)
   - Otherwise: top of stack is the next greater element
   - Push current element's index onto stack
3. **Return** the result array

## Complexity Analysis

- **Time Complexity:** O(n) - Each element pushed and popped at most once
- **Space Complexity:** O(n) - Stack space and result array

## Edge Cases

- [ ] Empty array → empty result
- [ ] Single element → -1 (no greater element)
- [ ] All elements in decreasing order → all -1
- [ ] All elements in increasing order → each element has next greater
- [ ] All elements same → all -1

## Solution Code

```java
import java.util.*;
public class NextGreaterElement {

    public static void main(String[] args){
        int arr[] = {6,8,0,1,3};
        Stack<Integer> st = new Stack<>();
        int nxtGreater[] = new int[arr.length];

        // Traverse from right to left
        for(int i = arr.length-1;i>=0;i--){
            // Pop elements smaller than or equal to current
            while(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                st.pop();
            }
            
            // Set next greater element
            if(st.isEmpty()){
                nxtGreater[i]=-1; // No greater element
            }
            else{
                nxtGreater[i] = arr[st.peek()]; // Next greater found
            }
            
            // Push current index
            st.push(i);
        }
        
        // Print result
        for(int i=0;i<nxtGreater.length;i++){
            System.out.print(nxtGreater[i]+" ");
        }
        System.out.println();
    }
}
```

## Alternative Approaches

1. **Brute Force O(n²):** For each element, scan right to find next greater
2. **Left-to-right traversal:** Process elements left to right (more complex)
3. **Circular array variant:** Use modulo arithmetic for circular next greater

## Related Problems

- **Next Greater Element II** (LeetCode 503) - Circular array version
- **Daily Temperatures** (LeetCode 739) - Find next warmer day
- **Maximum Area in Histogram** (uses next smaller element)
- **Stock Span Problem** (similar stack-based approach)

## Personal Notes

- Fundamental monotonic stack problem
- Template for many stack-based array problems
- Important for understanding decreasing/increasing stack patterns
- Common building block for more complex stack algorithms

## Tags

`#stack` `#monotonic-stack` `#next-greater-element` `#array` `#medium` `#template-problem`

---

**Revision History:**
- 2025-07-05: Initial documentation with comprehensive algorithm analysis 