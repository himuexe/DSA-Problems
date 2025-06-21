import java.util.*;
public class OddEven {
    public static void oddEven(int number){
        if(number %2 == 0){
            System.out.println(number + " is Even ");
        }
        else{
            System.out.println(number + " is Odd ");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to check if its Odd or even:");
        int number = sc.nextInt();
        oddEven(number);
        sc.close();
    }
}