import java.util.Scanner;
public class Palindrome {
    public static boolean isPalindrome(int x){
        int n =x;
        if(n < 0){
           return false;
        }
        int rev =0;
        while(n>0){
        rev = rev*10 + (n%10);
        n /= 10;
        }
        return rev == x;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to check if its a palindrome: ");
        int number = sc.nextInt();
        if(isPalindrome(number)){
            System.out.println("The number: "+number+" is a Palindrome");
        }
        else{
            System.out.println("The number: "+number+" is not a Palindrome");
        }
        sc.close();
    }
}
