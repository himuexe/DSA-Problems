import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Segment Tree Implementation - Efficient Range Query Data Structure
 * 
 * A Segment Tree is a tree data structure used for storing information about intervals
 * or segments. It allows answering range queries over an array effectively, while still
 * being flexible enough to allow modifying the array. This implementation provides
 * efficient solutions for range sum, minimum, maximum, and other associative operations.
 * 
 * Key Features:
 * - Range queries in O(log n) time
 * - Point updates in O(log n) time
 * - Range updates with lazy propagation in O(log n) time
 * - Memory efficient with O(n) space complexity
 * - Generic support for different operations (sum, min, max, etc.)
 * 
 * Segment Tree Properties:
 * 1. Each leaf represents an element of the input array
 * 2. Each internal node represents a segment/range of the array
 * 3. Root represents the entire array range [0, n-1]
 * 4. For node representing [l, r], left child is [l, mid] and right child is [mid+1, r]
 * 5. Tree height is O(log n) and uses 4n space in worst case
 * 
 * Applications:
 * - Range sum/min/max queries
 * - Greatest Common Divisor (GCD) queries
 * - Range updates with lazy propagation
 * - Computational geometry problems
 * - Database indexing for range queries
 * - Online algorithms and competitive programming
 * 
 * Advantages over other data structures:
 * - Array: O(n) range queries vs O(log n) in Segment Tree
 * - Sqrt Decomposition: Better for range updates, similar query time
 * - Binary Indexed Tree: More flexible operations, handles non-commutative operations
 * 
 * Time Complexities:
 * - Build: O(n) where n is the size of input array
 * - Point Query: O(log n)
 * - Range Query: O(log n)
 * - Point Update: O(log n)
 * - Range Update: O(log n) with lazy propagation
 * 
 * Space Complexity: O(n) for the segment tree array
 * 
 * @param <T> the type of elements stored in the segment tree
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class SegmentTree<T extends Number & Comparable<T>> {
    
    // Instance variables
    private T[] tree;              // Array representation of segment tree
    private T[] lazy;              // Lazy propagation array for range updates
    private T[] originalArray;     // Copy of original input array
    private int n;                 // Size of original array
    private int treeSize;          // Size of segment tree array
    private BinaryOperator<T> operation; // Operation for combining values (sum, min, max)
    private T identity;            // Identity element for the operation
    private String operationType;  // Type of operation for display purposes
    
    /**
     * Constructor for Sum Segment Tree.
     * Creates a segment tree for range sum queries.
     * 
     * @param array the input array
     * @throws IllegalArgumentException if array is null or empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @SuppressWarnings("unchecked")
    public SegmentTree(T[] array) {
        this(array, (a, b) -> {
            if (a instanceof Integer) {
                return (T) Integer.valueOf(a.intValue() + b.intValue());
            } else if (a instanceof Long) {
                return (T) Long.valueOf(a.longValue() + b.longValue());
            } else if (a instanceof Double) {
                return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
            }
            throw new IllegalArgumentException("Unsupported number type for sum operation");
        }, getZero(array[0]), "SUM");
    }
    
    /**
     * Constructor for custom operation Segment Tree.
     * 
     * @param array the input array
     * @param operation the binary operation to combine values
     * @param identity the identity element for the operation
     * @param operationType string description of the operation
     * @throws IllegalArgumentException if any parameter is null or array is empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @SuppressWarnings("unchecked")
    public SegmentTree(T[] array, BinaryOperator<T> operation, T identity, String operationType) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (identity == null) {
            throw new IllegalArgumentException("Identity element cannot be null");
        }
        
        this.n = array.length;
        this.treeSize = 4 * n; // Ensure enough space for complete binary tree
        this.operation = operation;
        this.identity = identity;
        this.operationType = operationType != null ? operationType : "CUSTOM";
        
        // Initialize arrays
        this.tree = (T[]) new Number[treeSize];
        this.lazy = (T[]) new Number[treeSize];
        this.originalArray = Arrays.copyOf(array, n);
        
        // Initialize lazy array with identity elements
        Arrays.fill(lazy, identity);
        
        build(1, 0, n - 1);
        
        System.out.println("✅ Built " + operationType + " Segment Tree with " + n + " elements");
    }
    
    /**
     * Helper method to get zero value for different number types.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Number> T getZero(T sample) {
        if (sample instanceof Integer) {
            return (T) Integer.valueOf(0);
        } else if (sample instanceof Long) {
            return (T) Long.valueOf(0L);
        } else if (sample instanceof Double) {
            return (T) Double.valueOf(0.0);
        }
        throw new IllegalArgumentException("Unsupported number type");
    }
    
    /**
     * Factory method to create a Range Minimum Query (RMQ) Segment Tree.
     * 
     * @param array the input array
     * @return SegmentTree configured for minimum range queries
     */
    public static <T extends Number & Comparable<T>> SegmentTree<T> forRangeMin(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        // Find maximum value to use as identity (neutral element for min)
        T maxValue = array[0];
        for (T value : array) {
            if (value.compareTo(maxValue) > 0) {
                maxValue = value;
            }
        }
        
        return new SegmentTree<>(array, 
                                BinaryOperator.minBy(Comparator.naturalOrder()), 
                                maxValue, 
                                "MIN");
    }
    
    /**
     * Factory method to create a Range Maximum Query (RMQ) Segment Tree.
     * 
     * @param array the input array
     * @return SegmentTree configured for maximum range queries
     */
    public static <T extends Number & Comparable<T>> SegmentTree<T> forRangeMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        // Find minimum value to use as identity (neutral element for max)
        T minValue = array[0];
        for (T value : array) {
            if (value.compareTo(minValue) < 0) {
                minValue = value;
            }
        }
        
        return new SegmentTree<>(array, 
                                BinaryOperator.maxBy(Comparator.naturalOrder()), 
                                minValue, 
                                "MAX");
    }
    
    // ==================== CORE SEGMENT TREE OPERATIONS ====================
    
    /**
     * Builds the segment tree recursively.
     * 
     * @param node current node index in tree array
     * @param start start index of current segment
     * @param end end index of current segment
     */
    private void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node - store array element
            tree[node] = originalArray[start];
        } else {
            // Internal node - combine children
            int mid = (start + end) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;
            
            build(leftChild, start, mid);
            build(rightChild, mid + 1, end);
            
            tree[node] = operation.apply(tree[leftChild], tree[rightChild]);
        }
    }
    
    /**
     * Updates a single element in the array and segment tree.
     * 
     * @param index the index to update (0-based)
     * @param value the new value
     * @throws IllegalArgumentException if index is out of bounds
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    public void update(int index, T value) {
        if (index < 0 || index >= n) {
            throw new IllegalArgumentException("Index out of bounds: " + index);
        }
        
        originalArray[index] = value;
        update(1, 0, n - 1, index, value);
        
        System.out.println("✅ Updated index " + index + " to value " + value);
    }
    
    /**
     * Recursive helper for point update.
     */
    private void update(int node, int start, int end, int index, T value) {
        if (start == end) {
            // Leaf node - update value
            tree[node] = value;
        } else {
            // Internal node - update children and combine
            int mid = (start + end) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;
            
            if (index <= mid) {
                update(leftChild, start, mid, index, value);
            } else {
                update(rightChild, mid + 1, end, index, value);
            }
            
            tree[node] = operation.apply(tree[leftChild], tree[rightChild]);
        }
    }
    
    /**
     * Queries the result of operation over a range [left, right].
     * 
     * @param left left boundary of range (0-based, inclusive)
     * @param right right boundary of range (0-based, inclusive)
     * @return result of operation over the range
     * @throws IllegalArgumentException if range is invalid
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    public T query(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            throw new IllegalArgumentException("Invalid range: [" + left + ", " + right + "]");
        }
        
        pushDown(1, 0, n - 1); // Ensure lazy updates are propagated
        T result = query(1, 0, n - 1, left, right);
        
        System.out.println("📊 " + operationType + " query [" + left + ", " + right + "] = " + result);
        return result;
    }
    
    /**
     * Recursive helper for range query.
     */
    private T query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            // No overlap
            return identity;
        }
        
        if (left <= start && end <= right) {
            // Complete overlap
            return tree[node];
        }
        
        // Partial overlap
        pushDown(node, start, end);
        
        int mid = (start + end) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;
        
        T leftResult = query(leftChild, start, mid, left, right);
        T rightResult = query(rightChild, mid + 1, end, left, right);
        
        return operation.apply(leftResult, rightResult);
    }
    
    /**
     * Updates all elements in a range [left, right] by adding a value.
     * Uses lazy propagation for efficiency.
     * 
     * @param left left boundary of range (0-based, inclusive)
     * @param right right boundary of range (0-based, inclusive)
     * @param value value to add to all elements in range
     * @throws IllegalArgumentException if range is invalid
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) for recursion stack
     */
    @SuppressWarnings("unchecked")
    public void updateRange(int left, int right, T value) {
        if (left < 0 || right >= n || left > right) {
            throw new IllegalArgumentException("Invalid range: [" + left + ", " + right + "]");
        }
        
        // Only support range updates for sum operations
        if (!"SUM".equals(operationType)) {
            throw new UnsupportedOperationException("Range updates only supported for SUM operations");
        }
        
        updateRange(1, 0, n - 1, left, right, value);
        
        // Update original array for consistency
        for (int i = left; i <= right; i++) {
            if (originalArray[i] instanceof Integer) {
                originalArray[i] = (T) Integer.valueOf(originalArray[i].intValue() + value.intValue());
            } else if (originalArray[i] instanceof Long) {
                originalArray[i] = (T) Long.valueOf(originalArray[i].longValue() + value.longValue());
            } else if (originalArray[i] instanceof Double) {
                originalArray[i] = (T) Double.valueOf(originalArray[i].doubleValue() + value.doubleValue());
            }
        }
        
        System.out.println("✅ Range update [" + left + ", " + right + "] += " + value);
    }
    
    /**
     * Recursive helper for range update with lazy propagation.
     */
    @SuppressWarnings("unchecked")
    private void updateRange(int node, int start, int end, int left, int right, T value) {
        if (right < start || end < left) {
            // No overlap
            return;
        }
        
        if (left <= start && end <= right) {
            // Complete overlap - apply lazy update
            if (tree[node] instanceof Integer) {
                tree[node] = (T) Integer.valueOf(tree[node].intValue() + value.intValue() * (end - start + 1));
                lazy[node] = (T) Integer.valueOf(lazy[node].intValue() + value.intValue());
            } else if (tree[node] instanceof Long) {
                tree[node] = (T) Long.valueOf(tree[node].longValue() + value.longValue() * (end - start + 1));
                lazy[node] = (T) Long.valueOf(lazy[node].longValue() + value.longValue());
            } else if (tree[node] instanceof Double) {
                tree[node] = (T) Double.valueOf(tree[node].doubleValue() + value.doubleValue() * (end - start + 1));
                lazy[node] = (T) Double.valueOf(lazy[node].doubleValue() + value.doubleValue());
            }
            return;
        }
        
        // Partial overlap - push down and recurse
        pushDown(node, start, end);
        
        int mid = (start + end) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;
        
        updateRange(leftChild, start, mid, left, right, value);
        updateRange(rightChild, mid + 1, end, left, right, value);
        
        tree[node] = operation.apply(tree[leftChild], tree[rightChild]);
    }
    
    /**
     * Pushes down lazy updates to children.
     */
    @SuppressWarnings("unchecked")
    private void pushDown(int node, int start, int end) {
        if (!lazy[node].equals(identity)) {
            if (start != end) {
                // Not a leaf - push to children
                int leftChild = 2 * node;
                int rightChild = 2 * node + 1;
                int mid = (start + end) / 2;
                
                // Update left child
                if (tree[leftChild] instanceof Integer) {
                    tree[leftChild] = (T) Integer.valueOf(tree[leftChild].intValue() + lazy[node].intValue() * (mid - start + 1));
                    lazy[leftChild] = (T) Integer.valueOf(lazy[leftChild].intValue() + lazy[node].intValue());
                } else if (tree[leftChild] instanceof Long) {
                    tree[leftChild] = (T) Long.valueOf(tree[leftChild].longValue() + lazy[node].longValue() * (mid - start + 1));
                    lazy[leftChild] = (T) Long.valueOf(lazy[leftChild].longValue() + lazy[node].longValue());
                } else if (tree[leftChild] instanceof Double) {
                    tree[leftChild] = (T) Double.valueOf(tree[leftChild].doubleValue() + lazy[node].doubleValue() * (mid - start + 1));
                    lazy[leftChild] = (T) Double.valueOf(lazy[leftChild].doubleValue() + lazy[node].doubleValue());
                }
                
                // Update right child
                if (tree[rightChild] instanceof Integer) {
                    tree[rightChild] = (T) Integer.valueOf(tree[rightChild].intValue() + lazy[node].intValue() * (end - mid));
                    lazy[rightChild] = (T) Integer.valueOf(lazy[rightChild].intValue() + lazy[node].intValue());
                } else if (tree[rightChild] instanceof Long) {
                    tree[rightChild] = (T) Long.valueOf(tree[rightChild].longValue() + lazy[node].longValue() * (end - mid));
                    lazy[rightChild] = (T) Long.valueOf(lazy[rightChild].longValue() + lazy[node].longValue());
                } else if (tree[rightChild] instanceof Double) {
                    tree[rightChild] = (T) Double.valueOf(tree[rightChild].doubleValue() + lazy[node].doubleValue() * (end - mid));
                    lazy[rightChild] = (T) Double.valueOf(lazy[rightChild].doubleValue() + lazy[node].doubleValue());
                }
            }
            
            // Clear lazy value
            lazy[node] = identity;
        }
    }
    
    // ==================== UTILITY AND ANALYSIS METHODS ====================
    
    /**
     * Gets the current array after all updates.
     * 
     * @return copy of the current array state
     */
    public T[] getCurrentArray() {
        return Arrays.copyOf(originalArray, n);
    }
    
    /**
     * Gets the size of the original array.
     * 
     * @return size of the array
     */
    public int size() {
        return n;
    }
    
    /**
     * Gets the operation type of this segment tree.
     * 
     * @return string description of the operation
     */
    public String getOperationType() {
        return operationType;
    }
    
    /**
     * Calculates the height of the segment tree.
     * 
     * @return height of the tree
     */
    public int getHeight() {
        return (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
    
    /**
     * Gets the total number of nodes in the segment tree.
     * 
     * @return number of nodes
     */
    public int getNodeCount() {
        return treeSize;
    }
    
    /**
     * Finds the minimum value in a range.
     * Only works if this is a MIN segment tree.
     * 
     * @param left left boundary (inclusive)
     * @param right right boundary (inclusive)
     * @return minimum value in range
     * @throws UnsupportedOperationException if not a MIN tree
     */
    public T rangeMin(int left, int right) {
        if (!"MIN".equals(operationType)) {
            throw new UnsupportedOperationException("rangeMin only available for MIN segment trees");
        }
        return query(left, right);
    }
    
    /**
     * Finds the maximum value in a range.
     * Only works if this is a MAX segment tree.
     * 
     * @param left left boundary (inclusive)
     * @param right right boundary (inclusive)
     * @return maximum value in range
     * @throws UnsupportedOperationException if not a MAX tree
     */
    public T rangeMax(int left, int right) {
        if (!"MAX".equals(operationType)) {
            throw new UnsupportedOperationException("rangeMax only available for MAX segment trees");
        }
        return query(left, right);
    }
    
    /**
     * Calculates the sum of elements in a range.
     * Only works if this is a SUM segment tree.
     * 
     * @param left left boundary (inclusive)
     * @param right right boundary (inclusive)
     * @return sum of elements in range
     * @throws UnsupportedOperationException if not a SUM tree
     */
    public T rangeSum(int left, int right) {
        if (!"SUM".equals(operationType)) {
            throw new UnsupportedOperationException("rangeSum only available for SUM segment trees");
        }
        return query(left, right);
    }
    
    // ==================== DISPLAY AND VISUALIZATION ====================
    
    /**
     * Prints the current state of the array.
     */
    public void printArray() {
        System.out.println("📋 Current Array: " + Arrays.toString(originalArray));
    }
    
    /**
     * Prints the segment tree structure in a readable format.
     */
    public void printTree() {
        System.out.println("🌳 " + operationType + " Segment Tree Structure:");
        printTreeRecursive(1, 0, n - 1, 0);
        
        System.out.println("\n📊 Segment Tree Statistics:");
        System.out.println("   Array Size: " + n);
        System.out.println("   Tree Height: " + getHeight());
        System.out.println("   Node Count: " + getNodeCount());
        System.out.println("   Operation: " + operationType);
        System.out.println("   Identity: " + identity);
    }
    
    /**
     * Recursive helper for printing tree structure.
     */
    private void printTreeRecursive(int node, int start, int end, int depth) {
        if (node >= treeSize || tree[node] == null) return;
        
        String indent = "  ".repeat(depth);
        String rangeStr = (start == end) ? "[" + start + "]" : "[" + start + "-" + end + "]";
        String lazyStr = (!lazy[node].equals(identity)) ? " (lazy: " + lazy[node] + ")" : "";
        
        System.out.println(indent + "Node " + node + " " + rangeStr + ": " + tree[node] + lazyStr);
        
        if (start != end) {
            int mid = (start + end) / 2;
            printTreeRecursive(2 * node, start, mid, depth + 1);
            printTreeRecursive(2 * node + 1, mid + 1, end, depth + 1);
        }
    }
    
    /**
     * Returns a string representation of the segment tree.
     */
    @Override
    public String toString() {
        return "SegmentTree{size=" + n + ", operation=" + operationType + 
               ", height=" + getHeight() + ", nodes=" + getNodeCount() + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating Segment Tree operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🌳 Segment Tree Interactive Demo");
        System.out.println("================================");
        System.out.println("Efficient range query data structure!");
        
        // Get array input
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        Integer[] array = new Integer[size];
        
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        
        // Choose operation type
        System.out.println("\nChoose operation type:");
        System.out.println("1. SUM (Range Sum Queries)");
        System.out.println("2. MIN (Range Minimum Queries)");
        System.out.println("3. MAX (Range Maximum Queries)");
        System.out.print("Enter choice (1-3): ");
        int opChoice = scanner.nextInt();
        
        SegmentTree<Integer> segTree;
        switch (opChoice) {
            case 1:
                segTree = new SegmentTree<>(array);
                break;
            case 2:
                segTree = SegmentTree.forRangeMin(array);
                break;
            case 3:
                segTree = SegmentTree.forRangeMax(array);
                break;
            default:
                segTree = new SegmentTree<>(array);
        }
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Point Update");
            System.out.println("2.  Range Query");
            System.out.println("3.  Range Update (SUM only)");
            System.out.println("4.  Print Current Array");
            System.out.println("5.  Print Tree Structure");
            System.out.println("6.  Get Array Size");
            System.out.println("7.  Get Tree Height");
            System.out.println("8.  Demo with Range Queries");
            System.out.println("9.  Exit");
            System.out.print("👉 Enter your choice (1-9): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter index to update: ");
                        int updateIndex = scanner.nextInt();
                        System.out.print("Enter new value: ");
                        int newValue = scanner.nextInt();
                        segTree.update(updateIndex, newValue);
                        break;
                        
                    case 2:
                        System.out.print("Enter left boundary: ");
                        int left = scanner.nextInt();
                        System.out.print("Enter right boundary: ");
                        int right = scanner.nextInt();
                        segTree.query(left, right);
                        break;
                        
                    case 3:
                        if ("SUM".equals(segTree.getOperationType())) {
                            System.out.print("Enter left boundary: ");
                            int rangeLeft = scanner.nextInt();
                            System.out.print("Enter right boundary: ");
                            int rangeRight = scanner.nextInt();
                            System.out.print("Enter value to add: ");
                            int addValue = scanner.nextInt();
                            segTree.updateRange(rangeLeft, rangeRight, addValue);
                        } else {
                            System.out.println("❌ Range updates only supported for SUM operations");
                        }
                        break;
                        
                    case 4:
                        segTree.printArray();
                        break;
                        
                    case 5:
                        segTree.printTree();
                        break;
                        
                    case 6:
                        System.out.println("📊 Array size: " + segTree.size());
                        break;
                        
                    case 7:
                        System.out.println("📊 Tree height: " + segTree.getHeight());
                        break;
                        
                    case 8:
                        System.out.println("🎬 Demo: Testing multiple range queries...");
                        for (int i = 0; i < Math.min(5, segTree.size()); i++) {
                            int demoRight = Math.min(i + 2, segTree.size() - 1);
                            segTree.query(i, demoRight);
                        }
                        break;
                        
                    case 9:
                        System.out.println("👋 Thank you for using Segment Tree Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-9.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 