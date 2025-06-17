package nodes;

/**
 * DoublyNode class representing a single node in a doubly linked list.
 * Each node contains data and references to both the next and previous nodes.
 * 
 * This is the fundamental building block for doubly linked list operations,
 * allowing bidirectional traversal of the list.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class DoublyNode {
    
    /** The data stored in this node */
    public int data;
    
    /** Reference to the next node in the linked list */
    public DoublyNode next;
    
    /** Reference to the previous node in the linked list */
    public DoublyNode prev;
    
    /**
     * Constructor to create a new node with given data.
     * Both next and prev pointers are initialized to null.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * 
     * @example
     * // Create a new node with data 42
     * DoublyNode node = new DoublyNode(42);
     */
    public DoublyNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    /**
     * Constructor to create a new node with given data and references.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the integer data to be stored in this node
     * @param prev reference to the previous node
     * @param next reference to the next node
     * 
     * @example
     * // Create a node and link it between two existing nodes
     * DoublyNode prevNode = new DoublyNode(5);
     * DoublyNode nextNode = new DoublyNode(15);
     * DoublyNode currentNode = new DoublyNode(10, prevNode, nextNode);
     */
    public DoublyNode(int data, DoublyNode prev, DoublyNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    
    /**
     * Returns a string representation of this node.
     * Shows data and connection status for debugging.
     * 
     * @return string representation showing the node's data and connections
     */
    @Override
    public String toString() {
        return "DoublyNode{data=" + data + 
               ", hasPrev=" + (prev != null) + 
               ", hasNext=" + (next != null) + "}";
    }
    
    /**
     * Checks if this node has a next node.
     * 
     * @return true if this node has a next node, false otherwise
     */
    public boolean hasNext() {
        return this.next != null;
    }
    
    /**
     * Checks if this node has a previous node.
     * 
     * @return true if this node has a previous node, false otherwise
     */
    public boolean hasPrev() {
        return this.prev != null;
    }
    
    /**
     * Checks if this node is isolated (no connections to other nodes).
     * 
     * @return true if this node has no next or previous connections
     */
    public boolean isIsolated() {
        return this.next == null && this.prev == null;
    }
} 