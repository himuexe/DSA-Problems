# Longest Valid Parentheses

**Source:** Kunal | **Topic:** Stacks | **Difficulty:** Hard  

---

## Problem Statement
Given a string consisting of '(' and ')', return the length of the longest substring that forms a valid parentheses sequence.

## Intuition/Approach
Use a stack of indices to track the last unmatched position. Start with -1 as a sentinel. For '(', push index. For ')', pop; if the stack becomes empty, push current index as the new base; otherwise, the current valid length is `i - stack.peek()`.

## Key Observations
- Track indices, not characters, to derive lengths directly.
- Sentinel `-1` seed handles the first valid span and unmatched leading ')'.
- Each index is pushed and popped at most once.

## Algorithm Steps
1. Initialize stack and push -1.
2. For each index `i` in `s`:
   - If `s[i] == '('`, push `i`.
   - Else pop; if stack becomes empty, push `i`; else update `ans = max(ans, i - stack.peek())`.
3. Return `ans`.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n) in worst case
- **Justification:** Single pass; stack operations are O(1).

## Edge Cases Considered
- [x] Empty string
- [x] No valid parentheses
- [x] All '(' characters
- [x] All ')' characters
- [x] Nested parentheses
- [x] Multiple disjoint valid sequences

## Solution Code

```java
import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}
```

## Alternative Approaches
- Dynamic programming with `dp[i]` as length ending at `i`.
- Two-scan counter method (left-to-right, right-to-left) counting opens/closes.

## Personal Notes
Using indices instead of characters keeps the computation simple and robust to long invalid prefixes.

---
**Tags:** #stacks #strings #parentheses #monotonicStack 