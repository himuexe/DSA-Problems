import exceptions.StackUnderflowException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Linked List-based Stack Implementation
 * 
 * This class implements a stack data structure using a linked list as the underlying
 * storage mechanism. It follows the LIFO (Last In, First Out) principle and provides
 * dynamic sizing with no fixed capacity limit.
 * 
 * Key Features:
 * - Dynamic size that grows as needed
 * - No size limit except available memory
 * - Node-based structure with flexible memory allocation
 * - Easy to extend with additional operations
 * - Memory efficient - only allocates what's needed
 * 
 * Best Use Cases:
 * - Unknown or highly variable size requirements
 * - Applications requiring frequent size changes
 * - Memory-efficient applications where only needed space is allocated
 * - Educational purposes to understand linked data structures
 * 
 * Time Complexities:
 * - Push: O(1)
 * - Pop: O(1)
 * - Peek: O(1)
 * - Search: O(n)
 * - isEmpty/size: O(1)
 * - Clear: O(n) due to node traversal for cleanup
 * 
 * Space Complexity: O(n) where n is the number of elements
 * 
 * @param <T> the type of elements stored in this stack
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class LinkedStack<T> {
    
    /**
     * Node class for the linked list structure.
     * Each node contains data and a reference to the next node.
     */
    private class StackNode {
        T data;
        StackNode next;
        
        /**
         * Creates a new node with the given data.
         * 
         * @param data the data to store in this node
         */
        StackNode(T data) {
            this.data = data;
            this.next = null;
        }
        
        @Override
        public String toString() {
            return data != null ? data.toString() : "null";
        }
    }
    
    // Instance variables
    private StackNode top;
    private int size;
    
    /**
     * Default constructor that creates an empty linked stack.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }
    
    // ==================== CORE STACK OPERATIONS ====================
    
    /**
     * Pushes an element onto the top of the stack.
     * 
     * @param element the element to be pushed onto the stack
     * @throws IllegalArgumentException if element is null
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot push null element onto stack");
        }
        
        StackNode newNode = new StackNode(element);
        newNode.next = top;
        top = newNode;
        size++;
        
        System.out.println("✅ Pushed '" + element + "' onto stack. Current size: " + size);
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
        
        T element = top.data;
        StackNode nodeToRemove = top;
        top = top.next;
        size--;
        
        // Help GC
        nodeToRemove.next = null;
        nodeToRemove.data = null;
        
        System.out.println("✅ Popped '" + element + "' from stack. Current size: " + size);
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
        return top.data;
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
        return top == null;
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
        return size;
    }
    
    // ==================== STACK-SPECIFIC OPERATIONS ====================
    
    /**
     * Removes all elements from the stack, making it empty.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void clear() {
        // Traverse and clear all nodes to help GC
        StackNode current = top;
        while (current != null) {
            StackNode next = current.next;
            current.data = null;
            current.next = null;
            current = next;
        }
        
        top = null;
        size = 0;
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
        
        StackNode current = top;
        int position = 1;
        
        while (current != null) {
            if (element.equals(current.data)) {
                System.out.println("🔍 Found '" + element + "' at position " + position + " from top");
                return position;
            }
            current = current.next;
            position++;
        }
        
        System.out.println("❌ Element '" + element + "' not found in stack");
        return -1;
    }
    
    // ==================== UTILITY OPERATIONS ====================
    
    /**
     * Displays the current state of the stack from top to bottom.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printStack() {
        System.out.println("\n🔗 Current Stack State (Linked List):");
        System.out.println("Size: " + size + " | Memory: Dynamic allocation");
        
        if (isEmpty()) {
            System.out.println("│ (empty stack) │");
        } else {
            StackNode current = top;
            int position = 1;
            
            System.out.println("┌" + "─".repeat(15) + "┐");
            while (current != null) {
                String indicator = (position == 1) ? " ← TOP" : "";
                System.out.printf("│ %-12s │%s%n", current.data, indicator);
                
                current = current.next;
                position++;
                
                if (current != null) {
                    System.out.println("├" + "─".repeat(15) + "┤");
                }
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
        if (isEmpty()) {
            return (T[]) new Object[0];
        }
        
        T[] result = (T[]) new Object[size];
        StackNode current = top;
        
        // Fill array from top to bottom, then reverse for bottom-to-top order
        for (int i = size - 1; i >= 0; i--) {
            result[i] = current.data;
            current = current.next;
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
            return "LinkedStack(empty)";
        }
        
        StringBuilder sb = new StringBuilder("LinkedStack[");
        StackNode current = top;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        
        sb.append("] (size=").append(size).append(")");
        return sb.toString();
    }
    
    // ==================== ADDITIONAL UTILITY METHODS ====================
    
    /**
     * Returns the memory usage information for educational purposes.
     * 
     * @return string describing memory usage characteristics
     */
    public String getMemoryInfo() {
        long estimatedBytes = size * (8 + 8 + 16); // Rough estimate: data reference + next reference + object overhead
        return String.format("Estimated memory: ~%d bytes for %d nodes", estimatedBytes, size);
    }
    
    /**
     * Checks if the linked structure is properly maintained (for debugging).
     * 
     * @return true if the structure is valid, false otherwise
     */
    public boolean isStructureValid() {
        if (isEmpty()) {
            return size == 0 && top == null;
        }
        
        int nodeCount = 0;
        StackNode current = top;
        
        while (current != null && nodeCount <= size + 1) { // +1 to catch infinite loops
            nodeCount++;
            current = current.next;
        }
        
        boolean valid = nodeCount == size && current == null;
        if (!valid) {
            System.out.println("⚠️ Structure validation failed: counted " + nodeCount + " nodes, expected " + size);
        }
        return valid;
    }
    
    // ==================== INTERACTIVE DEMO ====================
    
    /**
     * Interactive demonstration of the LinkedStack with a comprehensive menu system.
     * Allows users to test all operations and observe the stack behavior.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedStack<String> stack = new LinkedStack<>();
        
        System.out.println("🔗 LINKED LIST-BASED STACK DEMONSTRATION");
        System.out.println("=========================================");
        System.out.println("Welcome to the interactive Linked Stack demo!");
        System.out.println("This demonstrates a dynamic-size stack with unlimited capacity.\n");
        
        System.out.println("✅ Created LinkedStack with dynamic sizing");
        
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("          LINKED STACK OPERATIONS MENU");
            System.out.println("=".repeat(50));
            System.out.println("1.  Push Element");
            System.out.println("2.  Pop Element");
            System.out.println("3.  Peek/Top Element");
            System.out.println("4.  Check if Empty");
            System.out.println("5.  Get Size");
            System.out.println("6.  Search Element");
            System.out.println("7.  Clear Stack");
            System.out.println("8.  Print Stack");
            System.out.println("9.  Convert to Array");
            System.out.println("10. Stack Information");
            System.out.println("11. Memory Information");
            System.out.println("12. Validate Structure");
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
                        
                    case 5: // Size
                        System.out.println("📏 Current size: " + stack.size());
                        break;
                        
                    case 6: // Search
                        System.out.print("Enter element to search: ");
                        String searchElement = scanner.nextLine().trim();
                        if (!searchElement.isEmpty()) {
                            stack.search(searchElement);
                        } else {
                            System.out.println("❌ Cannot search for empty element");
                        }
                        break;
                        
                    case 7: // Clear
                        stack.clear();
                        break;
                        
                    case 8: // Print
                        stack.printStack();
                        break;
                        
                    case 9: // To Array
                        Object[] array = stack.toArray();
                        System.out.println("📋 Stack as array (bottom to top): " + Arrays.toString(array));
                        break;
                        
                    case 10: // Information
                        System.out.println("\n📊 STACK INFORMATION:");
                        System.out.println("Type: Linked List-Based Stack (Dynamic Size)");
                        System.out.println("Current Size: " + stack.size());
                        System.out.println("Capacity: Unlimited (memory permitting)");
                        System.out.println("Is Empty: " + stack.isEmpty());
                        System.out.println("Memory Layout: Non-contiguous linked nodes");
                        System.out.println("Time Complexity: O(1) for push, pop, peek");
                        System.out.println("Space Efficiency: Only allocates needed memory");
                        break;
                        
                    case 11: // Memory Info
                        System.out.println("💾 " + stack.getMemoryInfo());
                        break;
                        
                    case 12: // Validate Structure
                        boolean valid = stack.isStructureValid();
                        System.out.println("🔧 Structure validation: " + (valid ? "✅ VALID" : "❌ INVALID"));
                        break;
                        
                    case 0: // Exit
                        running = false;
                        System.out.println("👋 Thank you for using Linked Stack Demo!");
                        System.out.println("🎓 You've learned about dynamic-size stack operations!");
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