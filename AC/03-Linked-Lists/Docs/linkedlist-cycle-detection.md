# Linked List Cycle Detection

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Detect if a linked list contains a cycle using Floyd's Cycle Detection Algorithm (Tortoise and Hare). Return true if cycle exists, false otherwise. A cycle exists when a node's next pointer points to a previously visited node.

## Intuition/Approach
Use Floyd's two-pointer technique where slow pointer moves 1 step and fast pointer moves 2 steps. If there's a cycle, the fast pointer will eventually meet the slow pointer inside the cycle. If there's no cycle, the fast pointer will reach null.

## Key Observations
- Fast pointer gains 1 position per iteration relative to slow pointer
- If cycle exists, pointers will definitely meet within the cycle
- Meeting only occurs if there's a cycle (no false positives)
- Single pass algorithm with constant space
- Must check fast and fast.next for null to avoid errors

## Algorithm Steps
1. Initialize slow = head, fast = head
2. While fast != null && fast.next != null:
   - Move slow = slow.next (1 step)
   - Move fast = fast.next.next (2 steps)
   - If slow == fast, return true (cycle detected)
3. Return false (fast reached null, no cycle)

## Complexity Analysis
- **Time Complexity:** O(n) - At most n iterations to detect cycle
- **Space Complexity:** O(1) - Only using two pointers
- **Justification:** Fast pointer will meet slow within cycle length iterations, constant space

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node without cycle
- [x] Single node with self-loop
- [x] Two nodes with/without cycle
- [x] Fast pointer reaching null
- [x] Very long cycle

## Solution Code

```java
// Language: Java
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
            
            if(slow == fast){
                return true;            // Cycle detected
            }
        }
        
        return false;                   // No cycle found
    }
}

// ListNode definition
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
```

## Alternative Approaches
1. **HashSet**: Store visited nodes, O(n) space complexity
2. **Marking**: Modify node values to mark visited (destructive)
3. **Slow Pointer Only**: Check if slow pointer revisits nodes (complex)
4. **Counting**: Limit iterations to detect infinite loops (imprecise)

## Related Problems
- **AC:** Remove Cycle, Palindrome Check (uses similar pattern)
- **Kunal:** Cycle problems, Two-pointer techniques
- **LeetCode:** #141 Linked List Cycle, #142 Linked List Cycle II, #287 Find Duplicate Number

## Personal Notes
- Classic application of Floyd's algorithm - fundamental technique
- Key insight: different speeds guarantee meeting in cycle
- Must master this for many LinkedList problems
- Foundation for finding cycle start point

## Revision History
- **First Solve:** 2025-07-02 - Implemented Floyd's cycle detection algorithm
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #floyd-algorithm #cycle-detection #two-pointer #tortoise-hare 