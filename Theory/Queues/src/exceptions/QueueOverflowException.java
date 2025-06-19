package exceptions;

/**
 * Exception thrown when attempting to add an element to a queue that has reached its maximum capacity.
 * 
 * This exception is typically thrown by fixed-capacity queue implementations (like ArrayQueue
 * or CircularQueue) when the enqueue operation would exceed the queue's size limit.
 * 
 * Usage scenarios:
 * - ArrayQueue attempting to enqueue when array is full
 * - CircularQueue attempting to enqueue when all positions are occupied
 * - Any bounded queue implementation reaching its capacity limit
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class QueueOverflowException extends RuntimeException {
    
    /**
     * Constructs a new QueueOverflowException with a default message.
     */
    public QueueOverflowException() {
        super("Queue overflow: Cannot enqueue element - queue is at maximum capacity");
    }
    
    /**
     * Constructs a new QueueOverflowException with the specified detail message.
     * 
     * @param message the detail message explaining the cause of the overflow
     */
    public QueueOverflowException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new QueueOverflowException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the cause of the overflow
     * @param cause the cause of the exception
     */
    public QueueOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
} 