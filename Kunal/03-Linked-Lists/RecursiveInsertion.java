class Node {
    int data;
    Node next;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    
    // Recursive method to insert at end of the list
    public void insertRecursive(int data) {
        head = insertRecursive(head, data);
    }
    
    private Node insertRecursive(Node current, int data) {
        // Base case: if we've reached the end, create new node
        if (current == null) {
            return new Node(data);
        }
        
        // Recursive case: keep traversing until we reach the end
        current.next = insertRecursive(current.next, data);
        return current;
    }
    
    // Recursive method to insert at specific position (0-based index)
    public void insertAtPositionRecursive(int data, int position) {
        head = insertAtPositionRecursive(head, data, position);
    }
    
    private Node insertAtPositionRecursive(Node current, int data, int position) {
        // Base case 1: position is 0 or list is empty
        if (position == 0 || current == null) {
            Node newNode = new Node(data);
            newNode.next = current;
            return newNode;
        }
        
        // Recursive case: move to next node and decrement position
        current.next = insertAtPositionRecursive(current.next, data, position - 1);
        return current;
    }
    
    // Method to print the linked list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class RecursiveInsertion {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        // Inserting elements at the end using recursion
        list.insertRecursive(10);
        list.insertRecursive(20);
        list.insertRecursive(30);
        
        System.out.println("List after inserting at end:");
        list.printList();  // Output: 10 20 30
        
        // Inserting at specific positions using recursion
        list.insertAtPositionRecursive(15, 1);  // Insert at position 1
        list.insertAtPositionRecursive(5, 0);   // Insert at position 0 (head)
        
        System.out.println("List after inserting at positions:");
        list.printList();  // Output: 5 10 15 20 30
    }
}