import java.util.Scanner;

public class Binary {

    public static int binaryToDecimal(int binary) {
        int decimal = 0;
        int power = 0;

        while (binary != 0) {
            int digit = binary % 10;
            decimal += digit * (1 << power); // Using bitwise shift for power of 2
            power++;
            binary /= 10;
        }
        return decimal;
    }

    public static String decimalToBinary(int decimal) {
        if (decimal == 0) return "0"; // Handle the case for 0 explicitly
        StringBuilder binary = new StringBuilder();

        while (decimal > 0) {
            binary.insert(0, decimal % 2); // Prepend the binary digit
            decimal /= 2;
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a binary number: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid binary number.");
                return; // Exit if input is invalid
            }
            int binary = scanner.nextInt();
            System.out.println("Decimal number: " + binaryToDecimal(binary));

            System.out.print("Enter a decimal number: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                return; // Exit if input is invalid
            }
            int decimal = scanner.nextInt();
            System.out.println("Binary number: " + decimalToBinary(decimal));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}