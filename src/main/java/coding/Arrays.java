package coding;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gonglei
 * @date 2020/3/13 13:14
 */
public class Arrays {
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


	public static void main(String... args){
//		System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
		int a[] = {0,0,1,1};
		int b[] = {1,0,2,1};
		System.out.println(isRectangleOverlap(a,b));
	}

}
