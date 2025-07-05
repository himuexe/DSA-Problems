/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return head;
        }
        
        // Step 1: Create copy nodes and insert them between original nodes
        Node curr = head;
        while(curr != null){
            Node temp = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = temp;
            curr = temp;
        }
        
        // Step 2: Assign random pointers for the copy nodes
        curr = head;
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Step 3: Separate the original and copied lists
        curr = head;
        Node copyHead = head.next;
        Node copyCurr = copyHead;
        
        while(curr != null){
            curr.next = curr.next.next;
            curr = curr.next;
            
            if(copyCurr.next != null){
                copyCurr.next = copyCurr.next.next;
                copyCurr = copyCurr.next;
            }
        }
        
        return copyHead;
    }
}