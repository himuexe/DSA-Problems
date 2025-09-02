# Longest Palindromic Substring

**Source:** Kunal | **Topic:** Strings, Two Pointers | **Difficulty:** Medium

---

## Problem Statement
Find the longest palindromic substring in a given string. A palindrome reads the same forwards and backwards.

## Intuition/Approach
Use the "expand around centers" technique:
- For each position, consider it as the center of an odd-length palindrome or the left center of an even-length palindrome.
- Expand outward while characters match and stay within bounds.
- Track the longest palindrome found using global start and end pointers.

## Key Observations
- Palindromes can be odd-length (centered at a character) or even-length (centered between characters).
- Expansion stops at mismatches or string boundaries.
- Global variables simplify tracking the longest palindrome.
- The approach naturally handles all palindrome types efficiently.

## Algorithm Steps
1. Initialize global start and end pointers for the longest palindrome.
2. For each index i in the string:
   - Expand around i for odd-length palindromes (left = i, right = i).
   - Expand around i and i+1 for even-length palindromes (left = i, right = i+1).
3. During expansion:
   - While within bounds and characters match, move left and right pointers outward.
   - Update start and end if the current palindrome is longer.
4. Return the substring defined by start and end.

## Complexity Analysis
- **Time Complexity:** O(n²) - Each of n centers may expand up to n/2 positions.
- **Space Complexity:** O(1) - Only uses constant extra space (excluding output).
- **Justification:** Linear centers with linear expansion give quadratic time; global pointers avoid extra space.

## Edge Cases Considered
- [x] Single character string (palindrome of length 1).
- [x] All characters identical (entire string is a palindrome).
- [x] No palindrome longer than 1.
- [x] Even-length vs. odd-length palindromes.
- [x] Empty string (return empty string).

## Solution Code
```java
public class Solution {
    int start = 0, end = 0;
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);     // Odd-length palindromes
            expandAroundCenter(s, i, i + 1); // Even-length palindromes
        }
        return s.substring(start, end + 1);
    }
    
    private void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        left += 1;
        right -= 1;
        if (end - start + 1 < right - left + 1) {
            start = left;
            end = right;
        }
    }
}
```

## Alternative Approaches
- **Dynamic Programming:** Use a 2D table to track palindromic substrings (O(n²) space).
- **Manacher’s Algorithm:** Achieve O(n) time complexity for advanced use cases.
- **Brute Force:** Check all substrings (O(n³) time, impractical).

## Personal Notes
- The expand-around-center approach is elegant and intuitive compared to DP.
- Managing global start/end pointers reduces the need for complex parameter passing.
- Understanding the dual-center cases (odd and even) was key to solving this efficiently.

---
**Tags:** #strings #palindrome #two_pointers #expand_around_center