package sort;

import java.util.Arrays;

public class BucketSort implements MyArraySort {

    private static final InsertSort insertSort = new InsertSort();

    @Override
    public int[] sort(int[] nums) throws Exception {
        // 拷贝数组
        int[] arr = Arrays.copyOf(nums, nums.length);

        return bucketSort(arr, 5);
    }


    // bucket sort
    private int[] bucketSort(int[] arr, int bucketSize) throws Exception {

        if (arr.length == 0) {
            return arr;
        }

        // 找到最大值和最小值
        int maxVal = arr[0];
        int minVal = arr[0];
        for (int val : arr) {
            if (val < minVal) {
                minVal = val;
            } else if (val > maxVal) {
                maxVal = val;
            }
        }

        int bucketCnt = (int) Math.floor((maxVal - minVal) / bucketSize) + 1;
        int[][] buckets = new int[bucketCnt][0];

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int idx = (int) Math.floor((arr[i] - minVal) / bucketSize);
            buckets[idx] = arrAppend(arr, arr[i]);
        }

        int sortedIdx = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0)
                continue;
            // 对每个桶进行插入排序
            bucket = insertSort.sort(bucket);
            for (int val : bucket) {
                arr[sortedIdx++] = val;
            }
        }
        return arr;
    }

    // 自动扩容，并保存数据
    private int[] arrAppend(int[] arr, int val) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = val;
        return arr;
    }
}
