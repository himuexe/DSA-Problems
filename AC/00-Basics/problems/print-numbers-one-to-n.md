# Print Numbers from 1 to N

**Source:** AC | **Topic:** 00-Basics | **Difficulty:** Easy  
**Date Solved:** 2024-12-19 | **Revision Due:** 2024-12-20 | **Status:** Solved

---

## Problem Statement
Print all numbers from 1 to N (inclusive) where N is a given positive integer. Each number should be printed on a new line.

## Intuition/Approach
Use a simple for loop to iterate from 1 to N and print each number. This demonstrates basic loop structure and output formatting.

## Key Observations
- Simple iteration from 1 to N (inclusive)
- Each number printed on separate line
- Basic loop control and output operations
- Foundation for more complex iteration patterns

## Algorithm Steps
1. Take input number N from user
2. Initialize loop from i = 1
3. Continue while i <= N
4. Print current value of i
5. Increment i and repeat

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Justification:** Single loop through n numbers, constant extra space

## Edge Cases Considered
- [x] N = 1 (prints single number)
- [x] N > 1 (prints full sequence)
- [x] Large values of N
- [ ] N = 0 or negative (not handled in current implementation)

## Solution Code

```java
// Language: Java
public static void printNumbers(int number){
    for(int i = 1; i <= number; i++){
        System.out.println(i);
    }
}
```

## Alternative Approaches
- **While Loop:** Using while loop instead of for loop
- **Recursive:** Recursive function to print numbers (less efficient)
- **Stream API:** IntStream.rangeClosed(1,n).forEach(System.out::println)

## Related Problems
- **AC:** [SumN.java - similar iteration patterns]
- **Kunal:** [Fibonacci.java - sequence generation]
- **LeetCode:** [Fizz Buzz - Problem 412]

## Personal Notes
Most basic loop example. Essential for understanding iteration, loop control, and output formatting. Foundation for all sequence generation problems.

## Revision History
- **First Solve:** 2024-12-19 - Documented from existing AC 00-Basics implementation
- **Review 1:** [Pending] - [Notes]
- **Review 2:** [Pending] - [Notes]

---
**Tags:** #basics #loops #iteration #printing #fundamentals 