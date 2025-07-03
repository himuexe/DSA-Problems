public class ReverseLL {
     class Node{
        int data;
        Node next;
        Node (int data ){
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public void reverse(){
        Node prev = null;
        Node curr = tail= head;
        Node next;
        while(curr !=null){
            next = curr.next;
            curr.next = prev;
            prev=curr;
            curr=next;
        }
        head = prev;
    }
    public void print(){
        if(head == null){
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args){
        ReverseLL ll = new ReverseLL();
        ll.addFirst(5);
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);
        ll.print();
        ll.reverse();
        ll.print();

    }
}
