# Valid Anagram

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Determine if two strings are anagrams (use the same letters with the same multiplicities).

## Intuition/Approach
Count character frequencies: increment for `s`, decrement for `t`; all counts must end at zero.

## Key Observations
- Length mismatch ⇒ not anagrams
- A single fixed-size array works for lowercase a–z
- Net-zero counts across all characters implies anagram

## Algorithm Steps
1. If lengths differ, return false
2. Create int[26] counts
3. For i in [0..n): ++counts[s[i]-'a']; --counts[t[i]-'a']
4. Verify all counts are zero

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass; fixed array size

## Edge Cases Considered
- [x] Empty strings
- [x] Single character
- [x] Same string
- [x] Different lengths

## Solution Code

```java
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
- Sort both strings and compare
- HashMap for general alphabets/case sensitivity

## Personal Notes
- Reusable frequency-array trick for many string comparison tasks

---
**Tags:** #strings #anagram #frequency