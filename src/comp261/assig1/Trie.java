package comp261.assig1;

import java.util.*;

/**
 * This is an implementation of a trie, used for the search box.
 */

public class Trie {
	TrieNode root; // the root node of the trie

	public Trie() {
		root = new TrieNode();
	}

	/**
	 * Adds a given stop to the Trie.
	 */
	public void add(Stop stop) {
		// TODO
		// write the code to add a stop object into the trie
		TrieNode curr = root;
		String id = stop.getId();
		if(id == null || id.equals(""))  return ;
		for(int i = 0; i<id.length(); i++){
			Character ch = id.charAt(i);
			if(!curr.children.containsKey(ch)) {
				curr.children.put(ch,new TrieNode());
				curr.data.add(stop);
			}
			curr = curr.children.get(ch);
		}
		curr.data.add(stop);
		// END of TODO
	}

	/**
	 * Returns all the stops whose names start with a given prefix.
	 */
	public List<Stop> getAll(String prefix) {
		// TODO
		// write the code to get all the stops whose names match the prefix.
		if(prefix == null || prefix.equals("")) return null;
		TrieNode node = root;
		for(int i = 0; i<prefix.length(); i++){
			char ch = prefix.charAt(i);
			if(!node.children.containsKey(ch)) return null;
			node = node.children.get(ch);
			if(i == prefix.length()-1){
				return node.data;
			}
		}
		return null;

		// END of TODO
	}


	/**
	 * Represents a single node in the trie. It contains a collection of the
	 * stops whose names are exactly the traversal down to this node.
	 */
	private class TrieNode {
		List<Stop> data = new ArrayList<>();
		Map<Character, TrieNode> children = new HashMap<>();
		public TrieNode(){
			data = new ArrayList<>();
			children = new HashMap<>();
		}
	}
}