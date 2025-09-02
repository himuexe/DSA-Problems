# String Permutations - Comprehensive Recursive Generation

**Source:** Kunal | **Topic:** Recursion, Permutation Generation | **Difficulty:** Intermediate

---

## Problem Statement
Generate all possible permutations of a given string using recursion. Provide both a void method (printing results) and a return method (collecting results in an ArrayList).

## Intuition/Approach
Use recursion to generate permutations by inserting each character at all possible positions:
- **Base Case:** When the unprocessed string is empty, add/print the permutation.
- **Character Insertion:** For each character, try inserting it at every position in the current permutation.
- **Recursion:** Process the remaining characters recursively.
- The approach systematically builds all n! permutations.

## Key Observations
- Each character is processed one at a time, reducing the unprocessed string.
- Insertions at all positions (0 to length) generate all possible permutations.
- String operations (substring, concatenation) are used for manipulation.
- The number of permutations grows factorially (n!).
- Dual implementations (print and return) cater to different use cases.

## Algorithm Steps
1. **Void Method (Print):**
   - If unprocessed string is empty, print the current permutation.
   - Extract the first character from the unprocessed string.
   - For each position in the current permutation (0 to length):
     - Insert the character at that position.
     - Recurse with the new permutation and remaining characters.
2. **Return Method (ArrayList):**
   - If unprocessed string is empty, return a list with the current permutation.
   - Extract the first character and iterate over insertion positions.
   - Collect all permutations from recursive calls in a list.
   - Return the combined list of permutations.

## Complexity Analysis
- **Time Complexity:** O(n! × n²) - n! permutations, each requiring O(n) insertions and O(n) string operations.
- **Space Complexity:** O(n! × n) - For storing n! permutations of length n, plus O(n) recursion stack.
- **Justification:** The factorial number of permutations dominates, with string operations adding linear overhead per permutation.

## Edge Cases Considered
- [x] Empty string (returns single empty permutation).
- [x] Single character (single permutation).
- [x] Duplicate characters (all permutations generated).
- [x] Long strings (factorial explosion handled).
- [x] String with special characters (handled as regular characters).

## Solution Code
```java
import java.util.ArrayList;

public class Permutations {
    public static void perm(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            perm(f + ch + s, up.substring(1));
        }
    }

    public static ArrayList<String> permList(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            ans.addAll(permList(f + ch + s, up.substring(1)));
        }
        return ans;
    }
}
```

## Alternative Approaches
- **Swap-Based Permutations:** Use character swapping to generate permutations in-place.
- **Iterative Permutations:** Use a queue or heap to generate permutations iteratively.
- **Lexicographic Order:** Generate permutations in sorted order for specific use cases.
- **Handle Duplicates:** Use a set to avoid duplicate permutations if needed.

## Personal Notes
- The insertion-based approach is intuitive for understanding permutation generation.
- String immutability in Java makes this less efficient; StringBuilder could optimize it.
- The dual implementation (print and return) clarifies the difference between side-effect and functional recursion.

---
**Tags:** #recursion #permutations #string_processing