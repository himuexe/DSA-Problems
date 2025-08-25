# String Compression

**Source:** AC | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Compress a string by counting consecutive identical characters and representing them as character followed by the count (only when count > 1).

## Intuition/Approach
Use a single pass to count consecutive occurrences and build the compressed result, appending counts only when greater than one.

## Key Observations
- Single pass traversal with pointer advancement
- Append count only for repeats (skip for singles)
- Use StringBuilder to avoid concatenation overhead

## Algorithm Steps
1. Initialize an empty StringBuilder result
2. Iterate index i from 0 to n-1
3. Count how many times current character repeats consecutively
4. Append the character, and if count > 1 append the count
5. Continue until end of string; return result

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) extra (excluding output)
- **Justification:** Single linear scan; StringBuilder holds only the output

## Edge Cases Considered
- [ ] Empty input
- [ ] Single element
- [x] No consecutive characters → returns original string
- [x] All identical characters → returns char + count
- [x] Mixed alternating patterns

## Solution Code

```java
public static String compress(String str){
    StringBuilder newStr = new StringBuilder("");
    for(int i=0;i<str.length();i++){
        Integer count =1;
        while(i<str.length()-1 && str.charAt(i) == str.charAt(i+1)){
            count++;
            i++;
        }
        newStr.append(str.charAt(i));
        if(count > 1){
            newStr.append(count.toString());
        }
    }
    return newStr.toString();
}
```

## Alternative Approaches
- Two pointers with explicit start/end
- Character array in-place style (conceptual)
- Recursive segment compression

## Personal Notes
- Related: LeetCode 443 String Compression; classic run-length encoding pattern

---
**Tags:** #strings #twopointers #runlengthencoding