# First Non-Repeating Character in Stream/String

**Source:** AC | **Topic:** Queues | **Difficulty:** Easy  

---

## Problem Statement
Given a lowercase string, return the first character that does not repeat. If none, return the null character `\0`.

## Intuition/Approach
Count frequencies with an array of size 26 and push all characters into a queue preserving order. Pop from the queue until its front has frequency 1.

## Key Observations
- Queue maintains order of appearance.
- Frequency array provides O(1) lookups.
- One pass to fill counts and queue; one pass to find result.

## Algorithm Steps
1. Initialize `int[26] charCount` and `Queue<Character> q`.
2. For each char `c` in string: increment `charCount[c-'a']`, enqueue `c`.
3. While queue not empty and front count > 1, dequeue.
4. If queue empty, return `\0`; else return front.

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Justification:** Each character enqueued/dequeued at most once.

## Edge Cases Considered
- [x] Empty string
- [x] All repeating characters
- [x] First char unique
- [ ] Uppercase or non-letters (not supported by given code)

## Solution Code

```java
import java.util.*;
public class FirstNonRepeatingCharacter {
    public static char firstNonRepeatingCharacter(String str) {
        int[] charCount = new int[26];
        Queue<Character> queue = new LinkedList<>();
        for (char c : str.toCharArray()) {
            charCount[c - 'a']++;
            queue.add(c);
        }
        while (!queue.isEmpty()) {
            char current = queue.remove();
            if (charCount[current - 'a'] == 1) {
                return current;
            }
        }
        return '\0';
    }
}
```

## Alternative Approaches
- Use `LinkedHashMap<Character,Integer>` to track order and counts.
- Two-pass approach without queue if only the final result is needed.

## Personal Notes
Assumes only `a-z`. Extend to full ASCII/Unicode if needed.

---
**Tags:** #queues #string #frequency #first-unique
