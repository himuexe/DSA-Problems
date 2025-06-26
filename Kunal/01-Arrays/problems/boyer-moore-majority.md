# Boyer-Moore Majority Element

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Easy  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times. You may assume that the majority element always exists in the array.

## Intuition/Approach
Use Boyer-Moore Voting Algorithm to find majority element in O(1) space. The algorithm works by maintaining a candidate and count, canceling out different elements and keeping track of potential majority elements.

## Key Observations
- Majority element appears more than n/2 times
- Different elements can cancel each other out
- After cancellation, majority element will remain
- Algorithm maintains candidate and count variables
- When count becomes 0, switch to new candidate

## Algorithm Steps
1. Initialize count = 0 and majorityElement = 0
2. Iterate through array:
   - If count == 0, set current element as majorityElement
   - If current element equals majorityElement, increment count
   - Otherwise, decrement count
3. Return majorityElement (guaranteed to be majority)

## Complexity Analysis
- **Time Complexity:** O(n) - single pass through array
- **Space Complexity:** O(1) - only using constant extra space
- **Justification:** Each element visited once, constant space operations

## Edge Cases Considered
- [x] Single element array (element is majority)
- [x] All elements are same (that element is majority)
- [x] Majority element at beginning
- [x] Majority element at end
- [x] Alternating pattern with clear majority

## Solution Code

```java
// Language: Java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int majorityElement = 0;
        for(int i = 0; i < nums.length; i++){
            if(count == 0){
                majorityElement = nums[i];
            }
            if(nums[i] == majorityElement){
                count++;
            }
            else{
                count--;
            }
        }
        return majorityElement;
    }
}
```

## Alternative Approaches
- **HashMap:** Count frequencies and return element with count > n/2
- **Sorting:** Sort array and return middle element
- **Divide and Conquer:** Recursively find majority in subarrays

## Related Problems
- **AC:** [Frequency Count, Array Majority]
- **Kunal:** [Find Duplicates, Single Number]
- **LeetCode:** [169. Majority Element, 229. Majority Element II, 1150. Check If a Number Is Majority Element]

## Personal Notes
Classic algorithm demonstrating elegant solution to majority finding. The voting mechanism efficiently eliminates non-majority elements through cancellation. Essential algorithm for understanding space-efficient majority detection techniques.

## Revision History
- **First Solve:** June 26, 2025 - Implemented Boyer-Moore voting algorithm

---
**Tags:** #voting #majority #arrays #boyer-moore #algorithm 