import java.util.*;
public class CountOccurance {
    public static int count(int n , int digit){
        int count =0;
        while(n>0){
            int rem = n%10;
            if(rem == digit){
                count++;
            }
            n /= 10;
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int number = sc.nextInt();
        System.out.println("Enter the digit to count its occurance: ");
        int digit = sc.nextInt();
        System.out.println("The digit "+digit+" occures "+count(number,digit)+" times in "+number);
        sc.close();
    }
}
