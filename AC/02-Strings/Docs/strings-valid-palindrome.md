# Valid Palindrome

**Source:** AC | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Determine if a string is a valid palindrome considering only alphanumeric characters and ignoring case.

## Intuition/Approach
Preprocess to keep only lowercase alphanumerics, then compare from both ends towards the center.

## Key Observations
- Cleaning simplifies two-pointer logic
- Case normalization ensures consistent comparison
- Two-pointer comparison is linear time

## Algorithm Steps
1. Build cleaned string by filtering non-alphanumerics and lowercasing
2. Set left = 0, right = len - 1
3. While left < right, compare; if mismatch return false
4. If loop completes, return true

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** One pass to clean, one pass to compare

## Edge Cases Considered
- [x] Empty string (valid palindrome)
- [x] Single character
- [x] All non-alphanumeric → becomes empty
- [x] Mixed case
- [x] Digits included

## Solution Code

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

## Alternative Approaches
- Two pointers skipping non-alphanumerics in-place
- Reverse cleaned string and compare
- Regex-based cleaning

## Personal Notes
- Mirrors LeetCode 125; standard two-pointer validation

---
**Tags:** #strings #twopointers #palindrome