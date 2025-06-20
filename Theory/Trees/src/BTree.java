import nodes.BTreeNode;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * B-Tree Implementation - Multi-way Self-Balancing Search Tree
 * 
 * A B-Tree is a self-balancing tree data structure that maintains sorted data
 * and allows searches, sequential access, insertions, and deletions in logarithmic time.
 * The B-Tree generalizes the binary search tree by allowing nodes to have more than
 * two children, making it particularly well-suited for disk-based storage systems.
 * 
 * Key Features:
 * - Multi-way branching with configurable minimum degree
 * - All leaves at the same level (perfectly balanced height)
 * - Efficient disk I/O through larger node sizes
 * - Split and merge operations maintain balance
 * - Optimal for database indexing and file systems
 * 
 * B-Tree Properties:
 * 1. Every node has at most 2t-1 keys (where t is minimum degree)
 * 2. Every internal node has at least t-1 keys (except root)
 * 3. Root has at least 1 key (if tree is non-empty)
 * 4. All leaves are at the same level
 * 5. Keys within each node are stored in sorted order
 * 6. Each internal node with k keys has k+1 children
 * 
 * Applications:
 * - Database indexing (B+ trees in MySQL, PostgreSQL, Oracle)
 * - File systems (NTFS, HFS+, ext4, Btrfs)
 * - Search engines and information retrieval systems
 * - Memory management and virtual memory systems
 * - Large-scale data storage and retrieval
 * 
 * Advantages over Binary Search Trees:
 * - Better cache performance and disk I/O efficiency
 * - Fewer tree levels for the same number of keys
 * - More balanced structure reduces worst-case scenarios
 * - Optimal for systems with block-based storage
 * 
 * Time Complexities:
 * - Search: O(log n) where n is the number of keys
 * - Insert: O(log n) where n is the number of keys
 * - Delete: O(log n) where n is the number of keys
 * - Traverse: O(n) where n is the number of keys
 * - Split/Merge: O(t) where t is the minimum degree
 * 
 * Space Complexity: O(n) where n is the number of keys stored
 * 
 * @param <T> the type of keys stored in the B-Tree (must be Comparable)
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class BTree<T extends Comparable<T>> {
    
    // Instance variables
    private BTreeNode<T> root;
    private int minDegree;          // Minimum degree (t)
    private int maxKeys;            // Maximum keys per node (2t-1)
    private int minKeys;            // Minimum keys per internal node (t-1)
    private int size;               // Total number of keys in the tree
    private int height;             // Height of the tree
    private int nodeCount;          // Total number of nodes in the tree
    
    /**
     * Default constructor that creates an empty B-Tree with minimum degree 3.
     * This results in nodes with 2-5 keys and 3-6 children.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BTree() {
        this(3); // Default minimum degree
    }
    
    /**
     * Constructor that creates an empty B-Tree with specified minimum degree.
     * 
     * @param minDegree the minimum degree t (must be >= 2)
     * @throws IllegalArgumentException if minDegree < 2
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BTree(int minDegree) {
        if (minDegree < 2) {
            throw new IllegalArgumentException("Minimum degree must be at least 2, got: " + minDegree);
        }
        
        this.minDegree = minDegree;
        this.maxKeys = 2 * minDegree - 1;
        this.minKeys = minDegree - 1;
        this.root = null;
        this.size = 0;
        this.height = 0;
        this.nodeCount = 0;
    }
    
    /**
     * Constructor that creates a B-Tree and inserts initial keys.
     * 
     * @param minDegree the minimum degree
     * @param keys list of keys to insert initially
     * @throws IllegalArgumentException if minDegree < 2 or keys is null
     * 
     * Time Complexity: O(n log n) where n is the number of keys
     * Space Complexity: O(n) for storing the keys
     */
    public BTree(int minDegree, List<T> keys) {
        this(minDegree);
        if (keys == null) {
            throw new IllegalArgumentException("Keys list cannot be null");
        }
        
        for (T key : keys) {
            insert(key);
        }
    }
    
    // ==================== CORE B-TREE OPERATIONS ====================
    
    /**
     * Searches for a key in the B-Tree.
     * 
     * @param key the key to search for
     * @return true if key is found, false otherwise
     * @throws IllegalArgumentException if key is null
     * 
     * Time Complexity: O(log n) where n is the number of keys
     * Space Complexity: O(1)
     */
    public boolean search(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Search key cannot be null");
        }
        
        BTreeNode<T> node = searchNode(key);
        boolean found = (node != null);
        
        System.out.println(found ? 
            "✅ Found key '" + key + "' in B-Tree" : 
            "❌ Key '" + key + "' not found in B-Tree");
        
        return found;
    }
    
    /**
     * Helper method to find the node containing a specific key.
     * 
     * @param key the key to search for
     * @return the node containing the key, or null if not found
     */
    private BTreeNode<T> searchNode(T key) {
        return searchNodeRecursive(root, key);
    }
    
    /**
     * Recursive helper for searching a key in the subtree rooted at node.
     */
    private BTreeNode<T> searchNodeRecursive(BTreeNode<T> node, T key) {
        if (node == null) {
            return null;
        }
        
        int keyIndex = node.findKey(key);
        if (keyIndex != -1) {
            return node; // Key found in this node
        }
        
        if (node.isLeaf) {
            return null; // Key not found and this is a leaf
        }
        
        // Find appropriate child to search
        int childIndex = node.findInsertionIndex(key);
        return searchNodeRecursive(node.getChild(childIndex), key);
    }
    
    /**
     * Inserts a key into the B-Tree.
     * Handles node splitting to maintain B-Tree properties.
     * 
     * @param key the key to insert
     * @throws IllegalArgumentException if key is null
     * 
     * Time Complexity: O(log n) where n is the number of keys
     * Space Complexity: O(log n) for recursion stack
     */
    public void insert(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Insert key cannot be null");
        }
        
        if (search(key)) {
            System.out.println("⚠️ Key '" + key + "' already exists in B-Tree");
            return;
        }
        
        if (root == null) {
            // Create root for empty tree
            root = new BTreeNode<>(minDegree, true);
            root.insertKeyAt(0, key);
            size = 1;
            height = 1;
            nodeCount = 1;
        } else {
            // Insert into non-empty tree
            if (root.isFull()) {
                // Split root if full
                BTreeNode<T> newRoot = new BTreeNode<>(minDegree, false);
                newRoot.setChild(0, root);
                root.parent = newRoot;
                
                splitChild(newRoot, 0);
                root = newRoot;
                height++;
            }
            
            insertNonFull(root, key);
            size++;
        }
        
        System.out.println("✅ Inserted '" + key + "' into B-Tree. Size: " + size);
    }
    
    /**
     * Helper method to insert a key into a non-full node.
     */
    private void insertNonFull(BTreeNode<T> node, T key) {
        if (node.isLeaf) {
            // Insert key into leaf node
            int insertIndex = node.findInsertionIndex(key);
            node.insertKeyAt(insertIndex, key);
        } else {
            // Find child to insert into
            int childIndex = node.findInsertionIndex(key);
            BTreeNode<T> child = node.getChild(childIndex);
            
            if (child.isFull()) {
                // Split child if full
                splitChild(node, childIndex);
                
                // Determine which of the two children to insert into
                if (key.compareTo(node.getKey(childIndex)) > 0) {
                    childIndex++;
                }
            }
            
            insertNonFull(node.getChild(childIndex), key);
        }
    }
    
    /**
     * Splits a full child of a node.
     * 
     * @param parent the parent node
     * @param childIndex the index of the child to split
     */
    private void splitChild(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> fullChild = parent.getChild(childIndex);
        BTreeNode<T> newChild = new BTreeNode<>(minDegree, fullChild.isLeaf);
        
        int midIndex = minKeys; // t-1 (middle key index)
        
        // Move keys from full child to new child
        for (int i = 0; i < minKeys; i++) {
            T key = fullChild.removeKeyAt(midIndex + 1);
            newChild.insertKeyAt(i, key);
        }
        
        // Move children if not leaf
        if (!fullChild.isLeaf) {
            for (int i = 0; i <= minKeys; i++) {
                BTreeNode<T> child = fullChild.removeChildAt(midIndex + 1);
                newChild.setChild(i, child);
            }
        }
        
        // Move middle key up to parent
        T middleKey = fullChild.removeKeyAt(midIndex);
        int insertIndex = parent.findInsertionIndex(middleKey);
        parent.insertKeyAt(insertIndex, middleKey);
        parent.insertChildAt(insertIndex + 1, newChild);
        
        newChild.parent = parent;
        nodeCount++;
        
        System.out.println("🔄 Split node: middle key '" + middleKey + "' promoted to parent");
    }
    
    /**
     * Deletes a key from the B-Tree.
     * Handles node merging and key borrowing to maintain B-Tree properties.
     * 
     * @param key the key to delete
     * @return true if key was deleted, false if key wasn't found
     * @throws IllegalArgumentException if key is null
     * 
     * Time Complexity: O(log n) where n is the number of keys
     * Space Complexity: O(log n) for recursion stack
     */
    public boolean delete(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Delete key cannot be null");
        }
        
        if (isEmpty()) {
            System.out.println("❌ Cannot delete from empty B-Tree");
            return false;
        }
        
        if (!search(key)) {
            System.out.println("❌ Key '" + key + "' not found in B-Tree");
            return false;
        }
        
        deleteFromNode(root, key);
        size--;
        
        // Handle root reduction
        if (root.getKeyCount() == 0 && !root.isLeaf) {
            BTreeNode<T> oldRoot = root;
            root = root.getChild(0);
            root.parent = null;
            height--;
            nodeCount--;
        }
        
        System.out.println("✅ Deleted '" + key + "' from B-Tree. Size: " + size);
        return true;
    }
    
    /**
     * Helper method to delete a key from a specific node.
     */
    private void deleteFromNode(BTreeNode<T> node, T key) {
        int keyIndex = node.findKey(key);
        
        if (keyIndex != -1) {
            // Key found in this node
            if (node.isLeaf) {
                // Case 1: Key in leaf node
                node.removeKeyAt(keyIndex);
            } else {
                // Case 2: Key in internal node
                deleteFromInternalNode(node, keyIndex);
            }
        } else {
            // Key not in this node, find appropriate child
            if (node.isLeaf) {
                return; // Key not found
            }
            
            int childIndex = node.findInsertionIndex(key);
            BTreeNode<T> child = node.getChild(childIndex);
            
            // Ensure child has enough keys for deletion
            if (child.getKeyCount() <= minKeys) {
                fixChildWithMinimumKeys(node, childIndex);
                
                // Recompute child index after potential changes
                if (childIndex > node.getKeyCount()) {
                    childIndex = node.getKeyCount();
                }
                child = node.getChild(childIndex);
            }
            
            deleteFromNode(child, key);
        }
    }
    
    /**
     * Handles deletion of a key from an internal node.
     */
    private void deleteFromInternalNode(BTreeNode<T> node, int keyIndex) {
        T key = node.getKey(keyIndex);
        BTreeNode<T> leftChild = node.getChild(keyIndex);
        BTreeNode<T> rightChild = node.getChild(keyIndex + 1);
        
        if (leftChild.getKeyCount() > minKeys) {
            // Case 2a: Left child has enough keys
            T predecessor = findMaxKey(leftChild);
            node.removeKeyAt(keyIndex);
            node.insertKeyAt(keyIndex, predecessor);
            deleteFromNode(leftChild, predecessor);
        } else if (rightChild.getKeyCount() > minKeys) {
            // Case 2b: Right child has enough keys
            T successor = findMinKey(rightChild);
            node.removeKeyAt(keyIndex);
            node.insertKeyAt(keyIndex, successor);
            deleteFromNode(rightChild, successor);
        } else {
            // Case 2c: Both children have minimum keys - merge
            mergeChildren(node, keyIndex);
            deleteFromNode(leftChild, key);
        }
    }
    
    /**
     * Fixes a child that has minimum number of keys before deletion.
     */
    private void fixChildWithMinimumKeys(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> child = parent.getChild(childIndex);
        BTreeNode<T> leftSibling = (childIndex > 0) ? parent.getChild(childIndex - 1) : null;
        BTreeNode<T> rightSibling = (childIndex < parent.getKeyCount()) ? parent.getChild(childIndex + 1) : null;
        
        // Try to borrow from left sibling
        if (leftSibling != null && leftSibling.canLendKey()) {
            borrowFromLeftSibling(parent, childIndex);
        }
        // Try to borrow from right sibling
        else if (rightSibling != null && rightSibling.canLendKey()) {
            borrowFromRightSibling(parent, childIndex);
        }
        // Merge with a sibling
        else {
            if (leftSibling != null) {
                mergeChildren(parent, childIndex - 1);
            } else {
                mergeChildren(parent, childIndex);
            }
        }
    }
    
    /**
     * Borrows a key from the left sibling.
     */
    private void borrowFromLeftSibling(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> child = parent.getChild(childIndex);
        BTreeNode<T> leftSibling = parent.getChild(childIndex - 1);
        
        // Move parent key to child
        T parentKey = parent.getKey(childIndex - 1);
        child.insertKeyAt(0, parentKey);
        
        // Move left sibling's last key to parent
        T borrowedKey = leftSibling.removeKeyAt(leftSibling.getKeyCount() - 1);
        parent.removeKeyAt(childIndex - 1);
        parent.insertKeyAt(childIndex - 1, borrowedKey);
        
        // Move child if not leaf
        if (!child.isLeaf) {
            BTreeNode<T> borrowedChild = leftSibling.removeChildAt(leftSibling.getChildCount() - 1);
            child.insertChildAt(0, borrowedChild);
        }
        
        System.out.println("🔄 Borrowed key '" + borrowedKey + "' from left sibling");
    }
    
    /**
     * Borrows a key from the right sibling.
     */
    private void borrowFromRightSibling(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> child = parent.getChild(childIndex);
        BTreeNode<T> rightSibling = parent.getChild(childIndex + 1);
        
        // Move parent key to child
        T parentKey = parent.getKey(childIndex);
        child.insertKeyAt(child.getKeyCount(), parentKey);
        
        // Move right sibling's first key to parent
        T borrowedKey = rightSibling.removeKeyAt(0);
        parent.removeKeyAt(childIndex);
        parent.insertKeyAt(childIndex, borrowedKey);
        
        // Move child if not leaf
        if (!child.isLeaf) {
            BTreeNode<T> borrowedChild = rightSibling.removeChildAt(0);
            child.setChild(child.getChildCount() - 1, borrowedChild);
        }
        
        System.out.println("🔄 Borrowed key '" + borrowedKey + "' from right sibling");
    }
    
    /**
     * Merges a child with its right sibling.
     */
    private void mergeChildren(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> leftChild = parent.getChild(childIndex);
        BTreeNode<T> rightChild = parent.getChild(childIndex + 1);
        T separatorKey = parent.getKey(childIndex);
        
        // Add separator key to left child
        leftChild.insertKeyAt(leftChild.getKeyCount(), separatorKey);
        
        // Move all keys from right child to left child
        while (rightChild.getKeyCount() > 0) {
            T key = rightChild.removeKeyAt(0);
            leftChild.insertKeyAt(leftChild.getKeyCount(), key);
        }
        
        // Move all children from right child to left child (if not leaf)
        if (!leftChild.isLeaf) {
            for (int i = 0; i < rightChild.getChildCount(); i++) {
                BTreeNode<T> child = rightChild.getChild(i);
                if (child != null) {
                    leftChild.setChild(leftChild.getChildCount(), child);
                }
            }
        }
        
        // Remove separator key and right child from parent
        parent.removeKeyAt(childIndex);
        parent.removeChildAt(childIndex + 1);
        nodeCount--;
        
        System.out.println("🔄 Merged children with separator key '" + separatorKey + "'");
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Finds the minimum key in a subtree.
     */
    private T findMinKey(BTreeNode<T> node) {
        while (!node.isLeaf) {
            node = node.getChild(0);
        }
        return node.getKey(0);
    }
    
    /**
     * Finds the maximum key in a subtree.
     */
    private T findMaxKey(BTreeNode<T> node) {
        while (!node.isLeaf) {
            node = node.getChild(node.getChildCount() - 1);
        }
        return node.getKey(node.getKeyCount() - 1);
    }
    
    // ==================== TRAVERSAL METHODS ====================
    
    /**
     * Performs in-order traversal of the B-Tree.
     * Returns all keys in sorted order.
     * 
     * @return list of all keys in sorted order
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n) where n is the number of keys
     * Space Complexity: O(n) for the result list + O(h) for recursion
     */
    public List<T> inorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot traverse empty B-Tree");
        }
        
        List<T> result = new ArrayList<>();
        inorderTraversalRecursive(root, result);
        
        System.out.println("📋 In-order traversal: " + result);
        return result;
    }
    
    /**
     * Recursive helper for in-order traversal.
     */
    private void inorderTraversalRecursive(BTreeNode<T> node, List<T> result) {
        if (node == null) return;
        
        for (int i = 0; i < node.getKeyCount(); i++) {
            // Visit left child
            if (!node.isLeaf) {
                inorderTraversalRecursive(node.getChild(i), result);
            }
            
            // Visit key
            result.add(node.getKey(i));
        }
        
        // Visit rightmost child
        if (!node.isLeaf) {
            inorderTraversalRecursive(node.getChild(node.getKeyCount()), result);
        }
    }
    
    /**
     * Performs level-order traversal of the B-Tree.
     * Returns keys organized by tree levels.
     * 
     * @return list of levels, each containing keys at that level
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n) where n is the number of keys
     * Space Complexity: O(n) for the result + O(w) for queue where w is max width
     */
    public List<List<T>> levelOrderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot traverse empty B-Tree");
        }
        
        List<List<T>> result = new ArrayList<>();
        Queue<BTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<T> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                BTreeNode<T> node = queue.poll();
                currentLevel.addAll(node.getAllKeys());
                
                // Add all children to queue
                if (!node.isLeaf) {
                    for (BTreeNode<T> child : node.getAllChildren()) {
                        queue.offer(child);
                    }
                }
            }
            
            result.add(currentLevel);
        }
        
        System.out.println("📋 Level-order traversal: " + result);
        return result;
    }
    
    // ==================== TREE ANALYSIS METHODS ====================
    
    /**
     * Gets the number of keys stored in the B-Tree.
     * 
     * @return the number of keys
     */
    public int size() {
        return size;
    }
    
    /**
     * Gets the height of the B-Tree.
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the number of nodes in the B-Tree.
     * 
     * @return the number of nodes
     */
    public int getNodeCount() {
        return nodeCount;
    }
    
    /**
     * Checks if the B-Tree is empty.
     * 
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null || size == 0;
    }
    
    /**
     * Gets the minimum degree of the B-Tree.
     * 
     * @return the minimum degree
     */
    public int getMinDegree() {
        return minDegree;
    }
    
    /**
     * Calculates the space efficiency of the B-Tree.
     * Returns the average fill ratio of nodes.
     * 
     * @return space efficiency ratio (0.0 to 1.0)
     */
    public double getSpaceEfficiency() {
        if (isEmpty()) return 1.0;
        
        int totalCapacity = nodeCount * maxKeys;
        return (double) size / totalCapacity;
    }
    
    /**
     * Validates the B-Tree properties.
     * Checks all B-Tree invariants for correctness.
     * 
     * @return true if tree is valid, false otherwise
     */
    public boolean validateBTree() {
        if (isEmpty()) return true;
        
        try {
            return validateBTreeRecursive(root, null, null, true);
        } catch (Exception e) {
            System.out.println("❌ B-Tree validation failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Recursive helper for B-Tree validation.
     */
    private boolean validateBTreeRecursive(BTreeNode<T> node, T minKey, T maxKey, boolean isRoot) {
        if (node == null) return true;
        
        // Check key count constraints
        if (isRoot) {
            if (node.getKeyCount() < 1) {
                throw new IllegalStateException("Root must have at least 1 key");
            }
        } else {
            if (node.getKeyCount() < minKeys) {
                throw new IllegalStateException("Internal node has too few keys: " + node.getKeyCount());
            }
        }
        
        if (node.getKeyCount() > maxKeys) {
            throw new IllegalStateException("Node has too many keys: " + node.getKeyCount());
        }
        
        // Check key ordering within node
        for (int i = 0; i < node.getKeyCount() - 1; i++) {
            if (node.getKey(i).compareTo(node.getKey(i + 1)) >= 0) {
                throw new IllegalStateException("Keys not in ascending order within node");
            }
        }
        
        // Check key bounds
        for (int i = 0; i < node.getKeyCount(); i++) {
            T key = node.getKey(i);
            if (minKey != null && key.compareTo(minKey) <= 0) {
                throw new IllegalStateException("Key violates lower bound: " + key);
            }
            if (maxKey != null && key.compareTo(maxKey) >= 0) {
                throw new IllegalStateException("Key violates upper bound: " + key);
            }
        }
        
        // Recursively validate children
        if (!node.isLeaf) {
            if (node.getChildCount() != node.getKeyCount() + 1) {
                throw new IllegalStateException("Child count mismatch");
            }
            
            for (int i = 0; i <= node.getKeyCount(); i++) {
                T newMin = (i == 0) ? minKey : node.getKey(i - 1);
                T newMax = (i == node.getKeyCount()) ? maxKey : node.getKey(i);
                
                if (!validateBTreeRecursive(node.getChild(i), newMin, newMax, false)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Clears all keys from the B-Tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        this.root = null;
        this.size = 0;
        this.height = 0;
        this.nodeCount = 0;
        
        System.out.println("🗑️ B-Tree cleared. All keys removed.");
    }
    
    // ==================== DISPLAY AND VISUALIZATION ====================
    
    /**
     * Prints the structure of the B-Tree in a tree-like format.
     */
    public void printBTreeStructure() {
        if (isEmpty()) {
            System.out.println("🌳 B-Tree is empty");
            return;
        }
        
        System.out.println("🌳 B-Tree Structure (min degree = " + minDegree + "):");
        printBTreeRecursive(root, "", true, 0);
        
        System.out.println("\n📊 B-Tree Statistics:");
        System.out.println("   Keys: " + size);
        System.out.println("   Nodes: " + nodeCount);
        System.out.println("   Height: " + height);
        System.out.println("   Min Degree: " + minDegree);
        System.out.println("   Space Efficiency: " + String.format("%.2f%%", getSpaceEfficiency() * 100));
        System.out.println("   Valid: " + (validateBTree() ? "✅" : "❌"));
    }
    
    /**
     * Recursive helper for printing B-Tree structure.
     */
    private void printBTreeRecursive(BTreeNode<T> node, String prefix, boolean isLast, int level) {
        if (node == null) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + 
                          "Level " + level + ": " + node.getAllKeys());
        
        if (!node.isLeaf) {
            String childPrefix = prefix + (isLast ? "    " : "│   ");
            List<BTreeNode<T>> children = node.getAllChildren();
            
            for (int i = 0; i < children.size(); i++) {
                boolean isLastChild = (i == children.size() - 1);
                printBTreeRecursive(children.get(i), childPrefix, isLastChild, level + 1);
            }
        }
    }
    
    /**
     * Returns a string representation of the B-Tree.
     */
    @Override
    public String toString() {
        return "BTree{size=" + size + ", height=" + height + 
               ", nodes=" + nodeCount + ", minDegree=" + minDegree + 
               ", efficiency=" + String.format("%.2f%%", getSpaceEfficiency() * 100) + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating B-Tree operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🌳 B-Tree (Multi-way Search Tree) Interactive Demo");
        System.out.println("==================================================");
        System.out.print("Enter minimum degree (default 3): ");
        String degreeInput = scanner.nextLine().trim();
        int minDegree = degreeInput.isEmpty() ? 3 : Integer.parseInt(degreeInput);
        
        BTree<Integer> btree = new BTree<>(minDegree);
        System.out.println("Created B-Tree with minimum degree " + minDegree);
        System.out.println("Node capacity: " + (2 * minDegree - 1) + " keys, " + (2 * minDegree) + " children");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Key");
            System.out.println("2.  Search Key");
            System.out.println("3.  Delete Key");
            System.out.println("4.  In-order Traversal");
            System.out.println("5.  Level-order Traversal");
            System.out.println("6.  Print Tree Structure");
            System.out.println("7.  Get Tree Size");
            System.out.println("8.  Get Tree Height");
            System.out.println("9.  Get Node Count");
            System.out.println("10. Get Space Efficiency");
            System.out.println("11. Validate B-Tree");
            System.out.println("12. Clear Tree");
            System.out.println("13. Demo with Sample Data");
            System.out.println("14. Exit");
            System.out.print("👉 Enter your choice (1-14): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter integer key to insert: ");
                        int insertKey = scanner.nextInt();
                        btree.insert(insertKey);
                        break;
                        
                    case 2:
                        System.out.print("Enter integer key to search: ");
                        int searchKey = scanner.nextInt();
                        btree.search(searchKey);
                        break;
                        
                    case 3:
                        System.out.print("Enter integer key to delete: ");
                        int deleteKey = scanner.nextInt();
                        btree.delete(deleteKey);
                        break;
                        
                    case 4:
                        btree.inorderTraversal();
                        break;
                        
                    case 5:
                        btree.levelOrderTraversal();
                        break;
                        
                    case 6:
                        btree.printBTreeStructure();
                        break;
                        
                    case 7:
                        System.out.println("📊 Tree size: " + btree.size());
                        break;
                        
                    case 8:
                        System.out.println("📊 Tree height: " + btree.getHeight());
                        break;
                        
                    case 9:
                        System.out.println("📊 Node count: " + btree.getNodeCount());
                        break;
                        
                    case 10:
                        System.out.println("📊 Space efficiency: " + 
                                         String.format("%.2f%%", btree.getSpaceEfficiency() * 100));
                        break;
                        
                    case 11:
                        boolean isValid = btree.validateBTree();
                        System.out.println("📊 B-Tree validity: " + (isValid ? "✅ Valid" : "❌ Invalid"));
                        break;
                        
                    case 12:
                        btree.clear();
                        break;
                        
                    case 13:
                        System.out.println("🎬 Loading sample data...");
                        int[] sampleData = {10, 20, 5, 6, 12, 30, 7, 17, 15, 25, 35, 40};
                        for (int key : sampleData) {
                            btree.insert(key);
                        }
                        System.out.println("✅ Sample data loaded. Try operations!");
                        break;
                        
                    case 14:
                        System.out.println("👋 Thank you for using B-Tree Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-14.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 