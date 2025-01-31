import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr) {
        // Edge case: if the array is empty or null, return immediately
        if (arr == null || arr.length == 0) {
            return;
        }

        int n = arr.length;
        boolean swapped; // Flag to check if any swaps occurred in a pass

        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset the flag at the start of each pass

            // Inner loop to compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true; // Set the flag if a swap occurs
                }
            }

            // If no swaps occurred in a pass, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}