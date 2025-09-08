# Maximum Area in Histogram

**Source:** AC | **Topic:** Stacks | **Difficulty:** Hard  

## Problem Statement

Given an array of integers representing the heights of bars in a histogram, find the maximum area of a rectangle that can be formed using one or more consecutive bars.

## Intuition/Approach

The key insight is to use a stack-based approach to find the next smaller element on both sides for each bar:
1. **Next Smaller Right (NSR):** For each bar, find the nearest smaller bar to its right
2. **Next Smaller Left (NSL):** For each bar, find the nearest smaller bar to its left  
3. **Calculate Area:** For each bar as height, width = NSR[i] - NSL[i] - 1

The maximum area rectangle with bar i as the shortest bar will have:
- Height = arr[i]
- Width = distance between next smaller elements on both sides

## Key Observations

- Stack stores indices, not values, to calculate width efficiently
- Monotonic stack technique ensures O(n) time complexity
- Each element is pushed and popped at most once
- Width calculation: NSR[i] - NSL[i] - 1 gives valid rectangle width

## Algorithm Steps

1. **Find Next Smaller Right:**
   - Traverse array from right to left
   - Use stack to maintain indices of elements in increasing order
   - For each element, pop smaller elements and find next smaller
   
2. **Find Next Smaller Left:**
   - Traverse array from left to right
   - Use stack to maintain indices of elements in increasing order
   - For each element, pop smaller elements and find next smaller

3. **Calculate Maximum Area:**
   - For each bar i: area = height[i] * (NSR[i] - NSL[i] - 1)
   - Track maximum area found

## Complexity Analysis

- **Time Complexity:** O(n) - Each element pushed and popped once from stack
- **Space Complexity:** O(n) - Stack space and NSR/NSL arrays

## Edge Cases Considered

- [ ] Empty array → return 0
- [ ] Single element → return element value
- [ ] All elements same → return n * element_value
- [ ] Strictly increasing → each element forms rectangle with previous elements
- [ ] Strictly decreasing → each element forms rectangle of width 1

## Solution Code

```java
import java.util.*;
public class MaxAreaHistogram {

    public static void maxArea(int[] arr){
        int maxArea =0;
        int[] nsr = new int[arr.length];
        int[] nsl = new int[arr.length];

        // Find Next Smaller Right
        Stack<Integer> st = new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                nsr[i]=arr.length;
            }
            else{
                nsr[i] = st.peek();
            }
            st.push(i);
        }

        // Find Next Smaller Left
        st = new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                nsl[i]=-1;
            }
            else{
                nsl[i] = st.peek();
            }
            st.push(i);
        }

        // Calculate maximum area
        for(int i=0;i<arr.length;i++){
            int height = arr[i];
            int width = nsr[i] - nsl[i] -1;
            int currArea = height*width;
            maxArea = Math.max(currArea,maxArea);
        }
        System.out.println("Max area : "+maxArea);
    }

    public static void main(String[] args){
        int[] arr ={2,1,5,6,2,3};
        maxArea(arr);
    }
}
```

## Alternative Approaches

1. **Brute Force O(n²):** For each bar, expand left and right until smaller element found
2. **Single Pass Stack:** Calculate area while finding next smaller elements in single traversal
3. **Divide and Conquer:** Recursively find maximum area in left and right subarrays

## Personal Notes

- Classic stack problem demonstrating monotonic stack technique
- Important for understanding next smaller/greater element patterns
- Foundation for 2D matrix rectangle problems
- Common in technical interviews for stack data structure questions

---
**Tags:** #stack #monotonic-stack #next-smaller-element #histogram #rectangle #hard #interview-prep