import java.util.Scanner;

public class SumTillN {

    // Method to calculate the sum of numbers from 1 to n
    public static int sumUpToN(int number) {
        // Using the formula for the sum of the first n natural numbers
        return (number * (number + 1)) / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a positive integer: ");
            // Validate input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                return;
            }
            int number = scanner.nextInt();
            if (number < 1) {
                System.out.println("Please enter a positive integer greater than 0.");
                return;
            }
            int result = sumUpToN(number);
            System.out.println("The sum of numbers from 1 to " + number + " is " + result + ".");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}