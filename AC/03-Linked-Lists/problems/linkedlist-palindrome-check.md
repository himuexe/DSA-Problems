# Linked List Palindrome Check

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Medium  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Check if a linked list is a palindrome by comparing the first half with the reversed second half. Return true if the linked list reads the same forward and backward, false otherwise.

## Intuition/Approach
Use a three-phase approach: 1) Find the middle using Floyd's algorithm, 2) Reverse the second half of the list, 3) Compare the first half with the reversed second half. This avoids using extra space while maintaining O(n) time complexity.

## Key Observations
- Floyd's algorithm efficiently finds the middle in one pass
- For odd length lists, middle element doesn't affect palindrome check
- Reversing second half allows direct comparison with first half
- Only need to compare until shorter half is exhausted
- Can be done in O(1) space complexity

## Algorithm Steps
1. Use slow/fast pointers to find middle of the list
2. Reverse the second half starting from middle
3. Compare first half with reversed second half node by node
4. Return false if any mismatch found, true otherwise

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass to find middle + reverse + compare
- **Space Complexity:** O(1) - Only using pointers, no extra space
- **Justification:** Three separate O(n) operations, constant space usage

## Edge Cases Considered
- [x] Empty list (return true)
- [x] Single node (return true)
- [x] Two nodes (compare directly)
- [x] Odd vs even length lists
- [x] All same elements
- [x] Non-palindrome cases

## Solution Code

```java
// Language: Java
public class PalindromeLL {
    class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    
    public static Node head;

    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // Find middle using Floyd's algorithm
    public Node findMid(){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;  // slow is at middle
    }

    // Check if linked list is palindrome
    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return true;  // Empty or single node is palindrome
        }
        
        // Step 1: Find middle
        Node mid = findMid();
        
        // Step 2: Reverse second half
        Node curr = mid;
        Node prev = null;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;  // Head of reversed second half
        
        // Step 3: Compare first half with reversed second half
        Node left = head;
        while(right != null){
            if(left.data != right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
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
1. **Stack-Based**: Use stack to store first half, O(n) space
2. **Array Conversion**: Convert to array, use two pointers, O(n) space
3. **Recursive**: Recursive comparison, O(n) stack space
4. **String Conversion**: Convert to string and check, O(n) space

## Related Problems
- **AC:** Reverse LinkedList, Find Middle
- **Kunal:** LinkedList variations, Two-pointer problems
- **LeetCode:** #234 Palindrome Linked List, #9 Palindrome Number, #125 Valid Palindrome

## Personal Notes
- Combines multiple LinkedList techniques: Floyd's algorithm + reversal + comparison
- Key insight: only need to compare until shorter half exhausted
- Important to handle odd/even length lists correctly
- Could restore original list if needed (additional step)

## Revision History
- **First Solve:** 2025-07-02 - Implemented find middle + reverse + compare approach
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #palindrome #floyd-algorithm #reversal #two-pointer 