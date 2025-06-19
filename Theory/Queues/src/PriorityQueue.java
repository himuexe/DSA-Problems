import exceptions.QueueOverflowException;
import exceptions.QueueUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Priority Queue Implementation using Binary Heap
 * 
 * This class implements a priority queue data structure using a binary heap
 * as the underlying storage mechanism. Elements are dequeued based on their
 * priority rather than insertion order, making it different from regular FIFO queues.
 * 
 * Key Features:
 * - Supports both min-heap (highest priority = smallest value) and max-heap modes
 * - Dynamic resizing when capacity is reached
 * - Efficient insertion and deletion operations
 * - Complete binary tree structure stored in array
 * - Maintains heap property after every operation
 * 
 * Heap Properties:
 * - Min-Heap: parent <= children (root is minimum)
 * - Max-Heap: parent >= children (root is maximum)
 * - Complete binary tree structure
 * - Array representation: left child = 2*i+1, right child = 2*i+2, parent = (i-1)/2
 * 
 * Use Cases:
 * - Task scheduling systems
 * - Dijkstra's shortest path algorithm
 * - Huffman coding
 * - Operating system process scheduling
 * - A* pathfinding algorithm
 * 
 * Time Complexities:
 * - Enqueue (insert): O(log n)
 * - Dequeue (extract): O(log n)
 * - Peek: O(1)
 * - Build heap: O(n)
 * 
 * Space Complexity: O(capacity)
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class PriorityQueue {
    
    // Instance variables
    private int[] heap;
    private int size;
    private int capacity;
    private boolean isMinHeap;  // true for min-heap, false for max-heap
    
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;
    
    /**
     * Constructor that creates a min-heap priority queue with default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(DEFAULT_CAPACITY)
     */
    public PriorityQueue() {
        this(DEFAULT_CAPACITY, true);
    }
    
    /**
     * Constructor that creates a priority queue with specified capacity and heap type.
     * 
     * @param capacity initial capacity of the priority queue
     * @param isMinHeap true for min-heap (smaller values have higher priority), false for max-heap
     * @throws IllegalArgumentException if capacity is negative or zero
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public PriorityQueue(int capacity, boolean isMinHeap) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, got: " + capacity);
        }
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }
    
    // ==================== CORE PRIORITY QUEUE OPERATIONS ====================
    
    /**
     * Enqueues an element with priority to the priority queue.
     * Element is inserted maintaining heap property.
     * 
     * @param element the element to be added to the queue
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public void enqueue(int element) {
        // Resize if necessary
        if (size >= capacity) {
            resize();
        }
        
        // Insert element at the end and heapify up
        heap[size] = element;
        heapifyUp(size);
        size++;
        
        String heapType = isMinHeap ? "min-heap" : "max-heap";
        System.out.println("✅ Enqueued '" + element + "' to " + heapType + " priority queue. Current size: " + size);
    }
    
    /**
     * Dequeues and returns the element with highest priority.
     * 
     * @return the element with highest priority
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot dequeue from empty priority queue");
        }
        
        // Store root element (highest priority)
        int root = heap[0];
        
        // Move last element to root and heapify down
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            heapifyDown(0);
        }
        
        String heapType = isMinHeap ? "min-heap" : "max-heap";
        System.out.println("✅ Dequeued '" + root + "' from " + heapType + " priority queue. Current size: " + size);
        return root;
    }
    
    /**
     * Returns the element with highest priority without removing it.
     * 
     * @return the element with highest priority
     * @throws QueueUnderflowException if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int peek() {
        if (isEmpty()) {
            throw new QueueUnderflowException("Cannot peek at empty priority queue");
        }
        return heap[0];
    }
    
    /**
     * Checks if the priority queue is empty.
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
    
    // ==================== HEAP OPERATIONS ====================
    
    /**
     * Maintains heap property by moving element up the tree.
     * Used after insertion.
     * 
     * @param index index of the element to heapify up
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void heapifyUp(int index) {
        if (index == 0) return;  // Root reached
        
        int parentIndex = (index - 1) / 2;
        
        // Check if heap property is violated
        if (hasHigherPriority(heap[index], heap[parentIndex])) {
            // Swap with parent
            swap(index, parentIndex);
            // Recursively heapify up
            heapifyUp(parentIndex);
        }
    }
    
    /**
     * Maintains heap property by moving element down the tree.
     * Used after deletion.
     * 
     * @param index index of the element to heapify down
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int extremeIndex = index;
        
        // Find child with higher priority
        if (leftChild < size && hasHigherPriority(heap[leftChild], heap[extremeIndex])) {
            extremeIndex = leftChild;
        }
        
        if (rightChild < size && hasHigherPriority(heap[rightChild], heap[extremeIndex])) {
            extremeIndex = rightChild;
        }
        
        // If heap property is violated, swap and continue
        if (extremeIndex != index) {
            swap(index, extremeIndex);
            heapifyDown(extremeIndex);
        }
    }
    
    /**
     * Determines if first element has higher priority than second.
     * 
     * @param a first element
     * @param b second element
     * @return true if a has higher priority than b
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private boolean hasHigherPriority(int a, int b) {
        return isMinHeap ? a < b : a > b;
    }
    
    /**
     * Swaps two elements in the heap array.
     * 
     * @param i first index
     * @param j second index
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Resizes the internal array when capacity is reached.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private void resize() {
        int newCapacity = capacity * GROWTH_FACTOR;
        heap = Arrays.copyOf(heap, newCapacity);
        capacity = newCapacity;
        System.out.println("✅ Priority queue resized to capacity: " + capacity);
    }
    
    /**
     * Returns the current capacity of the priority queue.
     * 
     * @return the current capacity
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Removes all elements from the queue, making it empty.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        size = 0;
        Arrays.fill(heap, 0, size, 0);
        System.out.println("✅ Priority queue cleared. All elements removed.");
    }
    
    /**
     * Returns the heap type (min-heap or max-heap).
     * 
     * @return "Min-Heap" or "Max-Heap"
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public String getHeapType() {
        return isMinHeap ? "Min-Heap" : "Max-Heap";
    }
    
    /**
     * Prints the current state of the priority queue showing heap structure.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Priority queue is empty: []");
            return;
        }
        
        System.out.print("Priority queue (" + getHeapType() + "): [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] (Size: " + size + "/" + capacity + ")");
    }
    
    /**
     * Prints the heap in tree structure format for visualization.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(log n)
     */
    public void printHeapTree() {
        if (isEmpty()) {
            System.out.println("Priority queue is empty - no tree to display");
            return;
        }
        
        System.out.println("\nHeap Tree Structure (" + getHeapType() + "):");
        printHeapTreeHelper(0, "", true);
        System.out.println();
    }
    
    /**
     * Helper method for printing heap tree structure.
     * 
     * @param index current node index
     * @param prefix prefix for current line
     * @param isLast whether this is the last child
     */
    private void printHeapTreeHelper(int index, String prefix, boolean isLast) {
        if (index >= size) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + heap[index]);
        
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        
        if (leftChild < size) {
            printHeapTreeHelper(leftChild, prefix + (isLast ? "    " : "│   "), rightChild >= size);
        }
        if (rightChild < size) {
            printHeapTreeHelper(rightChild, prefix + (isLast ? "    " : "│   "), true);
        }
    }
    
    /**
     * Returns an array containing all elements in heap order.
     * 
     * @return array representation of the heap
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] toArray() {
        return Arrays.copyOf(heap, size);
    }
    
    /**
     * Returns a string representation of the priority queue.
     * 
     * @return string representation showing heap type and elements
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "PriorityQueue[" + getHeapType() + "]";
        }
        
        StringBuilder sb = new StringBuilder("PriorityQueue[" + getHeapType() + ": ");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE TESTING ====================
    
    /**
     * Interactive main method for testing and demonstrating PriorityQueue operations.
     * Provides a menu-driven interface for educational purposes.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🏆 PriorityQueue Implementation Demo");
        System.out.println("====================================");
        
        Scanner scanner = new Scanner(System.in);
        
        // Choose heap type
        System.out.print("Choose heap type (1 for Min-Heap, 2 for Max-Heap): ");
        int heapChoice = scanner.nextInt();
        boolean isMinHeap = heapChoice == 1;
        
        PriorityQueue pq = new PriorityQueue(10, isMinHeap);
        System.out.println("Created " + pq.getHeapType() + " priority queue with capacity 10");
        
        while (true) {
            System.out.println("\n📋 Priority Queue Operations Menu:");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Peek element");
            System.out.println("4. Check if empty");
            System.out.println("5. Get size");
            System.out.println("6. Print queue");
            System.out.println("7. Print heap tree");
            System.out.println("8. Clear queue");
            System.out.println("9. Queue info");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice (0-9): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter element to enqueue: ");
                        int element = scanner.nextInt();
                        pq.enqueue(element);
                        break;
                        
                    case 2:
                        try {
                            int dequeued = pq.dequeue();
                            System.out.println("✅ Dequeued: " + dequeued);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 3:
                        try {
                            int top = pq.peek();
                            System.out.println("✅ Highest priority element: " + top);
                        } catch (QueueUnderflowException e) {
                            System.out.println("✗ Error: " + e.getMessage());
                        }
                        break;
                        
                    case 4:
                        System.out.println("✅ Queue is " + (pq.isEmpty() ? "empty" : "not empty"));
                        break;
                        
                    case 5:
                        System.out.println("✅ Queue size: " + pq.size() + "/" + pq.getCapacity());
                        break;
                        
                    case 6:
                        pq.printQueue();
                        break;
                        
                    case 7:
                        pq.printHeapTree();
                        break;
                        
                    case 8:
                        pq.clear();
                        break;
                        
                    case 9:
                        System.out.println("✅ Heap Type: " + pq.getHeapType());
                        System.out.println("✅ Current Size: " + pq.size());
                        System.out.println("✅ Current Capacity: " + pq.getCapacity());
                        System.out.println("✅ Is Empty: " + pq.isEmpty());
                        System.out.println("✅ Array Representation: " + Arrays.toString(pq.toArray()));
                        break;
                        
                    case 0:
                        System.out.println("👋 Exiting PriorityQueue demo. Thanks for learning!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("✗ Invalid choice. Please enter 0-9.");
                }
                
            } catch (Exception e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
} 