# Valid Parentheses - Kunal Strings

## Problem Statement
**Difficulty:** Easy  
**Topic:** Stack, String Processing, HashMap  
**Source:** Kunal Kushwaha DSA Course

Determine if a string containing only parentheses characters is valid. Valid means properly opened and closed in correct order.

**Example:**
- Input: `"()[]{}"`
- Output: `true`
- Input: `"([)]"`
- Output: `false`

## Intuition/Approach
**Algorithm: Stack-based Matching**
1. **HashMap for mapping** - Map closing brackets to opening brackets
2. **Stack for tracking** - Store opening brackets in stack
3. **Process characters** - Push opening brackets, pop and validate closing brackets
4. **Final validation** - Stack should be empty for valid parentheses

**Key Insight:** Stack naturally handles the Last-In-First-Out nature of nested parentheses.

## Key Observations
- **Stack data structure** - Perfect for bracket matching problems
- **HashMap optimization** - Efficient bracket type identification
- **Order validation** - Ensures proper nesting structure
- **Edge case handling** - Empty stack scenarios handled gracefully

## Algorithm Implementation
```java
import java.util.*;

public boolean isValid(String s) {
    HashMap<Character,Character> map = new HashMap<>();
    map.put(')','(');
    map.put(']','[');
    map.put('}','{');
    Stack<Character> stack = new Stack<>();
    for(int i=0;i<s.length();i++){
        char curr = s.charAt(i);
        if(map.containsKey(curr)){
            char pop =stack.size() != 0? stack.pop() : '#';
            if(pop != map.get(curr)){
                return false;
            }
        }
        else{
            stack.push(curr);
        }
    }
    return stack.isEmpty();
}
```

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(n) - Stack storage in worst case
- **Stack Operations:** Each push/pop is O(1)

## Edge Cases Considered
1. **Empty string** - Valid (returns true)
2. **Single bracket** - Invalid (unmatched)
3. **Only opening brackets** - Invalid (stack not empty)
4. **Only closing brackets** - Invalid (empty stack pop)
5. **Mixed valid/invalid** - Proper validation logic

## Alternative Approaches
1. **Counter-based** - Count each bracket type (less robust)
2. **Recursive approach** - Process brackets recursively
3. **String replacement** - Replace valid pairs iteratively
4. **Array-based stack** - Use array instead of Stack class

## LeetCode Connection
- **LeetCode 20:** Valid Parentheses (exact match)
- **Pattern:** Stack-based validation problems
- **Related:** Bracket matching and nested structure problems

## Cross-References
- **Related AC Problems:** N/A - specific stack application
- **Similar Kunal Problems:** LongestSubstring (HashMap usage)
- **Core Concept:** Stack data structure and bracket matching

---
**Learning Focus:** Stack data structure, bracket matching algorithms, HashMap optimization 