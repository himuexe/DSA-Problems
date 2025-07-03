# Reorder Linked List

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Hard  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Reorder a linked list from L₀→L₁→L₂→...→Lₙ₋₁→Lₙ to L₀→Lₙ→L₁→Lₙ₋₁→L₂→Lₙ₋₂→... by alternating between nodes from beginning and end. Transform in-place without using extra space.

## Intuition/Approach
Use a three-phase approach: 1) Find middle using Floyd's algorithm and split list, 2) Reverse the second half to access nodes from end, 3) Merge both halves alternately. This provides O(n) time complexity with O(1) space.

## Key Observations
- Three independent phases each with specific purpose
- Floyd's algorithm efficiently finds middle without counting
- Reversing second half enables backward access
- Alternating merge creates desired reordering pattern
- In-place operation with only pointer manipulation

## Algorithm Steps
1. Phase 1: Find middle using slow/fast pointers and split list
2. Phase 2: Reverse second half using three-pointer technique
3. Phase 3: Merge alternately - take node from first half, then second half
4. Handle remaining nodes (first half may have one extra for odd length)
5. Ensure proper null termination

## Complexity Analysis
- **Time Complexity:** O(n) - Three separate linear passes
- **Space Complexity:** O(1) - Only using pointers
- **Justification:** Find middle O(n), reverse O(n), merge O(n) = 3n = O(n)

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node list (no reordering needed)
- [x] Two-node list (already in correct order)
- [x] Odd vs even length lists
- [x] Three-node list (minimum case for reordering)

## Solution Code

```java
// Language: Java
public class ReorderLL {
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

    public void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        // Phase 1: Find the middle of the list
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Phase 2: Reverse the second half
        Node secondHalf = slow.next;
        slow.next = null;  // Split the list
        Node prev = null;
        Node curr = secondHalf;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Phase 3: Merge the two halves alternately
        Node first = head;
        Node second = prev;  // prev is the new head of reversed second half
        while (second != null) {
            Node temp1 = first.next;
            Node temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
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
1. **Stack-Based**: Use stack to reverse second half, O(n) space
2. **Array Conversion**: Convert to array, reorder, rebuild list
3. **Recursive**: Recursive approach with O(n) stack space
4. **Deque-Based**: Use deque for front/back access

## Related Problems
- **AC:** Reverse LinkedList, Find Middle, Merge operations
- **Kunal:** Advanced LinkedList algorithms, Pattern combinations
- **LeetCode:** #143 Reorder List, #206 Reverse Linked List, #876 Middle of Linked List

## Personal Notes
- Combines multiple LinkedList techniques in one problem
- Key insight: three-phase approach simplifies complex problem
- Important to handle odd/even length lists correctly
- Demonstrates mastery of fundamental LinkedList operations

## Revision History
- **First Solve:** 2025-07-02 - Implemented three-phase reorder algorithm
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #reorder #three-phase #floyd-algorithm #advanced 