package nodes;

/**
 * AVLNode class representing a single node in an AVL (Adelson-Velsky and Landis) tree.
 * Extends TreeNode with additional balance factor tracking for self-balancing operations.
 * 
 * AVL trees maintain the balance property: for every node, the heights of its left and 
 * right subtrees differ by at most 1. This ensures O(log n) operations in all cases.
 * 
 * Balance Factor = height(right subtree) - height(left subtree)
 * Valid balance factors: -1, 0, +1
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class AVLNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the left child node */
    public AVLNode<T> left;
    
    /** Reference to the right child node */
    public AVLNode<T> right;
    
    /** Reference to the parent node */
    public AVLNode<T> parent;
    
    /** Height of this node (longest path to any leaf) */
    public int height;
    
    /** Balance factor: height(right) - height(left) */
    public int balanceFactor;
    
    /**
     * Constructor to create a new AVL node with given data.
     * Initializes height to 0 and balance factor to 0 (leaf node).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * 
     * @example
     * // Create a new AVL node with integer data
     * AVLNode<Integer> node = new AVLNode<>(42);
     */
    public AVLNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
        this.balanceFactor = 0;
    }
    
    /**
     * Constructor to create a new AVL node with data and child references.
     * Parent pointer is initialized to null, height and balance factor calculated.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * @param left reference to the left child node
     * @param right reference to the right child node
     */
    public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
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
        
        // Calculate height and balance factor
        updateHeightAndBalance();
    }
    
    /**
     * Updates the height and balance factor of this node based on its children.
     * Should be called after any structural changes to the subtree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void updateHeightAndBalance() {
        int leftHeight = (left != null) ? left.height : -1;
        int rightHeight = (right != null) ? right.height : -1;
        
        this.height = Math.max(leftHeight, rightHeight) + 1;
        this.balanceFactor = rightHeight - leftHeight;
    }
    
    /**
     * Checks if this node is balanced according to AVL property.
     * A node is balanced if its balance factor is -1, 0, or +1.
     * 
     * @return true if balanced, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isBalanced() {
        return Math.abs(balanceFactor) <= 1;
    }
    
    /**
     * Checks if this node is left-heavy (left subtree taller than right).
     * 
     * @return true if left-heavy (balance factor = -1), false otherwise
     */
    public boolean isLeftHeavy() {
        return balanceFactor == -1;
    }
    
    /**
     * Checks if this node is right-heavy (right subtree taller than left).
     * 
     * @return true if right-heavy (balance factor = +1), false otherwise
     */
    public boolean isRightHeavy() {
        return balanceFactor == 1;
    }
    
    /**
     * Checks if this node is perfectly balanced (equal height subtrees).
     * 
     * @return true if perfectly balanced (balance factor = 0), false otherwise
     */
    public boolean isPerfectlyBalanced() {
        return balanceFactor == 0;
    }
    
    /**
     * Checks if this node violates AVL property (needs rebalancing).
     * 
     * @return true if imbalanced (|balance factor| > 1), false otherwise
     */
    public boolean needsRebalancing() {
        return Math.abs(balanceFactor) > 1;
    }
    
    /**
     * Gets the imbalance type for rotation decisions.
     * 
     * @return string describing imbalance: "LEFT_LEFT", "LEFT_RIGHT", "RIGHT_RIGHT", "RIGHT_LEFT", or "BALANCED"
     */
    public String getImbalanceType() {
        if (balanceFactor < -1) {
            // Left-heavy imbalance
            if (left != null && left.balanceFactor <= 0) {
                return "LEFT_LEFT";
            } else {
                return "LEFT_RIGHT";
            }
        } else if (balanceFactor > 1) {
            // Right-heavy imbalance
            if (right != null && right.balanceFactor >= 0) {
                return "RIGHT_RIGHT";
            } else {
                return "RIGHT_LEFT";
            }
        } else {
            return "BALANCED";
        }
    }
    
    /**
     * Checks if this node is a leaf (has no children).
     * 
     * @return true if this node has no children, false otherwise
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    /**
     * Checks if this node has a left child.
     * 
     * @return true if this node has a left child, false otherwise
     */
    public boolean hasLeftChild() {
        return left != null;
    }
    
    /**
     * Checks if this node has a right child.
     * 
     * @return true if this node has a right child, false otherwise
     */
    public boolean hasRightChild() {
        return right != null;
    }
    
    /**
     * Checks if this node has a parent.
     * 
     * @return true if this node has a parent, false otherwise (root node)
     */
    public boolean hasParent() {
        return parent != null;
    }
    
    /**
     * Checks if this node is the root of the tree.
     * 
     * @return true if this node is the root (has no parent), false otherwise
     */
    public boolean isRoot() {
        return parent == null;
    }
    
    /**
     * Checks if this node is a left child of its parent.
     * 
     * @return true if this node is a left child, false otherwise
     */
    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }
    
    /**
     * Checks if this node is a right child of its parent.
     * 
     * @return true if this node is a right child, false otherwise
     */
    public boolean isRightChild() {
        return parent != null && parent.right == this;
    }
    
    /**
     * Gets the sibling of this node (the other child of the same parent).
     * 
     * @return the sibling node, or null if no sibling exists
     */
    public AVLNode<T> getSibling() {
        if (parent == null) {
            return null; // Root has no sibling
        }
        
        if (isLeftChild()) {
            return parent.right;
        } else {
            return parent.left;
        }
    }
    
    /**
     * Counts the number of children this node has.
     * 
     * @return the number of children (0, 1, or 2)
     */
    public int getChildrenCount() {
        int count = 0;
        if (left != null) count++;
        if (right != null) count++;
        return count;
    }
    
    /**
     * Returns a string representation of this AVL node.
     * Includes data, height, and balance factor for debugging.
     * 
     * @return string representation with AVL-specific information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AVLNode{data=").append(data);
        sb.append(", height=").append(height);
        sb.append(", balance=").append(balanceFactor);
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", balanced=").append(isBalanced());
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a detailed string representation showing the node's complete state.
     * 
     * @return detailed string representation including all relationships and AVL properties
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AVLNode{");
        sb.append("data=").append(data);
        sb.append(", height=").append(height);
        sb.append(", balance=").append(balanceFactor);
        sb.append(", parent=").append(parent != null ? parent.data : "null");
        sb.append(", left=").append(left != null ? left.data : "null");
        sb.append(", right=").append(right != null ? right.data : "null");
        sb.append(", isRoot=").append(isRoot());
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", balanced=").append(isBalanced());
        if (needsRebalancing()) {
            sb.append(", imbalanceType=").append(getImbalanceType());
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a compact string for tree visualization.
     * Shows data with balance factor for easy reading.
     * 
     * @return compact string: "data(balance)"
     */
    public String toCompactString() {
        return data + "(" + (balanceFactor >= 0 ? "+" : "") + balanceFactor + ")";
    }
} 