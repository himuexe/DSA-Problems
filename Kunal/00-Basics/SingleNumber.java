public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int ans =nums[0];
        for(int i=1;i<nums.length;i++){
            ans = ans ^ nums[i];
        }
        return ans;
    }
    public static void main(String[] args){
        int[] arr ={1,1,2,2,3};
        System.out.println(singleNumber(arr));
    }
}
