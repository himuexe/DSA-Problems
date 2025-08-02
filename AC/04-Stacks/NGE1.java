import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        
        // Find next greater element for each element in nums2
        for (int num : nums2) {
            while (!st.isEmpty() && st.peek() < num) {
                nextGreaterMap.put(st.pop(), num);
            }
            st.push(num);
        }
        
        // For remaining elements in stack, no greater element exists
        while (!st.isEmpty()) {
            nextGreaterMap.put(st.pop(), -1);
        }
        
        // Build the result array for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }
        
        return result;
    }
}