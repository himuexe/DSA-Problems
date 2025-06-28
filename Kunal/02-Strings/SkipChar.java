public class SkipChar {
    
    public static void skip(String p , String up){
        if(up.isEmpty()){
            System.err.println(p);
            return;
        }
        char ch = up.charAt(0);
        if(ch=='a'){
            skip(p, up.substring(1));
        }
        else{
            skip(p+ch, up.substring(1));
        }
    }
    public static String skipChar(String up){
        if(up.isEmpty()){
            return "";
        }
        char ch = up.charAt(0);
        if(ch=='a'){
            return  skipChar( up.substring(1));
        }
        else{
           return ch+skipChar( up.substring(1));
        }
    }
    public static String SkipApple(String up){
        if(up.isEmpty()){
            return "";
        }
        if(up.startsWith("apple")){
            return SkipApple(up.substring(5));
        }
        else{
            return up.charAt(0)+SkipApple(up.substring(1));
        }
    }
    public static void main (String[] args){
        skip("","bbacadd");
        System.out.println(skipChar("meaaaow"));
        System.out.println(SkipApple("meowapplehai"));
    }
}
