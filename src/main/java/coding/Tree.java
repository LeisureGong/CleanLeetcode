package coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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

	//中序和后序序列构建二叉树
	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		//仿照从前序和中序遍历序列构造二叉树
		if(postorder.length == 0) return null;

		//找到root节点
		int rootValue = postorder[postorder.length-1];
		TreeNode root = new TreeNode(rootValue);
		//fine the root index int the inorder array
		int rootIndex = findIndex(inorder,rootValue);

		//build the left child  tree
		int[] leftPostorder = Arrays.copyOfRange(postorder,0,rootIndex);
		int[] leftInorder = Arrays.copyOfRange(inorder,0,rootIndex);
		root.left = buildTree(leftInorder,leftPostorder);

		//build the right child tree
		int[] rightPostorder = Arrays.copyOfRange(postorder,rootIndex,postorder.length-1);
		int[] rightInorder = Arrays.copyOfRange(inorder,rootIndex+1,inorder.length);
		root.right = buildTree(rightInorder,rightPostorder);

		return root;
	}

	//find the index int the inorder array
	public static int findIndex(int[] inorder,int value){
		for(int i = 0;i < inorder.length;i++){
			if(value == inorder[i]){
				return i;
			}
		}
		return -1;
	}


	// 二叉树最近公共祖先
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		Deque<TreeNode> deque = new ArrayDeque<>();
		Map<TreeNode,TreeNode> parent = new HashMap<>();
		deque.push(root);
		parent.put(root,null);
		while(!parent.containsKey(p) || !parent.containsKey(q)){

			TreeNode node = deque.pop();

			if(node.left != null){
				parent.put(node.left,node);
				deque.push(node.left);
			}
			if(node.right != null){
				parent.put(node.right,node);
				deque.push(node.right);
			}
		}
		// p's ancestor
		Set<TreeNode> ancestor = new HashSet<>();
		while(p != null){
			ancestor.add(p);
			p = parent.get(p);
		}
		while(!ancestor.contains(q)){
			q = parent.get(q);
		}
		return q;
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

	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for(int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static String treeNodeToString(TreeNode root) {
		if (root == null) {
			return "[]";
		}

		String output = "";
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);
		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (node == null) {
				output += "null, ";
				continue;
			}

			output += String.valueOf(node.val) + ", ";
			nodeQueue.add(node.left);
			nodeQueue.add(node.right);
		}
		return "[" + output.substring(0, output.length() - 2) + "]";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[] inorder = stringToIntegerArray(line);
			line = in.readLine();
			int[] postorder = stringToIntegerArray(line);

			TreeNode ret = buildTree(inorder, postorder);

			String out = treeNodeToString(ret);

			System.out.print(out);
		}
	}
}
