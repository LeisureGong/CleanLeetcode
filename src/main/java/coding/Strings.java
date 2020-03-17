package coding;

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
//    public static void findLongestPalindrome(String s,int low,int high){
//        while(low >= 0 && high < s.length()){
//            if(s.charAt(low) == s.charAt(high)){
//                if(high - low + 1 > maxLen){
//                    maxLen = high - low + 1;
//                    sub = s.substring(low,high+1);
//                }
//                low--;high++;
//            }else break;
//        }
//    }



    public static void main(String... args){
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }




}
