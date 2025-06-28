import java.util.ArrayList;

public class LinearSearch {
    
    static ArrayList<Integer> list = new ArrayList<>();

    public static void findAllIndex(int[] arr,int index ,int target){
        if(index==arr.length-1){
            return;
        }
        if(arr[index]==target){
            list.add(index);
        }
        findAllIndex(arr, index+1, target);
    }

    public static void main(String[] args){
        int arr[] = {20,1,2,4,19,2,4};
        findAllIndex(arr, 0,2);
        System.out.println(list.toString());
    }

}
