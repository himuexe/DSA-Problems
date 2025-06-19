# Split Array Largest Sum (Binary Search)

**Source:** Kunal | **Topic:** 11-Searching | **Difficulty:** Hard  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized. Return the minimized largest sum.

## Intuition/Approach
Use binary search on the answer space. The search range is from the maximum element (minimum possible largest sum) to the sum of all elements (maximum possible largest sum). For each mid value, check if it's possible to split into k subarrays.

## Key Observations
- Search space: [max_element, sum_of_all_elements]
- For each candidate sum, simulate the splitting process
- If we can split with fewer than k pieces, the sum is too large
- If we need more than k pieces, the sum is too small
- Binary search minimizes the maximum sum

## Algorithm Steps
1. Set start = max element, end = sum of all elements
2. While start < end:
   - Calculate mid = start + (end - start) / 2
   - Simulate splitting with max sum = mid
   - Count how many pieces needed
   - If pieces > k: start = mid + 1 (need larger sum)
   - Else: end = mid (try smaller sum)
3. Return the final answer

## Complexity Analysis
- **Time Complexity:** O(n × log(sum - max))
- **Space Complexity:** O(1)
- **Justification:** Binary search iterations × validation pass through array

## Edge Cases Considered
- [x] k = 1 (entire array as one subarray)
- [x] k = nums.length (each element as separate subarray)
- [x] Array with single element
- [x] Array with identical elements
- [x] Large sum values

## Solution Code

```java
// Language: Java
public static int splitArray(int[] nums, int k) {
    int start = 0;
    int end = 0;
    for(int i = 0; i < nums.length; i++){
        start = Math.max(start, nums[i]);
        end += nums[i];
    }
    while(start < end){
        int mid = start + (end - start) / 2;
        int sum = 0;
        int pieces = 1;
        for(int num : nums){
            if(sum + num > mid){
                sum = num;
                pieces++;
            }
            else{
                sum += num;
            }
        }
        if(pieces > k){
            start = mid + 1;
        }
        else{
            end = mid;
        }
    }
    return end;
}
```

## Alternative Approaches
- **Dynamic Programming:** O(n²k) solution using DP table
- **Greedy with Validation:** Try all possible sums with greedy splitting
- **Prefix Sums:** Optimize the validation process

## Related Problems
- **Kunal:** [Search in Rotated Array - binary search patterns]
- **LeetCode:** [Split Array Largest Sum - Problem 410]
- **LeetCode:** [Capacity To Ship Packages - Problem 1011]

## Personal Notes
Advanced binary search on answer space technique. The key insight is recognizing that we can binary search on the result rather than the input. This pattern appears in many optimization problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 11-Searching implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #searching #binarySearch #answerSpace #optimization #hard 