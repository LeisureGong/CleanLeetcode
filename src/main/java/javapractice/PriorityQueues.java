package javapractice;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gonglei
 * @date 2020/4/28
 */
public class PriorityQueues {

	/**
	 * 对开始时间进行排序，如果相邻的时间没有重叠，则移除最早的那个时间
	 * 堆的大小即为最大并集
	 *
	 * @param intervals [开始时间，结束时间]二维数组
	 * @return 最大的并集
	 * @date 2020/4/28
	 */
	public static int maxCount(int[][] intervals){
		// 根据开始时间排序
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
		// 小顶堆，保存结束时间
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int i = 0;i < intervals.length;i++){
			//相邻时间没有重叠，则移除最早的结束时间
			if(!minHeap.isEmpty() && minHeap.peek() <= intervals[i][0]){
				minHeap.remove();
			}
			minHeap.add(intervals[i][1]);
		}
		return minHeap.size();
	}
	public static void main(String args[]){
		int[][] times = new int[][]{{0,30},{31,40},{5,10},{7,8},{41,50}};
		System.out.println(maxCount(times));
	}
}
