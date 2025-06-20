import nodes.TreeNode;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * Binary Tree Implementation with Comprehensive Operations
 * 
 * This class implements a binary tree data structure where each node has at most two children,
 * referred to as the left child and right child. It provides all fundamental tree operations
 * including insertion, deletion, traversals, and tree analysis.
 * 
 * Key Features:
 * - Generic implementation supporting any Comparable data type
 * - All three depth-first traversals (inorder, preorder, postorder)
 * - Level-order (breadth-first) traversal
 * - Tree structure analysis (height, size, balance checking)
 * - Node insertion and deletion with proper tree maintenance
 * - Comprehensive search operations
 * - Educational console output with visual indicators
 * 
 * Tree Structure:
 * - Each node contains data and references to left and right children
 * - Parent references maintained for efficient navigation
 * - Root node serves as the entry point to the tree
 * - Supports duplicate values (unlike BST)
 * 
 * Traversal Methods:
 * - Inorder: Left → Root → Right (sorted order for BST)
 * - Preorder: Root → Left → Right (useful for tree copying)
 * - Postorder: Left → Right → Root (useful for tree deletion)
 * - Level-order: Level by level using queue (BFS approach)
 * 
 * Best Use Cases:
 * - Expression trees for mathematical expressions
 * - Decision trees for algorithms
 * - File system hierarchies
 * - Organizational structures
 * - Huffman coding trees
 * 
 * Time Complexities (for general binary tree):
 * - Insertion: O(1) at specific position, O(n) for search-based insertion
 * - Deletion: O(n) for search and removal
 * - Search: O(n) in worst case (unbalanced tree)
 * - Traversals: O(n) to visit all nodes
 * - Height calculation: O(n)
 * 
 * Space Complexity: O(n) for storage, O(h) for recursive operations where h is height
 * 
 * @param <T> the type of elements stored in this tree, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class BinaryTree<T extends Comparable<T>> {
    
    // Instance variables
    private TreeNode<T> root;
    private int size;
    
    /**
     * Default constructor that creates an empty binary tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }
    
    /**
     * Constructor that creates a binary tree with a single root node.
     * 
     * @param rootData the data for the root node
     * @throws IllegalArgumentException if rootData is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BinaryTree(T rootData) {
        if (rootData == null) {
            throw new IllegalArgumentException("Root data cannot be null");
        }
        this.root = new TreeNode<>(rootData);
        this.size = 1;
    }
    
    // ==================== BASIC TREE OPERATIONS ====================
    
    /**
     * Inserts a new node as the root of the tree.
     * If tree already has a root, the old root becomes the left child of the new root.
     * 
     * @param data the data to insert as the new root
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void insertRoot(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        TreeNode<T> newRoot = new TreeNode<>(data);
        if (root != null) {
            newRoot.left = root;
            root.parent = newRoot;
        }
        root = newRoot;
        size++;
        
        System.out.println("✅ Inserted '" + data + "' as new root. Tree size: " + size);
    }
    
    /**
     * Inserts data as the left child of the specified parent node.
     * 
     * @param parentData the data of the parent node
     * @param childData the data to insert as left child
     * @throws TreeNodeNotFoundException if parent node is not found
     * @throws IllegalArgumentException if data is null or parent already has left child
     * 
     * Time Complexity: O(n) for searching parent
     * Space Complexity: O(h) for recursion stack
     */
    public void insertLeft(T parentData, T childData) {
        if (parentData == null || childData == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        TreeNode<T> parent = findNode(parentData);
        if (parent == null) {
            throw new TreeNodeNotFoundException("Parent node with data '" + parentData + "' not found");
        }
        if (parent.left != null) {
            throw new IllegalArgumentException("Parent already has a left child");
        }
        
        TreeNode<T> newNode = new TreeNode<>(childData);
        parent.left = newNode;
        newNode.parent = parent;
        size++;
        
        System.out.println("✅ Inserted '" + childData + "' as left child of '" + parentData + "'. Tree size: " + size);
    }
    
    /**
     * Inserts data as the right child of the specified parent node.
     * 
     * @param parentData the data of the parent node
     * @param childData the data to insert as right child
     * @throws TreeNodeNotFoundException if parent node is not found
     * @throws IllegalArgumentException if data is null or parent already has right child
     * 
     * Time Complexity: O(n) for searching parent
     * Space Complexity: O(h) for recursion stack
     */
    public void insertRight(T parentData, T childData) {
        if (parentData == null || childData == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        TreeNode<T> parent = findNode(parentData);
        if (parent == null) {
            throw new TreeNodeNotFoundException("Parent node with data '" + parentData + "' not found");
        }
        if (parent.right != null) {
            throw new IllegalArgumentException("Parent already has a right child");
        }
        
        TreeNode<T> newNode = new TreeNode<>(childData);
        parent.right = newNode;
        newNode.parent = parent;
        size++;
        
        System.out.println("✅ Inserted '" + childData + "' as right child of '" + parentData + "'. Tree size: " + size);
    }
    
    /**
     * Searches for a node with the specified data.
     * 
     * @param data the data to search for
     * @return the node containing the data, or null if not found
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion
     */
    public TreeNode<T> findNode(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Search data cannot be null");
        }
        return findNodeRecursive(root, data);
    }
    
    /**
     * Helper method for recursive node searching.
     */
    private TreeNode<T> findNodeRecursive(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (node.data.equals(data)) {
            return node;
        }
        
        // Search in left subtree
        TreeNode<T> leftResult = findNodeRecursive(node.left, data);
        if (leftResult != null) {
            return leftResult;
        }
        
        // Search in right subtree
        return findNodeRecursive(node.right, data);
    }
    
    /**
     * Checks if the tree contains the specified data.
     * 
     * @param data the data to search for
     * @return true if data is found, false otherwise
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public boolean contains(T data) {
        return findNode(data) != null;
    }
    
    // ==================== TREE TRAVERSAL METHODS ====================
    
    /**
     * Performs inorder traversal (Left → Root → Right) and returns the result.
     * For BST, this returns elements in sorted order.
     * 
     * @return list of elements in inorder sequence
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for result list + O(h) for recursion
     */
    public List<T> inorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform inorder traversal on empty tree");
        }
        
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        
        System.out.println("📋 Inorder Traversal: " + result);
        return result;
    }
    
    /**
     * Helper method for recursive inorder traversal.
     */
    private void inorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            inorderRecursive(node.left, result);   // Left
            result.add(node.data);                 // Root
            inorderRecursive(node.right, result);  // Right
        }
    }
    
    /**
     * Performs preorder traversal (Root → Left → Right) and returns the result.
     * Useful for creating a copy of the tree structure.
     * 
     * @return list of elements in preorder sequence
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for result list + O(h) for recursion
     */
    public List<T> preorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform preorder traversal on empty tree");
        }
        
        List<T> result = new ArrayList<>();
        preorderRecursive(root, result);
        
        System.out.println("📋 Preorder Traversal: " + result);
        return result;
    }
    
    /**
     * Helper method for recursive preorder traversal.
     */
    private void preorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.data);                 // Root
            preorderRecursive(node.left, result);  // Left
            preorderRecursive(node.right, result); // Right
        }
    }
    
    /**
     * Performs postorder traversal (Left → Right → Root) and returns the result.
     * Useful for safely deleting all nodes in the tree.
     * 
     * @return list of elements in postorder sequence
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for result list + O(h) for recursion
     */
    public List<T> postorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform postorder traversal on empty tree");
        }
        
        List<T> result = new ArrayList<>();
        postorderRecursive(root, result);
        
        System.out.println("📋 Postorder Traversal: " + result);
        return result;
    }
    
    /**
     * Helper method for recursive postorder traversal.
     */
    private void postorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            postorderRecursive(node.left, result);  // Left
            postorderRecursive(node.right, result); // Right
            result.add(node.data);                  // Root
        }
    }
    
    /**
     * Performs level-order traversal (breadth-first) and returns the result.
     * Visits nodes level by level from left to right.
     * 
     * @return list of elements in level-order sequence
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(w) where w is maximum width of tree
     */
    public List<T> levelOrderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform level-order traversal on empty tree");
        }
        
        List<T> result = new ArrayList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            result.add(current.data);
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        
        System.out.println("📋 Level-order Traversal: " + result);
        return result;
    }
    
    // ==================== TREE ANALYSIS METHODS ====================
    
    /**
     * Calculates and returns the height of the tree.
     * Height is the longest path from root to a leaf node.
     * 
     * @return the height of the tree (-1 for empty tree)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is height
     */
    public int getHeight() {
        return getHeightRecursive(root);
    }
    
    /**
     * Helper method for recursive height calculation.
     */
    private int getHeightRecursive(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }
        
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Returns the number of nodes in the tree.
     * 
     * @return the size of the tree
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the tree is empty.
     * 
     * @return true if tree is empty, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Gets the root data of the tree.
     * 
     * @return the data stored in the root node
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T getRoot() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot get root of empty tree");
        }
        return root.data;
    }
    
    /**
     * Clears the entire tree, removing all nodes.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
        System.out.println("🗑️ Tree cleared. All nodes removed.");
    }
    
    /**
     * Counts the number of leaf nodes in the tree.
     * 
     * @return the number of leaf nodes
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int countLeaves() {
        return countLeavesRecursive(root);
    }
    
    /**
     * Helper method for recursive leaf counting.
     */
    private int countLeavesRecursive(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        return countLeavesRecursive(node.left) + countLeavesRecursive(node.right);
    }
    
    // ==================== DELETION OPERATIONS ====================
    
    /**
     * Deletes a node with the specified data value.
     * For binary trees (not BST), this deletes the first occurrence found during traversal.
     * 
     * @param data the data value to delete
     * @return true if deletion was successful, false if data not found
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public boolean delete(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot delete null data");
        }
        
        if (isEmpty()) {
            System.out.println("❌ Cannot delete from empty tree");
            return false;
        }
        
        // Special case: deleting root
        if (root.data.equals(data)) {
            return deleteRoot();
        }
        
        TreeNode<T> nodeToDelete = findNode(data);
        if (nodeToDelete == null) {
            System.out.println("❌ Data '" + data + "' not found in tree");
            return false;
        }
        
        return deleteNode(nodeToDelete);
    }
    
    /**
     * Deletes the root node and restructures the tree.
     * 
     * @return true if deletion was successful
     */
    private boolean deleteRoot() {
        if (root == null) {
            return false;
        }
        
        T rootData = root.data;
        
        // Case 1: Root is a leaf
        if (root.isLeaf()) {
            root = null;
            size--;
            System.out.println("✅ Deleted root '" + rootData + "' (was leaf). Tree is now empty.");
            return true;
        }
        
        // Case 2: Root has only left child
        if (root.left != null && root.right == null) {
            root = root.left;
            root.parent = null;
            size--;
            System.out.println("✅ Deleted root '" + rootData + "'. Left child promoted to root.");
            return true;
        }
        
        // Case 3: Root has only right child
        if (root.right != null && root.left == null) {
            root = root.right;
            root.parent = null;
            size--;
            System.out.println("✅ Deleted root '" + rootData + "'. Right child promoted to root.");
            return true;
        }
        
        // Case 4: Root has both children - use replacement strategy
        // For general binary trees, we'll replace with rightmost node in left subtree
        TreeNode<T> replacement = findRightmostNode(root.left);
        T replacementData = replacement.data;
        
        // Delete the replacement node from its current position
        deleteNode(replacement);
        
        // Replace root's data with replacement data
        root.data = replacementData;
        
        System.out.println("✅ Deleted root '" + rootData + "'. Replaced with '" + replacementData + "'.");
        return true;
    }
    
    /**
     * Deletes a specific node from the tree.
     * 
     * @param nodeToDelete the node to delete
     * @return true if deletion was successful
     */
    private boolean deleteNode(TreeNode<T> nodeToDelete) {
        if (nodeToDelete == null) {
            return false;
        }
        
        T nodeData = nodeToDelete.data;
        TreeNode<T> parent = nodeToDelete.parent;
        
        // Case 1: Node is a leaf
        if (nodeToDelete.isLeaf()) {
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            size--;
            System.out.println("✅ Deleted leaf node '" + nodeData + "'");
            return true;
        }
        
        // Case 2: Node has only left child
        if (nodeToDelete.left != null && nodeToDelete.right == null) {
            TreeNode<T> child = nodeToDelete.left;
            
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
                child.parent = parent;
            } else {
                // Node to delete is root
                root = child;
                child.parent = null;
            }
            size--;
            System.out.println("✅ Deleted node '" + nodeData + "'. Left child promoted.");
            return true;
        }
        
        // Case 3: Node has only right child
        if (nodeToDelete.right != null && nodeToDelete.left == null) {
            TreeNode<T> child = nodeToDelete.right;
            
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
                child.parent = parent;
            } else {
                // Node to delete is root
                root = child;
                child.parent = null;
            }
            size--;
            System.out.println("✅ Deleted node '" + nodeData + "'. Right child promoted.");
            return true;
        }
        
        // Case 4: Node has both children
        // Replace with rightmost node in left subtree (predecessor)
        TreeNode<T> replacement = findRightmostNode(nodeToDelete.left);
        T replacementData = replacement.data;
        
        // Delete the replacement node recursively
        deleteNode(replacement);
        
        // Replace the data of nodeToDelete with replacement data
        nodeToDelete.data = replacementData;
        
        System.out.println("✅ Deleted node '" + nodeData + "'. Replaced with '" + replacementData + "'.");
        return true;
    }
    
    /**
     * Finds the rightmost node in a subtree (used for deletion replacement).
     * 
     * @param subtreeRoot the root of the subtree
     * @return the rightmost node
     */
    private TreeNode<T> findRightmostNode(TreeNode<T> subtreeRoot) {
        if (subtreeRoot == null) {
            return null;
        }
        
        while (subtreeRoot.right != null) {
            subtreeRoot = subtreeRoot.right;
        }
        
        return subtreeRoot;
    }
    
    /**
     * Deletes an entire subtree rooted at the node with given data.
     * 
     * @param data the root data of the subtree to delete
     * @return the number of nodes deleted
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(n) - may need to search entire tree
     * Space Complexity: O(h)
     */
    public int deleteSubtree(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot delete subtree with null data");
        }
        
        TreeNode<T> subtreeRoot = findNode(data);
        if (subtreeRoot == null) {
            System.out.println("❌ Node '" + data + "' not found. Cannot delete subtree.");
            return 0;
        }
        
        int deletedCount = countNodesInSubtree(subtreeRoot);
        
        // If subtree root is the main root
        if (subtreeRoot == root) {
            root = null;
            size = 0;
            System.out.println("✅ Deleted entire tree (" + deletedCount + " nodes)");
            return deletedCount;
        }
        
        // Remove subtree from parent
        TreeNode<T> parent = subtreeRoot.parent;
        if (parent.left == subtreeRoot) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        
        size -= deletedCount;
        System.out.println("✅ Deleted subtree rooted at '" + data + "' (" + deletedCount + " nodes)");
        return deletedCount;
    }
    
    /**
     * Counts the number of nodes in a subtree.
     * 
     * @param subtreeRoot the root of the subtree
     * @return the number of nodes in the subtree
     */
    private int countNodesInSubtree(TreeNode<T> subtreeRoot) {
        if (subtreeRoot == null) {
            return 0;
        }
        
        return 1 + countNodesInSubtree(subtreeRoot.left) + countNodesInSubtree(subtreeRoot.right);
    }
    
    /**
     * Deletes all leaf nodes from the tree.
     * This operation can be performed multiple times to prune the tree level by level.
     * 
     * @return the number of leaf nodes deleted
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int deleteAllLeaves() {
        if (isEmpty()) {
            System.out.println("❌ Cannot delete leaves from empty tree");
            return 0;
        }
        
        List<TreeNode<T>> leaves = collectLeaves(root);
        int deletedCount = 0;
        
        for (TreeNode<T> leaf : leaves) {
            if (leaf != root) { // Don't delete root if it's the only node
                TreeNode<T> parent = leaf.parent;
                if (parent.left == leaf) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deletedCount++;
            }
        }
        
        // Special case: if root is the only leaf
        if (leaves.size() == 1 && leaves.get(0) == root) {
            root = null;
            deletedCount = 1;
            System.out.println("✅ Deleted root (was the only leaf). Tree is now empty.");
        } else {
            System.out.println("✅ Deleted " + deletedCount + " leaf nodes");
        }
        
        size -= deletedCount;
        return deletedCount;
    }
    
    /**
     * Collects all leaf nodes in a subtree.
     * 
     * @param node the root of the subtree
     * @return list of leaf nodes
     */
    private List<TreeNode<T>> collectLeaves(TreeNode<T> node) {
        List<TreeNode<T>> leaves = new ArrayList<>();
        if (node == null) {
            return leaves;
        }
        
        if (node.isLeaf()) {
            leaves.add(node);
        } else {
            leaves.addAll(collectLeaves(node.left));
            leaves.addAll(collectLeaves(node.right));
        }
        
        return leaves;
    }
    
    /**
     * Deletes nodes at a specific level in the tree.
     * Level 0 is the root, level 1 contains root's children, etc.
     * 
     * @param level the level to delete (0-based)
     * @return the number of nodes deleted
     * @throws IllegalArgumentException if level is negative
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for level-order traversal
     */
    public int deleteLevel(int level) {
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative");
        }
        
        if (isEmpty()) {
            System.out.println("❌ Cannot delete from empty tree");
            return 0;
        }
        
        if (level == 0) {
            // Deleting root level
            int deletedCount = size;
            root = null;
            size = 0;
            System.out.println("✅ Deleted level 0 (root). Tree is now empty. Deleted " + deletedCount + " nodes.");
            return deletedCount;
        }
        
        List<TreeNode<T>> nodesAtLevel = getNodesAtLevel(level);
        if (nodesAtLevel.isEmpty()) {
            System.out.println("❌ No nodes found at level " + level);
            return 0;
        }
        
        int deletedCount = 0;
        for (TreeNode<T> node : nodesAtLevel) {
            // Count nodes in subtrees before deletion
            int subtreeSize = countNodesInSubtree(node);
            
            // Remove node from its parent
            TreeNode<T> parent = node.parent;
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            
            deletedCount += subtreeSize;
        }
        
        size -= deletedCount;
        System.out.println("✅ Deleted level " + level + " (" + nodesAtLevel.size() + " nodes and their " + 
                         (deletedCount - nodesAtLevel.size()) + " descendants)");
        return deletedCount;
    }
    
    /**
     * Gets all nodes at a specific level.
     * 
     * @param targetLevel the level to get nodes from
     * @return list of nodes at the specified level
     */
    private List<TreeNode<T>> getNodesAtLevel(int targetLevel) {
        List<TreeNode<T>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode<T>> queue = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        
        queue.offer(root);
        levels.offer(0);
        
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            int currentLevel = levels.poll();
            
            if (currentLevel == targetLevel) {
                result.add(current);
            } else if (currentLevel < targetLevel) {
                if (current.left != null) {
                    queue.offer(current.left);
                    levels.offer(currentLevel + 1);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                    levels.offer(currentLevel + 1);
                }
            }
        }
        
        return result;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Prints the tree structure in a visual format.
     * Shows the hierarchical structure with indentation.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("🌳 Tree is empty");
            return;
        }
        
        System.out.println("🌳 Binary Tree Structure:");
        printTreeRecursive(root, "", true);
        System.out.println("📊 Tree Statistics: Size=" + size + ", Height=" + getHeight() + ", Leaves=" + countLeaves());
    }
    
    /**
     * Helper method for recursive tree printing.
     */
    private void printTreeRecursive(TreeNode<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.data);
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTreeRecursive(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printTreeRecursive(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the tree.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "BinaryTree{empty}";
        }
        return "BinaryTree{root=" + root.data + ", size=" + size + ", height=" + getHeight() + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating BinaryTree operations.
     * Provides a menu-driven interface for hands-on learning.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();
        
        System.out.println("🌳 Binary Tree Interactive Demo");
        System.out.println("=====================================");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Root Node");
            System.out.println("2.  Insert Left Child");
            System.out.println("3.  Insert Right Child");
            System.out.println("4.  Search for Node");
            System.out.println("5.  Inorder Traversal");
            System.out.println("6.  Preorder Traversal");
            System.out.println("7.  Postorder Traversal");
            System.out.println("8.  Level-order Traversal");
            System.out.println("9.  Print Tree Structure");
            System.out.println("10. Get Tree Height");
            System.out.println("11. Get Tree Size");
            System.out.println("12. Count Leaf Nodes");
            System.out.println("13. Clear Tree");
            System.out.println("14. Delete Node");
            System.out.println("15. Delete Subtree");
            System.out.println("16. Delete All Leaves");
            System.out.println("17. Delete Level");
            System.out.println("18. Exit");
            System.out.print("👉 Enter your choice (1-18): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter root data: ");
                        int rootData = scanner.nextInt();
                        tree.insertRoot(rootData);
                        break;
                        
                    case 2:
                        System.out.print("Enter parent data: ");
                        int parentLeft = scanner.nextInt();
                        System.out.print("Enter left child data: ");
                        int leftChild = scanner.nextInt();
                        tree.insertLeft(parentLeft, leftChild);
                        break;
                        
                    case 3:
                        System.out.print("Enter parent data: ");
                        int parentRight = scanner.nextInt();
                        System.out.print("Enter right child data: ");
                        int rightChild = scanner.nextInt();
                        tree.insertRight(parentRight, rightChild);
                        break;
                        
                    case 4:
                        System.out.print("Enter data to search: ");
                        int searchData = scanner.nextInt();
                        if (tree.contains(searchData)) {
                            System.out.println("✅ Found '" + searchData + "' in the tree");
                        } else {
                            System.out.println("❌ '" + searchData + "' not found in the tree");
                        }
                        break;
                        
                    case 5:
                        tree.inorderTraversal();
                        break;
                        
                    case 6:
                        tree.preorderTraversal();
                        break;
                        
                    case 7:
                        tree.postorderTraversal();
                        break;
                        
                    case 8:
                        tree.levelOrderTraversal();
                        break;
                        
                    case 9:
                        tree.printTree();
                        break;
                        
                    case 10:
                        System.out.println("📏 Tree height: " + tree.getHeight());
                        break;
                        
                    case 11:
                        System.out.println("📊 Tree size: " + tree.size());
                        break;
                        
                    case 12:
                        System.out.println("🍃 Leaf nodes: " + tree.countLeaves());
                        break;
                        
                    case 13:
                        tree.clear();
                        break;
                        
                    case 14:
                        System.out.print("Enter data to delete: ");
                        int deleteData = scanner.nextInt();
                        if (tree.delete(deleteData)) {
                            System.out.println("✅ Deleted '" + deleteData + "' from the tree");
                        } else {
                            System.out.println("❌ Data '" + deleteData + "' not found in the tree");
                        }
                        break;
                        
                    case 15:
                        System.out.print("Enter subtree root data: ");
                        int subtreeRootData = scanner.nextInt();
                        int deletedSubtreeCount = tree.deleteSubtree(subtreeRootData);
                        System.out.println("✅ Deleted subtree rooted at '" + subtreeRootData + "' (" + deletedSubtreeCount + " nodes)");
                        break;
                        
                    case 16:
                        int deletedLeavesCount = tree.deleteAllLeaves();
                        System.out.println("✅ Deleted " + deletedLeavesCount + " leaf nodes");
                        break;
                        
                    case 17:
                        System.out.print("Enter level to delete: ");
                        int levelToDelete = scanner.nextInt();
                        int deletedLevelCount = tree.deleteLevel(levelToDelete);
                        System.out.println("✅ Deleted level " + levelToDelete + " (" + deletedLevelCount + " nodes)");
                        break;
                        
                    case 18:
                        System.out.println("👋 Thank you for using Binary Tree Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-18.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 