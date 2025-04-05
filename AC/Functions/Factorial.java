import java.util.*;

public class Factorial{
    public static void calculateFactorial(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact *= i;
        }
        System.out.print("Factorial of "+n+" is "+fact);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter a number to calculate factorial:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            calculateFactorial(n);
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
}
