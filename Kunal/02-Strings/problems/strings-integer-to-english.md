# Integer to English Words

**Source:** Kunal | **Topic:** Strings | **Difficulty:** Hard  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Convert a non-negative integer to its English words representation. For example, 123 becomes "One Hundred Twenty Three".

## Intuition/Approach
Break number into groups of three digits (thousands, millions, billions). Process each group separately using helper function, then combine with appropriate scale words.

## Key Observations
- Numbers processed in groups of 3 digits (ones, thousands, millions, billions)
- Special cases for numbers less than 20 (unique words)
- Tens place uses different word pattern
- Hundreds place follows consistent pattern
- Recursive structure for processing groups

## Algorithm Steps
1. Handle special case of 0
2. Process number in groups of 1000 (thousands, millions, billions)
3. For each non-zero group:
   - Convert group to words using helper function
   - Append appropriate scale word (thousand, million, billion)
4. Helper function handles:
   - Numbers < 20 (direct lookup)
   - Numbers < 100 (tens + recursive call)
   - Numbers ≥ 100 (hundreds + recursive call)

## Complexity Analysis
- **Time Complexity:** O(log n) where n is the input number
- **Space Complexity:** O(1) excluding output string
- **Justification:** Process each digit group once, constant space for arrays

## Edge Cases Considered
- [x] Number 0 (special case returns "Zero")
- [x] Numbers less than 20 (direct lookup)
- [x] Round numbers (100, 1000, etc.)
- [x] Maximum integer value
- [x] Numbers with trailing zeros in groups

## Solution Code

```java
// Language: Java
class Solution {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) return "";
        else if (num < 20) return LESS_THAN_20[num] + " ";
        else if (num < 100) return TENS[num / 10] + " " + helper(num % 10);
        else return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}
```

## Alternative Approaches
- **Recursive Division:** Pure recursive approach without arrays
- **Iterative with StringBuilder:** StringBuilder for efficiency
- **Lookup Tables:** More comprehensive lookup tables

## Related Problems
- **AC:** [Number system conversion problems]
- **Kunal:** [String manipulation and number processing]
- **LeetCode:** [Integer to English Words - Problem 273]

## Personal Notes
Complex string manipulation with number processing. Requires careful handling of edge cases and proper spacing. The recursive helper function elegantly handles the three-digit group conversion. Understanding English number naming conventions is crucial.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing Kunal Strings implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #strings #numberConversion #recursion #englishWords #hardProblem 