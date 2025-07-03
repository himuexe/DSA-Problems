# Linked List Iterative Search

**Source:** AC | **Topic:** LinkedList | **Difficulty:** Easy  
**Date Solved:** 2025-07-02 | **Revision Due:** 2025-07-09 | **Status:** Solved

---

## Problem Statement
Search for a key in a linked list using iterative approach and return the index of the first occurrence. Return -1 if key is not found. Traverse the list sequentially and maintain an index counter.

## Intuition/Approach
Use linear search by traversing the linked list from head to tail. Maintain an index counter and compare each node's data with the target key. Return the index immediately when found, or -1 if the entire list is traversed without finding the key.

## Key Observations
- Linear search is the only option for linked lists (no random access)
- Must traverse sequentially from head to find elements
- Index tracking is essential for returning position
- Early termination when key is found improves average case performance
- Returns first occurrence if duplicates exist

## Algorithm Steps
1. Check if list is empty (head == null), return -1
2. Initialize temp = head and index = 0
3. While temp != null:
   - Compare temp.data with key
   - If match found, return current index
   - Move to next: temp = temp.next, index++
4. Return -1 if key not found after complete traversal

## Complexity Analysis
- **Time Complexity:** O(n) - May need to traverse entire list
- **Space Complexity:** O(1) - Only using temporary variables
- **Justification:** Sequential access requires linear time, constant space

## Edge Cases Considered
- [x] Empty list (head == null)
- [x] Single node list
- [x] Key at first position (index 0)
- [x] Key at last position
- [x] Key not present in list
- [x] Duplicate keys (returns first occurrence)

## Solution Code

```java
// Language: Java
public class ItrSearchLL {
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

    public int search(int key){
        if(head == null){
            return -1;  // Empty list
        }
        
        Node temp = head;
        int i = 0;
        
        while(temp != null){
            if(temp.data == key){
                return i;  // Key found at index i
            }
            temp = temp.next;
            i++;
        }
        
        return -1;  // Key not found
    }

    public static void main(String[] args){
        ItrSearchLL ll = new ItrSearchLL();
        ll.addFirst(5);
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);
        System.out.println(ll.search(3));  // Output: 2
    }
}
```

## Alternative Approaches
1. **Recursive Search**: Implement recursively with O(n) stack space
2. **Enhanced Search**: Return node reference instead of index
3. **Multiple Occurrences**: Return all indices of matching elements
4. **Boolean Search**: Return true/false for existence check

## Related Problems
- **AC:** LinkedList basic operations, traversal problems
- **Kunal:** Search variations, LinkedList manipulation
- **LeetCode:** #83 Remove Duplicates, #141 Cycle Detection, #237 Delete Node

## Personal Notes
- Fundamental LinkedList operation - building block for other algorithms
- Key insight: sequential access only, no binary search possible
- Important to handle empty list case properly
- Foundation for more complex search and manipulation problems

## Revision History
- **First Solve:** 2025-07-02 - Implemented iterative linear search with index tracking
- **Review 1:** (scheduled for 2025-07-09)
- **Review 2:** (to be scheduled)

---
**Tags:** #linkedlist #linear-search #iterative #traversal #indexing 