public class FirstAndLast{
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        ans[0] = search(nums, target, true);
        if(ans[0]!=-1){
        ans[1] = search(nums, target, false);
        }
        return ans;
    }

    int search(int[] nums, int target, boolean start) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                ans = mid;
                if (start) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
         FirstAndLast solution = new  FirstAndLast();
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println(result1[0] + " " + result1[1]);

        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] result2 = solution.searchRange(nums2, target2);
        System.out.println(result2[0] + " " + result2[1]);

        int[] nums3 = {};
        int target3 = 0;
        int[] result3 = solution.searchRange(nums3, target3);
        System.out.println(result3[0] + " " + result3[1]);

        int[] nums4 = {1, 2, 3, 4, 5};
        int target4 = 3;
        int[] result4 = solution.searchRange(nums4, target4);
        System.out.println(result4[0] + " " + result4[1]);
    }
}