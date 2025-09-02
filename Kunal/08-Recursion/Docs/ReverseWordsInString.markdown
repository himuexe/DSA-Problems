# Reverse Words in String

**Source:** Kunal | **Topic:** Strings, Two Pointers | **Difficulty:** Medium

---

## Problem Statement
Reverse the order of words in a string, where words are separated by spaces. The result should have no leading/trailing spaces or multiple consecutive spaces.

## Intuition/Approach
Use a three-step in-place algorithm:
- Reverse the entire string to put words in reverse order (with words reversed).
- Reverse each individual word to correct their content.
- Clean up extra spaces to ensure single spaces between words and no leading/trailing spaces.

## Key Observations
- Reversing the entire string flips word order but reverses each word.
- Individual word reversal corrects the word content.
- Space cleanup handles multiple spaces and edge cases.
- In-place manipulation minimizes space usage.

## Algorithm Steps
1. Convert the string to a character array for in-place modification.
2. Reverse the entire array.
3. Identify and reverse each word in the array.
4. Clean up spaces:
   - Skip leading spaces.
   - Copy single spaces between words.
   - Remove trailing spaces.
5. Return the resulting string.

## Complexity Analysis
- **Time Complexity:** O(n) - Three linear passes over the string.
- **Space Complexity:** O(1) - In-place modification (excluding input/output).
- **Justification:** Each pass (reverse, word reverse, cleanup) is linear, and only constant extra space is used.

## Edge Cases Considered
- [x] Single word (no spaces).
- [x] Multiple consecutive spaces.
- [x] Leading/trailing spaces.
- [x] Empty string.
- [x] String with only spaces.

## Solution Code
```java
public class Solution {
    public String reverseWords(String s) {
        char[] str = s.toCharArray();
        reverse(str, 0, str.length - 1);
        reverseWords(str);
        return cleanSpaces(str);
    }
    
    private String cleanSpaces(char[] str) {
        int left = 0, right = 0;
        while (right < str.length) {
            while (right < str.length && str[right] == ' ') right++;
            while (right < str.length && str[right] != ' ') {
                str[left] = str[right];
                left++;
                right++;
            }
            while (right < str.length && str[right] == ' ') right++;
            if (right < str.length) {
                str[left] = ' ';
                left++;
            }
        }
        return new String(str).substring(0, left);
    }
    
    private void reverseWords(char[] str) {
        int left = 0, right = 0;
        while (left < str.length) {
            while (left < str.length && str[left] == ' ') left++;
            right = left;
            while (right < str.length && str[right] != ' ') right++;
            reverse(str, left, right - 1);
            left = right;
        }
    }
    
    private void reverse(char[] str, int left, int right) {
        while (left < right) {
            char temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }
    }
}
```

## Alternative Approaches
- **Split and Join:** Split the string into words, reverse the list, and join with single spaces (O(n) space).
- **Stack-Based:** Push words onto a stack and pop in reverse order (O(n) space).
- **Two-Pointer Extraction:** Extract words and build the result string (O(n) space).

## Personal Notes
- The three-step in-place approach is elegant and space-efficient.
- Managing pointers during space cleanup was challenging but rewarding.
- This problem highlights the power of in-place string manipulation techniques.

---
**Tags:** #strings #two_pointers #in_place_manipulation #word_reversal