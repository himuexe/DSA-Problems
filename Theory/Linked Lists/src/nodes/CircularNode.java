package nodes;

/**
 * CircularNode class representing a single node in a circular linked list.
 * Each node contains data and a reference to the next node in the sequence.
 * In a circular linked list, the last node points back to the first node.
 * 
 * This is the fundamental building block for circular linked list operations,
 * enabling continuous traversal without null termination.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class CircularNode {
    
    /** The data stored in this node */
    public int data;
    
    /** Reference to the next node in the circular linked list */
    public CircularNode next;
    
    /**
     * Constructor to create a new node with given data.
     * The next pointer is initialized to null, but will be set to maintain
     * circular property when added to a list.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * 
     * @example
     * // Create a new node with data 42
     * CircularNode node = new CircularNode(42);
     */
    public CircularNode(int data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Constructor to create a new node with given data and next reference.
     * Useful for creating nodes that will be immediately linked.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * @param next reference to the next node
     * 
     * @example
     * // Create a node and link it to an existing node
     * CircularNode nextNode = new CircularNode(10);
     * CircularNode currentNode = new CircularNode(5, nextNode);
     */
    public CircularNode(int data, CircularNode next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Creates a self-referencing node (single node circular list).
     * This is useful for initializing a circular list with one element.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * @return a new CircularNode that points to itself
     * 
     * @example
     * // Create a single-node circular list
     * CircularNode head = CircularNode.createSelfReferencing(42);
     */
    public static CircularNode createSelfReferencing(int data) {
        CircularNode node = new CircularNode(data);
        node.next = node;
        return node;
    }
    
    /**
     * Returns a string representation of this node.
     * Useful for debugging and visualization purposes.
     * 
     * @return string representation showing the node's data
     */
    @Override
    public String toString() {
        return "CircularNode{data=" + data + "}";
    }
    
    /**
     * Checks if this node has a next node.
     * In a proper circular list, this should always be true.
     * 
     * @return true if this node has a next node, false otherwise
     */
    public boolean hasNext() {
        return this.next != null;
    }
    
    /**
     * Checks if this node points to itself (single node circular list).
     * 
     * @return true if this node points to itself, false otherwise
     */
    public boolean isSelfReferencing() {
        return this.next == this;
    }
    
    /**
     * Checks if this node is part of a proper circular structure.
     * A node is properly circular if it has a next node (not null).
     * 
     * @return true if the node is part of a circular structure
     */
    public boolean isPartOfCircularStructure() {
        return this.next != null;
    }
} 