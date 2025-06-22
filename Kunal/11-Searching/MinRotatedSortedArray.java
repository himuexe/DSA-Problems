class Solution {
    public int findMin(int[] nums) {
        // If the array is not rotated, just return the first element
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        
        int pivot = findPivot(nums);
        return nums[pivot + 1];
    }
    
    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is the pivot
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }
            
            // Decide which half to search
            if (nums[mid] <= nums[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}