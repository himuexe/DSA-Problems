import nodes.AVLNode;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * AVL Tree Implementation - Self-Balancing Binary Search Tree
 * 
 * Named after Adelson-Velsky and Landis, AVL trees maintain balance automatically
 * through rotations, ensuring O(log n) performance for all operations.
 * 
 * Key Features:
 * - Automatic balancing after insertions and deletions
 * - Four rotation types: Left, Right, Left-Right, Right-Left
 * - Height-balanced property: |balance_factor| ≤ 1 for all nodes
 * - Guaranteed O(log n) operations in all cases
 * - Real-time balance monitoring and visualization
 * - Educational rotation demonstrations
 * 
 * AVL Properties:
 * - Maintains BST ordering property
 * - Balance factor = height(right) - height(left)
 * - Valid balance factors: -1, 0, +1
 * - Automatic rebalancing when |balance_factor| > 1
 * - Height difference between subtrees never exceeds 1
 * 
 * Rotation Types:
 * 1. Left Rotation (Right-Right case): Single rotation when right subtree is right-heavy
 * 2. Right Rotation (Left-Left case): Single rotation when left subtree is left-heavy
 * 3. Left-Right Rotation: Double rotation for left subtree that's right-heavy
 * 4. Right-Left Rotation: Double rotation for right subtree that's left-heavy
 * 
 * Time Complexities (guaranteed for AVL):
 * - Search: O(log n)
 * - Insertion: O(log n)
 * - Deletion: O(log n)
 * - All operations maintain logarithmic bound
 * 
 * Space Complexity: O(n) for storage, O(log n) for operations
 * 
 * Best Use Cases:
 * - Applications requiring guaranteed O(log n) performance
 * - Frequent searches with occasional insertions/deletions
 * - Database indexing where balance is critical
 * - Real-time systems requiring predictable performance
 * - Educational demonstrations of tree balancing
 * 
 * @param <T> the type of elements stored in this AVL tree, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class AVLTree<T extends Comparable<T>> {
    
    // Instance variables
    private AVLNode<T> root;
    private int size;
    private int rotationCount;  // Track rotations for educational purposes
    
    /**
     * Default constructor that creates an empty AVL tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public AVLTree() {
        this.root = null;
        this.size = 0;
        this.rotationCount = 0;
    }
    
    /**
     * Constructor that creates an AVL tree with a single root node.
     * 
     * @param rootData the data for the root node
     * @throws IllegalArgumentException if rootData is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public AVLTree(T rootData) {
        if (rootData == null) {
            throw new IllegalArgumentException("Root data cannot be null");
        }
        this.root = new AVLNode<>(rootData);
        this.size = 1;
        this.rotationCount = 0;
    }
    
    // ==================== AVL CORE OPERATIONS ====================
    
    /**
     * Inserts a new value into the AVL tree while maintaining balance.
     * Automatically performs rotations if needed to maintain AVL property.
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
        root = insertRecursive(root, data);
        
        if (rotationCount > initialRotations) {
            System.out.println("🔄 Performed " + (rotationCount - initialRotations) + 
                             " rotation(s) to maintain AVL balance");
        }
    }
    
    /**
     * Helper method for recursive AVL insertion with balancing.
     * 
     * @param node current node in recursion
     * @param data data to insert
     * @return the new root of this subtree after insertion and balancing
     */
    private AVLNode<T> insertRecursive(AVLNode<T> node, T data) {
        // Step 1: Perform standard BST insertion
        if (node == null) {
            size++;
            System.out.println("✅ Inserted '" + data + "' into AVL tree. Size: " + size);
            return new AVLNode<>(data);
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison == 0) {
            System.out.println("⚠️ '" + data + "' already exists in AVL tree. Duplicates not allowed.");
            return node;
        } else if (comparison < 0) {
            node.left = insertRecursive(node.left, data);
            if (node.left != null) {
                node.left.parent = node;
            }
        } else {
            node.right = insertRecursive(node.right, data);
            if (node.right != null) {
                node.right.parent = node;
            }
        }
        
        // Step 2: Update height and balance factor
        node.updateHeightAndBalance();
        
        // Step 3: Check balance and perform rotations if needed
        return rebalance(node);
    }
    
    /**
     * Deletes a value from the AVL tree while maintaining balance.
     * Automatically performs rotations if needed after deletion.
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
        
        if (!search(data)) {
            throw new TreeNodeNotFoundException("Cannot delete '" + data + "' - not found in AVL tree");
        }
        
        int initialRotations = rotationCount;
        root = deleteRecursive(root, data);
        size--;
        
        System.out.println("✅ Deleted '" + data + "' from AVL tree. Size: " + size);
        if (rotationCount > initialRotations) {
            System.out.println("🔄 Performed " + (rotationCount - initialRotations) + 
                             " rotation(s) after deletion to maintain balance");
        }
    }
    
    /**
     * Helper method for recursive AVL deletion with balancing.
     */
    private AVLNode<T> deleteRecursive(AVLNode<T> node, T data) {
        // Step 1: Perform standard BST deletion
        if (node == null) {
            return null;
        }
        
        int comparison = data.compareTo(node.data);
        
        if (comparison < 0) {
            node.left = deleteRecursive(node.left, data);
            if (node.left != null) {
                node.left.parent = node;
            }
        } else if (comparison > 0) {
            node.right = deleteRecursive(node.right, data);
            if (node.right != null) {
                node.right.parent = node;
            }
        } else {
            // Node to delete found
            
            // Case 1: Leaf node or single child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // Case 2: Two children - find inorder successor
            AVLNode<T> successor = findMinNode(node.right);
            
            // Replace node's data with successor's data
            node.data = successor.data;
            
            // Delete the successor
            node.right = deleteRecursive(node.right, successor.data);
            if (node.right != null) {
                node.right.parent = node;
            }
        }
        
        // Step 2: Update height and balance factor
        node.updateHeightAndBalance();
        
        // Step 3: Check balance and perform rotations if needed
        return rebalance(node);
    }
    
    // ==================== AVL BALANCING AND ROTATIONS ====================
    
    /**
     * Rebalances a node if it violates AVL property.
     * Determines the appropriate rotation type and performs it.
     * 
     * @param node the node to check and potentially rebalance
     * @return the new root of the rebalanced subtree
     */
    private AVLNode<T> rebalance(AVLNode<T> node) {
        if (!node.needsRebalancing()) {
            return node;  // Already balanced
        }
        
        String imbalanceType = node.getImbalanceType();
        System.out.println("⚖️ Detected " + imbalanceType + " imbalance at node " + node.data);
        
        switch (imbalanceType) {
            case "LEFT_LEFT":
                return rightRotation(node);
            case "RIGHT_RIGHT":
                return leftRotation(node);
            case "LEFT_RIGHT":
                return leftRightRotation(node);
            case "RIGHT_LEFT":
                return rightLeftRotation(node);
            default:
                return node;
        }
    }
    
    /**
     * Performs a left rotation (for Right-Right case).
     * 
     *     y                x
     *    / \              / \
     *   T1  x     =>     y   z
     *      / \          / \ / \
     *     T2  z        T1 T2 T3 T4
     *        / \
     *       T3 T4
     * 
     * @param y the node to rotate around
     * @return the new root of this subtree (x)
     */
    private AVLNode<T> leftRotation(AVLNode<T> y) {
        System.out.println("🔄 Performing LEFT rotation on node " + y.data);
        
        AVLNode<T> x = y.right;
        AVLNode<T> T2 = x.left;
        
        // Perform rotation
        x.left = y;
        y.right = T2;
        
        // Update parent pointers
        x.parent = y.parent;
        y.parent = x;
        if (T2 != null) {
            T2.parent = y;
        }
        
        // Update heights and balance factors
        y.updateHeightAndBalance();
        x.updateHeightAndBalance();
        
        rotationCount++;
        System.out.println("✅ Left rotation complete. New subtree root: " + x.data);
        
        return x;
    }
    
    /**
     * Performs a right rotation (for Left-Left case).
     * 
     *       y              x
     *      / \            / \
     *     x   T4   =>    z   y
     *    / \            / \ / \
     *   z   T3         T1 T2 T3 T4
     *  / \
     * T1 T2
     * 
     * @param y the node to rotate around
     * @return the new root of this subtree (x)
     */
    private AVLNode<T> rightRotation(AVLNode<T> y) {
        System.out.println("🔄 Performing RIGHT rotation on node " + y.data);
        
        AVLNode<T> x = y.left;
        AVLNode<T> T3 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T3;
        
        // Update parent pointers
        x.parent = y.parent;
        y.parent = x;
        if (T3 != null) {
            T3.parent = y;
        }
        
        // Update heights and balance factors
        y.updateHeightAndBalance();
        x.updateHeightAndBalance();
        
        rotationCount++;
        System.out.println("✅ Right rotation complete. New subtree root: " + x.data);
        
        return x;
    }
    
    /**
     * Performs a left-right rotation (for Left-Right case).
     * This is a double rotation: left rotation on left child, then right rotation on root.
     * 
     * @param node the node to rotate around
     * @return the new root of this subtree
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        System.out.println("🔄 Performing LEFT-RIGHT double rotation on node " + node.data);
        
        // First rotation: left rotation on left child
        node.left = leftRotation(node.left);
        node.left.parent = node;
        
        // Second rotation: right rotation on root
        return rightRotation(node);
    }
    
    /**
     * Performs a right-left rotation (for Right-Left case).
     * This is a double rotation: right rotation on right child, then left rotation on root.
     * 
     * @param node the node to rotate around
     * @return the new root of this subtree
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        System.out.println("🔄 Performing RIGHT-LEFT double rotation on node " + node.data);
        
        // First rotation: right rotation on right child
        node.right = rightRotation(node.right);
        node.right.parent = node;
        
        // Second rotation: left rotation on root
        return leftRotation(node);
    }
    
    // ==================== SEARCH AND UTILITY OPERATIONS ====================
    
    /**
     * Searches for a value in the AVL tree.
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
        
        return searchRecursive(root, data);
    }
    
    /**
     * Helper method for recursive search.
     */
    private boolean searchRecursive(AVLNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return searchRecursive(node.left, data);
        } else {
            return searchRecursive(node.right, data);
        }
    }
    
    /**
     * Finds and returns the minimum value in the AVL tree.
     * 
     * @return the minimum value
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public T findMin() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find minimum in empty AVL tree");
        }
        
        AVLNode<T> minNode = findMinNode(root);
        return minNode.data;
    }
    
    /**
     * Helper method to find the node with minimum value.
     */
    private AVLNode<T> findMinNode(AVLNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * Finds and returns the maximum value in the AVL tree.
     * 
     * @return the maximum value
     * @throws TreeEmptyException if tree is empty
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public T findMax() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find maximum in empty AVL tree");
        }
        
        AVLNode<T> maxNode = findMaxNode(root);
        return maxNode.data;
    }
    
    /**
     * Helper method to find the node with maximum value.
     */
    private AVLNode<T> findMaxNode(AVLNode<T> node) {
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
            throw new TreeEmptyException("Cannot perform inorder traversal on empty AVL tree");
        }
        
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        
        System.out.println("📋 AVL Inorder Traversal (Sorted): " + result);
        return result;
    }
    
    /**
     * Helper method for recursive inorder traversal.
     */
    private void inorderRecursive(AVLNode<T> node, List<T> result) {
        if (node != null) {
            inorderRecursive(node.left, result);
            result.add(node.data);
            inorderRecursive(node.right, result);
        }
    }
    
    // ==================== AVL ANALYSIS AND VALIDATION ====================
    
    /**
     * Validates that the tree maintains AVL properties.
     * Checks both BST property and AVL balance property.
     * 
     * @return true if valid AVL tree, false otherwise
     */
    public boolean isValidAVL() {
        boolean valid = isValidAVLRecursive(root, null, null);
        if (valid) {
            System.out.println("✅ AVL tree is valid - maintains all AVL properties");
        } else {
            System.out.println("❌ AVL tree is invalid - violates AVL properties");
        }
        return valid;
    }
    
    /**
     * Helper method for AVL validation.
     */
    private boolean isValidAVLRecursive(AVLNode<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        
        // Check BST property
        if ((min != null && node.data.compareTo(min) <= 0) ||
            (max != null && node.data.compareTo(max) >= 0)) {
            return false;
        }
        
        // Check AVL balance property
        if (!node.isBalanced()) {
            return false;
        }
        
        // Verify height and balance factor calculations
        int leftHeight = (node.left != null) ? node.left.height : -1;
        int rightHeight = (node.right != null) ? node.right.height : -1;
        int expectedHeight = Math.max(leftHeight, rightHeight) + 1;
        int expectedBalance = rightHeight - leftHeight;
        
        if (node.height != expectedHeight || node.balanceFactor != expectedBalance) {
            return false;
        }
        
        // Recursively validate subtrees
        return isValidAVLRecursive(node.left, min, node.data) &&
               isValidAVLRecursive(node.right, node.data, max);
    }
    
    /**
     * Calculates and returns the height of the AVL tree.
     * 
     * @return the height of the tree
     */
    public int getHeight() {
        return (root != null) ? root.height : -1;
    }
    
    /**
     * Gets the balance factor of the root node.
     * 
     * @return the balance factor of the root
     */
    public int getRootBalance() {
        return (root != null) ? root.balanceFactor : 0;
    }
    
    /**
     * Returns the total number of rotations performed.
     * Useful for analyzing tree balance efficiency.
     * 
     * @return total rotation count
     */
    public int getRotationCount() {
        return rotationCount;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Returns the number of nodes in the AVL tree.
     * 
     * @return the size of the tree
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the AVL tree is empty.
     * 
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Gets the root data of the AVL tree.
     * 
     * @return the data stored in the root node
     * @throws TreeEmptyException if tree is empty
     */
    public T getRoot() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot get root of empty AVL tree");
        }
        return root.data;
    }
    
    /**
     * Clears the entire AVL tree.
     */
    public void clear() {
        root = null;
        size = 0;
        rotationCount = 0;
        System.out.println("🗑️ AVL tree cleared. All nodes removed.");
    }
    
    /**
     * Prints the AVL tree structure with balance factors.
     * Shows the tree structure along with balance information for each node.
     */
    public void printAVL() {
        if (isEmpty()) {
            System.out.println("🌳 AVL tree is empty");
            return;
        }
        
        System.out.println("🌳 AVL Tree Structure (with balance factors):");
        printAVLRecursive(root, "", true);
        System.out.println("📊 AVL Statistics: Size=" + size + ", Height=" + getHeight() + 
                         ", Rotations=" + rotationCount + ", Root Balance=" + getRootBalance());
    }
    
    /**
     * Helper method for recursive AVL printing with balance factors.
     */
    private void printAVLRecursive(AVLNode<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.toCompactString());
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printAVLRecursive(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printAVLRecursive(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the AVL tree.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "AVLTree{empty}";
        }
        return "AVLTree{root=" + root.data + ", size=" + size + ", height=" + getHeight() + 
               ", rotations=" + rotationCount + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating AVL tree operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree<Integer> avl = new AVLTree<>();
        
        System.out.println("🌳 AVL Tree Interactive Demo");
        System.out.println("==============================");
        System.out.println("Watch automatic balancing in action!");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Value");
            System.out.println("2.  Search Value");
            System.out.println("3.  Delete Value");
            System.out.println("4.  Find Minimum");
            System.out.println("5.  Find Maximum");
            System.out.println("6.  Inorder Traversal (Sorted)");
            System.out.println("7.  Print AVL Structure");
            System.out.println("8.  Get AVL Height");
            System.out.println("9.  Get Tree Size");
            System.out.println("10. Validate AVL Properties");
            System.out.println("11. Get Rotation Count");
            System.out.println("12. Clear Tree");
            System.out.println("13. Demo Auto-Balance");
            System.out.println("14. Exit");
            System.out.print("👉 Enter your choice (1-14): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to insert: ");
                        int insertValue = scanner.nextInt();
                        avl.insert(insertValue);
                        break;
                        
                    case 2:
                        System.out.print("Enter value to search: ");
                        int searchValue = scanner.nextInt();
                        boolean found = avl.search(searchValue);
                        System.out.println(found ? "✅ Found!" : "❌ Not found!");
                        break;
                        
                    case 3:
                        System.out.print("Enter value to delete: ");
                        int deleteValue = scanner.nextInt();
                        avl.delete(deleteValue);
                        break;
                        
                    case 4:
                        System.out.println("📍 Minimum: " + avl.findMin());
                        break;
                        
                    case 5:
                        System.out.println("📍 Maximum: " + avl.findMax());
                        break;
                        
                    case 6:
                        avl.inorderTraversal();
                        break;
                        
                    case 7:
                        avl.printAVL();
                        break;
                        
                    case 8:
                        System.out.println("📏 AVL height: " + avl.getHeight());
                        break;
                        
                    case 9:
                        System.out.println("📊 Tree size: " + avl.size());
                        break;
                        
                    case 10:
                        avl.isValidAVL();
                        break;
                        
                    case 11:
                        System.out.println("🔄 Total rotations performed: " + avl.getRotationCount());
                        break;
                        
                    case 12:
                        avl.clear();
                        break;
                        
                    case 13:
                        System.out.println("🎬 Demonstrating auto-balance with sequence: 1,2,3,4,5,6,7");
                        for (int i = 1; i <= 7; i++) {
                            System.out.println("\n--- Inserting " + i + " ---");
                            avl.insert(i);
                            avl.printAVL();
                        }
                        break;
                        
                    case 14:
                        System.out.println("👋 Thank you for using AVL Tree Demo!");
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