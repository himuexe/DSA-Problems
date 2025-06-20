package nodes;

import java.util.*;

/**
 * BTreeNode class representing a single node in a B-Tree (multi-way search tree).
 * Optimized for disk-based storage systems and database indexing applications.
 * 
 * A B-Tree is a self-balancing tree data structure that maintains sorted data
 * and allows searches, sequential access, insertions, and deletions in logarithmic time.
 * Unlike binary trees, B-Tree nodes can have multiple keys and children, making
 * them ideal for systems that read and write large blocks of data.
 * 
 * Key Features:
 * - Multi-way branching (degree-based node capacity)
 * - Sorted key storage within nodes
 * - Balanced height through split/merge operations
 * - Optimized for disk I/O operations
 * - Database indexing applications
 * 
 * B-Tree Properties:
 * 1. Every node has at most 2t-1 keys (where t is minimum degree)
 * 2. Every internal node has at least t-1 keys
 * 3. Root has at least 1 key (if tree is non-empty)
 * 4. All leaves are at the same level
 * 5. Keys within each node are stored in sorted order
 * 6. Each internal node with k keys has k+1 children
 * 
 * Applications:
 * - Database indexing (B+ trees in SQL databases)
 * - File systems (NTFS, HFS+, ext4)
 * - Search engines and information retrieval
 * - Memory management systems
 * - Large-scale data storage and retrieval
 * 
 * Time Complexities:
 * - Search: O(log n)
 * - Insert: O(log n)
 * - Delete: O(log n)
 * - Traverse: O(n)
 * 
 * Space Complexity: O(n) where n is the number of keys stored
 * 
 * @param <T> the type of keys stored in this B-Tree node (must be Comparable)
 * @author DSA Learning Project
 * @version 1.0
 */
public class BTreeNode<T extends Comparable<T>> {
    
    /** Maximum number of keys this node can hold (2t-1 where t is minimum degree) */
    public int maxKeys;
    
    /** Minimum number of keys this node must hold (t-1 where t is minimum degree) */
    public int minKeys;
    
    /** Minimum degree of the B-Tree (defines node capacity) */
    public int minDegree;
    
    /** List of keys stored in this node (sorted order) */
    public List<T> keys;
    
    /** List of child pointers (size = keys.size() + 1 for internal nodes) */
    public List<BTreeNode<T>> children;
    
    /** Flag indicating if this node is a leaf */
    public boolean isLeaf;
    
    /** Reference to parent node (useful for tree operations) */
    public BTreeNode<T> parent;
    
    /** Node identifier for debugging and visualization */
    public int nodeId;
    
    /** Static counter for unique node IDs */
    private static int nodeCounter = 0;
    
    /**
     * Constructor for B-Tree node with specified minimum degree.
     * Creates an empty node with capacity based on minimum degree.
     * 
     * @param minDegree the minimum degree t (node capacity = 2t-1 keys, 2t children)
     * @param isLeaf whether this node is a leaf
     * @throws IllegalArgumentException if minDegree < 2
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(t) where t is minimum degree
     */
    public BTreeNode(int minDegree, boolean isLeaf) {
        if (minDegree < 2) {
            throw new IllegalArgumentException("Minimum degree must be at least 2, got: " + minDegree);
        }
        
        this.minDegree = minDegree;
        this.maxKeys = 2 * minDegree - 1;
        this.minKeys = minDegree - 1;
        this.isLeaf = isLeaf;
        this.parent = null;
        this.nodeId = ++nodeCounter;
        
        this.keys = new ArrayList<>(maxKeys);
        this.children = new ArrayList<>(maxKeys + 1);
        
        // Initialize children list for internal nodes
        if (!isLeaf) {
            for (int i = 0; i <= maxKeys; i++) {
                children.add(null);
            }
        }
    }
    
    /**
     * Copy constructor for creating a node with same properties.
     * 
     * @param other the node to copy properties from
     * @param isLeaf whether the new node should be a leaf
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(t)
     */
    public BTreeNode(BTreeNode<T> other, boolean isLeaf) {
        this(other.minDegree, isLeaf);
        this.parent = other.parent;
    }
    
    // ==================== KEY OPERATIONS ====================
    
