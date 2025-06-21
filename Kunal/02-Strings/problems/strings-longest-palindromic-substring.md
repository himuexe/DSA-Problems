# Longest Palindromic Substring

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Find the longest palindromic substring in a given string. A palindrome reads the same forwards and backwards.

## Intuition/Approach
Use "expand around centers" technique. For each possible center (character or between characters), expand outward while characters match. Track the longest palindrome found.

## Key Observations
- Palindromes have two types of centers: single character (odd length) or between characters (even length)
- Expand from center outward until mismatch or boundaries
- Need to check both odd and even length palindromes for each position
- Use global variables to track longest palindrome boundaries

## Algorithm Steps
1. Initialize global start and end pointers
2. For each position in string:
   - Check for odd-length palindrome centered at current position
   - Check for even-length palindrome centered between current and next position
3. For each center expansion:
   - Expand while characters match and within bounds
   - Update global longest if current palindrome is longer
4. Return substring using global start and end

## Complexity Analysis
- **Time Complexity:** O(n²) where n is string length
- **Space Complexity:** O(1)
- **Justification:** For each center, expand up to n positions, constant extra space

## Edge Cases Considered
- [x] Single character string (palindrome of length 1)
- [x] All same characters
- [x] No palindrome longer than 1
- [x] Entire string is palindrome
- [x] Even vs odd length palindromes

## Solution Code

```java
// Language: Java
class Solution {
    int start=0;int end=0;
    public String longestPalindrome(String s) {
        for(int i=0;i<s.length();i++){
            expandAroundCenter(s,i,i);
            expandAroundCenter(s,i,i+1);
        }
        return s.substring(start,end+1);
    }
    private void expandAroundCenter(String s , int left , int right){
        while(left>=0 && right< s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        left +=1;
        right -= 1;
        if(end-start+1 < right-left+1){
            start=left;
            end=right;
        }
    }
}
```

## Alternative Approaches
- **Dynamic Programming:** O(n²) time, O(n²) space
- **Manacher's Algorithm:** O(n) time, O(n) space (advanced)
- **Brute Force:** O(n³) time, check all substrings

## Related Problems
- **AC:** [Palindrome validation problems]
- **Kunal:** [String manipulation problems]
- **LeetCode:** [Longest Palindromic Substring - Problem 5]

## Personal Notes
Elegant expand-around-center approach. Global variable usage avoids parameter passing overhead. Understanding both odd and even center cases is crucial. The algorithm naturally handles edge cases through boundary checks.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #palindrome #expandAroundCenter #twoPointers #optimization 