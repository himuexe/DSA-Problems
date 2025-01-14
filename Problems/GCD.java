import java.util.Scanner;

public class GCD {
    
    // Method to calculate GCD using the Euclidean algorithm
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // When b is 0, a is the GCD
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter two integers to find their GCD: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter two integers.");
                return; 
            }
            int a = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter two integers.");
                return; 
            }
            int b = scanner.nextInt();
            System.out.println("The GCD of " + a + " and " + b + " is: " + gcd(a, b));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }
}