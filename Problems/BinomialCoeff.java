import java.math.BigInteger;
import java.util.Scanner;

public class BinomialCoeff {

    public static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger calculateBinomialCoeff(int n, int k) {
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k must be between 0 and n inclusive.");
        }
        BigInteger numerator = calculateFactorial(n);
        BigInteger denominator = calculateFactorial(k).multiply(calculateFactorial(n - k));
        return numerator.divide(denominator);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the value of n: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int n = scanner.nextInt();

            System.out.print("Enter the value of r: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int r = scanner.nextInt();

            if (n < 0 || r < 0) {
                System.out.println("Invalid input. n and r must be non-negative.");
                return;
            }

            BigInteger result = calculateBinomialCoeff(n, r);
            System.out.println("The binomial coefficient of " + n + " and " + r + " is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}