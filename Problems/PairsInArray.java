import java.util.Scanner;

public class PairsInArray {

    // Method to print all pairs in the array
    public static void printPairs(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            System.out.println("Not enough elements to form pairs.");
            return;
        }

        int totalCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                System.out.print("(" + numbers[i] + ", " + numbers[j] + ") ");
                totalCount++;
            }
            System.out.println(); // Move to the next line after printing pairs for the current element
        }
        System.out.println("Total Pairs: " + totalCount);
    }

    // Method to validate and read array input
    public static int[] readArray(Scanner scanner, int size) {
        int[] numbers = new int[size];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
            numbers[i] = scanner.nextInt();
        }
        return numbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the size of the array: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
            int size = scanner.nextInt();
            if (size <= 0) {
                System.out.println("Array size must be a positive integer.");
                return;
            }

            int[] numbers = readArray(scanner, size); // Read and validate array elements
            printPairs(numbers); // Print all pairs
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}