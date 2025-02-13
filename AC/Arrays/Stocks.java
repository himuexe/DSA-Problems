import java.util.*;

public class Stocks {

    public static int buyAndSellStocks(int[] prices) {
        // Edge case: if the array is empty or null, return 0
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE; // Track the minimum price so far
        int maxProfit = 0; // Track the maximum profit

        for (int price : prices) {
            if (price < minPrice) {
                // Update the minimum price if the current price is lower
                minPrice = price;
            } else {
                // Calculate the profit if we sell at the current price
                int profit = price - minPrice;
                // Update the maximum profit if the current profit is greater
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum profit: " + buyAndSellStocks(prices));
    }
}