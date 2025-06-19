# Longest Common Prefix

**Difficulty:** Easy  
**Topic:** Strings, Prefix Matching  
**Source:** Kunal (Kunal Kushwaha)

## Problem Statement

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Examples:**
```
Input: strs = ["flower","flow","flight"]
Output: "fl"

Input: strs = ["dog","racecar","car"]
Output: "" (no common prefix)
```

## Approach & Intuition

### Horizontal Scanning:
The solution uses a horizontal scanning approach where we compare strings pair by pair to find the common prefix.

### Algorithm Steps:
1. **Initialize:** Start with the first string as the initial prefix
2. **Iterate:** For each subsequent string, find the common prefix with current prefix
3. **Shrink Prefix:** Keep reducing the prefix until it matches the beginning of current string
4. **Early Termination:** If prefix becomes empty at any point, return empty string

### Key Insight:
The `indexOf()` method returns 0 only when the prefix matches from the beginning of the string. We keep shortening the prefix until this condition is met.

## Key Observations

1. **Prefix Property:** The common prefix of all strings is also the prefix of any individual string
2. **Shrinking Strategy:** By progressively shortening the prefix, we ensure it remains common
3. **Early Exit:** Empty prefix means no common prefix exists
4. **Order Independent:** The algorithm works regardless of string order

## Complexity Analysis

**Time Complexity:** O(S) where S is the sum of all characters in all strings
- In worst case, we might need to compare all characters
- Each string comparison takes O(m) where m is the length of current prefix

**Space Complexity:** O(1) 
- Only using a few variables for tracking
- Prefix manipulation is done in-place using substring

## Edge Cases Considered

1. **Empty Array:** Returns empty string (handled by length check)
2. **Single String:** Returns the string itself
3. **Empty String Present:** Will result in empty common prefix
4. **No Common Prefix:** Returns empty string when prefix becomes empty

## Code Implementation

```java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    
    String prefix = strs[0];  // Start with first string
    
    for (int i = 1; i < strs.length; i++) {
        // Keep shrinking prefix until it matches from beginning
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) {
                return "";  // No common prefix
            }
        }
    }
    return prefix;
}
```

## Alternative Solutions

1. **Vertical Scanning:** Compare character by character across all strings
2. **Divide and Conquer:** Recursively divide the array and merge results
3. **Binary Search:** Binary search on the length of possible prefix
4. **Trie-based:** Build a Trie and find the longest common path

## Algorithm Walkthrough

**Example: ["flower", "flow", "flight"]**
```
Step 1: prefix = "flower"
Step 2: Compare with "flow"
  - "flow".indexOf("flower") != 0, so shrink: "flowe"
  - "flow".indexOf("flowe") != 0, so shrink: "flow"
  - "flow".indexOf("flow") == 0, prefix = "flow"
Step 3: Compare with "flight"
  - "flight".indexOf("flow") != 0, so shrink: "flo"
  - "flight".indexOf("flo") != 0, so shrink: "fl"
  - "flight".indexOf("fl") == 0, prefix = "fl"
Result: "fl"
```

## Cross-Reference

- **Related Problems:** String matching, pattern recognition
- **Prerequisites:** String manipulation, indexOf() method
- **Next Steps:** Trie data structure, advanced string algorithms

---

*Documented on: 2024-12-19*  
*Category: Strings (Prefix Matching)* 