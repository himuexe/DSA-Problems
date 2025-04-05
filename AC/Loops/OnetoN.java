import java.util.*;


public class OnetoN{
    public static  void printN(int n){
        for(int i=1;i<=n;i++){
            System.out.println(i);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter a number:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            printN(n);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}