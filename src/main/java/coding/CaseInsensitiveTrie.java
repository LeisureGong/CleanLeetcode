package coding;

import sun.text.normalizer.Trie;

import java.lang.reflect.Parameter;
import java.util.LinkedList;

/**
 * @author gonglei
 * @date 2020/7/7
 */
public class CaseInsensitiveTrie {

	private int SIZE = 26;
	private TrieNode root;

	CaseInsensitiveTrie() {
		root  = new TrieNode();
	}

	private class TrieNode {
		// how many words go through this node
		private int num;
		// TrieNode[26] in this case
		private TrieNode[] son;
		// end of s string
		private boolean isEnd;
		//
		private char val;
		// for DFS
		private boolean visited;

		TrieNode() {
			num = 1;
			son = new TrieNode[SIZE];
			isEnd = false;
			visited = false;
		}
	}

	public void insert(String str) {
		if (str == null || str.length() == 0) {
			return;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] == null) {
				node.son[pos] = new TrieNode();
				node.son[pos].val = letters[i];
			} else {
				node.son[pos].num++;
			}
			node = node.son[pos];
		}
		node.isEnd = true;
	}

	// count how many words start with the specific prefix
	public int countPrefix(String prefix) {
		if(prefix == null || prefix.length() == 0) {
			return -1;
		}

		TrieNode node = root;
		char[] letters = prefix.toCharArray();
		for (int i = 0, len = prefix.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] == null) {
				return 0;
			} else {
				node = node.son[pos];
			}
		}
		return node.num;
	}

	// search a word in the tree
	public boolean has(String str) {
		if(str == null || str.length() == 0) {
			return false;
		}

		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0,len = str.length();i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] != null) {
				node = node.son[pos];
			} else {
				return false;
			}
		}
		return node.isEnd;
	}

	// DFS use stack  --> like a 26-nary tree
	public void printAllWords() {
		TrieNode rootNode = root;
		if(root == null) return;
		LinkedList<TrieNode> list = new LinkedList<>();
		for (int i = 0; i < SIZE; i++) {
			TrieNode node = rootNode.son[i];
			if (node != null) {
				list.addLast(node);
				while (!list.isEmpty()) {
					TrieNode curNode = list.getLast();
					TrieNode firstChild = firstChildOf(curNode);
					while (firstChild != null) {
						list.addLast(firstChild);
						// dfs
						firstChild = firstChildOf(firstChild);
					}
					TrieNode strEnd = list.getLast();
					if(strEnd.isEnd) {
						printLinkedList(list);
					}
					list.removeLast();
				}
			}
			list.clear();
		}
	}

	// print each node in preNode
	public void preTraverse(TrieNode node) {
		if (node != null) {
			System.out.print(node.val + "-");
			for (TrieNode child : node.son) {
				preTraverse(child);
			}
		}
	}

	// get the first unvisited child of a node
	public TrieNode firstChildOf(TrieNode node) {
		if(node == null) return null;
		for(int i = 0; i < SIZE; i++) {
			TrieNode tmp = node.son[i];
			if(tmp != null && !tmp.visited) {
				tmp.visited = true;
				return tmp;
			}
		}
		return null;
	}

	public static void printLinkedList(LinkedList<TrieNode> list) {
		if (list == null || list.size() == 0) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = list.size();i < len; i++) {
			sb.append(list.get(i).val);
		}
		System.out.println(sb.toString());
	}

	public TrieNode getRoot() {
		return this.root;
	}

	public static void main(String[] args) {
		CaseInsensitiveTrie tree = new CaseInsensitiveTrie();
		String[] strs = {
				"banana",
				"band",
				"bee",
				"absolute",
				"acm"
		};
		String[] prefix = {
				"ba",
				"b",
				"band",
				"abc"
		};
		for (String str : strs) {
			tree.insert(str);
		}
		System.out.println(tree.has("abc"));
		tree.preTraverse(tree.getRoot());
		System.out.println("\n--------------------------");
		tree.printAllWords();
		for (String pre : prefix) {
			int num = tree.countPrefix(pre);
			System.out.println(pre + " " + num);
		}
	}
}
