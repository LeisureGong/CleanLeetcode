package sort;

import java.util.Arrays;

public class MergeSort implements MyArraySort{

    @Override
    public int[] sort(int[] nums) throws Exception {

        int[] arr = Arrays.copyOf(nums, nums.length);

        if (arr.length < 2) {
            return nums;
        }

        int mid = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0 , mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        return merge(sort(left), sort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int l = 0, r = 0, len = 0;
        while (len < left.length + right.length) {
            if (left[l] < right[r]) {
                result[len++] = left[l++];
                if (l == left.length) {
                    while (r < right.length)
                        result[len++] = right[r++];
                }
            } else {
                result[len++] = right[r++];
                if (r == right.length) {
                    while (l < left.length)
                        result[len++] = left[l++];
                }
            }
        }
        return result;
    }
}
