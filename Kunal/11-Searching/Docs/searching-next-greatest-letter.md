# Find Smallest Letter Greater Than Target

**Source:** Kunal | **Topic:** Searching | **Difficulty:** Easy  

---

## Problem Statement
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is lexicographically greater than the target letter. Letters also wrap around, meaning if the target is 'z' and the letters are ['a', 'b'], the answer should be 'a'.

## Intuition/Approach
Use binary search to find the smallest character greater than target. The key insight is that when binary search terminates, the left pointer will be at the position of the smallest element greater than target. Handle the wrap-around case using modulo operation.

**Key Insight:** Binary search can find the smallest element greater than a target even when the target doesn't exist in the array. When the search ends, the left pointer naturally points to the correct position, and modulo handles the wrap-around behavior elegantly.

## Key Observations
- Array is sorted, so binary search is applicable
- Need to find the smallest element > target (not ≥)
- Wrap-around behavior: if no element > target, return first element
- Use modulo operation to handle wrap-around elegantly
- When binary search ends, left pointer is at the answer position

## Algorithm Steps
1. **Initialize boundaries:** left = 0, right = letters.length - 1
2. **Binary search loop:** While left <= right
   - If letters[mid] > target, search left half (potential answer)
   - If letters[mid] ≤ target, search right half
3. **Handle wrap-around:** When loop ends, left is at answer position
4. **Return result:** Return letters[left % letters.length]

## Complexity Analysis
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)
- **Justification:** Binary search with constant extra space

## Edge Cases Considered
- [x] Target smaller than all letters - Return first letter
- [x] Target larger than all letters - Return first letter (wrap-around)
- [x] Target equals some letter - Return next greater letter
- [x] Target between two letters - Return the next greater letter
- [x] Single letter array - Return that letter if > target, else same letter
- [x] All letters same - Return that letter

## Solution Code

```java
public static char nextGreatestLetter(char[] letters, char target) {
    int left = 0;
    int right = letters.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (letters[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    // left is now at the position of smallest element > target
    // Use modulo to handle wrap-around
    return letters[left % letters.length];
}
```

## Alternative Approaches
1. **Linear Search:** O(n) - scan array but inefficient for large arrays
2. **Binary Search with Exact Match:** Handle equal case separately, same complexity
3. **Built-in Functions:** Use Arrays.binarySearch() but need to handle result carefully
4. **Two-Pass Approach:** First find if target exists, then find next - more complex

## Personal Notes
This is an elegant use of binary search where we don't need an exact match. The key insight is understanding that when binary search terminates, the left pointer naturally points to the correct position. The modulo operation handles the wrap-around behavior beautifully, making this a clean and efficient solution.

---

**Tags:** #searching #binarysearch #characters #wraparound #modulo #easy 