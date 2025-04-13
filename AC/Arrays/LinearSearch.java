import java.util.*;

public class LinearSearch {
    public static void linearSearch(int arr[], int key){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==key){
                System.out.println("Element found at index "+i);
                return;
            }
        }
        System.out.println("Element not found");
    }
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        try {
            int n;
            System.out.println("Enter the size of the array:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            n = sc.nextInt();
            int arr[] = new int[n];
            System.out.println("Enter the elements of the array:");
            for(int i=0;i<n;i++){
                if(!sc.hasNextInt()){
                    throw new Exception("Invalid Input");
                }
                arr[i] = sc.nextInt();
            }
            System.out.println("Enter the element to be searched:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int key = sc.nextInt();
            linearSearch(arr,key);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}