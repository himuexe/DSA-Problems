# Decode String

**Source:** Kunal | **Topic:** Stacks | **Difficulty:** Medium  

---

## Problem Statement
Given an encoded string in the form `k[encoded_string]`, decode it by repeating `encoded_string` exactly `k` times. Nested encodings are allowed, e.g., `3[a2[c]] -> accaccacc`.

## Intuition/Approach
Use two stacks to handle nested structures: a number stack for repetition counts and a string stack for accumulated partial strings. On `[`, push current state; on `]`, pop state and expand; digits build multi-digit counts; letters append to the current builder.

## Key Observations
- Nested encodings require LIFO handling, hence stacks.
- Numbers can be multi-digit; parse completely before encountering `[`.
- Use a `StringBuilder` for efficient concatenation.

## Algorithm Steps
1. Initialize `numStack`, `strStack`, and an empty `StringBuilder curr`.
2. Iterate characters in `s`:
   - If digit: parse full number and push to `numStack`.
   - If `[` : push `curr.toString()` to `strStack`; reset `curr`.
   - If `]` : pop `k` and previous string; set `curr = prev + curr.repeat(k)`.
   - Else: append letter to `curr`.
3. Return `curr.toString()`.

## Complexity Analysis
- **Time Complexity:** O(n + total_expanded_length)
- **Space Complexity:** O(n + nesting_depth)
- **Justification:** Each input char is processed once; expansions contribute to output size.

## Edge Cases Considered
- [x] Single level encoding `3[a]`
- [x] Nested encoding `2[a3[b]]`
- [x] Multi-digit numbers `12[a]`
- [x] Adjacent encoded strings `2[a]3[b]`
- [x] Plain string with no encoding

## Solution Code

```java
import java.util.*;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                numStack.push(num);
            } else if (ch == '[') {
                strStack.push(curr.toString());
                curr = new StringBuilder();
            } else if (ch == ']') {
                int k = numStack.pop();
                StringBuilder temp = new StringBuilder(strStack.pop());
                for (int j = 0; j < k; j++) temp.append(curr);
                curr = temp;
            } else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}
```

## Alternative Approaches
- Recursive descent parsing with index reference.
- Single-stack encoding of both counts and sentinel markers, though clarity may suffer.

## Personal Notes
The two-stack approach cleanly separates count and string state and is easy to reason about for deeply nested inputs.

---
**Tags:** #stacks #strings #parsing #nested** 