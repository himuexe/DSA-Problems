package nodes;

/**
 * TreeNode class representing a single node in a binary tree implementation.
 * Each node contains data and references to left and right child nodes.
 * 
 * This is the fundamental building block for all tree-based operations.
 * Used in BinaryTree, BinarySearchTree, AVLTree, RedBlackTree, and other tree implementations.
 * 
 * Generic implementation supports any comparable data type for maximum flexibility.
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class TreeNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the left child node */
    public TreeNode<T> left;
    
    /** Reference to the right child node */
    public TreeNode<T> right;
    
    /** Reference to the parent node (useful for certain operations) */
    public TreeNode<T> parent;
    
    /**
     * Constructor to create a new tree node with given data.
     * All child and parent pointers are initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * 
     * @example
     * // Create a new node with integer data
     * TreeNode<Integer> node = new TreeNode<>(42);
     * 
     * // Create a new node with string data
     * TreeNode<String> stringNode = new TreeNode<>("hello");
     */
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    
    /**
     * Constructor to create a new tree node with data and child references.
     * Parent pointer is initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * @param left reference to the left child node
     * @param right reference to the right child node
     * 
     * @example
     * // Create leaf nodes
     * TreeNode<Integer> leftChild = new TreeNode<>(10);
     * TreeNode<Integer> rightChild = new TreeNode<>(30);
     * 
     * // Create parent node with children
     * TreeNode<Integer> parent = new TreeNode<>(20, leftChild, rightChild);
     */
    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = null;
        
        // Set parent references for children
        if (left != null) {
            left.parent = this;
        }
        if (right != null) {
            right.parent = this;
        }
    }
    
    /**
     * Checks if this node is a leaf (has no children).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has no children, false otherwise
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
    /**
     * Checks if this node has a left child.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has a left child, false otherwise
     */
    public boolean hasLeftChild() {
        return this.left != null;
    }
    
    /**
     * Checks if this node has a right child.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has a right child, false otherwise
     */
    public boolean hasRightChild() {
        return this.right != null;
    }
    
    /**
     * Checks if this node has a parent.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has a parent, false otherwise (root node)
     */
    public boolean hasParent() {
        return this.parent != null;
    }
    
    /**
     * Checks if this node is the root of the tree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node is the root (has no parent), false otherwise
     */
    public boolean isRoot() {
        return this.parent == null;
    }
    
    /**
     * Checks if this node is a left child of its parent.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node is a left child, false otherwise
     */
    public boolean isLeftChild() {
        return this.parent != null && this.parent.left == this;
    }
    
    /**
     * Checks if this node is a right child of its parent.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node is a right child, false otherwise
     */
    public boolean isRightChild() {
        return this.parent != null && this.parent.right == this;
    }
    
    /**
     * Gets the sibling of this node (the other child of the same parent).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the sibling node, or null if no sibling exists
     */
    public TreeNode<T> getSibling() {
        if (this.parent == null) {
            return null; // Root has no sibling
        }
        
        if (this.isLeftChild()) {
            return this.parent.right;
        } else {
            return this.parent.left;
        }
    }
    
    /**
     * Counts the number of children this node has.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the number of children (0, 1, or 2)
     */
    public int getChildrenCount() {
        int count = 0;
        if (this.left != null) count++;
        if (this.right != null) count++;
        return count;
    }
    
    /**
     * Returns a string representation of this node.
     * Useful for debugging and visualization purposes.
     * 
     * @return string representation showing the node's data and structure info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TreeNode{data=").append(data);
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", childrenCount=").append(getChildrenCount());
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a detailed string representation showing the node's position in the tree.
     * 
     * @return detailed string representation including parent and children info
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TreeNode{");
        sb.append("data=").append(data);
        sb.append(", parent=").append(parent != null ? parent.data : "null");
        sb.append(", left=").append(left != null ? left.data : "null");
        sb.append(", right=").append(right != null ? right.data : "null");
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", isRoot=").append(isRoot());
        sb.append("}");
        return sb.toString();
    }
} 