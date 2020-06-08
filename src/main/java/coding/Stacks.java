package coding;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class Stacks {

    public static String decodeString(String s) {
        StringBuffer sb = new StringBuffer();
        Deque<StringBuffer> stack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();
        int i = 0,num = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num*10 + c - '0';
            } else if(Character.isAlphabetic(c)) {
                sb.append(c);
            } else if(c == '[') {
                stack.add(sb);
                numStack.push(num);
                sb = new StringBuffer();
                num = 0;
            } else if(c == ']') {
                StringBuffer temp = stack.pop();
                // 遇到右括号，直接弹出一个数字栈
                int n = numStack.pop();
                while(n-- > 0) temp.append(sb);
                sb = temp;
            }
            ++i;
        }
        return sb.toString();

    }

    public static void main(String... args) {
        System.out.println(decodeString("100[leetcode]"));
    }
}
