package sort;

import java.util.Arrays;

public class InsertSort implements MyArraySort {

    @Override
    public int[] sort(int[] nums) throws Exception {

        int[] arr = Arrays.copyOf(nums, nums.length);

        for (int i = 1; i < arr.length; i++) {

            // 要插入的数据
            int tmp = arr[i];
            // 从已排序的序列最右边开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 把数放到正确的位置上
            arr[j] = tmp;
        }
        return arr;
    }
}
