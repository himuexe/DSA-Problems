# Boyer-Moore Majority Vote Algorithm

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊n/2⌋ times. You may assume that the array is non-empty and the majority element always exists.

## Intuition/Approach
Use Boyer-Moore majority vote algorithm: maintain a candidate and count. If current element equals candidate, increment count; otherwise decrement count. If count becomes 0, set current element as new candidate.

## Key Observations
- Majority element appears more than n/2 times
- Boyer-Moore algorithm finds candidate in single pass
- If majority exists, candidate will be the majority element
- Count represents relative frequency of candidate vs others
- Algorithm works because majority element appears more than half the time

## Algorithm Steps
1. Initialize candidate = first element, count = 1
2. Iterate through array starting from second element
3. If current element == candidate, increment count
4. If current element != candidate, decrement count
5. If count == 0, set current element as new candidate, count = 1
6. Return candidate

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array, constant extra space

## Edge Cases Considered
- [x] Single element - Return that element
- [x] Two elements (same) - Return that element
- [x] Two elements (different) - Return first element
- [x] All elements same - Return any element
- [x] Majority at beginning - Algorithm finds it
- [x] Majority at end - Algorithm finds it

## Solution Code

```java
// Language: Java
public static int majorityElement(int[] nums) {
    int candidate = nums[0];
    int count = 1;
    
    for (int i = 1; i < nums.length; i++) {
        if (count == 0) {
            candidate = nums[i];
            count = 1;
        } else if (nums[i] == candidate) {
            count++;
        } else {
            count--;
        }
    }
    
    return candidate;
}
```

## Alternative Approaches
- **HashMap:** Count occurrences of each element O(n) space
- **Sorting:** Sort array, middle element is majority O(n log n) time
- **Random sampling:** Randomly sample elements and verify O(n) expected time

## Personal Notes
This is a brilliant algorithm that demonstrates how mathematical insights can lead to elegant solutions. The key insight is that if an element appears more than half the time, it will "survive" the canceling process. This algorithm is optimal in both time and space complexity and is a great example of how to think beyond brute force approaches.

---
**Tags:** #arrays #majority #boyermoore #easy #voting 