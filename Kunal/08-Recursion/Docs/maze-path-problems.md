# Maze Problems - Path Counting and Generation

**Source:** Kunal | **Topic:** Recursion/Combinatorics | **Difficulty:** Medium  

---

## Problem Statement
Two related maze problems:
1. Count paths from (r,c) to (1,1) with only down/right moves.
2. Generate all such paths as strings of moves.

## Intuition/Approach
**Recursive Path Exploration:**
1. **Base Case:** When reaching destination (1,1), count as 1 path or add path string
2. **Movement Options:** From any position, can move down (r-1) or right (c-1)
3. **Path Counting:** Sum all possible paths from current position
4. **Path Generation:** Concatenate direction strings ('D' for down, 'R' for right)

**Key Insight:** Both problems use similar recursive structure but different return types - integer count vs. string list.

## Key Observations
- **Coordinate System:** Uses (row, col) with (1,1) as destination
- **Movement Direction:** Down decreases row, Right decreases column
- **Path Representation:** 'D' for down movement, 'R' for right movement
- **Termination:** Base case when both coordinates reach 1
- **Mathematical Relation:** Count equals C(r+c-2, r-1) combinatorial formula

## Algorithm Steps
### Path Counting (count method):
1. **Base Case:** If r==1 OR c==1, return 1 (edge reached)
2. **Recursive Case:** return count(r-1,c) + count(r,c-1)

### Path Generation (path method):
1. **Base Case:** If r==1 AND c==1, print path string and return
2. **Down Movement:** If r>1, recurse with path+'D' and r-1
3. **Right Movement:** If c>1, recurse with path+'R' and c-1

## Complexity Analysis
- **Time Complexity:** O(2^(r+c)) (naive)
- **Space Complexity:** O(r+c)
- **Justification:** Binary branching per step; depth ~ r+c.

## Edge Cases Considered
- [x] Single cell (1,1) - one path of length 0
- [x] Single row (r=1) - only right movements
- [x] Single column (c=1) - only down movements
- [x] Small grids (2×2, 3×3)
- [x] Large grids (exponential growth)

## Solution Code
```java
public class Maze {
    
    // Path counting method
    public static int count(int r, int c) {
        if (r == 1 || c == 1) {
            return 1;  // Reached edge, one path remaining
        }
        
        int left = count(r - 1, c);  // Move down
        int right = count(r, c - 1); // Move right
        
        return left + right;  // Total paths
    }
    
    // Path generation method
    public static void path(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);  // Reached destination
            return;
        }
        
        if (r > 1) {
            path(p + 'D', r - 1, c);  // Move down
        }
        
        if (c > 1) {
            path(p + 'R', r, c - 1);  // Move right
        }
    }
}
```

## Example Walkthrough (3×3 Grid)
**Input:** count(3, 3) and path("", 3, 3)

**Path Counting Tree:**
```
count(3,3)
├─ count(2,3) = 3
│  ├─ count(1,3) = 1
│  └─ count(2,2) = 2
└─ count(3,2) = 3
   ├─ count(2,2) = 2  
   └─ count(3,1) = 1
Total: 6 paths
```

**Path Generation Output:**
```
DDRR
DRDR
DRRD
RDDR
RDRD
RRDD
```

**Path Explanation:**
- **DDRR:** Down, Down, Right, Right
- **DRDR:** Down, Right, Down, Right
- etc.

## Mathematical Verification
For 3×3 grid: C(3+3-2, 3-1) = C(4,2) = 4!/(2!×2!) = 6 ✓

## Optimization Opportunities
1. **Memoization:** Store computed results for (r,c) pairs
2. **Dynamic Programming:** Bottom-up table construction
3. **Mathematical Formula:** Direct combinatorial calculation
4. **Path Return:** Return ArrayList instead of printing

## Key Learning Points
- **Recursive Decomposition:** Breaking path problems into subproblems
- **Base Case Design:** Proper termination conditions
- **State Representation:** Using coordinates and path strings
- **Combinatorial Understanding:** Connection to mathematical formulas

## Applications
- **Robot Navigation:** Grid movement optimization
- **Dynamic Programming:** Foundation for path optimization problems
- **Game Development:** Character movement in grid-based games
- **Algorithm Design:** Understanding recursive problem decomposition

---

---
**Tags:** #recursion #paths #combinatorics