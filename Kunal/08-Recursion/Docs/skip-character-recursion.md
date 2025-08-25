# Skip Character Recursion - String Filtering Techniques

## Problem Statement
Remove specific characters or substrings from a string using recursive approaches. Implement multiple variations: skip character, return filtered string, and skip substring patterns.

## Intuition/Approach
**Recursive Filtering Strategy:**
1. **Character-by-Character Processing:** Examine each character recursively
2. **Conditional Inclusion:** Include character only if it doesn't match skip criteria
3. **Base Case:** Empty string returns appropriate result
4. **Tail Recursion:** Process remaining string after current character

**Key Insight:** Build result string by making include/exclude decisions recursively.

## Key Observations
- **Linear Recursion:** Processes one character at a time
- **Conditional Logic:** Different actions based on character matching
- **Multiple Variations:** Print-based, return-based, and substring patterns
- **String Immutability:** Creates new strings at each recursive call
- **Pattern Matching:** Can skip individual characters or entire substrings

## Algorithm Steps
1. **Skip Character (Print-based):**
   - If string empty, print current result
   - If current character matches skip criteria, recurse without adding
   - Otherwise, add character to result and recurse

2. **Skip Character (Return-based):**
   - If string empty, return empty string
   - If current character matches, return result of recursive call on remaining
   - Otherwise, return current character + recursive result

3. **Skip Substring Pattern:**
   - Check if string starts with target substring
   - If yes, skip entire substring and recurse
   - Otherwise, include current character and recurse

## Time & Space Complexity
- **Time Complexity:** O(n)
  - Visit each character once: O(n)
  - String operations: O(n) total across all calls
- **Space Complexity:** O(n)
  - Recursion stack depth: O(n)
  - String creation: O(n) for result
  - Substring operations: O(n) total

## Edge Cases Considered
- [x] Empty string input
- [x] String with all characters to be skipped
- [x] String with no characters to be skipped
- [x] Single character string
- [x] Substring larger than remaining string
- [x] Multiple occurrences of skip pattern

## Code Implementation
```java
// Print-based character skipping
public static void skip(String p, String up) {
    if (up.isEmpty()) {
        System.out.println(p);  // Print filtered result
        return;
    }
    char ch = up.charAt(0);
    if (ch == 'a') {
        skip(p, up.substring(1));          // Skip 'a'
    } else {
        skip(p + ch, up.substring(1));     // Include character
    }
}

// Return-based character skipping
public static String skipChar(String up) {
    if (up.isEmpty()) {
        return "";  // Base case
    }
    char ch = up.charAt(0);
    if (ch == 'a') {
        return skipChar(up.substring(1));         // Skip 'a'
    } else {
        return ch + skipChar(up.substring(1));    // Include + recurse
    }
}

// Substring pattern skipping
public static String skipApple(String up) {
    if (up.isEmpty()) {
        return "";
    }
    if (up.startsWith("apple")) {
        return skipApple(up.substring(5));        // Skip "apple"
    } else {
        return up.charAt(0) + skipApple(up.substring(1));  // Include + recurse
    }
}
```

## Example Walkthrough
**Input:** "bbacadd" (skip 'a')
**Process:**
- 'b' → include → "b" + skip("bacadd")
- 'b' → include → "bb" + skip("acadd")  
- 'a' → skip → "bb" + skip("cadd")
- 'c' → include → "bbc" + skip("add")
- 'a' → skip → "bbc" + skip("dd")
- 'd' → include → "bbcd" + skip("d")
- 'd' → include → "bbcdd"

**Output:** "bbcdd"

## Key Learning Points
- **Tail Recursion:** Efficient recursive pattern for string processing
- **Conditional Recursion:** Different recursive paths based on conditions
- **String Building:** Constructing result strings through recursion
- **Pattern Recognition:** Extending from characters to substring patterns

## Applications
- **Text Processing:** Remove unwanted characters or patterns
- **String Sanitization:** Clean input data recursively
- **Compiler Design:** Lexical analysis and token filtering
- **Data Cleaning:** Recursive data preprocessing

---
**Date:** June 27, 2025  
**Topic:** String Processing & Recursion  
**Difficulty:** Beginner-Intermediate  
**Category:** Recursive String Manipulation 