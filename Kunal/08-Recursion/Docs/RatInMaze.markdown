# Rat in a Maze - All Paths

**Source:** Kunal Recursion (Advanced) | **Topic:** Backtracking, Pathfinding | **Difficulty:** Medium-Hard

---

## Problem Statement
Given an N×N maze where 1 represents an open path and 0 represents a blocked cell, find all possible paths from the top-left corner (0,0) to the bottom-right corner (N-1, N-1). The rat can move in four directions: Down (D), Up (U), Right (R), Left (L).

## Intuition/Approach
This problem is solved using depth-first search (DFS) with backtracking to explore all possible paths. The approach involves:
- Starting from (0,0) and using DFS to traverse all valid paths.
- Maintaining a visited array to prevent cycles.
- Building path strings (using D, U, R, L) incrementally.
- Backtracking by unmarking visited cells to explore alternative paths.

## Key Observations
- Paths are encoded as strings (D, U, R, L) for simplicity.
- A visited array prevents revisiting cells in the same path, avoiding infinite loops.
- Backtracking ensures all possible paths are explored without interference.
- Boundary and validity checks are critical to avoid index errors.
- The destination (N-1, N-1) is reached when a valid path is found.

## Algorithm Steps
1. Initialize DFS from (0,0) with an empty path string.
2. Check if the current cell is valid (within bounds, open, not visited).
3. If the destination (N-1, N-1) is reached, add the current path to results.
4. Mark the current cell as visited.
5. Recursively explore all four directions (Down, Up, Right, Left) with updated path.
6. Backtrack by unmarking the current cell to allow other paths to use it.
7. Return the list of all valid paths.

## Complexity Analysis
- **Time Complexity:** O(4^(N²)) - In the worst case (all cells open), each cell can branch in four directions, leading to an exponential number of paths.
- **Space Complexity:** O(N²) - For the visited array and recursion stack depth.
- **Justification:** The exponential time complexity arises from exploring all possible paths in a grid, while space is dominated by the visited array and recursive call stack. Path storage adds O(P×L), where P is the number of paths and L is the average path length.

## Edge Cases Considered
- [x] Starting cell is blocked (return empty list).
- [x] No path exists to the destination.
- [x] Single cell maze (start == destination).
- [x] Maze with all cells blocked except a single path.
- [x] Multiple valid paths of varying lengths.

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

## Alternative Approaches
- **BFS-based Approach:** Use breadth-first search to find all paths, though less common due to higher memory usage.
- **Dynamic Programming:** Memoize paths to reduce redundant computations, though this is complex for all-paths problems.
- **Iterative DFS:** Use a stack to simulate recursion, potentially improving space efficiency for large mazes.

## Personal Notes
- The backtracking step (unmarking visited cells) was initially tricky to understand but is critical for exploring all paths.
- Ensuring boundary checks before accessing the matrix avoids runtime errors.
- This problem is a great example of combining DFS with state management (visited array and path string).

---
**Tags:** #backtracking #pathfinding #dfs #recursion