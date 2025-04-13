import java.util.*;

public class BinarySearch {
    public static int binarySearch(int arr[], int key) {
        int low = 0, high = arr.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;  
            
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1; 
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the size of the array:");
            if (!sc.hasNextInt()) {
                throw new Exception("Invalid Input: Array size must be an integer");
            }
            int n = sc.nextInt();
            
            if (n < 0) {
                throw new Exception("Invalid Input: Array size cannot be negative");
            }
            
            int arr[] = new int[n];
            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < n; i++) {
                if (!sc.hasNextInt()) {
                    throw new Exception("Invalid Input: Array elements must be integers");
                }
                arr[i] = sc.nextInt();
            }
            
            System.out.println("Enter the element to be searched:");
            if (!sc.hasNextInt()) {
                throw new Exception("Invalid Input: Search key must be an integer");
            }
            int key = sc.nextInt();
            
            Arrays.sort(arr);
            System.err.println("Sorted array: " + Arrays.toString(arr));
            int result = binarySearch(arr, key);
            
            if (result == -1) {
                System.out.println("Element not found");
            } else {
                System.out.println("Element found at index " + result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}