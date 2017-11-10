package playground;

import static playground.Utils.*;

// Trie: insert/print (located in Utils)

public class TrieInsert {
    public static void main(String[] args) {
        TrieNode n1 = TrieNode.create("test");
        TrieNode.insert(n1, "testing");
        TrieNode.insert(n1, "abc");
        TrieNode.insert(n1, "");
        print(n1);
    }
}
