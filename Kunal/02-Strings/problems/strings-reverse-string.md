# Reverse String

**Difficulty:** Easy  
**Topic:** Strings, Two Pointers, In-place Modification  
**Source:** Kunal (Kunal Kushwaha)

## Problem Statement

Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array **in-place** with O(1) extra memory.

**Examples:**
```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## Approach & Intuition

### Two Pointers Technique:
The solution uses the classic two-pointers approach where we swap characters from both ends moving towards the center.

### Algorithm Steps:
1. **Initialize Pointers:** Set `left` to 0 and `right` to last index
2. **Swap and Move:** While `left < right`:
   - Swap characters at `left` and `right` positions
   - Move `left` forward and `right` backward
3. **Termination:** Stop when pointers meet or cross

### Key Insight:
By swapping characters from opposite ends and moving inward, we achieve complete reversal in exactly n/2 operations.

## Key Observations

1. **In-place Operation:** No additional array needed, modifies input directly
2. **Symmetric Swapping:** Each character swaps exactly once with its mirror position
3. **Optimal Movements:** Each iteration processes two characters (from both ends)
4. **Pointer Convergence:** Pointers meet in the middle for odd-length arrays

## Complexity Analysis

**Time Complexity:** O(n/2) = O(n) where n is the length of the array
- Each character is processed exactly once
- Total iterations: n/2

**Space Complexity:** O(1) 
- Only using a constant amount of extra space for pointers and temp variable
- In-place modification as required

## Edge Cases Considered

1. **Empty Array:** Loop won't execute, returns empty array
2. **Single Character:** No swapping needed, returns as-is
3. **Two Characters:** Single swap operation
4. **Odd Length:** Middle character stays in place

## Code Implementation

```java
public void reverseString(char[] s) {
    int left = 0;
    int right = s.length - 1;
    
    while (left < right) {
        // Swap characters at left and right positions
        char temp = s[right];
        s[right] = s[left];
        s[left] = temp;
        
        // Move pointers toward center
        left++;
        right--;
    }
}
```

## Alternative Solutions

1. **Recursion:** Recursive approach with two pointers
2. **Built-in Methods:** Using Collections.reverse() (not in-place)
3. **Stack-based:** Push all characters and pop to reverse (extra space)
4. **XOR Swapping:** Swap without temp variable using XOR operations

## Algorithm Walkthrough

**Example: ['h','e','l','l','o']**
```
Initial: left=0, right=4
Step 1: Swap s[0] and s[4] → ['o','e','l','l','h'], left=1, right=3
Step 2: Swap s[1] and s[3] → ['o','l','l','e','h'], left=2, right=2
Step 3: left >= right, stop
Result: ['o','l','l','e','h']
```

## Optimization Notes

1. **No Temp Variable:** Could use XOR swapping to eliminate temp variable
2. **Bit Manipulation:** For specific character sets, could use bitwise operations

## Cross-Reference

- **Related Problems:** Palindrome checking, array reversal
- **Prerequisites:** Two pointers technique, basic array manipulation
- **Next Steps:** Reverse words in string, advanced string manipulation

---

*Documented on: 2024-12-19*  
*Category: Strings (Two Pointers)* 