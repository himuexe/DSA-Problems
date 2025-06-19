public class SearchSortedArray {
     public static int search(int[] nums, int target) {
        //find pivot
        int pivot = pivot(nums);
        //apply binarysearch to left side
        int leftside = binarySearch(nums , 0 , pivot , target);
        if(leftside != -1){
            return leftside;
        }
        //apply binarysearch to right side
        int rightside = binarySearch(nums , pivot+1 , nums.length-1 , target);
        return rightside;
    }
    static int pivot(int[] nums){
        int left=0;
        int right=nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if( mid < right && nums[mid]>nums[mid+1]){
               return mid;
            }
            else if(mid >left &&nums[mid]<nums[mid-1]){
                return mid-1;
            }
            else if(nums[mid]<= nums[left]){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return -1;
    }
    static int binarySearch(int[] nums, int left , int right , int target){
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]>target){
                right = mid-1;
            }
            else if(nums[mid]<target){
                left =mid+1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        int nums[]={4,5,6,7,0,1,2};
        System.out.println(search(nums, 06));
    }
}
