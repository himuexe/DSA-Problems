# Basic Combinations Generation

## Problem Statement
Generate all possible combinations of k numbers chosen from the range [1, n]. Each combination should contain exactly k unique numbers in ascending order.

## Intuition/Approach
The algorithm uses recursive backtracking to generate combinations systematically:
1. Build combinations incrementally by choosing one number at a time
2. Use start index to maintain ascending order and avoid duplicates
3. When combination reaches size k, add it to results
4. Backtrack by removing last element and trying next option
5. Ensure each number is used at most once per combination

## Key Observations
- **Ascending Order**: Start index ensures combinations are generated in order
- **No Duplicates**: Each number appears at most once in each combination
- **Systematic Generation**: Explores all possible k-sized subsets
- **Efficient Pruning**: Stops when not enough numbers remain to complete combination

## Algorithm Steps
1. **Initialize**: Create empty result list and start DFS
2. **DFS with Backtracking**:
   - **Base Case**: If combination size equals k, add to results
   - **Choice Loop**: For each number from start to n:
     - **Include**: Add number to current combination
     - **Recurse**: Continue with next start index (i+1)
     - **Backtrack**: Remove number and try next option
3. **Return**: All valid k-sized combinations from range [1, n]

## Time & Space Complexity
- **Time Complexity**: O(C(n,k))
  - Generates exactly C(n,k) = n!/(k!(n-k)!) combinations
  - Each combination takes O(k) time to create and add
- **Space Complexity**: O(C(n,k) × k)
  - Stores all C(n,k) combinations, each of size k
  - Recursion depth is at most k

## Edge Cases Considered
- k = 0: Returns single empty combination
- k = n: Returns single combination containing all numbers
- k > n: Returns empty list (impossible)
- n = 1: Returns [1] if k=1, empty list if k>1

## Code Implementation
```java
import java.util.*;
class Solution {
    private void dfs(List<List<Integer>> list, List<Integer> comb, 
                    int n, int k, int start){
        if(comb.size() == k){
            list.add(new ArrayList<>(comb));
            return;
        }
        for(int i = start; i <= n; i++){
            comb.add(i);
            dfs(list, comb, n, k, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, new ArrayList<>(), n, k, 1);
        return list;
    }
}
```

## Example Usage
```java
// Example 1: Choose 2 from 4
int n1 = 4, k1 = 2;
// Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]

// Example 2: Choose 1 from 1
int n2 = 1, k2 = 1;
// Output: [[1]]

// Example 3: Choose 3 from 5
int n3 = 5, k3 = 3;
// Output: [[1,2,3], [1,2,4], [1,2,5], [1,3,4], [1,3,5], [1,4,5], [2,3,4], [2,3,5], [2,4,5], [3,4,5]]
```

## Optimization Opportunities
1. **Pruning Enhancement**: Skip when remaining numbers insufficient to complete combination
2. **Iterative Approach**: Use bit manipulation for better space efficiency
3. **Mathematical Generation**: Use lexicographic ordering algorithms
4. **Memory Optimization**: Generate combinations on-demand instead of storing all

## Real-World Applications
- **Team Selection**: Choosing k team members from n candidates
- **Sampling**: Statistical sampling of k items from population of n
- **Feature Selection**: Choosing k features from n available features in ML
- **Tournament Design**: Creating matchups and group combinations 