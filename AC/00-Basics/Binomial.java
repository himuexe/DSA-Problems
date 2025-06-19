package Basics;
import java.util.*;
public class Binomial {
    public static int calculateFactorial(int number){
        int fact =1;
        for(int i=1;i<=number;i++){
            fact *= i;
        }
        return fact;
    }
     public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n");
        int n = sc.nextInt();
        System.out.println("Enter r");
        int r = sc.nextInt();
        int binomial = calculateFactorial(n) /(calculateFactorial(r)*calculateFactorial(n-r));
        System.out.println("The binomial coefficient is: "+ binomial);
        sc.close();
    }
}
