# Sliding Window Maximum

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Hard  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of integers and a sliding window of size k, return the maximum element in each window as the window slides from left to right through the array.

## Intuition/Approach
Use a deque (double-ended queue) to maintain indices of array elements in decreasing order. The deque front always contains the index of the maximum element in the current window.

## Key Observations
- Need to efficiently find maximum in each sliding window
- Deque maintains indices in decreasing order of their values
- Remove indices that are out of current window from front
- Remove indices with smaller values from rear before adding new index
- Front of deque always contains index of maximum element

## Algorithm Steps
1. Initialize deque and result array
2. For each element in the array:
   - Remove indices outside current window from deque front
   - Remove indices with smaller values from deque rear
   - Add current index to deque rear
   - If window is complete, add maximum (front element) to result
3. Return result array

## Complexity Analysis
- **Time Complexity:** O(n) - each element is added and removed from deque at most once
- **Space Complexity:** O(k) - deque stores at most k elements
- **Justification:** Amortized O(1) per element due to deque operations

## Edge Cases Considered
- [x] Single element array (k=1)
- [x] Window size equals array length
- [x] All elements are same
- [x] Strictly increasing array
- [x] Strictly decreasing array
- [x] Array with duplicates

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            while(deque.size() > 0 && deque.peekFirst() < i - k + 1){
                deque.pollFirst();
            }
            while(deque.size() > 0 && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i - k + 1 >= 0){
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
```

## Alternative Approaches
- **Brute Force:** Check maximum in each window O(n*k)
- **Segment Tree:** Build segment tree for range maximum queries O(n log n)
- **Sparse Table:** Precompute range maximums O(n log n) preprocessing

## Related Problems
- **AC:** [Maximum Subarray, Range Queries, Window Problems]
- **Kunal:** [Monotonic Stack, Deque Applications]
- **LeetCode:** [239. Sliding Window Maximum, 862. Shortest Subarray with Sum at Least K]

## Personal Notes
Advanced problem demonstrating monotonic deque application. The key insight is maintaining indices in decreasing order to efficiently track maximum elements. Essential pattern for sliding window optimization problems.

## Revision History
- **First Solve:** June 26, 2025 - Implemented monotonic deque approach

---
**Tags:** #deque #slidingwindow #monotonic #arrays #maximum 