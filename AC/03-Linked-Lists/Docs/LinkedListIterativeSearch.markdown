# Linked List Iterative Search

**Source:** AC | **Topic:** LinkedList, Linear Search | **Difficulty:** Easy

---

## Problem Statement
Search for a key in a singly linked list iteratively and return the index of the first occurrence, or -1 if not found.

## Intuition/Approach
Traverse the list from head to tail, maintaining an index counter. Compare each node’s data with the key, returning the index on a match or -1 after complete traversal.

## Key Observations
- Linear search is necessary due to sequential access.
- Index tracking allows returning the position.
- Early termination on finding the key improves average-case performance.
- Returns first occurrence for duplicate keys.

## Algorithm Steps
1. If head is null, return -1.
2. Initialize temp = head, index = 0.
3. While temp != null:
   - If temp.data == key, return index.
   - Move temp = temp.next, increment index.
4. Return -1 if key not found.

## Complexity Analysis
- **Time Complexity:** O(n) - Worst case traverses entire list.
- **Space Complexity:** O(1) - Only uses temporary variables.
- **Justification:** Sequential traversal with constant space.

## Edge Cases Considered
- [x] Empty list.
- [x] Single node.
- [x] Key at first/last position.
- [x] Key not present.
- [x] Duplicate keys (return first).
- [x] Large lists.

## Solution Code
```java
public class ItrSearchLL {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public int search(int key) {
        if (head == null) {
            return -1;
        }
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }
}
```

## Alternative Approaches
- **Recursive Search:** Use recursion (O(n) stack space).
- **Return Node:** Return node reference instead of index.
- **All Occurrences:** Collect all indices of key (O(n) space).
- **Boolean Search:** Return true/false for existence (simpler).

## Personal Notes
- Simple but foundational for linked list traversal.
- Changed static members to instance variables for better encapsulation.
- Early termination is key for efficiency in average cases.

---
**Tags:** #linkedlist #linear_search #iterative #traversal