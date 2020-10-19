package sort;

public class RadixSort {

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

    /**
     * 对数组按照照exp进行排序
     * @param a 数组
     * @param exp 指数
     * @return
     * @date 2020/10/14
     */
    private int[] expSort(int[] a, int exp) {
        // 存储被排序数组
        int[] output = new int[a.length];
        // 10个桶
        int[] buckets = new int[10];

        // 每个桶的频率
        for (int i = 0; i < a.length; i++)
            buckets[(a[i]/exp)%10]++;

        // 累加buckets[i]
        for (int i = 1; i < 10; i++) {
            buckets[i] += buckets[i-1];
        }

        for (int i = a.length - 1; i >= 0; i--) {
            output[buckets[(a[i]/exp)%10] - 1] = a[i];
            buckets[(a[i]/exp)%10]--;
        }
        return  output;
    }

    /**
     * 对数组进行基数排序
     * @param a
     * @return
     * @date 2020/10/14
     */
    public void radixSort(int[] a) {
        int max = getMax(a);

    }
}
