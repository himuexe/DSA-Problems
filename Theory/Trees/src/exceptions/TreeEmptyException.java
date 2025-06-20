package exceptions;

/**
 * Exception thrown when attempting to perform operations on an empty tree.
 * 
 * This exception is typically thrown when tree operations require at least one node
 * but the tree is completely empty (root is null).
 * 
 * Usage scenarios:
 * - Attempting to search in an empty tree
 * - Trying to delete from an empty tree
 * - Accessing root data when tree is empty
 * - Performing traversals on empty trees
 * - Finding minimum/maximum in empty tree
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class TreeEmptyException extends RuntimeException {
    
    /**
     * Constructs a new TreeEmptyException with a default message.
     */
    public TreeEmptyException() {
        super("Tree is empty: Cannot perform operation on empty tree");
    }
    
    /**
     * Constructs a new TreeEmptyException with the specified detail message.
     * 
     * @param message the detail message explaining the specific operation that failed
     */
    public TreeEmptyException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new TreeEmptyException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the specific operation that failed
     * @param cause the cause of the exception
     */
    public TreeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
} 