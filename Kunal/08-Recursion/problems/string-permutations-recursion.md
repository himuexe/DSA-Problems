# String Permutations - Comprehensive Recursive Generation

## Problem Statement
Generate all possible permutations of a given string using recursion. Provide both void method (printing results) and return method (collecting results in ArrayList).

## Intuition/Approach
**Recursive Permutation Generation:**
1. **Base Case:** When unprocessed string is empty, add/print current permutation
2. **Character Insertion:** For each position in current string, insert next character
3. **Recursive Exploration:** Recurse with remaining characters to process
4. **Position Iteration:** Try inserting character at all valid positions (0 to length)

**Key Insight:** Generate permutations by systematically inserting each character at every possible position in the growing permutation.

## Key Observations
- **Character Extraction:** Process one character at a time from unprocessed string
- **Position Insertion:** Insert character at positions 0, 1, 2, ..., current_length
- **String Manipulation:** Use substring operations to split and reconstruct strings
- **Factorial Growth:** n! total permutations for n characters
- **Dual Implementation:** Both void (print) and return (collect) versions

## Algorithm Steps
### Void Method (perm):
1. **Base Case:** If up.isEmpty(), print current permutation p
2. **Character Processing:** Extract first character from up
3. **Position Insertion:** For each position i from 0 to p.length():
   - Split p into prefix (0 to i) and suffix (i to end)
   - Recursively call with p_prefix + ch + p_suffix and remaining characters

### Return Method (permList):
1. **Base Case:** If up.isEmpty(), return list containing current permutation
2. **Character Processing:** Extract first character from up
3. **Result Collection:** For each position insertion:
   - Recursively get all permutations for modified string
   - Add all results to answer list
4. **Return:** Combined list of all permutations

## Time & Space Complexity
- **Time Complexity:** O(n! × n²)
  - n! permutations to generate
  - n positions to try for each character
  - O(n) string operations per recursive call
- **Space Complexity:** O(n! × n)
  - n! permutations stored (return method)
  - Each permutation of length n
  - Recursion stack depth: O(n)

## Edge Cases Considered
- [x] Empty string (single empty permutation)
- [x] Single character (single permutation)
- [x] Duplicate characters (all combinations generated)
- [x] Long strings (factorial explosion)
- [x] String operation edge cases

## Code Implementation
```java
import java.util.ArrayList;

public class Permutations {
    
    // Void method - prints permutations
    public static void perm(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);  // Print complete permutation
            return;
        }
        
        char ch = up.charAt(0);  // Get next character to place
        
        // Try inserting character at each possible position
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);        // Prefix
            String s = p.substring(i, p.length()); // Suffix
            perm(f + ch + s, up.substring(1));    // Recurse with insertion
        }
    }
    
    // Return method - collects permutations in ArrayList
    public static ArrayList<String> permList(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);  // Add complete permutation
            return list;
        }
        
        char ch = up.charAt(0);  // Get next character to place
        ArrayList<String> ans = new ArrayList<>();
        
        // Try inserting character at each possible position
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);        // Prefix
            String s = p.substring(i, p.length()); // Suffix
            ans.addAll(permList(f + ch + s, up.substring(1)));
        }
        
        return ans;
    }
    
    public static void main(String[] args) {
        perm("", "abc");
        System.out.println(permList("", "abc"));
    }
}
```

## Example Walkthrough (Input: "abc")
**Call:** perm("", "abc")

**Detailed Recursive Tree:**
```
perm("", "abc")  [ch='a']
├─ perm("a", "bc")  [ch='b']
│  ├─ perm("ba", "c")  [ch='c']
│  │  ├─ perm("cba", "") → Print "cba"
│  │  ├─ perm("bcba", "") → Print "bcba"  
│  │  └─ perm("bac", "") → Print "bac"
│  ├─ perm("abb", "c")  [ch='c']
│  │  ├─ perm("cab", "") → Print "cab"
│  │  ├─ perm("acb", "") → Print "acb"
│  │  └─ perm("abc", "") → Print "abc"
└─ Continue with other positions...
```

**Final Output:**
```
abc
acb
bac
bca
cab
cba
```

## Character Insertion Process
For "abc" with current p="a" and next character 'b':

**Position 0:** Insert b at start → "ba"
**Position 1:** Insert b at end → "ab"

This generates all possible ways to insert 'b' into existing permutation "a".

## Alternative Implementations
1. **Swap-based:** Use character swapping instead of string concatenation
2. **Iterative:** Build permutations iteratively with queues
3. **Lexicographic:** Generate permutations in lexicographic order
4. **With Duplicates:** Handle repeated characters using sets

## Optimization Opportunities
1. **StringBuilder:** Use StringBuilder for string operations
2. **Character Array:** Avoid string concatenation overhead
3. **Iterative Approach:** Eliminate recursion stack overhead
4. **In-place Generation:** Minimize memory allocation

## Key Learning Points
- **Permutation Generation:** Understanding recursive permutation algorithms
- **String Operations:** Efficient string manipulation techniques
- **Recursive Pattern:** Standard recursive problem-solving template
- **Position-based Insertion:** Systematic character placement strategy

## Applications
- **Combinatorics:** Understanding permutation generation
- **Cryptography:** Password and key generation
- **Game Development:** Shuffling and arrangement algorithms
- **Algorithm Design:** Foundation for more complex permutation problems

---
**Date:** June 28, 2025  
**Topic:** Recursion & Permutation Generation  
**Difficulty:** Intermediate  
**Category:** Classic Recursion 