import java.util.Arrays;

public class CyclicSort {
    public static void sort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correctPos = arr[i] - 1;
            if (arr[i] != arr[correctPos]) {
                int temp = arr[i];
                arr[i] = arr[correctPos];
                arr[correctPos] = temp;
            } else {
                i++;
            }
        }
    }
    
    public static void main(String[] args) {
        int arr[] = {2, 1, 3, 5, 4};
        sort(arr);
        System.out.println(Arrays.toString(arr));  
    }
}