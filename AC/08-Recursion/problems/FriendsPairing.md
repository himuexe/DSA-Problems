# Friends Pairing Problem

**Date:** June 25, 2025  
**Topic:** Recursion  
**Difficulty:** Medium  
**Time Complexity:** O(2^n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Given **n** friends, find the total number of ways they can remain single or can be paired up. Each friend can either:
1. **Remain single**
2. **Be paired with exactly one other friend**

No friend can be in multiple pairs, and each pair consists of exactly 2 friends.

**Example:**
```
For n = 3 friends (A, B, C):
Ways = 4
1. (A)(B)(C) - All single
2. (AB)(C)   - A-B paired, C single  
3. (AC)(B)   - A-C paired, B single
4. (BC)(A)   - B-C paired, A single

For n = 4: Ways = 10
```

---

## Approach & Intuition

### Recursive Strategy with Combinatorial Analysis
**Key Insight:** Consider the choices for the last friend (friend n)

Friend n can either:
1. **Stay single:** Remaining (n-1) friends can be arranged in `f(n-1)` ways
2. **Pair with any of (n-1) friends:** 
   - Choose 1 friend from (n-1) to pair with: `(n-1)` choices
   - Remaining (n-2) friends arrange in `f(n-2)` ways
   - Total: `(n-1) × f(n-2)` ways

**Recurrence:** `f(n) = f(n-1) + (n-1) × f(n-2)`

### Mathematical Foundation
- **Base cases:** f(1) = 1, f(2) = 2
- **Recursive relation:** Each friend's choice affects remaining subproblem size
- **Counting principle:** Addition for mutually exclusive cases

---

## Code Implementation

```java
public class FriendsPairing {
    public static int pairing(int n) {
        // Base cases
        if(n == 1 || n == 2) {
            return n;
        }
        
        // Friend n stays single: f(n-1) ways
        int fnm1 = pairing(n-1);
        
        // Friend n pairs with someone: (n-1) choices × f(n-2) ways  
        int fm2 = pairing(n-2);
        int pairways = (n-1) * fm2;
        
        return fnm1 + pairways;
    }
    
    public static void main(String[] args) {
        System.out.println(pairing(3));  // Output: 4
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(2^n) - Exponential due to overlapping subproblems
- **Space Complexity:** O(n) - Recursion stack depth
- **Recurrence:** T(n) = T(n-1) + T(n-2) + O(1)

### Growth Pattern
| n | f(n) | Computation |
|---|------|-------------|
| 1 | 1 | Base case |
| 2 | 2 | Base case |  
| 3 | 4 | 2 + (2×1) = 4 |
| 4 | 10 | 4 + (3×2) = 10 |
| 5 | 26 | 10 + (4×4) = 26 |

---

## Recursion Tree Analysis

### Example: pairing(4)
```
pairing(4)
├── pairing(3) = 4
│   ├── pairing(2) = 2
│   └── (2-1) × pairing(1) = 1×1 = 1
│   └── Result: 2 + 1 = 3 ❌ (This shows issue in tree - actual is 4)
└── (4-1) × pairing(2) = 3×2 = 6
└── Final: 4 + 6 = 10 ✓
```

**Corrected Tree:**
```
pairing(4)
├── Friend 4 stays single: pairing(3) = 4
└── Friend 4 pairs: 3 choices × pairing(2) = 3×2 = 6  
└── Total: 4 + 6 = 10
```

---

## Mathematical Derivation

### Step-by-step Analysis for n=4
**Case 1:** Friend 4 stays single
- Arrangements for friends {1,2,3}: f(3) = 4 ways

**Case 2:** Friend 4 pairs with someone  
- **Pair with friend 1:** Arrangements for {2,3}: f(2) = 2 ways
- **Pair with friend 2:** Arrangements for {1,3}: f(2) = 2 ways  
- **Pair with friend 3:** Arrangements for {1,2}: f(2) = 2 ways
- **Total pairing ways:** 3 × 2 = 6 ways

**Final result:** 4 + 6 = 10 ways

---

## Edge Cases

- [x] **n = 1:** One friend, 1 way (stay single)
- [x] **n = 2:** Two friends, 2 ways (both single OR pair together)
- [x] **n = 0:** No friends, conventionally 1 way (empty arrangement)
- [x] **Large n:** Exponential growth, may cause stack overflow
- [x] **Negative n:** Not handled, needs input validation

---

## Alternative Approaches

### 1. Dynamic Programming (Bottom-Up)
```java
public static int pairingDP(int n) {
    if(n <= 2) return n;
    
    int[] dp = new int[n+1];
    dp[1] = 1;
    dp[2] = 2;
    
    for(int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + (i-1) * dp[i-2];
    }
    return dp[n];
}
```
- **Time:** O(n), **Space:** O(n)

### 2. Space-Optimized DP
```java
public static int pairingOptimized(int n) {
    if(n <= 2) return n;
    
    int prev2 = 1, prev1 = 2;
    for(int i = 3; i <= n; i++) {
        int curr = prev1 + (i-1) * prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```
- **Time:** O(n), **Space:** O(1)

### 3. Memoization (Top-Down)
```java
static int[] memo;
public static int pairingMemo(int n) {
    if(memo[n] != -1) return memo[n];
    if(n <= 2) return memo[n] = n;
    
    memo[n] = pairingMemo(n-1) + (n-1) * pairingMemo(n-2);
    return memo[n];
}
```

---

## Combinatorial Interpretation

### Connection to Bell Numbers
Friends pairing is related to **restricted Bell numbers** where each block has size ≤ 2.

### Generating Function
The exponential generating function is:
```
F(x) = e^x × e^(x²/2) = e^(x + x²/2)
```

### Recurrence Relation Analysis
The recurrence `f(n) = f(n-1) + (n-1)×f(n-2)` can be understood as:
- **Linear term:** f(n-1) represents single friend choice
- **Quadratic term:** (n-1)×f(n-2) represents pairing choices

---

## Alternative Problem Formulations

### 1. Count Pairs Only
```java
// Count arrangements with exactly k pairs
public static int countWithKPairs(int n, int k) {
    if(k > n/2) return 0;
    if(k == 0) return 1;
    if(n == 2*k) return factorial(2*k)/(pow(2,k)*factorial(k));
    // More complex implementation needed
}
```

### 2. Maximum Pairs Version
```java
// Maximum number of pairs possible
public static int maxPairs(int n) {
    return n/2;  // Simple: floor(n/2)
}
```

---

## Key Learnings

1. **Combinatorial recursion:** Break down by last element's choices
2. **Multiplication principle:** (n-1) ways to choose pair partner
3. **Overlapping subproblems:** Multiple paths to same state
4. **Base case design:** Handle small cases explicitly
5. **DP optimization:** Exponential → linear time improvement

---

## Related Problems

- **Bell Numbers:** General set partitioning
- **Stirling Numbers:** Partitions with fixed block sizes
- **Marriage Problem:** Bipartite matching
- **Catalan Numbers:** Various combinatorial structures
- **Fibonacci Variants:** Similar recurrence patterns

---

## Notes

- **Interview Frequency:** Medium - tests combinatorial recursion
- **Mathematical depth:** Rich combinatorial interpretation
- **Optimization potential:** Excellent DP example
- **Pattern recognition:** f(n) = f(n-1) + coefficient×f(n-2)
- **Real-world applications:** Social network analysis, matching problems
``` 