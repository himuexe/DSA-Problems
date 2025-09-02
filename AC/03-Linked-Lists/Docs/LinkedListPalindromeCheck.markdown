# Linked List Palindrome Check

**Source:** AC | **Topic:** LinkedList, Two Pointers | **Difficulty:** Medium

---

## Problem Statement
Check if a singly linked list is a palindrome by comparing the first half with the reversed second half.

## Intuition/Approach
Use a three-step approach:
- Find the middle using Floyd’s algorithm.
- Reverse the second half of the list.
- Compare the first half with the reversed second half node by node.

## Key Observations
- Floyd’s algorithm efficiently locates the middle.
- Reversing the second half enables direct comparison.
- Only compare up to the length of the shorter half.
- Odd-length lists ignore the middle element.
- In-place approach uses O(1) space.

## Algorithm Steps
1. If list is empty or single-node, return true.
2. Find middle using slow/fast pointers (fast starts at head.next).
3. Reverse second half starting from middle.
4. Compare first half (from head) with reversed second half.
5. Return false on mismatch, true if all match.

## Complexity Analysis
- **Time Complexity:** O(n) - Three linear passes (middle, reverse, compare).
- **Space Complexity:** O(1) - Only pointers used.
- **Justification:** Each phase is linear, with no extra data structures.

## Edge Cases Considered
- [x] Empty list (palindrome).
- [x] Single node (palindrome).
- [x] Two nodes (compare directly).
- [x] Odd vs. even length lists.
- [x] All identical elements.
- [x] Non-palindrome cases.

## Solution Code
```java
public class PalindromeLL {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public Node findMid() {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        Node mid = findMid();
        Node curr = mid.next;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public void print() {
        if (head == null) {
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
```

## Alternative Approaches
- **Stack-Based:** Store first half in stack (O(n) space).
- **Array Conversion:** Convert to array, use two pointers (O(n) space).
- **Recursive:** Compare recursively (O(n) stack space).
- **String Conversion:** Convert to string, check palindrome (O(n) space).

## Personal Notes
- Combining Floyd’s algorithm and reversal makes this elegant.
- Reversing only the second half saves time and space.
- Restoring the list (optional) would require another reversal.

---
**Tags:** #linkedlist #palindrome #floyd_algorithm #reversal #two_pointers