    /**
     * Searches for a key in this node.
     * Returns the index where key is found, or -1 if not found.
     * 
     * @param key the key to search for
     * @return index of key if found, -1 otherwise
     * @throws IllegalArgumentException if key is null
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public int findKey(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Finds the index where a key should be inserted to maintain sorted order.
     * Uses binary search for efficiency.
     * 
     * @param key the key to find insertion position for
     * @return the index where key should be inserted
     * @throws IllegalArgumentException if key is null
     * 
     * Time Complexity: O(log t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public int findInsertionIndex(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        int left = 0, right = keys.size() - 1;
        int result = keys.size(); // Default to end of list
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = keys.get(mid).compareTo(key);
            
            if (comparison > 0) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Inserts a key at the specified index.
     * Maintains sorted order by shifting elements if necessary.
     * 
     * @param index the index to insert at
     * @param key the key to insert
     * @throws IllegalArgumentException if key is null or index is invalid
     * @throws IllegalStateException if node is full
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public void insertKeyAt(int index, T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (index < 0 || index > keys.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        if (isFull()) {
            throw new IllegalStateException("Cannot insert into full node");
        }
        
        keys.add(index, key);
    }
    
    /**
     * Removes the key at the specified index.
     * 
     * @param index the index of the key to remove
     * @return the removed key
     * @throws IllegalArgumentException if index is invalid
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public T removeKeyAt(int index) {
        if (index < 0 || index >= keys.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        
        return keys.remove(index);
    }
    
    /**
     * Gets the key at the specified index.
     * 
     * @param index the index of the key to get
     * @return the key at the specified index
     * @throws IllegalArgumentException if index is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T getKey(int index) {
        if (index < 0 || index >= keys.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        
        return keys.get(index);
    }
    
    // ==================== CHILD OPERATIONS ====================
    
    /**
     * Gets the child at the specified index.
     * 
     * @param index the index of the child to get
     * @return the child at the specified index
     * @throws IllegalArgumentException if index is invalid
     * @throws IllegalStateException if this is a leaf node
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BTreeNode<T> getChild(int index) {
        if (isLeaf) {
            throw new IllegalStateException("Leaf nodes have no children");
        }
        if (index < 0 || index >= children.size()) {
            throw new IllegalArgumentException("Invalid child index: " + index);
        }
        
        return children.get(index);
    }
    
    /**
     * Sets the child at the specified index.
     * 
     * @param index the index to set the child at
     * @param child the child node to set
     * @throws IllegalArgumentException if index is invalid
     * @throws IllegalStateException if this is a leaf node
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setChild(int index, BTreeNode<T> child) {
        if (isLeaf) {
            throw new IllegalStateException("Leaf nodes cannot have children");
        }
        if (index < 0 || index >= children.size()) {
            throw new IllegalArgumentException("Invalid child index: " + index);
        }
        
        children.set(index, child);
        if (child != null) {
            child.parent = this;
        }
    }
    
    /**
     * Inserts a child at the specified index.
     * Shifts existing children to make room.
     * 
     * @param index the index to insert the child at
     * @param child the child node to insert
     * @throws IllegalArgumentException if index is invalid
     * @throws IllegalStateException if this is a leaf node
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public void insertChildAt(int index, BTreeNode<T> child) {
        if (isLeaf) {
            throw new IllegalStateException("Leaf nodes cannot have children");
        }
        if (index < 0 || index > children.size()) {
            throw new IllegalArgumentException("Invalid child index: " + index);
        }
        
        children.add(index, child);
        if (child != null) {
            child.parent = this;
        }
    }
    
    /**
     * Removes the child at the specified index.
     * 
     * @param index the index of the child to remove
     * @return the removed child
     * @throws IllegalArgumentException if index is invalid
     * @throws IllegalStateException if this is a leaf node
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public BTreeNode<T> removeChildAt(int index) {
        if (isLeaf) {
            throw new IllegalStateException("Leaf nodes have no children");
        }
        if (index < 0 || index >= children.size()) {
            throw new IllegalArgumentException("Invalid child index: " + index);
        }
        
        BTreeNode<T> removedChild = children.remove(index);
        if (removedChild != null) {
            removedChild.parent = null;
        }
        return removedChild;
    }
    
    // ==================== NODE STATE QUERIES ====================
    
    /**
     * Checks if this node is full (has maximum number of keys).
     * 
     * @return true if node is full, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull() {
        return keys.size() == maxKeys;
    }
    
    /**
     * Checks if this node has minimum number of keys required.
     * Root node can have fewer keys than minimum.
     * 
     * @return true if node has at least minimum keys, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean hasMinimumKeys() {
        return keys.size() >= minKeys;
    }
    
    /**
     * Checks if this node can lend a key (has more than minimum).
     * 
     * @return true if node can lend a key, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean canLendKey() {
        return keys.size() > minKeys;
    }
    
    /**
     * Checks if this node needs to borrow a key (has fewer than minimum).
     * 
     * @return true if node needs to borrow, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean needsToBorrow() {
        return keys.size() < minKeys;
    }
    
    /**
     * Gets the number of keys in this node.
     * 
     * @return the number of keys
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getKeyCount() {
        return keys.size();
    }
    
    /**
     * Gets the number of children this node has.
     * 
     * @return the number of children
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getChildCount() {
        return isLeaf ? 0 : children.size();
    }
    
    /**
     * Checks if this node is the root (has no parent).
     * 
     * @return true if this is the root node, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isRoot() {
        return parent == null;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Gets all keys in this node as a list.
     * Returns a copy to prevent external modification.
     * 
     * @return list of all keys in sorted order
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(t)
     */
    public List<T> getAllKeys() {
        return new ArrayList<>(keys);
    }
    
