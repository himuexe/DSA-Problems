import exceptions.StackOverflowException;
import exceptions.StackUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Array-based Stack Implementation
 * 
 * This class implements a stack data structure using a fixed-size array as the underlying
 * storage mechanism. It follows the LIFO (Last In, First Out) principle and provides
 * all standard stack operations with O(1) time complexity for core operations.
 * 
 * Key Features:
 * - Fixed capacity defined at initialization
 * - Generic implementation supporting any data type
 * - Stack overflow protection with custom exceptions
 * - Memory efficient with minimal overhead per element
 * - Cache-friendly contiguous memory layout
 * 
 * Best Use Cases:
 * - Known size limits and memory constraints
 * - High-performance applications requiring predictable memory usage
 * - Embedded systems or performance-critical code
 * 
 * Time Complexities:
 * - Push: O(1)
 * - Pop: O(1)
 * - Peek: O(1)
 * - Search: O(n)
 * - isEmpty/isFull/size: O(1)
 * 
 * Space Complexity: O(capacity)
 * 
 * @param <T> the type of elements stored in this stack
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class ArrayStack<T> {
    
    // Instance variables
    private T[] stack;
    private int top;
    private int capacity;
    
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Default constructor that creates a stack with default capacity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(DEFAULT_CAPACITY)
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.capacity = DEFAULT_CAPACITY;
        this.stack = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    /**
     * Constructor that creates a stack with specified capacity.
     * 
     * @param capacity the maximum number of elements this stack can hold
     * @throws IllegalArgumentException if capacity is negative or zero
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, got: " + capacity);
        }
        this.capacity = capacity;
        this.stack = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    // ==================== CORE STACK OPERATIONS ====================
    
    /**
     * Pushes an element onto the top of the stack.
     * 
     * @param element the element to be pushed onto the stack
     * @throws StackOverflowException if the stack is at maximum capacity
     * @throws IllegalArgumentException if element is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot push null element onto stack");
        }
        if (isFull()) {
            throw new StackOverflowException("Cannot push '" + element + "' - stack is at maximum capacity (" + capacity + ")");
        }
        
        stack[++top] = element;
        System.out.println("✅ Pushed '" + element + "' onto stack. Current size: " + size());
    }
    
    /**
     * Removes and returns the element at the top of the stack.
     * 
     * @return the element at the top of the stack
     * @throws StackUnderflowException if the stack is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public T pop() {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot pop from empty stack");
        }
        
        T element = stack[top];
        stack[top] = null; // Help GC
        top--;
        
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
     * Checks if the stack is full.
     * 
     * @return true if the stack is at maximum capacity, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull() {
        return top == capacity - 1;
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
     * Removes all elements from the stack, making it empty.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        // Help GC by nullifying references
        Arrays.fill(stack, 0, size(), null);
        top = -1;
        System.out.println("✅ Stack cleared. All elements removed.");
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
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Returns the maximum capacity of the stack.
     * 
     * @return the maximum number of elements this stack can hold
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Displays the current state of the stack from top to bottom.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printStack() {
        System.out.println("\n📚 Current Stack State:");
        System.out.println("Capacity: " + capacity + " | Size: " + size() + " | Available: " + (capacity - size()));
        
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
     * @return string representation showing elements from top to bottom
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "ArrayStack(empty)";
        }
        
        StringBuilder sb = new StringBuilder("ArrayStack[");
        for (int i = top; i >= 0; i--) {
            sb.append(stack[i]);
            if (i > 0) sb.append(", ");
        }
        sb.append("] (size=").append(size()).append("/").append(capacity).append(")");
        return sb.toString();
    }
    
    // ==================== INTERACTIVE DEMO ====================
    
    /**
     * Interactive demonstration of the ArrayStack with a comprehensive menu system.
     * Allows users to test all operations and observe the stack behavior.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayStack<String> stack = null;
        
        System.out.println("🏗️  ARRAY-BASED STACK DEMONSTRATION");
        System.out.println("=====================================");
        System.out.println("Welcome to the interactive Array Stack demo!");
        System.out.println("This demonstrates a fixed-capacity stack with overflow protection.\n");
        
        // Initialize stack
        System.out.print("Enter stack capacity (default 5): ");
        String input = scanner.nextLine().trim();
        int capacity = input.isEmpty() ? 5 : Integer.parseInt(input);
        stack = new ArrayStack<>(capacity);
        
        System.out.println("✅ Created ArrayStack with capacity: " + capacity);
        
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           ARRAY STACK OPERATIONS MENU");
            System.out.println("=".repeat(50));
            System.out.println("1.  Push Element");
            System.out.println("2.  Pop Element");
            System.out.println("3.  Peek/Top Element");
            System.out.println("4.  Check if Empty");
            System.out.println("5.  Check if Full");
            System.out.println("6.  Get Size");
            System.out.println("7.  Get Capacity");
            System.out.println("8.  Search Element");
            System.out.println("9.  Clear Stack");
            System.out.println("10. Print Stack");
            System.out.println("11. Convert to Array");
            System.out.println("12. Stack Information");
            System.out.println("0.  Exit");
            System.out.println("=".repeat(50));
            
            // Show current stack state
            stack.printStack();
            
            System.out.print("Enter your choice (0-12): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1: // Push
                        System.out.print("Enter element to push: ");
                        String pushElement = scanner.nextLine().trim();
                        if (!pushElement.isEmpty()) {
                            stack.push(pushElement);
                        } else {
                            System.out.println("❌ Cannot push empty element");
                        }
                        break;
                        
                    case 2: // Pop
                        try {
                            String poppedElement = stack.pop();
                            System.out.println("📤 Popped element: '" + poppedElement + "'");
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
                        
                    case 5: // Is Full
                        boolean full = stack.isFull();
                        System.out.println("🔍 Stack is " + (full ? "FULL" : "NOT FULL"));
                        break;
                        
                    case 6: // Size
                        System.out.println("📏 Current size: " + stack.size());
                        break;
                        
                    case 7: // Capacity
                        System.out.println("📦 Maximum capacity: " + stack.getCapacity());
                        break;
                        
                    case 8: // Search
                        System.out.print("Enter element to search: ");
                        String searchElement = scanner.nextLine().trim();
                        if (!searchElement.isEmpty()) {
                            stack.search(searchElement);
                        } else {
                            System.out.println("❌ Cannot search for empty element");
                        }
                        break;
                        
                    case 9: // Clear
                        stack.clear();
                        break;
                        
                    case 10: // Print
                        stack.printStack();
                        break;
                        
                    case 11: // To Array
                        Object[] array = stack.toArray();
                        System.out.println("📋 Stack as array (bottom to top): " + Arrays.toString(array));
                        break;
                        
                    case 12: // Information
                        System.out.println("\n📊 STACK INFORMATION:");
                        System.out.println("Type: Array-Based Stack (Fixed Capacity)");
                        System.out.println("Capacity: " + stack.getCapacity());
                        System.out.println("Current Size: " + stack.size());
                        System.out.println("Available Space: " + (stack.getCapacity() - stack.size()));
                        System.out.println("Is Empty: " + stack.isEmpty());
                        System.out.println("Is Full: " + stack.isFull());
                        System.out.println("Memory Layout: Contiguous array storage");
                        System.out.println("Time Complexity: O(1) for push, pop, peek");
                        break;
                        
                    case 0: // Exit
                        running = false;
                        System.out.println("👋 Thank you for using Array Stack Demo!");
                        System.out.println("🎓 You've learned about fixed-capacity stack operations!");
                        break;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter a number between 0-12.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            } catch (StackOverflowException | IllegalArgumentException e) {
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