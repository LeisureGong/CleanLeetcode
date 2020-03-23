package coding;

/**
 * @author gonglei
 * @date 2020/3/23 17:16
 */
public class Exam {
	public static int longestCommonSubsequence(String text_1, String text_2) {
		char[] text1 = text_1.toCharArray();
		char[] text2 = text_2.toCharArray();
		int m = text1.length;
		int n = text2.length;
		if(m<= 0 || n<=0) return 0;
		int[][] dp = new int[m][n];
		//初始化边界点
		int m1 = 0,n1 = 0;
		while(m1 < m && text1[m1] != text2[0]){
			dp[m1][0] = 0;
			m1++;
		}
		while(m1 < m){
			dp[m1][0] = 1;
			m1++;
		}
		while(n1 < n && text2[n1] != text1[0]){
			dp[0][n1] = 0;
			n1++;
		}
		while(n1 < n){
			dp[0][n1] = 1;
			n1++;
		}

		for(int i = 1;i < m;i++){
			for(int j = 1;j < n;j++){
				if(text1[i] == text2[j]){
					dp[i][j] = dp[i-1][j-1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
				}
			}
		}
		return dp[m-1][n-1];
	}

	public static void main(String... args){
		System.out.println(longestCommonSubsequence("abcde","ace"));
	}

}
