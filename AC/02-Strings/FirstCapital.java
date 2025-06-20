
public class FirstCapital {
    public static String convert(String str){
        StringBuilder newStr = new StringBuilder("");
        char ch = Character.toUpperCase(str.charAt(0));
        newStr.append(ch);
        for(int i=1;i<str.length();i++){
            if(str.charAt(i)==' ' && i< str.length()-1){
                newStr.append(str.charAt(i));
                i++;
                newStr.append(Character.toUpperCase(str.charAt(i)));
            }
            else{
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
    public static void main(String[] args){
        String str = "my name is himanshu";
        System.out.println(convert(str));
    }    
}
