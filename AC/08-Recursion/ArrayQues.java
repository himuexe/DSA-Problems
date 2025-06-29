import java.util.Arrays;

public class ArrayQues {
    
    public static void changeArr(int[] arr , int idx , int val){
        if(idx == arr.length){
            System.err.println(Arrays.toString(arr));
            return;
        }
        arr[idx]=val;
        changeArr(arr, idx+1, val+1);
        arr[idx] = arr[idx]-2;

    }

    public static void main(String[] args){
        int[] arr = new int[5];
        changeArr(arr, 0, 1);
        System.out.println(Arrays.toString(arr));
    }

}
