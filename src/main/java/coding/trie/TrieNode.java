package coding.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gonglei
 * @date 2020/7/9
 */
public class TrieNode {

	private HashMap<Character, TrieNode> children = new HashMap<>();
	private boolean endOfWord;

	Map<Character, TrieNode> getChildren() {
		return children;
	}

	boolean isEndOfWord() {
		return endOfWord;
	}

	void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}
}
