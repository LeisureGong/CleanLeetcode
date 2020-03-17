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

    //62不同路径
    public static int uniquePaths(int m,int n){
        if(m <= 0 || n <= 0) return 0;
        //dp数组
        int[][] dp = new int[m][n];
        //初始化边界点
        for(int i = 0; i< m;i++)
            dp[i][0] = 1;
        for(int i = 0;i < n;i++)
            dp[0][i] = 1;
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //64最小路径和
    public static int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        if(m <= 0 || n <= 0) return 0;
        //位置(i,j)的路径和为dp[i][j]
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i< m;i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int i = 1;i < n;i++)
            dp[0][i] = dp[0][i-1] + grid[0][i];
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    //72 编辑距离
    public static int minDistance(String word1, String word2) {

        int n1 = word1.length();
        int n2 = word2.length();
        //i和j分别代表word1和word2的长度，dp[i][j]表示将word1转为word2所需的做小操作次数
        //最优子结构：若word1[i] == word1[j],则dp[i][j] == dp[i-1][j-1]
        int[][] dp = new int[n1+1][n2+1];
        for(int i = 1;i <= n1;i++)
            dp[i][0] = dp[i-1][0] + 1;
        for(int i = 1;i <= n2;i++)
            dp[0][i] = dp[0][i-1] + 1;
        for(int i = 1;i <= n1;i++){
            for(int j = 1;j <= n2;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String... args){
//        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
//        System.out.println(uniquePaths(7,3));
//        int[][] nums = {{1,3,1},{1,5,1},{4,2,1}};
//        System.out.println(minPathSum(nums));
        System.out.println(minDistance("","a"));
    }



}
