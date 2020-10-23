package top.yuwenxin.stuct;

import java.util.List;


@SuppressWarnings("all")
public class DictTree {
    private static class TrieNode { // 每个节点最多有26个不同的小写字母
        private boolean isEnd;
        private TrieNode[] next;
        public TrieNode() {
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    private TrieNode root;

    public DictTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.next[ch] == null)
                cur.next[ch] = new TrieNode();
            cur = cur.next[ch];
        }
        cur.isEnd = true; // 加上一个标记，表示为一个单词
    }

    public boolean search(String word) {
        TrieNode cur = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.next[ch] == null)
                return false;
            cur = cur.next[ch];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            int ch = prefix.charAt(i) - 'a';
            if (cur.next[ch] == null) return false; // 若还没遍历完给定的前缀子串，则直接返回false
            cur = cur.next[ch];
        }
        return true; // 直接返回true
    }

    public String searchShortestReplacement(String word){
        TrieNode cur = root;
        int len = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.next[ch] != null) sb.append(word.charAt(i));
            if (cur.next[ch].isEnd) return sb.toString();
            cur = cur.next[ch];
        }
        return word;
    }
}

class ReplaceWords{
    public static void main(String[] args) {
        System.out.println("hello");
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        DictTree dictTree = new DictTree();
        for (String s :
                dictionary) {
            dictTree.insert(s);
        }

        String[] ss = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (String s:
             ss) {
            res.append(dictTree.searchShortestReplacement(s)).append(' ');
        }
        return res.substring(0, res.length() - 1);
    }
}
