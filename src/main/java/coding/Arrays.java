package coding;

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
	public static void main(String... args){
		System.out.println(majorityElement(new int[]{8,8,7,7,7}));
	}
}
