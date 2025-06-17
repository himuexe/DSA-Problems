package Basics;
import java.util.*;
public class SumN {
    public static int sumUptoN(int number){
        int sum =0;
        for(int i=1;i<=number;i++){
            sum += i;
        }
        return sum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to calculate sum upto the number");
        int number = sc.nextInt();
        System.out.println("The sum is :"+ sumUptoN(number));
        sc.close();
    }
}
