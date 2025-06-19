import java.util.Scanner;

public class Kadanes {
    public static int kadane(int[] arr) {
        int maxCurrent = arr[0];
        int maxGlobal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
            maxGlobal = Math.max(maxGlobal, maxCurrent);
        }
        return maxGlobal;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        
        System.out.print("Enter array elements: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        
        int maxSum = kadane(arr);
        System.out.println("Maximum subarray sum: " + maxSum);
        sc.close();
    }
}