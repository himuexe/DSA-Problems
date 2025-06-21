package nodes;

/**
 * HeapNode class representing a single node in a heap implementation.
 * Each node contains data and references to parent and child nodes.
 * 
 * This is the fundamental building block for heap-based operations.
 * Used in BinaryHeap, PriorityQueueHeap, and other heap implementations.
 * 
 * Generic implementation supports any comparable data type for maximum flexibility.
 * 
 * @param <T> the type of data stored in this node, must be Comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class HeapNode<T extends Comparable<T>> {
    
    /** The data stored in this node */
    public T data;
    
    /** Reference to the parent node */
    public HeapNode<T> parent;
    
    /** Reference to the left child node */
    public HeapNode<T> left;
    
    /** Reference to the right child node */
    public HeapNode<T> right;
    
    /** Index position in array representation (useful for heap operations) */
    public int index;
    
    /**
     * Constructor to create a new heap node with given data.
     * All child and parent pointers are initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * 
     * @example
     * // Create a new node with integer data
     * HeapNode<Integer> node = new HeapNode<>(42);
     * 
     * // Create a new node with string data
     * HeapNode<String> stringNode = new HeapNode<>("hello");
     */
    public HeapNode(T data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.index = -1; // Unassigned index
    }
    
    /**
     * Constructor to create a new heap node with data and index.
     * All child and parent pointers are initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the data to be stored in this node
     * @param index the index position in array representation
     * 
     * @example
     * // Create a new node with integer data and index
     * HeapNode<Integer> node = new HeapNode<>(42, 0);
     */
    public HeapNode(T data, int index) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.index = index;
    }
    
    /**
     * Constructor to create a new heap node with data and child references.
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
     * HeapNode<Integer> leftChild = new HeapNode<>(10);
     * HeapNode<Integer> rightChild = new HeapNode<>(30);
     * 
     * // Create parent node with children
     * HeapNode<Integer> parent = new HeapNode<>(5, leftChild, rightChild);
     */
    public HeapNode(T data, HeapNode<T> left, HeapNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = null;
        this.index = -1;
        
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
     * Checks if this node is the root of the heap.
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
    public HeapNode<T> getSibling() {
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
     * Gets the index of the left child in array representation.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the left child index, or -1 if no index assigned
     */
    public int getLeftChildIndex() {
        return (this.index >= 0) ? 2 * this.index + 1 : -1;
    }
    
    /**
     * Gets the index of the right child in array representation.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the right child index, or -1 if no index assigned
     */
    public int getRightChildIndex() {
        return (this.index >= 0) ? 2 * this.index + 2 : -1;
    }
    
    /**
     * Gets the index of the parent in array representation.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the parent index, or -1 if root or no index assigned
     */
    public int getParentIndex() {
        return (this.index > 0) ? (this.index - 1) / 2 : -1;
    }
    
    /**
     * Checks if this node satisfies min-heap property with its children.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if min-heap property is satisfied, false otherwise
     */
    public boolean satisfiesMinHeapProperty() {
        boolean leftOk = (this.left == null) || (this.data.compareTo(this.left.data) <= 0);
        boolean rightOk = (this.right == null) || (this.data.compareTo(this.right.data) <= 0);
        return leftOk && rightOk;
    }
    
    /**
     * Checks if this node satisfies max-heap property with its children.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if max-heap property is satisfied, false otherwise
     */
    public boolean satisfiesMaxHeapProperty() {
        boolean leftOk = (this.left == null) || (this.data.compareTo(this.left.data) >= 0);
        boolean rightOk = (this.right == null) || (this.data.compareTo(this.right.data) >= 0);
        return leftOk && rightOk;
    }
    
    /**
     * Returns a string representation of this node.
     * Shows the data value.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return string representation of the node
     */
    @Override
    public String toString() {
        return data.toString();
    }
    
    /**
     * Returns a detailed string representation of this node.
     * Shows data, index, and relationship information.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return detailed string representation of the node
     * 
     * @example
     * Output: "42[idx:0, parent:null, left:10, right:30]"
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.toString()).append("[");
        sb.append("idx:").append(index).append(", ");
        sb.append("parent:").append(parent != null ? parent.data : "null").append(", ");
        sb.append("left:").append(left != null ? left.data : "null").append(", ");
        sb.append("right:").append(right != null ? right.data : "null");
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
    public int compareTo(HeapNode<T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with null node");
        }
        return this.data.compareTo(other.data);
    }
    
    /**
     * Creates a copy of this node (shallow copy - only copies data, not references).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return a new HeapNode with the same data
     */
    public HeapNode<T> copy() {
        return new HeapNode<>(this.data, this.index);
    }
} 