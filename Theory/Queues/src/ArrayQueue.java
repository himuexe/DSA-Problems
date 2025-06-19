import exceptions.QueueOverflowException;
import exceptions.QueueUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Array-based Queue Implementation using Circular Array
 * 
 * This class implements a queue data structure using a fixed-size circular array as the 
 * underlying storage mechanism. It follows the FIFO (First In, First Out) principle and 
 * provides all standard queue operations with O(1) time complexity for core operations.
 * 
 * Key Features:
 * - Fixed capacity defined at initialization
 * - Circular array implementation for space efficiency
 * - Queue overflow/underflow protection with custom exceptions
 * - Memory efficient with minimal overhead per element
 * - Cache-friendly contiguous memory layout
 * 
 * Circular Array Logic:
 * - Uses front and rear pointers that wrap around the array
 * - Eliminates need to shift elements during dequeue operations
 * - Maintains O(1) time complexity for all core operations
 * 
 * Best Use Cases:
 * - Known size limits and memory constraints
 * - High-performance applications requiring predictable memory usage
 * - Systems requiring bounded queues (e.g., producer-consumer scenarios)
 * - Embedded systems or performance-critical code
 * 
 * Time Complexities:
 * - Enqueue: O(1)
 * - Dequeue: O(1)
 * - Front: O(1)
 * - Rear: O(1)
 * - isEmpty/isFull/size: O(1)
 * 
 * Space Complexity: O(capacity)
 * 
 * @param <T> the type of elements stored in this queue
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class ArrayQueue<T> {
    
    // Instance variables
    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Default constructor that creates a queue with default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(DEFAULT_CAPACITY)
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this.capacity = DEFAULT_CAPACITY;
        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    /**
     * Constructor that creates a queue with specified capacity.
     * 
     * @param capacity the maximum number of elements this queue can hold
     * @throws IllegalArgumentException if capacity is negative or zero
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, got: " + capacity);
        }
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    // ==================== CORE QUEUE OPERATIONS ====================
    
    /**
     * Enqueues an element to the rear of the queue.
     * 
     * @param element the element to be added to the queue
     * @throws QueueOverflowException if the queue is at maximum capacity
     * @throws IllegalArgumentException if element is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void enqueue(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot enqueue null element");
        }
        if (isFull()) {
            throw new QueueOverflowException("Cannot enqueue '" + element + "' - queue is at maximum capacity (" + capacity + ")");
        }
        
        rear = (rear + 1) % capacity;  // Circular increment
        queue[rear] = element;
        size++;
        
        System.out.println("✅ Enqueued '" + element + "' to queue. Current size: " + size);
    }
    
    /**
     * Dequeues and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot dequeue from empty queue");
        }
        
        T element = queue[front];
        queue[front] = null; // Help GC
        front = (front + 1) % capacity;  // Circular increment
        size--;
        
        System.out.println("✅ Dequeued '" + element + "' from queue. Current size: " + size);
        return element;
    }
    
    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T front() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek front of empty queue");
        }
        return queue[front];
    }
    
    /**
     * Returns the element at the rear of the queue without removing it.
     * 
     * @return the element at the rear of the queue
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T rear() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek rear of empty queue");
        }
        return queue[rear];
    }
    
    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks if the queue is full.
     * 
     * @return true if the queue is at maximum capacity, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull() {
        return size == capacity;
    }
    
    /**
     * Returns the current number of elements in the queue.
     * 
     * @return the number of elements currently in the queue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return size;
    }
    
    // ==================== QUEUE-SPECIFIC OPERATIONS ====================
    
    /**
     * Removes all elements from the queue, making it empty.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        // Help GC by nullifying references
        Arrays.fill(queue, null);
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("✅ Queue cleared. All elements removed.");
    }
    
    /**
     * Returns the maximum capacity of the queue.
     * 
     * @return the maximum number of elements this queue can hold
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Prints the current state of the queue from front to rear.
     * Displays queue elements in FIFO order.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty: []");
            return;
        }
        
        System.out.print("Queue (front to rear): [");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Size: " + size + "/" + capacity + ")");
    }
    
    /**
     * Returns an array containing all elements in the queue in FIFO order.
     * 
     * @return array representation of the queue
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            result[i] = queue[index];
        }
        return result;
    }
    
    /**
     * Returns a string representation of the queue.
     * 
     * @return string representation showing all elements from front to rear
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "ArrayQueue[]";
        }
        
        StringBuilder sb = new StringBuilder("ArrayQueue[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(queue[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE TESTING ====================
    
    /**
     * Interactive main method for testing and demonstrating ArrayQueue operations.
     * Provides a menu-driven interface for educational purposes.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🔥 ArrayQueue Implementation Demo");
        System.out.println("=================================");
        
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n📋 Queue Operations Menu:");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Front element");
            System.out.println("4. Rear element");
            System.out.println("5. Check if empty");
            System.out.println("6. Check if full");
            System.out.println("7. Get size");
            System.out.println("8. Print queue");
            System.out.println("9. Clear queue");
            System.out.println("10. Queue info");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice (0-10): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter element to enqueue: ");
                        int element = scanner.nextInt();
                        queue.enqueue(element);
                        break;
                        
                    case 2:
                        try {
                            Integer dequeued = queue.dequeue();
                            System.out.println("✅ Dequeued: " + dequeued);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        try {
                            Integer front = queue.front();
                            System.out.println("✅ Front element: " + front);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 4:
                        try {
                            Integer rear = queue.rear();
                            System.out.println("✅ Rear element: " + rear);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 5:
                        System.out.println("✅ Queue is " + (queue.isEmpty() ? "empty" : "not empty"));
                        break;
                        
                    case 6:
                        System.out.println("✅ Queue is " + (queue.isFull() ? "full" : "not full"));
                        break;
                        
                    case 7:
                        System.out.println("✅ Queue size: " + queue.size() + "/" + queue.getCapacity());
                        break;
                        
                    case 8:
                        queue.printQueue();
                        break;
                        
                    case 9:
                        queue.clear();
                        break;
                        
                    case 10:
                        System.out.println("✅ Queue Capacity: " + queue.getCapacity());
                        System.out.println("✅ Current Size: " + queue.size());
                        System.out.println("✅ Is Empty: " + queue.isEmpty());
                        System.out.println("✅ Is Full: " + queue.isFull());
                        System.out.println("✅ Array Representation: " + Arrays.toString(queue.toArray()));
                        break;
                        
                    case 0:
                        System.out.println("👋 Exiting ArrayQueue demo. Thanks for learning!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("✗ Invalid choice. Please enter 0-10.");
                }
                
            } catch (Exception e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
} 