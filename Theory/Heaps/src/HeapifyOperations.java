import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * HeapifyOperations class providing comprehensive heapify algorithms and strategies.
 * This class serves as an educational tool for understanding different heap manipulation
 * techniques including Floyd's algorithm, iterative and recursive heapification methods.
 * 
 * All methods are static utilities that can work with any array-based heap representation.
 * Educational implementation for DSA learning with detailed documentation and comparisons.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class HeapifyOperations {
    
    /**
     * Enum to represent heap types for consistency across operations.
     */
    public enum HeapType {
        MIN_HEAP, MAX_HEAP
    }
    
    // =====================================================
    // === CORE HEAPIFY ALGORITHMS ===
    // =====================================================
    
    /**
     * Floyd's Build-Heap algorithm - converts an arbitrary array into a heap.
     * This is the most efficient way to build a heap from an unsorted array.
     * 
     * Time Complexity: O(n) - surprisingly better than O(n log n)
     * Space Complexity: O(1) - in-place algorithm
     * 
     * @param array the array to heapify
     * @param heapType whether to build min-heap or max-heap
     * @param <T> the type of elements, must be comparable
     * 
     * @example
     * Integer[] arr = {4, 10, 3, 5, 1};
     * HeapifyOperations.buildHeap(arr, HeapType.MIN_HEAP);
     * // Result: [1, 4, 3, 5, 10] (min-heap)
     */
    public static <T extends Comparable<T>> void buildHeap(T[] array, HeapType heapType) {
        int n = array.length;
        if (n <= 1) {
            System.out.println("✓ Array size " + n + " - no heapification needed");
            return;
        }
        
        System.out.println("🔧 Building " + heapType + " using Floyd's algorithm...");
        System.out.println("Original array: " + Arrays.toString(array));
        
        // Start from the last non-leaf node and heapify down
        for (int i = getParentIndex(n - 1); i >= 0; i--) {
            heapifyDown(array, i, n, heapType);
        }
        
        System.out.println("✓ Heap built: " + Arrays.toString(array));
        System.out.println("Heapification complete - heap property satisfied");
    }
    
    /**
     * Heapify down operation - restores heap property by moving element downward.
     * Used after root extraction or when an element violates heap property.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param array the heap array
     * @param index starting index for heapification
     * @param heapSize current size of the heap
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void heapifyDown(T[] array, int index, 
                                                            int heapSize, HeapType heapType) {
        while (hasLeftChild(index, heapSize)) {
            int targetIndex = getLeftChildIndex(index);
            
            // Find the appropriate child to compare with
            if (hasRightChild(index, heapSize) && 
                shouldSwap(array[getRightChildIndex(index)], array[targetIndex], heapType)) {
                targetIndex = getRightChildIndex(index);
            }
            
            // If heap property is satisfied, stop
            if (!shouldSwap(array[targetIndex], array[index], heapType)) {
                break;
            }
            
            // Swap and continue
            swap(array, index, targetIndex);
            index = targetIndex;
        }
    }
    
    /**
     * Heapify up operation - restores heap property by moving element upward.
     * Used after insertion when a new element might violate heap property.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param array the heap array
     * @param index starting index for heapification
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void heapifyUp(T[] array, int index, HeapType heapType) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            
            if (!shouldSwap(array[index], array[parentIndex], heapType)) {
                break;
            }
            
            swap(array, index, parentIndex);
            index = parentIndex;
        }
    }
    
    /**
     * Recursive heapify down implementation for educational comparison.
     * Same functionality as iterative version but uses recursion.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack
     * 
     * @param array the heap array
     * @param index starting index for heapification
     * @param heapSize current size of the heap
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void heapifyDownRecursive(T[] array, int index, 
                                                                     int heapSize, HeapType heapType) {
        int targetIndex = index;
        
        // Check left child
        if (hasLeftChild(index, heapSize)) {
            int leftIndex = getLeftChildIndex(index);
            if (shouldSwap(array[leftIndex], array[targetIndex], heapType)) {
                targetIndex = leftIndex;
            }
        }
        
        // Check right child
        if (hasRightChild(index, heapSize)) {
            int rightIndex = getRightChildIndex(index);
            if (shouldSwap(array[rightIndex], array[targetIndex], heapType)) {
                targetIndex = rightIndex;
            }
        }
        
        // If we found a better candidate, swap and recurse
        if (targetIndex != index) {
            swap(array, index, targetIndex);
            heapifyDownRecursive(array, targetIndex, heapSize, heapType);
        }
    }
    
    // =====================================================
    // === SPECIALIZED HEAPIFY OPERATIONS ===
    // =====================================================
    
    /**
     * Partial heapify - heapifies only a portion of the array.
     * Useful for heap-based algorithms that work on subarrays.
     * 
     * Time Complexity: O(k log k) where k is the range size
     * Space Complexity: O(1)
     * 
     * @param array the array to partially heapify
     * @param start starting index (inclusive)
     * @param end ending index (exclusive)
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void partialHeapify(T[] array, int start, 
                                                               int end, HeapType heapType) {
        if (start >= end - 1) {
            System.out.println("✓ Range [" + start + ", " + end + ") too small for heapification");
            return;
        }
        
        System.out.println("🔧 Partial heapify on range [" + start + ", " + end + ")");
        System.out.println("Subarray before: " + Arrays.toString(
            Arrays.copyOfRange(array, start, end)));
        
        // Adjust indices for the subarray and heapify
        int size = end - start;
        for (int i = start + getParentIndex(size - 1); i >= start; i--) {
            partialHeapifyDown(array, i, start, end, heapType);
        }
        
        System.out.println("✓ Subarray after: " + Arrays.toString(
            Arrays.copyOfRange(array, start, end)));
    }
    
    /**
     * Helper method for partial heapify down operation.
     * 
     * @param array the array
     * @param index current index
     * @param start start of the heap range
     * @param end end of the heap range
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    private static <T extends Comparable<T>> void partialHeapifyDown(T[] array, int index, 
                                                                    int start, int end, HeapType heapType) {
        while (hasLeftChildInRange(index, start, end)) {
            int leftIndex = getLeftChildIndex(index - start) + start;
            int targetIndex = leftIndex;
            
            // Check right child if it exists
            if (hasRightChildInRange(index, start, end)) {
                int rightIndex = getRightChildIndex(index - start) + start;
                if (shouldSwap(array[rightIndex], array[targetIndex], heapType)) {
                    targetIndex = rightIndex;
                }
            }
            
            // If heap property is satisfied, stop
            if (!shouldSwap(array[targetIndex], array[index], heapType)) {
                break;
            }
            
            swap(array, index, targetIndex);
            index = targetIndex;
        }
    }
    
    /**
     * Heap sort implementation using heapify operations.
     * Demonstrates practical application of heap operations.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     * 
     * @param array the array to sort
     * @param ascending true for ascending order, false for descending
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void heapSort(T[] array, boolean ascending) {
        int n = array.length;
        if (n <= 1) {
            System.out.println("✓ Array size " + n + " - no sorting needed");
            return;
        }
        
        System.out.println("🔧 Heap Sort (" + (ascending ? "ascending" : "descending") + ")");
        System.out.println("Original: " + Arrays.toString(array));
        
        // Build heap (max-heap for ascending, min-heap for descending)
        HeapType buildType = ascending ? HeapType.MAX_HEAP : HeapType.MIN_HEAP;
        buildHeap(array, buildType);
        
        System.out.println("After build-heap: " + Arrays.toString(array));
        
        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(array, 0, i);
            
            // Heapify the reduced heap
            heapifyDown(array, 0, i, buildType);
        }
        
        System.out.println("✓ Sorted: " + Arrays.toString(array));
    }
    
    /**
     * Fix heap property starting from a specific node.
     * Useful when you know exactly which node might violate heap property.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param array the heap array
     * @param index the index that might violate heap property
     * @param heapSize current heap size
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     */
    public static <T extends Comparable<T>> void fixHeapProperty(T[] array, int index, 
                                                                int heapSize, HeapType heapType) {
        System.out.println("🔧 Fixing heap property at index " + index);
        
        // Try heapifying up first
        int originalIndex = index;
        heapifyUp(array, index, heapType);
        
        // If element moved up, we're done
        // Otherwise, try heapifying down
        if (index == originalIndex) {
            heapifyDown(array, index, heapSize, heapType);
        }
        
        System.out.println("✓ Heap property fixed");
    }
    
    // =====================================================
    // === VALIDATION AND ANALYSIS ===
    // =====================================================
    
    /**
     * Validate if an array satisfies heap property.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param array the array to validate
     * @param heapSize the size of the heap
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     * @return true if heap property is satisfied
     */
    public static <T extends Comparable<T>> boolean isValidHeap(T[] array, int heapSize, 
                                                               HeapType heapType) {
        for (int i = 0; i < heapSize; i++) {
            if (!isValidHeapNode(array, i, heapSize, heapType)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if a specific node satisfies heap property.
     * 
     * @param array the heap array
     * @param index the node index to check
     * @param heapSize the heap size
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     * @return true if node satisfies heap property
     */
    private static <T extends Comparable<T>> boolean isValidHeapNode(T[] array, int index, 
                                                                   int heapSize, HeapType heapType) {
        // Check left child
        if (hasLeftChild(index, heapSize)) {
            int leftIndex = getLeftChildIndex(index);
            if (shouldSwap(array[leftIndex], array[index], heapType)) {
                return false;
            }
        }
        
        // Check right child
        if (hasRightChild(index, heapSize)) {
            int rightIndex = getRightChildIndex(index);
            if (shouldSwap(array[rightIndex], array[index], heapType)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Find all violations of heap property in an array.
     * Useful for debugging and educational purposes.
     * 
     * @param array the array to analyze
     * @param heapSize the heap size
     * @param heapType min-heap or max-heap
     * @param <T> the type of elements
     * @return list of indices that violate heap property
     */
    public static <T extends Comparable<T>> List<Integer> findHeapViolations(T[] array, 
                                                                           int heapSize, HeapType heapType) {
        List<Integer> violations = new ArrayList<>();
        
        for (int i = 0; i < heapSize; i++) {
            if (!isValidHeapNode(array, i, heapSize, heapType)) {
                violations.add(i);
            }
        }
        
        return violations;
    }
    
    // =====================================================
    // === UTILITY METHODS ===
    // =====================================================
    
    /**
     * Get parent index for a given child index.
     */
    private static int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }
    
    /**
     * Get left child index for a given parent index.
     */
    private static int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
    
    /**
     * Get right child index for a given parent index.
     */
    private static int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }
    
    /**
     * Check if a node has a left child.
     */
    private static boolean hasLeftChild(int index, int heapSize) {
        return getLeftChildIndex(index) < heapSize;
    }
    
    /**
     * Check if a node has a right child.
     */
    private static boolean hasRightChild(int index, int heapSize) {
        return getRightChildIndex(index) < heapSize;
    }
    
    /**
     * Check if a node has a left child within a specific range.
     */
    private static boolean hasLeftChildInRange(int index, int start, int end) {
        int leftIndex = getLeftChildIndex(index - start) + start;
        return leftIndex < end;
    }
    
    /**
     * Check if a node has a right child within a specific range.
     */
    private static boolean hasRightChildInRange(int index, int start, int end) {
        int rightIndex = getRightChildIndex(index - start) + start;
        return rightIndex < end;
    }
    
    /**
     * Determine if element 'a' should be swapped with element 'b' based on heap type.
     */
    private static <T extends Comparable<T>> boolean shouldSwap(T a, T b, HeapType heapType) {
        int comparison = a.compareTo(b);
        return heapType == HeapType.MIN_HEAP ? comparison < 0 : comparison > 0;
    }
    
    /**
     * Swap two elements in an array.
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // =====================================================
    // === DISPLAY AND ANALYSIS METHODS ===
    // =====================================================
    
    /**
     * Print array in tree format for visualization.
     */
    public static <T> void printAsTree(T[] array, int heapSize) {
        if (heapSize == 0) {
            System.out.println("Empty heap");
            return;
        }
        
        System.out.println("Heap Tree Structure:");
        printTreeHelper(array, 0, heapSize, "", true);
    }
    
    /**
     * Helper method for tree printing.
     */
    private static <T> void printTreeHelper(T[] array, int index, int heapSize, 
                                          String prefix, boolean isLast) {
        if (index >= heapSize) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + array[index]);
        
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        
        if (hasRightChild(index, heapSize)) {
            printTreeHelper(array, getRightChildIndex(index), heapSize, childPrefix, 
                          !hasLeftChild(index, heapSize));
        }
        
        if (hasLeftChild(index, heapSize)) {
            printTreeHelper(array, getLeftChildIndex(index), heapSize, childPrefix, true);
        }
    }
    
    /**
     * Analyze heap characteristics and display statistics.
     */
    public static <T extends Comparable<T>> void analyzeHeap(T[] array, int heapSize, 
                                                           HeapType heapType) {
        System.out.println("\n=== Heap Analysis ===");
        System.out.println("Type: " + heapType);
        System.out.println("Size: " + heapSize);
        System.out.println("Array: " + Arrays.toString(Arrays.copyOf(array, heapSize)));
        
        if (heapSize > 0) {
            System.out.println("Root: " + array[0]);
            
            boolean valid = isValidHeap(array, heapSize, heapType);
            System.out.println("Valid Heap: " + valid);
            
            if (!valid) {
                List<Integer> violations = findHeapViolations(array, heapSize, heapType);
                System.out.println("Violations at indices: " + violations);
            }
            
            int height = (int) Math.floor(Math.log(heapSize) / Math.log(2));
            System.out.println("Height: " + height);
            
            // Find min and max elements
            T min = array[0], max = array[0];
            for (int i = 1; i < heapSize; i++) {
                if (array[i].compareTo(min) < 0) min = array[i];
                if (array[i].compareTo(max) > 0) max = array[i];
            }
            System.out.println("Min element: " + min);
            System.out.println("Max element: " + max);
        }
        System.out.println("====================\n");
    }
    
    // =====================================================
    // === INTERACTIVE MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive main method for testing and demonstration.
     * Provides comprehensive menu for heap operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] heap = null;
        int heapSize = 0;
        HeapType currentType = HeapType.MIN_HEAP;
        
        System.out.println("🔧 Heapify Operations Demo");
        System.out.println("=========================");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create array from input");
            System.out.println("2. Build heap (Floyd's algorithm)");
            System.out.println("3. Heapify down at index");
            System.out.println("4. Heapify up at index");
            System.out.println("5. Recursive heapify down");
            System.out.println("6. Partial heapify");
            System.out.println("7. Heap sort");
            System.out.println("8. Fix heap property");
            System.out.println("9. Validate heap");
            System.out.println("10. Find violations");
            System.out.println("11. Print as tree");
            System.out.println("12. Analyze heap");
            System.out.println("13. Switch heap type");
            System.out.println("14. Performance comparison");
            System.out.println("15. Demo all operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter array elements (space-separated): ");
                    scanner.nextLine(); // consume newline
                    String[] elements = scanner.nextLine().split(" ");
                    heap = new Integer[elements.length];
                    for (int i = 0; i < elements.length; i++) {
                        heap[i] = Integer.parseInt(elements[i]);
                    }
                    heapSize = heap.length;
                    System.out.println("✓ Array created: " + Arrays.toString(heap));
                    break;
                    
                case 2:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    buildHeap(heap, currentType);
                    break;
                    
                case 3:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Enter index to heapify down: ");
                    int downIndex = scanner.nextInt();
                    if (downIndex >= 0 && downIndex < heapSize) {
                        heapifyDown(heap, downIndex, heapSize, currentType);
                        System.out.println("✓ Result: " + Arrays.toString(Arrays.copyOf(heap, heapSize)));
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                    
                case 4:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Enter index to heapify up: ");
                    int upIndex = scanner.nextInt();
                    if (upIndex >= 0 && upIndex < heapSize) {
                        heapifyUp(heap, upIndex, currentType);
                        System.out.println("✓ Result: " + Arrays.toString(Arrays.copyOf(heap, heapSize)));
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                    
                case 5:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Enter index for recursive heapify down: ");
                    int recIndex = scanner.nextInt();
                    if (recIndex >= 0 && recIndex < heapSize) {
                        heapifyDownRecursive(heap, recIndex, heapSize, currentType);
                        System.out.println("✓ Result: " + Arrays.toString(Arrays.copyOf(heap, heapSize)));
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                    
                case 6:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Enter start index: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter end index (exclusive): ");
                    int end = scanner.nextInt();
                    if (start >= 0 && end <= heapSize && start < end) {
                        partialHeapify(heap, start, end, currentType);
                    } else {
                        System.out.println("Invalid range!");
                    }
                    break;
                    
                case 7:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Ascending order (y/n)? ");
                    boolean ascending = scanner.next().toLowerCase().startsWith("y");
                    heapSort(Arrays.copyOf(heap, heapSize), ascending);
                    break;
                    
                case 8:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    System.out.print("Enter index to fix: ");
                    int fixIndex = scanner.nextInt();
                    if (fixIndex >= 0 && fixIndex < heapSize) {
                        fixHeapProperty(heap, fixIndex, heapSize, currentType);
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                    
                case 9:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    boolean valid = isValidHeap(heap, heapSize, currentType);
                    System.out.println("Heap is " + (valid ? "valid" : "invalid"));
                    break;
                    
                case 10:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    List<Integer> violations = findHeapViolations(heap, heapSize, currentType);
                    if (violations.isEmpty()) {
                        System.out.println("✓ No heap property violations found");
                    } else {
                        System.out.println("✗ Violations at indices: " + violations);
                    }
                    break;
                    
                case 11:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    printAsTree(heap, heapSize);
                    break;
                    
                case 12:
                    if (heap == null) {
                        System.out.println("Please create an array first!");
                        break;
                    }
                    analyzeHeap(heap, heapSize, currentType);
                    break;
                    
                case 13:
                    currentType = (currentType == HeapType.MIN_HEAP) ? 
                                 HeapType.MAX_HEAP : HeapType.MIN_HEAP;
                    System.out.println("✓ Switched to " + currentType);
                    break;
                    
                case 14:
                    System.out.println("Performance Comparison: Iterative vs Recursive Heapify");
                    Integer[] testArray = {50, 30, 20, 15, 10, 8, 16, 40, 25, 12};
                    
                    // Test iterative
                    Integer[] iter = Arrays.copyOf(testArray, testArray.length);
                    long startTime = System.nanoTime();
                    for (int i = 0; i < 1000; i++) {
                        heapifyDown(iter, 0, iter.length, HeapType.MAX_HEAP);
                    }
                    long iterTime = System.nanoTime() - startTime;
                    
                    // Test recursive
                    Integer[] rec = Arrays.copyOf(testArray, testArray.length);
                    startTime = System.nanoTime();
                    for (int i = 0; i < 1000; i++) {
                        heapifyDownRecursive(rec, 0, rec.length, HeapType.MAX_HEAP);
                    }
                    long recTime = System.nanoTime() - startTime;
                    
                    System.out.println("Iterative: " + (iterTime / 1_000_000.0) + " ms");
                    System.out.println("Recursive: " + (recTime / 1_000_000.0) + " ms");
                    break;
                    
                case 15:
                    System.out.println("🎭 Demo: All Heapify Operations");
                    Integer[] demo = {4, 10, 3, 5, 1, 6, 8, 2, 9, 7};
                    System.out.println("Demo array: " + Arrays.toString(demo));
                    
                    buildHeap(demo, HeapType.MIN_HEAP);
                    printAsTree(demo, demo.length);
                    analyzeHeap(demo, demo.length, HeapType.MIN_HEAP);
                    heapSort(Arrays.copyOf(demo, demo.length), true);
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