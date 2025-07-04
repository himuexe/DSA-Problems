# Recursive Insertion in Linked List

**Source:** Kunal Kushwaha  
**Topic:** Linked Lists  
**Difficulty:** Easy

---

## Problem Statement

Implement recursive methods to insert elements in a linked list:
1. Insert at the end of the list
2. Insert at a specific position (0-based index)

### Examples
- **Insert at End:** [10, 20] → insertRecursive(30) → [10, 20, 30]
- **Insert at Position:** [10, 20, 30] → insertAtPosition(15, 1) → [10, 15, 20, 30]

---

## Intuition/Approach

Use recursion to traverse the linked list and insert elements:
1. **Base Case:** When we reach the desired position or end of list
2. **Recursive Case:** Continue traversing while making recursive calls
3. **Return:** Return the current node after recursive insertion

**Algorithm Steps:**
1. Define base cases for insertion
2. Make recursive calls to traverse the list
3. Insert the new node at the appropriate position
4. Return the updated list structure

---

## Key Observations

- **Recursion:** Natural fit for linked list operations
- **Base Cases:** Different base cases for end insertion vs position insertion
- **Node Creation:** Create new nodes in base cases
- **Pointer Updates:** Recursive calls handle pointer updates automatically

---

## Algorithm Steps

**For End Insertion:**
1. **Base Case:** If current node is null, create and return new node
2. **Recursive Case:** Set current.next = insertRecursive(current.next, data)
3. **Return:** Return current node

**For Position Insertion:**
1. **Base Case:** If position is 0 or current is null, create new node
2. **Recursive Case:** Set current.next = insertAtPosition(current.next, data, position-1)
3. **Return:** Return current node

---

## Time & Space Complexity

- **Time Complexity:** O(n) where n is the position for insertion
- **Space Complexity:** O(n) due to recursion stack

---

## Edge Cases

- [ ] Empty list (head = null)
- [ ] Insert at position 0 (head insertion)
- [ ] Insert at end of list
- [ ] Insert at position greater than list length
- [ ] Single node list

---

## Solution Code

```java
class Node {
    int data;
    Node next;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    
    // Recursive method to insert at end of the list
    public void insertRecursive(int data) {
        head = insertRecursive(head, data);
    }
    
    private Node insertRecursive(Node current, int data) {
        // Base case: if we've reached the end, create new node
        if (current == null) {
            return new Node(data);
        }
        
        // Recursive case: keep traversing until we reach the end
        current.next = insertRecursive(current.next, data);
        return current;
    }
    
    // Recursive method to insert at specific position (0-based index)
    public void insertAtPositionRecursive(int data, int position) {
        head = insertAtPositionRecursive(head, data, position);
    }
    
    private Node insertAtPositionRecursive(Node current, int data, int position) {
        // Base case 1: position is 0 or list is empty
        if (position == 0 || current == null) {
            Node newNode = new Node(data);
            newNode.next = current;
            return newNode;
        }
        
        // Recursive case: move to next node and decrement position
        current.next = insertAtPositionRecursive(current.next, data, position - 1);
        return current;
    }
    
    // Method to print the linked list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
```

---

## Alternative Approaches

1. **Iterative Approach:** Use loops instead of recursion (more memory efficient)
2. **Dummy Node Approach:** Use dummy node to simplify edge cases
3. **Tail Pointer Approach:** Maintain tail pointer for O(1) end insertion

---

## Related Problems

- **LeetCode 21:** Merge Two Sorted Lists (uses recursion)
- **LeetCode 206:** Reverse Linked List (recursive approach)
- **LeetCode 2:** Add Two Numbers (recursive solution possible)

---

## Personal Notes

- **Educational Pattern:** Great for understanding recursion in linked lists
- **Key Pattern:** Recursive traversal with base case handling
- **Interview Tip:** Discuss both recursive and iterative approaches
- **Practice Focus:** Understanding recursion stack and base cases

---

## Tags

`linked-list` `recursion` `insertion` `easy` `educational` 