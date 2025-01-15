import java.util.Scanner;

public class ArmStrong {
    
    // Method to check if a number is an Armstrong number
    public static boolean isArmstrongNumber(int n) {
        if (n < 0) {
            return false; // Armstrong numbers are non-negative
        }
        
        int originalNumber = n;
        int sum = 0;
        int numberOfDigits = String.valueOf(n).length(); // Get the number of digits
        
        // Calculate the sum of the digits raised to the power of the number of digits
        while (n != 0) {
            sum += Math.pow(n%10, numberOfDigits);
            n /= 10;
        }
        
        return sum == originalNumber; // Check if the sum equals the original number
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a number to check if it is an Armstrong number: ");
        
        // Check if the input is an integer
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (isArmstrongNumber(number)) {
                System.out.println(number + " is an Armstrong number.");
            } else {
                System.out.println(number + " is not an Armstrong number.");
            }
        } else {
            System.out.println("Invalid input. Please enter an integer.");
        }
        
        scanner.close(); // Close the scanner
    }
}