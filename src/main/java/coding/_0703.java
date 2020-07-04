package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/7/3
 */
public class _0703 {

	public static void main(String[] args) {
		List<List<Integer>> res = kSmallestPairs(new int[]{2,1,1},new int[]{3,1,2},2);
		System.out.println(res);
	}

	// 找出第k小的距离对
	public static int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		PriorityQueue<Node> heap = new PriorityQueue<Node>(nums.length,
				Comparator.<Node> comparingInt(node -> nums[node.nei] - nums[node.root]));
		for(int i = 0; i + 1 < nums.length; i++) {
			heap.offer(new Node(i,i+1));
		}
		Node node = null;
		for(; k > 0; --k) {
			node = heap.poll();
			if(node.nei + 1 < nums.length) {
				heap.offer(new Node(node.root,node.nei + 1));
			}
		}
		return nums[node.nei] - nums[node.root];
 	}

	static class Node {
		int root;
		int nei;
		Node (int r, int n) {
			root = r;
			nei = n;
		}
	}

	public static  List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

		List<List<Integer>> lists = new ArrayList<>();
		for(int i = 0;i < nums1.length;i++) {
			for(int j = 0;j < nums2.length;j++) {
				lists.add(Arrays.asList(nums1[i],nums2[j]));
			}
		}

		lists = lists.stream().sorted(Comparator.comparingInt(o -> (o.get(0) + o.get(1)))).collect(Collectors.toList());
		return lists.subList(0,lists.size() > k ? k : lists.size());

	}

	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			pq.offer(new int[]{matrix[i][0], i, 0});
		}
		for (int i = 0; i < k - 1; i++) {
			int[] now = pq.poll();
			if (now[2] != n - 1) {
				pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
			}
		}
		return pq.poll()[0];
	}
}
