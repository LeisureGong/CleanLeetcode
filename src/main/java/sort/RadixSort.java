package sort;

import java.util.Arrays;

public class RadixSort implements MyArraySort{

    @Override
    public int[] sort(int[] nums) throws Exception {

        int[] arr = Arrays.copyOf(nums, nums.length);
        // 获取数组中最大的数字
        int maxVal = getMax(arr);
        // 获取数字长度
        int maxDigit = getNumLength(maxVal);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取数组中的最大元素
     * @param a 数组
     * @return int
     * @date 2020/10/14
     */
    private int getMax(int[] a) {
        int max = a[0];
        for (int i = 1;i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max;
    }

    // 计算数字长度
    private int getNumLength(long num) {
        if (num == 0) {
            return 1;
        }
        int length = 0;
        for (long tmp = num; tmp != 0; tmp /= 10) {
            length++;
        }
        return length;
    }

//    /**
//     * 对数组按照照exp进行排序
//     * @param a 数组
//     * @param exp 指数
//     * @return
//     * @date 2020/10/14
//     */
//    private int[] expSort(int[] a, int exp) {
//        // 存储被排序数组
//        int[] output = new int[a.length];
//        // 10个桶
//        int[] buckets = new int[10];
//
//        // 每个桶的频率
//        for (int i = 0; i < a.length; i++)
//            buckets[(a[i]/exp)%10]++;
//
//        // 累加buckets[i]
//        for (int i = 1; i < 10; i++) {
//            buckets[i] += buckets[i-1];
//        }
//
//        for (int i = a.length - 1; i >= 0; i--) {
//            output[buckets[(a[i]/exp)%10] - 1] = a[i];
//            buckets[(a[i]/exp)%10]--;
//        }
//        return  output;
//    }

    /**
     * 对数组进行基数排序
     * @param arr
     * @return
     * @date 2020/10/14
     */
    public int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，扩展一倍队列数， 其中[0-9]对应负数，[10-19]对应正数
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int val : bucket) {
                    arr[pos++] = val;
                }
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
