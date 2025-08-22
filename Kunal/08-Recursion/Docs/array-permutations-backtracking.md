# Array Permutations Generation - Backtracking

## Problem Statement
Generate all possible permutations of a given integer array using recursive backtracking approach.

## Intuition/Approach
The algorithm uses depth-first search (DFS) with backtracking to generate all permutations. It builds permutations incrementally by:
1. For each position, try all unused elements from the original array
2. Recursively generate permutations for the remaining positions
3. Backtrack by removing the current element and trying the next option
4. When permutation reaches target length, add it to the result

## Key Observations
- **Backtracking Pattern**: Uses the classic include-exclude recursive pattern
- **State Management**: Maintains current permutation and checks for element usage
- **Complete Search**: Explores all possible arrangements systematically
- **Duplicate Handling**: Sorts array initially for consistent ordering

## Algorithm Steps
1. **Initialize**: Sort input array and create empty result list
2. **DFS Exploration**: For each recursive call:
   - **Base Case**: If permutation size equals array length, add to results
   - **Choice Loop**: Try each unused element from original array
   - **Include**: Add element to current permutation
   - **Recurse**: Continue building permutation with remaining positions
   - **Backtrack**: Remove element and try next option
3. **Return**: Complete list of all permutations

## Time & Space Complexity
- **Time Complexity**: O(n! × n)
  - n! permutations to generate
  - O(n) time to check contains() and copy permutation
- **Space Complexity**: O(n! × n)
  - O(n! × n) for storing all permutations
  - O(n) for recursion stack depth

## Edge Cases Considered
- Empty array: Returns empty list
- Single element: Returns single permutation
- Duplicate elements: Sorted array provides consistent ordering
- Large arrays: Factorial complexity grows rapidly

## Code Implementation
```java
import java.util.*;
class Solution {
    private void dfs(int[] nums, List<List<Integer>> list, List<Integer> perm){
        if(perm.size() == nums.length){
            list.add(new ArrayList<>(perm));
        }
        for(int i = 0; i < nums.length; i++){
            if(perm.contains(nums[i])) continue;
            perm.add(nums[i]);
            dfs(nums, list, perm);
            perm.remove(perm.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        dfs(nums, list, new ArrayList<>());
        return list;
    }
}
```

## Example Usage
```java
// Example 1: Basic permutations
int[] nums1 = {1, 2, 3};
// Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

// Example 2: Two elements
int[] nums2 = {0, 1};
// Output: [[0,1], [1,0]]
```

## Optimization Opportunities
1. **Boolean Array**: Use visited boolean array instead of contains() check for O(1) lookup
2. **Iterative Approach**: Use iterative methods for better space efficiency
3. **In-place Generation**: Generate permutations in lexicographic order without extra space
4. **Early Termination**: Add conditions to stop early if specific permutations are needed

## Real-World Applications
- **Combinatorial Problems**: Arrangement generation for optimization problems
- **Algorithm Design**: Testing all possible orderings in search algorithms
- **Puzzle Solving**: Generating solutions for arrangement-based puzzles
- **Cryptography**: Permutation-based cipher analysis and key generation 