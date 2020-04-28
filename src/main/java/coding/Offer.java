package coding;

/**
 * @author gonglei
 * @date 2020/4/28
 */
public class Offer {

	/**
	 * 位运算：异或分组
	 * 找到数组中两个只出现一次的数，PS其它数出现两次
	 * @return int[]
	 * @date 2020/4/28
	 */
	public static int[] singleNumbers(int nums[]){
		//数组中所有数异或的结果
		int k = 0;
		for(int num : nums){
			k ^= num;
		}
		//分组，找到最低位k中最低位的1,k
		int pos = 1;
		while((k & pos) == 0){
			pos <<= 1;
		}
		int num1 = 0;
		int num2 = 0;
		for(int num : nums){
			if((pos & num) == 0){
				num1 ^= num;
			}else{
				num2 ^= num;
			}
		}
		return new int[]{num1,num2};
	}

	public static void main(String args[]){
		System.out.println((char)'a' + 9);
	}
}
