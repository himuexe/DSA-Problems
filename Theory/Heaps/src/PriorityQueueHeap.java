import exceptions.HeapUnderflowException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * PriorityQueueHeap implementation demonstrating heap-based priority queue operations.
 * A priority queue is an abstract data type where elements are served based on priority
 * rather than insertion order. This implementation uses a binary heap for efficiency.
 * 
 * Features:
 * - Generic implementation with natural ordering and custom comparators
 * - O(log n) insertion and extraction operations
 * - O(1) peek operation to view highest priority element
 * - Support for both min-priority and max-priority queues
 * - Bulk operations for efficient batch processing
 * 
 * Educational implementation for DSA learning with detailed documentation.
 * 
 * @param <T> the type of elements stored in the priority queue
 * @author DSA Learning Project
 * @version 1.0
 */
public class PriorityQueueHeap<T> {
    
    /** The underlying heap to store elements */
    private ArrayList<T> heap;
    
    /** Custom comparator for element ordering */
    private Comparator<T> comparator;
    
    /** Default initial capacity */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Create a priority queue with natural ordering (min-priority queue).
     * Elements must implement Comparable interface.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueHeap() {
        this.heap = new ArrayList<>(DEFAULT_CAPACITY);
        this.comparator = (Comparator<T>) Comparator.naturalOrder();
        System.out.println("✓ Created min-priority queue with natural ordering");
    }
    
