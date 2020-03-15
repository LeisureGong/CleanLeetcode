package coding;

public class DynamicProgramming {

    //300.最长上升子序列
    public static int lengthOfLIS(int[] nums){
        if(nums.length == 0)  return 0;
        //状态转移方程：dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxAns = 1;
        for(int i = 1;i < nums.length;i++){
            int maxVal = 0;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    maxVal = Math.max(maxVal,dp[j]);
                }
            }
            dp[i] = maxVal + 1;
            maxAns = Math.max(maxAns,dp[i]);
        }
        return maxAns;
    }



    public static void main(String... args){
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

}
