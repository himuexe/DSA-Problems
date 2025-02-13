
import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearSearch {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return the index if the target is found
            }
        }
        return -1; // Return -1 if the target is not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            if (size <= 0) {
                System.out.println("Array size must be a positive integer.");
                return; // Exit if the size is invalid
            }

            int[] arr = new int[size];
            System.out.println("Enter the elements of the array: ");
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }

            System.out.print("Enter the target element: ");
            int target = scanner.nextInt();

            int index = linearSearch(arr, target);
            if (index == -1) {
                System.out.println("Element not found.");
            } else {
                System.out.println("Element found at index " + index + ".");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}