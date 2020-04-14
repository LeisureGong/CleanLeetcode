package coding;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gonglei
 * @date 2020/3/13 13:14
 */
public class ArraysCode {
	//169多数元素,求众数
	public static int majorityElement(int[] nums) {
		int i = 0;//index
		int count = 1;//计数
		int res = nums[0];//保存结果
		while(i < nums.length-1){
			if(res == nums[i+1]){
				count++;
			}else{
				count--;
			}
			i += 1;
			if(count == 0){
				res = nums[i];
				count = 1;
			}
		}
		return res;
	}

	public void nextPermutation(int[] nums) {
		//nums[k]是从右到左，第一个失衡数字
		int k = nums.length-1;
		//检查是否为最大数字
		boolean flag = true;
		for(int i = nums.length-2;i >= 0;i--){
			if(nums[i] < nums[i+1]){
				flag = false;
				k = i;
				break;
			}
		}
		if(flag){
			this.reverse(nums,0);
			return;
		}
		//从右到左，找到第一个比nums[k]，并交换
		if(k >= 0){
			int j = nums.length - 1;
			while(j > 0 && nums[j] <= nums[k]){
				j--;
			}
			swap(nums,j,k);
		}
		reverse(nums,k+1);

	}

	public void reverse(int[] nums,int start){
		int i = start,j = nums.length - 1;
		while(i < j){
			this.swap(nums,i,j);
			i++;
			j--;
		}
	}

