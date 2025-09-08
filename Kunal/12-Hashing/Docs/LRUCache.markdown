# LRU Cache

**Source:** Kunal | **Topic:** LinkedList, HashMap, System Design | **Difficulty:** Medium

---

## Problem Statement
Design an LRU (Least Recently Used) Cache with `get(key)` and `put(key, value)` operations in O(1) time complexity. Maintain the order of usage, evicting the least recently used item when capacity is exceeded.

## Intuition/Approach
Use a HashMap for O(1) key-to-node lookup and a doubly linked list for O(1) insertion/deletion. The list maintains usage order: head for most recently used, tail for least recently used. Dummy head/tail nodes simplify edge cases.

## Key Observations
- HashMap provides instant access to nodes.
- Doubly linked list enables O(1) updates for usage order.
- Dummy nodes avoid special cases for head/tail operations.
- Eviction occurs at the tail when capacity is exceeded.

## Algorithm Steps
1. **Initialize:** Create HashMap, dummy head/tail nodes, set capacity.
2. **Get(key):**
   - If key exists in HashMap, move node to head, return value.
   - Else, return -1.
3. **Put(key, value):**
   - If key exists, update value, move node to head.
   - If new key, create node, add to head, update HashMap.
   - If capacity exceeded, remove tail node, update HashMap.

## Complexity Analysis
- **Time Complexity:** O(1) - Both get and put operations.
- **Space Complexity:** O(capacity) - HashMap and list storage.
- **Justification:** HashMap lookups and list operations (add/remove/move) are constant-time.

## Edge Cases Considered
- [x] Capacity = 1.
- [x] Empty cache (get returns -1).
- [x] Cache full (evict tail).
- [x] Update existing key.
- [x] Repeated operations on same key.
- [x] Large capacity.

## Solution Code
```java
import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode() {}
        DLinkedNode(int key, int value) { this.key = key; this.value = value; }
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

    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
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
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
            if (size > capacity) {
                DLinkedNode tailNode = popTail();
                cache.remove(tailNode.key);
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
- **LinkedHashMap:** Use Java’s built-in access-order map (O(1) time, simpler code).
- **Array + HashMap:** Shift elements in array (O(n) time, inefficient).
- **Singly Linked List:** Requires O(n) deletion (less efficient).

## Personal Notes
- Combining HashMap and doubly linked list is a classic system design pattern.
- Dummy nodes greatly simplify edge case handling.
- This problem bridges data structures and real-world application.

---
**Tags:** #lru_cache #doubly_linkedlist #hashmap #system_design #medium