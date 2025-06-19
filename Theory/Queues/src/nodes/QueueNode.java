package nodes;

/**
 * QueueNode class representing a single node in a queue implementation.
 * Each node contains data and a reference to the next node in the sequence.
 * 
 * This is the fundamental building block for linked-based queue operations.
 * Used in LinkedQueue and other queue implementations that require node-based storage.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class QueueNode {
    
    /** The data stored in this node */
    public int data;
    
    /** Reference to the next node in the queue */
    public QueueNode next;
    
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
     * QueueNode node = new QueueNode(42);
     */
    public QueueNode(int data) {
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
     * QueueNode nextNode = new QueueNode(10);
     * QueueNode currentNode = new QueueNode(5, nextNode);
     */
    public QueueNode(int data, QueueNode next) {
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
        return "QueueNode{data=" + data + "}";
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