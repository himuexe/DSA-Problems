import nodes.RedBlackNode;
import nodes.RedBlackNode.NodeColor;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * Red-Black Tree Implementation - Self-Balancing Binary Search Tree
 * 
 * Red-Black trees are a type of self-balancing BST where each node has a color (red or black)
 * and the tree maintains balance through five key properties. This ensures O(log n) performance
 * for all operations while requiring less strict balancing than AVL trees.
 * 
 * Red-Black Tree Properties:
 * 1. Every node is either red or black
 * 2. The root is always black
 * 3. All leaves (NIL nodes) are black
 * 4. Red nodes cannot have red children (no consecutive red nodes)
 * 5. Every path from root to leaves has the same number of black nodes (black-height)
 * 
 * Key Features:
 * - Guaranteed O(log n) operations (search, insert, delete)
 * - Less rigid balancing than AVL trees (fewer rotations)
 * - Self-balancing through color changes and rotations
 * - Widely used in practice (Java TreeMap, C++ STL map)
 * - Educational implementation with detailed operation logging
 * - Interactive demonstrations of rebalancing algorithms
 * 
 * Balancing Operations:
 * - Color flips: Change colors to maintain properties
 * - Left rotation: Rotate subtree left around a node
 * - Right rotation: Rotate subtree right around a node
 * - Insertion fixup: Restore properties after insertion
 * - Deletion fixup: Restore properties after deletion
 * 
 * Time Complexities (guaranteed for Red-Black trees):
 * - Search: O(log n)
 * - Insertion: O(log n)
 * - Deletion: O(log n)
 * - All operations maintain logarithmic bound
 * 
 * Space Complexity: O(n) for storage, O(log n) for operations
 * 
 * Comparison with AVL Trees:
 * - Red-Black: Less strict balancing, fewer rotations, good for frequent insertions
 * - AVL: Stricter balancing, more rotations, better for frequent searches
 * - Both guarantee O(log n) operations
 * 
 * Best Use Cases:
 * - Database systems requiring frequent insertions
 * - Operating system schedulers
 * - Language runtime environments (Java TreeMap)
 * - Applications with mixed read/write workloads
 * - Educational demonstrations of balanced tree concepts
 * 
 * @param <T> the type of elements stored in this Red-Black tree, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class RedBlackTree<T extends Comparable<T>> {
    
    // Instance variables
    private RedBlackNode<T> root;
    private int size;
    private int rotationCount;      // Track rotations for educational purposes
    private int colorChangeCount;   // Track color changes for analysis
    
    /**
     * Default constructor that creates an empty Red-Black tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public RedBlackTree() {
        this.root = null;
        this.size = 0;
        this.rotationCount = 0;
        this.colorChangeCount = 0;
    }
    
    /**
     * Constructor that creates a Red-Black tree with a single root node.
     * Root is automatically colored BLACK as per RB property 2.
     * 
     * @param rootData the data for the root node
     * @throws IllegalArgumentException if rootData is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public RedBlackTree(T rootData) {
        if (rootData == null) {
            throw new IllegalArgumentException("Root data cannot be null");
        }
        this.root = new RedBlackNode<>(rootData, NodeColor.BLACK);
        this.size = 1;
        this.rotationCount = 0;
        this.colorChangeCount = 0;
    }
    
    // ==================== RED-BLACK TREE CORE OPERATIONS ====================
    
    /**
     * Inserts a new value into the Red-Black tree while maintaining RB properties.
     * Automatically performs rebalancing through color changes and rotations.
     * 
     * @param data the data to insert
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    public void insert(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data");
        }
        
        int initialRotations = rotationCount;
        int initialColorChanges = colorChangeCount;
        
        // Standard BST insertion
        root = insertBST(root, data);
        
        // Find the newly inserted node and fix violations
        RedBlackNode<T> newNode = findNode(data);
        if (newNode != null) {
            insertFixup(newNode);
        }
        
        // Ensure root is always black (RB property 2)
        if (root != null && root.isRed()) {
            root.colorBlack();
            colorChangeCount++;
        }
        
        // Educational output
        int rotationsPerformed = rotationCount - initialRotations;
        int colorChangesPerformed = colorChangeCount - initialColorChanges;
        
        System.out.println("✅ Inserted '" + data + "' into Red-Black tree. Size: " + size);
        if (rotationsPerformed > 0 || colorChangesPerformed > 0) {
            System.out.println("🔄 Rebalancing: " + rotationsPerformed + " rotations, " + 
                             colorChangesPerformed + " color changes");
        }
    }
    
    /**
     * Standard BST insertion that returns the new root.
     * New nodes are inserted as RED initially.
     */
    private RedBlackNode<T> insertBST(RedBlackNode<T> node, T data) {
        if (node == null) {
            size++;
            return new RedBlackNode<>(data); // New nodes start as RED
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison == 0) {
            System.out.println("⚠️ '" + data + "' already exists in Red-Black tree. Duplicates not allowed.");
            return node;
        } else if (comparison < 0) {
            node.left = insertBST(node.left, data);
            if (node.left != null) {
                node.left.parent = node;
            }
        } else {
            node.right = insertBST(node.right, data);
            if (node.right != null) {
                node.right.parent = node;
            }
        }
        
        return node;
    }
    
    /**
     * Fixes Red-Black tree violations after insertion.
     * Handles the cases where red-red violations occur.
     * 
     * @param node the newly inserted node (initially RED)
     */
    private void insertFixup(RedBlackNode<T> node) {
        while (node != root && node.parent != null && node.parent.isRed()) {
            RedBlackNode<T> parent = node.parent;
            RedBlackNode<T> grandparent = parent.parent;
            RedBlackNode<T> uncle = node.getUncle();
            
            if (parent.isLeftChild()) {
                // Parent is left child of grandparent
                if (uncle != null && uncle.isRed()) {
                    // Case 1: Uncle is RED - color flip
                    System.out.println("🎨 Case 1: Uncle is RED - performing color flip");
                    parent.colorBlack();
                    uncle.colorBlack();
                    grandparent.colorRed();
                    colorChangeCount += 3;
                    node = grandparent; // Continue checking from grandparent
                } else {
                    // Uncle is BLACK or null
                    if (node.isRightChild()) {
                        // Case 2: Node is right child - left rotation needed first
                        System.out.println("🔄 Case 2: Left rotation on parent " + parent.data);
                        node = parent;
                        rotateLeft(node);
                        parent = node.parent; // Update parent after rotation
                    }
                    
                    // Case 3: Node is left child - right rotation on grandparent
                    if (parent != null && grandparent != null) {
                        System.out.println("🔄 Case 3: Right rotation on grandparent " + grandparent.data);
                        parent.colorBlack();
                        grandparent.colorRed();
                        colorChangeCount += 2;
                        rotateRight(grandparent);
                    }
                    break; // Tree is now balanced
                }
            } else {
                // Parent is right child of grandparent (mirror cases)
                if (uncle != null && uncle.isRed()) {
                    // Case 1: Uncle is RED - color flip
                    System.out.println("🎨 Case 1 (mirror): Uncle is RED - performing color flip");
                    parent.colorBlack();
                    uncle.colorBlack();
                    grandparent.colorRed();
                    colorChangeCount += 3;
                    node = grandparent;
                } else {
                    // Uncle is BLACK or null
                    if (node.isLeftChild()) {
                        // Case 2: Node is left child - right rotation needed first
                        System.out.println("🔄 Case 2 (mirror): Right rotation on parent " + parent.data);
                        node = parent;
                        rotateRight(node);
                        parent = node.parent;
                    }
                    
                    // Case 3: Node is right child - left rotation on grandparent
                    if (parent != null && grandparent != null) {
                        System.out.println("🔄 Case 3 (mirror): Left rotation on grandparent " + grandparent.data);
                        parent.colorBlack();
                        grandparent.colorRed();
                        colorChangeCount += 2;
                        rotateLeft(grandparent);
                    }
                    break;
                }
            }
        }
    }
    
    /**
     * Deletes a value from the Red-Black tree while maintaining RB properties.
     * Automatically performs rebalancing after deletion.
     * 
     * @param data the data to delete
     * @throws IllegalArgumentException if data is null
     * @throws TreeNodeNotFoundException if data not found
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    public void delete(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Delete data cannot be null");
        }
        
        RedBlackNode<T> nodeToDelete = findNode(data);
        if (nodeToDelete == null) {
            throw new TreeNodeNotFoundException("Cannot delete '" + data + "' - not found in Red-Black tree");
        }
        
        int initialRotations = rotationCount;
        int initialColorChanges = colorChangeCount;
        
        deleteNode(nodeToDelete);
        size--;
        
        int rotationsPerformed = rotationCount - initialRotations;
        int colorChangesPerformed = colorChangeCount - initialColorChanges;
        
        System.out.println("✅ Deleted '" + data + "' from Red-Black tree. Size: " + size);
        if (rotationsPerformed > 0 || colorChangesPerformed > 0) {
            System.out.println("🔄 Deletion rebalancing: " + rotationsPerformed + " rotations, " + 
                             colorChangesPerformed + " color changes");
        }
    }
    
    /**
     * Deletes a specific node from the Red-Black tree.
     * Handles all three deletion cases and maintains RB properties.
     */
    private void deleteNode(RedBlackNode<T> nodeToDelete) {
        RedBlackNode<T> nodeToFix = null;
        RedBlackNode<T> nodeToFixParent = null;
        NodeColor originalColor = nodeToDelete.color;
        
        if (nodeToDelete.left == null) {
            // Case 1: No left child
            nodeToFix = nodeToDelete.right;
            nodeToFixParent = nodeToDelete.parent;
            transplant(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == null) {
            // Case 2: No right child
            nodeToFix = nodeToDelete.left;
            nodeToFixParent = nodeToDelete.parent;
            transplant(nodeToDelete, nodeToDelete.left);
        } else {
            // Case 3: Two children - find successor
            RedBlackNode<T> successor = findMinNode(nodeToDelete.right);
            originalColor = successor.color;
            nodeToFix = successor.right;
            nodeToFixParent = successor.parent;
            
            if (successor.parent == nodeToDelete) {
                if (nodeToFix != null) {
                    nodeToFix.parent = successor;
                }
                nodeToFixParent = successor;
            } else {
                transplant(successor, successor.right);
                successor.right = nodeToDelete.right;
                if (successor.right != null) {
                    successor.right.parent = successor;
                }
                nodeToFixParent = successor.parent;
            }
            
            transplant(nodeToDelete, successor);
            successor.left = nodeToDelete.left;
            if (successor.left != null) {
                successor.left.parent = successor;
            }
            successor.color = nodeToDelete.color;
        }
        
        // If we deleted a black node, we need to fix the tree
        if (originalColor == NodeColor.BLACK) {
            deleteFixup(nodeToFix, nodeToFixParent);
        }
    }
    
    /**
     * Replaces one subtree as a child of its parent with another subtree.
     */
    private void transplant(RedBlackNode<T> oldNode, RedBlackNode<T> newNode) {
        if (oldNode.parent == null) {
            root = newNode;
        } else if (oldNode.isLeftChild()) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }
        
        if (newNode != null) {
            newNode.parent = oldNode.parent;
        }
    }
    
    /**
     * Fixes Red-Black tree violations after deletion.
     * Handles cases where black-height property is violated.
     */
    private void deleteFixup(RedBlackNode<T> node, RedBlackNode<T> parent) {
        while (node != root && (node == null || node.isBlack())) {
            if (parent == null) break;
            
            if (node == parent.left) {
                RedBlackNode<T> sibling = parent.right;
                
                if (sibling != null && sibling.isRed()) {
                    // Case 1: Sibling is red
                    System.out.println("🔧 Delete Case 1: Red sibling - color change and rotation");
                    sibling.colorBlack();
                    parent.colorRed();
                    colorChangeCount += 2;
                    rotateLeft(parent);
                    sibling = parent.right;
                }
                
                if (sibling != null && 
                    (sibling.left == null || sibling.left.isBlack()) &&
                    (sibling.right == null || sibling.right.isBlack())) {
                    // Case 2: Both of sibling's children are black
                    System.out.println("🔧 Delete Case 2: Black sibling children - color sibling red");
                    sibling.colorRed();
                    colorChangeCount++;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling != null && (sibling.right == null || sibling.right.isBlack())) {
                        // Case 3: Sibling's right child is black
                        System.out.println("🔧 Delete Case 3: Right rotation on sibling");
                        if (sibling.left != null) {
                            sibling.left.colorBlack();
                            colorChangeCount++;
                        }
                        sibling.colorRed();
                        colorChangeCount++;
                        rotateRight(sibling);
                        sibling = parent.right;
                    }
                    
                    // Case 4: Sibling's right child is red
                    if (sibling != null) {
                        System.out.println("🔧 Delete Case 4: Left rotation on parent");
                        sibling.color = parent.color;
                        parent.colorBlack();
                        if (sibling.right != null) {
                            sibling.right.colorBlack();
                        }
                        colorChangeCount += 2;
                        rotateLeft(parent);
                    }
                    node = root;
                }
            } else {
                // Mirror cases for right child
                RedBlackNode<T> sibling = parent.left;
                
                if (sibling != null && sibling.isRed()) {
                    sibling.colorBlack();
                    parent.colorRed();
                    colorChangeCount += 2;
                    rotateRight(parent);
                    sibling = parent.left;
                }
                
                if (sibling != null &&
                    (sibling.right == null || sibling.right.isBlack()) &&
                    (sibling.left == null || sibling.left.isBlack())) {
                    sibling.colorRed();
                    colorChangeCount++;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (sibling != null && (sibling.left == null || sibling.left.isBlack())) {
                        if (sibling.right != null) {
                            sibling.right.colorBlack();
                            colorChangeCount++;
                        }
                        sibling.colorRed();
                        colorChangeCount++;
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }
                    
                    if (sibling != null) {
                        sibling.color = parent.color;
                        parent.colorBlack();
                        if (sibling.left != null) {
                            sibling.left.colorBlack();
                        }
                        colorChangeCount += 2;
                        rotateRight(parent);
                    }
                    node = root;
                }
            }
        }
        
        if (node != null && node.isRed()) {
            node.colorBlack();
            colorChangeCount++;
        }
    }
    
    // ==================== ROTATION OPERATIONS ====================
    
    /**
     * Performs a left rotation around the given node.
     * 
     * @param node the node to rotate around
     */
    private void rotateLeft(RedBlackNode<T> node) {
        if (node == null || node.right == null) return;
        
        RedBlackNode<T> rightChild = node.right;
        
        // Turn rightChild's left subtree into node's right subtree
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        
        // Link node's parent to rightChild
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node.isLeftChild()) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        
        // Put node on rightChild's left
        rightChild.left = node;
        node.parent = rightChild;
        
        rotationCount++;
    }
    
    /**
     * Performs a right rotation around the given node.
     * 
     * @param node the node to rotate around
     */
    private void rotateRight(RedBlackNode<T> node) {
        if (node == null || node.left == null) return;
        
        RedBlackNode<T> leftChild = node.left;
        
        // Turn leftChild's right subtree into node's left subtree
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        
        // Link node's parent to leftChild
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node.isRightChild()) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        
        // Put node on leftChild's right
        leftChild.right = node;
        node.parent = leftChild;
        
        rotationCount++;
    }
    
    // ==================== SEARCH AND UTILITY OPERATIONS ====================
    
    /**
     * Searches for a value in the Red-Black tree.
     * 
     * @param data the data to search for
     * @return true if found, false otherwise
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    public boolean search(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Search data cannot be null");
        }
        
        return findNode(data) != null;
    }
    
    /**
     * Finds and returns the node containing the specified data.
     */
    private RedBlackNode<T> findNode(T data) {
        return findNodeRecursive(root, data);
    }
    
    /**
     * Helper method for recursive node search.
     */
    private RedBlackNode<T> findNodeRecursive(RedBlackNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison == 0) {
            return node;
        } else if (comparison < 0) {
            return findNodeRecursive(node.left, data);
        } else {
            return findNodeRecursive(node.right, data);
        }
    }
    
    /**
     * Finds and returns the minimum value in the Red-Black tree.
     * 
     * @return the minimum value
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public T findMin() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find minimum in empty Red-Black tree");
        }
        
        RedBlackNode<T> minNode = findMinNode(root);
        return minNode.data;
    }
    
    /**
     * Helper method to find the node with minimum value.
     */
    private RedBlackNode<T> findMinNode(RedBlackNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * Finds and returns the maximum value in the Red-Black tree.
     * 
     * @return the maximum value
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public T findMax() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find maximum in empty Red-Black tree");
        }
        
        RedBlackNode<T> maxNode = findMaxNode(root);
        return maxNode.data;
    }
    
    /**
     * Helper method to find the node with maximum value.
     */
    private RedBlackNode<T> findMaxNode(RedBlackNode<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    /**
     * Performs inorder traversal which returns elements in sorted order.
     * 
     * @return list of elements in sorted order
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for result list + O(log n) for recursion
     */
    public List<T> inorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform inorder traversal on empty Red-Black tree");
        }
        
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        
        System.out.println("📋 Red-Black Inorder Traversal (Sorted): " + result);
        return result;
    }
    
    /**
     * Helper method for recursive inorder traversal.
     */
    private void inorderRecursive(RedBlackNode<T> node, List<T> result) {
        if (node != null) {
            inorderRecursive(node.left, result);
            result.add(node.data);
            inorderRecursive(node.right, result);
        }
    }
    
    // ==================== RED-BLACK TREE VALIDATION ====================
    
    /**
     * Validates that the tree maintains all Red-Black properties.
     * 
     * @return true if valid Red-Black tree, false otherwise
     */
    public boolean isValidRedBlackTree() {
        if (root == null) {
            System.out.println("✅ Empty tree is valid Red-Black tree");
            return true;
        }
        
        // Check property 2: root is black
        if (root.isRed()) {
            System.out.println("❌ Violation: Root is not black");
            return false;
        }
        
        // Check all properties recursively
        int blackHeight = validateRecursive(root);
        boolean valid = (blackHeight != -1);
        
        if (valid) {
            System.out.println("✅ Red-Black tree is valid - maintains all RB properties");
            System.out.println("📊 Black height: " + blackHeight);
        } else {
            System.out.println("❌ Red-Black tree is invalid - violates RB properties");
        }
        
        return valid;
    }
    
    /**
     * Helper method for recursive Red-Black tree validation.
     * Returns black height if valid, -1 if invalid.
     */
    private int validateRecursive(RedBlackNode<T> node) {
        if (node == null) {
            return 1; // NIL nodes contribute 1 to black height
        }
        
        // Check property 4: red node cannot have red children
        if (node.isRed()) {
            if ((node.left != null && node.left.isRed()) ||
                (node.right != null && node.right.isRed())) {
                System.out.println("❌ Violation: Red node " + node.data + " has red child");
                return -1;
            }
        }
        
        // Recursively validate subtrees
        int leftBlackHeight = validateRecursive(node.left);
        int rightBlackHeight = validateRecursive(node.right);
        
        if (leftBlackHeight == -1 || rightBlackHeight == -1) {
            return -1; // Invalid subtree
        }
        
        // Check property 5: same black height on all paths
        if (leftBlackHeight != rightBlackHeight) {
            System.out.println("❌ Violation: Different black heights at node " + node.data + 
                             " (left: " + leftBlackHeight + ", right: " + rightBlackHeight + ")");
            return -1;
        }
        
        // Add 1 if current node is black
        return leftBlackHeight + (node.isBlack() ? 1 : 0);
    }
    
    /**
     * Calculates and returns the height of the Red-Black tree.
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        return getHeightRecursive(root);
    }
    
    /**
     * Helper method for recursive height calculation.
     */
    private int getHeightRecursive(RedBlackNode<T> node) {
        if (node == null) {
            return -1;
        }
        
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Calculates the black height of the tree.
     * 
     * @return the black height
     */
    public int getBlackHeight() {
        return root != null ? root.getBlackHeight() : 0;
    }
    
    /**
     * Returns the total number of rotations performed.
     * 
     * @return total rotation count
     */
    public int getRotationCount() {
        return rotationCount;
    }
    
    /**
     * Returns the total number of color changes performed.
     * 
     * @return total color change count
     */
    public int getColorChangeCount() {
        return colorChangeCount;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Returns the number of nodes in the Red-Black tree.
     * 
     * @return the size of the tree
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the Red-Black tree is empty.
     * 
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Gets the root data of the Red-Black tree.
     * 
     * @return the data stored in the root node
     * @throws TreeEmptyException if tree is empty
     */
    public T getRoot() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot get root of empty Red-Black tree");
        }
        return root.data;
    }
    
    /**
     * Clears the entire Red-Black tree.
     */
    public void clear() {
        root = null;
        size = 0;
        rotationCount = 0;
        colorChangeCount = 0;
        System.out.println("🗑️ Red-Black tree cleared. All nodes removed.");
    }
    
    /**
     * Prints the Red-Black tree structure with colors.
     * Shows the tree structure along with color information for each node.
     */
    public void printRedBlackTree() {
        if (isEmpty()) {
            System.out.println("🌳 Red-Black tree is empty");
            return;
        }
        
        System.out.println("🌳 Red-Black Tree Structure (with colors):");
        printRBTreeRecursive(root, "", true);
        System.out.println("📊 RB Statistics: Size=" + size + ", Height=" + getHeight() + 
                         ", Black Height=" + getBlackHeight() + ", Rotations=" + rotationCount + 
                         ", Color Changes=" + colorChangeCount);
    }
    
    /**
     * Helper method for recursive Red-Black tree printing with colors.
     */
    private void printRBTreeRecursive(RedBlackNode<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.toTreeString());
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printRBTreeRecursive(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printRBTreeRecursive(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the Red-Black tree.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "RedBlackTree{empty}";
        }
        return "RedBlackTree{root=" + root.data + ", size=" + size + ", height=" + getHeight() + 
               ", rotations=" + rotationCount + ", colorChanges=" + colorChangeCount + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating Red-Black tree operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        
        System.out.println("🌳 Red-Black Tree Interactive Demo");
        System.out.println("====================================");
        System.out.println("Experience self-balancing with color-based rebalancing!");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Value");
            System.out.println("2.  Search Value");
            System.out.println("3.  Delete Value");
            System.out.println("4.  Find Minimum");
            System.out.println("5.  Find Maximum");
            System.out.println("6.  Inorder Traversal (Sorted)");
            System.out.println("7.  Print RB Tree Structure");
            System.out.println("8.  Get Tree Height");
            System.out.println("9.  Get Black Height");
            System.out.println("10. Get Tree Size");
            System.out.println("11. Validate RB Properties");
            System.out.println("12. Get Rotation Count");
            System.out.println("13. Get Color Change Count");
            System.out.println("14. Clear Tree");
            System.out.println("15. Demo RB Balancing");
            System.out.println("16. Exit");
            System.out.print("👉 Enter your choice (1-16): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to insert: ");
                        int insertValue = scanner.nextInt();
                        rbTree.insert(insertValue);
                        break;
                        
                    case 2:
                        System.out.print("Enter value to search: ");
                        int searchValue = scanner.nextInt();
                        boolean found = rbTree.search(searchValue);
                        System.out.println(found ? "✅ Found!" : "❌ Not found!");
                        break;
                        
                    case 3:
                        System.out.print("Enter value to delete: ");
                        int deleteValue = scanner.nextInt();
                        rbTree.delete(deleteValue);
                        break;
                        
                    case 4:
                        System.out.println("📍 Minimum: " + rbTree.findMin());
                        break;
                        
                    case 5:
                        System.out.println("📍 Maximum: " + rbTree.findMax());
                        break;
                        
                    case 6:
                        rbTree.inorderTraversal();
                        break;
                        
                    case 7:
                        rbTree.printRedBlackTree();
                        break;
                        
                    case 8:
                        System.out.println("📏 Tree height: " + rbTree.getHeight());
                        break;
                        
                    case 9:
                        System.out.println("⚫ Black height: " + rbTree.getBlackHeight());
                        break;
                        
                    case 10:
                        System.out.println("📊 Tree size: " + rbTree.size());
                        break;
                        
                    case 11:
                        rbTree.isValidRedBlackTree();
                        break;
                        
                    case 12:
                        System.out.println("🔄 Total rotations performed: " + rbTree.getRotationCount());
                        break;
                        
                    case 13:
                        System.out.println("🎨 Total color changes performed: " + rbTree.getColorChangeCount());
                        break;
                        
                    case 14:
                        rbTree.clear();
                        break;
                        
                    case 15:
                        System.out.println("🎬 Demonstrating RB balancing with sequence: 1,2,3,4,5,6,7,8");
                        for (int i = 1; i <= 8; i++) {
                            System.out.println("\n--- Inserting " + i + " ---");
                            rbTree.insert(i);
                            rbTree.printRedBlackTree();
                        }
                        break;
                        
                    case 16:
                        System.out.println("👋 Thank you for using Red-Black Tree Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-16.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 