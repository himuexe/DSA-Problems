import java.util.Scanner;

public class Palindrome {
    public static boolean isPalindrome(int number) {
        // Negative numbers are not palindromes
        if (number < 0) {
            return false;
        }

        int reversed = 0;
        int original = number; // Store the original number for comparison

        // Reverse the number
        while (number > 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10; // Reduce the number by one digit
        }

        // Check if the reversed number is equal to the original number
        return original == reversed;
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
            String result = isPalindrome(number) ? "Yes, it is a palindrome number." : "No, it is not a palindrome number.";
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}