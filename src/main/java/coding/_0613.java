package coding;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _0613 {

    public static void main(String... args) {

        isPalindrome("A man, a plan, a canal: Panama");

    }

    public static boolean isPalindrome(String s) {
        String t = s.replaceAll("[^a-zA-Z]","");
        char[] chs = t.toLowerCase().toCharArray();
        int i = 0,j = chs.length - 1;
        while(i < j) {
            if(chs[i] != chs[j]) return false;
            i++;j--;
        }
        return true;
    }



}
