# Lexicographically Largest String

**Source:** AC | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Find the lexicographically largest string from an array of strings.

## Intuition/Approach
Linearly scan the array while tracking the current largest by using Java's compareTo for lexicographic comparison.

## Key Observations
- Dictionary order comparison via compareTo
- Linear scan suffices; sorting is unnecessary
- Case-sensitive by default; adjust comparator if needed

## Algorithm Steps
1. Set largest = first element
2. For each remaining string s, if largest.compareTo(s) < 0 then set largest = s
3. Return largest

## Complexity Analysis
- **Time Complexity:** O(n·m)
- **Space Complexity:** O(1)
- **Justification:** Compare operations can touch up to the shorter string length

## Edge Cases Considered
- [ ] Empty input
- [x] Single element
- [x] All identical strings
- [x] Empty strings present
- [x] One string prefix of another

## Solution Code

```java
public static String getLargest(String[] str){
    String largest = str[0];
    for(int i=1;i<str.length;i++){
        if(largest.compareTo(str[i])<0){
            largest = str[i];
        }
    }
    return largest;
}
```

## Alternative Approaches
- Sort and take last (O(n log n))
- Streams: Arrays.stream(str).max(String::compareTo)
- Custom Comparator for case-insensitive compare

## Personal Notes
- Simple scan beats sorting for just finding max

---
**Tags:** #strings #comparison #arrays