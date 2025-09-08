# Stock Span Problem

**Source:** AC | **Topic:** Stacks | **Difficulty:** Medium  

## Problem Statement

The stock span problem is to find the span of stock prices for each day. The span on a given day is the maximum number of consecutive days (ending on that day) for which the price was less than or equal to the price on that day.

Example:
- Prices: [100, 80, 60, 70, 60, 85, 100]
- Spans:  [1, 1, 1, 2, 1, 4, 7]

## Intuition/Approach

Use a stack-based approach to efficiently find the span for each day:
1. **Stack stores indices** of previous days with higher prices
2. **For each day**, pop indices while their prices are <= current price
3. **Span calculation**: current_index - previous_higher_index
4. **If stack is empty**, span = current_index + 1 (all previous days)

The key insight is that once a day's price is lower than current day, all days between them are irrelevant for future span calculations.

## Key Observations

- Stack maintains indices of days with higher prices in increasing order
- Each day is pushed and popped at most once from stack
- Span represents consecutive days with price <= current day's price
- Previous higher price determines the span boundary

## Algorithm Steps

1. **Initialize** stack and span array
2. **Set first day span** to 1 and push index 0
3. **For each subsequent day:**
   - Pop indices while their prices <= current price
   - If stack empty: span = current_index + 1
   - Otherwise: span = current_index - top_of_stack
   - Push current index onto stack
4. **Return** span array

## Complexity Analysis

- **Time Complexity:** O(n) - Each element pushed and popped at most once
- **Space Complexity:** O(n) - Stack space and span array

## Edge Cases Considered

- [ ] Single day → span = 1
- [ ] All prices decreasing → all spans = 1
- [ ] All prices increasing → spans = [1, 2, 3, ..., n]
- [ ] All prices same → spans = [1, 2, 3, ..., n]
- [ ] Empty array → empty result

## Solution Code

```java
import java.util.*;
public class StockSpan {

    public static void stockSpan(int[] stocks, int[] span){
        Stack<Integer> st = new Stack<>();
        span[0] = 1; // First day always has span 1
        st.push(0);  // Push first day's index
        
        for(int i=1;i<stocks.length;i++){
            int currPrice= stocks[i];
            
            // Pop days with price <= current price
            while(!st.isEmpty() && currPrice > stocks[st.peek()]){
                st.pop();
            }
            
            // Calculate span
            if(st.isEmpty()){
                span[i] = i+1; // All previous days
            }
            else{
                int prevHigh = st.peek();
                span[i] = i-prevHigh; // Days since previous higher
            }
            
            st.push(i); // Push current day's index
        }
    }

    public static void main(String[] args){
        int stocks[] = {100,80,60,70,60,85,100};
        int span[] = new int[stocks.length];
        stockSpan(stocks,span);
        
        for(int i=0;i<span.length;i++){
            System.out.print(span[i]+" ");
        }
    }
}
```

## Alternative Approaches

1. **Brute Force O(n²):** For each day, scan backwards to count consecutive days
2. **Previous Greater Element:** Find previous greater element and calculate span
3. **Dynamic Programming:** Use memoization to avoid recalculating spans

## Personal Notes

- Classic application of monotonic stack in finance/trading
- Important for understanding how stack can optimize time-series problems
- Good example of how stack maintains "relevant" previous elements
- Common in interviews for demonstrating stack usage in real-world scenarios

---
**Tags:** #stack #monotonic-stack #time-series #finance #medium #array-processing