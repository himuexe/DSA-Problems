package exceptions;

/**
 * Exception thrown when attempting to perform operations on an empty heap
 * that require at least one element (such as extracting the root).
 * 
 * This exception extends HeapException and provides specific context
 * for heap underflow situations.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class HeapUnderflowException extends HeapException {
    
    /**
     * Creates a new HeapUnderflowException with a default message.
     */
    public HeapUnderflowException() {
        super("Heap is empty - cannot perform operation");
    }
    
    /**
     * Creates a new HeapUnderflowException with the specified error message.
     * 
     * @param message the detail message explaining the exception
     */
    public HeapUnderflowException(String message) {
        super(message);
    }
    
    /**
     * Creates a new HeapUnderflowException with the specified error message and cause.
     * 
     * @param message the detail message explaining the exception
     * @param cause the cause of this exception
     */
    public HeapUnderflowException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new HeapUnderflowException with the specified cause.
     * 
     * @param cause the cause of this exception
     */
    public HeapUnderflowException(Throwable cause) {
        super(cause);
    }
} 