import java.util.Scanner;

public class PrefixSum {

    // Method to calculate the maximum subarray sum using prefix sum
    public static int findMaxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];

        // Compute the prefix sum array
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int maxSum = Integer.MIN_VALUE;

        // Calculate the maximum subarray sum using prefix sum
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }

        return maxSum;
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
            int maxSum = findMaxSubarraySum(arr); // Find the maximum subarray sum
            System.out.println("Maximum Subarray Sum: " + maxSum);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}