	public void swap(int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	//面试题03. 数组中重复的数字
	public static int findRepeatNumber(int[] nums) {
		Map<Integer,Integer> map = new HashMap<>();
		for(int i = 0;i < nums.length;i++){
			int x = nums[i];
			if(map.containsKey(x)){
				return x;
			}else{
				map.put(x,1);
			}
		}
		return -1;
	}

	//836 矩形重叠
	public static boolean isRectangleOverlap(int[] rect1,int[] rect2) {
		int x1 = rect1[0], y1 = rect1[1];
		int x2 = rect1[2], y2 = rect1[3];
		int p1 = rect2[0],q1 = rect2[1];
		int p2 = rect2[2],q2 = rect2[3];
		if((x1 <= p1 && p1 < x2 && y1 <= q1 && q1 < y2) || (x1 <= p2 && p2 < x2 && y1 <= q2 && q2 < y2)){
			return true;
		}
		return false;
	}

	//945使数组唯一的最小增量
	public static int minIncrementForUnique(int[] A) {
		if(A.length <= 1) return 0;
		int result = 0;
		Arrays.sort(A);
		int i = 1;
		for(;i < A.length;i++){
			while(A[i] <= A[i-1]){
				A[i]++;
				result++;
			}
		}
		return result;
	}

	//1013. 将数组分成和相等的三个部分
	public static boolean canThreePartsEqualSum(int[] A) {
		//先求出总和,每等份的和为sum/3
		int sum = 0;
		for (int num : A)
			sum += num;
		if (sum % 3 != 0) return false;
		sum /= 3;
		int count = 0,currSum = 0;
		for(int i = 0;i < A.length;i++){
			currSum += A[i];
			if(currSum == sum){
				currSum = 0;
				count++;
			}
			if(count == 2) return true;
		}
		return false;
	}

	//914卡牌分组
	public static boolean hasGroupsSizeX(int[] deck) {
		//临界点
		if(deck.length < 2) return false;
		Map<Integer,Integer> map = new HashMap<>();
		for(int a : deck){
			map.put(a,map.getOrDefault(a,0)+1);
		}
		Integer[] nums = new Integer[map.size()];
		map.values().toArray(nums);
		int result = getMoreBigDiv(nums,map.size());
		return result == 1 ? false : true;


	}
	//求一组数的最大公约数
	public static int getMoreBigDiv(Integer nums[],int n){
		if(n == 1) return nums[n-1];
		return getBigDiv(nums[n-1],getMoreBigDiv(nums,n-1));
	}
	//求两个数的最大公约数
	public static int getBigDiv(int a,int b){
		if(b == 0) return a;
		return getBigDiv(b,a%b);
	}

	public static int trap(int[] height) {
		int left = 0,right = height.length - 1;
		int left_max = 0,right_max = 0;
		int ans = 0;
		while(left < right){
			if(height[left] < height[right]){
				if(height[left] >= left_max) left_max = height[left];
				else ans += (left_max - height[left]);
				++left;
			}else{
				if(height[right] >= right_max) right_max = height[right];else ans += (right_max - height[right]);
			}
		}
		return ans;
	}

	//289 game of life
	public static void gameOfLife(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		//原地，遍历两次，第一遍记录周围周围位置，第二遍修改状态
		//如果本身是1则乘10
		for(int i = 0;i<m;i++){
			for(int j = 0;j < n;j++){
				if(board[i][j] == 1) board[i][j] = 10;
			}
		}
		for(int i = 0;i < m;i++){
			for(int j = 0;j < n;j++){
				int count = 0;//计算周围存活数量
				if(i-1>=0 && board[i-1][j] >= 10)count++;
				if(i+1<m && board[i+1][j] >= 10)count++;
				if(j-1>=0 && board[i][j-1] >= 10)count++;
				if(j+1<n && board[i][j+1] >= 10)count++;
				if(i-1>=0 && j-1>=0 &&board[i-1][j-1] >= 10)count++;
				if(i-1>=0&&j+1<n &&board[i-1][j+1]>= 10)count++;
				if(i+1<m&&j-1>=0&&board[i+1][j-1]>= 10)count++;
				if(i+1<m&&j+1<n&&board[i+1][j+1]>= 10)count++;
				board[i][j] += count;
			}
		}
		for(int i = 0;i < m;i++){
			for(int j = 0;j < n;j++){
				int k = board[i][j];
				if(k == 3 ||k == 12 || k== 13) board[i][j] = 1;
				else board[i][j] = 0;
			}
		}
	}


	public static int movingCount(int m, int n, int k) {
		int result = 0;
		boolean[][] visited = new boolean[m][n];
		result = backtracking(0,0,m,n,k,visited,result);
		//注意，机器人只能向右或向下走
		return result;
	}

	public static int backtracking(int x,int y,int m,int n,int k,boolean[][] visited,int result){
		if(x < 0 || x >= m || y < 0 || y >= n ||(x/10 + x%10 + y/10 + y%10)> k || visited[x][y]) return 0;
		visited[x][y] = true;
		return backtracking(x+1,y,m,n,k,visited,result)+backtracking(x,y+1,m,n,k,visited,result)+1;
	}

	public static int[] distributeCandies(int candies, int num_people) {
		int[] result = new int[num_people];
		int base = (1 + num_people)*num_people/2;
		int sum = 0;
		int account = num_people*num_people;
		int i = 0;
		while((sum + account*i + base )< candies){
			sum += account*i + base;
			i++;
		}
		for(int j = 0;j < num_people;j++){
			result[j] = (j+1)*i + num_people*i*(i-1)/2;
		}
		int temp = candies - sum;
		//当前第一个数
		int t = num_people*i + 1;
		int j = 0;
		while(temp > 0){
			if(temp > t){
				result[j] += t;
				temp = temp - t;
				j++;t++;
			}else{
				result[j] += temp;
				break;
			}
		}
		return result;
	}




	public static void main(String... args){
//		System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
//		int a[] = {0,0,1,1};
//		int b[] = {1,0,2,1};
//		System.out.println(isRectangleOverlap(a,b));
//		System.out.println(minIncrementForUnique(new int[]{3,2,1,2,1,7}));
//		System.out.println(canThreePartsEqualSum(new int[]{0,2,1,-6,6,-7,9,1,2,0,1}));
//		System.out.println(hasGroupsSizeX(new int[]{1,1}));
//		System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
//		rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//		gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
//		System.out.println(movingCount(3,1,0));
		System.out.println(distributeCandies(10,3));
	}






}
