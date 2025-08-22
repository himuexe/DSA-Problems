# Maximum Length Unique Character Concatenation

## Problem Statement
Given an array of strings, find the maximum length of a string that can be formed by concatenating some strings from the array such that all characters in the resulting string are unique.

## Intuition/Approach
The algorithm uses backtracking to explore all possible combinations of strings:
1. For each string in the array, decide whether to include it in the concatenation
2. Before including, check if adding the string maintains unique character property
3. Explore both choices: include current string or skip it
4. Track the maximum length achieved across all valid combinations
5. Use character frequency counting to validate uniqueness

## Key Observations
- **Uniqueness Constraint**: All characters in final string must be unique
- **Combination Problem**: Choose subset of strings to maximize length
- **Validation Required**: Check character uniqueness before adding each string
- **Optimization**: Use frequency array for efficient character counting

## Algorithm Steps
1. **Initialize**: Start DFS with empty concatenated string
2. **DFS with String Selection**:
   - **Base Case**: Processed all strings, return current length
   - **Validation**: Check if adding current string maintains uniqueness
   - **Include Choice**: If valid, add string and recurse
   - **Skip Choice**: Always recurse without adding current string
   - **Maximum**: Return maximum of both choices
3. **Return**: Maximum length of valid unique character concatenation

## Time & Space Complexity
- **Time Complexity**: O(2^n × m)
  - 2^n possible combinations of n strings
  - O(m) time to validate uniqueness for each combination (m = average string length)
- **Space Complexity**: O(n × m)
  - Recursion depth at most n
  - String concatenation space proportional to total character count

## Edge Cases Considered
- Empty array: Returns 0
- Single string: Returns string length if all characters unique, 0 otherwise
- No valid combinations: Returns 0 when no subset has unique characters
- All strings invalid: Individual strings with duplicate characters

## Code Implementation
```java
import java.util.*;
class Solution {
    private boolean isUnique(String str){
        int[] arr = new int[26];
        for(int i = 0; i < str.length(); i++){
            arr[str.charAt(i) - 'a']++;
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 1){
                return false;
            }
        }
        return true;
    }

    private int dfs(List<String> arr, String str, int curr){
        if(curr == arr.size()){
            return str.length();
        }
        int left = 0, right = 0;
        String temp = str + arr.get(curr);
        if(isUnique(temp)){
            left = dfs(arr, temp, curr + 1);
        }
        right = dfs(arr, str, curr + 1);
        return Math.max(left, right);
    }
    
    public int maxLength(List<String> arr) {
        return dfs(arr, "", 0);
    }
}
```

## Example Usage
```java
// Example 1: Multiple valid combinations
List<String> arr1 = Arrays.asList("un", "iq", "ue");
// Output: 4 (concatenate "un" + "iq" = "uniq")

// Example 2: Skip invalid strings
List<String> arr2 = Arrays.asList("cha", "r", "act", "ers");
// Output: 6 (concatenate "cha" + "r" + "act" = "charact" is invalid due to 'c')
// Correct: "cha" + "r" = "char" (4) or "act" + "ers" = "acters" (6)

// Example 3: No valid combinations
List<String> arr3 = Arrays.asList("aa", "bb");
// Output: 0 (both strings have duplicate characters)
```

## Optimization Opportunities
1. **Early Pruning**: Pre-filter strings with duplicate characters
2. **Bit Manipulation**: Use bitmask to represent character sets for faster validation
3. **Memoization**: Cache results for repeated subproblems
4. **Greedy Approach**: Sort strings by length and prioritize longer unique strings

## Real-World Applications
- **Password Generation**: Creating secure passwords with unique character requirements
- **DNA Sequencing**: Finding maximum unique subsequence combinations
- **Text Processing**: Combining text fragments while maintaining character diversity
- **Resource Optimization**: Selecting resources to maximize coverage without overlap 