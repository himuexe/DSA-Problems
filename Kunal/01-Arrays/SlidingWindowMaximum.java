import java.util.*;
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(deque.size()>0 && deque.peekFirst()<i-k+1){
                deque.pollFirst();
            }
            while(deque.size()>0 && nums[deque.peekLast()]<nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i-k+1>=0){
                result[i-k+1]=nums[deque.peekFirst()];
            }
        }
        return result;
    }
}