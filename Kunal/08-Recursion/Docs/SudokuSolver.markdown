# Sudoku Solver - Advanced Backtracking Algorithm

**Source:** Kunal | **Topic:** Backtracking, Constraint Satisfaction | **Difficulty:** Hard

---

## Problem Statement
Solve a 9×9 Sudoku puzzle by filling empty cells (marked with '.') with digits 1-9, ensuring each row, column, and 3×3 sub-box contains all digits from 1 to 9 exactly once.

## Intuition/Approach
Use backtracking to solve the constraint satisfaction problem:
- Find an empty cell and try digits 1-9.
- Validate each digit against row, column, and 3×3 box constraints.
- Place a valid digit and recurse to the next empty cell.
- Backtrack if no solution is found, trying alternative digits.

## Key Observations
- The puzzle has three constraints: row, column, and 3×3 box uniqueness.
- Backtracking ensures all possibilities are explored systematically.
- A well-formed Sudoku puzzle has exactly one solution.
- Validation before placement improves efficiency.
- The search can be optimized by choosing cells strategically.

## Algorithm Steps
1. Find the next empty cell ('.' in board).
2. If no empty cell exists, return true (puzzle solved).
3. For each digit 1-9:
   - Check if the digit is valid (no conflicts in row, column, or 3×3 box).
   - Place the digit and recurse to solve the remaining puzzle.
   - If recursion succeeds, return true.
   - If recursion fails, backtrack by resetting the cell to '.'.
4. If no digit works, return false to trigger backtracking.

## Complexity Analysis
- **Time Complexity:** O(9^(n×n)) - Up to 9 choices per empty cell (n×n = 81 max).
- **Space Complexity:** O(n×n) - For the board and recursion stack (up to 81 levels).
- **Justification:** Exponential time due to trying all possibilities; space is dominated by the board and recursion stack.

## Edge Cases Considered
- [x] Already solved puzzle (no empty cells).
- [x] Unsolvable puzzle (invalid constraints).
- [x] Minimal clue puzzles (17+ clues required).
- [x] Multiple empty cells in the same region.
- [x] Invalid initial board state.

## Solution Code
```java
public class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
        }
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
}
```

## Alternative Approaches
- **Constraint Propagation:** Pre-compute valid digits per cell to reduce backtracking.
- **Most Constrained Variable:** Choose cells with the fewest possible digits first.
- **Bit Manipulation:** Use bitmasks for faster constraint checking.
- **Iterative Backtracking:** Use a stack to avoid recursion overhead.

## Personal Notes
- The constraint validation logic is critical and must be thorough to avoid invalid solutions.
- The backtracking approach is intuitive but can be optimized significantly for real-world use.
- This problem is a great example of applying backtracking to complex constraints.

---
**Tags:** #backtracking #constraint_satisfaction #sudoku #recursion