# Minimum Domino Rotations For Equal Row

**Source:** Kunal | **Topic:** Arrays | **Difficulty:** Medium  

---

## Problem Statement
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino. We may rotate the ith domino, so that A[i] and B[i] swap values. Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same. If it cannot be done, return -1.

## Intuition/Approach
Try to make all values in A equal to A[0] or B[0], and all values in B equal to A[0] or B[0]. For each target value, count the minimum rotations needed. Return the minimum of all possible targets.

## Key Observations
- Target value must be either A[0] or B[0]
- Need to check both possibilities
- For each target, count rotations needed for A and B
- Return minimum rotations possible
- If no target works, return -1

## Algorithm Steps
1. Try target = A[0]: count rotations needed for A and B
2. Try target = B[0]: count rotations needed for A and B
3. For each target, check if all positions can achieve that value
4. Return minimum rotations needed, or -1 if impossible

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single pass through array for each target, constant extra space

## Edge Cases Considered
- [x] Single domino - Return 0 (already equal)
- [x] Two dominoes (same values) - Return 0
- [x] Two dominoes (different values) - Check both targets
- [x] All dominoes same - Return 0
- [x] Impossible case - Return -1
- [x] Mixed values - Find minimum rotations

## Solution Code

```java
// Language: Java
public static int minDominoRotations(int[] A, int[] B) {
    int rotations = check(A, B, A[0]);
    if (rotations != -1) return rotations;
    return check(A, B, B[0]);
}

private static int check(int[] A, int[] B, int target) {
    int rotationsA = 0, rotationsB = 0;
    
    for (int i = 0; i < A.length; i++) {
        if (A[i] != target && B[i] != target) {
            return -1; // Cannot achieve target
        }
        if (A[i] != target) rotationsA++;
        if (B[i] != target) rotationsB++;
    }
    
    return Math.min(rotationsA, rotationsB);
}
```

## Alternative Approaches
- **Brute force:** Try all possible target values
- **Greedy:** Always choose the value that requires fewer rotations
- **Dynamic programming:** Track minimum rotations for each position

## Personal Notes
This is a good problem that tests understanding of array manipulation and optimization. The key insight is that we only need to try two target values (A[0] and B[0]) since any other target would require more rotations. The algorithm efficiently checks both possibilities and returns the minimum rotations needed.

---
**Tags:** #arrays #domino #rotations #medium #optimization 