import java.util.*;
class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            if(nums[i]>list.get(list.size()-1)){
                list.add(nums[i]);
            }
            else{
                int index = binarySearch(list,nums[i]);
                list.set(index,nums[i]);
            }
        }
        return list.size();
    }
    private int binarySearch(ArrayList<Integer> list , int val){
        int left=0;
        int right = list.size()-1;
        while(left<right){
            int mid = left+ (right-left)/2;
            if(val>list.get(mid)){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }
}