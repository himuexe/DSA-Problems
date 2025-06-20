import java.util.Arrays;

public class BubbleSort{
    public static void sort(int[] arr){
        boolean swapped;
        for(int i=0;i<arr.length-1;i++){
            swapped =false;
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                    swapped=true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }
    public static void main(String[] args){
        int[] arr = {13,1,14,2,10,11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}