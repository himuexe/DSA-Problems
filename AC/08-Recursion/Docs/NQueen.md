# N-Queens - Classic Backtracking Problem

**Source:** AC | **Topic:** Backtracking | **Difficulty:** Advanced  

---

## Problem Statement
Place N queens on an N×N chessboard so that no two queens attack each other (no same row, column, or diagonal conflicts).

## Intuition/Approach
- Place one queen per row; try all columns in that row.
- If a position is safe, place queen and recurse to next row; otherwise try next column.
- Backtrack by removing queen when stuck.

## Key Observations
- Safety requires checking column and both upper diagonals for existing queens.
- At most one queen per row reduces state space.
- Many N have multiple solutions; N=2 and N=3 have none.

## Algorithm Steps
1. Initialize N×N board with 'X'.
2. For row from 0..N-1, try columns 0..N-1:
   - If safe(row,col), place 'Q', recurse to row+1; else continue.
   - After recursion, reset to 'X' (backtrack).
3. When row==N, output a solution.

## Complexity Analysis
- **Time Complexity:** O(N!)
- **Space Complexity:** O(N²) for board, O(N) recursion depth
- **Justification:** Branching shrinks as rows increase; factorial growth overall.

## Edge Cases Considered
- [x] N = 1 (1 solution)
- [x] N = 2, 3 (no solutions)
- [x] Large N (exponential time)
- [x] Board bounds

## Solution Code

```java
public static boolean isSafe(char[][] board, int row, int col) {
    for (int i = row - 1; i >= 0; i--) if (board[i][col] == 'Q') return false;
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 'Q') return false;
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) if (board[i][j] == 'Q') return false;
    return true;
}

public static void nQueens(char[][] board, int row) {
    if (row == board.length) {
        printBoard(board);
        return;
    }
    for (int j = 0; j < board.length; j++) {
        if (isSafe(board, row, j)) {
            board[row][j] = 'Q';
            nQueens(board, row + 1);
            board[row][j] = 'X';
        }
    }
}
```

## Alternative Approaches
- Bitmask optimization for columns and diagonals → O(N) per row with ints/longs.
- Symmetry pruning for first-row choices to halve search for even N.

## Personal Notes
- Bitmasking drastically improves performance and reduces memory footprint.

---
**Tags:** #backtracking #nqueens #search