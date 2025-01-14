import java.util.Scanner;

public class OddEven {

    // Method to determine if a number is odd or even
    public static String determineOddOrEven(int number) {
        return (number % 2 == 0) ? "Even" : "Odd"; // Using a ternary operator for conciseness
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter an integer: ");
            // Validate input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                return;
            }
            int number = scanner.nextInt();
            String result = determineOddOrEven(number);
            System.out.println("The given number " + number + " is " + result + ".");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}