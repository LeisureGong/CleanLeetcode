package coding;

/**
 * @author gonglei
 * @date 2020/7/9
 */
class WordDictionary {

	private TrieNode root;

	private class TrieNode {
		private TrieNode[] son;
		private boolean isEnd;
		private char val;

		TrieNode() {
			son = new TrieNode[26];
			isEnd = false;
		}
	}
	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		if(word == null || word.length() == 0) return;

		TrieNode node = root;
		char[] letters = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			int pos = letters[i] - 'a';
			if(node.son[pos] == null) {
				node.son[pos] = new TrieNode();
				node.son[pos].val = letters[i];
			}
			node = node.son[pos];
		}
		node.isEnd = true;
	}
	boolean flag;
	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		if(word == null || word.length() == 0) {
			return false;
		}
		TrieNode node = root;
		char[] letters = word.toCharArray();
		flag = false;
		dfs(node,letters,0);
		return flag;
	}

	public void dfs(TrieNode root,char[] letters, int index) {
		if(flag || index > letters.length) return ;
		// 当index为letters.length-1且.
		if(index == letters.length - 1 && letters[index] == '.') {
			for(int i = 0;i < 26 && root.son[i] != null;i++) {
				flag = true;
				return;
			}
		}

		if(index == letters.length) {
			flag = root.isEnd;
			return;
		}

		if(letters[index] == '.') {
			for(int i = 0; i < 26; i++) {
				if(root.son[i] != null)
					dfs(root.son[i], letters,index + 1);
			}
		} else {
			int pos = letters[index] - 'a';
			if(root.son[pos] != null) {
				dfs(root.son[pos],letters,index + 1);
			} else {
				return;
			}
		}
		return;
	}


//			'[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]

	public static void main(String[] args) {
		WordDictionary dict = new WordDictionary();
		dict.addWord("bad");
		dict.addWord("dad");
		dict.addWord("mad");
		System.out.println(dict.search("b.."));
	}
}