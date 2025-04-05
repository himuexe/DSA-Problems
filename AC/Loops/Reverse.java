import java.util.*;

public class Reverse{
    public static void convertreverse(int n){
        int rev=0;
        while(n>0){
            rev = rev*10+ n%10;
            n=n/10;
        }
        System.out.println(rev);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter a number to reverse it:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            convertreverse(n);
        }
        catch(Exception e){ 
            System.out.println(e);
        }
    }
}