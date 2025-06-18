# Arrays - Quick Reference

## Core Operations & Complexities

| Operation | Implementation | Time | Space | Notes |
|-----------|----------------|------|-------|-------|
| Access | `arr[i]` | O(1) | O(1) | Direct index access |
| Linear Search | `for i in arr` | O(n) | O(1) | Check each element |
| Binary Search | `while left <= right` | O(log n) | O(1) | Requires sorted array |
| Insertion | `arr.insert(i, val)` | O(n) | O(1) | Shift elements |
| Deletion | `arr.pop(i)` | O(n) | O(1) | Shift elements |

## Essential Code Templates

### Template 1: Two Pointers
```python
def two_pointers(arr):
    left, right = 0, len(arr) - 1
    result = []
    
    while left < right:
        # Process current pair
        if some_condition:
            result.append([arr[left], arr[right]])
            left += 1
            right -= 1
        elif arr[left] + arr[right] < target:
            left += 1
        else:
            right -= 1
    
    return result
```

### Template 2: Sliding Window
```python
def sliding_window(arr, k):
    window_sum = sum(arr[:k])
    max_sum = window_sum
    
    for i in range(k, len(arr)):
        # Slide window: remove left, add right
        window_sum = window_sum - arr[i-k] + arr[i]
        max_sum = max(max_sum, window_sum)
    
    return max_sum
```

### Template 3: Prefix Sum
```python
def prefix_sum(arr):
    prefix = [0] * (len(arr) + 1)
    
    for i in range(len(arr)):
        prefix[i + 1] = prefix[i] + arr[i]
    
    return prefix
```

## Common Edge Cases
- [ ] Empty array `[]`
- [ ] Single element `[x]`
- [ ] Two elements `[x, y]`
- [ ] All same elements `[x, x, x]`
- [ ] Sorted array `[1, 2, 3, 4]`
- [ ] Reverse sorted `[4, 3, 2, 1]`
- [ ] Large array (10^5+ elements)
- [ ] Duplicate elements `[1, 2, 2, 3]`
- [ ] Negative numbers `[-1, -2, 3]`

## Problem-Solving Checklist
1. [ ] Understand the problem completely
2. [ ] Identify if it's search, manipulation, or counting
3. [ ] Consider edge cases (empty, single element)
4. [ ] Think about brute force first O(n²)
5. [ ] Look for optimization patterns
6. [ ] Consider space-time tradeoffs
7. [ ] Test with examples
8. [ ] Analyze final complexity

## Key Insights & Tips
- **Two Pointers:** Great for sorted arrays, finding pairs, or palindromes
- **Hash Map:** Trade space for time - O(n) space for O(1) lookup
- **Sorting:** Sometimes sorting first makes the problem easier
- **In-place:** Try to modify original array to save space

## Related Patterns
- **Two Pointers:** Pairs, triplets, palindromes, sorted arrays
- **Sliding Window:** Subarrays, substrings, fixed window size
- **Hash Map:** Frequency counting, seen elements, complement finding
- **Sorting:** When order matters or binary search is needed

## Quick Problem Classification

### By Approach
- **Brute Force:** O(n²) - nested loops, check all pairs
- **Optimized:** O(n log n) - sort first, then process
- **Linear:** O(n) - single pass with hash map or two pointers
- **Constant:** O(1) - mathematical formula or direct access

### By Pattern
- **Search Problems:** Binary search, linear search, find element
- **Manipulation Problems:** Rotate, reverse, move elements
- **Counting Problems:** Frequency, duplicates, missing numbers
- **Pairing Problems:** Two sum, three sum, closest pair

## Interview Question Bank
### Must-Know Problems
1. Two Sum - Easy - Hash map pattern
2. Maximum Subarray (Kadane's) - Easy - Dynamic programming
3. Rotate Array - Medium - In-place manipulation
4. 3Sum - Medium - Two pointers with sorting
5. Trapping Rain Water - Hard - Two pointers/stack

### Follow-up Variations
1. What if array is sorted? (Use binary search)
2. What if we need all pairs? (Nested loops or hash map)
3. What if memory is limited? (In-place operations)
4. What if array is very large? (Streaming algorithms) 