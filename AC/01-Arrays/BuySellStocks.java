import java.util.Scanner;

public class BuySellStocks {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of days: ");
        int n = sc.nextInt();
        int[] prices = new int[n];
        
        System.out.print("Enter daily prices: ");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        
        int profit = maxProfit(prices);
        System.out.println("Maximum profit: " + profit);
        sc.close();
    }
}