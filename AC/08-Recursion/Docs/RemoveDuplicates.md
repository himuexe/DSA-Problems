# Remove Duplicates from String

**Source:** AC | **Topic:** Recursion | **Difficulty:** Medium  

---

## Problem Statement
Given a string, remove duplicate characters so that only the first occurrence of each character remains, preserving original order.

Example:
```
"meeoow" → "meow"
```

## Intuition/Approach
- Traverse the string recursively while tracking seen characters.
- For lowercase-only input, use a boolean[26] for O(1) lookup.
- Append a character on its first occurrence; skip subsequent occurrences.

## Key Observations
- Boolean array is more space-efficient than a HashSet for fixed alphabets.
- Preserves order due to left-to-right processing.
- State is passed via parameters (index, builder, seen array).

## Algorithm Steps
1. If `idx == str.length()`, return.
2. Let `curr = str.charAt(idx)`.
3. If `seen[curr - 'a']` is true, recurse to next index without appending.
4. Otherwise mark seen and append `curr`, then recurse.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass; recursion stack and builder are linear.

## Edge Cases Considered
- [x] Empty string
- [x] Single character
- [x] All same characters
- [x] No duplicates
- [x] Uppercase handling (out of scope unless extended)

## Solution Code

```java
public class RemoveDuplicates {
    public static void removeDuplicates(String str, int idx, StringBuilder newStr, boolean[] seen) {
        if (idx == str.length()) {
            return;
        }
        char curr = str.charAt(idx);
        if (seen[curr - 'a']) {
            removeDuplicates(str, idx + 1, newStr, seen);
        } else {
            seen[curr - 'a'] = true;
            removeDuplicates(str, idx + 1, newStr.append(curr), seen);
        }
    }
}
```

## Alternative Approaches
- Iterative with HashSet or LinkedHashSet (preserves insertion order).
- Recursive version returning a new string instead of using a builder.
- Extended version handling both cases with a larger boolean array or map.

## Personal Notes
- For large alphabets (Unicode), prefer a BitSet/HashSet and iterative approach.

---
**Tags:** #recursion #strings #deduplication