package Breadth_First_Search_BFS_宽度优先遍历;

// 单词接龙 II
// 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化
// 一个表示此过程的 转换序列 是形式上像
// beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
// 每对相邻的单词之间仅有单个字母不同
// 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词
// 注意，beginWord 不必是字典 wordList 中的单词
// sk == endWord
// 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList
// 请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列
// 如果不存在这样的转换序列，返回一个空列表
// 每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回
// 测试链接 : https://leetcode.cn/problems/word-ladder-ii/

import java.util.*;

public class Code06_WordLadderII {

    public static HashSet<String> dict ;
    // 当前层
    public static HashSet<String> curLevel = new HashSet<>() ;
    // 下一层
    public static HashSet<String> nextLevel = new HashSet<>() ;

    // 反向图
    public static HashMap<String,ArrayList<String>> graph = new HashMap<>() ;
    // 存放有效生成路径
    public static LinkedList<String> path = new LinkedList<>() ;
    public static List<List<String>> ans = new ArrayList<>() ;

    public static void build(List<String> wordList){
        dict = new HashSet<>(wordList) ;
        graph.clear();
        ans.clear();
        curLevel.clear();
        nextLevel.clear();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        build(wordList);
        if (!dict.contains(endWord)) {
            return ans ;
        }
        if (bfs(beginWord , endWord)) {
            dfs(endWord , beginWord);
        }
        return ans ;
    }

    // 从begin -> end  ，遍历 建图，
    // 如果能找到 end , 就返回true
    public static boolean bfs(String begin , String end){
        boolean find = false ;
        curLevel.add(begin) ; // 加入 HashSet
        // 如果curleve不为空，就继续
        while (!curLevel.isEmpty()) {
            dict.removeAll(curLevel);  // 删除这层 是为了下边不重复
            for (String word : curLevel) {
                char[] w = word.toCharArray() ; // 转成字符
                for (int i = 0; i < w.length; i++) {
                    char old = w[i] ; //
                    for (char ch = 'a' ; ch <= 'z' ; ch++) {
                        w[i] = ch ; // 26个字母挨个对比
                        String str = String.valueOf(w);
                        // dict中有这个单词，并且不跟上边的word重复
                        if (dict.contains(str) && !str.equals(word)) {
                            // 如果找到end 就返回true
                            if (str.equals(end)) {
                                find = true ;
                            }
                            graph.putIfAbsent(str , new ArrayList<>()) ;// 建图
                            graph.get(str).add(word) ; // 在相应位置加入该单词
                            nextLevel.add(str) ; // 下一层中加入尝试的单词
                        }
                    }
                    w[i] = old ; // 恢复字符
                }
            }
            if (find) {
                return true ;  // 上边全部循环完成后，如果find为true才会返回true
            } else {
                // 否则 就是 curLevel 与 nextLevel 交换位置
                HashSet<String> tmp = curLevel ;
                curLevel = nextLevel ;
                nextLevel = tmp ;
                nextLevel.clear();
                // nextLevel当做curLevel 继续做上边的循环
                // curLevel当做nextLevel并且清空，进行下一层的单词存放
            }
        }
        return false ;
    }
    // 反向建图 end->begin
    public static void dfs (String word , String aim){
        path.addFirst(word); // word
        if (word.equals(aim)) {
            // 找到aim，在ans中加入该路径
            ans.add(new ArrayList<>(path)) ;
        } else if (graph.containsKey(word)) {
            for (String next : graph.get(word)) {
                dfs(next,aim); // 递归
            }
        }
        path.removeFirst() ;
    }
}
