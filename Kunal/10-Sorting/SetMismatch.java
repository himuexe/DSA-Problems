public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        sort(nums);
        for(int i=0;i< nums.length;i++){
            if(nums[i] != i+1){
                return new int[] {nums[i],i+1};
            }
        }
        return new int[] {-1,-1};
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
