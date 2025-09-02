# Binary Strings Without Consecutive 1s

**Source:** AC | **Topic:** Recursion | **Difficulty:** Medium  

---

## Problem Statement
Generate and print all binary strings of length n such that no two consecutive characters are 1s (no "11" substring allowed).

Example:
```
Input: n = 3
Output:
000
001
010
100
101

Total valid strings: 5
Invalid strings: 011, 110, 111 (contain consecutive 1s)
```

## Intuition/Approach
- Use state `lastPlace` to remember the last placed bit.
- Always try appending '0'.
- Append '1' only if lastPlace == 0 to avoid consecutive 1s.
- Stop when length reaches zero; print the built string.

## Key Observations
- Validity of the next bit depends on the previous bit only.
- Early pruning avoids generating invalid branches.
- Count of valid strings follows Fibonacci sequence.
- Backtracking/tree expansion is binary but constrained by state.

## Algorithm Steps
1. If `n == 0`, print `str` and return.
2. Recurse with `'0'`: `printBinaryStrings(n-1, 0, str+"0")`.
3. If `lastPlace == 0`, recurse with `'1'`: `printBinaryStrings(n-1, 1, str+"1")`.

## Complexity Analysis
- **Time Complexity:** O(F(n+2)) ≈ O(φ^n)
- **Space Complexity:** O(n)
- **Justification:** Branching limited by previous bit; recursion depth n.

## Edge Cases Considered
- [x] n = 0 (prints empty string)
- [x] n = 1 (prints 0 and 1)
- [x] n = 2 (prints 00, 01, 10)
- [x] Large n (exponential outputs)
- [x] Start with `lastPlace=0` to allow both choices initially

## Solution Code

```java
public class BinaryStrings {
    public static void printBinaryStrings(int n, int lastPlace, String str) {
        if (n == 0) {
            System.out.println(str);
            return;
        }

        // Always append 0
        printBinaryStrings(n - 1, 0, str + "0");

        // Append 1 only if last was 0
        if (lastPlace == 0) {
            printBinaryStrings(n - 1, 1, str + "1");
        }
    }

    public static void main(String[] args) {
        printBinaryStrings(3, 0, "");
    }
}
```

## Alternative Approaches
- Iterative BFS with a queue to build strings level by level.
- Dynamic programming to count valid strings without generating.
- Backtracking into a list to store results instead of printing.

## Personal Notes
- Number of valid strings equals F(n+2); this is a good interview pattern to recognize.

---
**Tags:** #recursion #backtracking #strings #fibonacci