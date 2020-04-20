package coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Collections of Tree
 * @author gonglei
 * @date 2020/4/20 13:43
 */
public class Tree {
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		if(root == null) return Collections.emptyList();
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode temp = root;
		while(!stack.isEmpty() || temp != null) {
			while(temp != null) {
				stack.push(temp);
				temp = temp.left;
			}
			if(!stack.isEmpty()){
				temp = stack.pop();
				//save result
				result.add(temp.val);
				temp = temp.right;
			}
		}
		return result;
	}

	//98 验证二叉搜索树
	public static boolean isValidBST(TreeNode root) {
		if(root == null) return true;
		//检查中序序列是不是有序的
		List<Integer> list = new ArrayList<>();
		inOrder(root,list);
		int i = 0;
		while(i < list.size() && (i+1) < list.size()){
			if(list.get(i) < list.get(i+1)){
				i++;
			}else{
				return false;
			}
		}
		return true;
	}

	public static void inOrder(TreeNode root,List<Integer> result){
		if(root != null){
			inOrder(root.left,result);
			result.add(root.val);
			inOrder(root.right,result);
		}
	}


	public static TreeNode stringToTreeNode(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);

		int index = 1;
		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int leftNumber = Integer.parseInt(item);
				node.left = new TreeNode(leftNumber);
				nodeQueue.add(node.left);
			}

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int rightNumber = Integer.parseInt(item);
				node.right = new TreeNode(rightNumber);
				nodeQueue.add(node.right);
			}
		}
		return root;
	}

	public static String integerArrayListToString(List<Integer> nums, int length) {
		if (length == 0) {
			return "[]";
		}

		String result = "";
		for(int index = 0; index < length; index++) {
			Integer number = nums.get(index);
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayListToString(List<Integer> nums) {
		return integerArrayListToString(nums, nums.size());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			TreeNode root = stringToTreeNode(line);

//			List<Integer> ret = inorderTraversal(root);
			boolean ret = isValidBST(root);

//			String out = integerArrayListToString(ret);

			System.out.print(ret);
		}
	}
}
