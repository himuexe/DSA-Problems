import java.util.Scanner;

public class Fibonacci {

    public int calculateFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer to calculate its Fibonacci number: ");
        
        try {
            int n = scanner.nextInt();
            Fibonacci fibonacci = new Fibonacci();
            int result = fibonacci.calculateFibonacci(n);
            System.out.println("Fibonacci number at position " + n + " is: " + result);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a non-negative integer.");
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}
