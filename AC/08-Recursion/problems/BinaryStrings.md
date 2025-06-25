# Binary Strings Without Consecutive 1s

**Date:** June 25, 2025  
**Topic:** Recursion  
**Difficulty:** Medium  
**Time Complexity:** O(2^n)  
**Space Complexity:** O(n)  
**Source:** Apna College DSA Course

---

## Problem Statement

Generate and print all binary strings of length **n** such that no two consecutive characters are **1s** (no "11" substring allowed).

**Example:**
```
Input: n = 3
Output: 
000
001
010
100
101

Total valid strings: 5
Invalid strings: 011, 110, 111 (contain consecutive 1s)
```

---

## Approach & Intuition

### Recursive Strategy with State Tracking
**Key Insight:** The validity of next character depends on the last placed character

**Algorithm Logic:**
1. **Track last character:** Use `lastPlace` parameter (0 or 1)
2. **Always append 0:** Valid after both 0 and 1
3. **Conditionally append 1:** Only valid after 0 (prevents consecutive 1s)
4. **Base case:** When length reaches 0, print the built string

### Decision Tree Analysis
At each position, we have:
- **After 0:** Can place either 0 or 1
- **After 1:** Can only place 0 (to avoid consecutive 1s)

### Why This Works
- **State constraint:** `lastPlace` prevents invalid sequences
- **Complete generation:** Explores all valid possibilities
- **Early pruning:** Skips invalid branches immediately
- **Correct counting:** Generates exactly all valid strings

---

## Code Implementation

```java
public class BinaryStrings {
    public static void printBinaryStrings(int n, int lastPlace, String str) {
        // Base case: string of required length built
        if(n == 0) {
            System.out.println(str);
            return;
        }
        
        // Always append 0 (valid after both 0 and 1)
        printBinaryStrings(n-1, 0, str+"0");
        
        // Append 1 only if last character was 0
        if(lastPlace == 0) {
            printBinaryStrings(n-1, 1, str+"1");
        }
    }
    
    public static void main(String[] args) {
        printBinaryStrings(3, 0, "");  // Start with lastPlace=0
    }
}
```

---

## Complexity Analysis

- **Time Complexity:** O(F(n+2)) ≈ O(φ^n) where φ ≈ 1.618 (Golden ratio)
- **Space Complexity:** O(n) - Recursion stack depth + string building
- **Total strings generated:** F(n+2) where F is Fibonacci sequence

### Mathematical Analysis
The number of valid binary strings follows Fibonacci pattern:
- n=1: 2 strings (0, 1)
- n=2: 3 strings (00, 01, 10)  
- n=3: 5 strings (000, 001, 010, 100, 101)

**Recurrence:** `Count(n) = Count(n-1) + Count(n-2)`

---

## Recursion Tree Analysis

### Example: printBinaryStrings(3, 0, "")
```
printBinaryStrings(3, 0, "")
├── printBinaryStrings(2, 0, "0")
│   ├── printBinaryStrings(1, 0, "00")
│   │   ├── printBinaryStrings(0, 0, "000") → Print "000"
│   │   └── printBinaryStrings(0, 1, "001") → Print "001"
│   └── printBinaryStrings(1, 1, "01")
│       └── printBinaryStrings(0, 0, "010") → Print "010"
└── printBinaryStrings(2, 1, "1")
    └── printBinaryStrings(1, 0, "10")
        ├── printBinaryStrings(0, 0, "100") → Print "100"
        └── printBinaryStrings(0, 1, "101") → Print "101"
```

---

## Edge Cases

- [x] **n = 0:** Prints empty string (base case)
- [x] **n = 1:** Prints "0" and "1" (both valid)
- [x] **n = 2:** Prints "00", "01", "10" (excludes "11")
- [x] **Large n:** Exponential growth in number of strings
- [x] **Initial lastPlace:** Should start with 0 to allow both 0 and 1

---

## Alternative Approaches

### 1. Iterative with Queue
```java
public static void printBinaryStringsIterative(int n) {
    Queue<String> queue = new LinkedList<>();
    queue.add("0");
    queue.add("1");
    
    for(int len = 1; len < n; len++) {
        int size = queue.size();
        for(int i = 0; i < size; i++) {
            String curr = queue.poll();
            queue.add(curr + "0");  // Always valid
            if(curr.charAt(curr.length()-1) == '0') {
                queue.add(curr + "1");  // Valid only after 0
            }
        }
    }
    
    while(!queue.isEmpty()) {
        System.out.println(queue.poll());
    }
}
```

### 2. Dynamic Programming (Count Only)
```java
public static int countValidStrings(int n) {
    if(n == 1) return 2;
    
    int[] dp = new int[n+1];
    dp[1] = 2;  // "0", "1"
    dp[2] = 3;  // "00", "01", "10"
    
    for(int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

### 3. Backtracking with List
```java
public static void generateBinaryStrings(int n, int pos, char[] str, List<String> result) {
    if(pos == n) {
        result.add(new String(str));
        return;
    }
    
    str[pos] = '0';
    generateBinaryStrings(n, pos+1, str, result);
    
    if(pos == 0 || str[pos-1] == '0') {
        str[pos] = '1';
        generateBinaryStrings(n, pos+1, str, result);
    }
}
```

---

## Variations

### Count Valid Strings Only
```java
public static int countBinaryStrings(int n, int lastPlace) {
    if(n == 0) return 1;
    
    int count = countBinaryStrings(n-1, 0);  // Append 0
    if(lastPlace == 0) {
        count += countBinaryStrings(n-1, 1);  // Append 1
    }
    return count;
}
```

### Generate with Different Constraints
- **No consecutive 0s:** Reverse the constraint logic
- **No more than k consecutive 1s:** Extend state tracking
- **Specific patterns:** Add more complex state management

---

## Mathematical Connection

### Fibonacci Relationship
Valid binary strings of length n without consecutive 1s = F(n+2)
- n=1: F(3) = 2
- n=2: F(4) = 3  
- n=3: F(5) = 5
- n=4: F(6) = 8

### Proof Intuition
- **Strings ending with 0:** All valid strings of length n-1
- **Strings ending with 1:** All valid strings of length n-2 (previous must end with 0)
- **Total:** F(n-1) + F(n-2) = F(n)

---

## Key Learnings

1. **State-dependent recursion:** Decision depends on previous choice
2. **Constraint modeling:** `lastPlace` parameter encodes constraint
3. **Pruning efficiency:** Early rejection of invalid branches
4. **Fibonacci pattern:** Many combinatorial problems follow this sequence
5. **String building:** Efficient concatenation in recursive calls

---

## Related Problems

- **Climbing Stairs:** Similar Fibonacci recursion pattern
- **House Robber:** Adjacent constraint problem
- **Decode Ways:** State-dependent string processing
- **Generate Balanced Parentheses:** Constraint-based generation
- **N-Queens:** Backtracking with constraint validation

---

## Notes

- **Interview Frequency:** Medium - tests recursion with constraints
- **Pattern:** State-dependent backtracking/generation
- **Optimization:** Can be memoized for counting version
- **Learning Value:** Excellent for understanding constraint propagation
- **Extension:** Foundation for more complex constraint satisfaction problems 