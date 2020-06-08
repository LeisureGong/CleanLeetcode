package coding;

import java.util.LinkedList;

/**
 * @author gonglei
 * @date 2020/5/26
 */
public class Stacks {

	public static String simplifyPath(String path) {

		// use Stack
		LinkedList<String> stack = new LinkedList<String>();
		int i = 0;
		while(i < path.length()) {
			int j = i+1;
			while(j < path.length() && path.charAt(j) != '/') j++;
			String str = path.substring(i,j);
			if("/..".equals(str) && stack.size() > 0) stack.removeLast();
			else if(!"/".equals(str) && !"/.".equals(str)) {
				stack.add(str);
			}
			i = j;
		}
		if(stack.size() == 0) stack.add("/");
		StringBuilder sb = new StringBuilder();
		while(stack.size() != 0) {
			sb.append(stack.removeFirst());
		}
		return sb.toString();
	}

	public static void main(String... args) {
		System.out.println(simplifyPath("/a/../../b/../c//.//"));
	}
}
