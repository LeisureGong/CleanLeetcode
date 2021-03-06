package coding;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

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
    //最长上升子序列-二分查找法
    public int lengthOfLISGreedy(int[] nums){
        int[] top = new int[nums.length];
        //堆的数目，即结果
        int piles = 0;
        for(int i = 0;i < nums.length;i++){
            //当前数字
            int poker = nums[i];
            //binary search
            int l = 0,r = piles;
            while(l < r){
                int m = (l+r)/2;
                if(top[m] >= poker){
                    r = m;
                }else if(top[m] < poker){
                    l = m + 1;
                }
            }

            if(l == piles) piles++;
            top[l] = poker;
        }
        return piles;
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

    //62 不同路径和
    //优化版
    public static int uniquePathsOpt(int m,int n){
        if(m <= 0 || n <= 0) return 0;
        //dp数组
        int[] dp = new int[n];
        //初始化边界点
        for(int i = 0;i < n;i++){
            dp[i] = 1;
        }
        for(int i = 1;i < m;i++){
            dp[0] = 1;
            for(int j = 1;j < n;j++){
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
    }

    //63不同路径 II
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(m <= 0 || n <= 0) return 0;
        //dp数组
        int dp[][] = new int[m][n];
        //初始化边界点
        int i = 0,j = 0;
        while(i < m && obstacleGrid[i][0] == 0){
            dp[i][0] = 1;
            i++;
        }
        while(i  < m){
            dp[i++][0] = 0;
        }
        while(j < n && obstacleGrid[0][j] == 0){
            dp[0][j] = 1;
            j++;
        }
        while(j < n){
            dp[0][j++] = 0;
        }

        for(i = 1;i < m;i++){
            for(j= 1;j < n;j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    //最优子结构方程
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    //64最小路径和
    //时间复杂度为O(m*n)
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

    //84. 柱状图中最大的矩形
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int ans = 0;
        for(int i = 0;i < n;i++){
            int height = Integer.MAX_VALUE;
            for(int j = i;j >0;j--){
                height = Math.min(height,heights[j]);
                ans = Math.max(ans,height*(i-j+1));
            }
        }
        return ans;
    }

    //213打家劫舍II
    public static int rob(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0],nums[1]);
        //dp数组
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        //选了第一个房子，没选最后一个
        for(int i = 2;i < n - 1;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        int t = dp[n-2];
        //选了最后一个房子，但没选第一个
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1],nums[2]);
        for(int i = 3;i <n;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return Math.max(t,dp[n-1]);
    }

    //338比特位计数
    public static int[] countBits(int num){
        int[] result = new int[num+1];
        result[0] = 0;
        for(int i = 1;i < num+1;i++){
            result[i] = result[i & (i-1)] + 1;
        }
        return result;
    }

    //343整数拆分
    public static int integerBreak(int n){
        if(n == 2) return 1;
        if(n == 3) return 2;
        int result = 1;
        while(n > 4){
            n = n - 3;
            result *= 3;
        }
        return n*result;
    }

    //357. 计算各个位数不同的数字个数
    public static int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 10;
        for(int i = 2;i <= n;i++){
            dp[i] = dp[i-1] + (dp[i-1]-dp[i-2])*(10-(i-1));
        }
        return dp[n];
    }
	//209长度最小的子数组
	public static int minSubArrayLen(int s, int[] nums) {
		int res = nums.length;
		int slow = 0,fast = 0;
		int tempSum = nums[fast];
		//遍历一次，做一次更新
		while(fast < nums.length && slow < nums.length){
			if(tempSum >= s){
				res = Math.min(res,fast-slow+1);
				tempSum -= nums[slow];
				slow++;
			}
			else if(tempSum < s && fast < nums.length-1){
				fast++;
				tempSum += nums[fast];
			}else slow++;
		}
		return res;
	}

	//279完全平方数
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        int max_square_index = (int) Math.sqrt(n) + 1;
        int[] square_num = new int[max_square_index];
        for(int i = 1;i < max_square_index;i++){
            square_num[i] = i*i;
        }
        for(int i = 1;i <= n;++i){
            for(int s = 1;s < max_square_index;++s){
                if(i < square_num[s]){
                    break;
                }
                dp[i] = Math.min(dp[i],dp[i - square_num[s]]+1);
            }
        }
        return dp[n];
    }
    public static void main(String... args){
//        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
//        System.out.println(uniquePaths(7,3));
//        int[][] nums = {{1,3,1},{1,5,1},{4,2,1}};
//        System.out.println(minPathSum(nums));
//        System.out.println(minDistance("","a"));
//        System.out.println(rob(new int[]{1,2,3}));
//        System.out.println(minDistance("","a"));
//        System.out.println(Arrays.toString(countBits(10)));
//        System.out.println(countNumbersWithUniqueDigits(2));
//	    minSubArrayLen(7,new int[]{2,3,1,2,4,3});
        System.out.println(numSquares(12));
    }





}
