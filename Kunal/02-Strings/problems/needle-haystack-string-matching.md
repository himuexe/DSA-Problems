# Needle in Haystack - String Pattern Matching

## Problem Statement
Find the index of the first occurrence of a substring (needle) in a string (haystack). Return -1 if the needle is not found in the haystack.

## Intuition/Approach
**Brute Force String Matching:**
1. **Edge Case:** If needle is empty, return 0 (empty string found at index 0)
2. **Length Validation:** If haystack is shorter than needle, return -1
3. **Sliding Window:** For each possible starting position in haystack:
   - Extract substring of needle length
   - Compare with needle using equals()
   - Return index if match found
4. **No Match:** Return -1 if no occurrence found

**Key Insight:** Use substring extraction and direct string comparison for simplicity and readability.

## Key Observations
- **String Methods:** Leverages built-in substring() and equals() methods
- **Boundary Optimization:** Loop only runs (n-m+1) times to avoid out-of-bounds
- **Early Termination:** Returns immediately upon first match found
- **Empty String Handling:** Standard convention treats empty needle as found at index 0
- **Case Sensitivity:** Exact character matching (case-sensitive comparison)

## Algorithm Steps
1. **Edge Case Check:**
   - If needle.isEmpty(): return 0
   - If haystack.length() < needle.length(): return -1

2. **Sliding Window Search:**
   - For i from 0 to (n-m):
     - Extract substring: haystack.substring(i, i+m)
     - Compare with needle using equals()
     - If match: return i
   
3. **No Match Found:** Return -1

## Time & Space Complexity
- **Time Complexity:** O(n × m)
  - Outer loop: O(n-m+1) = O(n) iterations
  - substring() operation: O(m) per iteration
  - equals() comparison: O(m) per iteration
  - Total: O(n × m) in worst case
- **Space Complexity:** O(m)
  - substring() creates new string of length m
  - No additional data structures needed
  - Overall space: O(m)

## Edge Cases Considered
- [x] Empty needle (return 0)
- [x] Empty haystack with non-empty needle (return -1)
- [x] Needle longer than haystack (return -1)
- [x] Needle equals haystack (return 0)
- [x] Needle not found (return -1)
- [x] Multiple occurrences (return first index)

## Code Implementation
```java
public int strStr(String haystack, String needle) {
    // Edge case: empty needle
    if (needle.isEmpty()) {
        return 0;
    }
    
    int n = haystack.length();
    int m = needle.length();
    
    // Edge case: needle longer than haystack
    if (n < m) {
        return -1;
    }
    
    // Sliding window search
    for (int i = 0; i <= n - m; i++) {
        String str = haystack.substring(i, i + m);
        if (str.equals(needle)) {
            return i;  // First occurrence found
        }
    }
    
    return -1;  // No occurrence found
}
```

## Example Walkthrough
**Input:** haystack = "sadbutsad", needle = "sad"

**Execution:**
- i=0: substring(0,3) = "sad" → equals("sad") = true → return 0 ✓
- Result: 0 (first occurrence at index 0)

**Input:** haystack = "leetcode", needle = "leeto"

**Execution:**
- i=0: substring(0,5) = "leetc" → equals("leeto") = false
- i=1: substring(1,6) = "eetco" → equals("leeto") = false  
- i=2: substring(2,7) = "etcod" → equals("leeto") = false
- i=3: substring(3,8) = "tcode" → equals("leeto") = false
- Result: -1 (no occurrence found)

## Alternative Implementations
1. **Built-in Method:** return haystack.indexOf(needle)
2. **Character-by-Character:** Manual comparison without substring()
3. **KMP Algorithm:** O(n+m) time complexity with preprocessing
4. **Boyer-Moore:** Efficient for large alphabets and long patterns

## Optimization Opportunities
1. **KMP Algorithm:** Optimal O(n+m) time complexity
2. **Boyer-Moore:** Good average-case performance
3. **Rabin-Karp:** Rolling hash for multiple pattern matching
4. **Character Comparison:** Avoid substring creation overhead

## Key Learning Points
- **String Manipulation:** Effective use of built-in string methods
- **Boundary Conditions:** Proper loop bounds to avoid errors
- **Edge Case Handling:** Comprehensive coverage of special cases
- **Algorithmic Awareness:** Understanding when to use different approaches

## Applications
- **Text Processing:** Search functionality in text editors
- **Pattern Matching:** Find occurrences of patterns in data
- **String Algorithms:** Foundation for more complex string operations
- **Search Engines:** Basic text search implementation

---
**Date:** June 28, 2025  
**Topic:** String Processing & Pattern Matching  
**Difficulty:** Easy  
**Category:** String Algorithms 