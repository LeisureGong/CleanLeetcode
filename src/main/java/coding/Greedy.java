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
    public boolean canJump(int[] nums) {
        int end = nums.length;
        int index = 0;
        while(nums[index] != 0 && index < end){
            int value = nums[index];
            int temp = 0,tempIndex = 0;
            for(int i = 1; i <= value;i++){
                if(nums[index+i]+index+i > temp){
                    tempIndex = index+i;
                }
            }
            index = tempIndex;
        }

        if(index >= end){
            return true;
        }
        return false;
    }
    public static void main(String... args){
        System.out.println(new int[]{3,2,1,0,4});
    }
}
