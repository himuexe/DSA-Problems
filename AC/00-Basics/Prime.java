package Basics;
import java.util.*;

public class Prime {
    public static void isPrime(int number) {
        if (number <= 1) {
            System.out.println("The number " + number + " is not Prime");
            return;
        }
        if (number == 2) {
            System.out.println("The number " + number + " is Prime");
            return;
        }
        if (number % 2 == 0) {
            System.out.println("The number " + number + " is not Prime");
            return;
        }
        
        boolean isPrime = true;
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        
        if (isPrime) {
            System.out.println("The number " + number + " is Prime");
        } else {
            System.out.println("The number " + number + " is not Prime");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to check if it is prime or not: ");
        int number = sc.nextInt();
        isPrime(number);
        sc.close();
    }
}