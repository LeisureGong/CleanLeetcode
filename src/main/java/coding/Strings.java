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
        while (x != 0) {
            ans = ans * 10 + x % 10;
            //边界值处理
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            x /= 10;
        }
        return ans;
    }

    //字符串转整数
    private static final int maxDiv10 = Integer.MAX_VALUE / 10;
    public static int atoi(String str){
        int i = 0,n = str.length();
        while(i<n && Character.isWhitespace(str.charAt(i))) i++;
        int sign = 1;
        if(i<n && str.charAt(i)=='+'){
            i++;
        }else if(i<n && str.charAt(i)=='-'){
            sign = -1;
            i++;
        }
        int num = 0;
        while(i < n && Character.isDigit(str.charAt(i))){
            int digit = Character.getNumericValue(str.charAt(i));
            if(num > maxDiv10 || num == maxDiv10 && digit >= 8){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return sign*num;
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
    //6.Z字形变换
    public static String convert(String s, int numRows) {
        if(numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0;i < Math.min(numRows,s.length());i++)
            rows.add(new StringBuilder());

        int curRows = 0;
        boolean goingDown = false;
        for(char c : s.toCharArray()){
            rows.get(curRows).append(c);
            if(curRows == 0 || curRows == numRows-1) goingDown = !goingDown;
            curRows += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : rows){
            result.append(sb);
        }
        return result.toString();
    }

    //242. 有效的字母异位词
    public static boolean isAnagram(String s, String t) {
        if(t.length() != s.length()) return false;
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        for(char a : s.toCharArray()){
            if(!map.containsKey(a)){
                map.put(a,1);
            }else{
                map.replace(a,map.get(a)+1);
            }
        }
        for(char c : t.toCharArray()){
            if(map.containsKey(c)){
                if(map.get(c) <= 0){
                    return false;
                }else{
                    map.replace(c,map.get(c)-1);
                }
            }else{
                return false;
            }
        }
        return true;
    }

    //38外观数列
    public static String countAndSay(int n) {
        StringBuilder res = new StringBuilder();
        String result = "1";
        for(int i = 1;i < n;i++){
            res.setLength(0);
            res.append(result);
            int j = 0,len = res.length();
            while(j < len){
                char c = res.charAt(j);
                int count = 1;
                while(++j < len && c == res.charAt(j)){
                    count++;
                }
                res.append(count).append(c);
            }
            result = res.toString().substring(len);
        }
        return result;
    }

    //151翻转单词
    public static String reverseWords(String s) {
        //去除首尾空格
        s = s.trim();
        s = s.replaceAll("\\s+"," ");
        s = new StringBuilder(s).reverse().toString();
        char[] chars = s.toCharArray();
        int slow = 0,fast = 0;
        while(fast < chars.length){
            if(fast == chars.length-1 || chars[fast+1] == ' '){
                strSwap(chars,slow,fast);
                if(fast != chars.length-1){
                    slow = fast+2;
                }
            }
            fast++;
        }
        return String.valueOf(chars);
    }

    public static void strSwap(char[] s,int left,int right){
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;right--;
        }
    }
    //67二进制求和
    public static String addBinary(String a, String b) {
        // String ra = new StringBuilder(a).reverse().toString();
        // String rb = new StringBuilder(b).reverse().toString();
        StringBuilder suffix = new StringBuilder();
        int alen = a.length();
        int blen = b.length();
        int carry = 0;
        while(alen > 0 && blen > 0){
            alen--;blen--;
            char temp = (char) (a.charAt(alen) + (b.charAt(blen) - '0') + carry);
            carry = (temp - '0') / 2;
           suffix.append((temp - '0') % 2);
        }
        String prefix = alen > blen ? a.substring(0,alen-blen) : b.substring(0,blen-alen);
        int plen = prefix.length();
        while(carry == 1 && plen-- > 0){
            char t = (char) (prefix.charAt(plen) + 1);
            carry = (t - '0') / 2;
            suffix.append((t - '0') % 2);
        }
        return carry == 1 ? "1" +  suffix.reverse().toString(): prefix.substring(0,plen) + suffix.reverse().toString();
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
//        System.out.println(reverse(-2147448));
//        System.out.println(convert("LEETCODEISHIRING",3));
//        System.out.println(isAnagram("an","naa"));
//        System.out.println(countAndSay(5));
        int n = 10;
        n = n >>1;
        while(n > 0){


        }
        System.out.println(11 & 1);
    }







}
