# Graph M-Coloring Problem

**Difficulty:** Hard  
**Category:** Backtracking + Graph Theory  
**Source:** Kunal Recursion (Advanced)

## Problem Statement
Given an undirected graph represented as an adjacency matrix and M colors, determine if the graph can be colored using at most M colors such that no two adjacent vertices have the same color.

## Intuition/Approach
This is a classic constraint satisfaction problem solved using backtracking. We assign colors to vertices one by one, ensuring the coloring constraint is never violated.

**Algorithm Steps:**
1. Start with vertex 0 and try assigning colors 1 to M
2. For each color assignment, check if it's safe (no adjacent vertex has the same color)
3. If safe, recursively color the next vertex
4. If successful, return true; otherwise, backtrack and try the next color
5. If no color works for current vertex, return false

## Key Observations
- **Safety Check:** A color is safe for a vertex if none of its adjacent vertices have the same color
- **Backtracking:** When a color assignment leads to failure, we backtrack and try the next color
- **Graph Representation:** Boolean adjacency matrix efficiently represents graph connections
- **Color Assignment:** Colors are represented as integers from 1 to M (0 means uncolored)

## Time & Space Complexity
- **Time Complexity:** O(M^V) - where V is number of vertices, trying M colors for each vertex
- **Space Complexity:** O(V) - for color array and recursion stack
- **Best Case:** O(V) - when graph can be easily colored
- **Worst Case:** O(M^V) - when extensive backtracking is required

## Edge Cases Considered
- Complete graph (requires V colors for V vertices)
- Empty graph (can be colored with 1 color)
- Linear graph (requires 2 colors)
- Single vertex graph
- Insufficient colors (M < chromatic number)

## Solution Code
```java
public class MColouring {
    
    public boolean isSafe(boolean[][] graph, int[] color, int currColor, int r) {
        // Check if current color conflicts with any adjacent vertex
        for (int c = 0; c < graph[0].length; c++) {
            if (graph[r][c] == true) {  // Adjacent vertex
                if (color[c] == currColor) return false;  // Conflict found
            }
        }
        return true;  // Safe to use this color
    }
    
    public boolean dfs(boolean[][] graph, int[] color, int m, int r) {
        if (r == graph.length) return true;  // All vertices colored successfully
        
        // Try all colors from 1 to m
        for (int i = 1; i <= m; i++) {
            if (isSafe(graph, color, i, r)) {
                color[r] = i;  // Assign color
                if (dfs(graph, color, m, r + 1)) return true;  // Recurse
                color[r] = 0;  // Backtrack
            }
        }
        return false;  // No valid coloring found
    }
    
    public boolean graphColouring(boolean graph[][], int m, int n) {
        int[] color = new int[n];  // Color array for vertices
        return dfs(graph, color, m, 0);  // Start DFS from vertex 0
    }
}
```

## Algorithm Analysis
- **Constraint Satisfaction:** Classic CSP problem with adjacency constraints
- **Backtracking Efficiency:** Prunes invalid branches early through safety checking
- **Graph Theory Application:** Relates to chromatic number and graph coloring theory
- **Real-world Applications:** Map coloring, register allocation, scheduling problems 