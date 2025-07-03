public class ReverseDLL {
    private Node head;
    private Node tail;

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

    // Add a node to the end of the list
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
    }

    // Reverse the doubly linked list
    public void reverse() {
        Node current = head;
        Node temp = null;

        // Swap next and prev pointers for all nodes
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // Move to the next node (originally current.next)
        }

        // Update head and tail
        if (temp != null) {
            tail = head;
            head = temp.prev;
        }
    }

    // Print the list (forward traversal)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.add(10);
        dll.add(20);
        dll.add(30);
        dll.add(40);

        System.out.println("Original List:");
        dll.printList(); // Output: 10 <-> 20 <-> 30 <-> 40 <-> null

        dll.reverse();
        System.out.println("Reversed List:");
        dll.printList(); // Output: 40 <-> 30 <-> 20 <-> 10 <-> null
    }
}