# Subsequences Generation - Recursive String Processing

## Problem Statement
Generate all possible subsequences (subsets) of a given string using recursion. Implement both print-based and return-based approaches.

## Intuition/Approach
**Recursive Choice Pattern:**
1. **For each character:** Two choices - include it or exclude it
2. **Base Case:** When no more characters left, print/return current subsequence
3. **Recursive Calls:** Make two calls for include/exclude decisions

**Key Insight:** Each character has 2 choices, leading to 2^n total subsequences.

## Key Observations
- **Binary Decision Tree:** Each character creates a branch (include/exclude)
- **Complete Enumeration:** Generates all 2^n possible subsequences
- **Two Implementation Styles:** Direct printing vs list collection
- **String Manipulation:** Uses substring operations for recursion
- **Exponential Growth:** Output size grows exponentially with input length

## Algorithm Steps
1. **Print-Based Approach (`sub`):**
   - Base case: If unprocessed string empty, print current subsequence
   - Get first character of unprocessed string
   - Make recursive call including current character
   - Make recursive call excluding current character

2. **Return-Based Approach (`subRet`):**
   - Base case: Return list containing current subsequence
   - Get recursive results from both include/exclude calls
   - Merge both result lists and return combined list

## Time & Space Complexity
- **Time Complexity:** O(2^n)
  - Each character has 2 choices: include or exclude
  - Total subsequences: 2^n
  - Each subsequence takes O(n) time to process
- **Space Complexity:** O(2^n × n)
  - Storing all subsequences: O(2^n)
  - Each subsequence length: O(n)
  - Recursion stack depth: O(n)

## Edge Cases Considered
- [x] Empty string input
- [x] Single character string
- [x] String with duplicate characters
- [x] Long strings (exponential output)
- [x] Special characters in string

## Code Implementation
```java
// Print-based subsequence generation
static void sub(String p, String up) {
    if (up.isEmpty()) {
        System.out.println(p);  // Print current subsequence
        return;
    }
    char ch = up.charAt(0);
    sub(p + ch, up.substring(1));    // Include current character
    sub(p, up.substring(1));         // Exclude current character
}

// Return-based subsequence generation
static ArrayList<String> subRet(String p, String up) {
    if (up.isEmpty()) {
        ArrayList<String> list = new ArrayList<>();
        list.add(p);  // Add current subsequence to list
        return list;
    }
    char ch = up.charAt(0);
    ArrayList<String> left = subRet(p + ch, up.substring(1));   // Include
    ArrayList<String> right = subRet(p, up.substring(1));       // Exclude
    left.addAll(right);  // Combine both results
    return left;
}
```

## Example Walkthrough
**Input:** "abc"
**Output:** "", "c", "b", "bc", "a", "ac", "ab", "abc"

**Recursion Tree:**
```
         sub("", "abc")
        /              \
   sub("a", "bc")    sub("", "bc")
   /         \         /         \
sub("ab","c") sub("a","c") sub("b","c") sub("","c")
```

## Key Learning Points
- **Recursive Patterns:** Include/exclude decision making
- **Exponential Algorithms:** Understanding 2^n complexity
- **String Recursion:** Effective use of substring operations
- **Two Return Styles:** Print vs collect patterns in recursion

## Applications
- **Combinatorics:** Generating all possible combinations
- **Backtracking:** Foundation for subset-based problems
- **Dynamic Programming:** Understanding state space explosion
- **Algorithm Design:** Recursive enumeration techniques

---
**Date:** June 27, 2025  
**Topic:** String Processing & Recursion  
**Difficulty:** Intermediate  
**Category:** Recursive Enumeration 