package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

    //5.最长回文子串
    public static String longestPalindrome(String s){
        if(s.length() <= 1) return s;
        int maxLen = 0;
        String sub = "";

        for(int i = 0; i < s.length();i++){
            //单核回文
            int low = i,high = i;
            while(low >= 0 && high < s.length()){
                if(s.charAt(low) == s.charAt(high)){
                    if(high - low + 1 > maxLen){
                        maxLen = high - low + 1;
                        sub = s.substring(low,high+1);
                    }
                    low--;high++;
                }else break;
            }
            //双核回文
            low = i;high = i+1;
            while(low >= 0 && high < s.length()){
                if(s.charAt(low) == s.charAt(high)){
                    if(high - low + 1 > maxLen){
                        maxLen = high - low + 1;
                        sub = s.substring(low,high+1);
                    }
                    low--;high++;
                }else break;
            }
        }
        return sub;
    }

    //20 有效的括号
    public static boolean isValid(String s){
        if(s.isEmpty()) return true;
        char[] left = {'(','{','['};
        Map<Character,Integer> right = new HashMap<>();
        right.put(')',0);
        right.put('}',1);
        right.put(']',2);
        //使用栈
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < s.length();i++){
            char t = s.charAt(i);
            if(t == '(' || t == '{' || t == '['){
                stack.push(t);
            }else{
                //从栈中取出一个字符
                if(stack.isEmpty())
                    return false;
                else{
                    int index = right.get(t);
                    if(stack.pop() != left[index]){
                        return false;
                    }
                }
            }
        }
        //判断栈中是否还有元素
        if(stack.isEmpty())
            return true;
        else return false;
    }

    //1060 拼写单词
    public static int countCharacters(String[] words,String chars){
        int[] c = new int[26];
        for(char t : chars.toCharArray()){
            c[t - 'a'] += 1;
        }
        //最终结果长度
        int res = 0;
        for(String word : words){
            int[] w = new int[26];
            for(char ww : word.toCharArray()){
                w[ww - 'a'] += 1;
            }
            int i;
            for(i = 0;i < 26;i++){
                if(w[i] > c[i]){
                    break;
                }
            }
            //判断是否遍历了所有
            if(i == 26){
                res += word.length();
            }
        }
        return res;
    }

    //3.无重复字符的最长字串
    public static int lengthOfLongestSubstring(String s){
        int n = s.length(),ans = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0,j = 0;j < n;j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)),i);
            }
            ans = Math.max(ans,j - i + 1);
            map.put(s.charAt(j),j+1);
        }
        return ans;
    }

    //7整数反转
    public static int reverse(int x) {
        int ans = 0;
        while(x != 0){
            //边界值处理
            if((ans*10) / 10 != ans){
                ans = 0;
                break;
            }
            ans = ans*10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    //409最长回文串
    public static int longestPalindromes(String s){
        int[] count = new int[128];
        for(char c : s.toCharArray()){
            count[c]++;
        }
        int ans = 0;
        for(int v :count){
            ans += v/2 * 2;
            if(v%2 == 1 && ans%2 == 0){
                ans++;
            }
        }
        return ans;
    }


    public static void main(String... args){
//        System.out.println(gcdOfStrings("aaaa","aa"));
//        System.out.println(compressString(""));
//        System.out.println(romanToInt("LVIII"));
//        System.out.println(intToRoman(3489));
//        System.out.println(isValid("()"));
//        String[] words = {"cat","bt","hat","tree"};
//        String chars = "atach";
//        System.out.println(countCharacters(words,chars));
//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("cbbd"));
        System.out.println(reverse(-2147448));
    }






}
