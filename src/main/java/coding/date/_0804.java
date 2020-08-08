package coding.date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0804 {

    public static void main(String[] args) {
        _0804 solution = new _0804();
        System.out.println(solution.findSubsequences(new int[]{4,6,7,7}));
    }


    List<List<Integer>> res;
    int[] nums;
    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new ArrayList<>();
        this.nums = nums;
        for (int i = 0; i < nums.length; ++i) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            dfs(i,tmp);
        }
        return res;
    }

    public void dfs(int first,List<Integer> tmp) {
        if (first >= nums.length) return;

        for (int i = first + 1; i < nums.length; i++) {
            if (nums[i] >= nums[first]) {
                tmp.add(nums[i]);
                res.add(new ArrayList<>(tmp));
                dfs(i,tmp);
                tmp.remove(tmp.size() - 1);
            }
        }

    }

//    List<List<Integer>> res;
    public List<List<Integer>> palindromePairs(String[] words) {

        res = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                if (help(words[i],words[j]))
                    res.add(Arrays.asList(i,j));
                if (help(words[j], words[i]))
                    res.add(Arrays.asList(j,i));
            }
        }
        return res;
    }

    private boolean help(String str1, String str2) {
        if (str1.length() < str2.length())
            return help(str2,str1);

        int len1 = str1.length(), len2 = str2.length();
        int i = 0;
        for (;i < len2; ++i) {
            if (str1.charAt(i) != str2.charAt(len2 - 1 - i))
                return false;
        }
        String str3 = str1.substring(len2,len1);
        return isValid(str3);
    }

    private boolean isValid(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2; ++i) {
            if (str.charAt(i) != str.charAt(len - 1 - i))
                return false;
        }
        return true;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res1 = -1,res2 = -1,mid = -1;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        res1 = mid;
        if (res1 == -1) return new int[]{-1,-1};

        left = 0;right = nums.length - 1;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] <= target) left = mid +1;
            else right = mid;
        }
        res2 = mid;
        return new int[]{res1,res2};
    }
}
