# N-Knights - Backtracking Placement Problem

**Source:** Kunal | **Topic:** Backtracking | **Difficulty:** Advanced  

---

## Problem Statement
Place N knights on an N×N chessboard so that no two knights attack each other.

## Intuition/Approach
- Place knights scanning row-by-row, col-by-col.
- Only need to check the four “backward” knight positions for safety.
- Recurse to next cell; backtrack when needed.

## Key Observations
- Knight moves in L-shapes (±2, ±1) pairs.
- Checking only previously visited positions suffices for correctness.

## Algorithm Steps
1. If `knights == 0`, output board (solution found).
2. If `row == N`, return.
3. If `col == N`, move to next row at col 0.
4. If safe at (row,col), place knight, recurse with `knights-1`, then undo.
5. Always recurse to next column without placing as well.

## Complexity Analysis
- **Time Complexity:** Exponential (similar to N-Queens)
- **Space Complexity:** O(N^2)
- **Justification:** Board state plus recursion depth across cells.

## Edge Cases Considered
- [x] N = 1
- [x] N = 2, 3 (often no full placements)
- [x] Bounds checks for all candidate moves

## Solution Code

```java
public class NKnights {
    static boolean isSafe(boolean[][] board, int row, int col) {
        if (isValid(board, row - 2, col - 1) && board[row - 2][col - 1]) return false;
        if (isValid(board, row - 1, col - 2) && board[row - 1][col - 2]) return false;
        if (isValid(board, row - 2, col + 1) && board[row - 2][col + 1]) return false;
        if (isValid(board, row - 1, col + 2) && board[row - 1][col + 2]) return false;
        return true;
    }

    static boolean isValid(boolean[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board.length;
    }

    static void knight(boolean[][] board, int row, int col, int knights) {
        if (knights == 0) { display(board); System.out.println(); return; }
        if (row == board.length) return;
        if (col == board.length) { knight(board, row + 1, 0, knights); return; }
        if (isSafe(board, row, col)) {
            board[row][col] = true;
            knight(board, row, col + 1, knights - 1);
            board[row][col] = false;
        }
        knight(board, row, col + 1, knights);
    }
}
```

## Alternative Approaches
- Bitboards to represent rows/columns for faster operations.
- Heuristics to choose next promising cells first.

## Personal Notes
- Displaying all solutions grows quickly; consider counting-only mode for large N.

---
**Tags:** #backtracking #chess #search