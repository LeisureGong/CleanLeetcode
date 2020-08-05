package coding.date;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/7/20
 */
public class _0720 {

	public static void main(String[] args) {
		_0720 solution = new _0720();

		System.out.println(solution.numSplits("aaaaa"));
	}


	int count = 0;
	public int numSplits(String s) {
		if (s == null || s.length() <= 1) return 0;
		for (int i = 1; i < s.length() - 1; ++i) {
			String s1 = s.substring(0,i);
			String s2 = s.substring(i,s.length());
			func(s1,s2);
		}
		return count;
	}

	private void func(String s1,String s2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for (char c1 : s1.toCharArray()) {
			set1.add(c1 - 'a');
		}
		for (char c2 : s2.toCharArray()) {
			set2.add(c2 - 'a');
		}
		if (set1.size() == set2.size()) count++;
	}




	int[] res;
	int m, n;
	int land[][];
	public int[] pondSizes(int[][] land) {
		int index = 0;
		this.land = land;
		this.m = land.length;
		this.n = land[0].length;
		res = new int[m * n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (land[i][j] == 0) {
					res[index++] = dfs(i,j);
				}
			}
		}
		res = Arrays.copyOfRange(res,0,index);
		Arrays.sort(res);
		return res;
	}

	private int dfs(int r, int c) {
		if(r < 0 || r >= m || c < 0 || c >= n || land[r][c] != 0) return 0;
		land[r][c] = -1;
		int tmp = 1;
		tmp +=
				dfs(r-1,c) + dfs(r+1,c) + dfs(r,c-1) + dfs(r,c+1);
		tmp +=
				dfs(r-1,c-1) + dfs(r+1,c-1) + dfs(r-1,c+1) + dfs(r+1,c+1);
		return tmp;
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
