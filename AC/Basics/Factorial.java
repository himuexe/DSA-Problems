package Basics;
import java.util.*;
public class Factorial {
    public static int calculateFactorial(int number){
        int fact =1;
        for(int i=1;i<=number;i++){
            fact *= i;
        }
        return fact;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to find its factorial");
        int number = sc.nextInt();
        System.out.println("The factorial of "+number+" is "+calculateFactorial(number));
        sc.close();
    }
}
