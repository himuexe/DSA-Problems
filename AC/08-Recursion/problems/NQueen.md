# N-Queens - Classic Backtracking Problem

## Problem Statement
Place N queens on an N×N chessboard such that no two queens attack each other. A queen can attack any piece in the same row, column, or diagonal.

## Intuition/Approach
**Backtracking Strategy:**
1. **Row-by-Row Placement:** Place queens one row at a time
2. **Safety Check:** For each column in current row, check if position is safe
3. **Recursive Exploration:** If safe, place queen and recurse to next row
4. **Backtrack:** If no valid placement found, remove queen and try next position

**Key Insight:** Constraint satisfaction through systematic exploration with backtracking.

## Key Observations
- **Attack Patterns:** Queens attack horizontally, vertically, and diagonally
- **Row Constraint:** Place exactly one queen per row
- **Safety Validation:** Check column and both diagonals for conflicts
- **Backtracking Nature:** Undo decisions when constraints violated
- **Multiple Solutions:** Most N values have multiple valid arrangements

## Algorithm Steps
1. **Initialization:** Create N×N board filled with 'X'
2. **Recursive Placement (nQueens):**
   - Base case: If row == N, print solution and return
   - For each column in current row:
     - Check if position (row, col) is safe
     - If safe: place queen, recurse to next row, backtrack
3. **Safety Check (isSafe):**
   - Check vertical column for existing queens
   - Check left diagonal (row-1, col-1) direction
   - Check right diagonal (row-1, col+1) direction

## Safety Validation Details
```java
// Vertical check: same column, previous rows
for(int i = row-1; i >= 0; i--)
    if(board[i][col] == 'Q') return false;

// Left diagonal: up-left direction  
for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--)
    if(board[i][j] == 'Q') return false;

// Right diagonal: up-right direction
for(int i = row-1, j = col+1; i >= 0 && j < N; i--, j++)
    if(board[i][j] == 'Q') return false;
```

## Time & Space Complexity
- **Time Complexity:** O(N!)
  - First row: N choices
  - Second row: ~(N-2) valid choices  
  - Third row: ~(N-4) valid choices
  - Overall: approximately N! permutations to explore
- **Space Complexity:** O(N²)
  - Board storage: O(N²)
  - Recursion stack: O(N) depth
  - Total space: O(N²)

## Edge Cases Considered
- [x] N = 1 (trivial case, one solution)
- [x] N = 2, N = 3 (no solutions exist)
- [x] N = 4 (first non-trivial case with solutions)
- [x] Large N values (exponential growth)
- [x] Board boundary conditions

## Code Implementation
```java
public static boolean isSafe(char[][] board, int row, int col) {
    // Check vertical column
    for (int i = row - 1; i >= 0; i--) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }
    
    // Check left diagonal
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    
    // Check right diagonal
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    
    return true;
}

public static void nQueens(char[][] board, int row) {
    if (row == board.length) {
        printBoard(board);  // Found valid solution
        return;
    }
    
    // Try placing queen in each column of current row
    for (int j = 0; j < board.length; j++) {
        if (isSafe(board, row, j)) {
            board[row][j] = 'Q';        // Place queen
            nQueens(board, row + 1);    // Recurse to next row
            board[row][j] = 'X';        // Backtrack
        }
    }
}
```

## Example Walkthrough (N=4)
**One Valid Solution:**
```
. Q . .
. . . Q  
Q . . .
. . Q .
```

**Placement Process:**
1. Row 0: Try col 1 → Safe → Place queen → Recurse
2. Row 1: Try col 3 → Safe → Place queen → Recurse  
3. Row 2: Try col 0 → Safe → Place queen → Recurse
4. Row 3: Try col 2 → Safe → Place queen → Solution found!

## Optimization Opportunities
1. **Bit Manipulation:** Use integers to track attacked positions
2. **Symmetry Reduction:** Exploit board symmetries to reduce search space
3. **Constraint Propagation:** Pre-calculate forbidden positions
4. **Heuristics:** Choose most constrained positions first

## Key Learning Points
- **Backtracking Pattern:** Systematic constraint satisfaction approach
- **State Space Exploration:** Efficient pruning of invalid branches
- **Constraint Modeling:** Converting problem rules into code logic
- **Recursive Design:** Natural fit for combinatorial problems

## Applications
- **Constraint Satisfaction:** General CSP problem-solving framework
- **Game AI:** Chess piece placement and game tree search
- **Resource Allocation:** Scheduling problems with conflicts
- **Combinatorial Optimization:** Permutation with constraints

---
**Date:** June 28, 2025  
**Topic:** Backtracking & Constraint Satisfaction  
**Difficulty:** Advanced  
**Category:** Classic Backtracking 