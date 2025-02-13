
import java.util.Scanner;

public class Prime {

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true; // 2 is the only even prime number
        }
        if (number % 2 == 0) {
            return false; // Exclude all other even numbers
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) { // Check only odd numbers
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void primesInRange(int number){
        for (int i = 2; i <= number; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int number = scanner.nextInt();
            if (isPrime(number)) {
                System.out.println(number + " is a prime number.");
            } else {
                System.out.println(number + " is not a prime number.");
            }
            System.out.println("Enter a number to calculate prime numbers up to that number: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int range = scanner.nextInt();
            primesInRange(range);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}