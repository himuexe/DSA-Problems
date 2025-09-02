# Graph M-Coloring Problem

**Source:** Kunal | **Topic:** Backtracking/Graphs | **Difficulty:** Hard  

---

## Problem Statement
Given an undirected graph (adjacency matrix) and an integer M, determine if the graph can be colored with at most M colors so that adjacent vertices have different colors.

## Intuition/Approach
- Assign colors vertex-by-vertex using backtracking.
- For each vertex, try all colors 1..M; place a color only if it is safe.
- Recurse to the next vertex; backtrack if no color works.

## Key Observations
- Safety: no neighbor shares the same color.
- Colors represented as integers; 0 means uncolored.
- Exponential worst-case, but pruning via safety check helps.

## Algorithm Steps
1. If all vertices are colored, return true.
2. For color in 1..M:
   - If safe for current vertex, assign and recurse to next.
   - If recursion succeeds, return true; else reset and try next color.
3. If no color works, return false.

## Complexity Analysis
- **Time Complexity:** O(M^V)
- **Space Complexity:** O(V)
- **Justification:** Try M colors for each of V vertices; recursion stack/arrays O(V).

## Edge Cases Considered
- [x] Complete graph
- [x] Empty graph
- [x] Path (2-colorable)
- [x] Single vertex
- [x] M less than chromatic number

## Solution Code

```java
public class MColouring {
    private boolean isSafe(boolean[][] graph, int[] color, int currColor, int r) {
        for (int c = 0; c < graph[0].length; c++) {
            if (graph[r][c] && color[c] == currColor) return false;
        }
        return true;
    }

    private boolean dfs(boolean[][] graph, int[] color, int m, int r) {
        if (r == graph.length) return true;
        for (int i = 1; i <= m; i++) {
            if (isSafe(graph, color, i, r)) {
                color[r] = i;
                if (dfs(graph, color, m, r + 1)) return true;
                color[r] = 0;
            }
        }
        return false;
    }

    public boolean graphColouring(boolean[][] graph, int m) {
        int[] color = new int[graph.length];
        return dfs(graph, color, m, 0);
    }
}
```

## Alternative Approaches
- Heuristics: order vertices by degree; try most constrained first.
- DSATUR or greedy coloring with backtracking fallback.

## Personal Notes
- For large graphs, combine backtracking with heuristics or use ILP/CP solvers.

---
**Tags:** #backtracking #graphs #coloring