package coding.date;

import org.omg.CORBA.StringHolder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author gonglei
 * @date 2020/7/13
 */
public class _0713 {

	public static void main(String[] args) {
		_0713 solution = new _0713();
		int[][] A = new int[][]{{4,5},{2,3},{1,2}};

		System.out.println(Arrays.toString(solution.findRightInterval(A)));
	}

	public int[] findRightInterval(int[][] intervals) {
		int[] res = new int[intervals.length];
		int[] helpArray = new int[intervals.length];

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < intervals.length; i++) {
			map.put(intervals[i][0],i);
			helpArray[i] = intervals[i][0];
		}
		Arrays.sort(helpArray);

		for(int i = 0; i < intervals.length; i++) {
			int[] tmp = intervals[i];
			int index = map.get(tmp[0]);
			// 对每一个tmp[1],找到以o[0]组成的数组中有没有比他大的
			int key = find(tmp[1],intervals);
			res[index] = key == -1 ? -1 : map.get(intervals[key]);
		}
		return res;
	}

	public int find(int target, int[][] intervals) {
		int left = 0, right = intervals.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (target <= intervals[mid][0]) right = mid - 1;
			else left = mid + 1;
		}
		return right != intervals.length - 1 ? left : -1;
	}

	private class TwoTuple {
		public final int first;
		public final int second;
		public TwoTuple(int a, int b) {
			first = a;
			second = b;
		}
	}

	public int[] kthSmallestPrimeFraction(int[] A, int K) {
		PriorityQueue<TwoTuple> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> (double)A[o.first] / A[o.second]));

		for (int i = 1; i < A.length; i++) {
			pq.add(new TwoTuple(0,i));
		}
		while(--K > 0) {
			TwoTuple tmp = pq.poll();
			if(tmp.first + 1 < tmp.second) {
				pq.add(new TwoTuple(tmp.first + 1, tmp.second));
			}
		}
		TwoTuple res = pq.poll();
		return new int[]{A[res.first],A[res.second]};
	}

	public int longestOnes(int[] A, int K) {

		int[] B = new int[A.length];
		int first, count, idxB = 0, idxA = 0;
		while (idxA < A.length) {
			first = A[idxA];
			count = 1;
			while (++idxA < A.length && A[idxA] == first) count++;
			B[idxB++] = first == 0 ? -count : count;
		}

		// 保存最终结果
		int res = 0, tempRes = 0, tempK = K;
		int left, right = 0;
		// 更新下第一个负数index,必为0或1
		left = B[1] > 0 ? 2 : 1;
		while (right < idxB) {
			if (B[right] >= 0) {
				tempRes += B[right];
			} else {
				// 先收缩左边的窗口，注意left是res的第二个数
				if (left < right) {
					while ( tempK + B[right] < 0) {
						tempK -= B[left];
						// 注意是减两个数
						tempRes += B[left] - B[left - 1];
					}
					tempK += B[right];
					tempRes -= B[right];
				} else {
					tempRes = 0;
					tempK = K;
				}
			}
			res = Math.max(res, tempRes);
			right++;
		}
		return res;
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
