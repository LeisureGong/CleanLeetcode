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

	public static void main(String... args){
		System.out.println(divide(-2147483648,-2));
	}

}
