# Pair Sum in Rotated Sorted Array

**Source:** AC | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
Find a pair of elements in a rotated sorted array that sum to a given target value. The array was originally sorted but has been rotated at some pivot point.

## Intuition/Approach
First find the pivot point (largest element), then use two pointers technique with circular array traversal. One pointer starts after pivot (smallest), other at pivot (largest).

## Key Observations
- Array maintains sorted property in circular manner
- Pivot point divides array into two sorted parts
- Two pointers can work circularly using modulo arithmetic
- Left pointer moves clockwise, right pointer moves counter-clockwise

## Algorithm Steps
1. Find pivot point (where arr[i] > arr[i+1])
2. Initialize left pointer at pivot+1 (smallest element)
3. Initialize right pointer at pivot (largest element)
4. Use two pointers with circular movement:
   - If sum equals target, return pair
   - If sum less than target, move left pointer clockwise
   - If sum greater than target, move right pointer counter-clockwise

## Complexity Analysis
- **Time Complexity:** O(n) where n is array length
- **Space Complexity:** O(1)
- **Justification:** Single pass to find pivot, single pass for two pointers

## Edge Cases Considered
- [x] No rotation (pivot at end)
- [x] Single element array
- [x] No valid pair exists
- [x] Multiple valid pairs (returns first found)
- [x] Target sum with array boundaries

## Solution Code
```java
// Language: Java
public static int[] twoSum(ArrayList<Integer> list, int target){
    int breakingPoint = pivot(list);
    int left =breakingPoint+1;
    int right =breakingPoint;
    while(left != right){
        if(list.get(left)+list.get(right)==target){
            return new int[]{list.get(right),list.get(left)};
        }
        else if(list.get(left)+list.get(right)<target){
            left = (left+1)%list.size();
        }
        else{
            right = (list.size()+right-1)%list.size();
        }
    }
    return new int[]{-1,-1};
}

public static int pivot(ArrayList<Integer> list){
    for(int i=0;i<list.size();i++){
        if(list.get(i)>list.get(i+1)){
            return i;
        }
    }
    return -1;
}
```

## Alternative Approaches
- Binary Search Pivot: Find pivot using binary search O(log n)
- HashMap Approach: Store complements in hash map
- Sort and Two Pointers: Sort array first (destroys rotation info)

## Personal Notes
Combines pivot finding with two pointers technique. Modulo arithmetic ensures circular traversal. Understanding rotated arrays is crucial for many advanced problems. The approach maintains O(n) complexity while handling rotation.

---
**Tags:** #arrays #twoPointers #rotatedArray #pivotFinding #circularTraversal 