import java.util.Arrays;

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        // Edge case: if the array is empty or null, return immediately
        if (arr == null || arr.length == 0) {
            return;
        }

        int n = arr.length;

        // Traverse the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the current index is the minimum
            int minIndex = i;

            // Find the index of the minimum element in the unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the element at index i
            swap(arr, i, minIndex);
        }
    }

    // Helper method to swap two elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        selectionSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}