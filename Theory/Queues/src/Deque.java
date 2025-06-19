import exceptions.QueueOverflowException;
import exceptions.QueueUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deque (Double-Ended Queue) Implementation using Circular Array
 * 
 * This class implements a double-ended queue (deque) data structure that allows
 * insertion and deletion operations at both ends (front and rear). It combines
 * the functionality of both stack and queue data structures.
 * 
 * Key Features:
 * - Insertion and deletion at both front and rear
 * - Circular array implementation for efficient operations
 * - Fixed capacity with overflow protection
 * - Support for both LIFO and FIFO operations
 * - Can be used as a stack, queue, or deque
 * 
 * Operations Supported:
 * - addFront() / addRear() - insertion operations
 * - removeFront() / removeRear() - deletion operations
 * - getFront() / getRear() - peek operations
 * - Stack operations: push = addFront, pop = removeFront
 * - Queue operations: enqueue = addRear, dequeue = removeFront
 * 
 * Use Cases:
 * - Browser history navigation (back/forward)
 * - Undo/Redo functionality
 * - Sliding window algorithms
 * - Palindrome checking
 * - Double-ended priority queues
 * 
 * Time Complexities:
 * - addFront/addRear: O(1)
 * - removeFront/removeRear: O(1)
 * - getFront/getRear: O(1)
 * - isEmpty/isFull/size: O(1)
 * 
 * Space Complexity: O(capacity)
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class Deque {
    
    // Instance variables
    private int[] deque;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Default constructor that creates a deque with default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(DEFAULT_CAPACITY)
     */
    public Deque() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor that creates a deque with specified capacity.
     * 
     * @param capacity the maximum number of elements this deque can hold
     * @throws IllegalArgumentException if capacity is negative or zero
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public Deque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, got: " + capacity);
        }
        this.capacity = capacity;
        this.deque = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    // ==================== CORE DEQUE OPERATIONS ====================
    
    /**
     * Adds an element to the front of the deque.
     * 
     * @param element the element to be added to the front
     * @throws QueueOverflowException if the deque is full
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void addFront(int element) {
        if (isFull()) {
            throw new QueueOverflowException("Cannot add to front - deque is full");
        }
        
        front = (front - 1 + capacity) % capacity;  // Circular decrement
        deque[front] = element;
        size++;
        
        System.out.println("✅ Added '" + element + "' to front of deque. Current size: " + size);
    }
    
    /**
     * Adds an element to the rear of the deque.
     * 
     * @param element the element to be added to the rear
     * @throws QueueOverflowException if the deque is full
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void addRear(int element) {
        if (isFull()) {
            throw new QueueOverflowException("Cannot add to rear - deque is full");
        }
        
        rear = (rear + 1) % capacity;  // Circular increment
        deque[rear] = element;
        size++;
        
        System.out.println("✅ Added '" + element + "' to rear of deque. Current size: " + size);
    }
    
    /**
     * Removes and returns the element from the front of the deque.
     * 
     * @return the element at the front of the deque
     * @throws QueueUnderflowException if the deque is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int removeFront() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot remove from front - deque is empty");
        }
        
        int element = deque[front];
        deque[front] = 0; // Clear for visualization
        front = (front + 1) % capacity;  // Circular increment
        size--;
        
        System.out.println("✅ Removed '" + element + "' from front of deque. Current size: " + size);
        return element;
    }
    
    /**
     * Removes and returns the element from the rear of the deque.
     * 
     * @return the element at the rear of the deque
     * @throws QueueUnderflowException if the deque is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int removeRear() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot remove from rear - deque is empty");
        }
        
        int element = deque[rear];
        deque[rear] = 0; // Clear for visualization
        rear = (rear - 1 + capacity) % capacity;  // Circular decrement
        size--;
        
        System.out.println("✅ Removed '" + element + "' from rear of deque. Current size: " + size);
        return element;
    }
    
    /**
     * Returns the element at the front of the deque without removing it.
     * 
     * @return the element at the front of the deque
     * @throws QueueUnderflowException if the deque is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getFront() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot get front - deque is empty");
        }
        return deque[front];
    }
    
    /**
     * Returns the element at the rear of the deque without removing it.
     * 
     * @return the element at the rear of the deque
     * @throws QueueUnderflowException if the deque is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getRear() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot get rear - deque is empty");
        }
        return deque[rear];
    }
    
    // ==================== STACK-LIKE OPERATIONS ====================
    
    /**
     * Pushes an element onto the deque (adds to front).
     * Equivalent to addFront() - provides stack-like interface.
     * 
     * @param element the element to push
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void push(int element) {
        addFront(element);
        System.out.println("💡 Stack operation: pushed " + element);
    }
    
    /**
     * Pops an element from the deque (removes from front).
     * Equivalent to removeFront() - provides stack-like interface.
     * 
     * @return the popped element
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int pop() {
        int element = removeFront();
        System.out.println("💡 Stack operation: popped " + element);
        return element;
    }
    
    // ==================== QUEUE-LIKE OPERATIONS ====================
    
    /**
     * Enqueues an element to the deque (adds to rear).
     * Equivalent to addRear() - provides queue-like interface.
     * 
     * @param element the element to enqueue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void enqueue(int element) {
        addRear(element);
        System.out.println("💡 Queue operation: enqueued " + element);
    }
    
    /**
     * Dequeues an element from the deque (removes from front).
     * Equivalent to removeFront() - provides queue-like interface.
     * 
     * @return the dequeued element
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int dequeue() {
        int element = removeFront();
        System.out.println("💡 Queue operation: dequeued " + element);
        return element;
    }
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Checks if the deque is empty.
     * 
     * @return true if the deque is empty, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks if the deque is full.
     * 
     * @return true if the deque is full, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull() {
        return size == capacity;
    }
    
    /**
     * Returns the current number of elements in the deque.
     * 
     * @return the number of elements currently in the deque
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the maximum capacity of the deque.
     * 
     * @return the maximum number of elements this deque can hold
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Removes all elements from the deque, making it empty.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void clear() {
        Arrays.fill(deque, 0);
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("✅ Deque cleared. All elements removed.");
    }
    
    /**
     * Prints the current state of the deque from front to rear.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Deque is empty: []");
            return;
        }
        
        System.out.print("Deque (front to rear): [");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(deque[index]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Size: " + size + "/" + capacity + ")");
    }
    
    /**
     * Prints the internal array state showing front and rear pointers.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printInternalState() {
        System.out.print("Internal Array: [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(deque[i]);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.print("Index:          [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(i);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.println("Front: " + front + " | Rear: " + rear + " | Size: " + size);
        
        System.out.print("Pointers:       [");
        for (int i = 0; i < capacity; i++) {
            if (isEmpty()) {
                System.out.print(" ");
            } else if (i == front && i == rear) {
                System.out.print("FR");
            } else if (i == front) {
                System.out.print("F");
            } else if (i == rear) {
                System.out.print("R");
            } else {
                System.out.print(" ");
            }
            if (i < capacity - 1) System.out.print(" ");
        }
        System.out.println("]");
        System.out.println();
    }
    
    /**
     * Returns an array containing all elements in the deque from front to rear.
     * 
     * @return array representation of the deque
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            result[i] = deque[index];
        }
        return result;
    }
    
    /**
     * Returns a string representation of the deque.
     * 
     * @return string representation showing all elements from front to rear
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Deque[]";
        }
        
        StringBuilder sb = new StringBuilder("Deque[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(deque[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE TESTING ====================
    
    /**
     * Interactive main method for testing and demonstrating Deque operations.
     * Provides a menu-driven interface for educational purposes.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🔄 Deque (Double-Ended Queue) Implementation Demo");
        System.out.println("=================================================");
        
        Deque deque = new Deque(6);
        Scanner scanner = new Scanner(System.in);
        
        // Show initial state
        deque.printInternalState();
        
        while (true) {
            System.out.println("📋 Deque Operations Menu:");
            System.out.println("=== Core Deque Operations ===");
            System.out.println("1. Add to Front");
            System.out.println("2. Add to Rear");
            System.out.println("3. Remove from Front");
            System.out.println("4. Remove from Rear");
            System.out.println("5. Get Front");
            System.out.println("6. Get Rear");
            System.out.println("=== Stack-like Operations ===");
            System.out.println("7. Push (add front)");
            System.out.println("8. Pop (remove front)");
            System.out.println("=== Queue-like Operations ===");
            System.out.println("9. Enqueue (add rear)");
            System.out.println("10. Dequeue (remove front)");
            System.out.println("=== Utility Operations ===");
            System.out.println("11. Check if empty");
            System.out.println("12. Check if full");
            System.out.println("13. Get size");
            System.out.println("14. Print deque");
            System.out.println("15. Show internal state");
            System.out.println("16. Clear deque");
            System.out.println("17. Deque info");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice (0-17): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter element to add to front: ");
                        int frontElement = scanner.nextInt();
                        try {
                            deque.addFront(frontElement);
                        } catch (QueueOverflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter element to add to rear: ");
                        int rearElement = scanner.nextInt();
                        try {
                            deque.addRear(rearElement);
                        } catch (QueueOverflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        try {
                            int removed = deque.removeFront();
                            System.out.println("✅ Removed from front: " + removed);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 4:
                        try {
                            int removed = deque.removeRear();
                            System.out.println("✅ Removed from rear: " + removed);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 5:
                        try {
                            int front = deque.getFront();
                            System.out.println("✅ Front element: " + front);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 6:
                        try {
                            int rear = deque.getRear();
                            System.out.println("✅ Rear element: " + rear);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 7:
                        System.out.print("Enter element to push: ");
                        int pushElement = scanner.nextInt();
                        try {
                            deque.push(pushElement);
                        } catch (QueueOverflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 8:
                        try {
                            int popped = deque.pop();
                            System.out.println("✅ Popped: " + popped);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 9:
                        System.out.print("Enter element to enqueue: ");
                        int enqueueElement = scanner.nextInt();
                        try {
                            deque.enqueue(enqueueElement);
                        } catch (QueueOverflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 10:
                        try {
                            int dequeued = deque.dequeue();
                            System.out.println("✅ Dequeued: " + dequeued);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 11:
                        System.out.println("✅ Deque is " + (deque.isEmpty() ? "empty" : "not empty"));
                        break;
                        
                    case 12:
                        System.out.println("✅ Deque is " + (deque.isFull() ? "full" : "not full"));
                        break;
                        
                    case 13:
                        System.out.println("✅ Deque size: " + deque.size() + "/" + deque.getCapacity());
                        break;
                        
                    case 14:
                        deque.printDeque();
                        break;
                        
                    case 15:
                        deque.printInternalState();
                        break;
                        
                    case 16:
                        deque.clear();
                        break;
                        
                    case 17:
                        System.out.println("✅ Capacity: " + deque.getCapacity());
                        System.out.println("✅ Current Size: " + deque.size());
                        System.out.println("✅ Is Empty: " + deque.isEmpty());
                        System.out.println("✅ Is Full: " + deque.isFull());
                        System.out.println("✅ Array Representation: " + Arrays.toString(deque.toArray()));
                        break;
                        
                    case 0:
                        System.out.println("👋 Exiting Deque demo. Thanks for learning!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("✗ Invalid choice. Please enter 0-17.");
                }
                
            } catch (Exception e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
} 