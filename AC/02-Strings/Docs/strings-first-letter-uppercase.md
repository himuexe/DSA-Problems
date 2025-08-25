# First Letter Uppercase

**Source:** AC | **Topic:** Strings | **Difficulty:** Easy  

---

## Problem Statement
Convert the first letter of each word in a string to uppercase while keeping other letters unchanged.

## Intuition/Approach
Detect word boundaries by spaces; uppercase the first character and any character following a space while copying others as-is.

## Key Observations
- Spaces mark new word starts
- StringBuilder preferred over repeated concatenation
- Character.toUpperCase handles casing safely

## Algorithm Steps
1. Append uppercase of first character
2. For each next char, if current is space and not last, append space and uppercase of next
3. Otherwise append current character
4. Return built string

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Single pass; output stored in builder

## Edge Cases Considered
- [ ] Empty input
- [x] Single element
- [x] Multiple consecutive spaces
- [x] Trailing space guarded by bounds check

## Solution Code

```java
public static String convert(String str){
    StringBuilder newStr = new StringBuilder("");
    char ch = Character.toUpperCase(str.charAt(0));
    newStr.append(ch);
    for(int i=1;i<str.length();i++){
        if(str.charAt(i)==' ' && i< str.length()-1){
            newStr.append(str.charAt(i));
            i++;
            newStr.append(Character.toUpperCase(str.charAt(i)));
        }
        else{
            newStr.append(str.charAt(i));
        }
    }
    return newStr.toString();
}
```

## Alternative Approaches
- Split and capitalize words, then join
- Regex with word-boundary capture
- Character array in-place style

## Personal Notes
- Useful utility in text formatting pipelines

---
**Tags:** #strings #textprocessing #implementation