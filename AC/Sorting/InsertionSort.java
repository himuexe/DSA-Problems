import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        // Edge case: if the array is empty or null, return immediately
        if (arr == null || arr.length == 0) {
            return;
        }

        int n = arr.length;

        // Traverse the array starting from the second element
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // The element to be inserted
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key in its correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        insertionSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}