package coding;

/**
 * @author gonglei
 * @date 2020/7/7
 */
public class _0707 {

	public static int compress(char[] chars) {

		if (chars.length <= 1) return chars.length;

		int i = 0,j = 1,index = i;
		while(j < chars.length) {
			while(j < chars.length && chars[i] == chars[j]) {
				j++;
			}
			int count = j - i;
			chars[index++] = chars[i];
			if(count > 1) {
				String countS = String.valueOf(count);
				for(int k = 0; k < countS.length(); k++) {
					chars[index++] = countS.charAt(k);
				}
			}
			i = j;
		}
		return index;
	}

	public static void main(String[] args) {
		char[] chs = new char[]{'a','a','a','b','b','a','a'};
		System.out.println(compress(chs));
	}
}
