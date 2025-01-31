import java.util.Arrays;

public class CountingSort {

    public static void countingSort(int[] arr) {
        // Edge case: if the array is empty or null, return immediately
        if (arr == null || arr.length == 0) {
            return;
        }

        // Find the maximum element in the array
        int max = Arrays.stream(arr).max().getAsInt();

        // Create a count array to store the frequency of each element
        int[] count = new int[max + 1];

        // Store the frequency of each element in the count array
        for (int num : arr) {
            count[num]++;
        }

        // Modify the count array to store the cumulative frequency
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Create a temporary array to store the sorted result
        int[] output = new int[arr.length];

        // Build the output array using the count array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        countingSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}