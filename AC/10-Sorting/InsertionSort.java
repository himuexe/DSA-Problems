import java.util.Arrays;
public class InsertionSort {
    public static void sort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int curr=arr[i];
            int prev=i-1;
            while(prev >=0 && arr[prev] >curr){
                arr[prev+1]=arr[prev];
                prev--;
            }
            arr[prev+1]=curr;
        }
    }
    public static void main(String[] args){
        int[] arr = {13,1,14,2,10,11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }    
}
