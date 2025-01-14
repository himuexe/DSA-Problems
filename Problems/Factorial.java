import java.util.Scanner;
import java.math.BigInteger;

public class Factorial {

    // Method to calculate the factorial of a number
    public static BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.ONE; // Use BigInteger for large results
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); // Multiply using BigInteger
        }
        return factorial;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a non-negative integer: ");
            // Validate input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int number = scanner.nextInt();
            if (number < 0) {
                System.out.println("Invalid input. Factorial is not defined for negative numbers.");
                return;
            }
            BigInteger result = calculateFactorial(number);
            System.out.println("The factorial of " + number + " is " + result + ".");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}