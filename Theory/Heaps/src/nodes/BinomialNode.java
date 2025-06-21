package nodes;

/**
 * BinomialNode class representing a node in a binomial heap.
 * Each node contains data, degree, and pointers to parent, child, and sibling nodes.
 * 
 * Binomial Tree Properties:
 * - B_k has 2^k nodes and height k
 * - Root has degree k with children being roots of B_0, B_1, ..., B_{k-1}
 * - Used in binomial heap operations for efficient merging
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class BinomialNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the parent node */
    public BinomialNode<T> parent;
    
    /** Reference to the leftmost child node */
    public BinomialNode<T> child;
    
    /** Reference to the right sibling node */
    public BinomialNode<T> sibling;
    
    /** Degree of the node (number of children) */
    public int degree;
    
    /** Mark for decrease-key and delete operations */
    public boolean marked;
    
    /**
     * Constructor to create a new binomial node with given data.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     */
    public BinomialNode(T data) {
        this.data = data;
        this.parent = null;
        this.child = null;
        this.sibling = null;
        this.degree = 0;
        this.marked = false;
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
        return this.child == null;
    }
    
    /**
     * Checks if this node has a parent.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has a parent, false otherwise
     */
    public boolean hasParent() {
        return this.parent != null;
    }
    
    /**
     * Checks if this node is a root (has no parent).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node is a root, false otherwise
     */
    public boolean isRoot() {
        return this.parent == null;
    }
    
    /**
     * Checks if this node has siblings.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has a sibling, false otherwise
     */
    public boolean hasSibling() {
        return this.sibling != null;
    }
    
    /**
     * Checks if this node has children.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has children, false otherwise
     */
    public boolean hasChildren() {
        return this.child != null;
    }
    
    /**
     * Adds a child to this node.
     * The new child becomes the leftmost child.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param newChild the child node to add
     */
    public void addChild(BinomialNode<T> newChild) {
        if (newChild == null) return;
        
        newChild.parent = this;
        newChild.sibling = this.child;
        this.child = newChild;
        this.degree++;
    }
    
    /**
     * Removes this node from its parent's child list.
     * 
     * Time Complexity: O(k) where k is the number of children of parent
     * Space Complexity: O(1)
     */
    public void removeFromParent() {
        if (this.parent == null) return;
        
        if (this.parent.child == this) {
            // This is the leftmost child
            this.parent.child = this.sibling;
        } else {
            // Find the previous sibling
            BinomialNode<T> prev = this.parent.child;
            while (prev != null && prev.sibling != this) {
                prev = prev.sibling;
            }
            if (prev != null) {
                prev.sibling = this.sibling;
            }
        }
        
        this.parent.degree--;
        this.parent = null;
        this.sibling = null;
    }
    
    /**
     * Links this node with another node of the same degree.
     * The node with smaller key becomes the parent.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param other the other node to link with
     * @return the node that becomes the parent
     */
    public BinomialNode<T> linkWith(BinomialNode<T> other) {
        if (other == null) return this;
        if (this.degree != other.degree) {
            throw new IllegalArgumentException("Cannot link nodes with different degrees");
        }
        
        // Make the node with smaller key the parent
        if (this.data.compareTo(other.data) <= 0) {
            this.addChild(other);
            return this;
        } else {
            other.addChild(this);
            return other;
        }
    }
    
    /**
     * Gets all children of this node as an array.
     * 
     * Time Complexity: O(k) where k is the degree
     * Space Complexity: O(k)
     * 
     * @return array of child nodes
     */
    @SuppressWarnings("unchecked")
    public BinomialNode<T>[] getChildren() {
        BinomialNode<T>[] children = (BinomialNode<T>[]) new BinomialNode[degree];
        BinomialNode<T> current = this.child;
        int index = 0;
        
        while (current != null && index < degree) {
            children[index++] = current;
            current = current.sibling;
        }
        
        return children;
    }
    
    /**
     * Counts the total number of nodes in the subtree rooted at this node.
     * 
     * Time Complexity: O(2^k) where k is the degree
     * Space Complexity: O(k) for recursion stack
     * 
     * @return the number of nodes in the subtree
     */
    public int getSubtreeSize() {
        int size = 1; // Count this node
        BinomialNode<T> current = this.child;
        
        while (current != null) {
            size += current.getSubtreeSize();
            current = current.sibling;
        }
        
        return size;
    }
    
    /**
     * Checks if this node satisfies the heap property with its children.
     * 
     * Time Complexity: O(k) where k is the degree
     * Space Complexity: O(1)
     * 
     * @return true if heap property is satisfied, false otherwise
     */
    public boolean satisfiesHeapProperty() {
        BinomialNode<T> current = this.child;
        
        while (current != null) {
            if (this.data.compareTo(current.data) > 0) {
                return false;
            }
            current = current.sibling;
        }
        
        return true;
    }
    
    /**
     * Checks if this node represents a valid binomial tree.
     * 
     * Time Complexity: O(2^k) where k is the degree
     * Space Complexity: O(k) for recursion stack
     * 
     * @return true if this is a valid binomial tree, false otherwise
     */
    public boolean isValidBinomialTree() {
        // Check degree property
        int childCount = 0;
        BinomialNode<T> current = this.child;
        int expectedDegree = this.degree - 1;
        
        while (current != null) {
            childCount++;
            
            // Each child should have the expected degree
            if (current.degree != expectedDegree) {
                return false;
            }
            
            // Check heap property
            if (this.data.compareTo(current.data) > 0) {
                return false;
            }
            
            // Recursively check subtree
            if (!current.isValidBinomialTree()) {
                return false;
            }
            
            current = current.sibling;
            expectedDegree--;
        }
        
        return childCount == this.degree;
    }
    
    /**
     * Finds the minimum node in the subtree rooted at this node.
     * 
     * Time Complexity: O(2^k) where k is the degree
     * Space Complexity: O(k) for recursion stack
     * 
     * @return the node with minimum data in the subtree
     */
    public BinomialNode<T> findMin() {
        BinomialNode<T> min = this;
        BinomialNode<T> current = this.child;
        
        while (current != null) {
            BinomialNode<T> childMin = current.findMin();
            if (childMin.data.compareTo(min.data) < 0) {
                min = childMin;
            }
            current = current.sibling;
        }
        
        return min;
    }
    
    /**
     * Returns a string representation of this node.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return string representation showing data and degree
     */
    @Override
    public String toString() {
        return data.toString() + "(d:" + degree + (marked ? ",M" : "") + ")";
    }
    
    /**
     * Returns a detailed string representation of this node.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return detailed string representation
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.toString()).append("[");
        sb.append("degree:").append(degree).append(", ");
        sb.append("marked:").append(marked).append(", ");
        sb.append("parent:").append(parent != null ? parent.data : "null").append(", ");
        sb.append("child:").append(child != null ? child.data : "null").append(", ");
        sb.append("sibling:").append(sibling != null ? sibling.data : "null");
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Compares this node with another node based on data values.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param other the other node to compare with
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    public int compareTo(BinomialNode<T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with null node");
        }
        return this.data.compareTo(other.data);
    }
    
    /**
     * Creates a copy of this node (shallow copy - only copies data and properties).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return a new BinomialNode with the same data
     */
    public BinomialNode<T> copy() {
        BinomialNode<T> copy = new BinomialNode<>(this.data);
        copy.degree = this.degree;
        copy.marked = this.marked;
        return copy;
    }
} 