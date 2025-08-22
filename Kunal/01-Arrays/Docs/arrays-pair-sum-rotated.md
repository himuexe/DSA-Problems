# Pair Sum in Rotated Sorted Array

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Given a rotated sorted array, find if there exists a pair with a given sum. The array is sorted in ascending order but has been rotated at some pivot point.

## Intuition/Approach
Use two-pointer technique with modifications for rotated array. Find the pivot point first, then use two pointers to search for the pair, handling the rotation by using modulo arithmetic.

## Key Observations
- Array is sorted but rotated
- Need to find pivot point
- Use two pointers with modulo arithmetic
- Handle rotation in pointer movement
- Search in both sorted portions

## Algorithm Steps
1. Find pivot point (smallest element)
2. Initialize left = pivot, right = pivot - 1
3. Use modulo arithmetic for circular array
4. If sum == target, return true
5. If sum > target, move right pointer left
6. If sum < target, move left pointer right

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Find pivot + two pointer search

## Edge Cases Considered
- [x] Empty array - Return false
- [x] Single element - Return false
- [x] Two elements - Check if sum equals target
- [x] No rotation - Standard two pointer approach
- [x] Full rotation - Back to original order
- [x] No pair found - Return false

## Solution Code

```java
// Language: Java
public static boolean findPair(int[] arr, int target) {
    int n = arr.length;
    if (n < 2) return false;
    
    // Find pivot (smallest element)
    int pivot = 0;
    for (int i = 1; i < n; i++) {
        if (arr[i] < arr[i-1]) {
            pivot = i;
            break;
        }
    }
    
    int left = pivot;
    int right = (pivot - 1 + n) % n;
    
    while (left != right) {
        int sum = arr[left] + arr[right];
        
        if (sum == target) {
            return true;
        } else if (sum > target) {
            right = (right - 1 + n) % n;
        } else {
            left = (left + 1) % n;
        }
    }
    
    return false;
}
```

## Alternative Approaches
- **HashSet:** Use HashSet to track seen elements
- **Linear search:** Check all pairs O(n²)
- **Sort first:** Sort array then use two pointers

## Personal Notes
This is an interesting problem that combines pivot finding with two-pointer technique. The key insight is using modulo arithmetic to handle the circular nature of the rotated array. This approach maintains the efficiency of two pointers while handling the rotation correctly.

---
**Tags:** #arrays #twopointers #rotation #sum #medium
