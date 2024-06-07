import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] sent = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        Trie trie = new Trie();

        for (int i = 0; i < dictionary.size(); i++) {
            trie.insert(dictionary.get(i), i);
        }
        for (int i = 0; i < sent.length; i++) {
            int search = trie.search(sent[i]);
            if (search == -1) {
                sb.append(sent[i]);
            } else {
                sb.append(dictionary.get(search));
            }
            if (i != sent.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    public void insert(String word, int ind) {
        int wordLength = word.length();
        TrieNode temp = root;
        for (int i = 0; i < wordLength; i++) {
            if (!temp.children.containsKey(word.charAt(i))) {
                TrieNode node = new TrieNode(word.charAt(i));
                temp.children.put(word.charAt(i), node);
            }
            temp = temp.children.get(word.charAt(i));
            if (i == wordLength - 1) {
                temp.isTerminal = true;
                temp.index = ind;
            }
        }

    }

    public int search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.children.containsKey(word.charAt(i))) {
                temp = temp.children.get(word.charAt(i));
                if (temp.isTerminal) {
                    return temp.index;
                }
            } else return -1;
        }
        return -1;
    }


}

class TrieNode {
    char value;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isTerminal;
    int index = -1;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminal = false;
    }
}





