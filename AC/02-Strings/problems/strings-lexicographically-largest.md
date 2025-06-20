# Lexicographically Largest String - AC Strings

## Problem Statement
**Difficulty:** Easy  
**Topic:** String Comparison, Array Processing  
**Source:** Apna College (AC)

Find the lexicographically largest string from an array of strings.

**Example:**
- Input: `["apple","mango","banana"]`
- Output: `"mango"`

## Intuition/Approach
**Algorithm: String Comparison Iteration**
1. **Initialize with first string** - Start with first element as candidate
2. **Compare with remaining strings** - Use compareTo() for lexicographic comparison
3. **Update largest** - Replace candidate when larger string found
4. **Return result** - Final largest string after all comparisons

**Key Insight:** Java's `compareTo()` method handles lexicographic comparison automatically.

## Key Observations
- **Lexicographic ordering** - Dictionary-like alphabetical comparison
- **String.compareTo() utility** - Built-in method for comparison
- **Linear scan efficiency** - Single pass through array
- **Case sensitivity** - Standard comparison is case-sensitive

## Algorithm Implementation
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

## Complexity Analysis
- **Time Complexity:** O(n×m) - n strings, average m length comparison
- **Space Complexity:** O(1) - Only storing reference to largest string
- **Comparison Cost:** Each compareTo() operation depends on string length

## Edge Cases Considered
1. **Single string array** - Returns the only string
2. **All identical strings** - Returns any (first) string
3. **Empty strings in array** - Handled by compareTo() logic
4. **Mixed case strings** - Capital letters come before lowercase
5. **Prefix relationships** - Longer string wins when one is prefix of another

## Alternative Approaches
1. **Sorting approach** - Sort array and take last element O(n log n)
2. **Stream API** - Use Java 8 streams with max() function
3. **Collections.max()** - Built-in utility method
4. **Custom comparator** - For case-insensitive comparison

## LeetCode Connection
- **Similar Pattern:** Array maximum finding problems
- **String Comparison:** Various string ordering problems
- **Related:** Dictionary order problems

## Cross-References
- **Related AC Problems:** FirstCapital (string processing)
- **Similar Kunal Problems:** String comparison patterns
- **Core Concept:** Lexicographic ordering fundamentals

---
**Learning Focus:** String comparison, lexicographic ordering, Java compareTo() method 