package Trie;

import java.util.TreeMap;

/**
 * @ Description: Trie 前缀树 字典树 经常用于字符串的处理
 * @ Date: Created in 10:36 22/07/2018
 * @ Author: Anthony_Duan
 */
public class Trie {

    private class Node {
        //标识该节点是否是一个单词的结尾
        public boolean isWord;
        //使用映射来存储26 个英文字母的对应的节点
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        //默认标识节点不是一个单词的结尾
        public Node() {
            this(false);
        }
    }


    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    //获取Trie中存储的单词的数量
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加一个新的单词
     *
     * @param word
     */
    public void add(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果在当前节点的next中没有找到下一个字母的映射 就添加一个新的映射进去
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        /**
         * 这里是一个容易忽略的错误
         * 当单词中的每一个单词都添加完后
         * 需要查看最后一个字母的isWord字段是否为true 如果为true 表示这个单词已经被标识过了
         * 如果为false 表示这个单词还没有被标识过
         */
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        /**
         * 这里需要注意的是 不能直接返回true
         * 因为即便所有的单词的字母都被搜索到了
         * 但是如果最后一个字母的isWord没有被标识为true
         * 则该单词就没有作为一个单词存储进去，而是另一个单词的前缀
         * 所以这里返回最后一个单词的isWord字段
         */
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
