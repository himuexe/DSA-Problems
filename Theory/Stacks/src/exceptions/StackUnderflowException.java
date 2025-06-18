package exceptions;

/**
 * Custom exception class for stack underflow conditions.
 * 
 * This exception is thrown when attempting to pop or peek from an empty stack.
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class StackUnderflowException extends RuntimeException {
    
    /**
     * Constructs a new StackUnderflowException with no detail message.
     */
    public StackUnderflowException() {
        super("Stack underflow: Cannot perform operation on empty stack");
    }
    
    /**
     * Constructs a new StackUnderflowException with the specified detail message.
     * 
     * @param message the detail message explaining the underflow condition
     */
    public StackUnderflowException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new StackUnderflowException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the underflow condition
     * @param cause the cause of the exception
     */
    public StackUnderflowException(String message, Throwable cause) {
        super(message, cause);
    }
} 