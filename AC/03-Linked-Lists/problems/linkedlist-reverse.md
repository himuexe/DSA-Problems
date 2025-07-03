# Reverse Linked List

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Reverse a singly linked list by changing the direction of pointers. Transform the list from 1→2→3→4→5 to 5→4→3→2→1. The reversal should be done in-place without using extra space.

## Intuition/Approach
Use the three-pointer technique to reverse the linked list iteratively. Maintain prev, curr, and next pointers. At each step, save the next node, reverse the current node's pointer, and move all pointers forward. This approach reverses the links in place.

## Key Observations
- Three-pointer technique is the standard approach for in-place reversal
- Must save next pointer before breaking the original link
- Original head becomes new tail after reversal
- Single pass through the list is sufficient
- No extra space needed beyond the three pointers

## Algorithm Steps
1. Initialize prev = null, curr = head, next = temp
2. Set tail = head (original head becomes new tail)
3. While curr != null:
   - Store next = curr.next
   - Reverse link: curr.next = prev
   - Move forward: prev = curr, curr = next
4. Update head = prev (prev becomes new head)

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the list
- **Space Complexity:** O(1) - Only using three pointers
- **Justification:** Visit each node once, constant extra space for pointers

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node list (head.next == null)
- [x] Two-node list (minimum case for reversal)
- [x] Proper head/tail pointer updates
- [x] Maintaining list integrity during reversal

## Solution Code

```java
// Language: Java
public class ReverseLL {
    class Node{
        int data;
        Node next;
        Node (int data ){
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void reverse(){
        Node prev = null;
        Node curr = tail = head;  // tail becomes original head
        Node next;
        
        while(curr != null){
            next = curr.next;      // Save next before breaking link
            curr.next = prev;      // Reverse the link
            prev = curr;           // Move prev forward
            curr = next;           // Move curr forward
        }
        head = prev;              // Update head to new first node
    }

    public void print(){
        if(head == null){
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
1. **Recursive**: Implement using recursion with O(n) stack space
2. **Stack-Based**: Use stack to reverse, requires O(n) extra space
3. **Two-Pass**: First pass to find length, second to reverse (unnecessary)
4. **Array Conversion**: Convert to array, reverse, rebuild (O(n) space)

## Related Problems
- **AC:** Reverse in Groups, Palindrome Check
- **Kunal:** Reverse variations, LinkedList manipulation
- **LeetCode:** #206 Reverse Linked List, #92 Reverse Linked List II, #25 Reverse Nodes in k-Group

## Personal Notes
- Most fundamental LinkedList algorithm - must master this pattern
- Three-pointer technique is used in many other LinkedList problems
- Key insight: save next before breaking links
- Foundation for more complex reversal problems

## Revision History
- **First Solve:** 2025-07-02 - Implemented three-pointer iterative reversal
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #reversal #three-pointer #in-place #iterative 