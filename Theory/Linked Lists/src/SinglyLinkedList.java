import nodes.SinglyNode;
import java.util.Scanner;

/**
 * SinglyLinkedList implementation with comprehensive operations.
 * A singly linked list is a linear data structure where each element (node)
 * contains data and a reference to the next node in the sequence.
 * 
 * Educational implementation for DSA learning with detailed documentation
 * and step-by-step operation demonstrations.
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class SinglyLinkedList {
    
    // =====================================================
    // === INSERTION OPERATIONS ===
    // =====================================================
    
    /**
     * Insert a new node at the front of the singly linked list.
     * This operation has constant time complexity as it doesn't require traversal.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param data data to be inserted
     * @return new head of the singly linked list
     * 
     * @example
     * SinglyNode head = null;
     * head = insertAtFront(head, 10);  // List: 10
     * head = insertAtFront(head, 5);   // List: 5 -> 10
     */
    public static SinglyNode insertAtFront(SinglyNode head, int data) {
        // Create a new node with the given data
        SinglyNode newNode = new SinglyNode(data);
        
        // Point the new node to the current head
        newNode.next = head;
        
        System.out.println("✓ Inserted " + data + " at the front");
        
        // Return the new node as the new head
        return newNode;
    }
    
    /**
     * Insert a new node at the end of the singly linked list.
     * Requires traversal to the last node, resulting in linear time complexity.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param data data to be inserted
     * @return head of the singly linked list
     * 
     * @example
     * SinglyNode head = insertAtEnd(head, 20);  // List: 5 -> 10 -> 20
     */
    public static SinglyNode insertAtEnd(SinglyNode head, int data) {
        // Create a new node with the given data
        SinglyNode newNode = new SinglyNode(data);
        
        // If the list is empty, return the new node as head
        if (head == null) {
            System.out.println("✓ Inserted " + data + " at the end (first node)");
            return newNode;
        }
        
        // Traverse to the last node
        SinglyNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        // Link the last node to the new node
        current.next = newNode;
        
        System.out.println("✓ Inserted " + data + " at the end");
        
        // Return the original head
        return head;
    }
    
    /**
     * Insert a new node at the specified position (0-indexed).
     * Position 0 means insertion at the front, position n means after n nodes.
     * 
     * Time Complexity: O(n) where n is the position
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param position the position where the new node should be inserted (0-indexed)
     * @param data data to be inserted
     * @return head of the singly linked list
     * 
     * @example
     * head = insertAtPosition(head, 1, 15);  // List: 5 -> 15 -> 10 -> 20
     */
    public static SinglyNode insertAtPosition(SinglyNode head, int position, int data) {
        // Validate position
        if (position < 0) {
            System.out.println("✗ Invalid position: " + position + ". Position must be non-negative.");
            return head;
        }
        
        // If inserting at the front (position 0)
        if (position == 0) {
            return insertAtFront(head, data);
        }
        
        // If list is empty and position > 0
        if (head == null) {
            System.out.println("✗ Position " + position + " is out of bounds for empty list");
            return null;
        }
        
        // Traverse to the position
        SinglyNode current = head;
        for (int i = 0; i < position - 1; i++) {
            if (current.next == null) {
                System.out.println("✗ Position " + position + " is out of bounds");
                return head;
            }
            current = current.next;
        }
        if(current.next == null){
           return insertAtEnd(head, data);
        }
        // Create new node and insert it
        SinglyNode newNode = new SinglyNode(data);
        newNode.next = current.next;
        current.next = newNode;
        
        System.out.println("✓ Inserted " + data + " at position " + position);
        
        return head;
    }
    
    /**
     * Insert a new node after a node with given key value.
     * Searches for the first occurrence of the key and inserts after it.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param key the value after which new node should be inserted
     * @param data data to be inserted
     * @return head of the singly linked list
     * 
     * @example
     * head = insertAfter(head, 10, 12);  // List: 5 -> 15 -> 10 -> 12 -> 20
     */
    public static SinglyNode insertAfter(SinglyNode head, int key, int data) {
        // Check if list is empty
        if (head == null) {
            System.out.println("✗ List is empty, cannot insert after " + key);
            return null;
        }
        
        // Start from the head and search for the key
        SinglyNode current = head;
        
        // Traverse the list to find the node with the key
        while (current != null) {
            if (current.data == key) {
                break;
            }
            current = current.next;
        }
        
        // If key is not found
        if (current == null) {
            System.out.println("✗ Key " + key + " not found in the list");
            return head;
        }
        
        // Create new node and insert it after the found node
        SinglyNode newNode = new SinglyNode(data);
        newNode.next = current.next;  // Point new node to the next node
        current.next = newNode;       // Point current node to new node
        
        System.out.println("✓ Inserted " + data + " after " + key);
        
        return head;
    }
    
    /**
     * Insert a new node before a node with given key value.
     * Searches for the first occurrence of the key and inserts before it.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param key the value before which new node should be inserted
     * @param data data to be inserted
     * @return head of the singly linked list
     * 
     * @example
     * head = insertBefore(head, 15, 8);  // List: 5 -> 8 -> 15 -> 10 -> 12 -> 20
     */
    public static SinglyNode insertBefore(SinglyNode head, int key, int data) {
        // If list is empty
        if (head == null) {
            System.out.println("✗ List is empty, cannot insert before " + key);
            return null;
        }
        
        // If key is at the head, insert at front
        if (head.data == key) {
            return insertAtFront(head, data);
        }
        
        // Search for the node before the key
        SinglyNode current = head;
        while (current.next != null && current.next.data != key) {
            current = current.next;
        }
        
        // If key is not found
        if (current.next == null) {
            System.out.println("✗ Key " + key + " not found in the list");
            return head;
        }
        
        // Insert the new node before the key
        SinglyNode newNode = new SinglyNode(data);
        newNode.next = current.next;  // Point new node to the node with key
        current.next = newNode;       // Point previous node to new node
        
        System.out.println("✓ Inserted " + data + " before " + key);
        
        return head;
    }
    
    // =====================================================
    // === DELETION OPERATIONS ===
    // =====================================================
    
    /**
     * Delete the first node from the singly linked list.
     * This operation has constant time complexity.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @return new head of the singly linked list
     * 
     * @example
     * head = deleteAtFront(head);  // Removes first node
     */
    public static SinglyNode deleteAtFront(SinglyNode head) {
        // If list is empty
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // Store the data of the node being deleted for debugging
        System.out.println("✓ Deleting node with data: " + head.data);
        
        // Move head to the next node
        SinglyNode newHead = head.next;
        
        // Clear the deleted node's reference (good practice)
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Delete the last node from the singly linked list.
     * Requires traversal to find the second-to-last node.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @return head of the singly linked list
     * 
     * @example
     * head = deleteAtEnd(head);  // Removes last node
     */
    public static SinglyNode deleteAtEnd(SinglyNode head) {
        // If list is empty
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If list has only one node
        if (head.next == null) {
            System.out.println("✓ Deleting node with data: " + head.data);
            return null;
        }
        
        // Traverse to the second last node
        SinglyNode current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        // Store the data of the node being deleted for debugging
        System.out.println("✓ Deleting node with data: " + current.next.data);
        
        // Remove the last node
        current.next = null;
        
        return head;
    }
    
    /**
     * Delete a node at the specified position (0-indexed).
     * Position 0 means deletion of the first node.
     * 
     * Time Complexity: O(n) where n is the position
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param position the position of the node to be deleted (0-indexed)
     * @return head of the singly linked list
     * 
     * @example
     * head = deleteAtPosition(head, 2);  // Deletes node at index 2
     */
    public static SinglyNode deleteAtPosition(SinglyNode head, int position) {
        // If list is empty
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If position is negative
        if (position < 0) {
            System.out.println("✗ Invalid position: " + position + ". Position must be non-negative.");
            return head;
        }
        
        // If deleting the first node (position 0)
        if (position == 0) {
            return deleteAtFront(head);
        }
        
        // Traverse to the node before the position to be deleted
        SinglyNode current = head;
        for (int i = 0; i < position - 1; i++) {
            if (current.next == null) {
                System.out.println("✗ Position " + position + " is out of bounds");
                return head;
            }
            current = current.next;
        }
        
        // Check if the position exists
        if (current.next == null) {
            System.out.println("✗ Position " + position + " is out of bounds");
            return head;
        }
        
        // Store the data of the node being deleted for debugging
        System.out.println("✓ Deleting node with data: " + current.next.data + " at position " + position);
        
        // Delete the node at the specified position
        SinglyNode nodeToDelete = current.next;
        current.next = nodeToDelete.next;
        
        // Clear the deleted node's reference (good practice)
        nodeToDelete.next = null;
        
        return head;
    }
    
    /**
     * Delete the first occurrence of a node with given key value.
     * Searches for the key and removes the first matching node.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head current head of the singly linked list
     * @param key the value of the node to be deleted
     * @return head of the singly linked list
     * 
     * @example
     * head = deleteByValue(head, 15);  // Deletes first node with value 15
     */
    public static SinglyNode deleteByValue(SinglyNode head, int key) {
        // If list is empty
        if (head == null) {
            System.out.println("✗ List is empty, nothing to delete");
            return null;
        }
        
        // If the key is at the head
        if (head.data == key) {
            System.out.println("✓ Deleting node with data: " + head.data);
            return head.next;
        }
        
        // Search for the node with the key
        SinglyNode current = head;
        while (current.next != null && current.next.data != key) {
            current = current.next;
        }
        
        // If key is not found
        if (current.next == null) {
            System.out.println("✗ Key " + key + " not found in the list");
            return head;
        }
        
        // Store the data of the node being deleted for debugging
        System.out.println("✓ Deleting node with data: " + current.next.data);
        
        // Delete the node with the key
        SinglyNode nodeToDelete = current.next;
        current.next = nodeToDelete.next;
        
        // Clear the deleted node's reference (good practice)
        nodeToDelete.next = null;
        
        return head;
    }
    
    // =====================================================
    // === UTILITY OPERATIONS ===
    // =====================================================
    
    /**
     * Print all elements in the singly linked list.
     * Traverses the entire list and displays each node's data.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head head of the singly linked list to be printed
     * 
     * @example
     * printList(head);  // Output: 5 -> 8 -> 10 -> 12 -> 20 -> NULL
     */
    public static void printList(SinglyNode head) {
        if (head == null) {
            System.out.println("📋 List is empty");
            return;
        }
        
        System.out.print("📋 List: ");
        SinglyNode current = head;
        
        // Traverse the list and print each node's data
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> NULL");
    }
    
    /**
     * Get the size of the singly linked list.
     * Counts the total number of nodes by traversing the entire list.
     * 
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1)
     * 
     * @param head head of the singly linked list
     * @return size of the singly linked list
     * 
     * @example
     * int size = getSize(head);  // Returns number of nodes
     */
    public static int getSize(SinglyNode head) {
        int count = 0;
        SinglyNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    /**
     * Check if the singly linked list is empty.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param head head of the singly linked list
     * @return true if the list is empty, false otherwise
     * 
     * @example
     * boolean empty = isEmpty(head);  // Returns true if list is empty
     */
    public static boolean isEmpty(SinglyNode head) {
        return head == null;
    }
    
    // =====================================================
    // === DEMO MAIN METHOD ===
    // =====================================================
    
    /**
     * Interactive demonstration of all singly linked list operations.
     * Provides a menu-driven interface for testing all functionality.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SinglyNode head = null;
        
        System.out.println("🔗 SINGLY LINKED LIST OPERATIONS DEMO");
        System.out.println("=====================================");
        
        while (true) {
            System.out.println("\n📋 Current List:");
            printList(head);
            System.out.println("\n📊 List Size: " + getSize(head));
            
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
                    System.out.println("\n🎉 Thank you for using Singly Linked List Demo!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
} 