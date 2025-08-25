# Group Anagrams

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Medium  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Group anagrams together from an array of strings. Anagrams are words formed by rearranging the letters of another word using all letters exactly once.

## Intuition/Approach
Use HashMap where key is the sorted version of each word and value is list of words that are anagrams. Words with same sorted representation are anagrams of each other.

## Key Observations
- Anagrams have identical character frequencies
- Sorting characters creates canonical representation
- HashMap groups words with same canonical form
- All anagrams map to same sorted key

## Algorithm Steps
1. Create HashMap to store sorted_string → list_of_anagrams
2. For each word in array:
   - Convert word to character array and sort it
   - Use sorted string as key
   - Add original word to the corresponding list
3. Return all values from HashMap

## Complexity Analysis
- **Time Complexity:** O(N * K log K) where N is number of words, K is max word length
- **Space Complexity:** O(N * K) for storing all strings
- **Justification:** Sorting each word takes O(K log K), HashMap operations are O(1)

## Edge Cases Considered
- [x] Empty array
- [x] Single word array
- [x] All words are anagrams
- [x] No anagrams (each word unique)
- [x] Empty strings in array

## Solution Code

```java
// Language: Java
import java.util.*;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String word : strs){
            char[] ca = word.toCharArray();
            Arrays.sort(ca);
            String str = new String(ca);
            if(!map.containsKey(str)){
                map.put(str,new ArrayList<>());
            }
            map.get(str).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
```

## Alternative Approaches
- **Frequency Count:** Use character frequency as key instead of sorting
- **Prime Number Mapping:** Assign prime numbers to each character
- **Counting Sort:** Use counting sort for O(K) sorting per word

## Related Problems
- **AC:** [String grouping problems]
- **Kunal:** [ValidAnagram.java - basic anagram validation]
- **LeetCode:** [Group Anagrams - Problem 49]

## Personal Notes
Classic HashMap with custom key generation. The sorted string as key is elegant and intuitive. Understanding how to create canonical representations is crucial for grouping problems. Time complexity depends on sorting algorithm used.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #anagram #hashMap #grouping #canonicalForm 