package Trie_前缀树;

// 用类描述实现前缀树
public class Code01_TrieTree {

    // 路过Pass+1，结束End+1
    // 数组
    class Trie1 {
        class TrieNode{
            public int pass ; // 路过
            public int end ; // 结束
            public TrieNode[] nexts ; // 下一个节点

            public TrieNode(){
                pass = 0 ;
                end= 0 ;
                nexts = new TrieNode[26] ; // 26个字母，26个分支
            }
        }

        private TrieNode root ; // 头结点
        public Trie1(){
            root = new TrieNode() ;
        }

        // 添加字符串 word
        public void insert(String word){
            TrieNode node = root ; // 新建头结点
            node.pass++ ; // pass ++
            for (int i = 0,path; i < word.length(); i++) {
                path = word.charAt(i) - 'a' ; // 决定选几编号的路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode() ; // 如果为空，新增节点
                }
                node = node.nexts[path]; // 如果有的话，node移到下一个节点
                node.pass++ ;
            }
            node.end++ ; // 最后在结束的节点上end+1，
        }


        // 在前缀树中删除字符串
        public void erase(String word){
            // 判断 前缀树中是否有该字符串
            if (countWordEqualTo(word) > 0) {
                TrieNode node = root ;
                node.pass-- ;  // 头结点--
                for (int i = 0,path; i < word.length(); i++) {
                    path = word.charAt(i) - 'a' ;
                    // 如果该节点pass为1，那么--等于0，那么该条线后边的节点都不要了，直接返回
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null ;
                        return;
                    }
                    node = node.nexts[path] ;
                }
                node.end-- ; // 如果退出循环，该节点的end--
            }
        }


        // 查看前缀树中，word单词出现几次
        public int countWordEqualTo(String word){
            TrieNode node = root ; // 头结点
            for (int i = 0 , path; i < word.length(); i++) {
                path = word.charAt(i) - 'a' ;
                if (node.nexts[path] == null) {
                    return  0 ;
                }
                node = node.nexts[path] ; // node往下移
            }
            return node.end ; // 走到底，查看底部的节点的end值就是字符串word出现了几次
        }

        // 查看前缀树中，有多少单词以pre做前缀
        // 也就是说有多少单词路过pre,最终返回pass值
        public int countWordsStartingWith(String pre){
            TrieNode node = root ;
            for (int i = 0,path; i < pre.length(); i++) {
                path = pre.charAt(i) - 'a' ;
                if (node.nexts[path] == null){
                    return 0 ;
                }
                node = node.nexts[path] ;
            }
            return node.pass ;
        }
    }
}
