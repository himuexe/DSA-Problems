package exceptions;

/**
 * Custom exception class for stack overflow conditions.
 * 
 * This exception is thrown when attempting to push an element onto a stack
 * that has already reached its maximum capacity.
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class StackOverflowException extends RuntimeException {
    
    /**
     * Constructs a new StackOverflowException with no detail message.
     */
    public StackOverflowException() {
        super("Stack overflow: Cannot push element, stack is at maximum capacity");
    }
    
    /**
     * Constructs a new StackOverflowException with the specified detail message.
     * 
     * @param message the detail message explaining the overflow condition
     */
    public StackOverflowException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new StackOverflowException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the overflow condition
     * @param cause the cause of the exception
     */
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
} 