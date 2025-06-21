import java.util.*;
public class ReverseNum {
    public static  int calculateReverse(int number){
        int rev=0;
        while(number>0){
            rev = number%10 + rev*10;
            number /= 10;
        }
        return rev;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to find its reverse: ");
        int number = sc.nextInt();
        System.out.println("The reverse of "+ number + " is "+ calculateReverse(number));
        sc.close();
    }
}
