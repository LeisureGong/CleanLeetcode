package coding;

import java.util.*;

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

	//四数之和
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums.length < 4) return result;
		Arrays.sort(nums);
		for(int i = 0;i < nums.length-3;i++){
			for(int j = i+1;j < nums.length-2;j++){
				int nowTarget = target - nums[i] - nums[j];
				int l = j+1;//left pointer
				int r = nums.length-1;//right pointer
				while(l < r){
					if(nums[l] + nums[r] == nowTarget){
						List<Integer> curr = new ArrayList<>();
						curr.add(nums[i]);
						curr.add(nums[j]);
						curr.add(nums[l]);
						curr.add(nums[r]);
						result.add(new ArrayList<>(curr));
						l++;r--;
						while(l<nums.length && nums[l]==nums[l-1])l++;
						while(r>=l && nums[r]==nums[r+1])r--;
					}else if(nums[l]+nums[r] > nowTarget){
						r--;
					}else l++;
				}
			}
		}
		return result;
	}

	//合并区间
	public static int[][] merge(int[][] intervals) {
		int m = intervals.length;
		//二维数组排序
		Arrays.sort(intervals,(v1,v2) -> v1[0] - v2[0]);
//		Arrays.sort(intervals,new Comparator<int[]>(){
//			@Override
//			public int compare(int[] o1,int[] o2){
//				if(o1[0] < o2[0]){
//					return -1;
//				}else if(o1[0] > o2[0]){
//					return 1;
//				}else if(o1[1] < o2[1]){
//					return -1;
//				}else return 1;
//			}
//		});
		int[][] res = new int[m][2];
		res[0][0] = intervals[0][0];
		res[0][1] = intervals[0][1];
		int k = 0;
		for(int i = 1;i < m;i++){
			if(intervals[i][0] >= res[k][0] && intervals[i][0] <= res[k][1]){
				if(intervals[i][1] > res[k][1]){
					res[k][1] = intervals[i][1];
				}
			}else if(intervals[i][0] > res[k][1]){
				k++;
				res[k][0] = intervals[i][0];
				res[k][1] = intervals[i][1];
			}
		}
		return Arrays.copyOf(res,k+1);
	}

	//179最大数
	public static String largestNumber(int[] nums) {
		StringBuilder sb = new StringBuilder();
		String[] strs = new String[nums.length];
		for(int i = 0;i < nums.length;i++){
			strs[i] = String.valueOf(nums[i]);
		}
//		sortByStartDigits(list);
		Arrays.sort(strs,(o1,o2) -> (o2+o1).compareTo(o1+o2));
		for(String i : strs){
			sb.append(i);
		}
		return sb.toString();
	}

	public static void sortByStartDigits(List<String> nums){
		Collections.sort(nums, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
	}
	//杨辉三角
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList();
		res.add(Arrays.asList(1));
		for (int i = 1; i < numRows; i++) {
			List<Integer> row = new ArrayList();
			row.add(1);
			List<Integer> last = res.get(i - 1);
			int j = 1;
			for (; j < (i+1) / 2; j++) {
				row.add(last.get(j - 1) + last.get(j));
			}
			List<Integer> temp = new ArrayList<>(row);
			Collections.reverse(temp);
			//假如当前行是偶数行
			if (i % 2 == 1) {
				row.addAll(temp);
			} else {
				row.add(last.get(j - 1) + last.get(j));
				row.addAll(temp);
			}
			res.add(row);
		}
		return res;
	}

	public static int mySqrt(int x) {
		if(x == 0) return 0;
		int left = 1,right = x;
		while(left < right){
			int mid = left + (right - left)/2;
			if((mid <= x / mid) && ((mid+1) > x / (mid+1))){
				return mid;
			}else if(mid * mid > x){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return -1;
	}

	//
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int m = nums1.length,n = nums2.length;
		int k = (m+n)/2;
		int m1 = findKth(nums1,m,nums2,n,k);
		if((m+n)%2 == 0){
			int m2 = findKth(nums1,m,nums2,n,k+1);
			return (m1 + m2)/2.0;
		}
		return m1;
	}

	// 寻找两个有序数组的第k位数
	public static int findKth(int[] nums1,int m,int[] nums2,int n,int k) {
		if(m < n) return findKth(nums2,n,nums1,m,k);
		if(n == 0) return nums1[k-1];
		if(k == 1) return Math.min(nums1[0],nums2[0]);
		// 如果n<k/2
		int j = Math.min(n,k/2);
		int i = k - j;
		if(nums1[i-1] > nums2[j-1]) {
			return findKth(nums1,i,Arrays.copyOfRange(nums2,j,nums2.length),n - j,k - j);
		}
		return findKth(Arrays.copyOfRange(nums1,i,nums1.length),m - i,nums2,n,k - i);
	}


	public static List<List<Integer>> threeSum(int[] nums) {
		if(nums.length < 3) return Collections.emptyList();
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for(int i = 0;i < nums.length - 3;i++) {
			int target = - nums[i];
			int j = i + 1,k = nums.length - 1;
			while(j < k) {
				int tempSum = nums[j] + nums[k];
				if(tempSum == target) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					temp.add(nums[k]);
					res.add(temp);
					j++;k--;
				} else if(tempSum < target) {
					j++;
				} else k--;
			}
		}
		return res;
	}




	public static void main(String... args){
//		String a = "helko";
//		a.replace("lk","ll");
//		System.out.println(a);
//		generate(5);
		threeSum(new int[]{-1, 0, 1, 2, -1, -4});

	}








}
