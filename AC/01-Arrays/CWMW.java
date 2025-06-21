class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int right =height.length-1;
        int area=0;
        while(left < right){
            int h = (int)Math.min(height[right],height[left]);
            int width = right - left;
            int currentArea = h*width;
            area = Math.max(area,currentArea);
            if(height[left] < height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return area;
    }
}