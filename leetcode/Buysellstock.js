package Lecture3;

public class BuySellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

        int res1 = solution1(prices);
        int res2 = solution2(prices);
    }

    // T.C.:- O(N^2)
    // S.C.:- O(1)
    public static int solution1(int[] prices) {
        int maxProfit = 0;
        for(int sell = 0; sell < prices.length; sell++) {
            int minBuy = Integer.MAX_VALUE;
            for(int buy = 0; buy < sell; buy++) {
                minBuy = Math.min(minBuy, prices[buy]);
            }

            int profit = prices[sell] - minBuy;
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    // T.C.:- O(N)
    // S.C.:- O(1)
    // Prefix Concept for minBuy
    // Similar Problems where this problem used
    // Running Sum Of 1D Array
    // Find Pivot Index
    // Product Of Array Except Self
    // Trapping Rainwater
    // Minimum Penalty For a Shop
    // Ways to Make Fair Array
    public static int solution2(int[] prices) {
        int maxProfit = 0;
        int minBuy = Integer.MAX_VALUE;
        for(int sell = 0; sell < prices.length; sell++) {
            int profit = prices[sell] - minBuy;
            maxProfit = Math.max(maxProfit, profit);
            minBuy = Math.min(minBuy, prices[sell]);
        }

        return maxProfit;
    }
}