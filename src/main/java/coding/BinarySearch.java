package coding;

public class BinarySearch {

    //29.两数相除
	public static int divide(int dividend,int divisor){
		if(divisor == 1) return dividend;
		if(divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE: -dividend;
		if(dividend == 0) return 0;
		//正负号
		boolean flag = false;
		if((dividend<0&&divisor>0) || (dividend>0&&divisor<0))
			flag = true;
		int result = divideSearch(Math.abs((long)dividend),Math.abs(divisor));
		return flag ? -result : result;
	}

	//商
	public static int divideSearch(long dividend,int divisor){
		if(dividend < divisor) return 0;
		//被除数
		int tempDivisor = divisor;
		int count = 1;
		//二分法减少搜索时间
		while(dividend - tempDivisor > tempDivisor){
			count = count + count;
			tempDivisor  = tempDivisor + tempDivisor;
		}
		return count + divideSearch(dividend-tempDivisor,divisor);
	}

	// 搜索旋转排序数组
	public static boolean search(int[] nums, int target) {
		if(nums == null || nums.length == 0){
			return false;
		}
		int low = 0,high = nums.length - 1,mid;
		while(low <= high){
			mid = low + (high-low)/2;
			if(nums[mid] == target){
				return true;
			}
			if(nums[low] == nums[mid]){
				low++;
				continue;
			}
			//前半部分有序
			if(nums[low] < nums[mid]){
				//target在前半部分
				if(nums[mid] > target && nums[low] <= target){
					high = mid - 1;
				}else{
					low = mid + 1;
				}
			}else{//后半部分有序
				if(nums[mid] < target && nums[high] >= target){
					low = mid + 1;
				}else{
					high = mid - 1;
				}
			}
		}
		return false;
	}

	public static void main(String... args){
		System.out.println(7 & 5);
	}

}
