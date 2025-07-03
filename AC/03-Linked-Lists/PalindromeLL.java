public class PalindromeLL {
    class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next=null;
        }
    }
    public static Node head;

    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
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

    public Node findMid(){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return true;
        }
        Node mid = findMid();
        Node curr = mid;
        Node prev =null;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev=curr;
            curr=next;
        }
        Node right = prev;
        Node left = head;
        while(right != null){
            if(left.data != right.data){
                return false;
            }
            left = left.next;
            right=right.next;
        }
        return true;
    }

    public static void main(String[] args){
        PalindromeLL ll = new PalindromeLL();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(2);
        ll.print();
        if(ll.isPalindrome()){
            System.out.println("Palindrome hai");
        }
        else{
            System.out.println("Nahi hai");
        }

    }

    
}
