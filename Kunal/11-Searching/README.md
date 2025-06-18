# Searching - Kunal (Kunal Kushwaha)

**Progress:** 3/15 Problems Completed | **Last Updated:** 2024-12-19

## Topic Overview
Searching algorithms are fundamental techniques for finding specific elements in data structures. This section covers searching problems from Kunal Kushwaha's DSA course, focusing on binary search variations and optimization techniques.

## Key Concepts to Master
- [x] Binary Search fundamentals
- [x] Binary Search variations (first/last position)
- [x] Floor and Ceiling algorithms
- [x] Wrap-around logic in searching
- [ ] Search in rotated arrays
- [ ] Search in matrix
- [ ] Peak finding algorithms
- [ ] Ternary search

## Problems Solved

### Easy (1/8)
- [x] [Find Smallest Letter Greater Than Target](problems/searching-next-greatest-letter.md) - Status: Solved
- [ ] [Search Insert Position](problems/searching-insert-position.md) - Status: Pending
- [ ] [Valid Perfect Square](problems/searching-perfect-square.md) - Status: Pending  
- [ ] [Arranging Coins](problems/searching-arranging-coins.md) - Status: Pending
- [ ] [Search in Infinite Array](problems/searching-infinite-array.md) - Status: Pending
- [ ] [Intersection of Arrays](problems/searching-intersection.md) - Status: Pending
- [ ] [Missing Number](problems/searching-missing-number.md) - Status: Pending
- [ ] [Peak Index in Mountain](problems/searching-peak-mountain.md) - Status: Pending

### Medium (2/5)
- [x] [Find First and Last Position of Element](problems/searching-first-and-last-position.md) - Status: Solved
- [x] [Floor and Ceiling of Target](problems/searching-floor-ceiling.md) - Status: Solved
- [ ] [Search in Rotated Array](problems/searching-rotated-array.md) - Status: Pending
- [ ] [Find Peak Element](problems/searching-find-peak.md) - Status: Pending
- [ ] [Search in 2D Matrix](problems/searching-2d-matrix.md) - Status: Pending

### Hard (0/2)
- [ ] [Median of Two Sorted Arrays](problems/searching-median-arrays.md) - Status: Pending
- [ ] [Split Array Largest Sum](problems/searching-split-array.md) - Status: Pending

## Revision Schedule

### Due Today
*No problems due for revision*

### Due Tomorrow (3 problems)
- [Find First and Last Position of Element] - Due: 2024-12-20
- [Floor and Ceiling of Target] - Due: 2024-12-20
- [Find Smallest Letter Greater Than Target] - Due: 2024-12-20

### Due Next Week
*No problems due for revision*

## Common Patterns & Techniques
1. **Standard Binary Search:** Basic template for sorted arrays ✅ *Foundation for all problems*
2. **Modified Binary Search:** Continue searching after finding target ✅ *Applied in First/Last Position*
3. **Boundary Conditions:** Handle edge cases in search space ✅ *Applied in Floor/Ceiling*
4. **Pointer Positioning:** Use search termination positions ✅ *Applied in Floor/Ceiling*
5. **Wrap-around Logic:** Handle circular array behavior ✅ *Applied in Next Greatest Letter*
6. **Modulo Operations:** Elegant wrap-around implementation ✅ *Applied in Next Greatest Letter*
7. **Two-Pass Binary Search:** Separate searches for different objectives ✅ *Applied in First/Last Position*

## Time Complexity Cheatsheet
| Operation | Best Case | Average Case | Worst Case |
|-----------|-----------|--------------|------------|
| Binary Search | O(1) | O(log n) | O(log n) |
| First/Last Position | O(log n) | O(log n) | O(log n) |
| Floor/Ceiling | O(log n) | O(log n) | O(log n) |
| Character Search | O(log n) | O(log n) | O(log n) |

## Space Complexity Notes
- All binary search variations: O(1) extra space
- Iterative implementations: O(1) space
- Recursive implementations: O(log n) space for call stack

## Algorithm Templates

### Standard Binary Search Template
```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### Find First/Last Occurrence Template
```java
int searchBoundary(int[] arr, int target, boolean findFirst) {
    int left = 0, right = arr.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            result = mid;
            if (findFirst) right = mid - 1;
            else left = mid + 1;
        } else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return result;
}
```

## Related Topics
- **Prerequisites:** Basic arrays, sorting concepts
- **Next Topics:** Tree traversals, Graph searching
- **Related:** Divide and conquer, Two pointers

## External Resources
- **Kunal Videos:** [Kunal Kushwaha Binary Search Playlist]
- **Additional:** LeetCode Binary Search problems, GeeksforGeeks Search articles

## Progress Log
- **2024-12-19:** Set up Kunal searching topic structure
- **2024-12-19:** Added Find First and Last Position problem documentation (advanced binary search)
- **2024-12-19:** Added Floor and Ceiling problem documentation (boundary search techniques)
- **2024-12-19:** Added Next Greatest Letter problem documentation (wrap-around binary search) 