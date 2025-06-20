# Valid Palindrome - AC Strings

## Problem Statement
**Difficulty:** Easy  
**Topic:** String Processing, Two Pointers  
**Source:** Apna College (AC)

Determine if a string is a valid palindrome considering only alphanumeric characters and ignoring case sensitivity.

**Example:**
- Input: `"A man, a plan, a canal: Panama"`
- Output: `true` (becomes "amanaplanacanalpanama" which is palindrome)

## Intuition/Approach
**Algorithm: String Cleaning + Two Pointers**
1. **Clean string** - Remove non-alphanumeric characters and convert to lowercase
2. **Two-pointer setup** - Start from both ends of cleaned string
3. **Character comparison** - Compare characters moving towards center
4. **Palindrome validation** - Return false if any mismatch found

**Key Insight:** Preprocessing to clean the string simplifies the palindrome check logic.

## Key Observations
- **String preprocessing** - Filtering non-alphanumeric characters is crucial
- **Case normalization** - Convert to lowercase for consistent comparison
- **Two-pointer efficiency** - O(n) time with single pass comparison
- **StringBuilder usage** - Efficient string building for cleaned version

## Algorithm Implementation
```java
public boolean isPalindrome(String s) {
    StringBuilder newStr = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isLetterOrDigit(c)) {
            newStr.append(Character.toLowerCase(c));
        }
    }
    int left = 0;
    int right = newStr.length() - 1;
    while (left < right) {
        if (newStr.charAt(left) != newStr.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

## Complexity Analysis
- **Time Complexity:** O(n) - String cleaning + palindrome check both O(n)
- **Space Complexity:** O(n) - StringBuilder for cleaned string storage
- **Character Operations:** Constant time for case conversion and validation

## Edge Cases Considered
1. **Empty string** - Considered valid palindrome
2. **Single character** - Always valid palindrome
3. **All non-alphanumeric** - Results in empty string (valid palindrome)
4. **Mixed case** - Normalized to lowercase for comparison
5. **Numbers included** - Alphanumeric includes digits

## Alternative Approaches
1. **In-place two pointers** - Skip non-alphanumeric during comparison
2. **Recursive approach** - Recursive character matching
3. **String reversal** - Clean string and compare with reverse
4. **Regular expression** - Use regex for string cleaning

## LeetCode Connection
- **LeetCode 125:** Valid Palindrome (exact same problem)
- **Related:** Palindrome problems with variations
- **Pattern:** String validation and two-pointer technique

## Cross-References
- **Related AC Problems:** StringCompression (string processing)
- **Similar Kunal Problems:** String validation patterns
- **Core Concept:** Palindrome detection and string preprocessing

---
**Learning Focus:** String preprocessing, two-pointer technique, palindrome validation 