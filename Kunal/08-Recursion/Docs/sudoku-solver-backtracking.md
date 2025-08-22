# Sudoku Solver - Advanced Backtracking Algorithm

## Problem Statement
Solve a 9×9 Sudoku puzzle using backtracking. Fill empty cells (marked with '.') with digits 1-9 such that each row, column, and 3×3 sub-box contains all digits from 1 to 9 exactly once.

## Intuition/Approach
**Constraint Satisfaction with Backtracking:**
1. **Find Empty Cell:** Scan board for first empty cell ('.')
2. **Try All Digits:** For each digit 1-9, check if placement is valid
3. **Validation:** Ensure digit doesn't violate row, column, or 3×3 box constraints
4. **Recursive Exploration:** If valid, place digit and recurse to next empty cell
5. **Backtrack:** If no solution found, remove digit and try next possibility

**Key Insight:** Systematic constraint checking with intelligent backtracking when conflicts arise.

## Key Observations
- **Constraint Types:** Row uniqueness, column uniqueness, 3×3 box uniqueness
- **Search Strategy:** First empty cell approach (left-to-right, top-to-bottom)
- **Validation Efficiency:** Check all three constraints before placement
- **Backtracking Trigger:** Failed recursive call indicates need to backtrack
- **Solution Uniqueness:** Well-formed Sudoku has exactly one solution

## Algorithm Steps
1. **Find Next Empty Cell:**
   - Scan rows 0-8, columns 0-8
   - Look for board[row][col] == '.'
   - If no empty cell found, puzzle is solved

2. **Try Each Digit (1-9):**
   - For each candidate digit, validate placement
   - Check row constraint, column constraint, 3×3 box constraint
   - If all constraints satisfied, proceed with placement

3. **Recursive Exploration:**
   - Place digit: board[row][col] = digit
   - Recursively solve remaining puzzle
   - If successful: return true (solution found)
   - If failed: backtrack by resetting board[row][col] = '.'

## Constraint Validation Details
```java
// Row Constraint: Check if digit exists in current row
for (int i = 0; i < 9; i++) {
    if (board[row][i] == num) return false;
}

// Column Constraint: Check if digit exists in current column  
for (int i = 0; i < 9; i++) {
    if (board[i][col] == num) return false;
}

// 3×3 Box Constraint: Check if digit exists in current sub-box
int boxRow = row - row % 3;  // Top-left corner of box
int boxCol = col - col % 3;
for (int i = boxRow; i < boxRow + 3; i++) {
    for (int j = boxCol; j < boxCol + 3; j++) {
        if (board[i][j] == num) return false;
    }
}
```

## Time & Space Complexity
- **Time Complexity:** O(9^(n×n))
  - n = number of empty cells (worst case: 81)
  - Each empty cell has up to 9 choices
  - Exponential backtracking in worst case
  - Average case much better due to constraint propagation
- **Space Complexity:** O(n×n)
  - Board storage: O(81) = O(1) constant
  - Recursion stack: O(number of empty cells)
  - Maximum depth: 81 levels

## Edge Cases Considered
- [x] Already solved puzzle (no empty cells)
- [x] Unsolvable puzzle (invalid constraints)
- [x] Multiple empty cells in same region
- [x] Minimal clue puzzles (17+ clues required)
- [x] Invalid initial board state

## Code Implementation
```java
public void solveSudoku(char[][] board) {
    solve(board);
}

private boolean solve(char[][] board) {
    // Find next empty cell
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == '.') {
                // Try each digit 1-9
                for (char num = '1'; num <= '9'; num++) {
                    if (isValid(board, row, col, num)) {
                        board[row][col] = num;  // Place digit
                        
                        if (solve(board)) {
                            return true;  // Solution found
                        } else {
                            board[row][col] = '.';  // Backtrack
                        }
                    }
                }
                return false;  // No valid digit found, trigger backtracking
            }
        }
    }
    return true;  // All cells filled successfully
}

private boolean isValid(char[][] board, int row, int col, char num) {
    // Check row constraint
    for (int i = 0; i < 9; i++) {
        if (board[row][i] == num) return false;
    }
    
    // Check column constraint
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == num) return false;
    }
    
    // Check 3×3 sub-box constraint
    int boxRow = row - row % 3;
    int boxCol = col - col % 3;
    
    for (int i = boxRow; i < boxRow + 3; i++) {
        for (int j = boxCol; j < boxCol + 3; j++) {
            if (board[i][j] == num) return false;
        }
    }
    
    return true;  // All constraints satisfied
}
```

## Example Walkthrough
**Initial Board (simplified):**
```
5 3 . | . 7 . | . . .
6 . . | 1 9 5 | . . .
. 9 8 | . . . | . 6 .
------+-------+------
8 . . | . 6 . | . . 3
4 . . | 8 . 3 | . . 1
7 . . | . 2 . | . . 6
------+-------+------
. 6 . | . . . | 2 8 .
. . . | 4 1 9 | . . 5
. . . | . 8 . | . 7 9
```

**Solution Process:**
1. Find first empty: (0,2)
2. Try '1': Check row 0, col 2, box 0 → Valid
3. Place '1', recurse to next empty cell
4. Continue until solution found or backtrack needed

## Optimization Opportunities
1. **Constraint Propagation:** Pre-compute possible values for each cell
2. **Most Constrained Variable:** Choose cell with fewest possibilities first
3. **Naked Singles:** Fill cells with only one possible value
4. **Hidden Singles:** Find digits that can only go in one cell
5. **Bit Manipulation:** Use bitmasks for faster constraint checking

## Key Learning Points
- **Advanced Backtracking:** Complex constraint satisfaction problem
- **Constraint Modeling:** Converting Sudoku rules into validation logic
- **Search Optimization:** Understanding when and how to backtrack
- **Problem Decomposition:** Breaking complex problem into manageable parts

## Applications
- **Constraint Satisfaction:** General CSP problem-solving framework
- **Game AI:** Puzzle solving and game state search
- **Logic Programming:** Rule-based reasoning systems
- **Optimization Problems:** Finding feasible solutions under constraints

---
**Date:** June 28, 2025  
**Topic:** Advanced Backtracking & Constraint Satisfaction  
**Difficulty:** Hard  
**Category:** Classic Backtracking 