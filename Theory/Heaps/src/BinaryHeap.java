import exceptions.HeapUnderflowException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * BinaryHeap implementation with comprehensive operations for both min-heap and max-heap.
 * A binary heap is a complete binary tree that satisfies the heap property:
 * - Min-heap: parent <= children (root is minimum)
 * - Max-heap: parent >= children (root is maximum)
 * 
 * This implementation uses an array-based representation for efficiency.
 * Educational implementation for DSA learning with detailed documentation.
 * 
 * @param <T> the type of elements stored in the heap, must be comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class BinaryHeap<T extends Comparable<T>> {
    
    /** The underlying array to store heap elements */
    private ArrayList<T> heap;
    
    /** The heap type - true for min-heap, false for max-heap */
    private boolean isMinHeap;
    
    /** The default initial capacity for the heap */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Constructor to create a binary heap with default capacity.
     * 
     * @param isMinHeap true for min-heap, false for max-heap
     */
    public BinaryHeap(boolean isMinHeap) {
        this.heap = new ArrayList<>(DEFAULT_CAPACITY);
        this.isMinHeap = isMinHeap;
        System.out.println("✓ Created " + (isMinHeap ? "min" : "max") + "-heap with capacity " + DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor to create a binary heap with specified initial capacity.
     * 
     * @param capacity the initial capacity of the heap
     * @param isMinHeap true for min-heap, false for max-heap
     */
    public BinaryHeap(int capacity, boolean isMinHeap) {
        this.heap = new ArrayList<>(capacity);
        this.isMinHeap = isMinHeap;
        System.out.println("✓ Created " + (isMinHeap ? "min" : "max") + "-heap with capacity " + capacity);
    }
    
    /**
     * Constructor to create a binary heap from an existing array.
     * Uses Floyd's algorithm for efficient heap construction in O(n) time.
     * 
     * @param array the array to convert to a heap
     * @param isMinHeap true for min-heap, false for max-heap
     */
    public BinaryHeap(T[] array, boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        this.heap = new ArrayList<>(Arrays.asList(array));
        buildHeap();
        System.out.println("✓ Built " + (isMinHeap ? "min" : "max") + "-heap from array with " + array.length + " elements");
    }
    
    // =====================================================
    // === CORE HEAP OPERATIONS ===
    // =====================================================
    
    /**
     * Insert a new element into the heap.
     * Maintains heap property by bubbling up the element.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param element the element to insert
     */
    public void insert(T element) {
        if (element == null) {
            System.out.println("✗ Cannot insert null element");
            return;
        }
        
        // Add element to the end of the heap
        heap.add(element);
        int index = heap.size() - 1;
        
        // Bubble up to maintain heap property
        heapifyUp(index);
        
        System.out.println("✓ Inserted " + element + " into " + (isMinHeap ? "min" : "max") + "-heap");
    }
    
    /**
     * Extract the root element (minimum for min-heap, maximum for max-heap).
     * Replaces root with last element and bubbles down.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @return the root element
     * @throws HeapUnderflowException if heap is empty
     */
    public T extractRoot() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot extract from empty heap");
        }
        
        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        
        // If heap is now empty, return the root
        if (heap.isEmpty()) {
            System.out.println("✓ Extracted " + root + " (heap now empty)");
            return root;
        }
        
        // Replace root with last element and heapify down
        heap.set(0, lastElement);
        heapifyDown(0);
        
        System.out.println("✓ Extracted " + root + " from " + (isMinHeap ? "min" : "max") + "-heap");
        return root;
    }
    
    /**
     * Peek at the root element without removing it.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the root element
     * @throws HeapUnderflowException if heap is empty
     */
    public T peek() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot peek empty heap");
        }
        return heap.get(0);
    }
    
    /**
     * Remove a specific element from the heap.
     * Finds the element and maintains heap property after removal.
     * 
     * Time Complexity: O(n) for search + O(log n) for heapify
     * Space Complexity: O(1)
     * 
     * @param element the element to remove
     * @return true if element was found and removed, false otherwise
     */
    public boolean remove(T element) {
        if (element == null) {
            System.out.println("✗ Cannot remove null element");
            return false;
        }
        
        // Find the element
        int index = heap.indexOf(element);
        if (index == -1) {
            System.out.println("✗ Element " + element + " not found in heap");
            return false;
        }
        
        // Replace with last element
        T lastElement = heap.remove(heap.size() - 1);
        
        // If we removed the last element, we're done
        if (index == heap.size()) {
            System.out.println("✓ Removed " + element + " from heap");
            return true;
        }
        
        // Replace the element at index with last element
        T oldElement = heap.set(index, lastElement);
        
        // Restore heap property - may need to bubble up or down
        if (shouldSwapWithParent(index)) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
        
        System.out.println("✓ Removed " + element + " from heap");
        return true;
    }
    
    // =====================================================
    // === HEAPIFY OPERATIONS ===
    // =====================================================
    
    /**
     * Bubble up an element to maintain heap property.
     * Used after insertion or when an element's priority increases.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param index the index of the element to bubble up
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            
            if (!shouldSwapWithParent(index)) {
                break;
            }
            
            // Swap with parent
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    
    /**
     * Bubble down an element to maintain heap property.
     * Used after extraction or when an element's priority decreases.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param index the index of the element to bubble down
     */
    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int targetIndex = getLeftChildIndex(index);
            
            // Find the child to swap with (left or right)
            if (hasRightChild(index) && shouldSwapChildren(index)) {
                targetIndex = getRightChildIndex(index);
            }
            
            // If heap property is satisfied, stop
            if (!shouldSwapWithChild(index, targetIndex)) {
                break;
            }
            
            // Swap with the chosen child
            swap(index, targetIndex);
            index = targetIndex;
        }
    }
    
    /**
     * Build a heap from an unordered array using Floyd's algorithm.
     * Starts from the last non-leaf node and heapifies down.
     * 
     * Time Complexity: O(n) - surprisingly better than O(n log n)
     * Space Complexity: O(1)
     */
    private void buildHeap() {
        // Start from the last non-leaf node
        for (int i = getParentIndex(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    // =====================================================
    // === UTILITY METHODS ===
    // =====================================================
    
    /**
     * Check if the heap is empty.
     * 
     * @return true if heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * Get the size of the heap.
     * 
     * @return the number of elements in the heap
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Clear all elements from the heap.
     */
    public void clear() {
        heap.clear();
        System.out.println("✓ Cleared all elements from heap");
    }
    
    /**
     * Check if the heap contains a specific element.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param element the element to search for
     * @return true if element is found, false otherwise
     */
    public boolean contains(T element) {
        return heap.contains(element);
    }
    
    /**
     * Get all elements in the heap as a list.
     * Note: This does not guarantee heap order.
     * 
     * @return a list containing all heap elements
     */
    public List<T> toList() {
        return new ArrayList<>(heap);
    }
    
    /**
     * Validate heap property for debugging.
     * 
     * @return true if heap property is maintained, false otherwise
     */
    public boolean isValidHeap() {
        for (int i = 0; i < heap.size(); i++) {
            if (!isValidHeapNode(i)) {
                return false;
            }
        }
        return true;
    }
    
    // =====================================================
    // === INDEX CALCULATION METHODS ===
    // =====================================================
    
    /**
     * Get the parent index of a given index.
     * 
     * @param index the child index
     * @return the parent index
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    
    /**
     * Get the left child index of a given index.
     * 
     * @param index the parent index
     * @return the left child index
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    
    /**
     * Get the right child index of a given index.
     * 
     * @param index the parent index
     * @return the right child index
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    
    /**
     * Check if a node has a left child.
     * 
     * @param index the node index
     * @return true if left child exists, false otherwise
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }
    
    /**
     * Check if a node has a right child.
     * 
     * @param index the node index
     * @return true if right child exists, false otherwise
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < heap.size();
    }
    
    // =====================================================
    // === COMPARISON HELPER METHODS ===
    // =====================================================
    
    /**
     * Check if an element should be swapped with its parent.
     * 
     * @param index the element index
     * @return true if swap is needed, false otherwise
     */
    private boolean shouldSwapWithParent(int index) {
        if (index == 0) return false; // Root has no parent
        
        int parentIndex = getParentIndex(index);
        return shouldSwap(heap.get(index), heap.get(parentIndex));
    }
    
    /**
     * Check if an element should be swapped with a specific child.
     * 
     * @param parentIndex the parent index
     * @param childIndex the child index
     * @return true if swap is needed, false otherwise
     */
    private boolean shouldSwapWithChild(int parentIndex, int childIndex) {
        return shouldSwap(heap.get(childIndex), heap.get(parentIndex));
    }
    
    /**
     * Check which child should be preferred for swapping.
     * 
     * @param parentIndex the parent index
     * @return true if right child should be preferred, false for left child
     */
    private boolean shouldSwapChildren(int parentIndex) {
        int leftIndex = getLeftChildIndex(parentIndex);
        int rightIndex = getRightChildIndex(parentIndex);
        return shouldSwap(heap.get(rightIndex), heap.get(leftIndex));
    }
    
    /**
     * Core comparison method that respects heap type.
     * 
     * @param a first element
     * @param b second element
     * @return true if 'a' should be higher in the heap than 'b'
     */
    private boolean shouldSwap(T a, T b) {
        int comparison = a.compareTo(b);
        return isMinHeap ? comparison < 0 : comparison > 0;
    }
    
    /**
     * Validate heap property for a specific node.
     * 
     * @param index the node index to validate
     * @return true if node satisfies heap property, false otherwise
     */
    private boolean isValidHeapNode(int index) {
        if (hasLeftChild(index)) {
            int leftIndex = getLeftChildIndex(index);
            if (shouldSwap(heap.get(leftIndex), heap.get(index))) {
                return false;
            }
        }
        
        if (hasRightChild(index)) {
            int rightIndex = getRightChildIndex(index);
            if (shouldSwap(heap.get(rightIndex), heap.get(index))) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Swap two elements in the heap.
     * 
     * @param i first index
     * @param j second index
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    // =====================================================
    // === DISPLAY METHODS ===
    // =====================================================
    
    /**
     * Print the heap in array format.
     */
    public void printHeap() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }
        
        System.out.println("Heap (" + (isMinHeap ? "min" : "max") + "-heap): " + heap);
        System.out.println("Size: " + size() + ", Root: " + heap.get(0));
    }
    
    /**
     * Print the heap in tree format for better visualization.
     */
    public void printTreeFormat() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }
        
        System.out.println("Heap Tree Structure (" + (isMinHeap ? "min" : "max") + "-heap):");
        printTreeFormatHelper(0, "", true);
    }
    
    /**
     * Helper method for tree format printing.
     * 
     * @param index current node index
     * @param prefix string prefix for indentation
     * @param isLast whether this is the last child
     */
    private void printTreeFormatHelper(int index, String prefix, boolean isLast) {
        if (index >= heap.size()) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + heap.get(index));
        
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        
        if (hasRightChild(index)) {
            printTreeFormatHelper(getRightChildIndex(index), childPrefix, !hasLeftChild(index));
        }
        
        if (hasLeftChild(index)) {
            printTreeFormatHelper(getLeftChildIndex(index), childPrefix, true);
        }
    }
    
    /**
     * Display heap statistics and properties.
     */
    public void displayStats() {
        System.out.println("\n=== Heap Statistics ===");
        System.out.println("Type: " + (isMinHeap ? "Min-Heap" : "Max-Heap"));
        System.out.println("Size: " + size());
        System.out.println("Empty: " + isEmpty());
        
        if (!isEmpty()) {
            System.out.println("Root: " + heap.get(0));
            System.out.println("Valid Heap: " + isValidHeap());
            
            // Calculate height
            int height = (int) Math.floor(Math.log(size()) / Math.log(2));
            System.out.println("Height: " + height);
            
            // Memory usage estimation
            int estimatedBytes = size() * 24; // Rough estimate for object references
            System.out.println("Estimated Memory: ~" + estimatedBytes + " bytes");
        }
        System.out.println("=======================\n");
    }
    
    // =====================================================
    // === INTERACTIVE MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive main method for testing and demonstration.
     * Provides a comprehensive menu system for heap operations.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryHeap<Integer> heap = null;
        
        System.out.println("🌟 Binary Heap Implementation Demo");
        System.out.println("===================================");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create Min-Heap");
            System.out.println("2. Create Max-Heap");
            System.out.println("3. Create Heap from Array");
            System.out.println("4. Insert Element");
            System.out.println("5. Extract Root");
            System.out.println("6. Peek Root");
            System.out.println("7. Remove Element");
            System.out.println("8. Check Contains");
            System.out.println("9. Print Heap (Array)");
            System.out.println("10. Print Tree Format");
            System.out.println("11. Display Statistics");
            System.out.println("12. Validate Heap");
            System.out.println("13. Clear Heap");
            System.out.println("14. Bulk Insert Demo");
            System.out.println("15. Performance Test");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    heap = new BinaryHeap<>(true);
                    break;
                    
                case 2:
                    heap = new BinaryHeap<>(false);
                    break;
                    
                case 3:
                    System.out.print("Enter array elements (space-separated): ");
                    scanner.nextLine(); // consume newline
                    String[] elements = scanner.nextLine().split(" ");
                    Integer[] array = new Integer[elements.length];
                    for (int i = 0; i < elements.length; i++) {
                        array[i] = Integer.parseInt(elements[i]);
                    }
                    System.out.print("Min-heap (m) or Max-heap (M)? ");
                    String type = scanner.next();
                    heap = new BinaryHeap<>(array, type.equalsIgnoreCase("m"));
                    break;
                    
                case 4:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    System.out.print("Enter element to insert: ");
                    int element = scanner.nextInt();
                    heap.insert(element);
                    break;
                    
                case 5:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    try {
                        Integer root = heap.extractRoot();
                        System.out.println("Extracted: " + root);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    try {
                        Integer root = heap.peek();
                        System.out.println("Root: " + root);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 7:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    System.out.print("Enter element to remove: ");
                    int removeElement = scanner.nextInt();
                    boolean removed = heap.remove(removeElement);
                    if (!removed) {
                        System.out.println("Element not found in heap");
                    }
                    break;
                    
                case 8:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    System.out.print("Enter element to search: ");
                    int searchElement = scanner.nextInt();
                    boolean contains = heap.contains(searchElement);
                    System.out.println("Contains " + searchElement + ": " + contains);
                    break;
                    
                case 9:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    heap.printHeap();
                    break;
                    
                case 10:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    heap.printTreeFormat();
                    break;
                    
                case 11:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    heap.displayStats();
                    break;
                    
                case 12:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    boolean valid = heap.isValidHeap();
                    System.out.println("Heap is " + (valid ? "valid" : "invalid"));
                    break;
                    
                case 13:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    heap.clear();
                    break;
                    
                case 14:
                    if (heap == null) {
                        System.out.println("Please create a heap first!");
                        break;
                    }
                    System.out.println("Bulk inserting: 15, 25, 5, 10, 30, 8, 20");
                    int[] bulkElements = {15, 25, 5, 10, 30, 8, 20};
                    for (int elem : bulkElements) {
                        heap.insert(elem);
                    }
                    heap.printTreeFormat();
                    break;
                    
                case 15:
                    System.out.println("Performance Test: Creating heap with 1000 elements");
                    BinaryHeap<Integer> testHeap = new BinaryHeap<>(true);
                    long startTime = System.nanoTime();
                    for (int i = 1000; i > 0; i--) {
                        testHeap.insert(i);
                    }
                    long endTime = System.nanoTime();
                    System.out.println("Insertion time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    
                    startTime = System.nanoTime();
                    while (!testHeap.isEmpty()) {
                        try {
                            testHeap.extractRoot();
                        } catch (HeapUnderflowException e) {
                            break;
                        }
                    }
                    endTime = System.nanoTime();
                    System.out.println("Extraction time: " + (endTime - startTime) / 1_000_000.0 + " ms");
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