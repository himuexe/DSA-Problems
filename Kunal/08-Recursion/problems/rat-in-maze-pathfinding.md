# Rat in a Maze - All Paths

**Difficulty:** Medium-Hard  
**Category:** Backtracking + Pathfinding  
**Source:** Kunal Recursion (Advanced)

## Problem Statement
Given an N×N maze where 1 represents an open path and 0 represents a blocked cell, find all possible paths from the top-left corner (0,0) to the bottom-right corner (N-1, N-1). The rat can move in four directions: Down (D), Up (U), Right (R), Left (L).

## Intuition/Approach
This is a classic pathfinding problem solved using DFS with backtracking. We explore all possible paths while maintaining a visited array to avoid cycles, and backtrack when we hit dead ends.

**Algorithm Steps:**
1. Start DFS from (0,0) with empty path string
2. Check boundary conditions and cell validity (open cell, not visited)
3. If destination reached, add current path to results
4. Mark current cell as visited and explore all 4 directions
5. Recursively call DFS for each valid direction with updated path
6. Backtrack by unmarking current cell as visited

## Key Observations
- **Direction Encoding:** Paths are encoded as strings using D, U, R, L characters
- **Visited Array:** Prevents cycles and infinite loops during exploration
- **Backtracking:** Essential for exploring all possible paths without interference
- **Boundary Checking:** Ensures we don't access invalid array indices
- **Path Building:** Current path is built incrementally during DFS traversal

## Time & Space Complexity
- **Time Complexity:** O(4^(N²)) - worst case when all cells are open and we explore all paths
- **Space Complexity:** O(N²) - for visited array and recursion stack depth
- **Practical Complexity:** Often much better due to blocked cells and pruning
- **Path Storage:** Additional O(P×L) where P is number of paths and L is average path length

## Edge Cases Considered
- Starting cell is blocked (return empty list)
- No path exists to destination
- Single cell maze (start == destination)
- Maze with all cells blocked except path
- Multiple valid paths of different lengths

## Solution Code
```java
import java.util.ArrayList;

public class RatInMaze {
    
    public static void dfs(int[][] matrix, boolean[][] visited, 
                          ArrayList<String> res, int n, int r, int c, String currPath) {
        // Boundary and validity checks
        if (r < 0 || r >= n || c < 0 || c >= n || 
            matrix[r][c] == 0 || visited[r][c]) return;
        
        // Destination reached
        if (r == n - 1 && c == n - 1) {
            res.add(currPath);
            return;
        }
        
        // Mark current cell as visited
        visited[r][c] = true;
        
        // Explore all 4 directions
        dfs(matrix, visited, res, n, r + 1, c, currPath + "D");  // Down
        dfs(matrix, visited, res, n, r - 1, c, currPath + "U");  // Up
        dfs(matrix, visited, res, n, r, c + 1, currPath + "R");  // Right
        dfs(matrix, visited, res, n, r, c - 1, currPath + "L");  // Left
        
        // Backtrack - unmark current cell
        visited[r][c] = false;
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) {
        boolean[][] visited = new boolean[n][n];
        ArrayList<String> res = new ArrayList<>();
        dfs(m, visited, res, n, 0, 0, "");
        return res;
    }
}
```

## Algorithm Analysis
- **Pathfinding Strategy:** Exhaustive DFS exploration with backtracking
- **Cycle Prevention:** Visited array ensures no cell is revisited in current path
- **Path Encoding:** String representation allows easy path reconstruction
- **Complete Solution:** Finds ALL possible paths, not just one optimal path
- **Classic Problem:** Foundation for more complex maze and pathfinding algorithms 