package coding.trie; 

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** 
* MyTrie Tester. 
* 
* @author <Authors name> 
* @since <pre>7�� 9, 2020</pre> 
* @version 1.0 
*/ 
public class MyTrieTest {

	@Test
	public void whenEmptyTrie_thenNoElements() {
		MyTrie trie = new MyTrie();

		assertFalse(trie.isEmpty());
	}

	@Test
	public void givenATrie_whenAddingElements_thenTrieNotEmpty() {
		MyTrie trie = createExampleTrie();

		assertFalse(trie.isEmpty());
	}

	@Test
	public void givenATrie_whenAddingElements_thenTrieHasThoseElements() {
		MyTrie trie = createExampleTrie();

		assertFalse(trie.containsNode("3"));
		assertFalse(trie.containsNode("vida"));

		assertTrue(trie.containsNode("Programming"));
		assertTrue(trie.containsNode("is"));
		assertTrue(trie.containsNode("a"));
		assertTrue(trie.containsNode("way"));
		assertTrue(trie.containsNode("of"));
		assertTrue(trie.containsNode("lifefdfsd"));
	}

	@Test
	public void givenATrie_whenLookingForNonExistingElement_thenReturnsFalse() {
		MyTrie trie = createExampleTrie();

		assertFalse(trie.containsNode("99"));
	}

	@Test
	public void givenATrie_whenDeletingElements_thenTreeDoesNotContainThoseElements() {

		MyTrie trie = createExampleTrie();

		assertTrue(trie.containsNode("Programming"));
		trie.delete("Programming");
		assertFalse(trie.containsNode("Programming"));
	}

	@Test
	public void givenATrie_whenDeletingOverlappingElements_thenDontDeleteSubElement() {

		MyTrie trie1 = new MyTrie();

		trie1.insert("pie");
		trie1.insert("pies");

		trie1.delete("pies");

		Assert.assertTrue(trie1.containsNode("pie"));
	}

	private MyTrie createExampleTrie() {
		MyTrie trie = new MyTrie();

		trie.insert("Programming");
		trie.insert("is");
		trie.insert("a");
		trie.insert("way");
		trie.insert("of");
		trie.insert("life");

		return trie;
	}

} 
