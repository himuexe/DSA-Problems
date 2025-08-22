# Combination Sum - Unlimited Element Reuse

## Problem Statement
Find all unique combinations in candidates array where the candidate numbers sum to target. The same number may be chosen from candidates unlimited number of times.

## Intuition/Approach
The algorithm uses backtracking with DFS to explore all possible combinations:
1. For each element, decide whether to include it in the current combination
2. If included, continue searching with the same element (unlimited reuse)
3. Use running sum to track progress toward target
4. Backtrack when sum exceeds target or target is reached
5. Use start index to avoid duplicate combinations

## Key Observations
- **Unlimited Reuse**: Same element can be used multiple times in one combination
- **No Duplicates**: Start index prevents generating duplicate combinations
- **Pruning**: Early termination when sum exceeds target
- **Complete Search**: Explores all valid combination paths

## Algorithm Steps
1. **Initialize**: Create empty result list and start DFS
2. **DFS with Backtracking**:
   - **Base Cases**: 
     - If sum equals target: add combination to results
     - If sum exceeds target: return (pruning)
   - **Choice Loop**: For each element from start index to end:
     - **Include**: Add element to current combination
     - **Recurse**: Continue with same start index (allowing reuse)
     - **Backtrack**: Remove element and try next option
3. **Return**: All valid combinations that sum to target

## Time & Space Complexity
- **Time Complexity**: O(N^(T/M))
  - N = number of candidates
  - T = target value
  - M = minimal value among candidates
  - In worst case, explores all possible combinations
- **Space Complexity**: O(T/M)
  - Maximum recursion depth is T/M (using smallest element)
  - Space for storing combinations varies based on solutions

## Edge Cases Considered
- Target is 0: Returns empty combination
- No valid combinations: Returns empty list
- Single element solutions: Handles direct matches
- Large target values: Efficient pruning prevents excessive exploration

## Code Implementation
```java
import java.util.*;
class Solution {
    private void dfs(int[] nums, List<List<Integer>> list, List<Integer> comb, 
                    int target, int sum, int start){
        if(sum == target){
            list.add(new ArrayList<>(comb));
            return;
        }
        else if(sum > target){
            return;
        }
        for(int i = start; i < nums.length; i++){
            comb.add(nums[i]);
            dfs(nums, list, comb, target, sum + nums[i], i);
            comb.remove(comb.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(candidates, list, new ArrayList<>(), target, 0, 0);
        return list;
    }
}
```

## Example Usage
```java
// Example 1: Multiple solutions
int[] candidates1 = {2, 3, 6, 7};
int target1 = 7;
// Output: [[2,2,3], [7]]

// Example 2: Unlimited reuse
int[] candidates2 = {2, 3, 5};
int target2 = 8;
// Output: [[2,2,2,2], [2,3,3], [3,5]]
```

## Optimization Opportunities
1. **Array Sorting**: Sort candidates to enable early termination in loops
2. **Target Tracking**: Pass remaining target instead of sum for cleaner logic
3. **Memoization**: Cache results for repeated subproblems (though limited benefit)
4. **Iterative Approach**: Use stack-based iteration for memory efficiency

## Real-World Applications
- **Coin Change Problems**: Finding ways to make change with unlimited coin supply
- **Resource Allocation**: Distributing unlimited resources to reach target capacity
- **Recipe Optimization**: Finding ingredient combinations to reach nutritional targets
- **Financial Planning**: Investment combinations to reach specific portfolio values 