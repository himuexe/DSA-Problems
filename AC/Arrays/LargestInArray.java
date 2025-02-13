import java.util.InputMismatchException;
import java.util.Scanner;

public class LargestInArray {

    public static int[] findLargestAndSmallest(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return new int[]{max, min}; // Return both max and min in an array
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

            int[] result = findLargestAndSmallest(arr);
            System.out.println("The largest element in the array is: " + result[0]);
            System.out.println("The smallest element in the array is: " + result[1]);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}