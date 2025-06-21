import exceptions.HeapUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BinomialHeap implementation demonstrating advanced heap operations with efficient merging.
 * A binomial heap is a collection of binomial trees that satisfies the heap property.
 * It supports union, insert, extract-min, decrease-key, and delete operations efficiently.
 * 
 * Key Features:
 * - Union operation in O(log n) time
 * - Insert, extract-min, decrease-key, delete in O(log n) time
 * - Efficient merging of multiple heaps
 * - Support for lazy deletion and key updates
 * 
 * Binomial Tree Properties:
 * - B_k has 2^k nodes, height k
 * - B_k is formed by linking two B_{k-1} trees
 * - Root has degree k with children being roots of B_0, B_1, ..., B_{k-1}
 * 
 * Educational implementation for DSA learning with detailed documentation.
 * 
 * @param <T> the type of elements stored in the heap, must be comparable
 * @author DSA Learning Project
 * @version 1.0
 */
public class BinomialHeap<T extends Comparable<T>> {
    
    /**
     * Node class representing a node in the binomial heap.
     * Each node contains data, degree, and pointers to parent, child, and sibling.
     */
    public class BinomialNode {
        T data;
        BinomialNode parent;
        BinomialNode child;    // Leftmost child
        BinomialNode sibling;  // Right sibling
        int degree;           // Number of children
        boolean marked;       // For decrease-key and delete operations
        
        /**
         * Create a new binomial node with given data.
         */
        public BinomialNode(T data) {
            this.data = data;
            this.parent = null;
            this.child = null;
            this.sibling = null;
            this.degree = 0;
            this.marked = false;
        }
        
        @Override
        public String toString() {
            return data.toString() + "(d:" + degree + ")";
        }
    }
    
    /** Root list of binomial trees (sorted by degree) */
    private BinomialNode head;
    
    /** Current minimum node for O(1) access */
    private BinomialNode minNode;
    
    /** Size of the heap */
    private int size;
    
    /**
     * Create an empty binomial heap.
     */
    public BinomialHeap() {
        this.head = null;
        this.minNode = null;
        this.size = 0;
        System.out.println("✓ Created empty binomial heap");
    }
    
    /**
     * Create a binomial heap with a single element.
     */
    public BinomialHeap(T data) {
        this();
        insert(data);
    }
    
    // =====================================================
    // === CORE BINOMIAL HEAP OPERATIONS ===
    // =====================================================
    
    /**
     * Insert a new element into the binomial heap.
     * Creates a new binomial heap with single element and unions with current heap.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param data the element to insert
     */
    public void insert(T data) {
        if (data == null) {
            System.out.println("✗ Cannot insert null element");
            return;
        }
        
        // Create a new heap with single element
        BinomialHeap<T> tempHeap = new BinomialHeap<>();
        tempHeap.head = new BinomialNode(data);
        tempHeap.minNode = tempHeap.head;
        tempHeap.size = 1;
        
        // Union with current heap
        union(tempHeap);
        
        System.out.println("✓ Inserted " + data + " into binomial heap (size: " + size + ")");
    }
    
