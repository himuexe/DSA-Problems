import java.util.*;

public class ReverseString {

    public static String reverse(String str){
        Stack<Character> st = new Stack<>();
        int idx =0;
        while(idx < str.length()){
            st.push(str.charAt(idx));
            idx++;
        }
        StringBuilder result = new StringBuilder("");
        while(!st.isEmpty()){
            char curr = st.pop();
            result.append(curr);
        }
        return result.toString();
    }

    public static void main(String[] args){
        String str = "abcd";
        System.out.println(reverse(str));
    }
}
