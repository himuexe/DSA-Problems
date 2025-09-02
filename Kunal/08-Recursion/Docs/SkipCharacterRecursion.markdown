# Skip Character Recursion - String Filtering Techniques

**Source:** Kunal Recursion | **Topic:** String Processing, Recursion | **Difficulty:** Beginner-Intermediate

---

## Problem Statement
Remove specific characters or substrings from a string using recursive approaches, including skip character (print-based and return-based) and skip substring patterns.

## Intuition/Approach
The approach processes the string character by character:
- **Recursive Traversal:** Examine each character or substring recursively.
- **Conditional Inclusion:** Include characters/substrings that don’t match the skip criteria.
- **Base Case:** Return or print when the string is empty.
- Variations include printing results, returning a new string, or skipping entire substrings.

## Key Observations
- Linear recursion processes one character at a time.
- String immutability in Java requires creating new strings at each step.
- Substring skipping requires checking prefixes of the remaining string.
- The approach is flexible for different filtering criteria (single character or substring).
- Tail recursion simplifies the recursive structure.

## Algorithm Steps
1. **Skip Character (Print-based):**
   - If string is empty, print the result.
   - If current character matches skip criteria, recurse without adding it.
   - Else, add character to result and recurse.
2. **Skip Character (Return-based):**
   - If string is empty, return "".
   - If current character matches, recurse on the rest.
   - Else, return current character + recursive result.
3. **Skip Substring:**
   - If string is empty, return "".
   - If string starts with the target substring, skip it and recurse.
   - Else, include current character and recurse.

## Complexity Analysis
- **Time Complexity:** O(n) - Each character is processed once.
- **Space Complexity:** O(n) - Recursion stack depth and new strings created.
- **Justification:** Linear traversal and string operations dominate time, while recursion and string building dominate space.

## Edge Cases Considered
- [x] Empty input.
- [x] String with all characters to skip.
- [x] String with no characters to skip.
- [x] Single character string.
- [x] Substring larger than remaining string.
- [x] Multiple occurrences of the skip pattern.

## Solution Code
```java
public class SkipCharacterRecursion {
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
}
```

## Alternative Approaches
- **Iterative String Processing:** Use loops for better performance and less stack usage.
- **Regular Expressions:** Use regex replaceAll() for simple pattern removal.
- **StringBuilder:** Reduce string creation overhead in recursive calls.

## Personal Notes
- The substring skipping variation (e.g., "apple") was a good exercise in pattern matching.
- Java’s string immutability makes recursive string building less efficient but clearer to understand.
- This problem reinforces the importance of clear base cases in recursive string problems.

---
**Tags:** #string_processing #recursion #filtering