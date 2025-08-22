# Cyclic Sort

**Source:** Kunal | **Topic:** Sorting | **Difficulty:** Medium  

---

## Problem Statement
Implement Cyclic Sort algorithm to sort an array containing numbers from 1 to N.

## Intuition/Approach
**Cyclic Sort** is a specialized sorting algorithm designed for arrays containing numbers in a specific range (1 to N). It places each number at its correct index position.

**Key Insight:** Number `n` should be at index `n-1`. For each element, calculate where it should be placed and swap it to the correct position. Only increment the index when an element is correctly placed.

## Key Observations
- **Direct Placement:** Each number is placed directly at its target position
- **No Comparisons:** Unlike comparison-based sorts, uses index calculation
- **Conditional Increment:** Index only increments when element is correctly placed
- **Perfect for Range:** Optimal for consecutive numbers starting from 1

## Algorithm Steps
1. **Initialize:** Start with index `i = 0`
2. **Calculate Position:** For current element `arr[i]`, calculate correct position as `correctPos = arr[i] - 1`
3. **Check Position:** If `arr[i] != arr[correctPos]`, swap elements
4. **Increment Conditionally:** Only increment `i` when element is in correct position
5. **Repeat:** Continue until all elements are in correct positions

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Each element is moved at most once to its correct position, and we use only constant extra space

## Edge Cases Considered
- [x] Array with single element (correct by definition)
- [x] Already sorted array (quick verification)
- [x] Reverse sorted array (maximum swaps needed)
- [x] Array with all elements in wrong positions

## Solution Code

```java
public static void sort(int[] arr) {
    int i = 0;
    while (i < arr.length) {
        int correctPos = arr[i] - 1;
        if (arr[i] != arr[correctPos]) {
            int temp = arr[i];
            arr[i] = arr[correctPos];
            arr[correctPos] = temp;
        } else {
            i++;
        }
    }
}
```

## Alternative Approaches
1. **Comparison-based sorts:** Bubble, Insertion, Selection (O(n²) time)
2. **Efficient sorts:** Merge, Quick, Heap (O(n log n) time)
3. **Counting sort:** O(n) time but O(n) space for general arrays

## Personal Notes
Cyclic sort is a specialized algorithm that achieves O(n) time complexity for arrays containing numbers 1 to N. The key insight is using index mapping instead of comparisons. This algorithm is particularly useful for problems involving finding missing numbers, duplicates, or set mismatches in constrained ranges.

---

**Tags:** #sorting #cyclicsort #arrays #indexmapping #medium 