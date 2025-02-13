import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2; // Prevents potential overflow
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            if (size <= 0) {
                System.out.println("Array size must be a positive integer.");
                return;
            }

            int[] arr = new int[size];
            System.out.print("Enter the elements of the array (space-separated): ");
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }

            // Sort the array before performing binary search
            Arrays.sort(arr);
            System.out.println("Sorted array: " + Arrays.toString(arr));

            System.out.print("Enter the target value: ");
            int target = scanner.nextInt();

            int result = binarySearch(arr, target);
            if (result != -1) {
                System.out.println("Element found at index " + result + ".");
            } else {
                System.out.println("Element not found.");
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