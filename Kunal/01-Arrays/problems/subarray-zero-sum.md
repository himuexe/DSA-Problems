# Maximum Length Subarray with Zero Sum

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array containing positive and negative integers, find the maximum length of a subarray whose sum is zero.

## Intuition/Approach
Use HashMap to store cumulative sums and their first occurrence indices. If same cumulative sum appears twice, the subarray between those indices has sum zero. Track maximum length found.

## Key Observations
- Cumulative sum approach: if sum[i] == sum[j], then subarray (i,j] has sum 0
- HashMap stores first occurrence of each cumulative sum
- If cumulative sum is 0, entire prefix has sum 0
- Update maximum length whenever zero-sum subarray is found
- Only store first occurrence to maximize subarray length

## Algorithm Steps
1. Initialize sum = 0, len = 0, and HashMap
2. For each element in array:
   - Add element to cumulative sum
   - If sum == 0: update len = i + 1 (entire prefix)
   - If sum exists in map: update len = max(len, i - map.get(sum))
   - If sum not in map: store sum with current index
3. Return maximum length found

## Complexity Analysis
- **Time Complexity:** O(n) - single pass through array
- **Space Complexity:** O(n) - HashMap storage for cumulative sums
- **Justification:** Each element processed once, HashMap operations O(1) average

## Edge Cases Considered
- [x] No zero-sum subarray exists (return 0)
- [x] Entire array sums to zero
- [x] Multiple zero-sum subarrays (return maximum length)
- [x] Array with single zero element
- [x] Array starting with zero-sum prefix
- [x] Empty array (return 0)

## Solution Code

```java
// Language: Java
import java.util.HashMap;

public class SubArray0Sum {
    public static int maxlen(int arr[], int n){
        int sum = 0;
        int len = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            sum += arr[i];
            if(sum == 0){
                len = i + 1;
            }
            else if(map.containsKey(sum)){
                len = Math.max(len, i - map.get(sum));
            }
            else{
                map.put(sum, i);
            }
        }
        return len;
    }
}
```

## Alternative Approaches
- **Brute Force:** Check all subarrays O(n³) or O(n²) with prefix sums
- **Set-based:** Use HashSet to track seen cumulative sums
- **Two-pass:** Separate passes for positive and negative handling

## Related Problems
- **AC:** [Subarray Sum Problems, Maximum Subarray]
- **Kunal:** [Two Sum, Prefix Sum Applications]
- **LeetCode:** [325. Maximum Size Subarray Sum Equals k, 560. Subarray Sum Equals K]

## Personal Notes
Excellent problem demonstrating prefix sum + HashMap technique. The key insight is that identical cumulative sums indicate zero-sum subarrays between those positions. Essential pattern for subarray sum problems.

## Revision History
- **First Solve:** June 26, 2025 - Implemented HashMap-based cumulative sum approach

---
**Tags:** #prefixsum #hashmap #subarray #zerosum #arrays 