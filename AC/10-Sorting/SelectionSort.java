import java.util.Arrays;;
public class SelectionSort {
    public static void sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minPos=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[minPos]){
                    minPos=j;
                }
            }
            int temp =arr[minPos];
            arr[minPos]=arr[i];
            arr[i]=temp;
        }
    }
    public static void main(String[] args){
        int[] arr = {13,1,14,2,10,11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }        
}
