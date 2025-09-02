# String Descending Sequence Splitting

**Source:** Kunal | **Topic:** Backtracking, String Processing | **Difficulty:** Medium

---

## Problem Statement
Given a string of digits, determine if it can be split into a sequence of consecutive descending integers, where each subsequent number is exactly 1 less than the previous number, and the sequence contains at least 2 numbers.

## Intuition/Approach
Use backtracking to try all possible splits of the string:
- Start from the beginning and try different substring lengths to form numbers.
- Validate that each new number is exactly one less than the previous.
- Recursively build the sequence, ensuring the entire string is consumed.
- Backtrack when a split doesn’t lead to a valid sequence.

## Key Observations
- The sequence must have at least two numbers.
- Each number must be exactly one less than the previous (e.g., 5,4,3).
- The entire string must be used without leftover digits.
- Leading zeros are allowed in this context, as they are valid parts of numbers.

## Algorithm Steps
1. Initialize a DFS with an empty list to store the sequence.
2. For each starting index, try extracting numbers of varying lengths.
3. Validate the number:
   - If the list is empty, accept any valid number.
   - Otherwise, ensure the number is exactly one less than the last number in the list.
4. Add the valid number to the list and recurse with the next index.
5. If the end of the string is reached and the list has at least 2 numbers, return true.
6. Backtrack by removing the number and try a different length.
7. Return false if no valid sequence is found.

## Complexity Analysis
- **Time Complexity:** O(n²) - For each position, try up to n substring lengths; string-to-number conversion is O(n).
- **Space Complexity:** O(n) - For the recursion stack and the list storing the sequence.
- **Justification:** The backtracking explores all possible splits, with each position potentially trying n lengths, and string operations are linear.

## Edge Cases Considered
- [x] Single digit string (cannot form sequence of length ≥ 2).
- [x] No valid descending sequence exists.
- [x] Large numbers (handle with long or BigInteger).
- [x] Strings with leading zeros (valid in this context).
- [x] Short strings that cannot form a sequence.

## Solution Code
```java
import java.util.*;

public class Solution {
    private boolean dfs(List<Long> list, String str, int start) {
        if (start == str.length()) {
            return list.size() >= 2;
        }
        long num = 0;
        for (int i = start; i < str.length(); i++) {
            num = num * 10 + str.charAt(i) - '0';
            if (list.size() == 0 || list.get(list.size() - 1) - num == 1) {
                list.add(num);
                if (dfs(list, str, i + 1)) return true;
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

## Alternative Approaches
- **Iterative Approach:** Use nested loops to try all splits, avoiding recursion.
- **Dynamic Programming:** Memoize valid splits to reduce redundant computations.
- **Greedy Validation:** Pre-check if a descending sequence is mathematically possible based on string length and digit sum.

## Personal Notes
- The example usage in the original document had errors (e.g., "1234" is not descending; "050043" interpretation was unclear). Corrected examples to align with the descending sequence requirement.
- Handling leading zeros was a key consideration, as they are valid in this problem.
- The backtracking approach is intuitive but requires careful validation of the descending condition.

---
**Tags:** #backtracking #string_processing #recursion