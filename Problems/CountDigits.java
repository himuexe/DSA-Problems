import java.util.Scanner;

public class CountDigits {

    public static int countDigits(int number) {
        // Handle negative numbers by taking the absolute value
        int count = 0;
        int temp = Math.abs(number); // Use absolute value to handle negative numbers

        while (temp != 0) {
            int rem = temp % 10;
            // Check if the digit is not zero and if the original number is divisible by this digit
            if (rem != 0 && number % rem == 0) {
                count++;
            }
            temp /= 10; // Use shorthand for division
        }
        return count;
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
            int result = countDigits(number);
            System.out.println("The number of digits in " + number + " that are divisors of the number is: " + result);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}