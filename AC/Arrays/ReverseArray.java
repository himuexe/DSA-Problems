import java.util.*;

public class ReverseArray{

    public static void reverseit(int arr[]){
       int first = 0;
       int last = arr.length-1;
       while(first < last){
           int temp = arr[first];
           arr[first] = arr[last];
           arr[last] = temp;
           first++;
           last--;
       }
    }

    public static void main(String[] args) {
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
            reverseit(arr);
            System.out.println("The reversed array is:" + Arrays.toString(arr));
        }        
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
