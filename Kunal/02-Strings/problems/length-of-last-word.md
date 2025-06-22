# Length of Last Word

**Date:** 2025-01-19  
**Category:** Kunal Strings  
**Source:** Kunal DSA Course  
**Difficulty:** Easy  
**Topic:** String Processing

## Problem Statement

Given a string `s` consisting of words and spaces, return the length of the last word in the string. A word is a maximal substring consisting of non-space characters only.

## Intuition/Approach

**Reverse Traversal Approach:**
Start from the end of the string and work backwards:
1. Skip trailing spaces (if any)
2. Count characters until we hit a space or reach the beginning
3. Return the count

This approach is efficient because we don't need to process the entire string, just find the last word.

## Algorithm Steps

1. Initialize pointer at the end of string (`length - 1`)
2. Skip trailing whitespace by moving pointer left
3. Count non-space characters moving left until:
   - We hit a space character, OR
   - We reach the beginning of string
4. Return the character count

## Key Observations

- No need to trim or split the entire string
- Handles trailing spaces naturally
- Single pass from the end is sufficient
- Works with multiple spaces between words
- Edge cases: single word, only spaces, empty string

## Time & Space Complexity

- **Time Complexity:** O(m) where m is the length of the last word (not the entire string)
- **Space Complexity:** O(1) - only using a few variables

## Edge Cases Considered

- [ ] String with trailing spaces ("hello world  ")
- [ ] Single word string ("hello")
- [ ] Multiple spaces between words ("a   b")
- [ ] String with only spaces ("   ")
- [ ] Empty string ("")
- [ ] Single character ("a")

## Solution Code

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;
        
        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // Count characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }
}
```

## Alternative Approaches

1. **Split and Take Last:** 
   ```java
   String[] words = s.trim().split("\\s+");
   return words[words.length - 1].length();
   ```

2. **Last Index of Space:**
   ```java
   s = s.trim();
   return s.length() - s.lastIndexOf(' ') - 1;
   ```

3. **Regular Expression:** Find last word using regex patterns

## Optimization Notes

- The reverse traversal approach is most efficient for this problem
- Avoids creating additional strings or arrays
- Optimal for cases where the last word is much shorter than the entire string

## Related Problems

- Reverse Words in a String
- Valid Palindrome
- String compression
- Word break problems

**LeetCode Connection:** LeetCode #58 - Length of Last Word 