package nodes;

/**
 * RedBlackNode class representing a single node in a Red-Black tree.
 * Extends the concept of a binary search tree node with color property for balancing.
 * 
 * Red-Black trees maintain balance through five key properties:
 * 1. Every node is either red or black
 * 2. The root is always black
 * 3. All leaves (NIL nodes) are black
 * 4. Red nodes cannot have red children (no two red nodes are adjacent)
 * 5. Every path from root to leaves contains the same number of black nodes
 * 
 * These properties ensure the tree remains approximately balanced, guaranteeing
 * O(log n) operations in all cases with less strict balancing than AVL trees.
 * 
 * Color Significance:
 * - BLACK: Contributes to black-height, provides structural stability
 * - RED: Does not contribute to black-height, allows flexibility in structure
 * - NIL nodes: Conceptual black leaf nodes (typically not stored explicitly)
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class RedBlackNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the left child node */
    public RedBlackNode<T> left;
    
    /** Reference to the right child node */
    public RedBlackNode<T> right;
    
    /** Reference to the parent node */
    public RedBlackNode<T> parent;
    
    /** Color of this node (RED or BLACK) */
    public NodeColor color;
    
    /**
     * Enumeration for node colors in Red-Black tree.
     * Only two colors are allowed to maintain tree properties.
     */
    public enum NodeColor {
        RED("🔴"),
        BLACK("⚫");
        
        private final String symbol;
        
        NodeColor(String symbol) {
            this.symbol = symbol;
        }
        
        public String getSymbol() {
            return symbol;
        }
        
        @Override
        public String toString() {
            return symbol;
        }
    }
    
    /**
     * Constructor to create a new Red-Black node with given data.
     * New nodes are initially colored RED (following insertion strategy).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * 
     * @example
     * // Create a new Red-Black node with integer data
     * RedBlackNode<Integer> node = new RedBlackNode<>(42);
     * // Node starts as RED and will be recolored as needed
     */
    public RedBlackNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = NodeColor.RED; // New nodes start as RED
    }
    
    /**
     * Constructor to create a new Red-Black node with data and specified color.
     * Useful for creating root nodes (which must be BLACK) or testing scenarios.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * @param color the initial color of the node
     */
    public RedBlackNode(T data, NodeColor color) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = color;
    }
    
    /**
     * Constructor to create a new Red-Black node with data, children, and color.
     * Parent references for children are set automatically.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * @param left reference to the left child node
     * @param right reference to the right child node
     * @param color the color of this node
     */
    public RedBlackNode(T data, RedBlackNode<T> left, RedBlackNode<T> right, NodeColor color) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = null;
        this.color = color;
        
        // Set parent references for children
        if (left != null) {
            left.parent = this;
        }
        if (right != null) {
            right.parent = this;
        }
    }
    
    // ==================== COLOR OPERATIONS ====================
    
    /**
     * Checks if this node is colored RED.
     * 
     * @return true if node is red, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isRed() {
        return color == NodeColor.RED;
    }
    
    /**
     * Checks if this node is colored BLACK.
     * 
     * @return true if node is black, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isBlack() {
        return color == NodeColor.BLACK;
    }
    
    /**
     * Colors this node RED.
     * Used during Red-Black tree rebalancing operations.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void colorRed() {
        this.color = NodeColor.RED;
    }
    
    /**
     * Colors this node BLACK.
     * Used during Red-Black tree rebalancing operations.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void colorBlack() {
        this.color = NodeColor.BLACK;
    }
    
    /**
     * Flips the color of this node.
     * RED becomes BLACK, BLACK becomes RED.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void flipColor() {
        this.color = (color == NodeColor.RED) ? NodeColor.BLACK : NodeColor.RED;
    }
    
    /**
     * Gets the color of this node as a string with emoji.
     * 
     * @return string representation of the color with emoji
     */
    public String getColorString() {
        return color.toString();
    }
    
    // ==================== RELATIONSHIP CHECKS ====================
    
    /**
     * Checks if this node is a leaf (has no children).
     * In Red-Black trees, actual leaves are NIL nodes, but this checks
     * for nodes with no non-NIL children.
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
    public RedBlackNode<T> getSibling() {
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
     * Gets the uncle of this node (sibling of parent).
     * Used in Red-Black tree insertion and deletion operations.
     * 
     * @return the uncle node, or null if no uncle exists
     */
    public RedBlackNode<T> getUncle() {
        if (parent == null || parent.parent == null) {
            return null; // No grandparent means no uncle
        }
        
        return parent.getSibling();
    }
    
    /**
     * Gets the grandparent of this node.
     * Used in Red-Black tree rotation operations.
     * 
     * @return the grandparent node, or null if no grandparent exists
     */
    public RedBlackNode<T> getGrandparent() {
        if (parent == null) {
            return null;
        }
        return parent.parent;
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
    
    // ==================== RED-BLACK TREE SPECIFIC CHECKS ====================
    
    /**
     * Checks if this node violates the Red-Black tree property.
     * A RED node cannot have RED children (property 4).
     * 
     * @return true if this node violates RB properties, false otherwise
     */
    public boolean violatesRedBlackProperty() {
        if (isBlack()) {
            return false; // BLACK nodes never violate properties
        }
        
        // RED node - check if it has RED children
        boolean hasRedLeftChild = (left != null && left.isRed());
        boolean hasRedRightChild = (right != null && right.isRed());
        
        return hasRedLeftChild || hasRedRightChild;
    }
    
    /**
     * Checks if this node and its parent both are RED (double red violation).
     * This is the primary violation that triggers rebalancing in RB trees.
     * 
     * @return true if both this node and parent are RED, false otherwise
     */
    public boolean hasDoubleRedViolation() {
        return isRed() && parent != null && parent.isRed();
    }
    
    /**
     * Calculates the black height from this node to any leaf.
     * Black height is the number of black nodes on any path from this node
     * to a leaf (not counting this node if it's black).
     * 
     * @return the black height, or -1 if tree is invalid
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion
     */
    public int getBlackHeight() {
        return calculateBlackHeight(this);
    }
    
    /**
     * Helper method to recursively calculate black height.
     */
    private int calculateBlackHeight(RedBlackNode<T> node) {
        if (node == null) {
            return 1; // NIL nodes are black and contribute 1 to black height
        }
        
        int leftBlackHeight = calculateBlackHeight(node.left);
        int rightBlackHeight = calculateBlackHeight(node.right);
        
        // If black heights don't match, tree is invalid
        if (leftBlackHeight != rightBlackHeight || leftBlackHeight == -1) {
            return -1;
        }
        
        // Add 1 if current node is black
        return leftBlackHeight + (node.isBlack() ? 1 : 0);
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Returns a string representation of this Red-Black node.
     * Includes data and color for debugging and visualization.
     * 
     * @return string representation with Red-Black specific information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RBNode{data=").append(data);
        sb.append(", color=").append(color.getSymbol());
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", children=").append(getChildrenCount());
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a detailed string representation showing the node's complete state.
     * 
     * @return detailed string representation including all relationships and RB properties
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RedBlackNode{");
        sb.append("data=").append(data);
        sb.append(", color=").append(color.getSymbol());
        sb.append(", parent=").append(parent != null ? parent.data : "null");
        sb.append(", left=").append(left != null ? left.data : "null");
        sb.append(", right=").append(right != null ? right.data : "null");
        sb.append(", isRoot=").append(isRoot());
        sb.append(", isLeaf=").append(isLeaf());
        sb.append(", blackHeight=").append(getBlackHeight());
        if (hasDoubleRedViolation()) {
            sb.append(", VIOLATION=DoubleRed");
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a compact string for tree visualization.
     * Shows data with color indicator for easy reading.
     * 
     * @return compact string: "data color_emoji"
     */
    public String toCompactString() {
        return data + " " + color.getSymbol();
    }
    
    /**
     * Returns a string suitable for tree structure printing.
     * Includes data and color with clear formatting.
     * 
     * @return formatted string for tree display
     */
    public String toTreeString() {
        return String.format("%s%s", data, color.getSymbol());
    }
} 