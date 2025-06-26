# Minimum Domino Rotations

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  
**Date Solved:** June 26, 2025 | **Revision Due:** July 3, 2025 | **Status:** Solved

---

## Problem Statement
You have two rows of dominoes. Each domino has two values (top and bottom). Return the minimum number of rotations needed to make all values in one row the same, or -1 if impossible.

## Intuition/Approach
Try each possible target value (1-6) and check if it's possible to make one row uniform. For each target, count rotations needed for both top and bottom rows, return minimum possible rotations.

## Key Observations
- Only 6 possible target values (1-6 on dominoes)
- For each target, check if it appears on every domino (top or bottom)
- Count rotations needed to make top row uniform vs bottom row uniform
- If target doesn't appear on any domino, it's impossible (-1)
- Return minimum rotations across all valid targets

## Algorithm Steps
1. For each possible value (1-6):
   - Call helper function to calculate rotations needed
   - If valid rotations found, update minimum answer
2. Helper function for each target value:
   - Count rotations needed for top row to be uniform
   - Count rotations needed for bottom row to be uniform
   - If target missing from any domino, return -1
   - Return minimum of both rotation counts
3. Return overall minimum or -1 if no solution

## Complexity Analysis
- **Time Complexity:** O(n) - constant factor 6 for trying each value
- **Space Complexity:** O(1) - only using constant extra variables
- **Justification:** Fixed iterations over dominoes for each of 6 values

## Edge Cases Considered
- [x] All dominoes already uniform (0 rotations)
- [x] Impossible configuration (return -1)
- [x] Single domino (always possible)
- [x] All dominoes have same top/bottom values
- [x] Mixed configurations requiring rotations

## Solution Code

```java
// Language: Java
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = -1;
        for(int i = 1; i <= 6; i++){
            int currAns = findRotations(tops, bottoms, i);
            if(currAns != -1 && (ans == -1 || ans > currAns)){
                ans = currAns;
            }
        }
        return ans;
    }
    
    public int findRotations(int[] tops, int[] bottoms, int val){
        int ansTop = 0;
        int ansBottom = 0;
        for(int i = 0; i < tops.length; i++){
            if(tops[i] != val && bottoms[i] != val){
                return -1;
            }
            if(tops[i] != val){
                ansTop++;
            }
            if(bottoms[i] != val){
                ansBottom++;
            }
        }
        return Math.min(ansTop, ansBottom);
    }
}
```

## Alternative Approaches
- **Greedy Single Pass:** Check only values present in first domino
- **Dynamic Programming:** More complex state tracking (overkill for this problem)
- **Constraint Satisfaction:** Model as CSP problem

## Related Problems
- **AC:** [Array Transformation, Rotation Problems]
- **Kunal:** [Array Operations, Counting Problems]
- **LeetCode:** [1007. Minimum Domino Rotations For Equal Row, 189. Rotate Array]

## Personal Notes
Interesting problem combining enumeration with validation. The key insight is limiting search space to 6 possible values and checking feasibility for each. Demonstrates importance of constraint analysis in problem solving.

## Revision History
- **First Solve:** June 26, 2025 - Implemented enumeration with helper function approach

---
**Tags:** #enumeration #arrays #rotation #validation #optimization 