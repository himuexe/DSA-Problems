# Dice Combinations - Recursive Target Sum

## Problem Statement
Find all possible combinations of dice rolls (1-6) that sum to a given target value. Return all valid sequences as strings.

## Intuition/Approach
**Recursive Combination Generation:**
1. **Base Case:** When target becomes 0, add current path to result list
2. **Recursive Choices:** For each dice value (1-6), if value ≤ target:
   - Add digit to path string
   - Recurse with reduced target (target - dice_value)
3. **Combination Building:** Collect all results from recursive calls

**Key Insight:** Generate all valid dice sequences by exploring each possible dice roll at each step until target is reached.

## Key Observations
- **Dice Range:** Values 1-6 representing standard six-sided die
- **Target Reduction:** Each roll reduces remaining target by dice value
- **Path Building:** String accumulation tracks dice sequence
- **Boundary Condition:** Only roll dice if value doesn't exceed remaining target
- **Order Matters:** Different sequences with same dice values are distinct combinations

## Algorithm Steps
1. **Base Case Check:**
   - If target == 0: Create new list, add current path, return list
   
2. **Recursive Exploration:**
   - For each dice value i from 1 to 6:
     - Check if i ≤ target (valid roll)
     - Recursively call perm(path + i, target - i)
     - Add all returned combinations to result list
   
3. **Result Aggregation:**
   - Combine all recursive results into single list
   - Return complete list of valid combinations

## Time & Space Complexity
- **Time Complexity:** O(6^target)
  - Each recursive call has up to 6 choices
  - Maximum depth: target levels
  - Total recursive calls: exponential in target value
- **Space Complexity:** O(target × number_of_solutions)
  - Recursion stack depth: O(target)
  - Each solution string: O(target) length
  - Total combinations: O(6^target) in worst case

## Edge Cases Considered
- [x] Target = 0 (empty combination)
- [x] Target = 1 (single dice roll)
- [x] Target > 6 (multiple dice required)
- [x] Large targets (exponential combinations)
- [x] Impossible targets (negative values)

## Code Implementation
```java
import java.util.ArrayList;

public class Dice {
    
    public static ArrayList<String> perm(String p, int target) {
        if (target == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);  // Found valid combination
            return list;
        }
        
        ArrayList<String> list = new ArrayList<>();
        
        // Try each dice value (1-6)
        for (int i = 1; i <= 6 && i <= target; i++) {
            // Recursively explore with current dice roll
            list.addAll(perm(p + i, target - i));
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println(perm("", 4));
    }
}
```

## Example Walkthrough (Target = 4)
**Call:** perm("", 4)

**Recursive Tree (simplified):**
```
perm("", 4)
├─ perm("1", 3)
│  ├─ perm("11", 2) → ["1111", "1112", "112", "121", "13"]
│  ├─ perm("12", 2) → ["121", "13"]  
│  ├─ perm("13", 1) → ["131"]
│  └─ perm("14", 0) → ["14"]
├─ perm("2", 2)
│  ├─ perm("21", 1) → ["211"]
│  └─ perm("22", 0) → ["22"]
├─ perm("3", 1)
│  └─ perm("31", 0) → ["31"]
└─ perm("4", 0) → ["4"]
```

**Final Result:** ["1111", "112", "121", "13", "211", "22", "31", "4"]

**Combination Explanations:**
- **1111:** Four dice showing 1
- **112:** Dice showing 1, 1, 2
- **121:** Dice showing 1, 2, 1
- **13:** Dice showing 1, 3
- **211:** Dice showing 2, 1, 1
- **22:** Dice showing 2, 2
- **31:** Dice showing 3, 1
- **4:** Single die showing 4

## Alternative Implementations
1. **Void Method:** Print combinations instead of returning list
2. **Count Only:** Return integer count instead of combinations
3. **With Repetition Limit:** Limit number of dice rolls allowed
4. **Different Dice:** Support different sided dice (d8, d10, d20)

## Optimization Opportunities
1. **Memoization:** Cache results for repeated target values
2. **Dynamic Programming:** Bottom-up approach for counting
3. **Mathematical Formula:** Combinatorial calculation for counting only
4. **Early Termination:** Prune impossible branches earlier

## Key Learning Points
- **Recursive Enumeration:** Systematic generation of all possibilities
- **Path Tracking:** Building result strings through recursive calls
- **Boundary Conditions:** Proper constraint checking (dice ≤ target)
- **Result Aggregation:** Combining recursive results efficiently

## Applications
- **Game Development:** Dice roll probability calculations
- **Combinatorics:** Understanding recursive combination generation
- **Dynamic Programming:** Foundation for optimization problems
- **Probability Theory:** Analyzing dice game outcomes

---
**Date:** June 28, 2025  
**Topic:** Recursion & Combinatorial Generation  
**Difficulty:** Intermediate  
**Category:** Recursive Enumeration 