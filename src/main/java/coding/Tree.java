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
import java.util.stream.Collectors;

import static com.sun.deploy.config.Config.booleanToString;

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

	//102二叉树的层次遍历
	public static List<List<Integer>> levelOrder(TreeNode root) {
		if(root == null) return Collections.emptyList();
		List<List<Integer>> lists = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int level = 0;
		while(!queue.isEmpty()){
			lists.add(new ArrayList<>());

			int level_length = queue.size();
			for(int i = 0;i < level_length;i++){
				TreeNode node = queue.remove();
				lists.get(level).add(node.val);
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
			level++;
		}
		Collections.reverse(lists);
		return lists;
	}

	//二叉树的最小深度
	public static int minDepth(TreeNode root) {
		if(root == null) return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int level = 0;
		while(!queue.isEmpty()){
			level++;
			//遍历一层,注意，这里要取队列的长度
			int level_length = queue.size();
			for(int i = 0;i < level_length;i++){
				TreeNode node = queue.remove();
				if(node.left == null || node.right == null && node != root) return level;
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
		}
		return level;
	}

	//112求根到叶子节点路径之和
	static int sum;
	public static int sumNumbers(TreeNode root) {
		if(root == null) return 0;
		sum = 0;
		preOrder(root,root.val);
		return sum;
	}
	public static void preOrder(TreeNode node,int temp){
		if(node.left == null && node.right==null){
			sum += temp;
			return;
		}
		if(node.left != null){
			int lVal = node.left.val;
			temp *= 10;
			preOrder(node.left,  temp+lVal);
			temp /= 10;
		}
		if(node.right != null){
			int rVal = node.right.val;
			temp *= 10;
			preOrder(node.right, temp+rVal);
			temp /= 10;
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
//			line = in.readLine();
//			int sum = Integer.parseInt(line);

			sumNumbers(root);

//			boolean ret = pathSum(root, sum);
//
//			String out = booleanToString(ret);
//
//			System.out.print(out);
		}
	}
}
