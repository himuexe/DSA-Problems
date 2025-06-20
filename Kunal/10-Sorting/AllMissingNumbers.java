import java.util.List;
import java.util.ArrayList;
public class AllMissingNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i] != i+1){
                list.add(i+1);
            }
        } 
        return list;       
    }
    public  void sort(int[] arr){
        int i=0;
        while(i<arr.length){
            int correctPos=arr[i]-1;
            if(arr[i] != arr[correctPos]){
                swap(arr, i, correctPos);
            }
            else{
                i++;
            }
        }
    }
    public  void swap(int[] arr, int i, int j){
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
