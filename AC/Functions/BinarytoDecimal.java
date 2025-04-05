import java.util.*;

public class BinarytoDecimal{
    public static int conversion(int n){
        int decimal =0;
        int power =1;
        while(n>0){
            decimal += (n%10)*power;
            n=n/10;
            power*=2;
        }
        return decimal;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
        try {
            System.out.print("Enter a binary number:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            int decimal = conversion(n);
            System.out.println("Decimal value of "+n+" is "+decimal);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
