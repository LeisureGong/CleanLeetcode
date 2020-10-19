package sort;


/**
 * CountSort 不是基于比较排序，
 * @author gonglei
 * @date 2020/10/19
 */
public class CountSort implements MyArraySort{

    @Override
    public int[] sort(int[] nums) throws Exception {
        return new int[0];
    }

    private int[] countSort(int[] arr, int maxVal) {
        int bucketLen = maxVal + 1;
        int[] bucket = new int[bucketLen];

        for (int val : arr) {
            bucket[val]++;
        }

        int sortedIdx = 0;
        for (int j = 0; j < bucketLen; j++) {
            // 可能有重复值，所以用while
            while (bucket[j] > 0) {
                arr[sortedIdx++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr) {
        int maxVal = arr[0];
        for (int val : arr) {
            if (maxVal < val) {
                maxVal = val;
            }
        }
        return maxVal;
    }
}
