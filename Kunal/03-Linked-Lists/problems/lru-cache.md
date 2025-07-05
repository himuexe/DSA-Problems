# LRU Cache

**Source:** Kunal | **Topic:** Linked Lists/Hash Tables | **Difficulty:** Medium  
**Date Solved:** 2025-07-04 | **Revision Due:** 2025-07-11 | **Status:** Solved

---

## Problem Statement
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement the LRUCache class with get(key) and put(key, value) methods, both operating in O(1) time complexity.

## Intuition/Approach
Combine HashMap for O(1) access and Doubly Linked List for O(1) insertion/deletion. HashMap maps keys to nodes, while DLL maintains order of usage. Head represents most recently used, tail represents least recently used.

## Key Observations
- Need O(1) access: HashMap provides key-to-node mapping
- Need O(1) insertion/deletion: Doubly linked list with dummy head/tail
- Head side = most recently used, Tail side = least recently used
- Move to head on access, remove from tail when capacity exceeded

## Algorithm Steps
1. **Get Operation**: Check HashMap, if exists move to head and return value
2. **Put Operation**: 
   - If key exists: update value and move to head
   - If new key: create node, add to head, update HashMap
   - If capacity exceeded: remove tail node and update HashMap

## Complexity Analysis
- **Time Complexity:** O(1) - Both get and put operations
- **Space Complexity:** O(capacity) - HashMap and DLL storage
- **Justification:** HashMap provides O(1) lookup, DLL provides O(1) insertion/deletion

## Edge Cases Considered
- [x] Capacity = 1 (single element cache)
- [x] Get from empty cache
- [x] Put when cache is full
- [x] Update existing key
- [x] Multiple operations on same key

## Solution Code

```java
class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            size++;
            if (size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
```

## Alternative Approaches
- **LinkedHashMap**: Java's built-in implementation with access-order
- **Array + HashMap**: Less efficient due to O(n) shifts
- **Single Linked List**: Requires O(n) for deletion without prev pointer

## Related Problems
- **AC:** [Cache implementation problems]
- **Kunal:** [Hash table and linked list combinations]
- **LeetCode:** [146. LRU Cache]

## Personal Notes
Classic system design problem requiring hybrid data structure. The dummy head/tail technique simplifies edge cases. Key insight is maintaining both access speed (HashMap) and order (DLL) simultaneously.

## Revision History
- **First Solve:** 2025-07-04 - Implemented HashMap + Doubly Linked List solution
- **Review 1:** 2025-07-11 - [Notes]
- **Review 2:** 2025-07-18 - [Notes]

---
**Tags:** #lru #cache #hashmap #doublylinkedlist #medium #systemdesign 