package coding.date;

import org.omg.CORBA.StringHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gonglei
 * @date 2020/7/13
 */
public class _0713 {

	public static void main(String[] args) {
		_0713 solution = new _0713();
		String pattern = "aaa", str = "aa aa aa aa";
		System.out.println(solution.countSegments("    foo    bar"));
	}

	public int countSegments(String s) {
		s.trim();
		String[] res = s.split("\\s+");
		return res.length;
	}

	public String reverseVowels(String s) {
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		for (char c : s.toCharArray()) {
			if(isVowel(c)) sb.append(c);
		}
		sb.reverse();
		int index = 0;
		for(int i = 0; i < s.length();i++) {
			if(isVowel(s.charAt(i))) {
				res.append(sb.charAt(index++));
			} else {
				res.append(s.charAt(i));
			}
		}
		return res.toString();
	}

	public boolean isVowel(char c){
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		Map<Character,String> map = new HashMap<>();
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			String value = strs[i];
			if(!map.containsKey(c)) {
				if(map.containsValue(value)) {
					return false;
				} else {
					map.put(c,value);
				}

			} else {
				if(!map.get(c).equals(strs[i])) {
					return false;
				}
			}
		}
		return true;
	}

	public int titleToNumber(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			res = res * 26 + (s.charAt(i) - 'A' + 1);
		}
		return res;
	}

	public int reverseBits(int n) {
		int a=0;
		for(int i=0;i<=31;i++){
			a=a+((1&(n>>i))<<(31-i));
		}
		return a;
	}

	public int hammingDistance(int x, int y) {
		int res = 0;
		while(x != 0 || y != 0) {
			int a = x & 1;
			int b = y & 1;
			res += (a ^ b) ^ 0;
			x >>= 1;
			y >>= 1;
		}
		return res;
	}


}
