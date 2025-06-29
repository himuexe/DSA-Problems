import java.util.ArrayList;

public class Dice {

    public static ArrayList<String> perm(String p , int target){
        if(target==0){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        for(int i=1;i<=6 && i<=target;i++){
            list.addAll(perm(p+i, target-i));
        }
        return list;
    }

    public static void main (String[] args){
        System.out.println(perm("", 4));
    }
}
