# Reverse Words in String

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Reverse the order of words in a string. Words are separated by spaces, and the result should not contain leading/trailing spaces or multiple consecutive spaces.

## Intuition/Approach
Three-step process: 1) Reverse entire string, 2) Reverse each individual word, 3) Clean up extra spaces. This avoids using extra space for word storage.

## Key Observations
- Reversing entire string puts words in reverse order but words themselves are reversed
- Reversing individual words fixes the word content
- Space cleanup handles multiple spaces and leading/trailing spaces
- In-place manipulation using character array

## Algorithm Steps
1. Convert string to character array for in-place modification
2. Reverse entire character array
3. Reverse each individual word in the array
4. Clean up spaces:
   - Skip leading spaces
   - Keep single space between words
   - Remove trailing spaces

## Complexity Analysis
- **Time Complexity:** O(n) where n is string length
- **Space Complexity:** O(1) excluding input/output
- **Justification:** Three linear passes, in-place modification

## Edge Cases Considered
- [x] Single word (no spaces)
- [x] Multiple consecutive spaces
- [x] Leading/trailing spaces
- [x] Empty string
- [x] String with only spaces

## Solution Code

```java
// Language: Java
class Solution {
    public String reverseWords(String s) {
       char[] str = s.toCharArray();
       reverse(str,0,str.length-1);
       reverseWords(str);
       return cleanSpaces(str);
    }
    
    private String cleanSpaces(char[] str){
        int left=0;
        int right=0;
        while(right< str.length){
            while(right < str.length && str[right] == ' ')right++;
            while(right < str.length && str[right] != ' '){
                str[left] =str[right];
                left++;
                right++;
            }
            while(right < str.length && str[right] == ' ')right++;
            if(right < str.length){
                str[left]=' ';
                left++;
            }
        }
        return new String(str).substring(0,left);
    }
    
    private void reverseWords(char[] str){
        int left=0;
        int right=0;
        while(left< str.length){
            while(left< str.length && str[left] == ' ')left++;
            right=left;
            while(right < str.length && str[right] != ' ')right++;
            reverse(str,left,right-1);
            left=right;
           }
    }
    
    private void reverse(char[] str , int left , int right){
        while(left<right){
            char temp = str[left];
            str[left]= str[right];
            str[right]= temp;
            left++;
            right--;
        }
    }
}
```

## Alternative Approaches
- **Split and Join:** Use split() and join() methods (simpler but uses extra space)
- **Stack:** Push words onto stack and pop in reverse order
- **Two Pointers:** Extract words and build result string

## Related Problems
- **AC:** [String reversal problems]
- **Kunal:** [ReverseString.java - basic string reversal]
- **LeetCode:** [Reverse Words in a String - Problem 151]

## Personal Notes
Sophisticated string manipulation with space optimization. The three-step reversal technique is elegant and demonstrates advanced array manipulation. Understanding pointer management and space handling is crucial for this approach.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #wordReversal #inPlaceManipulation #twoPointers #spaceOptimization 