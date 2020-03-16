package coding;

import java.util.HashMap;
import java.util.Map;

public class Strings {

    public static String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }
        return str1.substring(0,gcd(str1.length(),str2.length()));

    }

    //辗转相除法
    public static int gcd(int a,int b){
        return  b == 0? a : gcd(b,a%b);
    }

    //8. 实现 strStr()
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty() || "".equals(needle)){
            return 0;
        }
        int l = haystack.length(),n = needle.length();
        for(int i = 0;i < l - n + 1;i++){
            if(haystack.substring(i,i + n).equals(needle)){
                return i;
            }
        }
        return -1;
    }
    //字符串压缩
    public static String compressString(String S){
        if(S.length() <= 2) return S;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char temp = S.charAt(0);
        for(int i = 1;i < S.length();i++){
            if(S.charAt(i) == temp){
                count++;
            }else{
                sb.append(temp).append(count);
                temp = S.charAt(i);
                count = 1;
            }
        }
        sb.append(temp).append(count);
        if(sb.length() < S.length()){
            return sb.toString();
        }else{
            return S;
        }
    }

    //13. 罗马数字转整数
    public static int romanToInt(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);
        int ans = 0;
        for(int i = 0;i < s.length();){
            if(i+1 < s.length() && map.containsKey(s.substring(i,i+2))){
                ans += map.get(s.substring(i,i+2));
                i += 2;
            }else{
                ans += map.get(s.substring(i,i+1));
                i += 1;
            }
        }
        return ans;
    }
    //12整数转罗马数字
    public static String intToRoman(int num){
        StringBuilder sb = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};  // 罗马数字
        int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};  // 阿拉伯数字
        int index = 0;
        while(num > 0){
            //贪心策略，找最大的先处理
            int count = num / arab[index];
            while(count-- > 0){
                sb.append(roman[index]);
            }
            num %= arab[index];
            index++;
        }
        return sb.toString();
    }




    public static void main(String... args){
//        System.out.println(gcdOfStrings("aaaa","aa"));
//        System.out.println(compressString(""));
//        System.out.println(romanToInt("LVIII"));
        System.out.println(intToRoman(3489));
    }




}
