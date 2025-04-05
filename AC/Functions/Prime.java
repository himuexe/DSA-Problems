import java.util.*;

public class Prime {
    public static void checkPrime(int n) {
        if (n <= 1) {
            System.out.println(n + " is not a prime number");
            return;
        }
        boolean isPrime = true;
        int sqrtN = (int) Math.sqrt(n); 
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        System.out.println(n + (isPrime ? " is a prime number" : " is not a prime number"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter a number to check prime or not:");
            if (!sc.hasNextInt()) {
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            checkPrime(n);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}