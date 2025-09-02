# Middle of the Linked List

**Source:** Kunal Kushwaha  
**Topic:** Linked Lists  
**Difficulty:** Easy

---

## Problem Statement

Given the head of a singly linked list, return the middle node of the linked list. If there are two middle nodes, return the second middle node.

### Examples
- **Input:** head = [1,2,3,4,5]
- **Output:** [3,4,5] (node 3)

- **Input:** head = [1,2,3,4,5,6]  
- **Output:** [4,5,6] (node 4, second middle)

---

## Intuition/Approach

Use Floyd's Tortoise and Hare algorithm (two-pointer technique):
- **Slow pointer:** Moves one step at a time
- **Fast pointer:** Moves two steps at a time
- When fast pointer reaches the end, slow pointer will be at the middle

**Algorithm Steps:**
1. Initialize slow and fast pointers to head
2. Move slow one step and fast two steps in each iteration
3. When fast reaches end, slow is at the middle
4. Return slow pointer

---

## Key Observations

- **Floyd's Algorithm:** Classic two-pointer technique for finding middle
- **Even Length:** Returns the second middle node (as per problem requirement)
- **Odd Length:** Returns the exact middle node
- **Optimal:** Single pass solution with O(1) space

---

## Algorithm Steps

1. **Initialize:** Set both slow and fast pointers to head
2. **Traverse:** Move slow by 1 and fast by 2 in each iteration
3. **Check Condition:** Continue while fast and fast.next are not null
4. **Return:** When loop ends, slow points to the middle node

---

## Time & Space Complexity

- **Time Complexity:** O(n) where n is the number of nodes
- **Space Complexity:** O(1) - only using two pointers

---

## Edge Cases

- [ ] Empty list (head = null)
- [ ] Single node list
- [ ] Two node list
- [ ] Even length list (returns second middle)
- [ ] Odd length list (returns exact middle)

---

## Solution Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
```

---

## Alternative Approaches

1. **Two-Pass Approach:** First pass to count nodes, second pass to find middle
2. **Array Approach:** Store all nodes in array, then return middle index
3. **Length-Based Approach:** Calculate length first, then traverse to middle

---

## Related Problems

- **LeetCode 141:** Linked List Cycle (uses same Floyd's algorithm)
- **LeetCode 142:** Linked List Cycle II
- **LeetCode 234:** Palindrome Linked List (uses middle finding)

---

## Personal Notes

- **LeetCode 876:** Classic easy-level problem that introduces Floyd's algorithm
- **Key Pattern:** Two-pointer technique with different speeds
- **Interview Tip:** Explain why this works mathematically
- **Practice Focus:** Foundation for more complex two-pointer problems

---

## Tags

`linked-list` `two-pointers` `floyds-algorithm` `easy` `middle-finding` 