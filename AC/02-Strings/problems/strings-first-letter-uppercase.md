# First Letter Uppercase - AC Strings

## Problem Statement
**Difficulty:** Easy  
**Topic:** String Manipulation, Character Processing  
**Source:** Apna College (AC)

Convert the first letter of each word in a string to uppercase while keeping other letters unchanged.

**Example:**
- Input: `"my name is himanshu"`
- Output: `"My Name Is Himanshu"`

## Intuition/Approach
**Algorithm: Word Boundary Detection**
1. **Capitalize first character** - Always uppercase the first letter
2. **Scan for spaces** - Detect word boundaries using space characters  
3. **Capitalize after spaces** - Convert character after space to uppercase
4. **Preserve other characters** - Keep all other characters unchanged

**Key Insight:** Word boundaries are identified by space characters, requiring look-ahead logic.

## Key Observations
- **Word boundary detection** - Spaces indicate start of new words
- **StringBuilder efficiency** - Avoid string concatenation overhead
- **Character case conversion** - Use Character.toUpperCase() utility
- **Index management** - Careful handling when skipping characters after spaces

## Algorithm Implementation
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

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(n) - StringBuilder for result construction
- **Character Operations:** Constant time for case conversion

## Edge Cases Considered
1. **Single word** - Only first character capitalized
2. **Multiple consecutive spaces** - Handles boundary detection correctly
3. **String ending with space** - Boundary check prevents out-of-bounds
4. **Single character** - Direct capitalization
5. **Empty string** - Would need additional null/empty check

## Alternative Approaches
1. **String.split() approach** - Split by spaces, capitalize each word
2. **Regular expression** - Pattern matching for word boundaries
3. **Character array** - Direct array manipulation
4. **Stream processing** - Functional approach with mapping

## LeetCode Connection
- **Similar Pattern:** String transformation problems
- **Text Processing:** Capitalize words problems
- **Related:** Title case conversion problems

## Cross-References
- **Related AC Problems:** StringCompression (StringBuilder usage)
- **Similar Kunal Problems:** String processing patterns
- **Core Concept:** String manipulation and character processing

---
**Learning Focus:** Word boundary detection, character case conversion, StringBuilder usage 