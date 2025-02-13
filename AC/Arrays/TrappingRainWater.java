import java.util.*;

public class TrappingRainWater {

    public static int trappedWater(int[] height) {
        // Edge case: if the array is empty or null, return 0
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;

        // Arrays to store the maximum height to the left and right of each bar
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill the leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Fill the rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate the trapped water
        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            // The water level is the minimum of the left and right maximum heights
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            // The trapped water at this bar is the water level minus the height of the bar
            trappedWater += waterLevel - height[i];
        }

        return trappedWater;
    }

    public static void main(String[] args) {
        int[] height = {4, 2, 0, 6, 3, 2, 5};
        System.out.println("Trapped water: " + trappedWater(height));
    }
}