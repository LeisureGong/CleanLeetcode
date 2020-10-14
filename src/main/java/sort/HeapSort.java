package sort;

import java.nio.channels.AsynchronousByteChannel;
import java.util.Arrays;

public class HeapSort {

    /**
     * 大顶堆，根节点向下调整
     * @param a 待排序的数组
     * @param start 被下调结点的起始位置
     * @param end 截止范围
     * @return
     * @date 2020/10/14
     */
    public void maxHeapDown(int[] a, int start, int end) {

        int curr = start;
        int left = 2 * curr + 1;
        int tmp = a[curr];

        for (; left <= end; curr = left, left = 2 * left + 1) {
            // left是左孩子， left+1是右孩子
            if (left < end && a[left] < a[left + 1]) {
                left++; // 左右孩子中选择较大者
            }
            if (tmp < a[left]) {
                a[curr] = a[left];
                a[left] = tmp;
            } else break;
        }
    }

    /**
     * 大顶堆 调整
     * @param a 待排序的数组
     * @param start 被下调结点的起始位置
     * @param end 截止范围
     * @return
     * @date 2020/10/14
     */
    public void minHeapDown(int[] a, int start, int end) {

        int curr = start;
        int left = 2 * curr + 1;
        int tmp = a[curr];

        for (; left < end; curr = left, left = 2 * left + 1) {
            if (left < end && a[left] > a[left + 1]) {
                left++; // 左右孩子中选择最小者
            }
            if (tmp > a[left]) {
                a[curr] = a[left];
                a[left] = tmp;
            } else break;
        }
    }

    /**
     * 堆排序 从小到大
     * @param a 待排序数组
     * @return
     * @date 2020/10/14
     */
    public int[] heapSortAsc(int[] a) {

        int len = a.length;
        // 从(n/2 - 1) --> 0逐次遍历，得到的数组是一个小顶堆
        for (int i = len/2 - 1; i >= 0; i--)
            maxHeapDown(a, i, len - 1);
        for (int i = len - 1; i > 0; i--) {
            // 交换a[0]和a[i], 交换后，a[i]是a[0...i]中最大的
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得其仍为一个大顶堆
            maxHeapDown(a, 0 , i - 1);
        }
        return a;
    }

    /**
     * 堆排序 从大到小
     * @param a 待排序数组
     * @return
     * @date 2020/10/14
     */
    public int[] heapSortDesc(int[] a) {

        int len = a.length;
        // 从(n/2 - 1) --> 0逐次遍历，得到的数组是一个小顶堆
        for (int i = len/2 - 1; i >= 0; i--)
            minHeapDown(a, i, len - 1);
        for (int i = len - 1; i > 0; i--) {
            // 交换a[0]和a[i], 交换后，a[i]是a[0...i]中最大的
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得其仍为一个大顶堆
            minHeapDown(a, 0 , i - 1);
        }
        return a;
    }

    public static void main(String[] args) {
        int a[] = new int[]{1,34,62,12,867,100,99,1001};

        HeapSort heapSort = new HeapSort();
        System.out.println("正序：");
        System.out.println("Before: " + Arrays.toString(a));
        heapSort.heapSortAsc(a);
        System.out.println("After: " + Arrays.toString(a));

        System.out.println("逆序：");
        System.out.println("Before: " + Arrays.toString(a));
        heapSort.heapSortDesc(a);
        System.out.println("After: " + Arrays.toString(a));


    }
}
