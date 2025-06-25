import java.util.*;
class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3) return false;
        
        int[] min = new int[nums.length];
        min[0] = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            min[i] = Math.min(nums[i], min[i-1]);
        }
        
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i = nums.length - 1; i >= 1; i--){
            if(nums[i] > min[i]){
                while((!st.isEmpty()) && (st.peek() <= min[i])){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek() < nums[i]){
                    return true;
                }
                st.push(nums[i]);
            }
        }
        return false;
    }
}