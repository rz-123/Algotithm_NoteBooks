package Bidirectional_BFS_双向广搜;

// 单词接龙
// 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列
// 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk ：
// 每一对相邻的单词只差一个字母。
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中
// 注意， beginWord 不需要在 wordList 中。sk == endWord
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList
// 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目
// 如果不存在这样的转换序列，返回 0 。
// 测试链接 : https://leetcode.cn/problems/word-ladder/

import java.util.HashSet;
import java.util.List;

public class Code01_WordLadder {

    public static int ladderLength(String begin , String end , List<String> wordList){

        HashSet<String> dict = new HashSet<>(wordList) ;
        // 如果目标单词不在这个单词表里 直接返回0
        if (!dict.contains(end)) {
            return 0 ;
        }

        HashSet<String> smallLevel = new HashSet<>() ; // 数量小的一侧
        HashSet<String> bigLevel = new HashSet<>() ; // 数量大的一侧
        // 数量小的一侧锁扩展出的下一层列表
        HashSet<String> nextLevel = new HashSet<>() ;

        smallLevel.add(begin) ; // 最初 从开始单词开始扩
        bigLevel.add(end) ;

        // 因为两端扩，所以最开始的层数为2
        // 数量小的那一侧不为空 就继续，每次 层数++ ，最后返回层数 就是经过单词的数量
        for (int len = 2; !smallLevel.isEmpty() ; len++) {
            for (String w : smallLevel) {
                char[] word = w.toCharArray() ;
                for (int i = 0; i < word.length; i++) {
                    char old = word[i] ; // 每个字符都尝试
                    for (char change = 'a' ; change <= 'z' ; change ++) {
                        if (change != old) {
                            word[i] = change ; // 替代字符进行对比
                            String next = String.valueOf(word);
                            if (bigLevel.contains(next)) {
                                return len ;
                            }
                            if (dict.contains(next)) {
                                dict.remove(next) ;
                                nextLevel.add(next) ;
                            }
                        }
                     }
                    word[i] = old ;
                }
            }
            // 如果下一层的数量要比这层小
            // 那就把 nextLevel 当做 smallLevel
            if (nextLevel.size() <= bigLevel.size()) {
                HashSet<String> tmp = smallLevel ;
                smallLevel = nextLevel ;
                // nextLevel = tmp ;
            } else {
                // 否则那就是bigLevel小，那么就把bigLevel换成smallLevel
                HashSet<String> tmp = smallLevel ;
                smallLevel = bigLevel ;
                bigLevel = nextLevel ;
//                nextLevel  = tmp ;
            }
            nextLevel.clear();
        }
        return 0 ;
    }
}
