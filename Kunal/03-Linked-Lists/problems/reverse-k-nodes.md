# Reverse Nodes in k-Group

**Source:** Kunal Kushwaha  
**Topic:** Linked Lists  
**Difficulty:** Hard

---

## Problem Statement

Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list. If the number of nodes is not a multiple of k, the remaining nodes should be left as they are.

### Examples
- **Input:** head = [1,2,3,4,5], k = 2
- **Output:** [2,1,4,3,5]

- **Input:** head = [1,2,3,4,5], k = 3  
- **Output:** [3,2,1,4,5]

---

## Intuition/Approach

The approach uses recursion to handle the reversal of k-groups:
1. Count if we have at least k nodes remaining
2. If yes, reverse the first k nodes and recursively handle the rest
3. If no, return the current head as is

**Algorithm Steps:**
1. Count nodes to ensure we have k nodes available
2. If k nodes available, reverse the first k nodes
3. Recursively reverse the remaining list
4. Connect the reversed portion with the recursively processed remainder

---

## Key Observations

- **Recursion:** Each recursive call handles one k-group
- **Base Case:** When fewer than k nodes remain, return as is
- **Helper Function:** Separate function to reverse exactly k nodes
- **Connection:** After reversing k nodes, connect with recursively processed remainder

---

## Algorithm Steps

1. **Count Nodes:** Check if at least k nodes are available
2. **Reverse K Nodes:** If available, reverse the first k nodes
3. **Recursive Call:** Process the remaining list recursively
4. **Connect:** Link the reversed portion with the processed remainder
5. **Return:** Return the new head of the reversed k-group

---

## Time & Space Complexity

- **Time Complexity:** O(n) where n is the number of nodes
- **Space Complexity:** O(n/k) for recursion stack, where n/k is the number of k-groups

---

## Edge Cases

- [ ] Empty list (head = null)
- [ ] Single node list
- [ ] k = 1 (no reversal needed)
- [ ] k >= list length (reverse entire list)
- [ ] Remaining nodes < k (leave as is)

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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Count the number of nodes in the list
        int count = 0;
        ListNode curr = head;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If we have k nodes, then we reverse them
        if (count == k) {
            // Reverse the first k nodes and get the new head
            ListNode reversedHead = reverseLinkedList(head, k);
            
            // Recursively reverse the remaining list and connect it
            head.next = reverseKGroup(curr, k);
            
            return reversedHead;
        }
        
        // If we don't have k nodes left, return head as is
        return head;
    }
    
    // Helper function to reverse the first k nodes of a linked list
    private ListNode reverseLinkedList(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (k > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }
        
        return prev;
    }
}
```

---

## Alternative Approaches

1. **Iterative Approach:** Use stack to store k nodes, then pop and reconnect
2. **Two-Pointer Approach:** Use two pointers to track k-group boundaries
3. **Dummy Node Approach:** Use a dummy node to simplify edge case handling

---

## Related Problems

- **LeetCode 206:** Reverse Linked List
- **LeetCode 92:** Reverse Linked List II
- **LeetCode 61:** Rotate List

---

## Personal Notes

- **LeetCode 25:** This is a classic hard-level linked list problem
- **Key Pattern:** Recursion with helper functions for complex list manipulation
- **Interview Tip:** Discuss both recursive and iterative approaches
- **Practice Focus:** Understanding the connection between reversed portions and recursion

---

## Tags

`linked-list` `recursion` `hard` `k-group-reversal` `list-manipulation` 