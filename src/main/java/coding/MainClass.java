package coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author gonglei
 * @date 2020/3/5 12:01
 */
class Solution {
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
}

public class MainClass {
	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for(int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static String integerArrayToString(int[] nums, int length) {
		if (length == 0) {
			return "[]";
		}

		String result = "";
		for(int index = 0; index < length; index++) {
			int number = nums[index];
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayToString(int[] nums) {
		return integerArrayToString(nums, nums.length);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[] nums = stringToIntegerArray(line);

			new Solution().nextPermutation(nums);
			String out = integerArrayToString(nums);

			System.out.print(out);
		}
	}
}