package coding;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        while(sc.hasNext()){
            String input = sc.nextLine();
            System.out.println(checkInput(input));
        }
    }

    public static int checkInput(String words){

        if(words.contains("AND") || words.contains("OR") || words.contains("NOT")){

            List<String> list = Arrays.asList(words.split(" "));
            String word1 = list.get(0);
            String w2 = list.get(list.size()-1);
            if(w2.equals("AND") || w2.equals("OR") || w2.equals("NOT")) return 0;
            if(word1.length()>1 && !word1.equals("NOT")) return 0;
            if(word1.equals("NOT") && !Character.isLowerCase(list.get(1).charAt(0))) return 0;

            for(int i = 1;i < list.size();i++){
                String pre = list.get(i-1);
                String now = list.get(i);
                int pLen = pre.length(),nLen = now.length();
                if(pLen == nLen && !now.equals("NOT"))
                    return 0;
                if(pLen == nLen && pre.equals("NOT"))
                    return 0;
            }
            return 1;
        }else{
            if(words.length() != 1){
                return 0;
            }

            char c = words.charAt(0);
            if(Character.isLowerCase(c)){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
