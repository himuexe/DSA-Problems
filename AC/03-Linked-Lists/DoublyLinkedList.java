public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    // Node class
    private static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    // Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add a node at the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Method to remove a node by value (first occurrence)
    public boolean remove(int data) {
        if (head == null) {
            return false; // List is empty
        }

        Node current = head;
        while (current != null) {
            if (current.data == data) {
                // Case 1: Removing the head node
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null; // List becomes empty
                    }
                }
                // Case 2: Removing the tail node
                else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                }
                // Case 3: Removing a middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // Node not found
    }

    // Method to print the list (forward traversal)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to print the list (backward traversal)
    public void printListReverse() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Main method for testing
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Adding elements
        dll.add(10);
        dll.add(20);
        dll.add(30);
        dll.add(40);

        System.out.println("Original List:");
        dll.printList(); // Output: 10 <-> 20 <-> 30 <-> 40 <-> null

        // Removing elements
        dll.remove(20);
        System.out.println("After removing 20:");
        dll.printList(); // Output: 10 <-> 30 <-> 40 <-> null

        dll.remove(40);
        System.out.println("After removing 40:");
        dll.printList(); // Output: 10 <-> 30 <-> null

        // Reverse traversal
        System.out.println("Reverse traversal:");
        dll.printListReverse(); // Output: 30 <-> 10 <-> null
    }
}