import exceptions.HeapUnderflowException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * FibonacciHeap implementation with optimal amortized time complexities.
 * This is the most advanced heap data structure, providing excellent performance
 * for decrease-key and delete operations through lazy deletion and cascading cuts.
 * 
 * Key Features:
 * - Insert: O(1) amortized
 * - Extract-min: O(log n) amortized
 * - Union: O(1) amortized
 * - Decrease-key: O(1) amortized
 * - Delete: O(log n) amortized
 * 
 * Applications:
 * - Dijkstra's shortest path algorithm
 * - Prim's minimum spanning tree
 * - Other graph algorithms requiring efficient decrease-key
 * 
 * Implementation uses:
 * - Circular doubly-linked lists for root list and child lists
 * - Lazy deletion with consolidation during extract-min
 * - Cascading cuts to maintain amortized bounds
 * - Fibonacci sequence properties for tree degrees
 * 
 * Educational implementation for DSA learning with detailed documentation.
 * 
 * @param <T> the type of elements stored in the heap, must be comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class FibonacciHeap<T extends Comparable<T>> {
    
    /**
     * Node class representing a node in the Fibonacci heap.
     * Uses circular doubly-linked list structure for efficient operations.
     */
    public class FibonacciNode {
        T data;
        FibonacciNode parent;
        FibonacciNode child;       // One of the children
        FibonacciNode left;        // Left sibling in circular list
        FibonacciNode right;       // Right sibling in circular list
        int degree;               // Number of children
        boolean marked;           // Whether node has lost a child since becoming child of another
        
        /**
         * Create a new Fibonacci node with given data.
         */
        public FibonacciNode(T data) {
            this.data = data;
            this.parent = null;
            this.child = null;
            this.left = this;      // Initially points to itself (circular)
            this.right = this;     // Initially points to itself (circular)
            this.degree = 0;
            this.marked = false;
        }
        
        @Override
        public String toString() {
            return data.toString() + "(d:" + degree + (marked ? ",M" : "") + ")";
        }
    }
    
    /** Minimum node in the heap */
    private FibonacciNode minNode;
    
    /** Number of nodes in the heap */
    private int size;
    
    /** Map to track nodes for decrease-key and delete operations */
    private Map<T, FibonacciNode> nodeMap;
    
    /** Golden ratio for degree bounds */
    private static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;
    
    /**
     * Create an empty Fibonacci heap.
     */
    public FibonacciHeap() {
        this.minNode = null;
        this.size = 0;
        this.nodeMap = new HashMap<>();
        System.out.println("✓ Created empty Fibonacci heap");
    }
    
    /**
     * Create a Fibonacci heap with a single element.
     */
    public FibonacciHeap(T data) {
        this();
        insert(data);
    }
    
    // =====================================================
    // === CORE FIBONACCI HEAP OPERATIONS ===
    // =====================================================
    
    /**
     * Insert a new element into the Fibonacci heap.
     * Simply adds to root list - no consolidation needed.
     * 
     * Time Complexity: O(1) amortized
     * Space Complexity: O(1)
     * 
     * @param data the element to insert
     */
    public void insert(T data) {
        if (data == null) {
            System.out.println("✗ Cannot insert null element");
            return;
        }
        
        FibonacciNode newNode = new FibonacciNode(data);
        nodeMap.put(data, newNode);
        
        if (minNode == null) {
            minNode = newNode;
        } else {
            // Add to root list
            addToRootList(newNode);
            
            // Update minimum if necessary
            if (newNode.data.compareTo(minNode.data) < 0) {
                minNode = newNode;
            }
        }
        
        size++;
        System.out.println("✓ Inserted " + data + " into Fibonacci heap (size: " + size + ")");
    }
    
    /**
     * Extract the minimum element from the Fibonacci heap.
     * Performs consolidation to maintain heap structure.
     * 
     * Time Complexity: O(log n) amortized
     * Space Complexity: O(1)
     * 
     * @return the minimum element
     * @throws HeapUnderflowException if heap is empty
     */
    public T extractMin() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot extract from empty Fibonacci heap");
        }
        
        FibonacciNode min = minNode;
        T minData = min.data;
        
        // Add all children of min to root list
        if (min.child != null) {
            FibonacciNode child = min.child;
            do {
                FibonacciNode nextChild = child.right;
                child.parent = null;
                addToRootList(child);
                child = nextChild;
            } while (child != min.child);
        }
        
        // Remove min from root list
        removeFromRootList(min);
        nodeMap.remove(minData);
        
        if (min == min.right) {
            // Heap had only one node
            minNode = null;
        } else {
            minNode = min.right;
            consolidate();
        }
        
        size--;
        System.out.println("✓ Extracted minimum " + minData + " (remaining: " + size + ")");
        return minData;
    }
    
    /**
     * Get the minimum element without removing it.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the minimum element
     * @throws HeapUnderflowException if heap is empty
     */
    public T getMin() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot get minimum from empty heap");
        }
        return minNode.data;
    }
    
    /**
     * Union this heap with another Fibonacci heap.
     * Simply concatenates root lists - no consolidation needed.
     * 
     * Time Complexity: O(1) amortized
     * Space Complexity: O(1)
     * 
     * @param other the other Fibonacci heap to union with
     */
    public void union(FibonacciHeap<T> other) {
        if (other == null || other.isEmpty()) {
            return;
        }
        
        if (this.isEmpty()) {
            this.minNode = other.minNode;
            this.size = other.size;
            this.nodeMap.putAll(other.nodeMap);
        } else {
            // Concatenate root lists
            concatenateRootLists(this.minNode, other.minNode);
            
            // Update minimum
            if (other.minNode.data.compareTo(this.minNode.data) < 0) {
                this.minNode = other.minNode;
            }
            
            this.size += other.size;
            this.nodeMap.putAll(other.nodeMap);
        }
        
        // Clear other heap
        other.minNode = null;
        other.size = 0;
        other.nodeMap.clear();
        
        System.out.println("✓ Union completed (total size: " + size + ")");
    }
    
    /**
     * Decrease the key of a given element.
     * Uses cascading cuts to maintain amortized bounds.
     * 
     * Time Complexity: O(1) amortized
     * Space Complexity: O(1)
     * 
     * @param oldData the current data value
     * @param newData the new data value (must be smaller)
     */
    public void decreaseKey(T oldData, T newData) {
        if (oldData == null || newData == null) {
            System.out.println("✗ Invalid data for decrease key");
            return;
        }
        
        if (newData.compareTo(oldData) >= 0) {
            System.out.println("✗ New key must be smaller than current key");
            return;
        }
        
        FibonacciNode node = nodeMap.get(oldData);
        if (node == null) {
            System.out.println("✗ Element not found in heap");
            return;
        }
        
        // Update node data and map
        node.data = newData;
        nodeMap.remove(oldData);
        nodeMap.put(newData, node);
        
        FibonacciNode parent = node.parent;
        
        // If heap property is violated, cut the node
        if (parent != null && node.data.compareTo(parent.data) < 0) {
            cut(node, parent);
            cascadingCut(parent);
        }
        
        // Update minimum if necessary
        if (node.data.compareTo(minNode.data) < 0) {
            minNode = node;
        }
        
        System.out.println("✓ Decreased key from " + oldData + " to " + newData);
    }
    
    /**
     * Delete a given element from the Fibonacci heap.
     * Uses decrease-key to negative infinity, then extract-min.
     * 
     * Time Complexity: O(log n) amortized
     * Space Complexity: O(1)
     * 
     * @param data the element to delete
     */
    public void delete(T data) {
        if (data == null) {
            System.out.println("✗ Cannot delete null element");
            return;
        }
        
        FibonacciNode node = nodeMap.get(data);
        if (node == null) {
            System.out.println("✗ Element not found in heap");
            return;
        }
        
        // Decrease to negative infinity (conceptually)
        // In practice, move to root and mark as minimum
        FibonacciNode parent = node.parent;
        if (parent != null) {
            cut(node, parent);
            cascadingCut(parent);
        }
        
        // Make this node the minimum
        minNode = node;
        
        // Extract minimum
        try {
            extractMin();
            System.out.println("✓ Deleted element " + data);
        } catch (HeapUnderflowException e) {
            System.out.println("✗ Error deleting element: " + e.getMessage());
        }
    }
    
    // =====================================================
    // === UTILITY OPERATIONS ===
    // =====================================================
    
    /**
     * Check if the heap is empty.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Get the size of the heap.
     * 
     * @return number of elements in the heap
     */
    public int size() {
        return size;
    }
    
    /**
     * Clear all elements from the heap.
     */
    public void clear() {
        minNode = null;
        size = 0;
        nodeMap.clear();
        System.out.println("✓ Cleared Fibonacci heap");
    }
    
    /**
     * Check if heap contains a specific element.
     * 
     * @param data the element to search for
     * @return true if element is found
     */
    public boolean contains(T data) {
        return nodeMap.containsKey(data);
    }
    
    // =====================================================
    // === HELPER METHODS ===
    // =====================================================
    
    /**
     * Add a node to the root list.
     */
    private void addToRootList(FibonacciNode node) {
        if (minNode == null) {
            minNode = node;
            node.left = node.right = node;
        } else {
            node.left = minNode;
            node.right = minNode.right;
            minNode.right.left = node;
            minNode.right = node;
        }
    }
    
    /**
     * Remove a node from the root list.
     */
    private void removeFromRootList(FibonacciNode node) {
        if (node.right == node) {
            // Only one node in root list
            return;
        }
        
        node.left.right = node.right;
        node.right.left = node.left;
    }
    
    /**
     * Concatenate two circular root lists.
     */
    private void concatenateRootLists(FibonacciNode list1, FibonacciNode list2) {
        FibonacciNode temp = list1.right;
        list1.right = list2.right;
        list2.right.left = list1;
        list2.right = temp;
        temp.left = list2;
    }
    
    /**
     * Consolidate trees to ensure no two trees have same degree.
     * This is the core operation that maintains logarithmic bound.
     */
    private void consolidate() {
        int maxDegree = (int) Math.floor(Math.log(size) / Math.log(GOLDEN_RATIO)) + 1;
        @SuppressWarnings("unchecked")
        FibonacciNode[] degreeTable = (FibonacciNode[]) new Object[maxDegree + 1];
        
        // Create list of root nodes
        List<FibonacciNode> rootNodes = new ArrayList<>();
        FibonacciNode current = minNode;
        if (current != null) {
            do {
                rootNodes.add(current);
                current = current.right;
            } while (current != minNode);
        }
        
        // Process each root node
        for (FibonacciNode node : rootNodes) {
            int degree = node.degree;
            
            while (degreeTable[degree] != null) {
                FibonacciNode other = degreeTable[degree];
                
                // Make sure node has smaller key
                if (node.data.compareTo(other.data) > 0) {
                    FibonacciNode temp = node;
                    node = other;
                    other = temp;
                }
                
                // Make other a child of node
                linkTrees(node, other);
                degreeTable[degree] = null;
                degree++;
            }
            
            degreeTable[degree] = node;
        }
        
        // Find new minimum
        minNode = null;
        for (FibonacciNode node : degreeTable) {
            if (node != null) {
                if (minNode == null || node.data.compareTo(minNode.data) < 0) {
                    minNode = node;
                }
            }
        }
    }
    
    /**
     * Link two trees by making child a child of parent.
     */
    private void linkTrees(FibonacciNode parent, FibonacciNode child) {
        // Remove child from root list
        removeFromRootList(child);
        
        // Add child to parent's child list
        if (parent.child == null) {
            parent.child = child;
            child.left = child.right = child;
        } else {
            child.left = parent.child;
            child.right = parent.child.right;
            parent.child.right.left = child;
            parent.child.right = child;
        }
        
        child.parent = parent;
        parent.degree++;
        child.marked = false;
    }
    
    /**
     * Cut a node from its parent and add to root list.
     */
    private void cut(FibonacciNode node, FibonacciNode parent) {
        // Remove node from parent's child list
        if (node.right == node) {
            parent.child = null;
        } else {
            if (parent.child == node) {
                parent.child = node.right;
            }
            node.left.right = node.right;
            node.right.left = node.left;
        }
        
        parent.degree--;
        
        // Add node to root list
        addToRootList(node);
        node.parent = null;
        node.marked = false;
    }
    
    /**
     * Perform cascading cut to maintain amortized bounds.
     */
    private void cascadingCut(FibonacciNode node) {
        FibonacciNode parent = node.parent;
        
        if (parent != null) {
            if (!node.marked) {
                node.marked = true;
            } else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }
    
    // =====================================================
    // === ANALYSIS AND VALIDATION ===
    // =====================================================
    
    /**
     * Validate Fibonacci heap properties.
     * 
     * @return true if all properties are satisfied
     */
    public boolean isValidFibonacciHeap() {
        return validateHeapProperty() && validateDegreeProperty() && validateMarkProperty();
    }
    
    /**
     * Validate heap property for all nodes.
     */
    private boolean validateHeapProperty() {
        return validateHeapPropertyHelper(minNode);
    }
    
    private boolean validateHeapPropertyHelper(FibonacciNode node) {
        if (node == null) return true;
        
        // Check all children
        if (node.child != null) {
            FibonacciNode child = node.child;
            do {
                if (node.data.compareTo(child.data) > 0) {
                    return false;
                }
                if (!validateHeapPropertyHelper(child)) {
                    return false;
                }
                child = child.right;
            } while (child != node.child);
        }
        
        return true;
    }
    
    /**
     * Validate degree property (each node's degree matches its children count).
     */
    private boolean validateDegreeProperty() {
        return validateDegreePropertyHelper(minNode);
    }
    
    private boolean validateDegreePropertyHelper(FibonacciNode node) {
        if (node == null) return true;
        
        // Count children
        int childCount = 0;
        if (node.child != null) {
            FibonacciNode child = node.child;
            do {
                childCount++;
                if (!validateDegreePropertyHelper(child)) {
                    return false;
                }
                child = child.right;
            } while (child != node.child);
        }
        
        return childCount == node.degree;
    }
    
    /**
     * Validate marking property (nodes can have at most one marked child).
     */
    private boolean validateMarkProperty() {
        return validateMarkPropertyHelper(minNode);
    }
    
    private boolean validateMarkPropertyHelper(FibonacciNode node) {
        if (node == null) return true;
        
        // Root nodes should not be marked
        if (node.parent == null && node.marked) {
            return false;
        }
        
        // Check children
        if (node.child != null) {
            FibonacciNode child = node.child;
            do {
                if (!validateMarkPropertyHelper(child)) {
                    return false;
                }
                child = child.right;
            } while (child != node.child);
        }
        
        return true;
    }
    
    // =====================================================
    // === DISPLAY METHODS ===
    // =====================================================
    
    /**
     * Print the structure of the Fibonacci heap.
     */
    public void printHeap() {
        if (isEmpty()) {
            System.out.println("Fibonacci heap is empty");
            return;
        }
        
        System.out.println("Fibonacci Heap Structure:");
        System.out.println("Size: " + size + ", Min: " + minNode.data);
        
        System.out.println("\nRoot List:");
        FibonacciNode current = minNode;
        int rootIndex = 0;
        do {
            System.out.println("Root " + rootIndex + ": " + current);
            printSubtree(current, "  ");
            current = current.right;
            rootIndex++;
        } while (current != minNode);
    }
    
    /**
     * Print a subtree rooted at given node.
     */
    private void printSubtree(FibonacciNode root, String indent) {
        if (root.child == null) return;
        
        FibonacciNode child = root.child;
        do {
            System.out.println(indent + "└─ " + child);
            printSubtree(child, indent + "   ");
            child = child.right;
        } while (child != root.child);
    }
    
    /**
     * Display heap statistics and properties.
     */
    public void displayStats() {
        System.out.println("\n=== Fibonacci Heap Statistics ===");
        System.out.println("Size: " + size);
        System.out.println("Empty: " + isEmpty());
        
        if (!isEmpty()) {
            System.out.println("Minimum: " + minNode.data);
            
            // Count trees in root list
            int treeCount = 0;
            int totalMarked = 0;
            FibonacciNode current = minNode;
            do {
                treeCount++;
                totalMarked += countMarkedNodes(current);
                current = current.right;
            } while (current != minNode);
            
            System.out.println("Number of trees: " + treeCount);
            System.out.println("Total marked nodes: " + totalMarked);
            System.out.println("Valid Fibonacci heap: " + isValidFibonacciHeap());
            
            // Theoretical maximum degree
            int maxDegree = (int) Math.floor(Math.log(size) / Math.log(GOLDEN_RATIO));
            System.out.println("Theoretical max degree: " + maxDegree);
        }
        System.out.println("=================================\n");
    }
    
    /**
     * Count marked nodes in a subtree.
     */
    private int countMarkedNodes(FibonacciNode root) {
        if (root == null) return 0;
        
        int count = root.marked ? 1 : 0;
        
        if (root.child != null) {
            FibonacciNode child = root.child;
            do {
                count += countMarkedNodes(child);
                child = child.right;
            } while (child != root.child);
        }
        
        return count;
    }
    
    /**
     * Get all elements in the heap (for testing purposes).
     */
    public List<T> getAllElements() {
        List<T> elements = new ArrayList<>();
        if (!isEmpty()) {
            getAllElementsHelper(minNode, elements);
        }
        return elements;
    }
    
    private void getAllElementsHelper(FibonacciNode node, List<T> elements) {
        if (node == null) return;
        
        FibonacciNode current = node;
        do {
            elements.add(current.data);
            getAllElementsHelper(current.child, elements);
            current = current.right;
        } while (current != node);
    }
    
    // =====================================================
    // === INTERACTIVE MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive main method for testing and demonstration.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        
        System.out.println("🌟 Fibonacci Heap Implementation Demo");
        System.out.println("=====================================");
        System.out.println("Most advanced heap with optimal amortized complexities!");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert Element");
            System.out.println("2. Extract Minimum");
            System.out.println("3. Get Minimum");
            System.out.println("4. Union with Another Heap");
            System.out.println("5. Decrease Key");
            System.out.println("6. Delete Element");
            System.out.println("7. Check Contains");
            System.out.println("8. Print Heap Structure");
            System.out.println("9. Display Statistics");
            System.out.println("10. Validate Heap");
            System.out.println("11. Clear Heap");
            System.out.println("12. Bulk Insert Demo");
            System.out.println("13. Union Demo");
            System.out.println("14. Decrease Key Demo");
            System.out.println("15. Performance Test");
            System.out.println("16. Show All Elements");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter element to insert: ");
                    int element = scanner.nextInt();
                    heap.insert(element);
                    break;
                    
                case 2:
                    try {
                        Integer min = heap.extractMin();
                        System.out.println("Extracted minimum: " + min);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    try {
                        Integer min = heap.getMin();
                        System.out.println("Minimum element: " + min);
                    } catch (HeapUnderflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter elements for second heap (space-separated): ");
                    scanner.nextLine(); // consume newline
                    String[] elements = scanner.nextLine().split(" ");
                    FibonacciHeap<Integer> otherHeap = new FibonacciHeap<>();
                    for (String elem : elements) {
                        try {
                            otherHeap.insert(Integer.parseInt(elem.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("Skipping invalid element: " + elem);
                        }
                    }
                    heap.union(otherHeap);
                    break;
                    
                case 5:
                    System.out.print("Enter current element value: ");
                    int oldValue = scanner.nextInt();
                    System.out.print("Enter new smaller value: ");
                    int newValue = scanner.nextInt();
                    heap.decreaseKey(oldValue, newValue);
                    break;
                    
                case 6:
                    System.out.print("Enter element to delete: ");
                    int deleteElement = scanner.nextInt();
                    heap.delete(deleteElement);
                    break;
                    
                case 7:
                    System.out.print("Enter element to search: ");
                    int searchElement = scanner.nextInt();
                    boolean contains = heap.contains(searchElement);
                    System.out.println("Contains " + searchElement + ": " + contains);
                    break;
                    
                case 8:
                    heap.printHeap();
                    break;
                    
                case 9:
                    heap.displayStats();
                    break;
                    
                case 10:
                    boolean valid = heap.isValidFibonacciHeap();
                    System.out.println("Heap is " + (valid ? "valid" : "invalid"));
                    break;
                    
                case 11:
                    heap.clear();
                    break;
                    
                case 12:
                    System.out.println("🎭 Bulk Insert Demo");
                    int[] bulkElements = {20, 35, 8, 15, 42, 3, 18, 27, 11, 6};
                    System.out.println("Inserting: " + java.util.Arrays.toString(bulkElements));
                    for (int elem : bulkElements) {
                        heap.insert(elem);
                    }
                    heap.printHeap();
                    break;
                    
                case 13:
                    System.out.println("🔗 Union Demo");
                    FibonacciHeap<Integer> fibHeap1 = new FibonacciHeap<>();
                    FibonacciHeap<Integer> fibHeap2 = new FibonacciHeap<>();
                    
                    int[] heap1Elements = {5, 15, 25};
                    int[] heap2Elements = {10, 20, 30, 40};
                    
                    System.out.println("Heap 1 elements: " + java.util.Arrays.toString(heap1Elements));
                    for (int elem : heap1Elements) fibHeap1.insert(elem);
                    
                    System.out.println("Heap 2 elements: " + java.util.Arrays.toString(heap2Elements));
                    for (int elem : heap2Elements) fibHeap2.insert(elem);
                    
                    System.out.println("\nBefore union:");
                    System.out.println("Heap 1 size: " + fibHeap1.size());
                    System.out.println("Heap 2 size: " + fibHeap2.size());
                    
                    fibHeap1.union(fibHeap2);
                    System.out.println("\nAfter union:");
                    fibHeap1.printHeap();
                    break;
                    
                case 14:
                    System.out.println("🔻 Decrease Key Demo");
                    FibonacciHeap<Integer> decreaseDemo = new FibonacciHeap<>();
                    int[] demoElements = {50, 30, 70, 20, 40, 60, 80};
                    
                    System.out.println("Inserting: " + java.util.Arrays.toString(demoElements));
                    for (int elem : demoElements) {
                        decreaseDemo.insert(elem);
                    }
                    
                    System.out.println("Before decrease key:");
                    decreaseDemo.printHeap();
                    
                    System.out.println("Decreasing 70 to 5...");
                    decreaseDemo.decreaseKey(70, 5);
                    
                    System.out.println("After decrease key:");
                    decreaseDemo.printHeap();
                    break;
                    
                case 15:
                    System.out.println("🚀 Performance Test");
                    FibonacciHeap<Integer> perfHeap = new FibonacciHeap<>();
                    
                    System.out.println("Inserting 10000 elements...");
                    long startTime = System.nanoTime();
                    for (int i = 10000; i > 0; i--) {
                        perfHeap.insert(i);
                    }
                    long endTime = System.nanoTime();
                    System.out.println("Insertion time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    
                    System.out.println("Performing 1000 decrease-key operations...");
                    startTime = System.nanoTime();
                    for (int i = 5000; i > 4000; i--) {
                        if (perfHeap.contains(i)) {
                            perfHeap.decreaseKey(i, i - 10000);
                        }
                    }
                    endTime = System.nanoTime();
                    System.out.println("Decrease-key time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    
                    System.out.println("Extracting all elements...");
                    startTime = System.nanoTime();
                    int extracted = 0;
                    while (!perfHeap.isEmpty()) {
                        try {
                            perfHeap.extractMin();
                            extracted++;
                        } catch (HeapUnderflowException e) {
                            break;
                        }
                    }
                    endTime = System.nanoTime();
                    System.out.println("Extraction time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    System.out.println("Extracted " + extracted + " elements");
                    break;
                    
                case 16:
                    List<Integer> allElements = heap.getAllElements();
                    System.out.println("All elements in heap: " + allElements);
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