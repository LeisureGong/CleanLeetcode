package coding;

public class Greedy {

    //122 买卖股票的最佳时机II
    public int maxProfit(int[] prices){
        int maxProfit = 0;
        for(int i = 1;i < prices.length;i++){
            if(prices[i] > prices[i - 1]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}
