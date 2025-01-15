import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReverseArray {

    public static void reverseArray(int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        while (first < last) {
            // Swap elements
            int temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
            first++;
            last--;
        }
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
            System.out.println("Enter the elements of the array (space-separated): ");
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }

            // Reverse the array
            reverseArray(arr);
            System.out.println("Reversed array: " + Arrays.toString(arr));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}