package exceptions;

/**
 * Base exception class for all heap-related exceptions.
 * This serves as the parent class for all specific heap exceptions,
 * providing a consistent exception hierarchy for heap operations.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class HeapException extends Exception {
    
    /**
     * Creates a new HeapException with no message.
     */
    public HeapException() {
        super();
    }
    
    /**
     * Creates a new HeapException with the specified error message.
     * 
     * @param message the detail message explaining the exception
     */
    public HeapException(String message) {
        super(message);
    }
    
    /**
     * Creates a new HeapException with the specified error message and cause.
     * 
     * @param message the detail message explaining the exception
     * @param cause the cause of this exception
     */
    public HeapException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new HeapException with the specified cause.
     * 
     * @param cause the cause of this exception
     */
    public HeapException(Throwable cause) {
        super(cause);
    }
} 