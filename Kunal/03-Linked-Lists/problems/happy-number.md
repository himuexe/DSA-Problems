# Happy Number

**Source:** Kunal Kushwaha  
**Topic:** Linked Lists (Floyd's Algorithm Application)  
**Difficulty:** Easy

---

## Problem Statement

Write an algorithm to determine if a number n is happy. A happy number is a number defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
- Those numbers for which this process ends in 1 are happy.

### Examples
- **Input:** n = 19
- **Output:** true
- **Explanation:** 1² + 9² = 82, 8² + 2² = 68, 6² + 8² = 100, 1² + 0² + 0² = 1

- **Input:** n = 2  
- **Output:** false

---

## Intuition/Approach

The key insight is that this problem can be modeled as cycle detection in a "linked list" where:
- Each number points to its next number (sum of squares of digits)
- If we reach 1, it's a happy number
- If we enter a cycle that doesn't include 1, it's not happy

**Algorithm Steps:**
1. Use Floyd's cycle detection algorithm (tortoise and hare)
2. Slow pointer moves one step, fast pointer moves two steps
3. If they meet and both are at 1, it's happy
4. If they meet at any other number, it's not happy

---

## Key Observations

- **Cycle Detection:** This is fundamentally a cycle detection problem
- **Floyd's Algorithm:** Use slow and fast pointers to detect cycles
- **Sum of Squares:** Helper function to calculate sum of squares of digits
- **Termination:** Either reaches 1 (happy) or enters a cycle (not happy)

---

## Algorithm Steps

1. **Initialize:** Set slow and fast pointers to the input number
2. **Move Pointers:** Slow moves one step, fast moves two steps
3. **Calculate Sum:** Use helper function to get sum of squares of digits
4. **Check Meeting:** Continue until slow and fast pointers meet
5. **Determine Result:** If they meet at 1, it's happy; otherwise, not happy

---

## Time & Space Complexity

- **Time Complexity:** O(log n) where n is the input number
- **Space Complexity:** O(1) - only using two pointers

---

## Edge Cases

- [ ] n = 1 (happy number)
- [ ] Single digit numbers (2-9)
- [ ] Large numbers (efficiency consideration)
- [ ] Numbers that quickly enter cycles
- [ ] Numbers that take many steps to reach 1

---

## Solution Code

```java
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        
        do {
            slow = findSquare(slow);
            fast = findSquare(findSquare(fast));
        } while (slow != fast);
        
        if (slow == 1) {
            return true;
        }
        return false;
    }
    
    private int findSquare(int n) {
        int ans = 0;
        while (n > 0) {
            int rem = n % 10;
            ans += rem * rem;
            n /= 10;
        }
        return ans;
    }
}
```

---

## Alternative Approaches

1. **HashSet Approach:** Use set to track visited numbers and detect cycles
2. **Recursive Approach:** Use recursion with memoization
3. **Mathematical Approach:** Pre-compute all possible cycles

---

## Related Problems

- **LeetCode 141:** Linked List Cycle (same Floyd's algorithm)
- **LeetCode 142:** Linked List Cycle II
- **LeetCode 876:** Middle of the Linked List

---

## Personal Notes

- **LeetCode 202:** Interesting application of Floyd's algorithm to non-linked list problems
- **Key Pattern:** Cycle detection in mathematical sequences
- **Interview Tip:** Explain the connection to linked list cycle detection
- **Practice Focus:** Understanding how Floyd's algorithm applies beyond linked lists

---

## Tags

`floyds-algorithm` `cycle-detection` `mathematical` `two-pointers` `easy` 