import java.util.Scanner;

public class Kadanes {

    // Method to calculate the maximum subarray sum using Kadane's Algorithm
    public static void calculateMaxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for (int num : arr) {
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        System.out.println("Maximum subarray sum: " + maxSoFar);
    }

    // Method to read and validate array input
    public static int[] readArray(Scanner scanner, int size) {
        int[] arr = new int[size];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
            arr[i] = scanner.nextInt();
        }
        return arr;
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

            int[] arr = readArray(scanner, size); // Read and validate array elements
            calculateMaxSubarraySum(arr); // Calculate and print the maximum subarray sum
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}