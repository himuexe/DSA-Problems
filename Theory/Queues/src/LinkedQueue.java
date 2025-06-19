import exceptions.QueueUnderflowException;
import nodes.QueueNode;
import java.util.Scanner;

/**
 * Linked List-based Queue Implementation
 * 
 * This class implements a queue data structure using a linked list as the underlying
 * storage mechanism. It follows the FIFO (First In, First Out) principle and provides
 * all standard queue operations with O(1) time complexity for core operations.
 * 
 * Key Features:
 * - Dynamic size - grows and shrinks as needed
 * - No capacity limitations (only limited by available memory)
 * - Node-based storage with pointers
 * - Memory efficient - allocates only what's needed
 * - No overflow exceptions (unless system runs out of memory)
 * 
 * Node Structure:
 * - Uses QueueNode class with data and next pointer
 * - Maintains head (front) and tail (rear) pointers
 * - Efficient insertion at rear and deletion at front
 * 
 * Best Use Cases:
 * - Unknown or highly variable queue sizes
 * - Memory-constrained environments where you don't want to pre-allocate
 * - Applications requiring unlimited queue capacity
 * - Systems where queue size fluctuates significantly
 * 
 * Time Complexities:
 * - Enqueue: O(1)
 * - Dequeue: O(1)
 * - Front: O(1)
 * - Rear: O(1)
 * - isEmpty/size: O(1)
 * 
 * Space Complexity: O(n) where n is the number of elements
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class LinkedQueue {
    
    // Instance variables
    private QueueNode head;  // Points to the front of the queue
    private QueueNode tail;  // Points to the rear of the queue
    private int size;        // Current number of elements
    
    /**
     * Constructor that creates an empty linked queue.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // ==================== CORE QUEUE OPERATIONS ====================
    
    /**
     * Enqueues an element to the rear of the queue.
     * 
     * @param element the element to be added to the queue
     * @throws IllegalArgumentException if element is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void enqueue(int element) {
        QueueNode newNode = new QueueNode(element);
        
        // If queue is empty, both head and tail point to the new node
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // Add new node at the rear and update tail
            tail.next = newNode;
            tail = newNode;
        }
        
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
    public int dequeue() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot dequeue from empty queue");
        }
        
        int element = head.data;
        head = head.next;
        size--;
        
        // If queue becomes empty, reset tail to null
        if (head == null) {
            tail = null;
        }
        
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
    public int front() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek front of empty queue");
        }
        return head.data;
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
            throw new QueueUnderflowException("Cannot peek rear of empty queue");
        }
        return tail.data;
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
        return head == null;
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
        head = null;
        tail = null;
        size = 0;
        System.out.println("✅ Queue cleared. All elements removed.");
    }
    
    /**
     * Searches for an element in the queue and returns its position from the front.
     * 
     * @param element the element to search for
     * @return the 1-based position from the front (1 = front element), or -1 if not found
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int search(int element) {
        QueueNode current = head;
        int position = 1;
        
        while (current != null) {
            if (current.data == element) {
                return position;
            }
            current = current.next;
            position++;
        }
        
        return -1; // Element not found
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
        QueueNode current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("] (Size: " + size + ")");
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
        int[] result = new int[size];
        QueueNode current = head;
        int index = 0;
        
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
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
            return "LinkedQueue[]";
        }
        
        StringBuilder sb = new StringBuilder("LinkedQueue[");
        QueueNode current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE TESTING ====================
    
    /**
     * Interactive main method for testing and demonstrating LinkedQueue operations.
     * Provides a menu-driven interface for educational purposes.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🔗 LinkedQueue Implementation Demo");
        System.out.println("==================================");
        
        LinkedQueue queue = new LinkedQueue();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n📋 Queue Operations Menu:");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Front element");
            System.out.println("4. Rear element");
            System.out.println("5. Check if empty");
            System.out.println("6. Get size");
            System.out.println("7. Print queue");
            System.out.println("8. Clear queue");
            System.out.println("9. Search element");
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
                        System.out.println("✅ Queue size: " + queue.size());
                        break;
                        
                    case 7:
                        queue.printQueue();
                        break;
                        
                    case 8:
                        queue.clear();
                        break;
                        
                    case 9:
                        System.out.print("Enter element to search: ");
                        int searchElement = scanner.nextInt();
                        int position = queue.search(searchElement);
                        if (position != -1) {
                            System.out.println("✅ Element " + searchElement + " found at position " + position + " from front");
                        } else {
                            System.out.println("✗ Element " + searchElement + " not found in queue");
                        }
                        break;
                        
                    case 10:
                        System.out.println("✅ Current Size: " + queue.size());
                        System.out.println("✅ Is Empty: " + queue.isEmpty());
                        System.out.println("✅ Array Representation: " + java.util.Arrays.toString(queue.toArray()));
                        System.out.println("✅ String Representation: " + queue.toString());
                        break;
                        
                    case 0:
                        System.out.println("👋 Exiting LinkedQueue demo. Thanks for learning!");
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