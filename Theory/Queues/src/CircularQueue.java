import exceptions.QueueOverflowException;
import exceptions.QueueUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Circular Queue Implementation
 * 
 * This class implements a circular queue data structure using a fixed-size array
 * with circular wrap-around logic. It demonstrates the classic circular queue
 * concept where the rear pointer wraps around to the beginning when it reaches
 * the end of the array.
 * 
 * Key Features:
 * - Fixed capacity with efficient space utilization
 * - Circular wrap-around logic prevents array shifting
 * - Uses one slot less than capacity to distinguish between full and empty states
 * - Visual demonstration of circular nature through debug methods
 * - Classic computer science circular queue implementation
 * 
 * Circular Logic:
 * - Front and rear pointers move in circular fashion
 * - When rear reaches array end, it wraps to beginning
 * - Queue is full when (rear + 1) % capacity == front
 * - Queue is empty when front == rear
 * - Actual capacity is (array_size - 1) to avoid ambiguity
 * 
 * Educational Value:
 * - Perfect example of modular arithmetic in data structures
 * - Demonstrates space-efficient queue implementation
 * - Shows classic solution to the circular array problem
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
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class CircularQueue {
    
    // Instance variables
    private int[] queue;
    private int front;
    private int rear;
    private int capacity;
    
    /**
     * Constructor that creates a circular queue with specified capacity.
     * Note: Actual usable capacity is (capacity - 1) due to circular queue logic.
     * 
     * @param capacity the size of the internal array
     * @throws IllegalArgumentException if capacity is less than 2
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public CircularQueue(int capacity) {
        if (capacity < 2) {
            throw new IllegalArgumentException("Capacity must be at least 2, got: " + capacity);
        }
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = 0;
    }
    
    // ==================== CORE QUEUE OPERATIONS ====================
    
    /**
     * Enqueues an element to the rear of the circular queue.
     * 
     * @param element the element to be added to the queue
     * @throws QueueOverflowException if the queue is full
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void enqueue(int element) {
        if (isFull()) {
            throw new QueueOverflowException("Cannot enqueue '" + element + "' - circular queue is full");
        }
        
        queue[rear] = element;
        rear = (rear + 1) % capacity;  // Circular increment
        
        System.out.println("✅ Enqueued '" + element + "' to circular queue. Current size: " + size());
        printCircularState();
    }
    
    /**
     * Dequeues and returns the element at the front of the circular queue.
     * 
     * @return the element at the front of the queue
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot dequeue from empty circular queue");
        }
        
        int element = queue[front];
        queue[front] = 0; // Clear the slot (optional, for visualization)
        front = (front + 1) % capacity;  // Circular increment
        
        System.out.println("✅ Dequeued '" + element + "' from circular queue. Current size: " + size());
        printCircularState();
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
    public int front() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek front of empty circular queue");
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
    public int rear() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek rear of empty circular queue");
        }
        // Rear points to next insertion position, so we need previous position
        int rearIndex = (rear - 1 + capacity) % capacity;
        return queue[rearIndex];
    }
    
    /**
     * Checks if the circular queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return front == rear;
    }
    
    /**
     * Checks if the circular queue is full.
     * 
     * @return true if the queue is full, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
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
        return (rear - front + capacity) % capacity;
    }
    
    // ==================== CIRCULAR QUEUE SPECIFIC OPERATIONS ====================
    
    /**
     * Returns the maximum usable capacity of the circular queue.
     * Note: This is one less than the array size due to circular queue logic.
     * 
     * @return the maximum number of elements this queue can hold
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity - 1;
    }
    
    /**
     * Removes all elements from the queue, making it empty.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void clear() {
        Arrays.fill(queue, 0);
        front = 0;
        rear = 0;
        System.out.println("✅ Circular queue cleared. All elements removed.");
        printCircularState();
    }
    
    /**
     * Prints the internal state of the circular queue showing the array,
     * front/rear pointers, and circular nature.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printCircularState() {
        System.out.print("Array: [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(queue[i]);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.print("Index: [");
        for (int i = 0; i < capacity; i++) {
            System.out.print(i);
            if (i < capacity - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.println("Front: " + front + " | Rear: " + rear + " | Size: " + size() + "/" + getCapacity());
        
        // Visual representation of front and rear pointers
        System.out.print("       ");
        for (int i = 0; i < capacity; i++) {
            if (i == front && i == rear) {
                System.out.print("FR");
            } else if (i == front) {
                System.out.print("F ");
            } else if (i == rear) {
                System.out.print("R ");
            } else {
                System.out.print("  ");
            }
            if (i < capacity - 1) System.out.print(" ");
        }
        System.out.println();
        System.out.println();
    }
    
    /**
     * Prints the current queue elements in FIFO order.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Circular queue is empty: []");
            return;
        }
        
        System.out.print("Circular queue (front to rear): [");
        int count = 0;
        int index = front;
        while (count < size()) {
            System.out.print(queue[index]);
            if (count < size() - 1) {
                System.out.print(", ");
            }
            index = (index + 1) % capacity;
            count++;
        }
        System.out.println("] (Size: " + size() + "/" + getCapacity() + ")");
    }
    
    /**
     * Returns an array containing all elements in the queue in FIFO order.
     * 
     * @return array representation of the queue
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] toArray() {
        int[] result = new int[size()];
        int count = 0;
        int index = front;
        
        while (count < size()) {
            result[count] = queue[index];
            index = (index + 1) % capacity;
            count++;
        }
        
        return result;
    }
    
    /**
     * Returns a string representation of the circular queue.
     * 
     * @return string representation showing all elements from front to rear
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "CircularQueue[]";
        }
        
        StringBuilder sb = new StringBuilder("CircularQueue[");
        int count = 0;
        int index = front;
        
        while (count < size()) {
            sb.append(queue[index]);
            if (count < size() - 1) {
                sb.append(", ");
            }
            index = (index + 1) % capacity;
            count++;
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE TESTING ====================
    
    /**
     * Interactive main method for testing and demonstrating CircularQueue operations.
     * Provides a menu-driven interface for educational purposes.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🔄 CircularQueue Implementation Demo");
        System.out.println("====================================");
        System.out.println("Note: This demo uses a capacity of 6, so usable capacity is 5");
        
        CircularQueue queue = new CircularQueue(6);  // Usable capacity = 5
        Scanner scanner = new Scanner(System.in);
        
        // Show initial state
        queue.printCircularState();
        
        while (true) {
            System.out.println("📋 Circular Queue Operations Menu:");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Front element");
            System.out.println("4. Rear element");
            System.out.println("5. Check if empty");
            System.out.println("6. Check if full");
            System.out.println("7. Get size");
            System.out.println("8. Print queue");
            System.out.println("9. Clear queue");
            System.out.println("10. Show circular state");
            System.out.println("11. Queue info");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice (0-11): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter element to enqueue: ");
                        int element = scanner.nextInt();
                        try {
                            queue.enqueue(element);
                        } catch (QueueOverflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 2:
                        try {
                            int dequeued = queue.dequeue();
                            System.out.println("✅ Dequeued: " + dequeued);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        try {
                            int front = queue.front();
                            System.out.println("✅ Front element: " + front);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 4:
                        try {
                            int rear = queue.rear();
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
                        queue.printCircularState();
                        break;
                        
                    case 11:
                        System.out.println("✅ Array Capacity: " + queue.capacity);
                        System.out.println("✅ Usable Capacity: " + queue.getCapacity());
                        System.out.println("✅ Current Size: " + queue.size());
                        System.out.println("✅ Is Empty: " + queue.isEmpty());
                        System.out.println("✅ Is Full: " + queue.isFull());
                        System.out.println("✅ Front Index: " + queue.front);
                        System.out.println("✅ Rear Index: " + queue.rear);
                        System.out.println("✅ Array Representation: " + Arrays.toString(queue.toArray()));
                        break;
                        
                    case 0:
                        System.out.println("👋 Exiting CircularQueue demo. Thanks for learning!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("✗ Invalid choice. Please enter 0-11.");
                }
                
            } catch (Exception e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
} 