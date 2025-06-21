package nodes;

/**
 * FibonacciNode class representing a node in a Fibonacci heap.
 * Uses circular doubly-linked list structure for efficient operations.
 * 
 * Each node contains:
 * - Data and degree (number of children)
 * - Parent pointer and one child pointer
 * - Left and right pointers for circular doubly-linked sibling list
 * - Mark bit for cascading cuts during decrease-key operations
 * 
 * Fibonacci Heap Properties:
 * - All nodes at same level form circular doubly-linked list
 * - Mark bit tracks if node has lost a child since becoming child of another node
 * - Used for optimal amortized time complexities in Fibonacci heap operations
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class FibonacciNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the parent node */
    public FibonacciNode<T> parent;
    
    /** Reference to one of the children (any child) */
    public FibonacciNode<T> child;
    
    /** Reference to left sibling in circular doubly-linked list */
    public FibonacciNode<T> left;
    
    /** Reference to right sibling in circular doubly-linked list */
    public FibonacciNode<T> right;
    
    /** Degree of the node (number of children) */
    public int degree;
    
    /** Mark bit for cascading cuts - true if node has lost a child since becoming child of another */
    public boolean marked;
    
    /**
     * Constructor to create a new Fibonacci node with given data.
     * Initializes as a singleton circular list.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     */
    public FibonacciNode(T data) {
        this.data = data;
        this.parent = null;
        this.child = null;
        this.left = this;      // Points to self (circular)
        this.right = this;     // Points to self (circular)
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
     * Checks if this node is alone in its sibling list (points to itself).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if this node has no siblings, false otherwise
     */
    public boolean isAlone() {
        return this.left == this && this.right == this;
    }
    
    /**
     * Adds a sibling to this node's circular doubly-linked list.
     * Inserts the new sibling to the right of this node.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param sibling the sibling node to add
     */
    public void addSibling(FibonacciNode<T> sibling) {
        if (sibling == null) return;
        
        // Insert sibling between this and this.right
        sibling.left = this;
        sibling.right = this.right;
        this.right.left = sibling;
        this.right = sibling;
        
        // Set same parent
        sibling.parent = this.parent;
    }
    
    /**
     * Removes this node from its sibling list.
     * Updates the circular doubly-linked list structure.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void removeFromSiblings() {
        this.left.right = this.right;
        this.right.left = this.left;
        
        // Make this node point to itself
        this.left = this;
        this.right = this;
    }
    
    /**
     * Adds a child to this node.
     * The new child is added to the child list and degree is incremented.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param newChild the child node to add
     */
    public void addChild(FibonacciNode<T> newChild) {
        if (newChild == null) return;
        
        // Remove child from its current position
        newChild.removeFromSiblings();
        newChild.parent = this;
        newChild.marked = false; // Children are never marked initially
        
        if (this.child == null) {
            // First child
            this.child = newChild;
        } else {
            // Add to child's sibling list
            this.child.addSibling(newChild);
        }
        
        this.degree++;
    }
    
    /**
     * Removes a child from this node.
     * Updates the child list and decrements degree.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param childToRemove the child node to remove
     */
    public void removeChild(FibonacciNode<T> childToRemove) {
        if (childToRemove == null || childToRemove.parent != this) return;
        
        if (this.child == childToRemove) {
            // Removing the designated child
            if (childToRemove.isAlone()) {
                this.child = null;
            } else {
                this.child = childToRemove.right;
                childToRemove.removeFromSiblings();
            }
        } else {
            // Remove from sibling list
            childToRemove.removeFromSiblings();
        }
        
        childToRemove.parent = null;
        this.degree--;
    }
    
    /**
     * Concatenates this node's sibling list with another node's sibling list.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param other the other node whose sibling list to concatenate
     */
    public void concatenateWith(FibonacciNode<T> other) {
        if (other == null || other == this) return;
        
        // Save references
        FibonacciNode<T> thisRight = this.right;
        FibonacciNode<T> otherLeft = other.left;
        
        // Connect the lists
        this.right = other;
        other.left = this;
        thisRight.left = otherLeft;
        otherLeft.right = thisRight;
    }
    
    /**
     * Gets all siblings of this node (including this node) as an array.
     * 
     * Time Complexity: O(k) where k is the number of siblings
     * Space Complexity: O(k)
     * 
     * @return array of sibling nodes
     */
    @SuppressWarnings("unchecked")
    public FibonacciNode<T>[] getSiblings() {
        // Count siblings first
        int count = 1;
        FibonacciNode<T> current = this.right;
        while (current != this) {
            count++;
            current = current.right;
        }
        
        // Create array and fill it
        FibonacciNode<T>[] siblings = (FibonacciNode<T>[]) new FibonacciNode[count];
        siblings[0] = this;
        current = this.right;
        int index = 1;
        
        while (current != this) {
            siblings[index++] = current;
            current = current.right;
        }
        
        return siblings;
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
    public FibonacciNode<T>[] getChildren() {
        if (this.child == null) {
            return (FibonacciNode<T>[]) new FibonacciNode[0];
        }
        
        FibonacciNode<T>[] children = (FibonacciNode<T>[]) new FibonacciNode[degree];
        FibonacciNode<T> current = this.child;
        int index = 0;
        
        do {
            children[index++] = current;
            current = current.right;
        } while (current != this.child && index < degree);
        
        return children;
    }
    
    /**
     * Counts the total number of nodes in the subtree rooted at this node.
     * 
     * Time Complexity: O(n) where n is the subtree size
     * Space Complexity: O(d) where d is the maximum depth
     * 
     * @return the number of nodes in the subtree
     */
    public int getSubtreeSize() {
        int size = 1; // Count this node
        
        if (this.child != null) {
            FibonacciNode<T> current = this.child;
            do {
                size += current.getSubtreeSize();
                current = current.right;
            } while (current != this.child);
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
        if (this.child == null) return true;
        
        FibonacciNode<T> current = this.child;
        do {
            if (this.data.compareTo(current.data) > 0) {
                return false;
            }
            current = current.right;
        } while (current != this.child);
        
        return true;
    }
    
    /**
     * Checks if this node represents a valid Fibonacci heap subtree.
     * 
     * Time Complexity: O(n) where n is the subtree size
     * Space Complexity: O(d) where d is the maximum depth
     * 
     * @return true if this is a valid Fibonacci heap subtree, false otherwise
     */
    public boolean isValidFibonacciSubtree() {
        // Check heap property
        if (!satisfiesHeapProperty()) {
            return false;
        }
        
        // Check degree matches number of children
        int childCount = 0;
        if (this.child != null) {
            FibonacciNode<T> current = this.child;
            do {
                childCount++;
                
                // Recursively check subtree
                if (!current.isValidFibonacciSubtree()) {
                    return false;
                }
                
                current = current.right;
            } while (current != this.child);
        }
        
        return childCount == this.degree;
    }
    
    /**
     * Finds the minimum node in the subtree rooted at this node.
     * 
     * Time Complexity: O(n) where n is the subtree size
     * Space Complexity: O(d) where d is the maximum depth
     * 
     * @return the node with minimum data in the subtree
     */
    public FibonacciNode<T> findMin() {
        FibonacciNode<T> min = this;
        
        if (this.child != null) {
            FibonacciNode<T> current = this.child;
            do {
                FibonacciNode<T> childMin = current.findMin();
                if (childMin.data.compareTo(min.data) < 0) {
                    min = childMin;
                }
                current = current.right;
            } while (current != this.child);
        }
        
        return min;
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
    public FibonacciNode<T> linkWith(FibonacciNode<T> other) {
        if (other == null) return this;
        if (this.degree != other.degree) {
            throw new IllegalArgumentException("Cannot link nodes with different degrees");
        }
        
        // Make the node with smaller key the parent
        if (this.data.compareTo(other.data) <= 0) {
            other.removeFromSiblings();
            this.addChild(other);
            return this;
        } else {
            this.removeFromSiblings();
            other.addChild(this);
            return other;
        }
    }
    
    /**
     * Returns a string representation of this node.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return string representation showing data, degree, and mark status
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
        sb.append("left:").append(left != this ? left.data : "self").append(", ");
        sb.append("right:").append(right != this ? right.data : "self");
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
    public int compareTo(FibonacciNode<T> other) {
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
     * @return a new FibonacciNode with the same data
     */
    public FibonacciNode<T> copy() {
        FibonacciNode<T> copy = new FibonacciNode<>(this.data);
        copy.degree = this.degree;
        copy.marked = this.marked;
        return copy;
    }
} 