# Partition K Equal Sum Subsets

## Problem Statement
Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

## Intuition/Approach
The algorithm uses backtracking to systematically try to build k subsets with equal sums:
1. Calculate total sum and check if divisible by k
2. Find target sum for each subset (total_sum / k)
3. Use backtracking to try assigning each number to different subsets
4. Track visited elements to avoid reuse
5. When one subset reaches target sum, start building the next subset

## Key Observations
- **Divisibility Check**: Total sum must be divisible by k
- **Target Sum**: Each subset must sum to exactly (total_sum / k)
- **Complete Partitioning**: Every element must be used exactly once
- **Optimization**: Process elements in reverse order for better pruning

## Algorithm Steps
1. **Preprocessing**: 
   - Calculate total sum and validate divisibility by k
   - Calculate target sum for each subset
2. **Backtracking Logic**:
   - **Success Base Case**: All k subsets successfully created
   - **Subset Completion**: When current subset reaches target sum, start next subset
   - **Element Choice**: Try each unvisited element for current subset
   - **Pruning**: Skip elements that exceed remaining sum or create duplicates
   - **Backtrack**: Unmark element and try next option
3. **Return**: True if valid partitioning exists, false otherwise

## Time & Space Complexity
- **Time Complexity**: O(k × 2^n)
  - In worst case, explores all possible subsets for each of k partitions
  - Pruning significantly reduces actual complexity
- **Space Complexity**: O(n)
  - Visited boolean array of size n
  - Recursion depth at most n

## Edge Cases Considered
- k = 1: Always possible if array is non-empty
- k > n: Impossible (more subsets than elements)
- Sum not divisible by k: Immediately return false
- Large elements: Element larger than target sum makes partitioning impossible

## Code Implementation
```java
class Solution {
    private boolean dfs(int[] nums, boolean[] visited, int k, int targetSum, 
                       int currSum, int index){
        if(k == 0) return true;
        if(targetSum == currSum){
            return dfs(nums, visited, k - 1, targetSum, 0, nums.length - 1);
        }
        for(int i = index; i >= 0; i--){
            if(visited[i]) continue;
            if(i + 1 < nums.length && nums[i] == nums[i + 1] && !visited[i + 1]) continue;
            if(currSum + nums[i] > targetSum) continue;
            visited[i] = true;
            if(dfs(nums, visited, k, targetSum, currSum + nums[i], i - 1)) return true;
            visited[i] = false;
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % k != 0) return false;
        int targetSum = sum / k;
        return dfs(nums, new boolean[nums.length], k, targetSum, 0, nums.length - 1);
    }
}
```

## Example Usage
```java
// Example 1: Possible partition
int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
int k1 = 4;
// Output: true (subsets: [5], [1, 4], [2, 3], [2, 3])

// Example 2: Impossible partition
int[] nums2 = {1, 2, 3, 4};
int k2 = 3;
// Output: false

// Example 3: Edge case
int[] nums3 = {1, 1, 1, 1};
int k3 = 2;
// Output: true (subsets: [1, 1], [1, 1])
```

## Optimization Opportunities
1. **Array Sorting**: Sort array in descending order for better pruning
2. **Duplicate Handling**: More efficient duplicate element skipping
3. **Memoization**: Cache states to avoid repeated computations
4. **Bit Manipulation**: Use bitmask to represent visited states more efficiently

## Real-World Applications
- **Load Balancing**: Distributing tasks equally among k servers
- **Resource Allocation**: Dividing resources into equal groups
- **Team Formation**: Creating balanced teams with equal total skill points
- **Scheduling**: Partitioning jobs into equal-duration time slots 