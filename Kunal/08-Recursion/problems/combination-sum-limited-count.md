# Combination Sum III - Limited Count and Range

## Problem Statement
Find all possible combinations of k numbers that add up to number n, given that only numbers 1-9 can be used and each combination should be a unique set of numbers.

## Intuition/Approach
The algorithm uses backtracking to explore combinations with strict constraints:
1. Generate combinations of exactly k unique numbers from range [1, 9]
2. Track remaining sum and required count simultaneously
3. Use start index to ensure ascending order and avoid duplicates
4. Prune early when constraints cannot be satisfied
5. Only numbers 1-9 are available, each used at most once per combination

## Key Observations
- **Fixed Range**: Only digits 1-9 are available
- **Exact Count**: Must use exactly k numbers, no more, no less
- **Unique Elements**: Each number can only be used once per combination
- **Dual Constraints**: Both count and sum must be satisfied simultaneously

## Algorithm Steps
1. **Initialize**: Start backtracking with empty combination
2. **Backtracking Logic**:
   - **Success Base Case**: If size equals k AND remaining sum is 0
   - **Failure Base Cases**: 
     - Size exceeds k, or remaining sum becomes negative/zero prematurely
   - **Choice Loop**: Try each number from start to 9:
     - **Include**: Add number to current combination
     - **Recurse**: Continue with next start index and reduced remaining sum
     - **Backtrack**: Remove number and try next option
3. **Return**: All valid k-sized combinations that sum to n

## Time & Space Complexity
- **Time Complexity**: O(C(9,k))
  - Upper bound is combinations of 9 items taken k at a time
  - Actual complexity lower due to sum constraint pruning
- **Space Complexity**: O(k)
  - Maximum recursion depth is k
  - Each combination stores exactly k elements

## Edge Cases Considered
- Impossible combinations: k > 9 or sum too large/small for k elements
- Minimum sum: k elements from 1,2,...,k = k(k+1)/2
- Maximum sum: k elements from (10-k),...,9 = k(19-k)/2
- Single element: k=1, direct validation if n is in [1,9]

## Code Implementation
```java
import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> current, 
                          int k, int remaining, int start) {
        // Base cases
        if (current.size() == k && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (current.size() >= k || remaining <= 0) {
            return;
        }
        
        // Try each number from start to 9
        for (int i = start; i <= 9; i++) {
            current.add(i);
            backtrack(result, current, k, remaining - i, i + 1);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
```

## Example Usage
```java
// Example 1: Three numbers summing to 7
int k1 = 3, n1 = 7;
// Output: [[1,2,4]]

// Example 2: Three numbers summing to 9
int k2 = 3, n2 = 9;
// Output: [[1,2,6], [1,3,5], [2,3,4]]

// Example 3: Impossible case
int k3 = 4, n3 = 1;
// Output: [] (impossible)
```

## Optimization Opportunities
1. **Early Validation**: Check mathematical bounds before starting backtracking
2. **Pruning Enhancement**: Skip when remaining sum too large for remaining slots
3. **Iterative Approach**: Use bit manipulation for small k values
4. **Mathematical Optimization**: Use combinatorial formulas for specific cases

## Real-World Applications
- **Lottery Systems**: Generating valid number combinations with sum constraints
- **Puzzle Games**: Number placement puzzles with sum and count restrictions
- **Resource Planning**: Selecting exact number of resources within budget limits
- **Cryptographic Applications**: Generating key combinations with specific properties 