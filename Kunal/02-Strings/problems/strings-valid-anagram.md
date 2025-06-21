# Valid Anagram

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Determine if two strings are anagrams of each other. An anagram is a word formed by rearranging the letters of another word, using all letters exactly once.

## Intuition/Approach
Use character frequency counting. Two strings are anagrams if they have the same character frequencies. Use array to count frequency differences efficiently.

## Key Observations
- Anagrams must have same length
- Character frequencies must be identical
- Can use single array to track frequency differences
- Increment for first string, decrement for second string
- Final array should be all zeros if anagrams

## Algorithm Steps
1. Check if strings have same length (early return if not)
2. Create frequency array of size 26 (for lowercase letters)
3. Iterate through strings simultaneously:
   - Increment count for character from first string
   - Decrement count for character from second string
4. Check if all counts are zero

## Complexity Analysis
- **Time Complexity:** O(n) where n is string length
- **Space Complexity:** O(1) - fixed size array of 26
- **Justification:** Single pass through strings, constant space

## Edge Cases Considered
- [x] Empty strings (anagrams)
- [x] Single character strings
- [x] Different length strings (not anagrams)
- [x] Same string (anagram of itself)
- [x] Case sensitivity (assumes lowercase)

## Solution Code

```java
// Language: Java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] arr = new int[26];
        for(int i=0;i<s.length();i++){
            arr[s.charAt(i)-'a']++;
            arr[t.charAt(i)-'a']--;
        }
        for (int count: arr){
            if(count!=0){
                return false;
            }
        }
        return true;
    }
}
```

## Alternative Approaches
- **Sorting:** Sort both strings and compare O(n log n)
- **HashMap:** Use HashMap for character counting
- **Prime Numbers:** Assign prime numbers to each character

## Related Problems
- **AC:** [String manipulation problems]
- **Kunal:** [GroupAnagrams.java - groups anagrams together]
- **LeetCode:** [Valid Anagram - Problem 242]

## Personal Notes
Classic frequency counting problem. The single-array approach is elegant and efficient. Understanding ASCII values and character arithmetic is important. The technique extends to more complex string comparison problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #anagram #frequencyCount #characterMapping #easy 