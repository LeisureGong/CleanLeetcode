package sort;

import java.util.Arrays;

public class SelectSort implements MyArraySort {

    @Override
    public int[] sort(int[] nums) throws Exception {

        int[] arr = Arrays.copyOf(nums, nums.length);

        // N-1轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每次需要比较的次数N-1
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // 每一轮扫描后，交换i和min
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }
}
