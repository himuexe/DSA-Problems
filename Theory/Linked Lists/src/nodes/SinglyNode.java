package nodes;

/**
 * SinglyNode class representing a single node in a singly linked list.
 * Each node contains data and a reference to the next node in the sequence.
 * 
 * This is the fundamental building block for singly linked list operations.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class SinglyNode {
    
    /** The data stored in this node */
    public int data;
    
    /** Reference to the next node in the linked list */
    public SinglyNode next;
    
    /**
     * Constructor to create a new node with given data.
     * The next pointer is initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * 
     * @example
     * // Create a new node with data 42
     * SinglyNode node = new SinglyNode(42);
     */
    public SinglyNode(int data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Constructor to create a new node with given data and next reference.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * @param next reference to the next node
     * 
     * @example
     * // Create a node and link it to an existing node
     * SinglyNode nextNode = new SinglyNode(10);
     * SinglyNode currentNode = new SinglyNode(5, nextNode);
     */
    public SinglyNode(int data, SinglyNode next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Returns a string representation of this node.
     * Useful for debugging and visualization purposes.
     * 
     * @return string representation showing the node's data
     */
    @Override
    public String toString() {
        return "SinglyNode{data=" + data + "}";
    }
    
    /**
     * Checks if this node has a next node.
     * 
     * @return true if this node has a next node, false otherwise
     */
    public boolean hasNext() {
        return this.next != null;
    }
} 