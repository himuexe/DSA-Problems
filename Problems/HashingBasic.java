import java.util.Scanner;

public class HashingBasic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Prompt for the size of the array
            System.out.println("Enter the size of the array: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Array size must be a positive integer.");
                return;
            }

            // Initialize the array
            int[] arr = new int[n];
            System.out.println("Enter the elements of the array: ");
            for (int i = 0; i < n; i++) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
                arr[i] = scanner.nextInt();
                if (arr[i] < 0 || arr[i] >= 13) {
                    System.out.println("Element out of range. Please enter a number between 0 and 12.");
                    i--; // Retry this input
                }
            }

            // Initialize the hash array
            int[] hash = new int[13];
            for (int i = 0; i < n; i++) {
                hash[arr[i]]++;
            }

            // Prompt for the number of queries
            System.out.println("Enter the number of queries to be searched: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
            int q = scanner.nextInt();
            if (q <= 0) {
                System.out.println("Number of queries must be a positive integer.");
                return;
            }

            // Process the queries
            System.out.println("Enter the queries: ");
            for (int i = 0; i < q; i++) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
                int number = scanner.nextInt();
                if (number < 0 || number >= 13) {
                    System.out.println("Query out of range. Please enter a number between 0 and 12.");
                    i--; // Retry this input
                } else {
                    System.out.println("The count of " + number + " is: " + hash[number]);
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}