package exceptions;

/**
 * Exception thrown when attempting to access or manipulate a tree node that does not exist.
 * 
 * This exception is typically thrown when search, delete, or update operations
 * cannot find the specified node or data value in the tree.
 * 
 * Usage scenarios:
 * - Searching for a value that doesn't exist in the tree
 * - Attempting to delete a non-existent node
 * - Trying to update data that isn't in the tree
 * - Accessing child nodes that don't exist
 * - Finding predecessor/successor of non-existent nodes
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class TreeNodeNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new TreeNodeNotFoundException with a default message.
     */
    public TreeNodeNotFoundException() {
        super("Tree node not found: The specified node or data does not exist in the tree");
    }
    
    /**
     * Constructs a new TreeNodeNotFoundException with the specified detail message.
     * 
     * @param message the detail message explaining which node was not found
     */
    public TreeNodeNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new TreeNodeNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message explaining which node was not found
     * @param cause the cause of the exception
     */
    public TreeNodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new TreeNodeNotFoundException for a specific data value.
     * 
     * @param data the data value that was not found in the tree
     */
    public TreeNodeNotFoundException(Object data) {
        super("Tree node not found: No node contains data '" + data + "' in the tree");
    }
} 