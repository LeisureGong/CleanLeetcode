package sort;

import java.util.Arrays;

public class ShellSort implements MyArraySort {

    @Override
    public int[] sort(int[] nums) throws Exception {

        int[] arr = Arrays.copyOf(nums, nums.length);

        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            //  从后向前插入排序
            for (int i = gap; i < arr.length;i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }
}