    /**
     * Gets all non-null children as a list.
     * 
     * @return list of all children
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(t)
     */
    public List<BTreeNode<T>> getAllChildren() {
        if (isLeaf) {
            return new ArrayList<>();
        }
        
        List<BTreeNode<T>> result = new ArrayList<>();
        for (BTreeNode<T> child : children) {
            if (child != null) {
                result.add(child);
            }
        }
        return result;
    }
    
    /**
     * Clears all keys and children from this node.
     * Useful for node recycling and cleanup.
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(1)
     */
    public void clear() {
        keys.clear();
        if (!isLeaf) {
            for (BTreeNode<T> child : children) {
                if (child != null) {
                    child.parent = null;
                }
            }
            children.clear();
            // Reinitialize children list
            for (int i = 0; i <= maxKeys; i++) {
                children.add(null);
            }
        }
    }
    
    /**
     * Creates a deep copy of this node (without parent/child relationships).
     * 
     * @return a deep copy of this node
     * 
     * Time Complexity: O(t) where t is minimum degree
     * Space Complexity: O(t)
     */
    public BTreeNode<T> deepCopy() {
        BTreeNode<T> copy = new BTreeNode<>(minDegree, isLeaf);
        copy.keys.addAll(this.keys);
        copy.nodeId = this.nodeId; // Preserve node ID for debugging
        
        // Note: This doesn't copy children/parent relationships
        // Those should be handled by the tree structure
        
        return copy;
    }
    
    // ==================== DISPLAY AND DEBUGGING ====================
    
    /**
     * Returns a string representation of this node.
     * Shows keys, leaf status, and basic node information.
     * 
     * @return string representation of the node
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BTreeNode{");
        sb.append("id=").append(nodeId);
        sb.append(", keys=").append(keys);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", keyCount=").append(keys.size());
        sb.append("/").append(maxKeys);
        if (!isLeaf) {
            int childCount = 0;
            for (BTreeNode<T> child : children) {
                if (child != null) childCount++;
            }
            sb.append(", childCount=").append(childCount);
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a detailed string representation for debugging.
     * 
     * @return detailed string representation
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BTreeNode{");
        sb.append("id=").append(nodeId);
        sb.append(", minDegree=").append(minDegree);
        sb.append(", keys=").append(keys);
        sb.append(" (").append(keys.size()).append("/").append(maxKeys).append(")");
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", parent=").append(parent != null ? parent.nodeId : "null");
        
        if (!isLeaf) {
            sb.append(", children=[");
            for (int i = 0; i < children.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(children.get(i) != null ? children.get(i).nodeId : "null");
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a compact representation for tree visualization.
     * 
     * @return compact string showing just the keys
     */
    public String toCompactString() {
        return keys.toString();
    }
} 