# Phone Number Letter Combinations - Classic LeetCode Problem

## Problem Statement
Given a string containing digits 2-9, return all possible letter combinations that the number could represent on a phone keypad. Each digit maps to a set of letters as shown on telephone keypads.

## Intuition/Approach
**Recursive Letter Combination Generation:**
1. **Base Case:** When no digits remain (up.isEmpty()), add current path to result
2. **Digit Mapping:** Get letter string for current digit (2→"abc", 3→"def", etc.)
3. **Letter Exploration:** For each letter in current digit's mapping:
   - Add letter to path
   - Recurse with remaining digits
4. **Result Collection:** Combine all recursive results

**Key Insight:** Generate all combinations by exploring each letter option for each digit position.

## Key Observations
- **Phone Keypad Mapping:** Standard telephone keypad letter assignments
- **Digit Range:** Only digits 2-9 have letter mappings (0,1 have none)
- **Variable Letter Count:** Some digits have 3 letters, others have 4 (7,9)
- **Order Preservation:** Combination order follows input digit sequence
- **Empty Input:** Empty string returns empty list (not single empty string)

## Phone Keypad Mapping
```
2: "abc"    3: "def"
4: "ghi"    5: "jkl"    6: "mno"
7: "pqrs"   8: "tuv"    9: "wxyz"
```

## Algorithm Steps
1. **Edge Case:** If input digits is empty, return empty list

2. **Recursive Processing:**
   - Base case: If up.isEmpty(), create list with current path
   - Get first digit and convert to letters using getLetters()
   - For each letter in digit's mapping:
     - Recursive call: perm(path + letter, remaining_digits)
     - Add all results to answer list

3. **Helper Method (getLetters):**
   - Switch statement mapping digits to letter strings
   - Return appropriate letter string for each digit

## Time & Space Complexity
- **Time Complexity:** O(3^N × 4^M)
  - N = number of digits mapping to 3 letters (2,3,4,5,6,8)
  - M = number of digits mapping to 4 letters (7,9)
  - Each recursive call explores all letter options
- **Space Complexity:** O(3^N × 4^M)
  - Result list storage: all combinations
  - Recursion stack: O(digits.length()) depth
  - String concatenation: O(digits.length()) per combination

## Edge Cases Considered
- [x] Empty input string (return empty list)
- [x] Single digit (return all mapped letters)
- [x] Digits with 3 letters vs 4 letters
- [x] Maximum input length (stack overflow protection)
- [x] Invalid digits (0, 1) - handled by switch default

## Code Implementation
```java
import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();  // Empty input case
        }
        return perm("", digits);
    }
    
    private ArrayList<String> perm(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);  // Base case: complete combination
            return list;
        }
        
        int digit = up.charAt(0) - '0';  // Get first digit
        ArrayList<String> ans = new ArrayList<>();
        String letters = getLetters(digit);  // Get corresponding letters
        
        // Explore each letter option
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            ans.addAll(perm(p + ch, up.substring(1)));
        }
        
        return ans;
    }
    
    private String getLetters(int digit) {
        switch (digit) {
            case 2: return "abc";
            case 3: return "def";
            case 4: return "ghi";
            case 5: return "jkl";
            case 6: return "mno";
            case 7: return "pqrs";
            case 8: return "tuv";
            case 9: return "wxyz";
            default: return "";  // Invalid digit
        }
    }
}
```

## Example Walkthrough
**Input:** digits = "23"

**Letter Mappings:**
- 2 → "abc"
- 3 → "def"

**Recursive Tree:**
```
perm("", "23")
├─ perm("a", "3")
│  ├─ perm("ad", "") → ["ad"]
│  ├─ perm("ae", "") → ["ae"]
│  └─ perm("af", "") → ["af"]
├─ perm("b", "3")
│  ├─ perm("bd", "") → ["bd"]
│  ├─ perm("be", "") → ["be"]
│  └─ perm("bf", "") → ["bf"]
└─ perm("c", "3")
   ├─ perm("cd", "") → ["cd"]
   ├─ perm("ce", "") → ["ce"]
   └─ perm("cf", "") → ["cf"]
```

**Final Result:** ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

## Alternative Implementations
1. **Iterative Approach:** Use queue/list to build combinations level by level
2. **Backtracking:** Use character array with backtracking
3. **Mathematical Indexing:** Direct calculation of nth combination
4. **Stream API:** Functional programming approach with Java streams

## Optimization Opportunities
1. **StringBuilder:** Use StringBuilder for string concatenation
2. **Character Array:** Avoid string concatenation overhead
3. **Precomputed Mapping:** Static array instead of switch statement
4. **Memory Pool:** Reuse ArrayList objects

## Key Learning Points
- **Recursive Enumeration:** Systematic generation of all combinations
- **String Manipulation:** Efficient handling of string operations
- **Problem Mapping:** Converting real-world problem to algorithmic solution
- **LeetCode Pattern:** Standard recursive backtracking template

## Applications
- **Phone Systems:** T9 predictive text input
- **Combinatorial Problems:** General combination generation
- **Game Development:** Password/code generation systems
- **User Interface:** Autocomplete and suggestion systems

---
**Date:** June 28, 2025  
**Topic:** Recursion & String Combinations  
**Difficulty:** Intermediate  
**Category:** LeetCode Classic 