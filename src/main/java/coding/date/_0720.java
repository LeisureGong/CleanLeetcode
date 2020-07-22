package coding.date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gonglei
 * @date 2020/7/20
 */
public class _0720 {

	public static void main(String[] args) {
		_0720 solution = new _0720();
		System.out.println(solution.maxNumOfSubstrings("adefaddaccc"));
	}

	int[] left;
	int[] right;
	char[] chs;
	public List<String> maxNumOfSubstrings(String s) {
		int n = s.length();
		chs = s.toCharArray();
		// 记录某个字符的最左边和最右边的出现位置s索引
		left = new int[26];
		right = new int[26];
		Arrays.fill(left,n);
		Arrays.fill(right,-1);
		for (int i = 0; i < n; ++i) {
			left[chs[i] - 'a'] = Math.min(left[chs[i] - 'a'],i);
			right[chs[i] - 'a'] = Math.max(right[chs[i] - 'a'],i);
		}

		List<String> res = new ArrayList<>();
		int last = -1;
		for (int i = 0; i < n; ++i) {
			if (i != left[chs[i] - 'a']) continue;
			int p = check(i);
			if (p == -1) continue;
//			 if (i > last) res.add("");
			res.add(s.substring(i,p+1));
			last = p;
		}
		return res;
	}

	//
	private int check(int index) {
		// 当前字符的右边位置
		int p = right[chs[index] - 'a'];
		for (int i = index; i <= p; ++i) {
			if (left[chs[i] - 'a'] < index || chs[i] != chs[index]) // 子字符串相交
				return -1;
			p = Math.max(p, right[chs[i] - 'a']);
		}
		return p;
	}
}
