import java.util.Scanner;

public class FactorialRecursion {

    // Method to calculate factorial using recursion
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        if (n == 0 || n == 1) {
            return 1; // Base case: 0! = 1 and 1! = 1
        }
        return n * factorial(n - 1); // Recursive case
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a non-negative integer: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int n = scanner.nextInt();
            if (n < 0) {
                System.out.println("Please enter a non-negative integer.");
                return;
            }
            long result = factorial(n);
            System.out.println("Factorial of " + n + " is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}