import java.util.*;
public class ReverseStack {

    public static void reverseStack(Stack<Integer> st){
        if(st.isEmpty()){
            return;
        }
        int top = st.pop();
        reverseStack(st);
        pushAtBottom(st, top);
    }

    public static void pushAtBottom(Stack<Integer> st , int data){
        if(st.isEmpty()){
            st.push(data);
            return;
        }
        int top = st.pop();
        pushAtBottom(st, data);
        st.push(top);
    }

    public static void printStack(Stack<Integer> st) {
        Stack<Integer> temp = new Stack<>();
        while (!st.isEmpty()) {
            int top = st.pop();
            System.out.println(top); 
            temp.push(top); 
        }
        while (!temp.isEmpty()) {
            st.push(temp.pop());
        }
    }


    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        printStack(st);
        reverseStack(st);
        printStack(st);
    }
}
