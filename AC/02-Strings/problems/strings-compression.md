# String Compression - AC Strings

## Problem Statement
**Difficulty:** Easy  
**Topic:** String Manipulation, Two Pointers  
**Source:** Apna College (AC)

Compress a string by counting consecutive identical characters and representing them as character followed by count (if count > 1).

**Example:**
- Input: `"aabbbcccc"`
- Output: `"a2b3c4"`

## Intuition/Approach
**Algorithm: Consecutive Character Counting**
1. **Iterate through string** - Process each character position
2. **Count consecutive characters** - Use inner loop to count same characters
3. **Build compressed string** - Append character + count (if > 1)
4. **Skip processed characters** - Move pointer past counted characters

**Key Insight:** Only append count when greater than 1 to handle single characters properly.

## Key Observations
- **String traversal optimization** - Single pass with smart pointer movement
- **Count representation** - Only append count for repeated characters
- **StringBuilder efficiency** - Avoid string concatenation overhead
- **Edge case handling** - Single characters don't need count suffix

## Algorithm Implementation
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

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(1) extra space - StringBuilder for result storage
- **Optimization:** No additional data structures needed beyond result

## Edge Cases Considered
1. **Single character string** - Returns same character
2. **No consecutive characters** - Returns original string  
3. **All same characters** - Returns char + count
4. **Empty string** - Returns empty string
5. **Mixed pattern** - Handles alternating compression correctly

## Alternative Approaches
1. **Two-pointer technique** - Similar logic with explicit pointers
2. **Character array approach** - Direct array manipulation
3. **Recursive solution** - Process segments recursively

## LeetCode Connection
- **LeetCode 443:** String Compression (similar concept)
- **Related:** Run-length encoding problems
- **Pattern:** Character frequency counting

## Cross-References
- **Related AC Problems:** ValidPalindrome (string processing)
- **Similar Kunal Problems:** N/A directly comparable
- **Core Concept:** String manipulation fundamentals

---
**Learning Focus:** String compression, consecutive character processing, StringBuilder optimization 