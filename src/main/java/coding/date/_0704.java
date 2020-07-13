package coding.date;

import java.util.*;

public class _0704 {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }


    public static List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();
        int len = s.length();

        for(int r1 = 1;r1 <= 3;r1++) {
            for(int l4 = len-3;l4 < len;l4++) {
                if(r1 + 6 < l4) break;  // midlen = l4 - r1
                if(r1 + 2 > l4) continue;
                String num1 = s.substring(0,r1);
                String num4 = s.substring(l4,len);
                if(!check(num1) || !check(num4)) continue;

                // 中间的字符串分割成两个数
                String num2,num3;
                for(int r2 = r1 + 1;r2 < l4;r2++) {
                    if(r2 - r1 > 3 || l4 - r2 > 3) continue;
                    num2 = s.substring(r1,r2);
                    num3 = s.substring(r2,l4);
                    if(!check(num2) || !check(num3)) continue;
                    StringBuilder sb = new StringBuilder();
                    sb.append(num1).append(".")
                            .append(num2).append(".")
                            .append(num3).append(".")
                            .append(num4);
                    res.add(sb.toString());
                }
            }
        }


        return res;
    }

    private static boolean check(String numS) {

        int num = Integer.parseInt(numS);
        if(numS.charAt(0) == '0') return false;
        return (num >= 0 && num <= 255) ? true : false;
    }
}
