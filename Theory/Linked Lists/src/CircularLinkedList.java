import nodes.CircularNode;
import java.util.Scanner;

/**
 * CircularLinkedList implementation with comprehensive operations.
 * A circular linked list is a linear data structure where each element (node)
 * contains data and a reference to the next node, with the last node pointing
 * back to the first node, creating a circular structure.
 * 
 * This enables continuous traversal without null termination and efficient
 * operations at both ends. Educational implementation for DSA learning.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class CircularLinkedList {
    
    // =====================================================
    // === INSERTION OPERATIONS ===
    // =====================================================
    
    /**
     * Insert a new node at the front of the circular linked list.
     * Requires finding the last node to update its next pointer.
     * 
     * Time Complexity: O(n) - need to find the last node to update its next pointer
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param data data to be inserted
     * @return new head of the circular linked list
     * 
     * @example
     * CircularNode head = null;
     * head = insertAtFront(head, 10);  // List: 10 -> 10 (circular)
     * head = insertAtFront(head, 5);   // List: 5 -> 10 -> 5 (circular)
     */
    public static CircularNode insertAtFront(CircularNode head, int data) {
        CircularNode newNode = new CircularNode(data);
        
        // If list is empty, create a single node circular list
        if (head == null) {
            newNode.next = newNode; // Point to itself
            System.out.println("✓ Inserted " + data + " as the first node (circular)");
            return newNode;
        }
        
        // Find the last node (the one pointing to head)
        CircularNode current = head;
        while (current.next != head) {
            current = current.next;
        }
        
        // Insert new node at front
        newNode.next = head;
        current.next = newNode; // Last node now points to new head
        
        System.out.println("✓ Inserted " + data + " at the front");
        return newNode;
    }
    
    /**
     * Insert a new node at the end of the circular linked list.
     * Requires traversal to the last node.
     * 
     * Time Complexity: O(n) - need to traverse to the last node
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param data data to be inserted
     * @return head of the circular linked list
     * 
     * @example
     * head = insertAtEnd(head, 20);  // List: 5 -> 10 -> 20 -> 5 (circular)
     */
    public static CircularNode insertAtEnd(CircularNode head, int data) {
        CircularNode newNode = new CircularNode(data);
        
        // If list is empty, create a single node circular list
        if (head == null) {
            newNode.next = newNode; // Point to itself
            System.out.println("✓ Inserted " + data + " as the first node (circular)");
            return newNode;
        }
        
        // Find the last node (the one pointing to head)
        CircularNode current = head;
        while (current.next != head) {
            current = current.next;
        }
        
        // Insert new node at end
        current.next = newNode;
        newNode.next = head; // Maintain circular property
        
        System.out.println("✓ Inserted " + data + " at the end");
        return head;
    }
    
    /**
     * Insert a new node at the specified position (0-indexed).
     * Position 0 means insertion at the front.
     * 
     * Time Complexity: O(n) - need to traverse to the position
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param position the position where the new node should be inserted (0-indexed)
     * @param data data to be inserted
     * @return head of the circular linked list
     * 
     * @example
     * head = insertAtPosition(head, 1, 15);  // List: 5 -> 15 -> 10 -> 20 -> 5 (circular)
     */
    public static CircularNode insertAtPosition(CircularNode head, int position, int data) {
        if (position < 0) {
            System.out.println("✗ Invalid position: " + position + ". Position must be non-negative.");
            return head;
        }
        
        // If inserting at position 0, use insertAtFront
        if (position == 0) {
            return insertAtFront(head, data);
        }
        
        // If list is empty and position > 0
        if (head == null) {
            System.out.println("✗ Position " + position + " is out of bounds for empty list");
            return null;
        }
        
        // Traverse to the position
        CircularNode current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            // Check if we've completed a full circle
            if (current == head) {
                System.out.println("✗ Position " + position + " is out of bounds");
                return head;
            }
        }
        
        // Create new node and insert it
        CircularNode newNode = new CircularNode(data);
        newNode.next = current.next;
        current.next = newNode;
        
        System.out.println("✓ Inserted " + data + " at position " + position);
        return head;
    }
    
    /**
     * Insert a new node after a node with given key value.
     * Searches for the first occurrence of the key and inserts after it.
     * 
     * Time Complexity: O(n) - need to search for the key
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param key the value after which new node should be inserted
     * @param data data to be inserted
     * @return head of the circular linked list
     * 
     * @example
     * head = insertAfter(head, 10, 12);  // List: 5 -> 15 -> 10 -> 12 -> 20 -> 5 (circular)
     */
    public static CircularNode insertAfter(CircularNode head, int key, int data) {
        if (head == null) {
            System.out.println("✗ List is empty, cannot insert after " + key);
            return null;
        }
        
        // Search for the key
        CircularNode current = head;
        do {
            if (current.data == key) {
                CircularNode newNode = new CircularNode(data);
                newNode.next = current.next;
                current.next = newNode;
                System.out.println("✓ Inserted " + data + " after " + key);
                return head;
            }
            current = current.next;
        } while (current != head);
        
        System.out.println("✗ Key " + key + " not found in the list");
        return head;
    }
    
    /**
     * Insert a new node before a node with given key value.
     * Searches for the first occurrence of the key and inserts before it.
     * This was the missing operation that needed to be implemented.
     * 
     * Time Complexity: O(n) - need to search for the key and its predecessor
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param key the value before which new node should be inserted
     * @param data data to be inserted
     * @return head of the circular linked list
     * 
     * @example
     * head = insertBefore(head, 15, 8);  // List: 5 -> 8 -> 15 -> 10 -> 12 -> 20 -> 5 (circular)
     */
    public static CircularNode insertBefore(CircularNode head, int key, int data) {
        if (head == null) {
            System.out.println("✗ List is empty, cannot insert before " + key);
            return null;
        }
        
        // If key is at the head, insert at front
        if (head.data == key) {
            return insertAtFront(head, data);
        }
        
        // Find the node before the key
        CircularNode current = head;
        do {
            if (current.next.data == key) {
                // Found the node before the key
                CircularNode newNode = new CircularNode(data);
                newNode.next = current.next;
                current.next = newNode;
                System.out.println("✓ Inserted " + data + " before " + key);
                return head;
            }
            current = current.next;
        } while (current != head);
        
        System.out.println("✗ Key " + key + " not found in the list");
        return head;
    }
    
    // =====================================================
    // === DELETION OPERATIONS ===
    // =====================================================
    
    /**
     * Delete the first node from the circular linked list.
     * Requires finding the last node to update its next pointer.
     * 
     * Time Complexity: O(n) - need to find the last node to update its next pointer
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @return new head of the circular linked list
     * 
     * @example
     * head = deleteAtFront(head);  // Removes first node
     */
    public static CircularNode deleteAtFront(CircularNode head) {
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If only one node exists
        if (head.next == head) {
            System.out.println("✓ Deleting node with data: " + head.data);
            return null;
        }
        
        // Find the last node (the one pointing to head)
        CircularNode current = head;
        while (current.next != head) {
            current = current.next;
        }
        
        System.out.println("✓ Deleting node with data: " + head.data);
        
        // Update last node to point to new head
        current.next = head.next;
        CircularNode newHead = head.next;
        
        // Clear the deleted node's reference
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Delete the last node from the circular linked list.
     * Requires traversal to find the second-to-last node.
     * 
     * Time Complexity: O(n) - need to traverse to the second-to-last node
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @return head of the circular linked list
     * 
     * @example
     * head = deleteAtEnd(head);  // Removes last node
     */
    public static CircularNode deleteAtEnd(CircularNode head) {
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If only one node exists
        if (head.next == head) {
            System.out.println("✓ Deleting node with data: " + head.data);
            return null;
        }
        
        // Find the second-to-last node
        CircularNode current = head;
        while (current.next.next != head) {
            current = current.next;
        }
        
        System.out.println("✓ Deleting node with data: " + current.next.data);
        
        // Remove the last node and maintain circular property
        CircularNode nodeToDelete = current.next;
        current.next = head;
        
        // Clear the deleted node's reference
        nodeToDelete.next = null;
        
        return head;
    }
    
    /**
     * Delete a node at the specified position (0-indexed).
     * Position 0 means deletion of the first node.
     * 
     * Time Complexity: O(n) - need to traverse to the position
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param position the position of the node to be deleted (0-indexed)
     * @return head of the circular linked list
     * 
     * @example
     * head = deleteAtPosition(head, 2);  // Deletes node at index 2
     */
    public static CircularNode deleteAtPosition(CircularNode head, int position) {
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        if (position < 0) {
            System.out.println("✗ Invalid position: " + position + ". Position must be non-negative.");
            return head;
        }
        
        // If deleting the first node (position 0)
        if (position == 0) {
            return deleteAtFront(head);
        }
        
        // Traverse to the node before the position to be deleted
        CircularNode current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            // Check if we've completed a full circle
            if (current == head) {
                System.out.println("✗ Position " + position + " is out of bounds");
                return head;
            }
        }
        
        // Check if the position to delete exists
        if (current.next == head && position > 0) {
            System.out.println("✗ Position " + position + " is out of bounds");
            return head;
        }
        
        System.out.println("✓ Deleting node with data: " + current.next.data + " at position " + position);
        
        // Delete the node at the specified position
        CircularNode nodeToDelete = current.next;
        current.next = nodeToDelete.next;
        
        // Clear the deleted node's reference
        nodeToDelete.next = null;
        
        return head;
    }
    
    /**
     * Delete the first occurrence of a node with given key value.
     * Searches for the key and removes the first matching node.
     * 
     * Time Complexity: O(n) - need to search for the key
     * Space Complexity: O(1)
     * 
     * @param head current head of the circular linked list
     * @param key the value of the node to be deleted
     * @return head of the circular linked list
     * 
     * @example
     * head = deleteByValue(head, 15);  // Deletes first node with value 15
     */
    public static CircularNode deleteByValue(CircularNode head, int key) {
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If key is at the head
        if (head.data == key) {
            return deleteAtFront(head);
        }
        
        // Search for the node with the key
        CircularNode current = head;
        do {
            if (current.next.data == key) {
                System.out.println("✓ Deleting node with data: " + current.next.data);
                
                // Delete the node with the key
                CircularNode nodeToDelete = current.next;
                current.next = nodeToDelete.next;
                
                // Clear the deleted node's reference
                nodeToDelete.next = null;
                
                return head;
            }
            current = current.next;
        } while (current != head);
        
        System.out.println("✗ Key " + key + " not found in the list");
        return head;
    }
    
    // =====================================================
    // === UTILITY OPERATIONS ===
    // =====================================================
    
    /**
     * Print all elements in the circular linked list.
     * Traverses the entire list once and displays each node's data.
     * 
     * Time Complexity: O(n) - traverse the list once
     * Space Complexity: O(1)
     * 
     * @param head head of the circular linked list to be printed
     * 
     * @example
     * printList(head);  // Output: 5 -> 8 -> 10 -> 12 -> 20 -> (back to 5)
     */
    public static void printList(CircularNode head) {
        if (head == null) {
            System.out.println("📋 List is empty");
            return;
        }
        
        System.out.print("📋 Circular List: ");
        CircularNode current = head;
        
        // Traverse the list once and print each node's data
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != head) {
                System.out.print(" -> ");
            }
        } while (current != head);
        
        System.out.println(" -> (back to " + head.data + ")");
    }
    
    /**
     * Get the size of the circular linked list.
     * Counts the total number of nodes by traversing the entire list once.
     * 
     * Time Complexity: O(n) - traverse the list once
     * Space Complexity: O(1)
     * 
     * @param head head of the circular linked list
     * @return size of the circular linked list
     * 
     * @example
     * int size = getSize(head);  // Returns number of nodes
     */
    public static int getSize(CircularNode head) {
        if (head == null) {
            return 0;
        }
        
        int count = 0;
        CircularNode current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);
        
        return count;
    }
    
    /**
     * Check if the circular linked list is empty.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param head head of the circular linked list
     * @return true if the list is empty, false otherwise
     * 
     * @example
     * boolean empty = isEmpty(head);  // Returns true if list is empty
     */
    public static boolean isEmpty(CircularNode head) {
        return head == null;
    }
    
    /**
     * Check if the linked list structure is properly circular.
     * Verifies that traversing the list eventually leads back to the head.
     * 
     * Time Complexity: O(n) - traverse to verify circular property
     * Space Complexity: O(1)
     * 
     * @param head head of the circular linked list
     * @return true if the list is properly circular, false otherwise
     * 
     * @example
     * boolean circular = isCircular(head);  // Returns true for proper circular list
     */
    public static boolean isCircular(CircularNode head) {
        if (head == null) {
            return true; // Empty list is considered circular
        }
        
        CircularNode current = head.next;
        while (current != null && current != head) {
            current = current.next;
        }
        
        return current == head;
    }
    
    // =====================================================
    // === DEMO MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive demonstration of all circular linked list operations.
     * Provides a menu-driven interface for testing all functionality.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CircularNode head = null;
        
        System.out.println("🔗 CIRCULAR LINKED LIST OPERATIONS DEMO");
        System.out.println("========================================");
        
        while (true) {
            System.out.println("\n📋 Current List:");
            printList(head);
            System.out.println("\n📊 List Size: " + getSize(head));
            System.out.println("🔄 Is Circular: " + isCircular(head));
            
            System.out.println("\n🎯 Choose an operation:");
            System.out.println("1. Insert at Front");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Insert After Value");
            System.out.println("5. Insert Before Value");
            System.out.println("6. Delete at Front");
            System.out.println("7. Delete at End");
            System.out.println("8. Delete at Position");
            System.out.println("9. Delete by Value");
            System.out.println("0. Exit");
            
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter data to insert at front: ");
                    int frontData = scanner.nextInt();
                    head = insertAtFront(head, frontData);
                    break;
                    
                case 2:
                    System.out.print("Enter data to insert at end: ");
                    int endData = scanner.nextInt();
                    head = insertAtEnd(head, endData);
                    break;
                    
                case 3:
                    System.out.print("Enter position (0-indexed): ");
                    int position = scanner.nextInt();
                    System.out.print("Enter data to insert: ");
                    int posData = scanner.nextInt();
                    head = insertAtPosition(head, position, posData);
                    break;
                    
                case 4:
                    System.out.print("Enter value to insert after: ");
                    int afterKey = scanner.nextInt();
                    System.out.print("Enter data to insert: ");
                    int afterData = scanner.nextInt();
                    head = insertAfter(head, afterKey, afterData);
                    break;
                    
                case 5:
                    System.out.print("Enter value to insert before: ");
                    int beforeKey = scanner.nextInt();
                    System.out.print("Enter data to insert: ");
                    int beforeData = scanner.nextInt();
                    head = insertBefore(head, beforeKey, beforeData);
                    break;
                    
                case 6:
                    head = deleteAtFront(head);
                    break;
                    
                case 7:
                    head = deleteAtEnd(head);
                    break;
                    
                case 8:
                    System.out.print("Enter position to delete (0-indexed): ");
                    int delPosition = scanner.nextInt();
                    head = deleteAtPosition(head, delPosition);
                    break;
                    
                case 9:
                    System.out.print("Enter value to delete: ");
                    int delValue = scanner.nextInt();
                    head = deleteByValue(head, delValue);
                    break;
                    
                case 0:
                    System.out.println("\n🎉 Thank you for using Circular Linked List Demo!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
} 