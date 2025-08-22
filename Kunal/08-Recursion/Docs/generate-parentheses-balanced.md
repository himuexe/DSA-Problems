# Generate Balanced Parentheses

## Problem Statement
Generate all combinations of well-formed parentheses for n pairs of parentheses. Each valid combination must have exactly n opening and n closing parentheses, properly balanced.

## Intuition/Approach
The algorithm uses recursive backtracking with balance tracking:
1. Build parentheses string character by character
2. Track count of opening and closing parentheses used
3. Add opening parenthesis when count < n
4. Add closing parenthesis when count < opening count (maintains balance)
5. When string length reaches 2n, add valid combination to results

## Key Observations
- **Balance Constraint**: Closing parentheses count never exceeds opening count
- **Length Constraint**: Final string must have exactly 2n characters
- **Greedy Valid Construction**: Only add closing when it maintains validity
- **Complete Generation**: Explores all valid parentheses arrangements

## Algorithm Steps
1. **Initialize**: Start DFS with empty string and zero counts
2. **DFS with Balance Tracking**:
   - **Base Case**: If string length equals 2n, add to results
   - **Add Opening**: If open count < n, add '(' and recurse
   - **Add Closing**: If close count < open count, add ')' and recurse
3. **Return**: All valid balanced parentheses combinations

## Time & Space Complexity
- **Time Complexity**: O(4^n / √n)
  - This is the nth Catalan number: C_n = (2n)!/(n!(n+1)!)
  - Approximately 4^n / (√π × n^(3/2))
- **Space Complexity**: O(4^n / √n)
  - Stores all valid combinations
  - Recursion depth is at most 2n

## Edge Cases Considered
- n = 0: Returns empty list or single empty string
- n = 1: Returns single combination "()"
- Large n: Exponential growth in number of valid combinations

## Code Implementation
```java
import java.util.*;
class Solution {
    private void dfs(List<String> list, int max, String str, int open, int close){
        if(max * 2 == str.length()){
            list.add(str);
            return;
        }
        if(open < max){
            dfs(list, max, str + "(", open + 1, close);
        }
        if(close < open){
            dfs(list, max, str + ")", open, close + 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        dfs(list, n, "", 0, 0);
        return list;
    }
}
```

## Example Usage
```java
// Example 1: 3 pairs
int n1 = 3;
// Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]

// Example 2: 1 pair
int n2 = 1;
// Output: ["()"]

// Example 3: 2 pairs
int n3 = 2;
// Output: ["(())", "()()"]
```

## Optimization Opportunities
1. **StringBuilder**: Use StringBuilder instead of string concatenation
2. **Iterative Approach**: Use stack-based iteration to avoid recursion overhead
3. **Mathematical Generation**: Use combinatorial algorithms for direct generation
4. **Memoization**: Cache intermediate results (though limited benefit for this problem)

## Real-World Applications
- **Code Generation**: Generating balanced bracket structures in programming
- **Mathematical Expressions**: Creating valid mathematical expression templates
- **Tree Structures**: Representing binary tree structures with parentheses notation
- **Compiler Design**: Parsing and validating nested structures in language design 