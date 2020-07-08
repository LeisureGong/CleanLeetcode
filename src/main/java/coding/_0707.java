package coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gonglei
 * @date 2020/7/7
 */
public class _0707 {

	public static void main(String[] args) {
		char[] chs = new char[]{'a','a','a','b','b','a','a'};
		System.out.println(compress(chs));
	}

	static class MyTrieTree {

		private MyTrieNode root;

		private class MyTrieNode {
			private int num;
			private MyTrieNode[] son;
			private boolean isEnd;
			private char val;
			private boolean visited;

			MyTrieNode() {
				num = 1;
				son = new MyTrieNode[26];
				isEnd = false;
				visited = false;
			}
		}
		MyTrieTree() {
			root = new MyTrieNode();
		}

		public void insert(String word) {
			if(word == null || word.length() == 0) {
				return;
			}
			MyTrieNode node = root;
			char[] letters = word.toCharArray();
			for(int i = 0;i < word.length();i++) {
				int pos = letters[i] - 'a';
				if(node.son[pos] == null) {
					node.son[pos] = new MyTrieNode();
					node.son[pos].val = letters[i];
				} else {
					node.son[pos].num++;
				}
				node = node.son[pos];
			}
			node.isEnd = true;
		}

		// get the first unvisited child of a node
		public MyTrieNode fistChildOf(MyTrieNode node) {
			if(node == null) return null;
			for (int i = 0;i < 26;i++) {
				MyTrieNode tmp = node.son[i];
				if(tmp != null && !tmp.visited) {
					tmp.visited = true;
					return tmp;
				}
			}
			return null;
		}

		// 遍历
		public List<String> traverse(char[][] board) {
			List<String> res = new ArrayList<>();
			// 保存遍历的node，方便打印
			LinkedList<MyTrieNode> list = new LinkedList<>();
			MyTrieNode rootNode = root;
			if(root == null) return Collections.emptyList();
			// 从根节点，遍历trie
			for (int i = 0; i < 26 && rootNode.son[i] != null; i++) {
				MyTrieNode sonOfRoot = rootNode.son[i];
				char sonVal = sonOfRoot.val;
				// 当前遍历中止位
				boolean flag = true;
				// 找到board中是否存在该char，剪枝

				// 反着来，现在board找到第一个字符board[i][j]
				// 看board相邻的三个字符在字符表中是否存在
				// 如果都不存在，则提前返回
				for (int j = 0; j < board.length;j++) {
					for (int k = 0; k < board[0].length; k++) {
						// 剪枝遍历
						if(board[i][j] == sonVal) {

						}
					}
				}


			}
		}

		// DFS
		public void dfs()


		public String printTrieNode(LinkedList<MyTrieNode> list) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0;i < list.size(); i++) {
				sb.append(list.get(i).val);
			}
			return sb.toString();
		}
	}



	public List<String> findWords(char[][] board, String[] words) {

		List<String> res = new ArrayList<>();
		// 构建单词前缀树
		MyTrieTree trieTree = new MyTrieTree();
		for(String word : words) {
			trieTree.insert(word);
		}
		res = trieTree.traverse(board);
		return res;
	}










	// 压缩字符串
	public static int compress(char[] chars) {

		if (chars.length <= 1) return chars.length;

		int i = 0,j = 1,index = i;
		while(j < chars.length) {
			while(j < chars.length && chars[i] == chars[j]) {
				j++;
			}
			int count = j - i;
			chars[index++] = chars[i];
			if(count > 1) {
				String countS = String.valueOf(count);
				for(int k = 0; k < countS.length(); k++) {
					chars[index++] = countS.charAt(k);
				}
			}
			i = j;
		}
		return index;
	}
}
