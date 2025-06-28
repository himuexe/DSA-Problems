import java.util.*;;
public class Subs {

    static void sub(String p , String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        sub(p+ch, up.substring(1));
        sub(p, up.substring(1));
    }

    static ArrayList<String> subRet(String p , String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = subRet(p+ch, up.substring(1));
        ArrayList<String> right = subRet(p, up.substring(1));
        left.addAll(right);
        return left;
    }

    public static void main(String[] args){
        String str = "abc";
        sub("", str);
        System.out.println(subRet("", str));
    }
}