    /**
     * Extract the minimum element from the binomial heap.
     * Removes the root with minimum key and reorganizes the heap.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @return the minimum element
     * @throws HeapUnderflowException if heap is empty
     */
    public T extractMin() throws HeapUnderflowException {
        if (isEmpty()) {
            throw new HeapUnderflowException("Cannot extract from empty binomial heap");
        }
        
        T minData = minNode.data;
        
        // Remove min node from root list
        removeNodeFromRootList(minNode);
        
        // Create new heap from children of min node
        BinomialHeap<T> childHeap = new BinomialHeap<>();
        if (minNode.child != null) {
            childHeap.head = reverseList(minNode.child);
            
            // Update parent pointers and count children
            BinomialNode current = childHeap.head;
            while (current != null) {
                current.parent = null;
                childHeap.size += (1 << current.degree); // 2^degree
                current = current.sibling;
            }
        }
        
        // Union current heap with child heap
        union(childHeap);
        
        // Update minimum
        updateMinimum();
        
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
     * Union this heap with another binomial heap.
     * Merges root lists and consolidates trees of same degree.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param other the other binomial heap to union with
     */
    public void union(BinomialHeap<T> other) {
        if (other == null || other.isEmpty()) {
            return;
        }
        
        // Merge root lists
        head = mergeRootLists(head, other.head);
        
        // Consolidate trees
        consolidate();
        
        // Update size and minimum
        size += other.size;
        updateMinimum();
        
        // Clear other heap
        other.head = null;
        other.minNode = null;
        other.size = 0;
        
        System.out.println("✓ Union completed (total size: " + size + ")");
    }
    
    /**
     * Decrease the key of a given node.
     * Updates the key and restores heap property by bubbling up.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param node the node whose key to decrease
     * @param newData the new data (must be smaller than current)
     */
    public void decreaseKey(BinomialNode node, T newData) {
        if (node == null || newData == null) {
            System.out.println("✗ Invalid node or data for decrease key");
            return;
        }
        
        if (newData.compareTo(node.data) >= 0) {
            System.out.println("✗ New key must be smaller than current key");
            return;
        }
        
        node.data = newData;
        
        // Bubble up to restore heap property
        BinomialNode current = node;
        BinomialNode parent = current.parent;
        
        while (parent != null && current.data.compareTo(parent.data) < 0) {
            // Swap data
            T temp = current.data;
            current.data = parent.data;
            parent.data = temp;
            
            current = parent;
            parent = current.parent;
        }
        
        // Update minimum if necessary
        updateMinimum();
        
        System.out.println("✓ Decreased key to " + newData);
    }
    
    /**
     * Delete a given node from the binomial heap.
     * Uses decrease-key to make it minimum, then extract-min.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param node the node to delete
     */
    public void delete(BinomialNode node) {
        if (node == null) {
            System.out.println("✗ Cannot delete null node");
            return;
        }
        
        // Mark node for deletion (conceptual negative infinity)
        T originalData = node.data;
        
        // Bubble to root by making it smaller than all others
        BinomialNode current = node;
        while (current.parent != null) {
            // Swap with parent
            T temp = current.data;
            current.data = current.parent.data;
            current.parent.data = temp;
            current = current.parent;
        }
        
        // Now the node is at root level, extract it
        try {
            extractMin();
            System.out.println("✓ Deleted node with data " + originalData);
        } catch (HeapUnderflowException e) {
            System.out.println("✗ Error deleting node: " + e.getMessage());
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
        head = null;
        minNode = null;
        size = 0;
        System.out.println("✓ Cleared binomial heap");
    }
    
    /**
     * Find a node with given data (for demonstration purposes).
     * Note: This is O(n) operation and not typically provided in production.
     * 
     * @param data the data to search for
     * @return the node if found, null otherwise
     */
    public BinomialNode findNode(T data) {
        return findNodeHelper(head, data);
    }
    
    private BinomialNode findNodeHelper(BinomialNode root, T data) {
        if (root == null) return null;
        
        if (root.data.equals(data)) return root;
        
        // Search in child
        BinomialNode found = findNodeHelper(root.child, data);
        if (found != null) return found;
        
        // Search in sibling
        return findNodeHelper(root.sibling, data);
    }
    
    // =====================================================
    // === HELPER METHODS ===
    // =====================================================
    
    /**
     * Merge two root lists in increasing order of degree.
     */
    private BinomialNode mergeRootLists(BinomialNode list1, BinomialNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        BinomialNode head, tail;
        
        // Initialize head
        if (list1.degree <= list2.degree) {
            head = tail = list1;
            list1 = list1.sibling;
        } else {
            head = tail = list2;
            list2 = list2.sibling;
        }
        
        // Merge remaining nodes
        while (list1 != null && list2 != null) {
            if (list1.degree <= list2.degree) {
                tail.sibling = list1;
                list1 = list1.sibling;
            } else {
                tail.sibling = list2;
                list2 = list2.sibling;
            }
            tail = tail.sibling;
        }
        
        // Append remaining nodes
        tail.sibling = (list1 != null) ? list1 : list2;
        
        return head;
    }
    
    /**
     * Consolidate trees by linking trees of same degree.
     */
    private void consolidate() {
        if (head == null) return;
        
        BinomialNode prev = null;
        BinomialNode curr = head;
        BinomialNode next = curr.sibling;
        
        while (next != null) {
            // Case 1: degrees are different
            if (curr.degree != next.degree ||
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            }
            // Case 2: degrees are same, link them
            else {
                if (curr.data.compareTo(next.data) <= 0) {
                    // curr becomes parent of next
                    curr.sibling = next.sibling;
                    linkTrees(curr, next);
                } else {
                    // next becomes parent of curr
                    if (prev == null) {
                        head = next;
                    } else {
                        prev.sibling = next;
                    }
                    linkTrees(next, curr);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
    }
    
    /**
     * Link two binomial trees of same degree.
     * Makes root with smaller key the parent.
     */
    private void linkTrees(BinomialNode parent, BinomialNode child) {
        child.parent = parent;
        child.sibling = parent.child;
        parent.child = child;
        parent.degree++;
    }
    
    /**
     * Remove a node from the root list.
     */
    private void removeNodeFromRootList(BinomialNode node) {
        if (head == node) {
            head = node.sibling;
        } else {
            BinomialNode current = head;
            while (current.sibling != node) {
                current = current.sibling;
            }
            current.sibling = node.sibling;
        }
    }
    
    /**
     * Reverse a linked list of nodes.
     */
    private BinomialNode reverseList(BinomialNode head) {
        BinomialNode prev = null;
        BinomialNode current = head;
        
        while (current != null) {
            BinomialNode next = current.sibling;
            current.sibling = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Update the minimum node pointer.
     */
    private void updateMinimum() {
        if (head == null) {
            minNode = null;
            return;
        }
        
        minNode = head;
        BinomialNode current = head.sibling;
        
        while (current != null) {
            if (current.data.compareTo(minNode.data) < 0) {
                minNode = current;
            }
            current = current.sibling;
        }
    }
    
    // =====================================================
    // === ANALYSIS AND VALIDATION ===
    // =====================================================
    
    /**
     * Validate the binomial heap properties.
     * 
     * @return true if all properties are satisfied
     */
    public boolean isValidBinomialHeap() {
        return validateRootList() && validateHeapProperty() && validateTreeStructure();
    }
    
    /**
     * Validate that root list is sorted by degree.
     */
    private boolean validateRootList() {
        BinomialNode current = head;
        while (current != null && current.sibling != null) {
            if (current.degree >= current.sibling.degree) {
                return false;
            }
            current = current.sibling;
        }
        return true;
    }
    
    /**
     * Validate heap property for all nodes.
     */
    private boolean validateHeapProperty() {
        return validateHeapPropertyHelper(head);
    }
    
    private boolean validateHeapPropertyHelper(BinomialNode node) {
        if (node == null) return true;
        
        // Check children
        BinomialNode child = node.child;
        while (child != null) {
            if (node.data.compareTo(child.data) > 0) {
                return false;
            }
            if (!validateHeapPropertyHelper(child)) {
                return false;
            }
            child = child.sibling;
        }
        
        // Check sibling
        return validateHeapPropertyHelper(node.sibling);
    }
    
    /**
     * Validate binomial tree structure properties.
     */
    private boolean validateTreeStructure() {
        BinomialNode current = head;
        while (current != null) {
            if (!validateBinomialTree(current)) {
                return false;
            }
            current = current.sibling;
        }
        return true;
    }
    
    /**
     * Validate that a tree is a proper binomial tree.
     */
    private boolean validateBinomialTree(BinomialNode root) {
        if (root == null) return true;
        
        // Count children and check their degrees
        int childCount = 0;
        BinomialNode child = root.child;
        int expectedDegree = root.degree - 1;
        
        while (child != null) {
            if (child.degree != expectedDegree) {
                return false;
            }
            if (!validateBinomialTree(child)) {
                return false;
            }
            childCount++;
            expectedDegree--;
            child = child.sibling;
        }
        
        return childCount == root.degree;
    }
    
    // =====================================================
    // === DISPLAY METHODS ===
    // =====================================================
    
    /**
     * Print the structure of the binomial heap.
     */
    public void printHeap() {
        if (isEmpty()) {
            System.out.println("Binomial heap is empty");
            return;
        }
        
        System.out.println("Binomial Heap Structure:");
        System.out.println("Size: " + size + ", Min: " + minNode.data);
        
        BinomialNode current = head;
        int treeIndex = 0;
        
        while (current != null) {
            System.out.println("\nBinomial Tree B" + current.degree + " (Tree " + treeIndex + "):");
            printTree(current, "", true);
            current = current.sibling;
            treeIndex++;
        }
    }
    
    /**
     * Print a single binomial tree in tree format.
     */
    private void printTree(BinomialNode node, String prefix, boolean isLast) {
        if (node == null) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + node);
        
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        
        // Print children
        BinomialNode child = node.child;
        while (child != null) {
            printTree(child, childPrefix, child.sibling == null);
            child = child.sibling;
        }
    }
    
    /**
     * Display heap statistics and properties.
     */
    public void displayStats() {
        System.out.println("\n=== Binomial Heap Statistics ===");
        System.out.println("Size: " + size);
        System.out.println("Empty: " + isEmpty());
        
        if (!isEmpty()) {
            System.out.println("Minimum: " + minNode.data);
            
            // Count trees
            int treeCount = 0;
            BinomialNode current = head;
            while (current != null) {
                treeCount++;
                current = current.sibling;
            }
            System.out.println("Number of binomial trees: " + treeCount);
            
            // Show tree degrees
            System.out.print("Tree degrees: ");
            current = head;
            while (current != null) {
                System.out.print("B" + current.degree + " ");
                current = current.sibling;
            }
            System.out.println();
            
            System.out.println("Valid binomial heap: " + isValidBinomialHeap());
        }
        System.out.println("===============================\n");
    }
    
    /**
     * Get all elements in the heap (for testing purposes).
     */
    public List<T> getAllElements() {
        List<T> elements = new ArrayList<>();
        getAllElementsHelper(head, elements);
        return elements;
    }
    
    private void getAllElementsHelper(BinomialNode node, List<T> elements) {
        if (node == null) return;
        
        elements.add(node.data);
        getAllElementsHelper(node.child, elements);
        getAllElementsHelper(node.sibling, elements);
    }
    
    // =====================================================
    // === INTERACTIVE MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive main method for testing and demonstration.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        
        System.out.println("🌳 Binomial Heap Implementation Demo");
        System.out.println("====================================");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert Element");
            System.out.println("2. Extract Minimum");
            System.out.println("3. Get Minimum");
            System.out.println("4. Union with Another Heap");
            System.out.println("5. Decrease Key");
            System.out.println("6. Delete Node");
            System.out.println("7. Find Node");
            System.out.println("8. Print Heap Structure");
            System.out.println("9. Display Statistics");
            System.out.println("10. Validate Heap");
            System.out.println("11. Clear Heap");
            System.out.println("12. Bulk Insert Demo");
            System.out.println("13. Union Demo");
            System.out.println("14. Performance Test");
            System.out.println("15. Show All Elements");
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
                    BinomialHeap<Integer> otherHeap = new BinomialHeap<>();
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
                    System.out.print("Enter element to find: ");
                    int findElement = scanner.nextInt();
                    BinomialHeap<Integer>.BinomialNode node = heap.findNode(findElement);
                    if (node != null) {
                        System.out.print("Enter new smaller value: ");
                        int newValue = scanner.nextInt();
                        heap.decreaseKey(node, newValue);
                    } else {
                        System.out.println("Element not found!");
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter element to delete: ");
                    int deleteElement = scanner.nextInt();
                    BinomialHeap<Integer>.BinomialNode deleteNode = heap.findNode(deleteElement);
                    if (deleteNode != null) {
                        heap.delete(deleteNode);
                    } else {
                        System.out.println("Element not found!");
                    }
                    break;
                    
                case 7:
                    System.out.print("Enter element to find: ");
                    int searchElement = scanner.nextInt();
                    BinomialHeap<Integer>.BinomialNode foundNode = heap.findNode(searchElement);
                    if (foundNode != null) {
                        System.out.println("Found node: " + foundNode);
                    } else {
                        System.out.println("Element not found!");
                    }
                    break;
                    
                case 8:
                    heap.printHeap();
                    break;
                    
                case 9:
                    heap.displayStats();
                    break;
                    
                case 10:
                    boolean valid = heap.isValidBinomialHeap();
                    System.out.println("Heap is " + (valid ? "valid" : "invalid"));
                    break;
                    
                case 11:
                    heap.clear();
                    break;
                    
                case 12:
                    System.out.println("🎭 Bulk Insert Demo");
                    int[] bulkElements = {15, 28, 41, 7, 25, 12, 33, 8, 19, 3};
                    System.out.println("Inserting: " + java.util.Arrays.toString(bulkElements));
                    for (int elem : bulkElements) {
                        heap.insert(elem);
                    }
                    heap.printHeap();
                    break;
                    
                case 13:
                    System.out.println("🔗 Union Demo");
                    BinomialHeap<Integer> heap1 = new BinomialHeap<>();
                    BinomialHeap<Integer> heap2 = new BinomialHeap<>();
                    
                    int[] heap1Elements = {10, 20, 30};
                    int[] heap2Elements = {5, 15, 25, 35};
                    
                    System.out.println("Heap 1 elements: " + java.util.Arrays.toString(heap1Elements));
                    for (int elem : heap1Elements) heap1.insert(elem);
                    
                    System.out.println("Heap 2 elements: " + java.util.Arrays.toString(heap2Elements));
                    for (int elem : heap2Elements) heap2.insert(elem);
                    
                    System.out.println("\nBefore union:");
                    System.out.println("Heap 1:");
                    heap1.printHeap();
                    System.out.println("Heap 2:");
                    heap2.printHeap();
                    
                    heap1.union(heap2);
                    System.out.println("\nAfter union:");
                    heap1.printHeap();
                    break;
                    
                case 14:
                    System.out.println("🚀 Performance Test");
                    BinomialHeap<Integer> perfHeap = new BinomialHeap<>();
                    
                    System.out.println("Inserting 1000 elements...");
                    long startTime = System.nanoTime();
                    for (int i = 1000; i > 0; i--) {
                        perfHeap.insert(i);
                    }
                    long endTime = System.nanoTime();
                    System.out.println("Insertion time: " + (endTime - startTime) / 1_000_000.0 + " ms");
                    
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
                    
                case 15:
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