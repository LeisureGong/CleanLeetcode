package coding.date;

import java.util.*;

public class _0709 {

    public static void main(String[] args) {
        _0709 solution = new _0709();
        int[] A = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int K = 3;
        System.out.println(solution.longestOnes(A,2));
    }

    public int longestOnes(int[] A, int K) {

        // B[]保存连续个数
        int[] B = new int[A.length];
        int first,count,idxA = 0,idxB = 0;
        while(idxA < A.length) {
            count = 1;
            first = A[idxA];
            while(++idxA <  A.length && A[idxA] == first) ++count;
            B[idxB++] = first == 0 ? -count : count;
        }

        // 最终结果
        int res = 0,tempK = K,temp = 0;
        int left = idxB,right = 0;
        while(right < idxB) {
            if(B[right] >= 0) {
                temp += B[right];
                res = Math.max(res,temp);
            } else {
                if(tempK + B[right] >= 0) {
                    tempK += B[right];
                    res = Math.max(res,temp - B[right]);
                    //  记录最左侧的负数
                    if(left == idxB) left = right;
                    // 如果right走到最右边，但是tempK仍然大于0
                    if(right == idxB - 1 && tempK > 0) {
                        res = Math.max(res,temp - B[right] + tempK);
                    }
                } else {
                    res = Math.max(res,temp + K);
                    temp = 0;tempK = K;
                    left = right;
                }
            }
            right++;
        }
        return res;
    }
    public int lengthOfLongestSubstring(String s) {
        int res = 0,j = 0;
        // Map<Char,Index>
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                j  = map.get(c) + 1;
            }
            res = Math.max(res,i - j + 1);
            map.put(c,i);
        }
        return res;
    }



    public static boolean wordBreak(String s, List<String> wordDict) {

        if(s == null || s.length() == 0) {
            return false;
        }
        int i = 0;
        boolean flag = true;
        while(i < s.length() && flag) {
            flag = false;
            for(String word : wordDict) {
                int wlen = word.length();
                if(i + wlen <= s.length() && s.substring(i,i + wlen).equals(word)) {
                    i = i + wlen;
                    flag = true;
                    break;
                }
            }
            if(i == s.length()) break;
        }
        return flag;
    }
}
