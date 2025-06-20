import nodes.TreeNode;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * Binary Search Tree (BST) Implementation with Comprehensive Operations
 * 
 * This class implements a binary search tree data structure that maintains the BST property:
 * for every node, all values in the left subtree are less than the node's value,
 * and all values in the right subtree are greater than the node's value.
 * 
 * Key Features:
 * - Maintains BST ordering property for efficient operations
 * - Generic implementation supporting any Comparable data type
 * - Efficient search, insertion, and deletion operations
 * - All traversal methods (inorder gives sorted sequence)
 * - Min/max element finding in O(log n) time
 * - Successor and predecessor operations
 * - Tree validation and balance analysis
 * - Educational console output with visual indicators
 * 
 * BST Properties:
 * - Left subtree contains only nodes with values less than parent
 * - Right subtree contains only nodes with values greater than parent
 * - Both left and right subtrees are also binary search trees
 * - No duplicate values allowed (unlike general binary tree)
 * - Inorder traversal produces sorted sequence
 * 
 * Time Complexities (average case for balanced BST):
 * - Search: O(log n)
 * - Insertion: O(log n)
 * - Deletion: O(log n)
 * - Min/Max: O(log n)
 * - Traversals: O(n)
 * - Successor/Predecessor: O(log n)
 * 
 * Time Complexities (worst case for unbalanced BST):
 * - Search: O(n) - degenerates to linked list
 * - Insertion: O(n)
 * - Deletion: O(n)
 * 
 * Space Complexity: O(n) for storage, O(h) for recursive operations where h is height
 * 
 * Best Use Cases:
 * - Maintaining sorted data with frequent insertions/deletions
 * - Dictionary implementations
 * - Database indexing
 * - Range queries and ordered statistics
 * - Symbol tables in compilers
 * 
 * @param <T> the type of elements stored in this BST, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class BinarySearchTree<T extends Comparable<T>> {
    
    // Instance variables
    private TreeNode<T> root;
    private int size;
    
    /**
     * Default constructor that creates an empty binary search tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    /**
     * Constructor that creates a BST with a single root node.
     * 
     * @param rootData the data for the root node
     * @throws IllegalArgumentException if rootData is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BinarySearchTree(T rootData) {
        if (rootData == null) {
            throw new IllegalArgumentException("Root data cannot be null");
        }
        this.root = new TreeNode<>(rootData);
        this.size = 1;
    }
    
    // ==================== BST CORE OPERATIONS ====================
    
    /**
     * Inserts a new value into the BST while maintaining BST properties.
     * Duplicates are not allowed - attempting to insert existing value is ignored.
     * 
     * @param data the data to insert
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(log n) for recursion stack
     */
    public void insert(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data");
        }
        
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            System.out.println("✅ Inserted '" + data + "' as root. BST size: " + size);
        } else {
            boolean inserted = insertRecursive(root, data);
            if (inserted) {
                System.out.println("✅ Inserted '" + data + "' into BST. BST size: " + size);
            } else {
                System.out.println("⚠️ '" + data + "' already exists in BST. Duplicates not allowed.");
            }
        }
    }
    
    /**
     * Helper method for recursive BST insertion.
     * 
     * @param node current node in recursion
     * @param data data to insert
     * @return true if insertion occurred, false if duplicate found
     */
    private boolean insertRecursive(TreeNode<T> node, T data) {
        int comparison = data.compareTo(node.data);
        
        if (comparison == 0) {
            // Duplicate found - don't insert
            return false;
        } else if (comparison < 0) {
            // Insert in left subtree
            if (node.left == null) {
                node.left = new TreeNode<>(data);
                node.left.parent = node;
                size++;
                return true;
            } else {
                return insertRecursive(node.left, data);
            }
        } else {
            // Insert in right subtree
            if (node.right == null) {
                node.right = new TreeNode<>(data);
                node.right.parent = node;
                size++;
                return true;
            } else {
                return insertRecursive(node.right, data);
            }
        }
    }
    
    /**
     * Searches for a value in the BST using BST properties for efficiency.
     * 
     * @param data the data to search for
     * @return true if found, false otherwise
     * @throws IllegalArgumentException if data is null
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(log n) for recursion stack
     */
    public boolean search(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Search data cannot be null");
        }
        
        boolean found = searchRecursive(root, data);
        if (found) {
            System.out.println("✅ Found '" + data + "' in BST");
        } else {
            System.out.println("❌ '" + data + "' not found in BST");
        }
        return found;
    }
    
    /**
     * Helper method for recursive BST search.
     */
    private boolean searchRecursive(TreeNode<T> node, T data) {
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
     * Deletes a value from the BST while maintaining BST properties.
     * Handles all three deletion cases: leaf, single child, two children.
     * 
     * @param data the data to delete
     * @throws IllegalArgumentException if data is null
     * @throws TreeNodeNotFoundException if data not found
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(log n) for recursion stack
     */
    public void delete(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Delete data cannot be null");
        }
        
        if (!search(data)) {
            throw new TreeNodeNotFoundException("Cannot delete '" + data + "' - not found in BST");
        }
        
        root = deleteRecursive(root, data);
        size--;
        System.out.println("✅ Deleted '" + data + "' from BST. BST size: " + size);
    }
    
    /**
     * Helper method for recursive BST deletion.
     * Handles three cases: leaf node, single child, two children.
     */
    private TreeNode<T> deleteRecursive(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        
        int comparison = data.compareTo(node.data);
        
        if (comparison < 0) {
            node.left = deleteRecursive(node.left, data);
        } else if (comparison > 0) {
            node.right = deleteRecursive(node.right, data);
        } else {
            // Node to delete found
            
            // Case 1: Leaf node (no children)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2: Single child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
            // Case 3: Two children
            // Find inorder successor (smallest value in right subtree)
            TreeNode<T> successor = findMinNode(node.right);
            
            // Replace node's data with successor's data
            node.data = successor.data;
            
            // Delete the successor (which has at most one child)
            node.right = deleteRecursive(node.right, successor.data);
        }
        
        return node;
    }
    
    // ==================== BST-SPECIFIC OPERATIONS ====================
    
    /**
     * Finds and returns the minimum value in the BST.
     * 
     * @return the minimum value
     * @throws TreeEmptyException if BST is empty
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(1)
     */
    public T findMin() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find minimum in empty BST");
        }
        
        TreeNode<T> minNode = findMinNode(root);
        System.out.println("📍 Minimum value in BST: " + minNode.data);
        return minNode.data;
    }
    
    /**
     * Helper method to find the node with minimum value.
     */
    private TreeNode<T> findMinNode(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * Finds and returns the maximum value in the BST.
     * 
     * @return the maximum value
     * @throws TreeEmptyException if BST is empty
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(1)
     */
    public T findMax() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find maximum in empty BST");
        }
        
        TreeNode<T> maxNode = findMaxNode(root);
        System.out.println("📍 Maximum value in BST: " + maxNode.data);
        return maxNode.data;
    }
    
    /**
     * Helper method to find the node with maximum value.
     */
    private TreeNode<T> findMaxNode(TreeNode<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    /**
     * Finds the inorder successor of a given value.
     * Successor is the next larger value in the BST.
     * 
     * @param data the value to find successor for
     * @return the successor value, or null if no successor exists
     * @throws IllegalArgumentException if data is null
     * @throws TreeNodeNotFoundException if data not found
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(1)
     */
    public T findSuccessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        TreeNode<T> node = findNodeBST(root, data);
        if (node == null) {
            throw new TreeNodeNotFoundException(data);
        }
        
        TreeNode<T> successor = findSuccessorNode(node);
        if (successor != null) {
            System.out.println("📍 Successor of '" + data + "': " + successor.data);
            return successor.data;
        } else {
            System.out.println("📍 No successor found for '" + data + "' (it's the maximum)");
            return null;
        }
    }
    
    /**
     * Helper method to find successor node.
     */
    private TreeNode<T> findSuccessorNode(TreeNode<T> node) {
        // Case 1: Node has right subtree - successor is leftmost node in right subtree
        if (node.right != null) {
            return findMinNode(node.right);
        }
        
        // Case 2: No right subtree - go up until we find a node that is left child of its parent
        TreeNode<T> parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        
        return parent;
    }
    
    /**
     * Finds the inorder predecessor of a given value.
     * Predecessor is the next smaller value in the BST.
     * 
     * @param data the value to find predecessor for
     * @return the predecessor value, or null if no predecessor exists
     * @throws IllegalArgumentException if data is null
     * @throws TreeNodeNotFoundException if data not found
     * 
     * Time Complexity: O(log n) average, O(n) worst case
     * Space Complexity: O(1)
     */
    public T findPredecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        TreeNode<T> node = findNodeBST(root, data);
        if (node == null) {
            throw new TreeNodeNotFoundException(data);
        }
        
        TreeNode<T> predecessor = findPredecessorNode(node);
        if (predecessor != null) {
            System.out.println("📍 Predecessor of '" + data + "': " + predecessor.data);
            return predecessor.data;
        } else {
            System.out.println("📍 No predecessor found for '" + data + "' (it's the minimum)");
            return null;
        }
    }
    
    /**
     * Helper method to find predecessor node.
     */
    private TreeNode<T> findPredecessorNode(TreeNode<T> node) {
        // Case 1: Node has left subtree - predecessor is rightmost node in left subtree
        if (node.left != null) {
            return findMaxNode(node.left);
        }
        
        // Case 2: No left subtree - go up until we find a node that is right child of its parent
        TreeNode<T> parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }
        
        return parent;
    }
    
    /**
     * Helper method to find node in BST using BST properties.
     */
    private TreeNode<T> findNodeBST(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        
        int comparison = data.compareTo(node.data);
        if (comparison == 0) {
            return node;
        } else if (comparison < 0) {
            return findNodeBST(node.left, data);
        } else {
            return findNodeBST(node.right, data);
        }
    }
    
    // ==================== TRAVERSAL METHODS ====================
    
    /**
     * Performs inorder traversal which returns elements in sorted order for BST.
     * This is the key property that makes BST useful for maintaining sorted data.
     * 
     * @return list of elements in sorted order
     * @throws TreeEmptyException if BST is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for result list + O(h) for recursion
     */
    public List<T> inorderTraversal() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot perform inorder traversal on empty BST");
        }
        
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        
        System.out.println("📋 BST Inorder Traversal (Sorted): " + result);
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
     * Returns elements in a given range [min, max] in sorted order.
     * 
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return list of elements in the range
     * @throws IllegalArgumentException if min > max or either is null
     * 
     * Time Complexity: O(log n + k) where k is number of elements in range
     * Space Complexity: O(k) for result
     */
    public List<T> rangeQuery(T min, T max) {
        if (min == null || max == null) {
            throw new IllegalArgumentException("Range bounds cannot be null");
        }
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }
        
        List<T> result = new ArrayList<>();
        rangeQueryRecursive(root, min, max, result);
        
        System.out.println("📋 Range Query [" + min + ", " + max + "]: " + result);
        return result;
    }
    
    /**
     * Helper method for range query.
     */
    private void rangeQueryRecursive(TreeNode<T> node, T min, T max, List<T> result) {
        if (node == null) {
            return;
        }
        
        // If current node is greater than min, explore left subtree
        if (node.data.compareTo(min) > 0) {
            rangeQueryRecursive(node.left, min, max, result);
        }
        
        // If current node is in range, add it
        if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {
            result.add(node.data);
        }
        
        // If current node is less than max, explore right subtree
        if (node.data.compareTo(max) < 0) {
            rangeQueryRecursive(node.right, min, max, result);
        }
    }
    
    // ==================== TREE ANALYSIS METHODS ====================
    
    /**
     * Validates that the tree maintains BST properties.
     * 
     * @return true if valid BST, false otherwise
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion
     */
    public boolean isValidBST() {
        boolean valid = isValidBSTRecursive(root, null, null);
        if (valid) {
            System.out.println("✅ BST is valid - maintains BST properties");
        } else {
            System.out.println("❌ BST is invalid - violates BST properties");
        }
        return valid;
    }
    
    /**
     * Helper method for BST validation.
     */
    private boolean isValidBSTRecursive(TreeNode<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        
        // Check if current node violates BST property
        if ((min != null && node.data.compareTo(min) <= 0) ||
            (max != null && node.data.compareTo(max) >= 0)) {
            return false;
        }
        
        // Recursively validate left and right subtrees
        return isValidBSTRecursive(node.left, min, node.data) &&
               isValidBSTRecursive(node.right, node.data, max);
    }
    
    /**
     * Calculates and returns the height of the BST.
     * 
     * @return the height of the BST
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion
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
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Returns the number of nodes in the BST.
     * 
     * @return the size of the BST
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the BST is empty.
     * 
     * @return true if BST is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Gets the root data of the BST.
     * 
     * @return the data stored in the root node
     * @throws TreeEmptyException if BST is empty
     */
    public T getRoot() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot get root of empty BST");
        }
        return root.data;
    }
    
    /**
     * Clears the entire BST.
     */
    public void clear() {
        root = null;
        size = 0;
        System.out.println("🗑️ BST cleared. All nodes removed.");
    }
    
    /**
     * Prints the BST structure in a visual format.
     */
    public void printBST() {
        if (isEmpty()) {
            System.out.println("🌳 BST is empty");
            return;
        }
        
        System.out.println("🌳 Binary Search Tree Structure:");
        printBSTRecursive(root, "", true);
        System.out.println("📊 BST Statistics: Size=" + size + ", Height=" + getHeight() + 
                         ", Min=" + findMin() + ", Max=" + findMax());
    }
    
    /**
     * Helper method for recursive BST printing.
     */
    private void printBSTRecursive(TreeNode<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.data);
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printBSTRecursive(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printBSTRecursive(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the BST.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "BinarySearchTree{empty}";
        }
        return "BinarySearchTree{root=" + root.data + ", size=" + size + ", height=" + getHeight() + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating BST operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        
        System.out.println("🌳 Binary Search Tree Interactive Demo");
        System.out.println("=======================================");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Value");
            System.out.println("2.  Search Value");
            System.out.println("3.  Delete Value");
            System.out.println("4.  Find Minimum");
            System.out.println("5.  Find Maximum");
            System.out.println("6.  Find Successor");
            System.out.println("7.  Find Predecessor");
            System.out.println("8.  Inorder Traversal (Sorted)");
            System.out.println("9.  Range Query");
            System.out.println("10. Print BST Structure");
            System.out.println("11. Get BST Height");
            System.out.println("12. Get BST Size");
            System.out.println("13. Validate BST");
            System.out.println("14. Clear BST");
            System.out.println("15. Exit");
            System.out.print("👉 Enter your choice (1-15): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to insert: ");
                        int insertValue = scanner.nextInt();
                        bst.insert(insertValue);
                        break;
                        
                    case 2:
                        System.out.print("Enter value to search: ");
                        int searchValue = scanner.nextInt();
                        bst.search(searchValue);
                        break;
                        
                    case 3:
                        System.out.print("Enter value to delete: ");
                        int deleteValue = scanner.nextInt();
                        bst.delete(deleteValue);
                        break;
                        
                    case 4:
                        bst.findMin();
                        break;
                        
                    case 5:
                        bst.findMax();
                        break;
                        
                    case 6:
                        System.out.print("Enter value to find successor: ");
                        int succValue = scanner.nextInt();
                        bst.findSuccessor(succValue);
                        break;
                        
                    case 7:
                        System.out.print("Enter value to find predecessor: ");
                        int predValue = scanner.nextInt();
                        bst.findPredecessor(predValue);
                        break;
                        
                    case 8:
                        bst.inorderTraversal();
                        break;
                        
                    case 9:
                        System.out.print("Enter minimum value: ");
                        int min = scanner.nextInt();
                        System.out.print("Enter maximum value: ");
                        int max = scanner.nextInt();
                        bst.rangeQuery(min, max);
                        break;
                        
                    case 10:
                        bst.printBST();
                        break;
                        
                    case 11:
                        System.out.println("📏 BST height: " + bst.getHeight());
                        break;
                        
                    case 12:
                        System.out.println("📊 BST size: " + bst.size());
                        break;
                        
                    case 13:
                        bst.isValidBST();
                        break;
                        
                    case 14:
                        bst.clear();
                        break;
                        
                    case 15:
                        System.out.println("👋 Thank you for using BST Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-15.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 