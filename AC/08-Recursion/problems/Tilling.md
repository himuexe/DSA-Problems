# Tiling Problem - Floor Tiling with 2x1 Tiles

**Date:** June 25, 2025  
**Topic:** Recursion  
**Difficulty:** Medium  
**Time Complexity:** O(2^n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Given a floor of size **2 × n**, find the number of ways to tile the floor using **2 × 1** tiles. Each tile can be placed either:
1. **Horizontally** (covering 2 × 1 space)
2. **Vertically** (covering 1 × 2 space, but need 2 tiles to fill 2 × 2)

Return the total number of distinct ways to completely tile the floor.

**Example:**
```
For n = 3 (2 × 3 floor):
Output: 3 ways
- All horizontal: [--][--][--]
- Mix 1: [|][|][--] (2 vertical + 1 horizontal)  
- Mix 2: [--][|][|] (1 horizontal + 2 vertical)
```

---

## Approach & Intuition

### Mathematical Recurrence Relation
**Key Insight:** This follows the **Fibonacci sequence pattern**

For a 2×n floor, we can place the first tile in two ways:
1. **Place one horizontal tile** → remaining subproblem: 2×(n-1) floor
2. **Place two vertical tiles** → remaining subproblem: 2×(n-2) floor

**Recurrence:** `T(n) = T(n-1) + T(n-2)`

### Base Cases Analysis
- **T(0) = 1:** Empty floor has 1 way (do nothing)
- **T(1) = 1:** 2×1 floor has 1 way (one horizontal tile)
- **T(2) = 2:** 2×2 floor has 2 ways (2 horizontal or 2 vertical)

### Why This Works
- **Horizontal placement:** Reduces problem by 1 column
- **Vertical placement:** Must place 2 tiles together, reduces by 2 columns
- **No overlap:** Each choice leads to independent subproblems
- **Complete coverage:** Every valid tiling follows this pattern

---

## Code Implementation

```java
public class Tilling {
    public static int tillingProblem(int n) {
        // Base cases
        if(n == 1 || n == 0) {
            return 1;
        }
        
        // Recursive relation: T(n) = T(n-1) + T(n-2)
        int fnm1 = tillingProblem(n-1);  // Horizontal tile placement
        int fm2 = tillingProblem(n-2);   // Vertical tiles placement
        
        return fnm1 + fm2;
    }
    
    public static void main(String[] args) {
        System.out.println(tillingProblem(3));  // Output: 3
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(2^n) - Exponential due to overlapping subproblems
- **Space Complexity:** O(n) - Recursion stack depth
- **Recurrence Relation:** T(n) = T(n-1) + T(n-2) + O(1)

### Performance Analysis
| n | Recursive Calls | Result |
|---|----------------|--------|
| 0 | 1 | 1 |
| 1 | 1 | 1 |
| 2 | 3 | 2 |
| 3 | 5 | 3 |
| 4 | 9 | 5 |
| 5 | 15 | 8 |

---

## Recursion Tree Analysis

### Example: tillingProblem(4)
```
tillingProblem(4)
├── tillingProblem(3)
│   ├── tillingProblem(2)
│   │   ├── tillingProblem(1) → 1
│   │   └── tillingProblem(0) → 1
│   │   └── Result: 2
│   └── tillingProblem(1) → 1
│   └── Result: 3
└── tillingProblem(2)
    ├── tillingProblem(1) → 1
    └── tillingProblem(0) → 1
    └── Result: 2
└── Final Result: 5
```

---

## Edge Cases

- [x] **n = 0:** Empty floor, returns 1 (valid base case)
- [x] **n = 1:** Single column, returns 1 (one horizontal tile)
- [x] **n = 2:** Returns 2 (horizontal pair or vertical pair)
- [x] **Large n:** Exponential time, may cause stack overflow
- [x] **Negative n:** Not handled, needs input validation

---

## Alternative Approaches

### 1. Dynamic Programming (Bottom-Up)
```java
public static int tillingDP(int n) {
    if(n <= 1) return 1;
    
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;
    
    for(int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```
- **Time:** O(n), **Space:** O(n)

### 2. Space-Optimized DP
```java
public static int tillingOptimized(int n) {
    if(n <= 1) return 1;
    
    int prev2 = 1, prev1 = 1;
    for(int i = 2; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```
- **Time:** O(n), **Space:** O(1)

### 3. Matrix Exponentiation
- **Time:** O(log n), **Space:** O(1)
- Uses fast matrix power for Fibonacci computation

---

## Mathematical Connection

### Fibonacci Sequence Relationship
The tiling problem generates the Fibonacci sequence:
- T(0) = F(1) = 1
- T(1) = F(2) = 1  
- T(2) = F(3) = 2
- T(3) = F(4) = 3
- T(4) = F(5) = 5

**Formula:** T(n) = F(n+1) where F is Fibonacci sequence

### Closed Form Solution
Using Binet's Formula:
```
T(n) = (φ^(n+1) - ψ^(n+1)) / √5
where φ = (1 + √5)/2, ψ = (1 - √5)/2
```

---

## Key Learnings

1. **Pattern Recognition:** Identifies as Fibonacci-type problem
2. **Recurrence Relation:** T(n) = T(n-1) + T(n-2)
3. **Exponential Growth:** Naive recursion has exponential time complexity
4. **Optimization Opportunity:** Perfect candidate for memoization/DP
5. **Real-world Application:** Models various combinatorial problems

---

## Related Problems

- **Fibonacci Numbers:** Direct mathematical connection
- **Climbing Stairs:** Similar recurrence relation
- **Domino Tiling:** 3×n version of same problem
- **Ways to Decode:** DP with similar structure
- **House Robber:** Dynamic programming pattern

---

## Notes

- **Interview Frequency:** Medium - good for testing recursion and DP concepts
- **Optimization Priority:** Exponential time makes this unsuitable for large inputs
- **Learning Value:** Excellent introduction to recursion → DP optimization
- **Pattern:** Classic divide-and-conquer with overlapping subproblems
- **Extension:** Can be generalized to different tile sizes and floor dimensions 