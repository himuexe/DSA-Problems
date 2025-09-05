public class CircularQueueArray {
    static class Queue{
        static int arr[];
        static int size;
        static int front;
        static int rear;
        Queue(int n){
            arr = new int[n];
            size=n;
            front=-1;
            rear=-1;
        }
        public static boolean isEmpty(){
            return front == -1 && rear == -1;
        }
        public static boolean isFull(){
            return (rear + 1) % size == front;
        }
        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }
            if(front == -1){
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int result = arr[front];
            if(front == rear){
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }
    }
    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        while(!q.isEmpty()){
            System.out.println(q.remove());
        }
    }
}
