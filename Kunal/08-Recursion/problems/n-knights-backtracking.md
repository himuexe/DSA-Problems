# N-Knights - Backtracking Placement Problem

## Problem Statement
Place N knights on an N×N chessboard such that no two knights attack each other. A knight attacks in an L-shaped pattern: 2 squares in one direction and 1 square perpendicular to that direction.

## Intuition/Approach
**Backtracking with Knight Attack Pattern:**
1. **Sequential Placement:** Process board left-to-right, top-to-bottom
2. **Safety Check:** For each position, verify no knight attacks this cell
3. **Knight Moves:** Check 4 possible knight attack positions (only backwards)
4. **Recursive Exploration:** Place knight if safe, recurse to next position
5. **Backtrack:** Remove knight if no solution found and try next position

**Key Insight:** Only check previous knight positions since we place knights sequentially from top-left to bottom-right.

## Key Observations
- **Attack Pattern:** Knights move in L-shape (2+1 or 1+2 squares)
- **Backward Checking:** Only check 4 positions: up-left knight moves
- **Sequential Processing:** Row-by-row, column-by-column placement
- **Solution Count:** Multiple valid arrangements possible for most N values
- **Pruning Efficiency:** Early termination when knight count reaches 0

## Knight Attack Positions (Backward Only)
```
. . K . .
. K . K .    Current position: (row, col)
. . X . .    Check positions: 
. . . . .    - (row-2, col-1): up-up-left
. . . . .    - (row-1, col-2): up-left-left
                                - (row-2, col+1): up-up-right
                                - (row-1, col+2): up-right-right
```

## Algorithm Steps
1. **Base Cases:**
   - If knights == 0: Found valid solution, display board
   - If row == N: Reached end without placing all knights, return
   - If col == N: Move to next row, reset column to 0

2. **Knight Placement:**
   - Check if current position (row, col) is safe
   - If safe: place knight, recurse with knights-1
   - Always try next position (regardless of placement)

3. **Safety Validation:**
   - Check 4 backward knight attack positions
   - Ensure positions are within board bounds
   - Return false if any position has existing knight

## Time & Space Complexity
- **Time Complexity:** O(N!)
  - N positions for first knight
  - ~(N-4) valid positions for second knight
  - Decreasing possibilities with each placement
  - Factorial complexity similar to N-Queens
- **Space Complexity:** O(N²)
  - Board storage: O(N²)
  - Recursion stack: O(N²) maximum depth
  - Display operations: O(N²) per solution

## Edge Cases Considered
- [x] N = 1 (trivial case, one solution)
- [x] N = 2, N = 3 (no solutions exist)
- [x] Small boards (limited knight placement)
- [x] Large N values (exponential growth)
- [x] Invalid knight positions (boundary checks)

## Code Implementation
```java
public class NKnights {
    
    static boolean isSafe(boolean[][] board, int row, int col) {
        // Check up-up-left
        if (isValid(board, row-2, col-1)) {
            if (board[row-2][col-1]) return false;
        }
        
        // Check up-left-left
        if (isValid(board, row-1, col-2)) {
            if (board[row-1][col-2]) return false;
        }
        
        // Check up-up-right
        if (isValid(board, row-2, col+1)) {
            if (board[row-2][col+1]) return false;
        }
        
        // Check up-right-right
        if (isValid(board, row-1, col+2)) {
            if (board[row-1][col+2]) return false;
        }
        
        return true;  // No attacking knight found
    }
    
    static boolean isValid(boolean[][] board, int row, int col) {
        return row >= 0 && row < board.length && 
               col >= 0 && col < board.length;
    }
    
    static void knight(boolean[][] board, int row, int col, int knights) {
        if (knights == 0) {
            display(board);  // Found valid solution
            System.out.println();
            return;
        }
        
        if (row == board.length) {
            return;  // Reached end of board
        }
        
        if (col == board.length) {
            knight(board, row + 1, 0, knights);  // Next row
            return;
        }
        
        // Try placing knight at current position
        if (isSafe(board, row, col)) {
            board[row][col] = true;  // Place knight
            knight(board, row, col + 1, knights - 1);
            board[row][col] = false;  // Backtrack
        }
        
        // Always try next position
        knight(board, row, col + 1, knights);
    }
}
```

## Example Walkthrough (N=4)
**One Valid Solution:**
```
K . K .
. . . .
. K . K
. . . .
```

**Placement Process:**
1. (0,0): Safe → Place knight → Recurse with 3 knights
2. (0,2): Safe → Place knight → Recurse with 2 knights
3. (2,1): Safe → Place knight → Recurse with 1 knight
4. (2,3): Safe → Place knight → Recurse with 0 knights → Solution!

## Optimization Opportunities
1. **Constraint Propagation:** Pre-calculate forbidden positions
2. **Symmetry Breaking:** Exploit board symmetries to reduce search
3. **Most Constrained First:** Choose positions with fewest possibilities
4. **Bit Manipulation:** Use bitboards for faster operations

## Key Learning Points
- **Knight Movement:** Understanding L-shaped attack patterns
- **Backward Validation:** Optimizing safety checks for sequential placement
- **Backtracking Pattern:** Standard recursive backtracking framework
- **Boundary Handling:** Proper bounds checking for board positions

## Applications
- **Puzzle Solving:** Chess piece placement problems
- **Constraint Satisfaction:** General CSP with geometric constraints
- **Game AI:** Board game state exploration
- **Combinatorial Search:** Arrangement problems with conflicts

---
**Date:** June 28, 2025  
**Topic:** Backtracking & Geometric Constraints  
**Difficulty:** Advanced  
**Category:** Board Placement Problems 