    /**
     * Create a priority queue with custom comparator.
     * 
     * @param comparator the comparator to determine element priority
     */
    public PriorityQueueHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>(DEFAULT_CAPACITY);
        this.comparator = comparator;
        System.out.println("✓ Created priority queue with custom comparator");
    }
    
    /**
     * Create a priority queue with specified initial capacity and natural ordering.
     * 
     * @param initialCapacity the initial capacity of the underlying array
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueHeap(int initialCapacity) {
        this.heap = new ArrayList<>(initialCapacity);
        this.comparator = (Comparator<T>) Comparator.naturalOrder();
        System.out.println("✓ Created min-priority queue with capacity " + initialCapacity);
    }
    
    /**
     * Create a priority queue with specified capacity and custom comparator.
     * 
     * @param initialCapacity the initial capacity
     * @param comparator the comparator for element ordering
     */
    public PriorityQueueHeap(int initialCapacity, Comparator<T> comparator) {
        this.heap = new ArrayList<>(initialCapacity);
        this.comparator = comparator;
        System.out.println("✓ Created priority queue with capacity " + initialCapacity + " and custom comparator");
    }
    
    /**
     * Create a priority queue from an existing collection.
     * Uses Floyd's algorithm for efficient heap construction.
     * 
     * @param elements the collection of elements to initialize with
     * @param comparator the comparator for ordering
     */
    public PriorityQueueHeap(List<T> elements, Comparator<T> comparator) {
        this.heap = new ArrayList<>(elements);
        this.comparator = comparator;
        buildHeap();
        System.out.println("✓ Built priority queue from " + elements.size() + " elements");
    }
    
    // =====================================================
    // === CORE PRIORITY QUEUE OPERATIONS ===
    // =====================================================
    
    /**
     * Add an element to the priority queue.
     * The element will be positioned according to its priority.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param element the element to add
     * @throws IllegalArgumentException if element is null
     */
    public void offer(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null element to priority queue");
        }
        
        // Add element to the end and bubble up
        heap.add(element);
        heapifyUp(heap.size() - 1);
        
        System.out.println("✓ Added " + element + " to priority queue (size: " + size() + ")");
    }
    
    /**
     * Add multiple elements to the priority queue efficiently.
     * More efficient than adding elements one by one.
     * 
     * Time Complexity: O(k log n) where k is number of elements
     * Space Complexity: O(1)
     * 
     * @param elements the elements to add
     */
    public void offerAll(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            System.out.println("✗ No elements to add");
            return;
        }
        
        System.out.println("🔧 Bulk adding " + elements.size() + " elements...");
        for (T element : elements) {
            if (element != null) {
                heap.add(element);
            }
        }
        
        // Rebuild heap for efficiency
        buildHeap();
        System.out.println("✓ Added " + elements.size() + " elements (total size: " + size() + ")");
    }
    
    /**
     * Remove and return the highest priority element.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @return the highest priority element
     * @throws HeapUnderflowException if queue is empty
     */
    public T poll() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot poll from empty priority queue");
        }
        
        T highestPriority = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        
        // If queue is now empty, return the element
        if (heap.isEmpty()) {
            System.out.println("✓ Polled " + highestPriority + " (queue now empty)");
            return highestPriority;
        }
        
        // Replace root with last element and heapify down
        heap.set(0, lastElement);
        heapifyDown(0);
        
        System.out.println("✓ Polled " + highestPriority + " (remaining: " + size() + ")");
        return highestPriority;
    }
    
    /**
     * View the highest priority element without removing it.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the highest priority element
     * @throws HeapUnderflowException if queue is empty
     */
    public T peek() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot peek empty priority queue");
        }
        return heap.get(0);
    }
    
    /**
     * Remove a specific element from the priority queue.
     * 
     * Time Complexity: O(n) for search + O(log n) for removal
     * Space Complexity: O(1)
     * 
     * @param element the element to remove
     * @return true if element was found and removed
     */
    public boolean remove(T element) {
        if (element == null) {
            System.out.println("✗ Cannot remove null element");
            return false;
        }
        
        // Find the element
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(element)) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            System.out.println("✗ Element " + element + " not found in priority queue");
            return false;
        }
        
        // Replace with last element
        T lastElement = heap.remove(heap.size() - 1);
        
        // If we removed the last element, we're done
        if (index == heap.size()) {
            System.out.println("✓ Removed " + element + " from priority queue");
            return true;
        }
        
        // Replace and restore heap property
        heap.set(index, lastElement);
        
        // May need to bubble up or down
        if (index > 0 && shouldSwapWithParent(index)) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
        
        System.out.println("✓ Removed " + element + " from priority queue");
        return true;
    }
    
    // =====================================================
    // === BATCH OPERATIONS ===
    // =====================================================
    
    /**
     * Poll multiple elements from the priority queue.
     * Useful for batch processing of high-priority items.
     * 
     * Time Complexity: O(k log n) where k is count
     * Space Complexity: O(k)
     * 
     * @param count the number of elements to poll
     * @return list of polled elements in priority order
     */
    public List<T> pollMultiple(int count) {
        List<T> results = new ArrayList<>();
        int actualCount = Math.min(count, size());
        
        System.out.println("🔧 Polling " + actualCount + " elements...");
        
        for (int i = 0; i < actualCount; i++) {
            try {
                results.add(poll());
            } catch (HeapUnderflowException e) {
                break; // Shouldn't happen due to size check
            }
        }
        
        System.out.println("✓ Polled " + results.size() + " elements: " + results);
        return results;
    }
    
    /**
     * Drain all elements from the priority queue in priority order.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * 
     * @return list of all elements in priority order
     */
    public List<T> drainAll() {
        List<T> results = new ArrayList<>();
        
        System.out.println("🔧 Draining all " + size() + " elements...");
        
        while (!isEmpty()) {
            try {
                results.add(poll());
            } catch (HeapUnderflowException e) {
                break; // Shouldn't happen
            }
        }
        
        System.out.println("✓ Drained " + results.size() + " elements: " + results);
        return results;
    }
    
    // =====================================================
    // === UTILITY OPERATIONS ===
    // =====================================================
    
    /**
     * Check if the priority queue is empty.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * Get the number of elements in the priority queue.
     * 
     * @return the size of the priority queue
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Remove all elements from the priority queue.
     */
    public void clear() {
        heap.clear();
        System.out.println("✓ Cleared priority queue");
    }
    
    /**
     * Check if the priority queue contains a specific element.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param element the element to search for
     * @return true if element is found
     */
    public boolean contains(T element) {
        return heap.contains(element);
    }
    
    /**
     * Get all elements as a list (not in priority order).
     * 
     * @return list containing all elements
     */
    public List<T> toList() {
        return new ArrayList<>(heap);
    }
    
    /**
     * Convert priority queue to sorted list.
     * Creates a copy and drains it to maintain original queue.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * 
     * @return sorted list of all elements
     */
    public List<T> toSortedList() {
        // Create a copy to avoid modifying original
        PriorityQueueHeap<T> copy = new PriorityQueueHeap<>(this.comparator);
        copy.heap.addAll(this.heap);
        copy.buildHeap();
        
        return copy.drainAll();
    }
    
    // =====================================================
    // === HEAP OPERATIONS (INTERNAL) ===
    // =====================================================
    
    /**
     * Build heap from current elements using Floyd's algorithm.
     */
    private void buildHeap() {
        for (int i = getParentIndex(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    /**
     * Bubble up element to restore heap property.
     */
    private void heapifyUp(int index) {
        while (index > 0 && shouldSwapWithParent(index)) {
            int parentIndex = getParentIndex(index);
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    
    /**
     * Bubble down element to restore heap property.
     */
    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int targetIndex = getLeftChildIndex(index);
            
            // Choose the child with higher priority
            if (hasRightChild(index) && 
                comparator.compare(heap.get(getRightChildIndex(index)), heap.get(targetIndex)) < 0) {
                targetIndex = getRightChildIndex(index);
            }
            
            // If heap property is satisfied, stop
            if (comparator.compare(heap.get(index), heap.get(targetIndex)) <= 0) {
                break;
            }
            
            swap(index, targetIndex);
            index = targetIndex;
        }
    }
    
    // =====================================================
    // === INDEX CALCULATIONS ===
    // =====================================================
    
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }
    
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < heap.size();
    }
    
    // =====================================================
    // === HELPER METHODS ===
    // =====================================================
    
    /**
     * Check if element should be swapped with its parent.
     */
    private boolean shouldSwapWithParent(int index) {
        if (index == 0) return false;
        int parentIndex = getParentIndex(index);
        return comparator.compare(heap.get(index), heap.get(parentIndex)) < 0;
    }
    
    /**
     * Swap two elements in the heap.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    /**
     * Validate heap property for debugging.
     */
    public boolean isValidHeap() {
        for (int i = 0; i < heap.size(); i++) {
            if (hasLeftChild(i) && 
                comparator.compare(heap.get(i), heap.get(getLeftChildIndex(i))) > 0) {
                return false;
            }
            if (hasRightChild(i) && 
                comparator.compare(heap.get(i), heap.get(getRightChildIndex(i))) > 0) {
                return false;
            }
        }
        return true;
    }
    
    // =====================================================
    // === DISPLAY METHODS ===
    // =====================================================
    
    /**
     * Print the priority queue contents.
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Priority queue is empty");
            return;
        }
        
        System.out.println("Priority Queue: " + heap);
        System.out.println("Size: " + size() + ", Highest Priority: " + heap.get(0));
    }
    
    /**
     * Print priority queue in tree format.
     */
    public void printTreeFormat() {
        if (isEmpty()) {
            System.out.println("Priority queue is empty");
            return;
        }
        
        System.out.println("Priority Queue Tree Structure:");
        printTreeHelper(0, "", true);
    }
    
    private void printTreeHelper(int index, String prefix, boolean isLast) {
        if (index >= heap.size()) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + heap.get(index));
        
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        
        if (hasRightChild(index)) {
            printTreeHelper(getRightChildIndex(index), childPrefix, !hasLeftChild(index));
        }
        
        if (hasLeftChild(index)) {
            printTreeHelper(getLeftChildIndex(index), childPrefix, true);
        }
    }
    
    /**
     * Display priority queue statistics.
     */
    public void displayStats() {
        System.out.println("\n=== Priority Queue Statistics ===");
        System.out.println("Size: " + size());
        System.out.println("Empty: " + isEmpty());
        
        if (!isEmpty()) {
            System.out.println("Highest Priority: " + heap.get(0));
            System.out.println("Valid Heap: " + isValidHeap());
            
            int height = (int) Math.floor(Math.log(size()) / Math.log(2));
            System.out.println("Height: " + height);
            
            // Find priority distribution
            System.out.println("Elements: " + heap);
        }
        System.out.println("=================================\n");
    }
    
    // =====================================================
    // === FACTORY METHODS FOR COMMON USE CASES ===
    // =====================================================
    
    /**
     * Create a max-priority queue for integers.
     */
    public static PriorityQueueHeap<Integer> createMaxPriorityQueue() {
        return new PriorityQueueHeap<>(Comparator.<Integer>reverseOrder());
    }
    
    /**
     * Create a min-priority queue for integers.
     */
    public static PriorityQueueHeap<Integer> createMinPriorityQueue() {
        return new PriorityQueueHeap<>(Comparator.<Integer>naturalOrder());
    }
    
    /**
     * Create a priority queue for strings based on length.
     */
    public static PriorityQueueHeap<String> createStringLengthPriorityQueue() {
        return new PriorityQueueHeap<>(Comparator.comparing(String::length));
    }
    
    // =====================================================
    // === INTERACTIVE MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive main method for testing and demonstration.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueueHeap<Integer> pq = null;
        
        System.out.println("🎯 Priority Queue Heap Implementation Demo");
        System.out.println("==========================================");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create Min-Priority Queue");
            System.out.println("2. Create Max-Priority Queue");
            System.out.println("3. Offer Element");
            System.out.println("4. Poll Element");
            System.out.println("5. Peek Element");
            System.out.println("6. Remove Element");
            System.out.println("7. Bulk Offer");
            System.out.println("8. Poll Multiple");
            System.out.println("9. Drain All");
            System.out.println("10. Check Contains");
            System.out.println("11. Print Queue");
            System.out.println("12. Print Tree Format");
            System.out.println("13. Display Statistics");
            System.out.println("14. Convert to Sorted List");
            System.out.println("15. Validate Heap Property");
            System.out.println("16. Clear Queue");
            System.out.println("17. Priority Queue Demo");
            System.out.println("18. Performance Test");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    pq = createMinPriorityQueue();
                    break;
                    
                case 2:
                    pq = createMaxPriorityQueue();
                    break;
                    
                case 3:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    System.out.print("Enter element to offer: ");
                    int element = scanner.nextInt();
                    pq.offer(element);
                    break;
                    
                case 4:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    try {
                        Integer polled = pq.poll();
                        System.out.println("Polled: " + polled);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 5:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    try {
                        Integer peeked = pq.peek();
                        System.out.println("Highest Priority: " + peeked);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    System.out.print("Enter element to remove: ");
                    int removeElement = scanner.nextInt();
                    pq.remove(removeElement);
                    break;
                    
                case 7:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    System.out.print("Enter elements (space-separated): ");
                    scanner.nextLine(); // consume newline
                    String[] elements = scanner.nextLine().split(" ");
                    List<Integer> bulkElements = new ArrayList<>();
                    for (String elem : elements) {
                        try {
                            bulkElements.add(Integer.parseInt(elem.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("Skipping invalid element: " + elem);
                        }
                    }
                    pq.offerAll(bulkElements);
                    break;
                    
                case 8:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    System.out.print("How many elements to poll? ");
                    int count = scanner.nextInt();
                    List<Integer> polled = pq.pollMultiple(count);
                    System.out.println("Polled elements: " + polled);
                    break;
                    
                case 9:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    List<Integer> all = pq.drainAll();
                    System.out.println("All elements in priority order: " + all);
                    break;
                    
                case 10:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    System.out.print("Enter element to search: ");
                    int search = scanner.nextInt();
                    boolean contains = pq.contains(search);
                    System.out.println("Contains " + search + ": " + contains);
                    break;
                    
                case 11:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    pq.printQueue();
                    break;
                    
                case 12:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    pq.printTreeFormat();
                    break;
                    
                case 13:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    pq.displayStats();
                    break;
                    
                case 14:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    List<Integer> sorted = pq.toSortedList();
                    System.out.println("Sorted list: " + sorted);
                    break;
                    
                case 15:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    boolean valid = pq.isValidHeap();
                    System.out.println("Valid heap property: " + valid);
                    break;
                    
                case 16:
                    if (pq == null) {
                        System.out.println("Please create a priority queue first!");
                        break;
                    }
                    pq.clear();
                    break;
                    
                case 17:
                    System.out.println("🎭 Priority Queue Demo");
                    PriorityQueueHeap<Integer> demo = createMinPriorityQueue();
                    System.out.println("Adding task priorities: 3, 1, 4, 1, 5, 9, 2, 6");
                    List<Integer> tasks = List.of(3, 1, 4, 1, 5, 9, 2, 6);
                    demo.offerAll(tasks);
                    demo.printTreeFormat();
                    
                    System.out.println("Processing tasks in priority order:");
                    List<Integer> processed = demo.drainAll();
                    System.out.println("Task processing order: " + processed);
                    break;
                    
                case 18:
                    System.out.println("🚀 Performance Test");
                    PriorityQueueHeap<Integer> perfTest = createMinPriorityQueue();
                    
                    System.out.println("Adding 10000 random elements...");
                    long startTime = System.nanoTime();
                    for (int i = 0; i < 10000; i++) {
                        perfTest.offer((int) (Math.random() * 10000));
                    }
                    long endTime = System.nanoTime();
                    System.out.println("Addition time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    
                    System.out.println("Polling all elements...");
                    startTime = System.nanoTime();
                    perfTest.drainAll();
                    endTime = System.nanoTime();
                    System.out.println("Polling time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    break;
                    
                case 0:
                    System.out.println("Goodbye! 👋");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
} 