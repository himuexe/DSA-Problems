# 132 Pattern Detection

**Source:** Kunal | **Topic:** Stacks | **Difficulty:** Medium  

---

## Problem Statement
Given an array of integers, determine whether there exists a subsequence of length 3 with indices i < j < k such that nums[i] < nums[k] < nums[j]. This is known as the 132 pattern.

## Intuition/Approach
Use preprocessing plus a stack. Precompute the minimum value up to each index to serve as potential nums[i]. Traverse from right to left keeping a decreasing stack of candidates for nums[k]. For each nums[j], discard stack elements ≤ min[i], check if the top is < nums[j] to confirm a 132 pattern.

## Key Observations
- Need i < j < k with nums[i] < nums[k] < nums[j].
- Prefix minima array lets us quickly test a valid nums[i] for each position.
- Right-to-left traversal with a monotonic stack efficiently finds feasible nums[k] values.

## Algorithm Steps
1. Build `min[i]` as the minimum of `nums[0..i]`.
2. Initialize an empty stack.
3. For `j` from right to left:
   - If `nums[j] > min[j]`:
     - Pop while `stack.top() <= min[j]`.
     - If stack not empty and `stack.top() < nums[j]`, return true.
     - Push `nums[j]` onto the stack.
4. Return false if no pattern found.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each element is pushed/popped at most once; prefix minima is linear.

## Edge Cases Considered
- [x] Array length < 3
- [x] Strictly increasing array
- [x] Strictly decreasing array
- [x] Duplicates present
- [x] Multiple valid patterns (return true on first)

## Solution Code

```java
import java.util.*;

class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        int n = nums.length;
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        Stack<Integer> st = new Stack<>();
        for (int j = n - 1; j >= 1; j--) {
            if (nums[j] > min[j]) {
                while (!st.isEmpty() && st.peek() <= min[j]) st.pop();
                if (!st.isEmpty() && st.peek() < nums[j]) return true;
                st.push(nums[j]);
            }
        }
        return false;
    }
}
```

## Alternative Approaches
- Brute force O(n³) over all triplets (too slow).
- Two-pass with range queries or balanced BST/segment tree to find a valid `nums[k]` between prefix min and current value.

## Personal Notes
Preprocessing minima and scanning from the right with a monotonic stack is the standard, linear-time pattern detection strategy here.

---
**Tags:** #stacks #arrays #monotonicStack #pattern 