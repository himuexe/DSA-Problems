# Longest Substring Without Repeating Characters - Kunal Strings

## Problem Statement
**Difficulty:** Medium  
**Topic:** Sliding Window, HashMap, String Processing  
**Source:** Kunal Kushwaha DSA Course

Find the length of the longest substring without repeating characters.

**Example:**
- Input: `"abcabcbb"`
- Output: `3` (substring "abc")

## Intuition/Approach
**Algorithm: Sliding Window with HashMap**
1. **Maintain sliding window** - Use left and right pointers
2. **Track character positions** - HashMap stores last seen index of each character
3. **Expand window** - Move right pointer and check for duplicates
4. **Contract window** - Move left pointer when duplicate found
5. **Update maximum** - Keep track of maximum window size

**Key Insight:** Use HashMap to efficiently detect duplicates and adjust window boundaries.

## Key Observations
- **Sliding window technique** - Optimal for substring problems
- **HashMap for O(1) lookup** - Efficient duplicate detection
- **Window size tracking** - Continuously update maximum length
- **Index management** - Careful handling of left pointer advancement

## Algorithm Implementation
```java
import java.util.HashMap;

public int lengthOfLongestSubstring(String s) {
    int ans=0;
    HashMap<Character,Integer> map = new HashMap<>();
    int left=0;
    for(int right=0;right<s.length();right++){
        char ch = s.charAt(right);
        if(!map.containsKey(ch)){
            map.put(ch,right);
        }
        else{
            left = Math.max(left,map.get(ch)+1);
            map.put(ch,right);
        }
        ans = Math.max(ans,right-left+1);
    }
    return ans;
}
```

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(min(m,n)) - HashMap size limited by character set
- **Optimization:** Sliding window avoids nested loops

## Edge Cases Considered
1. **Empty string** - Returns 0
2. **Single character** - Returns 1
3. **All unique characters** - Returns string length
4. **All same characters** - Returns 1
5. **Repeated pattern** - Handles overlapping windows correctly

## Alternative Approaches
1. **Brute force** - Check all substrings O(n³)
2. **Set-based sliding window** - Use HashSet instead of HashMap
3. **Array-based** - Fixed-size array for ASCII characters
4. **Two-pointer without HashMap** - Less efficient but possible

## LeetCode Connection
- **LeetCode 3:** Longest Substring Without Repeating Characters (exact match)
- **Pattern:** Sliding window problems
- **Related:** Various substring optimization problems

## Cross-References
- **Related AC Problems:** StringCompression (character processing)
- **Similar Kunal Problems:** ValidParentheses (HashMap usage)
- **Core Concept:** Sliding window and HashMap optimization

---
**Learning Focus:** Sliding window technique, HashMap for duplicate detection, substring optimization 