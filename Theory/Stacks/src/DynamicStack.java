import exceptions.StackUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Dynamic Array-based Stack Implementation
 * 
 * This class implements a stack data structure using a resizable array as the underlying
 * storage mechanism. It follows the LIFO (Last In, First Out) principle and provides
 * automatic resizing capabilities that combine the benefits of both array-based and
 * linked list implementations.
 * 
 * Key Features:
 * - Automatic resizing that grows and shrinks as needed
 * - Growth strategy: doubles capacity when full
 * - Shrink strategy: halves capacity when 25% utilized
 * - Amortized O(1) performance for push operations
 * - Best of both worlds: array efficiency with dynamic sizing
 * 
 * Best Use Cases:
 * - General-purpose stack implementation
 * - Applications with varying workloads
 * - When you need array performance but unknown size requirements
 * - Educational purposes to understand amortized analysis
 * 
 * Time Complexities:
 * - Push: O(1) amortized, O(n) worst case during resize
 * - Pop: O(1) amortized, O(n) worst case during shrink
 * - Peek: O(1)
 * - Search: O(n)
 * - isEmpty/size: O(1)
 * 
 * Space Complexity: O(capacity) where capacity ≥ n
 * 
 * @param <T> the type of elements stored in this stack
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class DynamicStack<T> {
    
    // Instance variables
    private T[] stack;
    private int top;
    private int capacity;
    
    // Constants for resizing strategy
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 2.0;
    private static final double SHRINK_THRESHOLD = 0.25;
    private static final int MIN_CAPACITY = 4;
    
    // Statistics for educational purposes
    private int resizeCount = 0;
    private int growthCount = 0;
    private int shrinkCount = 0;
    
    /**
     * Default constructor that creates a stack with default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(DEFAULT_CAPACITY)
     */
    @SuppressWarnings("unchecked")
    public DynamicStack() {
        this.capacity = DEFAULT_CAPACITY;
        this.stack = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    /**
     * Constructor that creates a stack with specified initial capacity.
     * 
     * @param initialCapacity the initial capacity of the stack
     * @throws IllegalArgumentException if initialCapacity is negative or zero
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(initialCapacity)
     */
    @SuppressWarnings("unchecked")
    public DynamicStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive, got: " + initialCapacity);
        }
        this.capacity = Math.max(initialCapacity, MIN_CAPACITY);
        this.stack = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    // ==================== CORE STACK OPERATIONS ====================
    
    /**
     * Pushes an element onto the top of the stack.
     * Automatically grows the stack if it reaches capacity.
     * 
     * @param element the element to be pushed onto the stack
     * @throws IllegalArgumentException if element is null
     * 
     * Time Complexity: O(1) amortized, O(n) worst case during resize
     * Space Complexity: O(1) amortized
     */
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot push null element onto stack");
        }
        
        // Check if we need to grow
        if (size() == capacity) {
            resize((int) (capacity * GROWTH_FACTOR));
            growthCount++;
            System.out.println("📈 Stack grew to capacity: " + capacity);
        }
        
        stack[++top] = element;
        System.out.println("✅ Pushed '" + element + "' onto stack. Current size: " + size());
    }
    
    /**
     * Removes and returns the element at the top of the stack.
     * Automatically shrinks the stack if utilization falls below threshold.
     * 
     * @return the element at the top of the stack
     * @throws StackUnderflowException if the stack is empty
     * 
     * Time Complexity: O(1) amortized, O(n) worst case during shrink
     * Space Complexity: O(1)
     */
    public T pop() {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot pop from empty stack");
        }
        
        T element = stack[top];
        stack[top] = null; // Help GC
        top--;
        
        // Check if we should shrink (avoid shrinking too small)
        if (size() > 0 && size() <= capacity * SHRINK_THRESHOLD && capacity > MIN_CAPACITY) {
            int newCapacity = Math.max(capacity / 2, MIN_CAPACITY);
            resize(newCapacity);
            shrinkCount++;
            System.out.println("📉 Stack shrunk to capacity: " + capacity);
        }
        
        System.out.println("✅ Popped '" + element + "' from stack. Current size: " + size());
        return element;
    }
    
    /**
     * Returns the element at the top of the stack without removing it.
     * 
     * @return the element at the top of the stack
     * @throws StackUnderflowException if the stack is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T peek() {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot peek at empty stack");
        }
        return stack[top];
    }
    
    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Returns the current number of elements in the stack.
     * 
     * @return the number of elements currently in the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return top + 1;
    }
    
    // ==================== STACK-SPECIFIC OPERATIONS ====================
    
    /**
     * Returns the current capacity of the stack.
     * 
     * @return the current maximum number of elements this stack can hold
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Removes all elements from the stack and resets to default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        // Help GC by nullifying references
        Arrays.fill(stack, 0, size(), null);
        top = -1;
        
        // Reset to default capacity
        if (capacity != DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
            stack = (T[]) new Object[capacity];
            resizeCount++;
        }
        
        System.out.println("✅ Stack cleared. Reset to default capacity: " + capacity);
    }
    
    /**
     * Searches for an element in the stack and returns its position from the top.
     * 
     * @param element the element to search for
     * @return the 1-based position from the top (1 = top element), or -1 if not found
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int search(T element) {
        if (element == null) return -1;
        
        for (int i = top; i >= 0; i--) {
            if (element.equals(stack[i])) {
                int position = top - i + 1;
                System.out.println("🔍 Found '" + element + "' at position " + position + " from top");
                return position;
            }
        }
        
        System.out.println("❌ Element '" + element + "' not found in stack");
        return -1;
    }
    
    /**
     * Manually triggers a resize to the specified capacity.
     * 
     * @param newCapacity the new capacity to resize to
     * @throws IllegalArgumentException if newCapacity is smaller than current size
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(newCapacity)
     */
    public void resize(int newCapacity) {
        if (newCapacity < size()) {
            throw new IllegalArgumentException("New capacity (" + newCapacity + 
                                             ") cannot be smaller than current size (" + size() + ")");
        }
        
        @SuppressWarnings("unchecked")
        T[] newStack = (T[]) new Object[newCapacity];
        
        // Copy existing elements
        for (int i = 0; i <= top; i++) {
            newStack[i] = stack[i];
        }
        
        stack = newStack;
        capacity = newCapacity;
        resizeCount++;
    }
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Displays the current state of the stack from top to bottom with capacity information.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printStack() {
        System.out.println("\n🔄 Current Stack State (Dynamic Array):");
        System.out.printf("Capacity: %d | Size: %d | Utilization: %.1f%% | Available: %d%n", 
                         capacity, size(), (size() * 100.0 / capacity), (capacity - size()));
        
        if (isEmpty()) {
            System.out.println("│ (empty stack) │");
        } else {
            System.out.println("┌" + "─".repeat(15) + "┐");
            for (int i = top; i >= 0; i--) {
                String indicator = (i == top) ? " ← TOP" : "";
                System.out.printf("│ %-12s │%s%n", stack[i], indicator);
                if (i > 0) System.out.println("├" + "─".repeat(15) + "┤");
            }
            System.out.println("└" + "─".repeat(15) + "┘");
        }
        System.out.println();
    }
    
    /**
     * Converts the stack to an array with elements ordered from bottom to top.
     * 
     * @return an array containing all stack elements
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[size()];
        for (int i = 0; i <= top; i++) {
            result[i] = stack[i];
        }
        return result;
    }
    
    /**
     * Returns a string representation of the stack.
     * 
     * @return string representation showing elements from top to bottom with capacity info
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "DynamicStack(empty, capacity=" + capacity + ")";
        }
        
        StringBuilder sb = new StringBuilder("DynamicStack[");
        for (int i = top; i >= 0; i--) {
            sb.append(stack[i]);
            if (i > 0) sb.append(", ");
        }
        sb.append("] (size=").append(size()).append("/").append(capacity).append(")");
        return sb.toString();
    }
    
    // ==================== EDUCATIONAL STATISTICS ====================
    
    /**
     * Returns statistics about resize operations for educational purposes.
     * 
     * @return string containing resize statistics
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public String getResizeStats() {
        return String.format("Resize Stats: Total=%d, Growth=%d, Shrink=%d", 
                           resizeCount, growthCount, shrinkCount);
    }
    
    /**
     * Returns the current utilization percentage of the stack.
     * 
     * @return utilization as a percentage (0.0 to 100.0)
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public double getUtilization() {
        return isEmpty() ? 0.0 : (size() * 100.0 / capacity);
    }
    
    /**
     * Returns information about the resizing strategy used by this stack.
     * 
     * @return string describing the resizing behavior
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public String getResizeStrategy() {
        return String.format("Growth: %.1fx when full | Shrink: when utilization < %.0f%% | Min capacity: %d", 
                           GROWTH_FACTOR, SHRINK_THRESHOLD * 100, MIN_CAPACITY);
    }
    
    // ==================== INTERACTIVE DEMO ====================
    
    /**
     * Interactive demonstration of the DynamicStack with a comprehensive menu system.
     * Allows users to test all operations and observe the dynamic resizing behavior.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DynamicStack<String> stack = new DynamicStack<>(4);
        
        System.out.println("🔄 DYNAMIC ARRAY-BASED STACK DEMONSTRATION");
        System.out.println("===========================================");
        System.out.println("Welcome to the interactive Dynamic Stack demo!");
        System.out.println("This demonstrates a stack that automatically resizes as needed.\n");
        System.out.println("✅ Created DynamicStack with initial capacity: 4");
        System.out.println("📊 Resize Strategy: 2.0x growth when full, shrink at 25% utilization");
        
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("         DYNAMIC STACK OPERATIONS MENU");
            System.out.println("=".repeat(50));
            System.out.println("1.  Push Element");
            System.out.println("2.  Pop Element");
            System.out.println("3.  Peek/Top Element");
            System.out.println("4.  Check if Empty");
            System.out.println("5.  Get Size");
            System.out.println("6.  Get Capacity");
            System.out.println("7.  Search Element");
            System.out.println("8.  Clear Stack");
            System.out.println("9.  Print Stack");
            System.out.println("10. Convert to Array");
            System.out.println("11. Stack Information");
            System.out.println("12. Resize Statistics");
            System.out.println("0.  Exit");
            System.out.println("=".repeat(50));
            
            stack.printStack();
            System.out.print("Enter your choice (0-12): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1: // Push
                        System.out.print("Enter element to push: ");
                        String element = scanner.nextLine().trim();
                        if (!element.isEmpty()) {
                            stack.push(element);
                        } else {
                            System.out.println("❌ Cannot push empty element");
                        }
                        break;
                        
                    case 2: // Pop
                        try { 
                            String popped = stack.pop();
                            System.out.println("📤 Popped element: '" + popped + "'");
                        } catch (StackUnderflowException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                        break;
                        
                    case 3: // Peek
                        try {
                            String topElement = stack.peek();
                            System.out.println("👁️  Top element: '" + topElement + "'");
                        } catch (StackUnderflowException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                        break;
                        
                    case 4: // Is Empty
                        boolean empty = stack.isEmpty();
                        System.out.println("🔍 Stack is " + (empty ? "EMPTY" : "NOT EMPTY"));
                        break;
                        
                    case 5: // Size
                        System.out.println("📏 Current size: " + stack.size());
                        break;
                        
                    case 6: // Capacity
                        System.out.println("📦 Current capacity: " + stack.getCapacity());
                        System.out.printf("📊 Utilization: %.1f%%\n", stack.getUtilization());
                        break;
                        
                    case 7: // Search
                        System.out.print("Enter element to search: ");
                        String searchElement = scanner.nextLine().trim();
                        if (!searchElement.isEmpty()) {
                            stack.search(searchElement);
                        } else {
                            System.out.println("❌ Cannot search for empty element");
                        }
                        break;
                        
                    case 8: // Clear
                        stack.clear();
                        break;
                        
                    case 9: // Print
                        stack.printStack();
                        break;
                        
                    case 10: // To Array
                        Object[] array = stack.toArray();
                        System.out.println("📋 Stack as array (bottom to top): " + Arrays.toString(array));
                        break;
                        
                    case 11: // Information
                        System.out.println("\n📊 STACK INFORMATION:");
                        System.out.println("Type: Dynamic Array-Based Stack");
                        System.out.println("Current Size: " + stack.size());
                        System.out.println("Current Capacity: " + stack.getCapacity());
                        System.out.printf("Utilization: %.1f%%\n", stack.getUtilization());
                        System.out.println("Is Empty: " + stack.isEmpty());
                        System.out.println("Memory Layout: Contiguous resizable array");
                        System.out.println("Time Complexity: O(1) amortized for push/pop");
                        System.out.println("Resize Strategy: " + stack.getResizeStrategy());
                        break;
                        
                    case 12: // Resize Stats
                        System.out.println("📈 " + stack.getResizeStats());
                        System.out.printf("📊 Current utilization: %.1f%%\n", stack.getUtilization());
                        break;
                        
                    case 0: // Exit
                        running = false;
                        System.out.println("👋 Thank you for using Dynamic Stack Demo!");
                        System.out.println("🎓 You've learned about dynamic resizing and amortized analysis!");
                        System.out.println("📈 Final stats: " + stack.getResizeStats());
                        break;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter a number between 0-12.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            }
            
            // Pause for user to read output
            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
} 