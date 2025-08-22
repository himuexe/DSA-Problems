# IP Address Restoration - Backtracking

## Problem Statement
Given a string containing only digits, restore all possible valid IP addresses by inserting dots. A valid IP address consists of exactly four integers separated by dots, where each integer is between 0 and 255 and cannot have leading zeros (except for the number 0 itself).

## Intuition/Approach
The algorithm uses backtracking to systematically try all possible ways to split the string into 4 segments:
1. For each segment, try all possible lengths (1, 2, or 3 digits)
2. Validate each segment to ensure it forms a valid IP octet (0-255, no leading zeros)
3. When 4 valid segments are found and entire string is consumed, add to results
4. Backtrack by trying different segment lengths and positions

## Key Observations
- **Segment Count**: Must have exactly 4 segments for valid IP address
- **Range Validation**: Each segment must be 0-255
- **Leading Zero Rule**: No leading zeros except for single digit "0"
- **Complete Consumption**: All digits must be used, no leftovers allowed

## Algorithm Steps
1. **Initialize**: Start DFS with empty path and segment count 0
2. **DFS with Segment Building**:
   - **Success Base Case**: 4 segments created and string fully consumed
   - **Try Different Lengths**: For each position, try 1, 2, and 3 digit segments
   - **Validation**: Check if current segment forms valid IP octet
   - **Recurse**: Continue with remaining string and incremented segment count
   - **Backtrack**: Try next segment length if current doesn't lead to solution
3. **Return**: All valid IP address combinations

## Time & Space Complexity
- **Time Complexity**: O(3^4) = O(81)
  - At most 3 choices for each of 4 segments
  - String validation is O(1) for each segment
- **Space Complexity**: O(1)
  - Recursion depth is at most 4 (number of segments)
  - Output space depends on number of valid solutions

## Edge Cases Considered
- String too short: Less than 4 digits cannot form valid IP
- String too long: More than 12 digits cannot form valid IP
- Leading zeros: "01" is invalid, but "0" is valid
- Out of range: Numbers > 255 are invalid

## Code Implementation
```java
import java.util.*;
class Solution {
    private boolean isValid(String str){
        if(str.isEmpty() || 
           str.length() > 3 || 
           (str.charAt(0) == '0' && str.length() > 1) ||
           Integer.parseInt(str) > 255){
            return false;
        }
        return true;
    }

    private void dfs(List<String> list, String str, String path, int seg, int start){
        if(seg == 4){
            if(start == str.length()){
                list.add(path.substring(0, path.length() - 1));
                return;
            }
        }
        for(int i = start; i < str.length() && i < start + 3; i++){
            String curr = str.substring(start, i + 1);
            if(isValid(curr)){
                dfs(list, str, path + curr + ".", seg + 1, i + 1);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        dfs(list, s, "", 0, 0);
        return list;
    }
}
```

## Example Usage
```java
// Example 1: Multiple valid IPs
String s1 = "25525511135";
// Output: ["255.255.11.135", "255.255.111.35"]

// Example 2: Single valid IP
String s2 = "0000";
// Output: ["0.0.0.0"]

// Example 3: No valid IPs
String s3 = "1111";
// Output: ["1.1.1.1"]
```

## Optimization Opportunities
1. **Early Pruning**: Check string length bounds before starting DFS
2. **Integer Parsing**: Pre-validate without parsing to integer
3. **StringBuilder**: Use StringBuilder for path building
4. **Iterative Approach**: Use loops instead of recursion for better space efficiency

## Real-World Applications
- **Network Configuration**: Validating and parsing IP address inputs
- **Log Analysis**: Extracting IP addresses from compressed log formats
- **Data Recovery**: Reconstructing network data from corrupted streams
- **Security Analysis**: Parsing potential IP addresses from threat intelligence data 