package exceptions;

/**
 * Exception thrown when attempting to remove an element from an empty queue.
 * 
 * This exception is thrown by queue implementations when the dequeue, front, or rear
 * operations are called on an empty queue where no elements are available.
 * 
 * Usage scenarios:
 * - Dequeue operation called on empty queue
 * - Front/peek operation called on empty queue
 * - Rear operation called on empty queue
 * - Any operation requiring queue elements when queue is empty
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class QueueUnderflowException extends RuntimeException {
    
    /**
     * Constructs a new QueueUnderflowException with a default message.
     */
    public QueueUnderflowException() {
        super("Queue underflow: Cannot dequeue element - queue is empty");
    }
    
    /**
     * Constructs a new QueueUnderflowException with the specified detail message.
     * 
     * @param message the detail message explaining the cause of the underflow
     */
    public QueueUnderflowException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new QueueUnderflowException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the cause of the underflow
     * @param cause the cause of the exception
     */
    public QueueUnderflowException(String message, Throwable cause) {
        super(message, cause);
    }
} 