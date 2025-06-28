class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }
    private  int quickSelect(int[] nums , int low , int high , int k){
        int pi = partition(nums,low,high);
        if(pi==k){
            return nums[pi];
        }
        else if(pi<k){
            return quickSelect(nums,pi+1,high,k);
        }
        else{
            return quickSelect(nums,low,pi-1,k);
        }
    }
    private int partition(int[] nums , int low , int high){
        int pivot = nums[high];
        int i = low-1;
        for(int j=low;j<high;j++){
            if(pivot>nums[j]){
                i++;
                swap(nums,i,j);
            }
        }
        swap(nums,i+1,high);
        return i+1;
    }
    private void swap(int[] nums ,int i , int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}