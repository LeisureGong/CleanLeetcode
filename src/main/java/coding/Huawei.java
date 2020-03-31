package coding;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Huawei {
    public static void main(String[] args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String words = sc.nextLine();
        System.out.println(lastLength(words));
    }

    public static int lastLength(String words){
        if(words.length() == 0) return 0;
        int len = 0;
        int j = words.length() - 1;
        while(j >= 0 && words.charAt(j) != ' '){
            len++;
            j--;
        }
        return len;
    }
}
