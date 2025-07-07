import java.util.*;
public class ValidPar {

    public static boolean isValid(String str){
        Stack<Character> st = new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == '{' || ch == '[' || ch == '('){
                st.push(ch);
            }
            else{
                if(st.isEmpty()){
                    return false;
                }
                if((st.peek() =='(' && ch == ')') || (st.peek()== '{' && ch == '}') || (st.peek() == '[' && ch ==']')){
                    st.pop();
                }
                else{
                    return false;
                }
            }
        }
        if(st.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        String str = "({})[";
        if(isValid(str)){
            System.out.println("Valid hai");
        }
        else{
            System.out.println("Nahi hai");
        }

    }
}
