import java.util.Scanner;

public class Divisors {
    
    // Method to calculate the sum of divisors of a given number
    public static int sumOfDivisors(int number) {
        if (number < 1) {
            return 0; // Return 0 for non-positive numbers
        }
        
        int sum = 0;
        // Iterate only up to the square root of the number
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) { // Check if i is a divisor
                sum += i; // Add the divisor
                if (i != number / i) { // Avoid adding the square root twice
                    sum += number / i; // Add the corresponding divisor
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a positive integer to calculate the sum of its divisors:");
        
        // Check if the input is an integer
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number < 1) {
                System.out.println("Please enter a positive integer.");
            } else {
                System.out.println("The sum of divisors of " + number + " is " + sumOfDivisors(number));
            }
        } else {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
        
        scanner.close(); // Ensure the scanner is closed
    }
}