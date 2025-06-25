# Remove Duplicates from String

**Date:** June 25, 2025  
**Topic:** Recursion  
**Difficulty:** Medium  
**Time Complexity:** O(n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Given a string, remove all duplicate characters and return a string with only the first occurrence of each character preserved. Use recursion to solve this problem.

**Example:**
```
Input: "meeoow"
Output: "meow"

Explanation:
- 'm' → first occurrence, keep
- 'e' → first occurrence, keep  
- 'e' → duplicate, remove
- 'o' → first occurrence, keep
- 'o' → duplicate, remove
- 'w' → first occurrence, keep
```

---

## Approach & Intuition

### Recursive Strategy with Frequency Tracking
**Key Components:**
1. **Index tracking:** Process string character by character
2. **Frequency map:** Boolean array to track seen characters
3. **StringBuilder:** Build result string efficiently
4. **Character mapping:** Use `char - 'a'` for lowercase letters

**Algorithm Logic:**
1. **Base case:** When index reaches string length, return
2. **Check frequency:** If character already seen, skip it
3. **First occurrence:** Mark as seen and append to result
4. **Recursive call:** Process next character

### Why This Approach Works
- **Linear traversal:** Each character processed exactly once
- **O(1) lookup:** Boolean array provides constant time checking
- **Preserves order:** First occurrence maintained due to left-to-right processing
- **Space efficient:** Boolean array uses minimal space (26 elements for lowercase)

---

## Code Implementation

```java
public class RemoveDuplicates {
    public static void removeDuplicates(String str, int idx, StringBuilder newStr, boolean[] map) {
        // Base case: processed all characters
        if(idx == str.length()) {
            return;
        }
        
        char curr = str.charAt(idx);
        
        // If character already seen, skip it
        if(map[curr - 'a']) {
            removeDuplicates(str, idx+1, newStr, map);
        } 
        else {
            // First occurrence: mark as seen and append
            map[curr - 'a'] = true;
            removeDuplicates(str, idx+1, newStr.append(curr), map);
        }
    }
    
    public static void main(String[] args) {
        String str = "meeoow";
        removeDuplicates(str, 0, new StringBuilder(""), new boolean[26]);
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(n) - Recursion stack + StringBuilder + boolean array
  - Recursion stack: O(n)
  - StringBuilder: O(n) worst case
  - Boolean array: O(26) = O(1)

### Performance Analysis
| Operation | Time | Explanation |
|-----------|------|-------------|
| **Character access** | O(1) | str.charAt(idx) |
| **Map lookup** | O(1) | map[curr - 'a'] |
| **Map update** | O(1) | map[curr - 'a'] = true |
| **StringBuilder append** | O(1) amortized | newStr.append(curr) |
| **Overall** | O(n) | Linear traversal |

---

## Recursion Tree Analysis

### Example: removeDuplicates("meeoow", 0, "", map)
```
removeDuplicates("meeoow", 0, "", [false×26])
├── curr='m', map[12]=false → mark true, append 'm'
├── removeDuplicates("meeoow", 1, "m", [m=true])
    ├── curr='e', map[4]=false → mark true, append 'e'  
    ├── removeDuplicates("meeoow", 2, "me", [m=true,e=true])
        ├── curr='e', map[4]=true → skip (duplicate)
        ├── removeDuplicates("meeoow", 3, "me", [m=true,e=true])
            ├── curr='o', map[14]=false → mark true, append 'o'
            ├── removeDuplicates("meeoow", 4, "meo", [m=true,e=true,o=true])
                ├── curr='o', map[14]=true → skip (duplicate)
                ├── removeDuplicates("meeoow", 5, "meo", [m=true,e=true,o=true])
                    ├── curr='w', map[22]=false → mark true, append 'w'
                    ├── removeDuplicates("meeoow", 6, "meow", [m=true,e=true,o=true,w=true])
                        └── idx==6==length → return
```

---

## Edge Cases

- [x] **Empty string:** Returns immediately (idx == 0 == str.length())
- [x] **Single character:** Returns same character
- [x] **All same characters:** Returns single character
- [x] **No duplicates:** Returns original string
- [x] **All characters different:** Returns original string
- [x] **Uppercase letters:** Not handled (assumes lowercase only)

---

## Alternative Approaches

### 1. Iterative with HashSet
```java
public static String removeDuplicatesIterative(String str) {
    Set<Character> seen = new HashSet<>();
    StringBuilder result = new StringBuilder();
    
    for(char c : str.toCharArray()) {
        if(!seen.contains(c)) {
            seen.add(c);
            result.append(c);
        }
    }
    return result.toString();
}
```
- **Time:** O(n), **Space:** O(n)

### 2. Using LinkedHashSet
```java
public static String removeDuplicatesSet(String str) {
    Set<Character> set = new LinkedHashSet<>();
    for(char c : str.toCharArray()) {
        set.add(c);
    }
    
    StringBuilder result = new StringBuilder();
    for(char c : set) {
        result.append(c);
    }
    return result.toString();
}
```

### 3. Recursive with Return Value
```java
public static String removeDuplicatesReturn(String str, int idx, boolean[] seen) {
    if(idx == str.length()) return "";
    
    char curr = str.charAt(idx);
    String rest = removeDuplicatesReturn(str, idx+1, seen);
    
    if(seen[curr - 'a']) {
        return rest;  // Skip duplicate
    } else {
        seen[curr - 'a'] = true;
        return curr + rest;  // Include first occurrence
    }
}
```

---

## Variations

### Handle Both Cases
```java
public static void removeDuplicatesAllCases(String str, int idx, StringBuilder newStr, boolean[] map) {
    if(idx == str.length()) return;
    
    char curr = str.charAt(idx);
    int index = Character.isLowerCase(curr) ? curr - 'a' : curr - 'A' + 26;
    
    if(map[index]) {
        removeDuplicatesAllCases(str, idx+1, newStr, map);
    } else {
        map[index] = true;
        removeDuplicatesAllCases(str, idx+1, newStr.append(curr), map);
    }
}
```

### Count Duplicates Removed
```java
public static int countDuplicatesRemoved(String str, int idx, boolean[] seen, int count) {
    if(idx == str.length()) return count;
    
    char curr = str.charAt(idx);
    if(seen[curr - 'a']) {
        return countDuplicatesRemoved(str, idx+1, seen, count+1);
    } else {
        seen[curr - 'a'] = true;
        return countDuplicatesRemoved(str, idx+1, seen, count);
    }
}
```

---

## Key Observations

### Character Frequency Tracking
- **Boolean array:** More space-efficient than HashMap for known character set
- **Index mapping:** `char - 'a'` converts character to array index
- **Assumption:** Input contains only lowercase English letters

### StringBuilder Usage
- **Efficiency:** Avoids string concatenation overhead
- **Mutability:** Allows in-place building of result
- **Amortized O(1):** Append operation is efficient

### Recursion Pattern
- **Tail recursion:** Can be optimized by compiler
- **State passing:** Map and StringBuilder passed through calls
- **No return value:** Result built in-place via StringBuilder

---

## Key Learnings

1. **Frequency tracking:** Boolean array for character presence
2. **Efficient string building:** StringBuilder over string concatenation
3. **Character mapping:** Convert char to array index
4. **Recursion with state:** Pass mutable objects through calls
5. **Space optimization:** Boolean array vs HashMap trade-offs

---

## Related Problems

- **Remove Duplicates from Sorted Array:** Similar concept, different data structure
- **First Unique Character:** Character frequency analysis
- **Longest Substring Without Repeating Characters:** Sliding window approach
- **Valid Anagram:** Character frequency comparison
- **Group Anagrams:** Character frequency grouping

---

## Notes

- **Interview Frequency:** Medium - tests recursion with state management
- **Assumption:** Lowercase English letters only (a-z)
- **Extension:** Can be modified for all ASCII or Unicode characters
- **Performance:** Linear time makes it efficient for large strings
- **Pattern:** Recursion with auxiliary data structures for optimization 