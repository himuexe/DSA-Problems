# Two Sum II - Input Array Is Sorted

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  

---

## Problem Statement
Given a 1-indexed integer array numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2].

## Intuition/Approach
Use two-pointer technique starting from both ends. Since the array is sorted, we can compare the sum of elements at left and right pointers with the target and move pointers accordingly to find the pair.

## Key Observations
- Array is sorted in ascending order
- Use two pointers from both ends
- If sum > target, move right pointer left
- If sum < target, move left pointer right
- Return 1-indexed positions
- Guaranteed to have exactly one solution

## Algorithm Steps
1. Initialize left = 0, right = n-1
2. Calculate sum = numbers[left] + numbers[right]
3. If sum == target, return [left+1, right+1]
4. If sum > target, decrement right
5. If sum < target, increment left
6. Continue until solution found

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass with two pointers

## Edge Cases Considered
- [x] Two elements - Check if sum equals target
- [x] Target at beginning - Found quickly
- [x] Target at end - Found quickly
- [x] Target in middle - Found by moving pointers
- [x] Duplicate elements - Handle correctly
- [x] Large numbers - Handle overflow

## Solution Code

```java
// Language: Java
public static int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;
    
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        
        if (sum == target) {
            return new int[]{left + 1, right + 1};
        } else if (sum > target) {
            right--;
        } else {
            left++;
        }
    }
    
    return new int[]{-1, -1}; // No solution found
}
```

## Alternative Approaches
- **HashSet:** Use HashSet to track seen elements O(n) space
- **Binary search:** For each element, binary search for complement
- **Brute force:** Check all pairs O(n²) time

## Personal Notes
This is a classic two-pointer problem that demonstrates the power of the technique on sorted arrays. The key insight is that since the array is sorted, we can eliminate many potential pairs by moving pointers based on the sum comparison. This approach is optimal and elegant.

---
**Tags:** #arrays #twopointers #sum #easy #sorted
