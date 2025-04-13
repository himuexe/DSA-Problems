import java.util.*;

public class LargestSmallest{

    public static int getLargest(int arr[]){
        int largest = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            largest = Math.max(largest,arr[i]);
        }
        return largest;
    }
    
    public static int getSmallest(int arr[]){
        int smallest = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            smallest = Math.min(smallest,arr[i]);
        }
        return smallest;
    }

    public static void main(String args[]){
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
            System.out.println("The largest element in the array is "+getLargest(arr));
            System.out.println("The smallest element in the array is "+getSmallest(arr));
            }
            catch(Exception e){
                System.out.println(e);
            }
    }
}