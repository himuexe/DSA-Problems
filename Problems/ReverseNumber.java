import java.util.Scanner;

public class ReverseNumber {

    // Method to reverse the digits of a number
    public static int reverseNumber(int number) {
        int reversed = 0;
        while (number > 0) {
            reversed = reversed * 10 + number % 10; // Build the reversed number
            number = number / 10; // Remove the last digit
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            // Validate input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int number = scanner.nextInt();
            int reversed = reverseNumber(number);
            System.out.println("The reversed number is: " + reversed);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}