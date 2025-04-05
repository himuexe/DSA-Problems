import java.util.*;

public class BinomialCoeff{
    public static int calculateFactorial(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact *= i;
        }
        return fact;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter n to calculate binomial coefficient:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            System.out.print("Enter r to calculate binomial coefficient:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int r = sc.nextInt();
            int nCr = calculateFactorial(n)/(calculateFactorial(r)*calculateFactorial(n-r));
            System.out.println(n+"C"+r+"="+nCr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}