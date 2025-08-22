# String Descending Sequence Splitting

## Problem Statement
Given a string of digits, determine if it can be split into a sequence of consecutive descending integers. The sequence must have at least 2 numbers, and each subsequent number should be exactly 1 less than the previous number.

## Intuition/Approach
The algorithm uses backtracking to try all possible ways to split the string into descending sequences:
1. Build sequence incrementally by trying different number lengths
2. For each position, extract numbers of various lengths and validate descending property
3. Each new number must be exactly 1 less than the previous number
4. Continue until entire string is consumed with valid descending sequence

## Key Observations
- **Minimum Length**: Sequence must contain at least 2 numbers
- **Descending Rule**: Each number must be exactly (previous - 1)
- **Complete Consumption**: Entire string must be used, no leftover digits
- **First Number Freedom**: First number can be any valid length

## Algorithm Steps
1. **Initialize**: Start DFS with empty number list
2. **DFS with Number Building**:
   - **Success Base Case**: Reached end of string with at least 2 numbers
   - **Number Extraction**: Try different lengths for current number
   - **Validation**: Check if current number equals (last_number - 1)
   - **Recurse**: Continue with remaining string and updated sequence
   - **Backtrack**: Remove current number and try different length
3. **Return**: True if valid descending sequence exists

## Time & Space Complexity
- **Time Complexity**: O(n²)
  - For each position, try all possible substring lengths
  - String to number conversion is O(length)
- **Space Complexity**: O(n)
  - List to store current sequence
  - Recursion depth at most n

## Edge Cases Considered
- Single digit: Cannot form descending sequence of length ≥ 2
- Large numbers: Handle long number conversion carefully
- No valid sequence: Return false when no valid split exists
- Leading zeros: Numbers can have leading zeros in this context

## Code Implementation
```java
import java.util.*;
class Solution {
    private boolean dfs(List<Long> list, String str, int start){
        if(start == str.length()){
            return list.size() >= 2;
        }
        long num = 0;
        for(int i = start; i < str.length(); i++){
            num = num * 10 + str.charAt(i) - '0';
            if(list.size() == 0 || list.get(list.size() - 1) - num == 1){
                list.add(num);
                if(dfs(list, str, i + 1)) return true;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    public boolean splitString(String s) {
        return dfs(new ArrayList<>(), s, 0);
    }
}
```

## Example Usage
```java
// Example 1: Valid descending sequence  
String s1 = "1234";
// Output: true (sequence: [1, 2, 3, 4] is NOT descending, so false)
// Correction: "4321" -> true (sequence: [4, 3, 2, 1])

// Example 2: Valid sequence
String s2 = "94932";
// Output: true (sequence: [9, 4, 9, 3, 2] -> [94, 93, 2] is not valid)
// Correction: "9493" -> false, "54321" -> true (sequence: [5, 4, 3, 2, 1])

// Example 3: Invalid sequence
String s3 = "050043";
// Output: true (sequence: [05, 004, 3] -> [5, 4, 3])
```

## Optimization Opportunities
1. **Early Termination**: Stop when remaining string too short for valid sequence
2. **Number Validation**: Pre-check if descending sequence is mathematically possible
3. **Integer Overflow**: Handle large numbers with BigInteger if needed
4. **Pruning**: Skip when current number is too large to continue descending

## Real-World Applications
- **Data Validation**: Checking if encoded sequences follow descending patterns
- **Pattern Recognition**: Identifying descending numeric sequences in data streams
- **Cryptography**: Validating descending key sequences in certain cipher systems
- **Quality Assurance**: Testing if version numbers follow descending release patterns 