# Character Case Check

**Source:** Kunal | **Topic:** 00-Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Check whether a given character is uppercase or lowercase. Read a single character input and determine its case classification.

## Intuition/Approach
Use ASCII value comparison to determine character case. Lowercase letters 'a'-'z' have ASCII values 97-122, while uppercase letters 'A'-'Z' have ASCII values 65-90.

## Key Observations
- Lowercase letters: 'a' to 'z' (ASCII 97-122)
- Uppercase letters: 'A' to 'Z' (ASCII 65-90)
- Direct character comparison works due to ASCII ordering
- Input handling using Scanner.next().trim().charAt(0)

## Algorithm Steps
1. Read character input from user
2. Extract first character using charAt(0)
3. Compare character with 'a' and 'z' bounds
4. If within lowercase range, print "Lowercase"
5. Otherwise, print "Uppercase"

## Complexity Analysis
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)
- **Justification:** Single character comparison, constant space

## Edge Cases Considered
- [x] Lowercase letters (a-z)
- [x] Uppercase letters (A-Z)
- [x] Input with extra whitespace (handled by trim())
- [ ] Non-alphabetic characters (assumed to be uppercase)
- [ ] Empty input (not handled)

## Solution Code

```java
// Language: Java
public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    char ch = sc.next().trim().charAt(0);
    if(ch >= 'a' && ch <= 'z'){
        System.out.println("Lowercase");
    }
    else{
        System.out.println("Uppercase");
    }
    sc.close();
}
```

## Alternative Approaches
- **Character.isLowerCase():** Built-in Java method for case checking
- **ASCII Values:** Direct ASCII value comparison (ch >= 97 && ch <= 122)
- **Switch Case:** Multiple case statements for character ranges

## Related Problems
- **AC:** [OddEven.java - similar conditional logic patterns]
- **Kunal:** [FizzBuzz.java - condition-based classification]
- **LeetCode:** [Detect Capital - Problem 520]

## Personal Notes
Fundamental character processing concept. Understanding ASCII values and character ranges is important for string manipulation. Good introduction to character-based conditionals.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #characters #ascii #conditionals #stringProcessing 