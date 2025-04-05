import java.util.*;

public class DecimaltoBinary{
    public static int conversion(int n){
        int binary = 0;
            int power = 1;
            while(n>0){
                binary += (n%2)*power;
                n=n/2;
                power*=10;
            }
            return binary;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter a decimal number:");
            if(!sc.hasNextInt())
                throw new Exception("Invalid Input");
            int n = sc.nextInt();
            int result = conversion(n);
            System.out.println("Binary value of "+n+" is "+result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}